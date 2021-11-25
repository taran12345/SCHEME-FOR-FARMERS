import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdminLogin } from '../classes/admin-login';
import { ClaimDetails } from '../classes/claim-details';
import { ClaimRequest } from '../classes/claim-request';
import { InsuranceDetails } from '../classes/insurance-details';
import { SellRequest } from '../classes/sell-request';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  sellRequest:number;
  sellRequestDetails:SellRequest;

  constructor(private http:HttpClient) { }

  public isValid(adminLogin: AdminLogin):Observable<boolean> {
    return  this.http.post<boolean>("http://localhost:9090/loginAdmin",adminLogin);
  }

  public approveFinalBidRequest(BidRequestId:number):Observable<boolean>{
    return  this.http.get<boolean>("http://localhost:9090/approveFinalBidRequest/"+BidRequestId);
  }

  public addInsurance(insuranceDetails:InsuranceDetails):Observable<number>{
    return  this.http.post<number>("http://localhost:9090/addAnInsurance",insuranceDetails);
  }

  public viewAllUnapprovedClaimRequests(): Observable<ClaimRequest[]>{
    return  this.http.get<ClaimRequest[]>("http://localhost:9090/viewAllUnapprovedClaimRequests");
   }
 
   public approveClaimRequest(claimRequestId:number): Observable<boolean>{
    return  this.http.get<boolean>("http://localhost:9090/approveClaimRequest/"+claimRequestId);
   }
 
  public rejectClaimRequest(claimRequestId:number):Observable<Boolean>{
    return this.http.get<Boolean>("http://localhost:9090/rejectClaimRequest/"+claimRequestId);
  }

}
