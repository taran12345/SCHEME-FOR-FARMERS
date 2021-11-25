package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Admin;
import com.lti.entity.BidRequest;
import com.lti.entity.BidderPersonalDetails;
import com.lti.entity.CropDetails;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.SellRequest;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public List<BidderPersonalDetails> viewAllBidderRegistrationRequests() {
		String status = "pending";
		String jpql = "select b from BidderPersonalDetails b where b.approveStatus=:astatus ";
		Query query = em.createQuery(jpql);
		query.setParameter("astatus", status);
		List<BidderPersonalDetails> bidders = query.getResultList();
		return bidders;

	}
	@Transactional
	public long addAAdmin() {
		Admin admin= new Admin();
		admin.setAdminId(101);
		admin.setAdminPassword("ram");
		em.persist(admin);
		return 0;
	}
	@Transactional
	public boolean loginAdmin(long adminId, String adminPassword) {
		String jpql ="from Admin a where a.adminId=:aid and a.adminPassword=:apassword";
		TypedQuery<Admin> query = em.createQuery(jpql, Admin.class);
		query.setParameter("aid", adminId);
		query.setParameter("apassword", adminPassword);
		
	   try {
		Admin admin = query.getSingleResult();
			
				return true;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		return false;
	}
	}

	@Transactional
	public boolean approveABidder(long bidderId) {
		BidderPersonalDetails bidder = em.find(BidderPersonalDetails.class, bidderId);
		bidder.setApproveStatus("approved");
		BidderPersonalDetails bidderPersonalDetails = em.merge(bidder);
		if(bidderPersonalDetails!=null)
		return true;
		else
			return false;

	}
	
	@Transactional
	public boolean rejectABidder(long bidderId) {
		BidderPersonalDetails bidder = em.find(BidderPersonalDetails.class, bidderId);
		bidder.setApproveStatus("rejected");
		BidderPersonalDetails bidderPersonalDetails = em.merge(bidder);
		if(bidderPersonalDetails!=null)
		return true;
		else
			return false;

	}

	@Transactional
	public long addACropType(CropDetails cropDetails) {
		String cropType = cropDetails.getCropType();
		String cropName=cropDetails.getCropName();
		String jpql = "select c from CropDetails c where c.cropType=:aCType and c.cropName=:aCName ";
		Query query = em.createQuery(jpql);
		query.setParameter("aCType", cropType);
		query.setParameter("aCName", cropName);
		List<CropDetails> cropDetails1 = query.getResultList();
		if(cropDetails1.size()==0)
		{
			CropDetails details = em.merge(cropDetails);
			return details.getCropId();	
		}
		return 0;

	}
	
	@Transactional
	public boolean removeACropType(CropDetails cropDetails) {
		CropDetails cropDetails1 = em.merge(cropDetails);
		em.remove(cropDetails1);
		return true;

	}

	@Transactional
	public List<SellRequest> viewAllUnapprovedSellRequests() {
		String status = "applied";
		String jpql = "select s from SellRequest s where s.sellRequestApproveStatus=:astatus ";
		Query query = em.createQuery(jpql);
		query.setParameter("astatus", status);
		List<SellRequest> sellRequests = query.getResultList();
		return sellRequests;

	}

	@Transactional
	public boolean approveSellRequest(long sellRequestId) {
		SellRequest sellRequest = em.find(SellRequest.class, sellRequestId);
		sellRequest.setSellRequestApproveStatus("approved");
		SellRequest request = em.merge(sellRequest);
		if(request!=null)
		return true;
		else
			return false;

	}
	
	@Transactional
	public boolean rejectSellRequest(long sellRequestId) {
		SellRequest sellRequest = em.find(SellRequest.class, sellRequestId);
		sellRequest.setSellRequestApproveStatus("rejected");
		SellRequest request = em.merge(sellRequest);
		if(request!=null)
		return true;
		else
			return false;

	}

	@Transactional
	public boolean approveFinalBidRequest(long bidRequestId) {
		BidRequest bidRequest = em.find(BidRequest.class, bidRequestId);
		SellRequest sellRequest=bidRequest.getSellRequest();
		long sellRequestId=sellRequest.getRequestId();
		String jpql = "select b from BidRequest b where b.sellRequest.requestId=:rId";
		TypedQuery<BidRequest> query = em.createQuery(jpql, BidRequest.class);
		query.setParameter("rId", sellRequestId);
		List<BidRequest> bidRequests=query.getResultList();
		for(BidRequest b:bidRequests){
			b.setStatus("rejected");
			em.merge(b);
		}
		sellRequest.setSellRequestApproveStatus("sold");
		em.merge(sellRequest);
		bidRequest.setStatus("approved");
		em.merge(bidRequest);
		return true;

	}
	
	@Transactional
	public List<FarmerPersonalDetails> viewAllFarmerRegistrationRequest() {
		String status = "pending";
	String jpql = "select u from FarmerPersonalDetails u where u.approveStatus=:astatus";
	TypedQuery<FarmerPersonalDetails> query = em.createQuery(jpql, FarmerPersonalDetails.class);
	query.setParameter("astatus", status);
	return query.getResultList();
	}
	
	@Transactional
	public boolean approveAFarmer(long farmerId){
	FarmerPersonalDetails u = em.find(FarmerPersonalDetails.class, farmerId);
	u.setApproveStatus("approved");
	FarmerPersonalDetails farmerPersonalDetails=em.merge(u);
	if(farmerPersonalDetails != null)
	return true;
	else
		return false;
	}
	
	@Transactional
	public boolean rejectAFarmer(long farmerId){
	FarmerPersonalDetails u = em.find(FarmerPersonalDetails.class, farmerId);
	u.setApproveStatus("rejected");
	FarmerPersonalDetails farmerPersonalDetails=em.merge(u);
	if(farmerPersonalDetails != null)
	return true;
	else
		return false;
	}

}