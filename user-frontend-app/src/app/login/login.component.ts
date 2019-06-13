import { Component, OnInit } from '@angular/core';
import { User } from '../model/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user : User = new User();
  errorMessage : string;

  constructor(private router : Router) { }

  ngOnInit() {
  }

}
