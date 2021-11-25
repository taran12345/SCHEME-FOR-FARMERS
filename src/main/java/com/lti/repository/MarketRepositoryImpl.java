package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.BidRequest;
import com.lti.entity.CropDetails;
import com.lti.entity.SellRequest;

@Repository
public class MarketRepositoryImpl implements MarketRepository {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public List<CropDetails> viewAllCrops() {
		String jpql = "select u from CropDetails u";
		TypedQuery<CropDetails> query = em.createQuery(jpql, CropDetails.class);
		return query.getResultList();
	}

	@Transactional
	public List<SellRequest> viewAllApprovedSellingRequests() {
		String status = "approved";
		String jpql = "select u from SellRequest u where u.sellRequestApproveStatus=:astatus";
		TypedQuery<SellRequest> query = em.createQuery(jpql, SellRequest.class);
		query.setParameter("astatus", status);
		return query.getResultList();
	}

	@Transactional
	public List<BidRequest> viewAllBiddingRequestsForOneSellRequest(long sellRequestId) {
		
		String jpql = "select u from BidRequest u where u.sellRequest.requestId=:astatus order by u.bidAmount desc";
		TypedQuery<BidRequest> query = em.createQuery(jpql, BidRequest.class);
		query.setParameter("astatus", sellRequestId);
		return query.getResultList();
	}
	
	
}