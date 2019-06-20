import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Message } from '../model/Message';

@Injectable({
  providedIn: 'root'
})
export class MessageServiceService {

  currentMessage : Message[]=[];

  constructor(private http : HttpClient) {

   }

   getAllMess(){
     return this.http.get<Message[]>('api/getAllMessages');
   }

   setCurrentMessage( currentMessage1: Message[]){
        this.currentMessage = currentMessage1;
    }

    getCurrentMessage(){
        return this.currentMessage;
    }
}
