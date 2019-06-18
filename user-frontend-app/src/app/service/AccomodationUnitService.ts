import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AccomodationType } from '../model/AccomodationType';
import { AccomodationService } from '../model/AccomodationService';
import { SearchDTO } from '../model/SearchDTO';
import { AccomodationUnit } from '../model/AccomodationUnit';

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
        return this.http.post<AccomodationUnit[]>('api/search',searchDTO);
    }

}