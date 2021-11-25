import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { FarmerService } from '../services/farmer.service';

@Injectable({
  providedIn: 'root'
})
export class FarmerRegistrationGuardGuard implements CanActivate {
  canActivate():boolean{
    if(sessionStorage.getItem('isRegistered')=="true"){
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
