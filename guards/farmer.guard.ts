import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FarmerGuard implements CanActivate {
  canActivate():boolean{
    if(sessionStorage.getItem('userType')=="farmer"){
      return true;
    }
    else{
      // this.router.navigate(["mainLink"]);
      return false;
    }
  }

  constructor(public router:Router){

  }
  
}
