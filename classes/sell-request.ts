import { CropDetails } from './crop-details';
import { FarmerPersonalDetails } from './farmer-personal-details';

export class SellRequest {
    requestId:number;
    quantity:number;
    sellRequestApproveStatus:String;
    cropDetails:CropDetails;
    farmer:FarmerPersonalDetails;
}
