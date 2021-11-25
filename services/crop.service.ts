import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CropDetails } from '../classes/crop-details';

@Injectable({
  providedIn: 'root'
})
export class CropService {

  constructor(private http:HttpClient) { }

  public viewAllCrops(): Observable<CropDetails[]>{
    return  this.http.get<CropDetails[]>("http://localhost:9090/viewAllCrops");
   }

   addACrop(crop: CropDetails):Observable<number> {
    return  this.http.post<number>("http://localhost:9090/addACrop",crop);
   }
}
