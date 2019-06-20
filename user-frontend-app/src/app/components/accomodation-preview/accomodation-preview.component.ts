import { Component, OnInit, Input, TemplateRef } from '@angular/core';
import { AccomodationUnit } from 'src/app/model/AccomodationUnit';
import { ReservationDTO } from 'src/app/model/ReservationDTO';
import { AccomodationUnitService } from 'src/app/service/AccomodationUnitService';
import { Router } from '@angular/router';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';

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
  hideReservationButton : boolean = false;
  reservationMessage : string;

  constructor(private accService : AccomodationUnitService, private modalService: BsModalService) { }

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

        this.reservationMessage = "Your reservation has been recorded. You will be able to see it after agent approves it!";
      },
      error =>{
        this.modalRef = this.modalService.show(template,this.config);

        this.reservationMessage = "Someone reserved this accomodation in the mean time, reserve another accomodation!";
      }
    )
  }

}
