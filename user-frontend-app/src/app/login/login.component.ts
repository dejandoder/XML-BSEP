import { Component, OnInit } from '@angular/core';
import { User } from '../model/User';
import { AuthService } from '../service/AuthService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user : User = new User();
  errorMessage : string;

  constructor(private authService : AuthService, private router: Router) { }

  ngOnInit() {
  }

  loginClick(){
    this.authService.login(this.user).subscribe(
      success => {
        if(!success) {
          this.errorMessage = "Wrong username or password";
        }else{
          this.router.navigate(["/home"]);
        }
      }
    );

  }

}
