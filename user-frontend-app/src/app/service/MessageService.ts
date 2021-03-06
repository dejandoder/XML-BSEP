import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/User';
import { MessageDTO } from '../model/MessageDTO';

@Injectable({
    providedIn: 'root',
})
export class MessageService{

    constructor(private http : HttpClient){}

    getContacts(){
        return this.http.get<User[]>('api/mess/user/getContacts');
    }

    getMessages(username : string){
        return this.http.post<MessageDTO[]>('api/mess/user/getMessages', username);
    }

    sendMessage(message : MessageDTO){
        return this.http.post('api/mess/user/sendMessage',message);
    }
}