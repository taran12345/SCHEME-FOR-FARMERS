package com.lti.controller;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.BidRequestDetails;
import com.lti.dto.BidderDocDetails;
import com.lti.dto.BidderLoginDetails;
import com.lti.dto.FarmerDocDetails;
import com.lti.dto.SellRequestWithCrop;
import com.lti.dto.adminLogin;
import com.lti.dto.bidderLogin;
import com.lti.dto.claimDetails;
import com.lti.dto.farmerLogin;
import com.lti.dto.farmerLoginDetails;
import com.lti.dto.sellRequestDetails;
import com.lti.entity.BidRequest;
import com.lti.entity.BidderPersonalDetails;
import com.lti.entity.ClaimRequest;
import com.lti.entity.CropDetails;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.InsuranceApplied;
import com.lti.entity.InsuranceDetails;
import com.lti.entity.SellRequest;
import com.lti.entity.UploadFarmerDoc;
import com.lti.service.AdminService;
import com.lti.service.BidderService;
import com.lti.service.FarmerService;
import com.lti.service.InsuranceService;
import com.lti.service.MarketService;

@RestController
@CrossOrigin
public class SchemeForFarmerResource {
	@Autowired
	AdminService adminService;
	
	@Autowired
	BidderService bidderService;

	@Autowired
	FarmerService farmerService;
	
	@Autowired
	MarketService marketService;
	
	@Autowired
	InsuranceService insuranceService;

	@RequestMapping("/viewAllFarmerRequests")
	public List<FarmerPersonalDetails> viewAllFarmerRegistrationRequest() {
		List<FarmerPersonalDetails> u = adminService.viewAllFarmerRegistrationRequest();
		return u;
		}
	
	@RequestMapping("/viewAllBidderRequests")
	public List<BidderPersonalDetails> viewAllBidderRegistrationRequest() {
		List<BidderPersonalDetails> u = adminService.viewAllBidderRegistrationRequests();
		return u;
		}
	
	@GetMapping("/viewAllUnapprovedSellRequests")
	public List<SellRequest> viewAllUnapprovedSellRequests(){
		return adminService.viewAllUnapprovedSellRequests();
	}
	
	@GetMapping("/viewAllBidRequestsForOneSellRequest/{sellRequestId}")
	public List<BidRequest> viewAllBidRequestsForSellRequest(@PathVariable("sellRequestId") long sellRequestId){
		return marketService.viewAllBiddingRequestsForOneSellRequest(sellRequestId);
	}
	
	@GetMapping("/viewAllApprovedSellRequests")
	public List<SellRequest> viewAllApprovedSellRequests(){
		List<SellRequest> sellRequests = marketService.viewAllApprovedSellingRequests();
		return sellRequests;
//		List<SellRequestWithCrop> requestWithCrops = new ArrayList<SellRequestWithCrop>();
//		for(SellRequest sellRequest:sellRequests) {
//			SellRequestWithCrop requestWithCrop = new SellRequestWithCrop();
//			requestWithCrop.setSellRequestId(sellRequest.getRequestId());
//			requestWithCrop.setQuantity(sellRequest.getQuantity());
//			requestWithCrop.setCropType(sellRequest.getCropDetails().getCropType());
//			requestWithCrop.setCropName(sellRequest.getCropDetails().getCropName());
//			requestWithCrops.add(requestWithCrop);
//		}
//		return requestWithCrops;
	}
	
	@GetMapping("/viewAllCrops")
	public List<CropDetails> viewAllCrops(){
		return marketService.viewAllCrops();
	}
	
	@GetMapping("/viewSoldHistory/{farmerId}")
	public List<SellRequest> viewSoldHistory(@PathVariable("farmerId") long farmerId){
		return farmerService.viewSoldHistory(farmerId);
	}
	
	@GetMapping("/viewBidHistory/{bidderId}")
	public List<BidRequest> viewBidRequests(@PathVariable("bidderId") long bidderId){
		return bidderService.viewBidHistory(bidderId);
	}
	
