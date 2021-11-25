import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {
    canActivate():boolean{
      if(sessionStorage.getItem('userType')=="admin"){
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
