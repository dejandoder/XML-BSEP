import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { AuthService } from 'src/app/service/AuthService';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user : User = new User();
  errorMessage : string;

  constructor(private authService : AuthService, private router: Router, private http : HttpClient) { 
    this.http.get('api/syncUsers').subscribe(
      data =>{
        console.log(data);
      },
      error =>{
        console.log(error);
      }
    )
  }

  ngOnInit() {
  }

  loginClick(){
    this.authService.login(this.user).subscribe(
      success => {
        if(!success) {
          this.errorMessage = "Wrong username or password";
        }else{
          this.http.get('api/syncAll').subscribe(
            data =>{
              console.log(data);
            },
            error =>{
              console.log(error);
            }
          )
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
