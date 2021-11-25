package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.ClaimRequest;
import com.lti.entity.CropDetails;
import com.lti.entity.InsuranceApplied;
import com.lti.entity.InsuranceDetails;
import com.lti.repository.InsuranceRepository;

@Service
public class InsuranceServiceImpl implements InsuranceService {
	
	@Autowired
	InsuranceRepository insuranceRepository;

	@Override
	public long applyForInsurance(InsuranceApplied insuranceApplied) {
		return insuranceRepository.applyForInsurance(insuranceApplied);
	}
	
	@Override
	public long claimAnInsurance(ClaimRequest claimRequest) {
		return insuranceRepository.claimAnInsurance(claimRequest);
	}

	@Override
	public boolean approveAClaimRequest(long claimRequestId) {
		return insuranceRepository.approveAClaimRequest(claimRequestId);
	}
	
	public boolean rejectAClaimRequest(long claimRequestId) {
		return insuranceRepository.rejectAClaimRequest(claimRequestId);
	}


	@Override
	public long addAnInsurance(InsuranceDetails insuranceDetails) {
		// TODO Auto-generated method stub
		return insuranceRepository.addAnInsurance(insuranceDetails);
	}


	@Override
	public List<InsuranceApplied> viewInsuranceHistory(long farmerId) {
		// TODO Auto-generated method stub
		return insuranceRepository.viewInsuranceHistory(farmerId);
	}

	@Override
	public List<ClaimRequest> viewAllUnapprovedClaimRequests() {
		// TODO Auto-generated method stub
		return insuranceRepository.viewAllUnapprovedClaimRequests();
	}
	
	public InsuranceApplied findAnInsurance(long policyNo) {
		return insuranceRepository.findAnInsurance(policyNo);
	}

	@Override
	public InsuranceDetails findInsuranceByCrop(long cropId) {
		return insuranceRepository.findInsuranceByCrop(cropId);
	}
	
	public CropDetails findCropByCropId(long cropId) {
		return insuranceRepository.findCropByCropId(cropId);
	}

}
