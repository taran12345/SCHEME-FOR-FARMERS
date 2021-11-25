package com.lti.repository;

import java.util.List;

import com.lti.entity.Admin;
import com.lti.entity.BidderPersonalDetails;
import com.lti.entity.CropDetails;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.SellRequest;

public interface AdminRepository {

	long addAAdmin();
	
	public List<BidderPersonalDetails> viewAllBidderRegistrationRequests();
	
	 boolean loginAdmin(long adminId, String adminPassword);

	public boolean approveABidder(long bidderId);

	public long addACropType(CropDetails cropDetails);

	public List<SellRequest> viewAllUnapprovedSellRequests();
	
	public boolean removeACropType(CropDetails cropDetails);

	public boolean approveSellRequest(long sellRequestId);
	
	public boolean rejectSellRequest(long sellRequestId);

	public boolean approveFinalBidRequest(long bidRequestId);
	
	public List<FarmerPersonalDetails> viewAllFarmerRegistrationRequest();
	
	public boolean approveAFarmer(long farmerId);
	
	public boolean rejectABidder(long bidderId);
	
	public boolean rejectAFarmer(long farmerId);

}