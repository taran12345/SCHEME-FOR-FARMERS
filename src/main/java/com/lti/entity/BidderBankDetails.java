package com.lti.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbl_bidderBankDetails")
public class BidderBankDetails {
	
	@Id
	@GeneratedValue
	long bidderBankDetailsId;
	
	long accountNo;
	String ifscCode;
	
	@OneToOne
	@JoinColumn(name="bidder_id")
	@JsonIgnore
	BidderPersonalDetails bidderPersonalDetails;

	public long getBidderBankDetailsId() {
		return bidderBankDetailsId;
	}

	public void setBidderBankDetailsId(long bidderBankDetailsId) {
		this.bidderBankDetailsId = bidderBankDetailsId;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public BidderPersonalDetails getBidderPersonalDetails() {
		return bidderPersonalDetails;
	}

	public void setBidderPersonalDetails(BidderPersonalDetails bidderPersonalDetails) {
		this.bidderPersonalDetails = bidderPersonalDetails;
	}
	
	
	

}
