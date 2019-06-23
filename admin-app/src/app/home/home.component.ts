import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/AuthService';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  navLinks = [
    {path: "users", label: "Users"},
    {path: "comments", label: "Comments"},
    {path: "services&categories", label: "Services and categories"}
  ]

  constructor(private authService : AuthService) { }

  ngOnInit() {
  }

  logout(){
    this.authService.logOutUser();
  }
}
