import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AccomodationType } from '../model/AccomodationType';
import { AccomodataionService } from '../model/AccomodationService';
import { AccomodationUnit } from '../model/AccomodationUnit';

@Injectable({
    providedIn: 'root',
})
export class AccomodationUnitService{

    currentAccUnits : AccomodationUnit[] = [];
    currentEditingAccUnit : AccomodationUnit;

    constructor(private http : HttpClient){

    }

    getAllAccomodationTypes(){
        return this.http.get<AccomodationType[]>('api/getAllAccTypes');
    }

    getAllAccomodationServices(){
        return this.http.get<AccomodataionService[]>('api/getAllAccServices');
    }

    addNewAccomodationUnit(accUnit : AccomodationUnit){
        return this.http.post<any>('api/addNewAccUnit',accUnit);
    }

    getAllAccUnits(){
        return this.http.get<AccomodationUnit[]>("api/getAllAccUnitByAgent");
    }

    setCurrentAccomodationUnits( accUnits: AccomodationUnit[]){
        this.currentAccUnits = accUnits;
    }

    getCurrentAccomodationUnits(){
        return this.currentAccUnits;
    }

    setEditingAccUnit(accUnit : AccomodationUnit){
        this.currentEditingAccUnit = accUnit;
    }

    getEditingAccUnit(){
        return this.currentEditingAccUnit;
    }

}