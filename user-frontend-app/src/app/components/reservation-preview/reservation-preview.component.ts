import { Component, OnInit, Input, TemplateRef } from '@angular/core';
import { ReservationDTO } from 'src/app/model/ReservationDTO';
import { ReservationService } from 'src/app/service/ReservationService';
import { Router } from '@angular/router';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { RecenesionService } from 'src/app/service/RecensionService';
import * as $ from 'jquery'
import { RecensionDTO } from 'src/app/model/RecensionDTO';

@Component({
  selector: 'app-reservation-preview',
  templateUrl: './reservation-preview.component.html',
  styleUrls: ['./reservation-preview.component.css']
})
export class ReservationPreviewComponent implements OnInit {

  @Input('res') resInput : ReservationDTO;
  res : ReservationDTO;
  modalRef: BsModalRef;
  config = {
    backdrop: true
  };

  constructor(private resService : ReservationService, private router : Router,  private modalService: BsModalService,
    private recService : RecenesionService) {

   }

  ngOnInit() {
    this.res = this.resInput;
  }

  cancelClick(){
    this.resService.deleteReservation(this.res.id).subscribe(
      data =>{
        this.res.status = "DELETED";
        this.res.cancelable = false;
      }
    )
  }

  showModal(template: TemplateRef<any>){
    this.modalRef = this.modalService.show(template,this.config);
  }

  okClick(){
    let error = 0;
    
    if($("#recTextarea").val() == ""){
      error++;
      $("#recTextarea").addClass('border-danger');
      setTimeout(() => {
        $("#recTextarea").removeClass('border-danger');
      }, 2000);
    }

    if($("#ratingInput").val()<1 || $("#ratingInput").val()>5){
      error++;
      $("#ratingInput").addClass('border-danger');
      setTimeout(() => {
        $("#ratingInput").removeClass('border-danger');
      }, 2000);
    }

    if(error != 0) return;
    else{
      var recDTO = new RecensionDTO();
      recDTO.comment = $("#recTextarea").val();
      recDTO.rating = $("#ratingInput").val();
      recDTO.accUnitId = this.res.accId;
      this.res.review = true;

      this.recService.addRecension(recDTO).subscribe(
        data =>{
          this.modalRef.hide();
        }
      )
    }
  }

}
