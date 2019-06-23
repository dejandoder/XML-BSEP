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
        return this.http.get<User[]>('api/getContacts');
    }

    getMessages(id : number){
        return this.http.post<MessageDTO[]>('api/getAllMessages', id);
    }

    sendMessage(message : MessageDTO){
        return this.http.post('api/sendMessage',message);
    }

    syncMessages(){
        return this.http.get('api/syncMessages');
    }
}