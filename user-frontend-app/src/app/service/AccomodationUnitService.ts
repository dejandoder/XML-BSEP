import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AccomodationType } from '../model/AccomodationType';
import { AccomodationService } from '../model/AccomodationService';
import { SearchDTO } from '../model/SearchDTO';
import { AccomodationUnit } from '../model/AccomodationUnit';
import { ReservationDTO } from '../model/ReservationDTO';

@Injectable({
    providedIn: 'root',
})
export class AccomodationUnitService{

    constructor(private http : HttpClient){}

    getAccTypes(){
        return this.http.get<AccomodationType[]>('api/acc/getAllAccTypes');
    }

    getAccServices(){
        return this.http.get<AccomodationService[]>('api/acc/getAllAccServices');
    }

    searchAccServices(searchDTO : SearchDTO){
        return this.http.post<AccomodationUnit[]>('api/acc/search',searchDTO);
    }

    reserveAccomodation(reservationDTO : ReservationDTO){
        return this.http.post('api/res/newReservation',reservationDTO);
    }
}