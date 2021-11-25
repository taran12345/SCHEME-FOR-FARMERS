package com.lti.service;

 

import java.util.List;

import com.lti.dto.BidderLoginDetails;
import com.lti.entity.BidRequest;
import com.lti.entity.BidderPersonalDetails;
import com.lti.entity.CropDetails;

 


public interface BidderService {
    long addOrUpdateBidder(BidderPersonalDetails BidderPersonalDetails);
    List<CropDetails> viewCropDetails();
    long applyForBid(long sellRequest_id , long bidder_id ,int bidAmount);
    List<BidRequest> viewBidHistory(long bidderId);
    public BidRequest findBidRequestById(long bidRequestId);
    BidderPersonalDetails findBidderById(long bidderId);
    public void updateBidderDoc(long bidderId, String newFileName1, String newFileName2);
    public boolean forgotPassword(long bidderId);
    public BidderLoginDetails loginBidder(long bidderId, String bidderPassword);

}