import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  links =  [
  { path: 'login'/*, component: LoginComponent */},
  { path: 'user',/* component: UserComponent */},
  { path: 'admin',/* component: AdminComponent */},
  ]
  constructor(private router : Router) { 
    
  }

  ngOnInit() {
  }

}
