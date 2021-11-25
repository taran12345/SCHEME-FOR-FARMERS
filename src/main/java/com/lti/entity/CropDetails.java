package com.lti.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_cropDetails")
public class CropDetails {

	@Id
	@SequenceGenerator(name = "seq_cropDetails", initialValue = 4001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cropDetails")
	long cropId;
	String cropType;
	String cropName;
	double cropBasePrice;

	@OneToOne(mappedBy = "cropDetails", cascade = CascadeType.ALL)
	@JsonIgnore
	SellRequest sellRequest;

	@OneToOne(mappedBy = "cropDetails", cascade = CascadeType.ALL)
	@JsonIgnore
	InsuranceDetails insuranceDetails;

	public InsuranceDetails getInsuranceDetails() {
		return insuranceDetails;
	}

	public void setInsuranceDetails(InsuranceDetails insuranceDetails) {
		this.insuranceDetails = insuranceDetails;
	}

	public SellRequest getSellRequest() {
		return sellRequest;
	}

	public void setSellRequest(SellRequest sellRequest) {
		this.sellRequest = sellRequest;
	}

	public long getCropId() {
		return cropId;
	}

	public void setCropId(long cropId) {
		this.cropId = cropId;
	}

	public String getCropType() {
		return cropType;
	}

	public void setCropType(String cropType) {
		this.cropType = cropType;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public double getCropBasePrice() {
		return cropBasePrice;
	}

	public void setCropBasePrice(double cropBasePrice) {
		this.cropBasePrice = cropBasePrice;
	}

}
