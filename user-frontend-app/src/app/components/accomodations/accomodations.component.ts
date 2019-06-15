import { Component, OnInit, TemplateRef } from '@angular/core';
import * as $ from 'jquery';
import { HttpClient } from '@angular/common/http';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { SearchDTO } from 'src/app/model/SearchDTO';
import { AccomodationType } from 'src/app/model/AccomodationType';
import { AccomodationService } from 'src/app/model/AccomodationService';
import { AccomodationUnitService } from 'src/app/service/AccomodationUnitService';
import { AccomodationUnit } from 'src/app/model/AccomodationUnit';
 
@Component({
  selector: 'app-accomodations',
  templateUrl: './accomodations.component.html',
  styleUrls: ['./accomodations.component.css']
})
export class AccomodationsComponent implements OnInit {

  address : string;
  modalRef: BsModalRef;
  config = {
    backdrop: true,
    ignoreBackdropClick: true
  };
  searchDTO : SearchDTO = new SearchDTO();
  accTypes : AccomodationType[] = [];
  accServices : AccomodationService[] = [];
  searchError : string;
  searchResults : AccomodationUnit[];
  categorySorting = -1;

  constructor(private http : HttpClient, private modalService: BsModalService, private accService : AccomodationUnitService) {

    accService.getAccTypes().subscribe(
      data =>{
        this.accTypes = data;
        this.accTypes.push(this.searchDTO.type);

      }
    )
    
    accService.getAccServices().subscribe(
      data => {
        this.accServices = data;
      })

    http.get<any>('testApi/test').subscribe(
      data => {
        this.searchResults = data;
      }
    )
   }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template,this.config);
  }

  ngOnInit() {
  }

  searchClick(){
    if(this.searchDTO.dates.length != 2){
      this.searchError = "Select dates!";
        setTimeout(()=>{
          this.searchError = "";
        },2000);
        return;
    }

    if(this.searchDTO.persons <= 0 || this.searchDTO.persons == null){
      this.searchError = "Select number of persons!";
        setTimeout(()=>{
          this.searchError = "";
        },2000);
        return;
    }

    //preuzimanje koordinata lokacije
    let address = $("#autocomplete").val().replace(' ','+');
    this.http.get<any>("google/json?address="+address+"&key=AIzaSyDnihJyw_34z5S1KZXp90pfTGAqhFszNJk").subscribe(
      data => {
        this.searchDTO.location.longitude = data.results[0].geometry.location.lng;
        this.searchDTO.location.lattitude = data.results[0].geometry.location.lat;
      },
      eror =>{
        this.searchError = "Select location!";
        setTimeout(()=>{
          this.searchError = "";
        },2000);
        return;
      }
    )

    for(let service of this.accServices){
      if(service.isChecked){
        this.searchDTO.services.push(service);
      }
    }
  }

  okClick(){
    let error = 0;
    if(this.searchDTO.maxDistance != null && this.searchDTO.maxDistance <= 0){
      $("#maxDistance").addClass("border-danger");
      setTimeout(()=>{
        $("#maxDistance").removeClass("border-danger");
      },2000);
      error++;
    }
    if(this.searchDTO.cancelationPeriod != null && this.searchDTO.cancelationPeriod <= 0){
      $("#cancelationPeriod").addClass("border-danger");
      setTimeout(()=>{
        $("#cancelationPeriod").removeClass("border-danger");
      },2000);
      error++;
    }
    if(error > 0) return;
    this.modalRef.hide();
  }

  sortByPrice(param : number){
    this.categorySorting = -1;
    if(param == 1){
      this.searchResults.sort(function(a,b){
        return b.price - a.price;
      })
    }else{
      this.searchResults.sort(function(a,b){
        return a.price - b.price;
      })
    }
  }

  sortByDistance(){
    this.categorySorting = -1;
    this.searchResults = this.searchResults.sort(function(a,b){
      return a.distance-b.distance;
    })
  }

  sortByRating(){
    this.categorySorting = -1;
    this.searchResults = this.searchResults.sort(function(a,b){
      return b.rating - a.rating;
    })
  }

  sortByCategory(){
    $(".form-check-input").each(
      function(){
        this.checked=false;
      }
    )
    this.searchResults = this.searchResults.sort(
    (a,b) =>{
      if(a.category == this.categorySorting && b.category == this.categorySorting) return -1;
      if(a.category == this.categorySorting && b.category != this.categorySorting) return -1;
      if(a.category != this.categorySorting && b.category == this.categorySorting) return  1;
    })
  }

}
