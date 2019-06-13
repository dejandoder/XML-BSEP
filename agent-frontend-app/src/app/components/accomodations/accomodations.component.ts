import { Component, OnInit } from '@angular/core';
import { AccomodationUnit } from 'src/app/model/AccomodationUnit';
import { AccomodationUnitService } from 'src/app/service/AccomodationUnitService';
import { Router } from '@angular/router';
@Component({
  selector: 'app-accomodations',
  templateUrl: './accomodations.component.html',
  styleUrls: ['./accomodations.component.css']
})
export class AccomodationsComponent implements OnInit {

  accUnits : AccomodationUnit[] = [];
  showAddAcc : boolean = true;

  constructor(private accService : AccomodationUnitService, private router : Router) {
    accService.getAllAccUnits().subscribe(
      data => {
        this.accUnits = data;
        accService.setCurrentAccomodationUnits(data);
      }
    )

    setInterval(()=>{
      this.accUnits = accService.getCurrentAccomodationUnits();
    }, 2000)
  }

  ngOnInit() {
  }

  deleteUnitClick(accUnit : AccomodationUnit){

  }
 
  editUnitClick(accUnit : AccomodationUnit){
    this.accService.setEditingAccUnit(accUnit);
    this.showAddAcc = false;
  }
 
  addNewAccClick(){
    this.showAddAcc = true;
  }

}
