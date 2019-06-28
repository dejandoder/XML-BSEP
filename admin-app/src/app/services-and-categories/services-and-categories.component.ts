import { Component, OnInit } from '@angular/core';
import { AccService } from '../model/AccService';
import { AccServicesService } from '../service/AccServicesService';
import { AccomodationType } from '../model/AccomodationType';
import { AccTypeService } from '../service/AccTypeService';
import * as $ from 'jquery';

@Component({
  selector: 'app-services-and-categories',
  templateUrl: './services-and-categories.component.html',
  styleUrls: ['./services-and-categories.component.css']
})
export class ServicesAndCategoriesComponent implements OnInit {

  services : AccService[] = [];
  newService : AccService = new AccService();
  accTypes : AccomodationType[] = [];
  newAccType : AccomodationType = new AccomodationType();
  typesError : boolean = false;
  servicesError : boolean = false;
  addTypeError : boolean = false;
  addServiceError : boolean = false;

  constructor(private accServicesService : AccServicesService, private accTypeService : AccTypeService) {
    accServicesService.getAllAccServices().subscribe(
      data => {
        this.services = data;
      }
    );

    accTypeService.getAllAccTypes().subscribe(
      data => {
        this.accTypes = data;
      }
    )
  }

  ngOnInit() {
  }

  addNewAccType(){
    if(this.newAccType.name != ""){
      this.accTypeService.addNewAccType(this.newAccType).subscribe(
        data => {
          this.accTypes = data;
          this.newAccType.name = "";
        },
        error =>{
          this.addTypeError = true;
          setInterval(
            () =>{
              this.addTypeError = false;
            },
            2000
          )
        }
      )
    }else{
      $("#newAccTypeInput").addClass("border border-danger");
      setInterval(function(){$("#newAccTypeInput").removeClass("border border-danger");}, 2000);
    }
  }

  deleteAccType(accType : AccomodationType){
    this.accTypeService.removeAccType(accType).subscribe(
      data => {
        this.accTypes = data;
      },
      error =>{
        this.typesError = true;
        setTimeout(
        () => {
          this.typesError = false;
        }
        , 2000)
      }
    )
  }

  addNewAccService(){
   if(this.newService.name != ""){
    this.accServicesService.addNewAccServide(this.newService).subscribe(
      data => {
        this.services = data;
        this.newService.name = "";
      },
      error =>{
          this.addServiceError = true;
          setTimeout(
            () =>{
              this.addServiceError = false;
            },
            2000
          )
        }
    )
   }else{
      $("#newAccServiceInput").addClass("border boreder-warining");
      setTimeout(function(){$("#newAccServiceInput").removeClass("border boreder-warining");}, 2000)
   }
  }

  deleteAccService(accService : AccService){
    this.accServicesService.removeAccService(accService).subscribe(
      data => {
        this.services = data;
      },
      error => {
        this.servicesError = true;
        setTimeout(
          () => {
            this.servicesError = false;
          },
          2000
        )
      }
    )
  }
}
