import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RecensionDTO } from '../model/RecensionDTO';

@Injectable({
    providedIn: 'root',
})
export class RecenesionService{

    constructor(private http : HttpClient){
        
    }

    getRecensions(accId : number){
        return this.http.post<RecensionDTO[]>('api/rat/all/getRecensionsByAccUnitForUser',accId);
    }    

    addRecension(recDTO : RecensionDTO){
        return this.http.post('api/rat/user/addRecension', recDTO);
    }

}