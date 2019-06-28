import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RecensionDTO } from '../model/RecensionDTO';

@Injectable({
    providedIn: 'root',
})
export class RecensionService{

    constructor(private http : HttpClient){

    }

    getRecensions(){
        return this.http.get<RecensionDTO[]>('api/rat/admin/getRecensions');
    }

    apporveRecension(id : number){
        return this.http.post('api/rat/admin/approveRecension', id);
    }

    declineRecension(id : number){
        return this.http.post('api/rat/admin/declineRecension', id);
    }
}