import { Component, OnInit } from '@angular/core';
import { ReservationDTO } from 'src/app/model/ReservationDTO';
import { ReservationService } from 'src/app/service/ReservationService';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  reservations : ReservationDTO[];

  constructor(private resService : ReservationService) {
      resService.getReservationsByUser().subscribe(
        data =>{
          this.reservations = data;
        }
      )
   }

  ngOnInit() {
  }

}
