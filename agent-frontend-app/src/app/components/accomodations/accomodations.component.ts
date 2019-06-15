import { Component, OnInit } from '@angular/core';
import { AccomodationUnit } from 'src/app/model/AccomodationUnit';
import { AccomodationUnitService } from 'src/app/service/AccomodationUnitService';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-accomodations',
  templateUrl: './accomodations.component.html',
  styleUrls: ['./accomodations.component.css']
})
export class AccomodationsComponent implements OnInit {

  accUnits : AccomodationUnit[] = [];
  showAddAcc : boolean = true;

  constructor(private accService : AccomodationUnitService, private router : Router, private route: ActivatedRoute) {
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
    this.router.navigate(['editAccomodation'], {relativeTo: this.route});
  }
 
  addNewAccClick(){
    this.router.navigate(['addAccomodation'], {relativeTo: this.route})
  }

}
