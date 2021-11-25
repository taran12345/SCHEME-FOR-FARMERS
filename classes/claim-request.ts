import { InsuranceApplied } from './insurance-applied';

export class ClaimRequest {
    id:number;
    causeForLoss:string;
    insuranceApplied:InsuranceApplied;
}
