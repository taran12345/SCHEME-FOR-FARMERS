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
@Table(name="tbl_bidderAddress")
public class BidderAddress {

	@Id
	@SequenceGenerator(name="seq_bidaddress",initialValue=1001,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_bidaddress")
	long addressId;
	String address;
	String bidderCity;
	String bidderState;
	int   bidderPinCode;
	
	@OneToOne
	@JoinColumn(name="bidder_id")
	@JsonIgnore
	BidderPersonalDetails bidderPersonalDetails;

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBidderCity() {
		return bidderCity;
	}

	public void setBidderCity(String bidderCity) {
		this.bidderCity = bidderCity;
	}

	public String getBidderState() {
		return bidderState;
	}

	public void setBidderState(String bidderState) {
		this.bidderState = bidderState;
	}

	public int getBidderPinCode() {
		return bidderPinCode;
	}

	public void setBidderPinCode(int bidderPinCode) {
		this.bidderPinCode = bidderPinCode;
	}

	public BidderPersonalDetails getBidderPersonalDetails() {
		return bidderPersonalDetails;
	}

	public void setBidderPersonalDetails(BidderPersonalDetails bidderPersonalDetails) {
		this.bidderPersonalDetails = bidderPersonalDetails;
	}
	
	
}
