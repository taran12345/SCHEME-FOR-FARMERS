package com.lti.dto;

import org.springframework.web.multipart.MultipartFile;

public class BidderDocDetails {
	private MultipartFile aadharImg;
	private MultipartFile panImg;
	
	public MultipartFile getAadharImg() {
		return aadharImg;
	}
	public void setAadharImg(MultipartFile aadharImg) {
		this.aadharImg = aadharImg;
	}
	public MultipartFile getPanImg() {
		return panImg;
	}
	public void setPanImg(MultipartFile panImg) {
		this.panImg = panImg;
	}
	
	
	
	
}