	@GetMapping("/approveAFarmer/{farmerId}")
	public boolean approveAFarmer(@PathVariable("farmerId") long farmerId) {
		return adminService.approveAFarmer(farmerId);
	}
	
	@GetMapping("/rejectAFarmer/{farmerId}")
	public boolean rejectAFarmer(@PathVariable("farmerId") long farmerId) {
		return adminService.rejectAFarmer(farmerId);
	}
	
	@PostMapping(value="/docsUpload/{farmerId}", consumes = {"multipart/form-data"})
	public Boolean upload(@ModelAttribute FarmerDocDetails farmerDocDetails, @PathVariable("farmerId") long farmerId) {
		String imgUploadLocation1 = "c:/uploads/";
		String imgUploadLocation2 = "c:/uploads/";
		String uploadedFileName1 = farmerDocDetails.getAadharImg().getOriginalFilename();
		String uploadedFileName2 = farmerDocDetails.getPanImg().getOriginalFilename();
		String newFileName1 = farmerId + "-" + uploadedFileName1;
		String newFileName2 = farmerId + "-" + uploadedFileName2;
		
		String targetFileName1 = imgUploadLocation1 + newFileName1;
		String targetFileName2 = imgUploadLocation2 + newFileName2;
		
		try {
			FileCopyUtils.copy(farmerDocDetails.getAadharImg().getInputStream(), new FileOutputStream(targetFileName1));
			FileCopyUtils.copy(farmerDocDetails.getPanImg().getInputStream(), new FileOutputStream(targetFileName2));
		} catch(IOException e) {
			e.printStackTrace(); //hoping no error would occur
			return false;
		}
		
		farmerService.updateFarmerDoc(farmerId, newFileName1, newFileName2);
		return true;
	}
	
	@GetMapping("/docsDownload")
    public FarmerPersonalDetails download(@RequestParam("farmerId") int farmerid, HttpServletRequest request) {
        FarmerPersonalDetails farmerPersonalDetails = farmerService.findFarmerById(farmerid);
   
        //the problem is that the image is in some another folder outside this project
        //because of this, on the client we will not be able to access it by default
        //we need to write the code to copy the image from d:/uploads folder temporarily into this project of ours
   
        //reading the project's deployed location
        String projPath = request.getServletContext().getRealPath("/");
        System.out.println(projPath);
        String tempDownloadPath = projPath + "downloads/";
//        creating this downloads folder in case if it doesn't exist
        File f = new File(tempDownloadPath);
        if(!f.exists())
            f.mkdir();
        System.out.println(tempDownloadPath);
        //the target location where we will save the profile pic of the customer
        String targetFile1 = tempDownloadPath + farmerPersonalDetails.getUploadFarmerDoc().getAadharImagePath();
       String targetFile2 = tempDownloadPath + farmerPersonalDetails.getUploadFarmerDoc().getPanImagePath();
//        //reading the original location where the image is present
       String uploadedImagesPath = "c:/uploads/";
   
       String sourceFile1 = uploadedImagesPath + farmerPersonalDetails.getUploadFarmerDoc().getAadharImagePath();
      String sourceFile2 = uploadedImagesPath + farmerPersonalDetails.getUploadFarmerDoc().getPanImagePath();
       
        try {
            FileCopyUtils.copy(new File(sourceFile1), new File(targetFile1));
            FileCopyUtils.copy(new File(sourceFile2), new File(targetFile2));
        } catch(IOException e) {
            e.printStackTrace(); //hoping for no error will occur
        }
//       
        return farmerPersonalDetails;
    }
	
