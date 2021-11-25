package com.lti.repository;

import java.util.List;

import com.lti.entity.BidRequest;
import com.lti.entity.CropDetails;
import com.lti.entity.SellRequest;

public interface MarketRepository {

	  List<CropDetails> viewAllCrops();
	  List<SellRequest> viewAllApprovedSellingRequests();
	  List<BidRequest> viewAllBiddingRequestsForOneSellRequest(long sellRequestId);
}
