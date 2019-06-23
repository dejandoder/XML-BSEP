import { Component, OnInit } from '@angular/core';
import { RecensionService } from '../service/RecensionService';
import { RecensionDTO } from '../model/RecensionDTO';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  recensions : RecensionDTO[] = [];

  constructor(private recService : RecensionService) { 
    recService.getRecensions().subscribe(
      data =>{
        this.recensions = data;
      }
    )
  }

  ngOnInit() {
  }

  approve(rec : RecensionDTO){
    this.recService.apporveRecension(rec.id).subscribe(
      data =>{
        this.recService.getRecensions().subscribe(
          data =>{
            this.recensions = data;
          }
        )
      }
    )
  }

  decline(rec : RecensionDTO){
    this.recService.declineRecension(rec.id).subscribe(
      data =>{
        this.recService.getRecensions().subscribe(
          data =>{
            this.recensions = data;
          }
        )
      }
    )
  }
}
