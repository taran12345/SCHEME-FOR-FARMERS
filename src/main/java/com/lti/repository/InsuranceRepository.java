package com.lti.repository;

import java.util.List;

import com.lti.entity.ClaimRequest;
import com.lti.entity.CropDetails;
import com.lti.entity.InsuranceApplied;
import com.lti.entity.InsuranceDetails;

public interface InsuranceRepository {
	public long applyForInsurance(InsuranceApplied insuranceApplied);
	public long addAnInsurance(InsuranceDetails insuranceDetails);
	public List<InsuranceApplied> viewInsuranceHistory(long farmerId);
	public long claimAnInsurance(ClaimRequest claimRequest);
	public List<ClaimRequest> viewAllUnapprovedClaimRequests();
	public boolean approveAClaimRequest(long claimRequestId);
	public InsuranceApplied findAnInsurance(long policyNo);
	public InsuranceDetails findInsuranceByCrop(long cropId);
	public boolean rejectAClaimRequest(long claimRequestId);
	public CropDetails findCropByCropId(long cropId);
}
