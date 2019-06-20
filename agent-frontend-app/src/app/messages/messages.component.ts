import { Component, OnInit } from '@angular/core';
import { Message } from '../model/Message';
import { MessageServiceService } from '../service/message-service.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  message : Message[] = [];

  constructor(private messageService : MessageServiceService, private router : Router, private route: ActivatedRoute) { 
    messageService.getAllMess().subscribe(
      data => {
        this.message = data;
        messageService.setCurrentMessage(data);
      }
    )

    setInterval(()=>{
      this.message = messageService.getCurrentMessage();
    }, 2000)
  }

  ngOnInit() {
  }

}
