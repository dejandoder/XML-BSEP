import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReservationDTO } from '../model/ReservationDTO';

@Injectable({
    providedIn: 'root',
})
export class ReservationService{
    constructor(private http: HttpClient){

    }

    getReservationsByUser(){
        return this.http.get<ReservationDTO[]>("api/res/user/getReservationsByUser");
    }

    deleteReservation(id : number){
        return this.http.post('api/res/user/deleteReservation', id);
    }
}