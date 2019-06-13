import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PricePlan } from '../model/PricePlan';

@Injectable({
    providedIn: 'root',
})
export class PricePlanService{

    constructor(private http : HttpClient){

    }

    getPricePlansByAccomodationUnit(id : number){
        return this.http.post<PricePlan[]>("api/getPricePlans", id);
    }

    addPricePlan(plan : PricePlan){
        return this.http.post("api/savePricePlan",plan);
    }
}