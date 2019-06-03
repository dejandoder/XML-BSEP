import { HttpClient } from '@angular/common/http';
import { AccService } from '../model/AccService';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class AccServicesService{

    constructor(private http : HttpClient){
    }
    
    getAllAccServices() {
       return this.http.get<AccService[]>("api/admin-service/getAllAccServices");
    }

}