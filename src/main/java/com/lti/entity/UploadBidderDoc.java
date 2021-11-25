package com.lti.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbl_uploadBidderDoc")
public class UploadBidderDoc {
	
	
	@Id
	@SequenceGenerator(name="seq_uploadBidderDoc",initialValue=10001,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_uploadBidderDoc")
	long bidderDocId; 
	String aadharImagePath;
	String panImagePath;
	
	@OneToOne
	@JoinColumn(name="bidder_id")
	@JsonIgnore
	BidderPersonalDetails bidderPersonalDetails;
	
	public String getAadharImagePath() {
		return aadharImagePath;
	}
	public void setAadharImagePath(String aadharImagePath) {
		this.aadharImagePath = aadharImagePath;
	}
	public String getPanImagePath() {
		return panImagePath;
	}
	public void setPanImagePath(String panImagePath) {
		this.panImagePath = panImagePath;
	}
	
	public long getBidderDocId() {
		return bidderDocId;
	}
	public void setBidderDocId(long bidderDocId) {
		this.bidderDocId = bidderDocId;
	}
	public BidderPersonalDetails getBidderPersonalDetails() {
		return bidderPersonalDetails;
	}
	public void setBidderPersonalDetails(BidderPersonalDetails bidderPersonalDetails) {
		this.bidderPersonalDetails = bidderPersonalDetails;
	}
	
	
}
