import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AccomodationType } from '../model/AccomodationType';

@Injectable({
    providedIn: 'root',
})
export class AccTypeService{

    constructor(private http : HttpClient){
    }

    getAllAccTypes(){
        return this.http.get<AccomodationType[]>("api/admin-service/getAllAccTypes");
    }

    addNewAccType(newAccType : AccomodationType){
        return this.http.post<AccomodationType[]>("api/admin-service/addNewAccType",newAccType);
    }

    removeAccType(accType : AccomodationType){
        return this.http.post<AccomodationType[]>("api/admin-service/removeAccType",accType);
    }
}