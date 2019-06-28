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
       return this.http.get<AccService[]>("api/acc/all/getAllAccServices");
    }

    addNewAccServide(accService : AccService){
        return this.http.post<AccService[]>("api/acc/admin/addAccService", accService);
    }

    removeAccService(accService : AccService){
        return this.http.post<AccService[]>("api/acc/admin/removeAccService", accService.id);
    }
}