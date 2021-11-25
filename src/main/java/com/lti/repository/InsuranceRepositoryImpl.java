package com.lti.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Admin;
import com.lti.entity.ClaimRequest;
import com.lti.entity.CropDetails;
import com.lti.entity.InsuranceApplied;
import com.lti.entity.InsuranceDetails;

@Repository
public class InsuranceRepositoryImpl implements InsuranceRepository {

	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public long applyForInsurance(InsuranceApplied insuranceApplied) {
		InsuranceApplied insuranceApplied1 = em.merge(insuranceApplied);
		return insuranceApplied1.getPolicyNo();
	}

	@Override
	@Transactional
	public long addAnInsurance(InsuranceDetails insuranceDetails) {
		CropDetails cropDetails = insuranceDetails.getCropDetails();
		long cropId = cropDetails.getCropId();
		String jpql ="select i from InsuranceDetails i where i.cropDetails.cropId=:cid";
		TypedQuery<InsuranceDetails> query = em.createQuery(jpql, InsuranceDetails.class);
		query.setParameter("cid", cropId);
		List<InsuranceDetails> insuranceDetails1 = query.getResultList();
		System.out.println(insuranceDetails1.size());
		
		if(insuranceDetails1.size() != 0) {
			return 0;
		}
		cropDetails.setInsuranceDetails(insuranceDetails);
		CropDetails cropDetails1 = em.merge(cropDetails);
//		InsuranceDetails insuranceDetails1 = em.merge(insuranceDetails);
		return cropDetails1.getInsuranceDetails().getInsuranceId();
	}

	@Override
	@Transactional
	public List<InsuranceApplied> viewInsuranceHistory(long farmerId) {
		String jpql ="select i from InsuranceApplied i where i.farmerPersonalDetails.farmerId=:fid";
		TypedQuery<InsuranceApplied> query = em.createQuery(jpql, InsuranceApplied.class);
		query.setParameter("fid", farmerId);
		
	   try {
		List<InsuranceApplied> insuranceApplieds = query.getResultList();
		for(InsuranceApplied insuranceApplied : insuranceApplieds) {
			LocalDate date1 = insuranceApplied.getValidTill();
			LocalDate date2 = LocalDate.now();
			if(date2.compareTo(date1)>0) {
				insuranceApplied.setStatus("expired");
				em.merge(insuranceApplied);
			}
		}
			
	    return insuranceApplieds;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		return null;
	}
	}

	@Override
	@Transactional
	public long claimAnInsurance(ClaimRequest claimRequest) {
		String status="applied";
		long policyNo=claimRequest.getInsuranceApplied().getPolicyNo();
		String jpql ="select i from InsuranceApplied i where i.status=:astatus and i.policyNo=:aPNo";
		TypedQuery<InsuranceApplied> query = em.createQuery(jpql, InsuranceApplied.class);
		query.setParameter("astatus", status);
		query.setParameter("aPNo", policyNo);
		
		try {
			InsuranceApplied insuranceApplied = query.getSingleResult();
				System.out.println("Entered");
				insuranceApplied.setStatus("applied for claim");
				insuranceApplied.setClaimRequest(claimRequest);
				InsuranceApplied insuranceApplied1 = em.merge(insuranceApplied);
				
				
				return insuranceApplied1.getClaimRequest().getId();
		} catch (Exception e) {
			return 0;
		}
			
		}

	@Override
	@Transactional
	public boolean approveAClaimRequest(long claimRequestId) {
		try {
			ClaimRequest claimRequest = em.find(ClaimRequest.class, claimRequestId);
			InsuranceApplied insuranceApplied = claimRequest.getInsuranceApplied();
			claimRequest.setClaimStatus("approved");
			insuranceApplied.setStatus("claim approved");
			insuranceApplied.setClaimRequest(claimRequest);
			em.merge(insuranceApplied);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	@Transactional
	public boolean rejectAClaimRequest(long claimRequestId) {
		try {
			ClaimRequest claimRequest = em.find(ClaimRequest.class, claimRequestId);
			InsuranceApplied insuranceApplied = claimRequest.getInsuranceApplied();
			claimRequest.setClaimStatus("rejected");
			insuranceApplied.setStatus("claim rejected");
			insuranceApplied.setClaimRequest(claimRequest);
			em.merge(insuranceApplied);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional
	public List<ClaimRequest> viewAllUnapprovedClaimRequests() {
		String status="applied";
		String jpql ="select c from ClaimRequest c where c.claimStatus=:astatus";
		TypedQuery<ClaimRequest> query = em.createQuery(jpql, ClaimRequest.class);
		query.setParameter("astatus", status);
		
	   try {
		List<ClaimRequest> claimRequests = query.getResultList();
			
	    return claimRequests;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		return null;
	}
	}
	
	@Transactional
	public InsuranceApplied findAnInsurance(long policyNo) {
		return em.find(InsuranceApplied.class, policyNo);
	}
	
	@Transactional
	public InsuranceDetails findInsuranceByCrop(long cropId) {
		CropDetails cropDetails = em.find(CropDetails.class, cropId);
		return cropDetails.getInsuranceDetails();
	}
	
	@Transactional
	public CropDetails findCropByCropId(long cropId) {
		return em.find(CropDetails.class, cropId);
	}

}
