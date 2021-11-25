package com.lti.repository;

import java.util.List;

import com.lti.dto.BidderLoginDetails;
import com.lti.entity.BidRequest;
import com.lti.entity.BidderPersonalDetails;
import com.lti.entity.CropDetails;
import com.lti.entity.UploadBidderDoc;

public interface BidderRepository {

	long addOrUpdateBidder(BidderPersonalDetails BidderPersonalDetails);

	List<CropDetails> viewCropDetails();

	long applyForBid(long sellRequest_id, long bidder_id, int bidAmount);
	
	public BidderLoginDetails loginBidder(long bidderId, String bidderPassword);

	List<BidRequest> viewBidHistory(long bidderId);
	
	public BidRequest findBidRequestById(long bidRequestId);
	
	public long addABidderWithDoc(BidderPersonalDetails bidderPersonalDetails, UploadBidderDoc uploadBidderDoc);
	
	public boolean forgotPassword(long bidderId);

	BidderPersonalDetails findBidderById(long bidderId);
}