	@PostMapping(value="/docsUploadForBidder/{bidderId}", consumes = {"multipart/form-data"})
	public Boolean uploadBidder(@ModelAttribute BidderDocDetails bidderDocDetails, @PathVariable("bidderId") long bidderId) {
		String imgUploadLocation1 = "c:/uploads/";
		String imgUploadLocation2 = "c:/uploads/";
		String uploadedFileName1 = bidderDocDetails.getAadharImg().getOriginalFilename();
		String uploadedFileName2 = bidderDocDetails.getPanImg().getOriginalFilename();
		String newFileName1 = bidderId + "-" + uploadedFileName1;
		String newFileName2 = bidderId + "-" + uploadedFileName2;
		
		String targetFileName1 = imgUploadLocation1 + newFileName1;
		String targetFileName2 = imgUploadLocation2 + newFileName2;
		
		try {
			FileCopyUtils.copy(bidderDocDetails.getAadharImg().getInputStream(), new FileOutputStream(targetFileName1));
			FileCopyUtils.copy(bidderDocDetails.getPanImg().getInputStream(), new FileOutputStream(targetFileName2));
		} catch(IOException e) {
			e.printStackTrace(); //hoping no error would occur
			return false;
		}
		
		bidderService.updateBidderDoc(bidderId, newFileName1, newFileName2);
		return true;
	}
	
	@GetMapping("/docsDownloadForBidder")
    public BidderPersonalDetails downloadBidder(@RequestParam("bidderId") long bidderid, HttpServletRequest request) {
		BidderPersonalDetails bidderPersonalDetails = bidderService.findBidderById(bidderid);
   
        //the problem is that the image is in some another folder outside this project
        //because of this, on the client we will not be able to access it by default
        //we need to write the code to copy the image from d:/uploads folder temporarily into this project of ours
   
        //reading the project's deployed location
        String projPath = request.getServletContext().getRealPath("/");
        System.out.println(projPath);
        String tempDownloadPath = projPath + "downloads/";
//        creating this downloads folder in case if it doesn't exist
        File f = new File(tempDownloadPath);
        if(!f.exists())
            f.mkdir();
        System.out.println(tempDownloadPath);
        //the target location where we will save the profile pic of the customer
        String targetFile1 = tempDownloadPath + bidderPersonalDetails.getUploadBidderDoc().getAadharImagePath();
       String targetFile2 = tempDownloadPath + bidderPersonalDetails.getUploadBidderDoc().getPanImagePath();
//        //reading the original location where the image is present
       String uploadedImagesPath = "c:/uploads/";
   
       String sourceFile1 = uploadedImagesPath + bidderPersonalDetails.getUploadBidderDoc().getAadharImagePath();
      String sourceFile2 = uploadedImagesPath + bidderPersonalDetails.getUploadBidderDoc().getPanImagePath();
       
        try {
            FileCopyUtils.copy(new File(sourceFile1), new File(targetFile1));
            FileCopyUtils.copy(new File(sourceFile2), new File(targetFile2));
        } catch(IOException e) {
            e.printStackTrace(); //hoping for no error will occur
        }
//       
        return bidderPersonalDetails;
    }

	
	@GetMapping("/approveABidder/{bidderId}")
	public boolean approveABidder(@PathVariable("bidderId") long bidderId) {
		return adminService.approveABidder(bidderId);
	}
	
	@GetMapping("/rejectABidder/{bidderId}")
	public boolean rejectABidder(@PathVariable("bidderId") long bidderId) {
		return adminService.rejectABidder(bidderId);
	}
	
	@PostMapping("/loginAdmin")
	public boolean loginAdmin(@RequestBody adminLogin adminLogin1) {
		return adminService.loginAdmin(adminLogin1.getAdminId(), adminLogin1.getAdminPassword());
	}
	
	@PostMapping("/addAAdmin")
	public long addAAdmin() {
		return adminService.addAAdmin();
	}
	
	@PostMapping("/loginFarmer")
	public farmerLoginDetails loginFarmer(@RequestBody farmerLogin farmerLogin1) {
		return farmerService.loginFarmer(farmerLogin1.getFarmerId(), farmerLogin1.getFarmerPassword());
	}
	
