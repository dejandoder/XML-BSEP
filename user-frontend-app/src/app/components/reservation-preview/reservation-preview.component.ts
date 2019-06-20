import { Component, OnInit, Input } from '@angular/core';
import { ReservationDTO } from 'src/app/model/ReservationDTO';
import { ReservationService } from 'src/app/service/ReservationService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reservation-preview',
  templateUrl: './reservation-preview.component.html',
  styleUrls: ['./reservation-preview.component.css']
})
export class ReservationPreviewComponent implements OnInit {

  @Input('res') resInput : ReservationDTO;
  res : ReservationDTO;
  constructor(private resService : ReservationService, private router : Router) {

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
}
