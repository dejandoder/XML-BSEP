import { AccomodataionService } from './AccomodationService';
import { Image } from './Image';
import { PricePlan } from './PricePlan';
import { Location } from './Location';
import { AccomodationType } from './AccomodationType';

export class AccomodationUnit{
    accomodationType : AccomodationType;
    category : string;
    services: AccomodataionService [] = [];
    description : string;
    images : Image [];
    pricePlans : PricePlan[];
    capacity : number;
    id : number;
    cancelingPeriod : number;
    location : Location = new Location();
    name : string;
}