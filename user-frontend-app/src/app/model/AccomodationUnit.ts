import { AccomodationType } from './AccomodationType';

export class AccomodationUnit{
    accomodationType : AccomodationType;   
    category : number;
    description : string;
    capacity : number;
    id : number;
    cancelingPeriod : number;
    location : Location;
    name : String;
    images : String[];
    rating : number = 4.1;
    price : number = 30;
    distance : number;
}