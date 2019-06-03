import { Component, OnInit } from '@angular/core';
import { AccService } from '../model/AccService';
import { AccServicesService } from '../service/AccServicesService';

@Component({
  selector: 'app-services-and-categories',
  templateUrl: './services-and-categories.component.html',
  styleUrls: ['./services-and-categories.component.css']
})
export class ServicesAndCategoriesComponent implements OnInit {

  services : AccService[];

  constructor(private accServicesService : AccServicesService) {
    accServicesService.getAllAccServices().subscribe(
      data => {
        this.services = data;
      }
    
    )
  }

  ngOnInit() {
  }

}
