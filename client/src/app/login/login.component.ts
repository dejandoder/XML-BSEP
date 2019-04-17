import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private http: HttpClient) { 
  }

  ngOnInit() {
  }

  loginUser(event){
    
    event.preventDefault()
    console.log(event)
    var userName= event.target[0].value
    var password= event.target[1].value
    var object={}
    object["username"]=userName
    object["password"]=password
    //console.log(userName)
    this.http.post("/api/auth",object).subscribe(
      data => 
             {
                    console.log("svaka cast")
                    console.log(data)
             },
      error => 
             {
                  console.log(error)
                  console.log("oce kurac")
             }
);
  }
}

