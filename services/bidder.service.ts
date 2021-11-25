import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BidRequest } from '../classes/bid-request';
import { BidRequestDetails } from '../classes/bid-request-details';
import { BidderLogin } from '../classes/bidder-login';
import { BidderLoginDetails } from '../classes/bidder-login-details';
import { BidderPersonalDetails } from '../classes/bidder-personal-details';

@Injectable({
  providedIn: 'root'
})
export class BidderService {

  showDetails: BidderPersonalDetails;
  bidderId:number;

  constructor(private http:HttpClient) { }

  public viewAllBidderRequests():Observable<BidderPersonalDetails[]> {
    return this.http.get<BidderPersonalDetails[]>("http://localhost:9090/viewAllBidderRequests");
   }
   public approveBidder(bidderId: number):Observable<boolean> {
    return this.http.get<boolean>("http://localhost:9090/approveABidder/"+bidderId);
   }
   public rejectBidder(bidderId: number):Observable<boolean> {
    return this.http.get<boolean>("http://localhost:9090/rejectABidder/"+bidderId);
   }

  public registerBidder(bidder: BidderPersonalDetails):Observable<number>{
    return  this.http.post<number>("http://localhost:9090/addABidder",bidder);
   }

   public isValid(bidderLogin: BidderLogin):Observable<BidderLoginDetails> {
    return  this.http.post<BidderLoginDetails>("http://localhost:9090/loginBidder",bidderLogin);
   }

   public bidHistory(bidId: number):Observable<BidRequest[]> {
    return  this.http.get<BidRequest[]>("http://localhost:9090/viewBidHistory/"+bidId);
   }

   public placeBidRequest(bidRequestDetails:BidRequestDetails):Observable<number> {
    return  this.http.post<number>("http://localhost:9090/applyBidRequest",bidRequestDetails);
   }

   public forgotPassword(bidderId:number):Observable<boolean> {
    return this.http.get<boolean>("http://localhost:9090/forgotPasswordForBidder/"+bidderId);
   }

   public docUpload(formData: FormData,bidderId:number) : Observable<any> {
    let url = "http://localhost:9090/docsUploadForBidder/"+bidderId;
   return this.http.post(url, formData); 
  }

  public fetchProfile(bidderId: number) : Observable<BidderPersonalDetails> {
    let url = "http://localhost:9090/docsDownloadForBidder?bidderId="+bidderId;
   return this.http.get<BidderPersonalDetails>(url); 
  }

  public findBidder(bidderId: number) : Observable<BidderPersonalDetails> {​
        let url = "http://localhost:9090/findBidder/"+bidderId;
       return this.http.get<BidderPersonalDetails>(url); 
      }​


}
