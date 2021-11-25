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
@Table(name="tbl_uploadFarmerDoc")
public class UploadFarmerDoc {
	
	
	@Id
	@SequenceGenerator(name="seq_uploadFarmerDoc",initialValue=10001,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_uploadFarmerDoc")
	long farmerDocId; 
	String aadharImagePath;
	String panImagePath;
	
	@OneToOne
	@JoinColumn(name="farmer_id")
	@JsonIgnore
	FarmerPersonalDetails farmerPersonalDetails;
	
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
	
	public long getFarmerDocId() {
		return farmerDocId;
	}
	public void setFarmerDocId(long farmerDocId) {
		this.farmerDocId = farmerDocId;
	}
	public FarmerPersonalDetails getFarmerPersonalDetails() {
		return farmerPersonalDetails;
	}
	public void setFarmerPersonalDetails(FarmerPersonalDetails farmerPersonalDetails) {
		this.farmerPersonalDetails = farmerPersonalDetails;
	}
	
	
}
