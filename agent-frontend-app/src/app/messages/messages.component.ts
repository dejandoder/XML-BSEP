import { Component, OnInit } from '@angular/core';
import { MessageService } from 'src/app/service/MessageService';
import { User } from 'src/app/model/User';
import { MessageDTO } from 'src/app/model/MessageDTO';
import * as $ from 'jquery'

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  contacts : User[] = [];
  currentMessages : MessageDTO[];
  newMessage : MessageDTO = new MessageDTO();
  currentReciver : User;

  constructor(private messService : MessageService) { 
    messService.getContacts().subscribe(
      data =>{
        this.contacts = data;
      }
    )
    setInterval(
      ()=>{
        messService.syncMessages().subscribe(
          data =>{
            if(this.currentReciver != null){
              this.messService.getMessages(this.currentReciver.id).subscribe(
                data =>{
                  this.currentMessages = data;
                }
              )
            }
          }
        )
      },3000)
  }

  ngOnInit() {
  }

  showMessages(user : User){
    this.currentReciver = user;
    this.messService.getMessages(user.id).subscribe(
      data =>{
        this.currentMessages = data;
      }
    )
  }

  sendClick(){
    if($("#textarea").val() == null){
      $("#textarea").addClass('border-danger');
      setTimeout(
        ()=>{
          $("#textarea").removeClass('border-danger');
        }, 2000)
    }else{
      this.newMessage.userId2 = this.currentReciver.id;
      this.newMessage.username2 = this.currentReciver.username;
      this.newMessage.content =  $("#textarea").val();

      this.messService.sendMessage(this.newMessage).subscribe(
        data =>{
          this.newMessage = new MessageDTO();
          this.messService.getMessages(this.currentReciver.id).subscribe(
            data =>{
              this.currentMessages = data;
              $("#textarea").val('')
            }
          )
        }
      )
    }
  }
}
