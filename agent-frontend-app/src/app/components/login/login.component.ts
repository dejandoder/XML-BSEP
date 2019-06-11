import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { AuthService } from 'src/app/service/AuthService';


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

  checkEmail(email : string) : boolean{
    return true;
  }

  sanitizeData(data : string) : string{
    return "aaaaaa";
  }
}
