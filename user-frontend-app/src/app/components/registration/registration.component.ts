import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/User';
import * as $ from 'jquery'
import { AuthService } from 'src/app/service/AuthService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user : User = new User();
  errorMessage : String;

  constructor(private authService : AuthService, private router : Router) { }

  ngOnInit() {
  }

  register(){
      let error = 0;

      if(this.user.name == ""){
        $("#name").addClass("border-danger");
        error++;
      }else{
        $("#name").removeClass("border-danger");
      }

      if(this.user.surname == ""){
        $("#surname").addClass("border-danger");
        error++;
      }else{
        $("#surname").removeClass("border-danger");
      }

      if(this.user.username == ""){
        $("#username").addClass("border-danger");
        error++;
      }else{
        $("#username").removeClass("border-danger");
      }

      if(this.user.password == ""){
        $("#password").addClass("border-danger");
        error++;
      }else{
        $("#password").removeClass("border-danger");
      }

      if(this.user.password != $("#cnfPass").val()){
        $("#cnfPass").addClass("border-danger");
        error++;
      }else{
        $("#cnfPass").removeClass("border-danger");
      }      

      if(error == 0){
        this.authService.register(this.user).subscribe(
          data =>{
            this.errorMessage = "You have been registerd, you can login after admin approves your registration!";
            setTimeout(() => {
              this.errorMessage = "";
              this.router.navigate[("/home")]
            }, 2000);
          },
          error => {
            this.errorMessage = "Invalid username or email!";
            setTimeout(() => {
              this.errorMessage = "";
            }, 2000);
          }
        )
      }else{
        return;
      }

  }

  changePass(){
    if(this.user.password != $("#cnfPass").val()){
      $("#cnfPass").addClass("border-danger");
    }else{
      $("#cnfPass").removeClass("border-danger");
    }
  }
}
