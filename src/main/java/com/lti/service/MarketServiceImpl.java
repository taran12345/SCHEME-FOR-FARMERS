package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lti.entity.BidRequest;
import com.lti.entity.CropDetails;
import com.lti.entity.SellRequest;
import com.lti.repository.MarketRepository;
import com.lti.repository.MarketRepositoryImpl;

@Service
public class MarketServiceImpl implements MarketService {

	@Autowired
	MarketRepository marketRepository;

	public List<CropDetails> viewAllCrops() {
		List<CropDetails> u = marketRepository.viewAllCrops();
		return u;
	}
	 
    public List<SellRequest> viewAllApprovedSellingRequests(){
        List<SellRequest> u = marketRepository.viewAllApprovedSellingRequests();
           return u;
    }
       public List<BidRequest> viewAllBiddingRequestsForOneSellRequest(long sellRequestId){
           List<BidRequest> u = marketRepository.viewAllBiddingRequestsForOneSellRequest(sellRequestId);
           return u;
       }
}