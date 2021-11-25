import { FarmerAddress } from './farmer-address';
import { FarmerBankDetails } from './farmer-bank-details';
import { FarmerDocuments } from './farmer-documents';
import { FarmerLand } from './farmer-land';
import { UploadFarmerDoc } from './upload-farmer-doc';

export class FarmerPersonalDetails {

    farmerId:number;
    farmerName : string;
    farmerContact: number;
    farmerEmail: string;
    farmerPassword : string;  
    farmerStatus:string;  
    farmerAddress:FarmerAddress;
    farmerBankDetails:FarmerBankDetails;
    farmerLands:FarmerLand[];
    farmerDocuments:FarmerDocuments;
    uploadFarmerDoc:UploadFarmerDoc;
}
