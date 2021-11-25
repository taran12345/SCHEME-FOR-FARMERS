import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MarketplaceGuard implements CanActivate {
  canActivate():boolean{
    if(sessionStorage.getItem('userId')!='0'){
      return true;
    }
    else{
      return false;
    }
  }

  constructor(public router:Router){

  }
  
}
