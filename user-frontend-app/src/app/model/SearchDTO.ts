import { AccomodationType } from './AccomodationType';
import { AccomodationService } from './AccomodationService';
import { Location } from './Location'

export class SearchDTO{
    dates : Date[] = [];
    location : Location = new Location();
    persons : number;
    type : AccomodationType = { id : -1, name : "Select type", disabled : true};
    category : number = -1;
    maxDistance : number;
    cancelationPeriod : number;
    services : AccomodationService[] = [];
}