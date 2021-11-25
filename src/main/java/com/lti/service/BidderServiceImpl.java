package com.lti.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dto.BidderLoginDetails;
import com.lti.entity.BidRequest;
import com.lti.entity.BidderPersonalDetails;
import com.lti.entity.CropDetails;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.UploadBidderDoc;
import com.lti.entity.UploadFarmerDoc;
import com.lti.repository.BidderRepository;

@Service
public class BidderServiceImpl implements BidderService {

	@Autowired
	BidderRepository bidderRepository;
	
	@Autowired
	EmailService emailService;

	public long addOrUpdateBidder(BidderPersonalDetails bidderPersonalDetails) {
		
		long bidderId = bidderRepository.addOrUpdateBidder(bidderPersonalDetails);
	    String bidderEmail = bidderPersonalDetails.getBidderEmail();
		if(bidderId>0) {
			String subject="Your Registration has been sent for approval..Once it is approved you will get notified through a mail";
			String text="Hi"+bidderPersonalDetails.getBidderName()+"!! Your registration id is :"+bidderId;
			emailService.sendEmailForNewRegistration(bidderEmail, text, subject);
			System.out.println("Email Sent.");
		}
		return bidderId;
	}

	public BidderLoginDetails loginBidder(long bidderId, String bidderPassword) {
		return bidderRepository.loginBidder(bidderId, bidderPassword);
	}

	public List<CropDetails> viewCropDetails() {
		return bidderRepository.viewCropDetails();
	}

	public long applyForBid(long sellRequest_id, long bidder_id, int bidAmount) {
		
		BidderPersonalDetails bidderPersonalDetails = bidderRepository.findBidderById(bidder_id);
	    String bidderEmail = bidderPersonalDetails.getBidderEmail();
		if(bidder_id>0 && sellRequest_id>0) {
			String subject="Bid Request status";
			String text="Hi"+bidderPersonalDetails.getBidderName()+"..Your bid request has been added for sell request with id :"+sellRequest_id + "you can check the status of bid request under the view bid history of your dashboard";
			emailService.sendEmailForNewRegistration(bidderEmail, text, subject);
			System.out.println("Email Sent.");
		}
		return bidderRepository.applyForBid(sellRequest_id, bidder_id, bidAmount);
	}

	public List<BidRequest> viewBidHistory(long bidderId) {
		return bidderRepository.viewBidHistory(bidderId);
	}
	
	public BidRequest findBidRequestById(long bidRequestId) {
		return bidderRepository.findBidRequestById(bidRequestId);
	}

	public BidderPersonalDetails findBidderById(long bidderId) {
		return bidderRepository.findBidderById(bidderId);
	}
	
	public void updateBidderDoc(long bidderId, String newFileName1, String newFileName2) {
		BidderPersonalDetails bidderPersonalDetails = bidderRepository.findBidderById(bidderId);
		
		UploadBidderDoc uploadBidderDoc = new UploadBidderDoc();
		uploadBidderDoc.setAadharImagePath(newFileName1);
		uploadBidderDoc.setPanImagePath(newFileName2);
		uploadBidderDoc.setBidderPersonalDetails(bidderPersonalDetails);
		bidderPersonalDetails.setUploadBidderDoc(uploadBidderDoc);
		bidderRepository.addABidderWithDoc(bidderPersonalDetails, uploadBidderDoc);
	}
	
	@Override
    public boolean forgotPassword(long bidderId) {
           BidderPersonalDetails bidderPersonalDetails = bidderRepository.findBidderById(bidderId);
           if(bidderPersonalDetails==null)
           {
        	   return false;
           }
           String bidderEmail = bidderPersonalDetails.getBidderEmail();
            if(bidderId>0) {
                String subject="Password";
                String text="Hi "+bidderPersonalDetails.getBidderName()+"Your Password is :"+bidderPersonalDetails.getBidderPassword()+"Now you can login to the portal using below mentioned password";
                emailService.sendEmailForNewRegistration(bidderEmail, text, subject);
                System.out.println("Email Sent.");
            }
        return bidderRepository.forgotPassword(bidderId);
        }
	
	
}