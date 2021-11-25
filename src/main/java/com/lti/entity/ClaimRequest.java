package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_claimRequest")
public class ClaimRequest {
	
	@Id
	@SequenceGenerator(name="seq_claimRequest",initialValue=8001,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_claimRequest")
	long Id;
	
	String claimStatus;
	
	String causeForLoss;
	
	@OneToOne
	@JoinColumn(name="policy_no")
	InsuranceApplied insuranceApplied;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public String getCauseForLoss() {
		return causeForLoss;
	}

	public void setCauseForLoss(String causeForLoss) {
		this.causeForLoss = causeForLoss;
	}

	public InsuranceApplied getInsuranceApplied() {
		return insuranceApplied;
	}

	public void setInsuranceApplied(InsuranceApplied insuranceApplied) {
		this.insuranceApplied = insuranceApplied;
	}
	
	
	
}
