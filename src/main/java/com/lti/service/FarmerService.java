package com.lti.service;

import java.util.List;

import com.lti.dto.farmerLoginDetails;
import com.lti.entity.CropDetails;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.SellRequest;

public interface FarmerService {

	 long addAFarmer(FarmerPersonalDetails farmerPersonalDetails);
	 long sellCropRequest(long farmerId,  long cropId , int quantity);
	 FarmerPersonalDetails findFarmerById(long farmerId);
	 List<SellRequest> viewSoldHistory(long farmerId);
	 public farmerLoginDetails loginFarmer(long farmerId, String farmerPassword);
	 public SellRequest findSellRequestById(long sellRequestId);
	 public boolean forgotPassword(long farmerId);
	 public long updateAFarmer(FarmerPersonalDetails farmerPersonalDetails);
	 
	 public FarmerPersonalDetails findAFarmer(long farmerId);
	 public void updateFarmerDoc(long farmerId, String newFileName1, String newFilename2);
}
