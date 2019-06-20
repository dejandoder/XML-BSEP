import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReservationDTO } from '../model/ReservationDTO';

@Injectable({
    providedIn: 'root',
})
export class ReservationService{

    constructor(private http : HttpClient){
    }

    approveReservation(accId : number){
       return this.http.post('api/approveReservation',accId);
    }

    confirmReservation(accId : number){
        return this.http.post('api/confirmReservation',accId);
     }

     declineReservation(accId : number){
        return this.http.post('api/declineReservation',accId);
     }

    agentReservation(resDTO : ReservationDTO){
        return this.http.post('api/agentReservation',resDTO);
    }

    getReservationsByAccUnit(accId : number){
       return this.http.post<ReservationDTO[]>('api/getReservationsByAccUnit',accId);
    }
}