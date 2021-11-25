package com.lti.service;

import java.util.List;

import com.lti.entity.BidRequest;
import com.lti.entity.CropDetails;
import com.lti.entity.SellRequest;

public interface MarketService {

	 List<CropDetails> viewAllCrops();
	 List<SellRequest> viewAllApprovedSellingRequests();
	 List<BidRequest> viewAllBiddingRequestsForOneSellRequest(long sellRequestId);
}
