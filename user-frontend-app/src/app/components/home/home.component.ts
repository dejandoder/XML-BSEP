import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import * as $ from 'jquery';
import { AuthService } from 'src/app/service/AuthService';
import { User } from 'src/app/model/User';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  userLogged : boolean;
  user : User = new User();
  errorMessage : string;

  constructor(private router : Router, private route: ActivatedRoute, private authService : AuthService) {
    this.router.navigate(["accomodations"], {relativeTo: this.route});
    this.userLogged = authService.isUserLogged();
    setInterval(() => {
      this.userLogged = authService.isUserLogged();
    }, 500);
   }

  ngOnInit() {
  }

  onNavClick(selector : string){
    $(".nav-link").removeClass('active');
    $("#"+selector).addClass('active');
    this.router.navigate([selector], {relativeTo: this.route});
  }

  loginClick(){
    this.authService.login(this.user).subscribe(
      success => {
        if(!success) {
          this.errorMessage = "Wrong username or password";
        }else{
          this.errorMessage = "";
        }
      }
    );
  }
  
  logoutClick(){
    this.authService.logOutUser();
    this.router.navigate(["/home"]);
  }
  
  registration(){
    this.router.navigate(["registration"]);
  }
}
