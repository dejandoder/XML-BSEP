import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/User';


@Injectable({
    providedIn: 'root',
})
export class UserService{

    constructor(private http : HttpClient){      
    }

    getAgents(){
        return this.http.get<User[]>("api/admin-service/getAgents");
    }

    getRegularUsers(){
        return this.http.get<User[]>("api/admin-service/getRegularUsers");
    }

    addAgent(newAgent : User){
        return this.http.post<User[]>("api/admin-service/addNewAgent",newAgent);
    }
}