package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbl_farmerPersonalDetails")
public class FarmerPersonalDetails {
	
	@Id
	@SequenceGenerator(name="seq_farmerPersonal",initialValue=7001,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_farmerPersonal")
	long farmerId;
	String farmerName;
	long farmerContact;
	String farmerEmail;
	String farmerPassword;
	
	String approveStatus;
	
	@OneToOne(mappedBy="farmerPersonalDetails",cascade=CascadeType.ALL)
	FarmerAddress farmerAddress;
	
	@OneToMany(mappedBy="farmerPersonalDetails",cascade= {CascadeType.PERSIST,
			CascadeType.DETACH,
			CascadeType.REFRESH,
			CascadeType.REMOVE},fetch=FetchType.EAGER)
	
	List<FarmerLand> farmerLands;
	
	@OneToOne(mappedBy="farmerPersonalDetails",cascade=CascadeType.ALL)
	FarmerBankDetails farmerBankDetails;
	
	@OneToOne(mappedBy="farmerPersonalDetails",cascade=CascadeType.ALL)
	FarmerDocuments farmerDocuments;
	
	@OneToMany(mappedBy="farmerPersonalDetails",cascade=CascadeType.ALL)
	@JsonIgnore
	List<SellRequest> sellRequests;
	
	@OneToOne(mappedBy="farmerPersonalDetails",cascade=CascadeType.ALL)
	@JsonIgnore
	InsuranceApplied insuranceApplied;
	
	@OneToOne(mappedBy="farmerPersonalDetails",cascade=CascadeType.ALL)
	UploadFarmerDoc uploadFarmerDoc;
	
	
	
	public UploadFarmerDoc getUploadFarmerDoc() {
		return uploadFarmerDoc;
	}
	public void setUploadFarmerDoc(UploadFarmerDoc uploadFarmerDoc) {
		this.uploadFarmerDoc = uploadFarmerDoc;
	}
	public InsuranceApplied getInsuranceApplied() {
		return insuranceApplied;
	}
	public void setInsuranceApplied(InsuranceApplied insuranceApplied) {
		this.insuranceApplied = insuranceApplied;
	}
	public List<SellRequest> getSellRequests() {
		return sellRequests;
	}
	public void setSellRequests(List<SellRequest> sellRequests) {
		this.sellRequests = sellRequests;
	}
	public FarmerAddress getFarmerAddress() {
		return farmerAddress;
	}
	public void setFarmerAddress(FarmerAddress farmerAddress) {
		this.farmerAddress = farmerAddress;
	}
	public List<FarmerLand> getFarmerLands() {
		return farmerLands;
	}
	public void setFarmerLands(List<FarmerLand> farmerLands) {
		this.farmerLands = farmerLands;
	}
	public FarmerBankDetails getFarmerBankDetails() {
		return farmerBankDetails;
	}
	public void setFarmerBankDetails(FarmerBankDetails farmerBankDetails) {
		this.farmerBankDetails = farmerBankDetails;
	}
	public FarmerDocuments getFarmerDocuments() {
		return farmerDocuments;
	}
	public void setFarmerDocuments(FarmerDocuments farmerDocuments) {
		this.farmerDocuments = farmerDocuments;
	}
	public long getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(long farmerId) {
		this.farmerId = farmerId;
	}
	public String getFarmerName() {
		return farmerName;
	}
	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}
	public long getFarmerContact() {
		return farmerContact;
	}
	public void setFarmerContact(long farmerContact) {
		this.farmerContact = farmerContact;
	}
	public String getFarmerEmail() {
		return farmerEmail;
	}
	public void setFarmerEmail(String farmerEmail) {
		this.farmerEmail = farmerEmail;
	}
	public String getFarmerPassword() {
		return farmerPassword;
	}
	public void setFarmerPassword(String farmerPassword) {
		this.farmerPassword = farmerPassword;
	}
	public String getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	
}
