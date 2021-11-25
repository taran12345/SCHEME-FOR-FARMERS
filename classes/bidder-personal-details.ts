import { BidderAddress } from './bidder-address';
import { BidderBankDetails } from './bidder-bank-details';
import { BidderDocuments } from './bidder-documents';
import { UploadBidderDoc } from './upload-bidder-doc';

export class BidderPersonalDetails {
    bidderId:number;
    bidderName:string;
    bidderPassword:string;
    bidderEmail:string;
    bidderContact:string;
    bidderStatus:string;
    bidderAddress:BidderAddress;
    bidderBankDetails:BidderBankDetails;
    bidderDocuments:BidderDocuments;
    uploadBidderDoc:UploadBidderDoc;
}
