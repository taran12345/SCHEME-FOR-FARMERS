package com.lti.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbl_farmerBankDetails")
public class FarmerBankDetails {
	
	@Id
	@GeneratedValue
	long farmerBankDetailsId;
	
	long farmerAccountNo;
	
	String ifscCode;
	
	public long getFarmerBankDetailsId() {
		return farmerBankDetailsId;
	}

	public void setFarmerBankDetailsId(long farmerBankDetailsId) {
		this.farmerBankDetailsId = farmerBankDetailsId;
	}

	@OneToOne
	@JoinColumn(name="farmer_id")
	@JsonIgnore
	FarmerPersonalDetails farmerPersonalDetails;


	public long getFarmerAccountNo() {
		return farmerAccountNo;
	}

	public void setFarmerAccountNo(long farmerAccountNo) {
		this.farmerAccountNo = farmerAccountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public FarmerPersonalDetails getFarmerPersonalDetails() {
		return farmerPersonalDetails;
	}

	public void setFarmerPersonalDetails(FarmerPersonalDetails farmerPersonalDetails) {
		this.farmerPersonalDetails = farmerPersonalDetails;
	}
	
}
