import { Injectable } from '@angular/core';
import { FarmerPersonalDetails } from '../classes/farmer-personal-details';
import { HttpClient } from '@angular/common/http'; 
import { Observable } from 'rxjs';
import { FarmerLogin } from '../classes/farmer-login';
import { SellRequest } from '../classes/sell-request';
import { SellRequestDetails } from '../classes/sell-request-details';
import { FarmerLoginDetails } from '../classes/farmer-login-details';
import { DetailInsurance } from '../classes/detail-insurance';
import { InsuranceApplied } from '../classes/insurance-applied';
import { ClaimDetails } from '../classes/claim-details';

@Injectable({
  providedIn: 'root'
})
export class FarmerService {
  
  constructor(private http:HttpClient) { }

  showDetails:FarmerPersonalDetails;
  index:number;
  farmerId:number;
  policyNo:number;

  
  public registerFarmer(farmer: FarmerPersonalDetails):Observable<number> {
   return  this.http.post<number>("http://localhost:9090/addAFarmer",farmer);
  }

  public farmerLogin(farmerLogin: FarmerLogin):Observable<FarmerLoginDetails> {
    return  this.http.post<FarmerLoginDetails>("http://localhost:9090/loginFarmer",farmerLogin);
   }

   public viewAllFarmerRequests():Observable<FarmerPersonalDetails[]> {
    return this.http.get<FarmerPersonalDetails[]>("http://localhost:9090/viewAllFarmerRequests");
   }

   public approveFarmer(farmerId:number):Observable<boolean> {
    return this.http.get<boolean>("http://localhost:9090/approveAFarmer/"+farmerId);
   }

   public rejectFarmer(farmerId:number):Observable<boolean> {
    return this.http.get<boolean>("http://localhost:9090/rejectAFarmer/"+farmerId);
   }

   public soldHistory(soldId: number):Observable<SellRequest[]> {
    return  this.http.get<SellRequest[]>("http://localhost:9090/viewSoldHistory/"+soldId);
   }

   public placeSellRequest(sellRequestDetails:SellRequestDetails):Observable<number> {
    return  this.http.post<number>("http://localhost:9090/placeSellRequest",sellRequestDetails);
   }

   public docUpload(formData: FormData,farmerId:number) : Observable<any> {
    let url = "http://localhost:9090/docsUpload/"+farmerId;
   return this.http.post(url, formData); 
  }

  public fetchProfile(farmerId: number) : Observable<FarmerPersonalDetails> {​
        let url = "http://localhost:9090/docsDownload?farmerId="+farmerId;
       return this.http.get<FarmerPersonalDetails>(url); 
      }​

    public calculateInsurance(detailInsurance : DetailInsurance) : Observable<InsuranceApplied> {​
          let url = "http://localhost:9090/calulateInsurance";
         return this.http.post<InsuranceApplied>(url,detailInsurance); 
        }​

      public applyInsurance(insuranceApplied : InsuranceApplied) : Observable<number> {​
            let url = "http://localhost:9090/applyForInsurance";
           return this.http.post<number>(url,insuranceApplied); 
          }​

        public viewInsuranceHistory(farmerId:number) : Observable<InsuranceApplied[]> {​
              let url = "http://localhost:9090/viewInsuranceHistory/"+farmerId;
             return this.http.get<InsuranceApplied[]>(url); 
            }​

          public applyClaimRequest(claimDetails:ClaimDetails):Observable<number>{
            return  this.http.post<number>("http://localhost:9090/applyForClaim",claimDetails);
          }

          public forgotPassword(farmerId:number):Observable<boolean> {
            return this.http.get<boolean>("http://localhost:9090/forgotPassword/"+farmerId);
           }

           public findFarmer(farmerId: number) : Observable<FarmerPersonalDetails> {​
                let url = "http://localhost:9090/findFarmer/"+farmerId;
               return this.http.get<FarmerPersonalDetails>(url); 
              }​

            public updateAFarmer(farmer:FarmerPersonalDetails) : Observable<number> {​
                  let url = "http://localhost:9090/updateAFarmer";
                 return this.http.post<number>(url,farmer); 
                }​
 
}