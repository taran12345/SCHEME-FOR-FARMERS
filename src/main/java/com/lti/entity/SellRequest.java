package com.lti.entity;

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
@Table(name="tbl_sellRequest")
public class SellRequest {
	
	
	@Id
	@SequenceGenerator(name="seq_sellRequest",initialValue=9001,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_sellRequest")
	long requestId;
	double quantity;
	String sellRequestApproveStatus;
	
	@OneToOne
	@JoinColumn(name="crop_id")
	
	CropDetails cropDetails;
	
	@ManyToOne
	@JoinColumn(name="farmer_id")
	
	FarmerPersonalDetails farmerPersonalDetails;
	
	@OneToMany(mappedBy="sellRequest",cascade=CascadeType.ALL)
	@JsonIgnore
	List<BidRequest> bidRequests;

	
	
	
	public List<BidRequest> getBidRequests() {
		return bidRequests;
	}

	public void setBidRequests(List<BidRequest> bidRequests) {
		this.bidRequests = bidRequests;
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getSellRequestApproveStatus() {
		return sellRequestApproveStatus;
	}

	public void setSellRequestApproveStatus(String sellRequestApproveStatus) {
		this.sellRequestApproveStatus = sellRequestApproveStatus;
	}

	public CropDetails getCropDetails() {
		return cropDetails;
	}

	public void setCropDetails(CropDetails cropDetails) {
		this.cropDetails = cropDetails;
	}

	public FarmerPersonalDetails getFarmerPersonalDetails() {
		return farmerPersonalDetails;
	}

	public void setFarmerPersonalDetails(FarmerPersonalDetails farmerPersonalDetails) {
		this.farmerPersonalDetails = farmerPersonalDetails;
	}
	
	

}

