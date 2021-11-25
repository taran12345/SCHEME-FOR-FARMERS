package com.lti.repository;

import java.util.List;

import com.lti.dto.farmerLoginDetails;
import com.lti.entity.CropDetails;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.SellRequest;
import com.lti.entity.UploadFarmerDoc;

public interface FarmerRepository {
	
	 long addAFarmer(FarmerPersonalDetails farmerPersonalDetails);
	 long sellCropRequest(long farmerId,  long cropId , int quantity);
	 FarmerPersonalDetails findFarmerById(long farmerId); 
	 List<SellRequest> viewSoldHistory(long farmerId);
	 public SellRequest findSellRequestById(long sellRequestId);
	 public FarmerPersonalDetails findAFarmer(long farmerId);
	 public long updateAFarmer(FarmerPersonalDetails farmerPersonalDetails);
	 public boolean forgotPassword(long farmerId);
	 public farmerLoginDetails loginFarmer(long farmerId, String farmerPassword);
	 public long addAFarmerWithDoc(FarmerPersonalDetails farmerPersonalDetails, UploadFarmerDoc uploadFarmerDoc);
	
}
