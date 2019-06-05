import { Component, OnInit } from '@angular/core';
import { User } from '../model/User';
import { UserService } from '../service/UserService';
import * as $ from 'jquery';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  regularUsers : User[] = [];
  agents : User[] = [];
  newAgent : User = new User();
  addAgentError : boolean = false;

  constructor(private userService : UserService) {
    this.userService.getAgents().subscribe(
      data => {
        this.agents = data;
      }
    )
    this.userService.getRegularUsers().subscribe(
      data => {
        this.regularUsers = data;
      }
    )
   }

  ngOnInit() {
  }

  addNewAgent(){
    let inputOk = 0;

    $(".my-input").each(function(){
      if( $(this).val() == ""){
        $(this).addClass("border border-danger");
        inputOk++;
      }else{
        $(this).removeClass("border border-danger");
      }
      if($(this).attr('id') == "pib"){
        if($(this).val() == "" || $(this).val()<10000001 || $(this).val()>99999999){
          $(this).addClass("border border-danger");
          inputOk++;
        }
      }
    })

    if(inputOk != 0){
      return;
    }else{
      this.userService.addAgent(this.newAgent).subscribe(
        data => {
          this.agents = data;
          this.newAgent = new User();
        },
        error => {
          this.addAgentError = true;
          setInterval(
            () => {
              this.addAgentError = false;
            },
            2000
          )
        }
      )
    }
  }
}
