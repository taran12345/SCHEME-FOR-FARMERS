package com.lti.dto;

public class SellRequestWithCrop {
	long sellRequestId;
	double quantity;
	String cropType;
	String cropName;
	public long getSellRequestId() {
		return sellRequestId;
	}
	public void setSellRequestId(long sellRequestId) {
		this.sellRequestId = sellRequestId;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
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
	
	
}
