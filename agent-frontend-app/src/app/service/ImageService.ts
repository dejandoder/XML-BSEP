import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AccomodationUnit } from '../model/AccomodationUnit';

@Injectable({
    providedIn: 'root',
})
export class ImageService{

    constructor(private http : HttpClient){
        
    }

     uploadImages(fd : FormData){
        return this.http.post('api/uploadImages',fd).subscribe();
     }

     getImagesByAccomodationUnit(accUnit : AccomodationUnit){
        return this.http.post("api/getImages", accUnit, { responseType: 'blob' });
     }

     getImagesIdsByAccomodationUnit(accUnit : AccomodationUnit){
      return this.http.post<number[]>("api/getImageIds", accUnit);
   }

   getImage(id : number){
      return this.http.post("api/getImage", id, {responseType: 'blob'});
   }
}