	@PostMapping("/loginBidder")
	public BidderLoginDetails loginBidder(@RequestBody bidderLogin bidderLogin1) {
		return bidderService.loginBidder(bidderLogin1.getBidderId(), bidderLogin1.getBidderPassword());
	}
	
	@RequestMapping(value="/addAFarmer",method = RequestMethod.POST)
	public long addAFarmer(@RequestBody FarmerPersonalDetails farmerPersonalDetails) {
		return farmerService.addAFarmer(farmerPersonalDetails);
	}
	
	@RequestMapping(value="/addABidder",method = RequestMethod.POST)
	public long addABidder(@RequestBody BidderPersonalDetails bidderPersonalDetails) {
		return bidderService.addOrUpdateBidder(bidderPersonalDetails);
	}
	
	@RequestMapping(value="/addACrop",method = RequestMethod.POST)
	public long addACrop(@RequestBody CropDetails cropDetails) {
		return adminService.addACropType(cropDetails);
	}
	
	@RequestMapping(value="/removeACrop",method = RequestMethod.POST)
	public boolean removeACrop(@RequestBody CropDetails cropDetails) {
		return adminService.removeACropType(cropDetails);
	}
	
	@RequestMapping(value="/placeSellRequest",method = RequestMethod.POST)
	public long addAbidder(@RequestBody sellRequestDetails sellRequestDetails) {
		return farmerService.sellCropRequest(sellRequestDetails.getFarmerId(), sellRequestDetails.getCropId() , sellRequestDetails.getQuantity());
	}
	
	@RequestMapping(value="/applyBidRequest",method = RequestMethod.POST)
	public long applyForBid(@RequestBody BidRequestDetails bidRequestDetails) {
		return bidderService.applyForBid(bidRequestDetails.getSellRequestId(), bidRequestDetails.getBidderId(), bidRequestDetails.getBidAmount());
	}
	
	@GetMapping("/approveASellRequest/{sellRequestId}")
	public boolean approveASellRequest(@PathVariable("sellRequestId") long sellRequestId) {
		return adminService.approveSellRequest(sellRequestId);
	}
	
	@GetMapping("/rejectASellRequest/{sellRequestId}")
	public boolean rejectASellRequest(@PathVariable("sellRequestId") long sellRequestId) {
		return adminService.rejectSellRequest(sellRequestId);
	}
	
	@RequestMapping(value="/updateAFarmer",method = RequestMethod.POST)
	public long updateAFarmer(@RequestBody FarmerPersonalDetails farmerPersonalDetails) {
		return farmerService.updateAFarmer(farmerPersonalDetails);
	}
	
	@GetMapping("/approveFinalBidRequest/{bidRequestId}")
	public boolean approveFinalBidRequest(@PathVariable("bidRequestId") long bidRequestId) {
		return adminService.approveFinalBidRequest(bidRequestId);
	}
	
	@RequestMapping(value="/addAnInsurance",method = RequestMethod.POST)
	public long addAnInsurance(@RequestBody InsuranceDetails insuranceDetails) {
		return insuranceService.addAnInsurance(insuranceDetails);
	}
	
