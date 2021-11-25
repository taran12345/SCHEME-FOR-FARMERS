package com.lti.dto;

public class BidRequestDetails {
    long sellRequestId;
    long bidderId;
    int bidAmount;
    
	public long getSellRequestId() {
		return sellRequestId;
	}
	public void setSellRequestId(long sellRequestId) {
		this.sellRequestId = sellRequestId;
	}
	public long getBidderId() {
		return bidderId;
	}
	public void setBidderId(long bidderId) {
		this.bidderId = bidderId;
	}
	public int getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}
	
    
    
}
