import { Component, OnInit, Input, TemplateRef } from '@angular/core';
import { AccomodationUnit } from 'src/app/model/AccomodationUnit';
import { ReservationDTO } from 'src/app/model/ReservationDTO';
import { AccomodationUnitService } from 'src/app/service/AccomodationUnitService';
import { Router } from '@angular/router';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { AuthService } from 'src/app/service/AuthService';
import { RecensionDTO } from 'src/app/model/RecensionDTO';
import { RecenesionService } from 'src/app/service/RecensionService';

@Component({
  selector: 'app-accomodation-preview',
  templateUrl: './accomodation-preview.component.html',
  styleUrls: ['./accomodation-preview.component.css']
})
export class AccomodationPreviewComponent implements OnInit {

  @Input('unit') accUnitInput : AccomodationUnit;
  accUnit : AccomodationUnit;
  @Input('dates') datesInput : Date[];
  dates : Date[];
  modalRef: BsModalRef;
  config = {
    backdrop: true,
    ignoreBackdropClick: true
  };
  config2 = {
    backdrop: true
  };
  hideReservationButton : boolean = false;
  reservationMessage : string;
  userLogged : boolean;
  recensions : RecensionDTO[];

  constructor(private accService : AccomodationUnitService, private modalService: BsModalService,
     private authService : AuthService, private recService : RecenesionService) {
    this.userLogged = authService.isUserLogged();
    setInterval(() => {
      this.userLogged = authService.isUserLogged();
    }, 500);
   }

  ngOnInit() {
    this.accUnit = this.accUnitInput;
    this.dates = this.datesInput;
  }

  okClick(){
    this.modalRef.hide();
    this.hideReservationButton = true;
  }

  reserveClick(template: TemplateRef<any>){
    let reservationDTO = new ReservationDTO();
    reservationDTO.fromDate = this.dates[0];
    reservationDTO.toDate = this.dates[1];
    reservationDTO.accId = this.accUnit.id;

   
    this.accService.reserveAccomodation(reservationDTO).subscribe(
      data =>{
        this.modalRef = this.modalService.show(template,this.config);

        this.reservationMessage = "Your reservation has been recorded. You can see it in reservation tab!";
      },
      error =>{
        this.modalRef = this.modalService.show(template,this.config);

        this.reservationMessage = "Someone reserved this accomodation in the mean time, reserve another accomodation!";
      }
    )
  }

  showRecensions(template: TemplateRef<any>){

    this.modalRef = this.modalService.show(template,this.config2);

    this.recService.getRecensions(this.accUnit.id).subscribe(
      data =>{
          this.recensions = data;
      }
    )
  }

}
