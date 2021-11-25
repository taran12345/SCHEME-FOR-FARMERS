package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dto.farmerLoginDetails;
import com.lti.entity.CropDetails;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.SellRequest;
import com.lti.entity.UploadFarmerDoc;
import com.lti.repository.FarmerRepository;

@Service
public class FarmerServiceImpl implements FarmerService {

	@Autowired
	FarmerRepository farmerRepository;
	
	@Autowired
	EmailService emailService;
	
	public long addAFarmer(FarmerPersonalDetails farmerPersonalDetails) {
	
		long farmerId = farmerRepository.addAFarmer(farmerPersonalDetails);
	    String farmerEmail = farmerPersonalDetails.getFarmerEmail();
		if(farmerId>0) {
			String subject="Your Registration has been sent for approval..Once it is approved you will get notified through a mail";
			String text="Hi"+farmerPersonalDetails.getFarmerName()+"!! Your registration id is :"+farmerId;
			emailService.sendEmailForNewRegistration(farmerEmail, text, subject);
			System.out.println("Email Sent.");
		}
		return farmerId; 
	}

	public farmerLoginDetails loginFarmer(long farmerId, String farmerPassword) {
		return farmerRepository.loginFarmer(farmerId, farmerPassword);
	}
	
	public FarmerPersonalDetails findAFarmer(long farmerId) {
		return farmerRepository.findFarmerById(farmerId);
	}

	public long sellCropRequest(long farmerId, long cropId , int quantity) {
		 FarmerPersonalDetails farmerPersonalDetails = farmerRepository.findAFarmer(farmerId);
		   String farmerEmail = farmerPersonalDetails.getFarmerEmail();
			if(farmerId>0) {
				String subject="Sell Request status";
				String text="Hi"+farmerPersonalDetails.getFarmerName()+"Your sell request has been placed for approval .. You can check the status for the same in the view sold history option in your dashboard";
				emailService.sendEmailForNewRegistration(farmerEmail, text, subject);
				System.out.println("Email Sent.");
			}
		
		return farmerRepository.sellCropRequest(farmerId, cropId, quantity);
	}
	
	public SellRequest findSellRequestById(long sellRequestId) {
		return farmerRepository.findSellRequestById(sellRequestId);
	}

	public  List<SellRequest>viewSoldHistory(long farmerId) {
		return farmerRepository.viewSoldHistory(farmerId);

	}
	
	public long updateAFarmer(FarmerPersonalDetails farmerPersonalDetails) {
		return farmerRepository.updateAFarmer(farmerPersonalDetails);
	}
	
	 
	public FarmerPersonalDetails findFarmerById(long farmerId) {
		 return farmerRepository.findFarmerById(farmerId);
	 }

	
	public void updateFarmerDoc(long farmerId, String newFileName1, String newFileName2) {
		FarmerPersonalDetails farmerPersonalDetails = farmerRepository.findFarmerById(farmerId);
		
		UploadFarmerDoc uploadFarmerDoc = new UploadFarmerDoc();
		uploadFarmerDoc.setAadharImagePath(newFileName1);
		uploadFarmerDoc.setPanImagePath(newFileName2);
		uploadFarmerDoc.setFarmerPersonalDetails(farmerPersonalDetails);
		farmerPersonalDetails.setUploadFarmerDoc(uploadFarmerDoc);
		farmerRepository.addAFarmerWithDoc(farmerPersonalDetails, uploadFarmerDoc);
	}
	
	@Override
    public boolean forgotPassword(long farmerId) {
           FarmerPersonalDetails farmerPersonalDetails = farmerRepository.findAFarmer(farmerId);
           if(farmerPersonalDetails==null)
           {
        	   return false;
           }
           String farmerEmail = farmerPersonalDetails.getFarmerEmail();
            if(farmerId>0) {
                String subject="Password";
                String text="Hi "+farmerPersonalDetails.getFarmerName()+"Your Password is :"+farmerPersonalDetails.getFarmerPassword()+"Now you can login to the portal using below mentioned password";
                emailService.sendEmailForNewRegistration(farmerEmail, text, subject);
                System.out.println("Email Sent.");
            }
        return farmerRepository.forgotPassword(farmerId);
        }

}
