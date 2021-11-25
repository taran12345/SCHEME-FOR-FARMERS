package com.lti.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.dto.BidderLoginDetails;
import com.lti.dto.farmerLoginDetails;
import com.lti.entity.BidRequest;
import com.lti.entity.BidderAddress;
import com.lti.entity.BidderBankDetails;
import com.lti.entity.BidderDocuments;
import com.lti.entity.BidderPersonalDetails;
import com.lti.entity.CropDetails;
import com.lti.entity.FarmerAddress;
import com.lti.entity.FarmerBankDetails;
import com.lti.entity.FarmerDocuments;
import com.lti.entity.FarmerLand;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.SellRequest;
import com.lti.entity.UploadBidderDoc;
import com.lti.entity.UploadFarmerDoc;

@Repository
public class BidderRepositoryImpl implements BidderRepository {
	@PersistenceContext
	EntityManager em;

	@Transactional
	public long addOrUpdateBidder(BidderPersonalDetails bidderPersonalDetails) {
		if(bidderPersonalDetails.getBidderId()==0) {
			bidderPersonalDetails.setApproveStatus("pending");
		}
        BidderAddress bidderAddress=bidderPersonalDetails.getBidderAddress();
        bidderAddress.setBidderPersonalDetails(bidderPersonalDetails);
        BidderBankDetails bidderBankDetails = bidderPersonalDetails.getBidderBankDetails();
        bidderBankDetails.setBidderPersonalDetails(bidderPersonalDetails);
        BidderDocuments bidderDocuments = bidderPersonalDetails.getBidderDocuments();
        bidderDocuments.setBidderPersonalDetails(bidderPersonalDetails);
        BidderPersonalDetails bidderDetails = em.merge(bidderPersonalDetails);
        return bidderDetails.getBidderId();
	}
	
	@Transactional
	public long addABidderWithDoc(BidderPersonalDetails bidderPersonalDetails, UploadBidderDoc uploadBidderDoc) {
		bidderPersonalDetails.setApproveStatus("pending");
		BidderAddress bidderAddress=bidderPersonalDetails.getBidderAddress();
		bidderAddress.setBidderPersonalDetails(bidderPersonalDetails);
		BidderBankDetails bidderBankDetails = bidderPersonalDetails.getBidderBankDetails();
		bidderBankDetails.setBidderPersonalDetails(bidderPersonalDetails);
		BidderDocuments bidderDocuments = bidderPersonalDetails.getBidderDocuments();
		bidderDocuments.setBidderPersonalDetails(bidderPersonalDetails);
		bidderPersonalDetails.setUploadBidderDoc(uploadBidderDoc);
		uploadBidderDoc.setBidderPersonalDetails(bidderPersonalDetails);
		BidderPersonalDetails bidderDetails = em.merge(bidderPersonalDetails);
		return bidderDetails.getBidderId();
	}

	@Transactional
	public BidderLoginDetails loginBidder(long bidderId, String bidderPassword) {
		String status = "approved";
		String jpql = "select b from BidderPersonalDetails b where b.bidderId=:bId and b.bidderPassword=:bpassword and b.approveStatus=:astatus";

		TypedQuery<BidderPersonalDetails> query = em.createQuery(jpql, BidderPersonalDetails.class);
		query.setParameter("bId", bidderId);
		query.setParameter("bpassword", bidderPassword);
		query.setParameter("astatus", status);

		BidderLoginDetails bidderLoginDetails = new BidderLoginDetails();

		try {
			BidderPersonalDetails bidder = query.getSingleResult();

			bidderLoginDetails.setBidderId(bidder.getBidderId());
			bidderLoginDetails.setBidderName(bidder.getBidderName());
			return bidderLoginDetails;

		} catch (Exception e) {
			
			BidderPersonalDetails bidderPersonalDetails = em.find(BidderPersonalDetails.class, bidderId);
			if(bidderPersonalDetails!=null) {
				System.out.println(bidderPersonalDetails.getApproveStatus());
				String str = bidderPersonalDetails.getApproveStatus();
				String str1="pending";
				if(str.equals(str1)) {
					bidderLoginDetails.setBidderId(bidderPersonalDetails.getBidderId());
					bidderLoginDetails.setBidderName("");
					return bidderLoginDetails;
				}
			}

			bidderLoginDetails.setBidderId(0);
			bidderLoginDetails.setBidderName("");
			return bidderLoginDetails;

		}

	}

	@Transactional
	public List<CropDetails> viewCropDetails() {
		String jpql = "select c from CropDetails c";
		Query query = em.createQuery(jpql);
		List<CropDetails> crops = query.getResultList();
		return crops;
	}

	@Transactional
	public long applyForBid(long sellRequest_id, long bidder_id, int bidAmount) {

		double maxAmount = 0;
		String jpql1 = "select b from BidRequest b where b.sellRequest.requestId=:sid";
		Query query = em.createQuery(jpql1, BidRequest.class);
		query.setParameter("sid", sellRequest_id);
	    List<BidRequest> bidRequests = query.getResultList();
	    for(BidRequest bidRequest:bidRequests) {
	    	if(maxAmount < bidRequest.getBidAmount()) {
	    		maxAmount=bidRequest.getBidAmount();
	    	}
	    }
		String jpql = "select b from BidRequest b where b.bidderPersonalDetails.bidderId=:bid and b.sellRequest.requestId=:sid";
		TypedQuery<BidRequest> query1 = em.createQuery(jpql, BidRequest.class);
		query1.setParameter("bid", bidder_id);
		query1.setParameter("sid", sellRequest_id);
		SellRequest sellRequest1 = em.find(SellRequest.class, sellRequest_id);
		CropDetails cropDetails = sellRequest1.getCropDetails();
		try {
			BidRequest req = query1.getSingleResult();
			return 0;
		} catch (Exception e) {
			if(bidAmount < cropDetails.getCropBasePrice())
			{
				return -1;
			}
			if(bidAmount < maxAmount) {
				return -2;
			}
			SellRequest request = em.find(SellRequest.class, sellRequest_id);
			BidderPersonalDetails bidderPersonalDetails = em.find(BidderPersonalDetails.class, bidder_id);
			BidRequest bidRequest = new BidRequest();
			bidRequest.setBidAmount(bidAmount);
			bidRequest.setBidDate(LocalDate.now());
			bidRequest.setStatus("applied");
			bidRequest.setSellRequest(request);
			bidRequest.setBidderPersonalDetails(bidderPersonalDetails);
			BidRequest bidRequest2 = em.merge(bidRequest);
			return bidRequest2.getBidRequestId();

		}
	}

	@Transactional
	public List<BidRequest> viewBidHistory(long bidderId) {
		String jpql = "select b from BidRequest b where b.bidderPersonalDetails.bidderId=:bId";
		Query query = em.createQuery(jpql);
		query.setParameter("bId", bidderId);
		List<BidRequest> requests = query.getResultList();
		return requests;
	}

	@Transactional
	public BidderPersonalDetails findBidderById(long bidderId) {
		return em.find(BidderPersonalDetails.class, bidderId);
	}
	
	@Transactional
	public BidRequest findBidRequestById(long bidRequestId) {
		return em.find(BidRequest.class, bidRequestId);
	}
	
	@Transactional
	public boolean forgotPassword(long bidderId) {
		return true;
	}
	
	
}