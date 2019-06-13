import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AccomodationUnit } from 'src/app/model/AccomodationUnit';
import { AccomodationType } from 'src/app/model/AccomodationType';
import { AccomodataionService } from 'src/app/model/AccomodationService';
import { AccomodationUnitService } from 'src/app/service/AccomodationUnitService';
import * as $ from 'jquery';
import { ImageService } from 'src/app/service/ImageService';
declare var ol: any;

@Component({
  selector: 'app-add-accomodation',
  templateUrl: './add-accomodation.component.html',
  styleUrls: ['./add-accomodation.component.css']
})
export class AddAccomodationComponent implements OnInit {

  map : any;
  newUnit : AccomodationUnit = new AccomodationUnit();
  selectedFiles : any[];
  selectImagesMessage = "Select images";
  imgSrc : any;
  accTypes : AccomodationType[];
  accServices : AccomodataionService[];
  selectedType : AccomodationType = {"id": -1, "name": "Accomodation type", "disabled" : true};
  selectedCategory : number = -1;
  errorMessage = "";

  constructor(private http : HttpClient, private accService : AccomodationUnitService, private imageService : ImageService ){   
    accService.getAllAccomodationServices().subscribe(
      data =>{
        this.accServices = data;
      }
    )
    accService.getAllAccomodationTypes().subscribe(
      data =>{
        this.accTypes = data;
        this.accTypes.push(this.selectedType);
      }
    )
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
  	
      var coord = ol.proj.toLonLat(event.coordinate); 
      this.setMarker(coord);

      this.http.get<any>('map/reverse?format=json&lon=' + coord[0] + '&lat=' + coord[1]).subscribe(
        data =>{
          this.newUnit.location.displayName = data.display_name;
          this.newUnit.location.lattitude = data.lat;
          this.newUnit.location.longitude = data.lon;
        })
  	});
  }

  setCenter(longitude, latitude) {
    var view = this.map.getView();
    view.setCenter(ol.proj.fromLonLat([longitude,latitude]));
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

  onFilesSelected(event){
    this.selectedFiles = event.target.files;

    if(this.selectedFiles.length == 0){
      this.selectImagesMessage = "Select images";
    }else{
      this.selectImagesMessage = "";
    
      for(let file of this.selectedFiles){
        this.selectImagesMessage = this.selectImagesMessage + file.name +", ";
      }
    }
  }

  uploadImages(accUnitId){
      let fd = new FormData();
      for ( let file of this.selectedFiles){
        fd.append('file', file);
      }
      fd.append('accId', accUnitId);
      this.imageService.uploadImages(fd);
  }

 selectTypeClick(type){
  this.selectedType = type;
 }

 selectCategoryClick(category : number){
   this.selectedCategory = category;
 }
 
 addUnitClick(){
   let errorCount : number = 0;

   if(this.newUnit.name == null){
     $("#name").addClass('border-danger');
     errorCount++;
   }else{
    $("#name").removeClass('border-danger');
   }

   if(this.selectedCategory == -1){
     $("#category").addClass('border-danger');
     errorCount++;
   }else{
     this.newUnit.category = this.selectedCategory.toString();
     $("#category").removeClass('border-danger');
   }

   if(this.selectedType.id == -1){
     $("#type").addClass('border-danger');
     errorCount++;
   }else{
     this.newUnit.accomodationType = this.selectedType;
     $("#type").removeClass('border-danger');
   }

   if(isNaN(this.newUnit.capacity) || this.newUnit.capacity<0){
     $("#capacity").addClass("border-danger");
     errorCount++;
   }else{
    $("#capacity").removeClass("border-danger");
   }

   if(isNaN(this.newUnit.cancelingPeriod) || this.newUnit.cancelingPeriod<0){
    $("#cancelingPeriod").addClass("border-danger");
    errorCount++;
   }else{
    $("#cancelingPeriod").removeClass("border-danger");
    }

    if(this.selectImagesMessage == "Select images"){
      errorCount++;
      this.errorMessage = "Select at least one image!";
      setInterval(()=>{
        this.errorMessage = "";
      },
      3000)
    }

    if(this.newUnit.location.displayName == "Location"){
      errorCount++;
      this.errorMessage = "Select location!";
      setInterval(()=>{
        this.errorMessage = "";
      },
      3000)
    }

    if(errorCount != 0 && this.errorMessage != "Select location!" && this.errorMessage != "Select at least one image!"){
      this.errorMessage = "Fill required fields corectly!";
      setTimeout(()=>{
        this.errorMessage = "";
      },
      3000)
    }

    if(errorCount == 0){
      for(let service of this.accServices){
        if(service.isChecked){
          this.newUnit.services.push(service);
        }
      }
      this.accService.addNewAccomodationUnit(this.newUnit).subscribe(
        data => {
          this.uploadImages(data.id);
          this.accService.getAllAccUnits().subscribe(
            data => {
              this.accService.setCurrentAccomodationUnits(data);
            }
          )
          //ciscenje forme
          this.selectedType  = {"id": -1, "name": "Accomodation type", "disabled" : true};
          this.accService.getAllAccomodationServices().subscribe(
            data =>{
              this.accServices = data;
            }
          )
          this.accService.getAllAccomodationTypes().subscribe(
            data =>{
              this.accTypes = data;
              this.accTypes.push(this.selectedType);
            }
          )
          this.newUnit = new AccomodationUnit();
          this.selectImagesMessage = "Select images"
        }
      )
    }else{
      return;
    }

 }
}

