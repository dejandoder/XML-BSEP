import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
declare var ol: any;

@Component({
  selector: 'app-add-accomodation',
  templateUrl: './add-accomodation.component.html',
  styleUrls: ['./add-accomodation.component.css']
})
export class AddAccomodationComponent implements OnInit {

  map : any;
  latitude: number = 18.5204;
  longitude: number = 73.8567;

  constructor(private http : HttpClient) {   
  }

  ngOnInit() {
    this.initilizeMap();
  }

  initilizeMap(){
    this.map = new ol.Map({
      target: 'map',
      layers: [
        new ol.layer.Tile({
          source: new ol.source.OSM()
        })
      ],
      view: new ol.View({
        center: ol.proj.fromLonLat([73.8567, 18.5204]),
        zoom: 8
      })
    });

    this.map.on('click', (event) => {
  		event.preventDefault();
  		/* Act on the event */
  		var coord = ol.proj.toLonLat(event.coordinate);
      this.setMarker(coord)

      this.http.get('map/reverse?format=json&lon=' + coord[0] + '&lat=' + coord[1]).subscribe(
        data =>{
          console.log(data);
        })
  	});
  }

  setCenter() {
    var view = this.map.getView();
    view.setCenter(ol.proj.fromLonLat([this.longitude, this.latitude]));
    view.setZoom(8);
  }

 
  setMarker(coords){

    var layersToRemove = [];
    this.map.getLayers().forEach(function (layer) {
        if (layer.get('name') != undefined && layer.get('name') === 'markerVector') {
            layersToRemove.push(layer);
        }
    });

    var len = layersToRemove.length;
    for(var i = 0; i < len; i++) {
        this.map.removeLayer(layersToRemove[i]);
    }

    var marker = new ol.Feature({
        geometry: new ol.geom.Point(ol.proj.transform(coords, 'EPSG:4326', 'EPSG:3857')),
    });

    var markers = new ol.source.Vector({
        features: [marker]
    });

    var markerVectorLayer = new ol.layer.Vector({
        source: markers,
    });
    this.map.addLayer(markerVectorLayer);
    markerVectorLayer.set('name','markerVector');
  }

}
