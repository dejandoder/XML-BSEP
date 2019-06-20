import { Component, OnInit } from '@angular/core';
import { AccomodationUnit } from 'src/app/model/AccomodationUnit';
import { AccomodationUnitService } from 'src/app/service/AccomodationUnitService';
import { ImageService } from 'src/app/service/ImageService';
import { PricePlanService } from 'src/app/service/PricePlanService';
import { PricePlan } from 'src/app/model/PricePlan';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { ReservationDTO } from 'src/app/model/ReservationDTO';
import { ReservationService } from 'src/app/service/ReservationService';

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
  disableDates : Date[] = [];

  reservations : ReservationDTO[] = [];

  resErrorMessage = "";

  constructor(private accService : AccomodationUnitService, private imageService : ImageService, private ppService : PricePlanService, private resService : ReservationService ) {
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
      },
      error => {
        this.ppErrorMessage = "Price plan overlaps with current plans!"
        setTimeout(() => {
         this.ppErrorMessage = "" 
        }, 2000);
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
      this.resService.getReservationsByAccUnit(this.accUnit.id).subscribe(
        data =>{
          this.reservations = data;
        }
      )
  }

  disableReservations(){
    if(this.disableDates.length != 2){
      this.resErrorMessage = "Select dates!";
      setTimeout(() => {
        this.resErrorMessage = "";
      }, 2000);
    }else{
      let res = new ReservationDTO();
      res.fromDate = this.disableDates[0];
      res.toDate = this.disableDates[1];
      res.accId = this.accUnit.id;
      this.resService.agentReservation(res).subscribe(
        data =>{
          this.resService.getReservationsByAccUnit(this.accUnit.id).subscribe(
            data =>{
              this.reservations = data;
            }
          );
          this.disableDates = [];
        }
      )
    }
  }

  approveReservation(res : ReservationDTO){
    this.resService.approveReservation(res.id).subscribe(
      data =>{
        this.resService.getReservationsByAccUnit(this.accUnit.id).subscribe(
          data =>{
            this.reservations = data;
          }
        )
      },
      error =>{
        this.resErrorMessage = "Reservation has been canceled!";
        setTimeout(() => {
          this.resErrorMessage = "";
        }, 2000);
      }
    )
  }

  declineReservation(res : ReservationDTO){
    this.resService.declineReservation(res.id).subscribe(
      data =>{
        this.resService.getReservationsByAccUnit(this.accUnit.id).subscribe(
          data =>{
            this.reservations = data;
          }
        )
      },
      error=>{
        this.resErrorMessage = "Reservation has been canceled!";
        setTimeout(() => {
          this.resErrorMessage = "";
        }, 2000);
      }
    )
  } 

  confirmReservation(res : ReservationDTO){
    this.resService.confirmReservation(res.id).subscribe(
      data =>{
        this.resService.getReservationsByAccUnit(this.accUnit.id).subscribe(
          data =>{
            this.reservations = data;
          }
        )
      },
      error =>{
        this.resErrorMessage = "Reservation has been canceled!";
        setTimeout(() => {
          this.resErrorMessage = "";
        }, 2000);
      }
    )
  }

}
