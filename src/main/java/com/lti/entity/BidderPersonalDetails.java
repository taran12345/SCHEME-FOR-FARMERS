package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbl_bidderPersonalDetails")
public class BidderPersonalDetails {
	
	@Id
	@SequenceGenerator(name="seq_bidPersonal",initialValue=2001,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_bidPersonal")
	long bidderId;
	String bidderName;
	long bidderContact;
	String bidderEmail;
	String bidderPassword;
	
	String approveStatus;
	
	@OneToOne(mappedBy="bidderPersonalDetails",cascade=CascadeType.ALL)
	BidderAddress bidderAddress;
	
	@OneToOne(mappedBy="bidderPersonalDetails",cascade=CascadeType.ALL)
	BidderBankDetails bidderBankDetails;
	
	@OneToOne(mappedBy="bidderPersonalDetails",cascade=CascadeType.ALL)
	BidderDocuments bidderDocuments;
	
	@OneToMany(mappedBy="bidderPersonalDetails",cascade=CascadeType.ALL)
	@JsonIgnore
	List<BidRequest> bidRequest;
	
	@OneToOne(mappedBy="bidderPersonalDetails",cascade=CascadeType.ALL)
	UploadBidderDoc uploadBidderDoc;
	
	
	public List<BidRequest> getBidRequest() {
		return bidRequest;
	}
	public void setBidRequest(List<BidRequest> bidRequest) {
		this.bidRequest = bidRequest;
	}
	public BidderAddress getBidderAddress() {
		return bidderAddress;
	}
	public void setBidderAddress(BidderAddress bidderAddress) {
		this.bidderAddress = bidderAddress;
	}
	public BidderBankDetails getBidderBankDetails() {
		return bidderBankDetails;
	}
	public void setBidderBankDetails(BidderBankDetails bidderBankDetails) {
		this.bidderBankDetails = bidderBankDetails;
	}
	public BidderDocuments getBidderDocuments() {
		return bidderDocuments;
	}
	public void setBidderDocuments(BidderDocuments bidderDocuments) {
		this.bidderDocuments = bidderDocuments;
	}
	public long getBidderId() {
		return bidderId;
	}
	public void setBidderId(long bidderId) {
		this.bidderId = bidderId;
	}
	public String getBidderName() {
		return bidderName;
	}
	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}
	public long getBidderContact() {
		return bidderContact;
	}
	public void setBidderContact(long bidderContact) {
		this.bidderContact = bidderContact;
	}
	public String getBidderEmail() {
		return bidderEmail;
	}
	public void setBidderEmail(String bidderEmail) {
		this.bidderEmail = bidderEmail;
	}
	public String getBidderPassword() {
		return bidderPassword;
	}
	public void setBidderPassword(String bidderPassword) {
		this.bidderPassword = bidderPassword;
	}
	public String getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	public UploadBidderDoc getUploadBidderDoc() {
		return uploadBidderDoc;
	}
	public void setUploadBidderDoc(UploadBidderDoc uploadBidderDoc) {
		this.uploadBidderDoc = uploadBidderDoc;
	}
	
	

	
}
