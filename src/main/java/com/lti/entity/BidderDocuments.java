package com.lti.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbl_bidderDocuments")
public class BidderDocuments {
	
	@Id
	@GeneratedValue
	long bidderDocumentsId;
	
	long aadharNo;
	String panNo;
	
	@OneToOne
	@JoinColumn(name="bidder_id")
	@JsonIgnore
	BidderPersonalDetails bidderPersonalDetails;

	public long getAadharNo() {
		return aadharNo;
	}

	public long getBidderDocumentsId() {
		return bidderDocumentsId;
	}

	public void setBidderDocumentsId(long bidderDocumentsId) {
		this.bidderDocumentsId = bidderDocumentsId;
	}

	public void setAadharNo(long aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public BidderPersonalDetails getBidderPersonalDetails() {
		return bidderPersonalDetails;
	}

	public void setBidderPersonalDetails(BidderPersonalDetails bidderPersonalDetails) {
		this.bidderPersonalDetails = bidderPersonalDetails;
	}
	
	

}
