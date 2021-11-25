import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BidRequest } from '../classes/bid-request';
import { SellRequest } from '../classes/sell-request';

@Injectable({
  providedIn: 'root'
})
export class MarketService {

  constructor(private http:HttpClient) { }

  public viewAllSellRequests(): Observable<SellRequest[]>{
    return  this.http.get<SellRequest[]>("http://localhost:9090/viewAllApprovedSellRequests");
   }

   public viewPreviousBids(requestId:number):Observable<BidRequest[]>{
    return  this.http.get<BidRequest[]>("http://localhost:9090/viewAllBidRequestsForOneSellRequest/"+requestId);
   }
   
   public viewAllUnapprovedSellRequests(): Observable<SellRequest[]>{
    return  this.http.get<SellRequest[]>("http://localhost:9090/viewAllUnapprovedSellRequests");
   }

   public approveARequest(sellId:number):Observable<Boolean>{
    return this.http.get<Boolean>("http://localhost:9090/approveASellRequest/"+sellId);
  }

  public rejectARequest(sellId:number):Observable<Boolean>{
    return this.http.get<Boolean>("http://localhost:9090/rejectASellRequest/"+sellId);
  }

  public viewAllBidRequests(sellRequestId:number):Observable<BidRequest[]>{
    return this.http.get<BidRequest[]>("http://localhost:9090/viewAllBidRequestsForOneSellRequest/"+sellRequestId);
  }

}