	@RequestMapping(value="/calulateInsurance",method = RequestMethod.POST)
	public InsuranceApplied calculateInsurance(@RequestBody com.lti.dto.DetailInsurance insuranceDetails) {
		InsuranceApplied insuranceApplied = new InsuranceApplied();
		insuranceApplied.setArea(insuranceDetails.getNoOfHectares());
		double sharePremium;
		double premiumAmount;
		LocalDate localDate = LocalDate.now();
		insuranceApplied.setFarmerPersonalDetails(farmerService.findFarmerById(insuranceDetails.getFarmerId()));
		insuranceApplied.setInsuranceDetails(insuranceService.findInsuranceByCrop(insuranceDetails.getCropId()));
		try {
			System.out.println(insuranceDetails.getCropId());
			System.out.println(insuranceService.findInsuranceByCrop(insuranceDetails.getCropId()).getAmountPerHectare());
			System.out.println(insuranceApplied.getArea());
			insuranceApplied.setSumInsured((insuranceApplied.getInsuranceDetails().getAmountPerHectare())*insuranceApplied.getArea());
			insuranceApplied.setValidFrom(localDate);
			insuranceApplied.setValidTill(localDate.plusYears(1));
			if(insuranceService.findCropByCropId(insuranceDetails.getCropId()).getCropType()=="Kharif") {
				sharePremium = 2;
				premiumAmount = (insuranceApplied.getSumInsured() * sharePremium)/100;
			}
			else {
				sharePremium = 1.5;
				premiumAmount = (insuranceApplied.getSumInsured() * sharePremium)/100;
			}
			
			insuranceApplied.setSharePremium(sharePremium);
			insuranceApplied.setPremiumAmount(premiumAmount);
			insuranceApplied.setStatus("applied");
			return insuranceApplied;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	@RequestMapping(value="/applyForInsurance",method = RequestMethod.POST)
	public long applyForInsurance(@RequestBody InsuranceApplied insuranceApplied) {
		return insuranceService.applyForInsurance(insuranceApplied);
	}
	
	@RequestMapping(value="/viewAllUnapprovedClaimRequests")
	public List<ClaimRequest> viewAllUnapprovedClaimRequests() {
		List<ClaimRequest> claimRequests = insuranceService.viewAllUnapprovedClaimRequests();
		for(ClaimRequest claimRequest : claimRequests) {
			System.out.println(claimRequest.getId());
		}
		return claimRequests;
	}
	
	@RequestMapping(value="/applyForClaim",method = RequestMethod.POST)
	public long applyForClaim(@RequestBody claimDetails claimDetails) {
		ClaimRequest claimRequest = new ClaimRequest();
		claimRequest.setClaimStatus("applied");
		claimRequest.setInsuranceApplied(insuranceService.findAnInsurance(claimDetails.getPolicyNo()));
		claimRequest.setCauseForLoss(claimDetails.getCauseForLoss());
		return insuranceService.claimAnInsurance(claimRequest);
	}
	
	@RequestMapping(value="/viewInsuranceHistory/{farmerId}")
	public List<InsuranceApplied> viewInsuranceHistory(@PathVariable("farmerId") long farmerId) {
		return insuranceService.viewInsuranceHistory(farmerId);
	}
	
	@RequestMapping(value="/approveClaimRequest/{claimRequestId}")
	public boolean approveClaimRequest(@PathVariable("claimRequestId") long claimRequestId) {
		return insuranceService.approveAClaimRequest(claimRequestId);
	}
	
	@RequestMapping(value="/rejectClaimRequest/{claimRequestId}")
	public boolean rejectClaimRequest(@PathVariable("claimRequestId") long claimRequestId) {
		return insuranceService.rejectAClaimRequest(claimRequestId);
	}
	
	@RequestMapping(value="/findFarmer/{farmerId}")
	public FarmerPersonalDetails findFarmerById(@PathVariable("farmerId") long farmerId) {
		return farmerService.findFarmerById(farmerId);
	}
	
	@RequestMapping(value="/findBidder/{bidderId}")
	public BidderPersonalDetails findBidderById(@PathVariable("bidderId") long bidderId) {
		return bidderService.findBidderById(bidderId);
	}
	
	@GetMapping("/forgotPassword/{farmerId}")
    public boolean forgotPassword(@PathVariable("farmerId") long farmerId) {
        return farmerService.forgotPassword(farmerId);
    }
	
	@GetMapping("/forgotPasswordForBidder/{bidderId}")
    public boolean forgotPasswordForBidder(@PathVariable("bidderId") long bidderId) {
        return bidderService.forgotPassword(bidderId);
    }
	
	
}
