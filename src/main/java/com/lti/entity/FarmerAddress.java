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
@Table(name="tbl_farmerAddress")
public class FarmerAddress {
	
	@Id
	@SequenceGenerator(name="seq_farmerAddress",initialValue=5001,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_farmerAddress")
	long farmerAddressId;
	String farmerAddress;
	String farmerCity;
	String farmerState;
	int   farmerPinCode;
	
	@OneToOne
	@JoinColumn(name="farmer_id")
	@JsonIgnore
	FarmerPersonalDetails farmerPersonalDetails;

	public long getFarmerAddressId() {
		return farmerAddressId;
	}

	public void setFarmerAddressId(long farmerAddressId) {
		this.farmerAddressId = farmerAddressId;
	}

	public String getFarmerAddress() {
		return farmerAddress;
	}

	public void setFarmerAddress(String farmerAddress) {
		this.farmerAddress = farmerAddress;
	}

	public String getFarmerCity() {
		return farmerCity;
	}

	public void setFarmerCity(String farmerCity) {
		this.farmerCity = farmerCity;
	}

	public String getFarmerState() {
		return farmerState;
	}

	public void setFarmerState(String farmerState) {
		this.farmerState = farmerState;
	}

	public int getFarmerPinCode() {
		return farmerPinCode;
	}

	public void setFarmerPinCode(int farmerPinCode) {
		this.farmerPinCode = farmerPinCode;
	}

	public FarmerPersonalDetails getFarmerPersonalDetails() {
		return farmerPersonalDetails;
	}

	public void setFarmerPersonalDetails(FarmerPersonalDetails farmerPersonalDetails) {
		this.farmerPersonalDetails = farmerPersonalDetails;
	}
	
	

}
