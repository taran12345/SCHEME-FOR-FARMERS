package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.BidRequest;
import com.lti.entity.BidderPersonalDetails;
import com.lti.entity.CropDetails;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.SellRequest;
import com.lti.repository.AdminRepository;
import com.lti.repository.FarmerRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	FarmerService farmerService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	BidderService bidderService;
	
	public long addAAdmin() {
		
		return adminRepository.addAAdmin();
	}
	
	public boolean rejectSellRequest(long sellRequestId) {
			System.out.println(sellRequestId);
			SellRequest sellRequest = farmerService.findSellRequestById(sellRequestId);
			// System.out.println(sellRequest.getRequestId());
			System.out.println(sellRequestId);
			FarmerPersonalDetails farmerPersonalDetails = sellRequest.getFarmerPersonalDetails();
			if(farmerPersonalDetails!=null) {
			System.out.println("found");
			}
			else {
			System.out.println("Found");
			}
			String farmerEmail = farmerPersonalDetails.getFarmerEmail();
			if(sellRequestId>0) {
			String subject="Sell Request Reject Confirmation!!";
			String text="Hi"+farmerPersonalDetails.getFarmerName()+"Your sell request applied for crop with id : "+ sellRequest.getCropDetails().getCropId() +" has been rejected by the admin .. you can check the same under view sold history option in your dashboard";
			emailService.sendEmailForNewRegistration(farmerEmail, text, subject);
			System.out.println("Email Sent.");
			}
			return adminRepository.rejectSellRequest(sellRequestId);
	}
	
	public boolean rejectAFarmer(long farmerId) {
		FarmerPersonalDetails farmerPersonalDetails = farmerService.findAFarmer(farmerId);
		   String farmerEmail = farmerPersonalDetails.getFarmerEmail();
			if(farmerId>0) {
				String subject="Registration UnSuccessfull!!";
				String text="Hi"+farmerPersonalDetails.getFarmerName()+"Due to some reasons your registration has been rejected   sorry to say this";
				emailService.sendEmailForNewRegistration(farmerEmail, text, subject);
				System.out.println("Email Sent.");
			}
		return adminRepository.rejectAFarmer(farmerId);
	}
	public boolean rejectABidder(long bidderId) {
		BidderPersonalDetails bidderPersonalDetails=bidderService.findBidderById(bidderId);
		   String bidderEmail = bidderPersonalDetails.getBidderEmail();
			if(bidderId>0) {
				String subject="Registration UnSuccessfull!!";
				String text="Hi"+bidderPersonalDetails.getBidderName()+"Due to some reasons your registration has been rejected   sorry to say this";
				emailService.sendEmailForNewRegistration(bidderEmail, text, subject);
				System.out.println("Email Sent.");
			}
		return adminRepository.rejectABidder(bidderId);
	}
	
	public List<FarmerPersonalDetails> viewAllFarmerRegistrationRequest() {
	List<FarmerPersonalDetails> u = adminRepository.viewAllFarmerRegistrationRequest();
	return u;
	}
	
	public boolean approveAFarmer(long farmerId){
		
	   FarmerPersonalDetails farmerPersonalDetails = farmerService.findAFarmer(farmerId);
	   String farmerEmail = farmerPersonalDetails.getFarmerEmail();
		if(farmerId>0) {
			String subject="Registration Successfull!!";
			String text="Hi"+farmerPersonalDetails.getFarmerName()+"Your registration id is :"+farmerId+"Now you can login to the portal using below mentioned id and password";
			emailService.sendEmailForNewRegistration(farmerEmail, text, subject);
			System.out.println("Email Sent.");
		}
	return adminRepository.approveAFarmer(farmerId);
	}


	public List<BidderPersonalDetails> viewAllBidderRegistrationRequests() {
		return adminRepository.viewAllBidderRegistrationRequests();
	}

	public boolean approveABidder(long bidderId) {
		BidderPersonalDetails bidderPersonalDetails=bidderService.findBidderById(bidderId);
		   String bidderEmail = bidderPersonalDetails.getBidderEmail();
			if(bidderId>0) {
				String subject="Registration Successfull!!";
				String text="Hi"+bidderPersonalDetails.getBidderName()+"Your registration id is :"+bidderId+"Now you can login to the portal using below mentioned id and password";
				emailService.sendEmailForNewRegistration(bidderEmail, text, subject);
				System.out.println("Email Sent.");
			}
		return adminRepository.approveABidder(bidderId);
	}

	public long addACropType(CropDetails cropDetails) {
		return adminRepository.addACropType(cropDetails);
	}

	public List<SellRequest> viewAllUnapprovedSellRequests() {
		return adminRepository.viewAllUnapprovedSellRequests();
	}

	public boolean removeACropType(CropDetails cropDetails) {
		return adminRepository.removeACropType(cropDetails);
	}
	
	public boolean approveSellRequest(long sellRequestId) {
		System.out.println(sellRequestId);
		SellRequest sellRequest = farmerService.findSellRequestById(sellRequestId);
//		System.out.println(sellRequest.getRequestId());
		System.out.println(sellRequestId);
		 FarmerPersonalDetails farmerPersonalDetails = sellRequest.getFarmerPersonalDetails();
		 if(farmerPersonalDetails!=null) {
			 System.out.println("found");
		 }
		 else {
			 System.out.println("Found");
		 }
		   String farmerEmail = farmerPersonalDetails.getFarmerEmail();
			if(sellRequestId>0) {
				String subject="Sell Request Approve Confirmation!!";
				String text="Hi"+farmerPersonalDetails.getFarmerName()+"Your sell request applied for crop with id : "+ sellRequest.getCropDetails().getCropId() +" has been approved by the admin .. you can check the same under view sold history option in your dashboard";
				emailService.sendEmailForNewRegistration(farmerEmail, text, subject);
				System.out.println("Email Sent.");
			}
		return adminRepository.approveSellRequest(sellRequestId);
	}

	public boolean approveFinalBidRequest(long bidRequestId) {
		
		BidRequest bidRequest = bidderService.findBidRequestById(bidRequestId);
		 SellRequest sellRequest = bidRequest.getSellRequest();
		   BidderPersonalDetails bidderPersonalDetails = bidRequest.getBidderPersonalDetails();
		   String bidderEmail=bidderPersonalDetails.getBidderEmail();
		   FarmerPersonalDetails farmerPersonalDetails = sellRequest.getFarmerPersonalDetails();
		   String farmerEmail=farmerPersonalDetails.getFarmerEmail();
		   List<BidRequest> bidRequests = sellRequest.getBidRequests();
		   
			if(bidRequestId>0) {
				String subject="Bid Request Approved!!";
				String text="Hi"+bidderPersonalDetails.getBidderName()+" Congratulations!! your bid has been approved for sell request with id:"+sellRequest.getRequestId() +"with amount "+bidRequest.getBidAmount();
				emailService.sendEmailForNewRegistration(bidderEmail, text, subject);
				System.out.println("Email Sent.");
				
				String subject1="Requested Crop has been Sold!!";
				String text1="Hi"+farmerPersonalDetails.getFarmerName()+"Comgratulations!! Your sell request applied for crop with id : "+ sellRequest.getCropDetails().getCropId() +" has been sold to bidder with id "+bidderPersonalDetails.getBidderId()+"with bid amount of Rs."+ bidRequest.getBidAmount() +".. you can check the same under view sold history option in your dashboard";
				emailService.sendEmailForNewRegistration(farmerEmail, text1, subject1);
				System.out.println("Email Sent.");
				
				for(BidRequest bidRequest2:bidRequests) {
					BidderPersonalDetails details = bidRequest2.getBidderPersonalDetails();
					String bidderEmail1=details.getBidderEmail();
					if(details.getBidderId()!=bidderPersonalDetails.getBidderId()) {
						String subject2="Bid Request Rejected!!";
						String text2="Hi"+bidderPersonalDetails.getBidderName()+" Sorry your bid has been rejected for sell request with id:"+sellRequest.getRequestId() +"with amount "+bidRequest2.getBidAmount();
						emailService.sendEmailForNewRegistration(bidderEmail1, text2, subject2);
						System.out.println("Email Sent.");
					}
				}
			}
		return adminRepository.approveFinalBidRequest(bidRequestId);
	}

	public boolean loginAdmin(long adminId, String adminPassword) {
		
		return adminRepository.loginAdmin(adminId, adminPassword);
	}

	
}