import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import * as $ from 'jquery';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router : Router, private route: ActivatedRoute) {
    this.router.navigate(["accomodations"], {relativeTo: this.route});
   }

  ngOnInit() {
  }

  onNavClick(selector : string){
    $(".nav-link").removeClass('active');
    $("#"+selector).addClass('active');
    this.router.navigate([selector], {relativeTo: this.route});
  }

  
}
