import { ClaimRequest } from './claim-request';
import { FarmerPersonalDetails } from './farmer-personal-details';
import { InsuranceDetails } from './insurance-details';

export class InsuranceApplied {
    validFrom:Date;
	validTill:Date;

	sharePremium:number;
	premiumAmount:number;
	sumInsured:number;
	Area:number;
    status:string;
    farmerPersonalDetails:FarmerPersonalDetails;
    insuranceDetails:InsuranceDetails;
}
