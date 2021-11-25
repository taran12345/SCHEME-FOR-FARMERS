package com.lti.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.dto.farmerLoginDetails;
import com.lti.entity.CropDetails;
import com.lti.entity.FarmerAddress;
import com.lti.entity.FarmerBankDetails;
import com.lti.entity.FarmerDocuments;
import com.lti.entity.FarmerLand;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.SellRequest;
import com.lti.entity.UploadFarmerDoc;

@Repository
public class FarmerRepositoryImpl implements FarmerRepository {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public long addAFarmer(FarmerPersonalDetails farmerPersonalDetails) {
		farmerPersonalDetails.setApproveStatus("pending");
		FarmerAddress farmerAddress=farmerPersonalDetails.getFarmerAddress();
		farmerAddress.setFarmerPersonalDetails(farmerPersonalDetails);
		FarmerBankDetails farmerBankDetails = farmerPersonalDetails.getFarmerBankDetails();
		farmerBankDetails.setFarmerPersonalDetails(farmerPersonalDetails);
		List<FarmerLand> farmerLands = farmerPersonalDetails.getFarmerLands();
		for(FarmerLand farmerLand:farmerLands) {
			farmerLand.setFarmerPersonalDetails(farmerPersonalDetails);
		}
		FarmerDocuments farmerDocuments = farmerPersonalDetails.getFarmerDocuments();
		farmerDocuments.setFarmerPersonalDetails(farmerPersonalDetails);
		FarmerPersonalDetails farmerDetails = em.merge(farmerPersonalDetails);
		return farmerDetails.getFarmerId();
	}
	
	@Transactional
	public long addAFarmerWithDoc(FarmerPersonalDetails farmerPersonalDetails, UploadFarmerDoc uploadFarmerDoc) {
		farmerPersonalDetails.setApproveStatus("pending");
		FarmerAddress farmerAddress=farmerPersonalDetails.getFarmerAddress();
		farmerAddress.setFarmerPersonalDetails(farmerPersonalDetails);
		FarmerBankDetails farmerBankDetails = farmerPersonalDetails.getFarmerBankDetails();
		farmerBankDetails.setFarmerPersonalDetails(farmerPersonalDetails);
		List<FarmerLand> farmerLands = farmerPersonalDetails.getFarmerLands();
		for(FarmerLand farmerLand:farmerLands) {
			farmerLand.setFarmerPersonalDetails(farmerPersonalDetails);
		}
		FarmerDocuments farmerDocuments = farmerPersonalDetails.getFarmerDocuments();
		farmerDocuments.setFarmerPersonalDetails(farmerPersonalDetails);
		farmerPersonalDetails.setUploadFarmerDoc(uploadFarmerDoc);
		uploadFarmerDoc.setFarmerPersonalDetails(farmerPersonalDetails);
		FarmerPersonalDetails farmerDetails = em.merge(farmerPersonalDetails);
		return farmerDetails.getFarmerId();
	}
	
	@Transactional
	public long updateAFarmer(FarmerPersonalDetails farmerPersonalDetails) {
		FarmerAddress farmerAddress=farmerPersonalDetails.getFarmerAddress();
		System.out.println(farmerAddress);
		farmerAddress.setFarmerPersonalDetails(farmerPersonalDetails);
		FarmerBankDetails farmerBankDetails = farmerPersonalDetails.getFarmerBankDetails();
		farmerBankDetails.setFarmerPersonalDetails(farmerPersonalDetails);
		List<FarmerLand> farmerLands = farmerPersonalDetails.getFarmerLands();
		for(FarmerLand farmerLand:farmerLands) {
			farmerLand.setFarmerPersonalDetails(farmerPersonalDetails);
			em.merge(farmerLand);
		}
		FarmerDocuments farmerDocuments = farmerPersonalDetails.getFarmerDocuments();
		farmerDocuments.setFarmerPersonalDetails(farmerPersonalDetails);
		FarmerPersonalDetails farmerDetails = em.merge(farmerPersonalDetails);
		return farmerDetails.getFarmerId();
	}
	

	@Transactional
	public FarmerPersonalDetails findAFarmer(long farmerId) {
		return em.find(FarmerPersonalDetails.class, farmerId);
	}
	

	@Transactional
	public farmerLoginDetails loginFarmer(long farmerId, String farmerPassword) {
		String status="approved";
		String jpql = "select f from FarmerPersonalDetails f where f.farmerId=:fId and f.farmerPassword=:fpassword and f.approveStatus=:astatus";

		TypedQuery<FarmerPersonalDetails> query = em.createQuery(jpql, FarmerPersonalDetails.class);
		query.setParameter("fId", farmerId);
		query.setParameter("fpassword", farmerPassword);
		query.setParameter("astatus", status);
		farmerLoginDetails farmerLoginDetails1 = new farmerLoginDetails();

		try {
			FarmerPersonalDetails farmer = query.getSingleResult();
			
			farmerLoginDetails1.setFarmerId(farmer.getFarmerId());
			farmerLoginDetails1.setFarmerName(farmer.getFarmerName());
			
			return farmerLoginDetails1;

		} catch (Exception e) {
			
			FarmerPersonalDetails farmerPersonalDetails = em.find(FarmerPersonalDetails.class, farmerId);
			if(farmerPersonalDetails!=null) {
				System.out.println(farmerPersonalDetails.getApproveStatus());
				String str = farmerPersonalDetails.getApproveStatus();
				String str1="pending";
				if(str.equals(str1)) {
					farmerLoginDetails1.setFarmerId(farmerPersonalDetails.getFarmerId());
					farmerLoginDetails1.setFarmerName("");
					return farmerLoginDetails1;
				}
			}

			farmerLoginDetails1.setFarmerId(0);
			farmerLoginDetails1.setFarmerName("");
			return farmerLoginDetails1;

		}
	}

	@Transactional
	public long sellCropRequest(long farmer_id, long cropId, int quantity) {
		
			SellRequest sellRequest = new SellRequest();
			

			FarmerPersonalDetails farmerPersonalDetails = em.find(FarmerPersonalDetails.class, farmer_id);
			sellRequest.setFarmerPersonalDetails(farmerPersonalDetails);
			CropDetails cropDetails = em.find(CropDetails.class, cropId);
			sellRequest.setCropDetails(cropDetails);
			sellRequest.setQuantity(quantity);
			sellRequest.setSellRequestApproveStatus("applied");
			SellRequest sellRequest2 = em.merge(sellRequest);
			return sellRequest2.getRequestId();
	}

	@Transactional
	public List<SellRequest> viewSoldHistory(long farmerId) {
		String jpql = "select s from SellRequest s where s. farmerPersonalDetails.farmerId=:fid";
		TypedQuery<SellRequest> query = em.createQuery(jpql, SellRequest.class);
		query.setParameter("fid", farmerId);
		List<SellRequest> requests = query.getResultList();
		return requests;
	}

	@Transactional
	public FarmerPersonalDetails findFarmerById(long farmerId) {
		FarmerPersonalDetails personalDetails = em.find(FarmerPersonalDetails.class, farmerId);
		return personalDetails;
	}
	
	@Transactional
	public SellRequest findSellRequestById(long sellRequestId) {
		return em.find(SellRequest.class,sellRequestId);
	}
	
	@Override
    public boolean forgotPassword(long farmerId) {
        return true;
    }
	

	
}
