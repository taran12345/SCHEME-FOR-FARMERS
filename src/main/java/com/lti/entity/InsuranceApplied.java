package com.lti.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_insuranceApplied")
public class InsuranceApplied {

	@Id
	@SequenceGenerator(name="seq_insuranceApplied",initialValue=7001,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_insuranceApplied")
	long policyNo;

	LocalDate validFrom;
	LocalDate validTill;

	double sharePremium;
	double premiumAmount;
	double sumInsured;
	double Area;
	String status;

	@OneToOne
	@JoinColumn(name="farmer_id")
	FarmerPersonalDetails farmerPersonalDetails;
	
	@ManyToOne
	@JoinColumn(name="insurance_id")
	InsuranceDetails insuranceDetails;
	
	@OneToOne(mappedBy="insuranceApplied",cascade=CascadeType.ALL)
	@JsonIgnore
	ClaimRequest claimRequest;
	

	public long getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(long policyNo) {
		this.policyNo = policyNo;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDate getValidTill() {
		return validTill;
	}

	public void setValidTill(LocalDate validTill) {
		this.validTill = validTill;
	}

	public double getPremiumAmount() {
		return premiumAmount;
	}

	public double getSharePremium() {
		return sharePremium;
	}

	public void setSharePremium(double sharePremium) {
		this.sharePremium = sharePremium;
	}

	public double getArea() {
		return Area;
	}

	public void setArea(double area) {
		Area = area;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public FarmerPersonalDetails getFarmerPersonalDetails() {
		return farmerPersonalDetails;
	}

	public void setFarmerPersonalDetails(FarmerPersonalDetails farmerPersonalDetails) {
		this.farmerPersonalDetails = farmerPersonalDetails;
	}

	public ClaimRequest getClaimRequest() {
		return claimRequest;
	}

	public void setClaimRequest(ClaimRequest claimRequest) {
		this.claimRequest = claimRequest;
	}

	public InsuranceDetails getInsuranceDetails() {
		return insuranceDetails;
	}

	public void setInsuranceDetails(InsuranceDetails insuranceDetails) {
		this.insuranceDetails = insuranceDetails;
	}

}
