import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/AuthService';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  
  navLinks = [
    {path: "accomodations", label: "My accomodations"},
    {path: "messages", label: "Messages"}
  ]
  constructor(private router : Router, private authService : AuthService) { 
    
  }

  ngOnInit() {
  }

  logout(){
    this.authService.logOutUser();
  }

}
