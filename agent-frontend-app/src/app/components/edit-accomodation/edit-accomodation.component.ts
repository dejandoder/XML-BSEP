import { Component, OnInit } from '@angular/core';
import { AccomodationUnit } from 'src/app/model/AccomodationUnit';
import { AccomodationUnitService } from 'src/app/service/AccomodationUnitService';
import { ImageService } from 'src/app/service/ImageService';
import { PricePlanService } from 'src/app/service/PricePlanService';
import { PricePlan } from 'src/app/model/PricePlan';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-edit-accomodation',
  templateUrl: './edit-accomodation.component.html',
  styleUrls: ['./edit-accomodation.component.css']
})
export class EditAccomodationComponent implements OnInit {

  accUnit : AccomodationUnit;
  images : String[] = [];
  pricePlans : PricePlan[] = [];
  newPricePlan : PricePlan = new PricePlan();
  ppErrorMessage : string = "";
  dates : Date[] = [];

  constructor(private accService : AccomodationUnitService, private imageService : ImageService, private ppService : PricePlanService ) {
    this.accUnit = accService.getEditingAccUnit();
    this.loadAccUnit();
    setInterval(
        () => {
          if(this.accUnit.id != accService.getEditingAccUnit().id){
             this.loadAccUnit();
            }
          },
          1000);
   }

  ngOnInit() {
  }

  addPricePlanClick(){
    if(this.newPricePlan.pricePerNight <= 0 || this.dates.length != 2){
      this.ppErrorMessage = "Fill inputs correctly!";
      setTimeout(() => {
        this.ppErrorMessage = "";
      }, 3000);
      return;
    }

    this.newPricePlan.fromDate = this.dates[0];
    this.newPricePlan.toDate = this.dates[1];
    this.newPricePlan.accID = this.accUnit.id;

    this.ppService.addPricePlan(this.newPricePlan).subscribe(
      data =>{
        this.ppService.getPricePlansByAccomodationUnit(this.accUnit.id).subscribe(
          data =>{
            this.pricePlans = data;
          }
        )
        this.dates =[];
        this.newPricePlan = new PricePlan();
      }
    
    )

  }

  deletePricePlan(plan : PricePlan){

  }

  loadAccUnit(){
    this.images = [];
    this.newPricePlan = new PricePlan();
    this.ppErrorMessage = "";
    this.dates = [];
    this.accUnit = this.accService.getEditingAccUnit();
      this.imageService.getImagesIdsByAccomodationUnit(this.accUnit).subscribe(
        data => {
          for(let id of data){
            this.imageService.getImage(id).subscribe(
              data =>{
                let reader = new FileReader();
                reader.addEventListener("load", () => {
                this.images.push(reader.result as string);
              }, false);
              reader.readAsDataURL(data);
            }
          )
        }
      });
      this.ppService.getPricePlansByAccomodationUnit(this.accUnit.id).subscribe(
        data =>{
          this.pricePlans = data;
        }
      )
  }
}
