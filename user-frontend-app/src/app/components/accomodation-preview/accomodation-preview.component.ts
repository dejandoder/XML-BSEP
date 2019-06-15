import { Component, OnInit, Input } from '@angular/core';
import { AccomodationUnit } from 'src/app/model/AccomodationUnit';

@Component({
  selector: 'app-accomodation-preview',
  templateUrl: './accomodation-preview.component.html',
  styleUrls: ['./accomodation-preview.component.css']
})
export class AccomodationPreviewComponent implements OnInit {

  @Input('unit') accUnitInput : AccomodationUnit;
  accUnit : AccomodationUnit;
  constructor() { }

  ngOnInit() {
    this.accUnit = this.accUnitInput;
  }

}
