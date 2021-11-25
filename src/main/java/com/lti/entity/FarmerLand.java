package com.lti.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbl_farmerLand")
public class FarmerLand {
	
	@Id
	@SequenceGenerator(name="seq_landAddress",initialValue=6001,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_landAddress")
	long landId;
	String area;
	String landAddress;
	int landPinCode;
	
	@ManyToOne
	@JoinColumn(name="farmer_id")
	@JsonIgnore
	FarmerPersonalDetails farmerPersonalDetails;

	public long getLandId() {
		return landId;
	}

	public void setLandId(long landId) {
		this.landId = landId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLandAddress() {
		return landAddress;
	}

	public void setLandAddress(String landAddress) {
		this.landAddress = landAddress;
	}

	public int getLandPinCode() {
		return landPinCode;
	}

	public void setLandPinCode(int landPinCode) {
		this.landPinCode = landPinCode;
	}

	public FarmerPersonalDetails getFarmerPersonalDetails() {
		return farmerPersonalDetails;
	}

	public void setFarmerPersonalDetails(FarmerPersonalDetails farmerPersonalDetails) {
		this.farmerPersonalDetails = farmerPersonalDetails;
	}
	

}
