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
        return this.http.get<User[]>("api/auth/admin/getAgents");
    }

    getRegularUsers(){
        return this.http.get<User[]>("api/auth/admin/getRegularUsers");
    }

    addAgent(newAgent : User){
        return this.http.post<User[]>("api/auth/admin/addNewAgent",newAgent);
    }

    activateUser(user : User){
        return this.http.post<User[]>("api/auth/admin/activateUser", user)
    }

    blockUser(user : User){
        return this.http.post<User[]>("api/auth/admin/blockUser", user)
    }
}