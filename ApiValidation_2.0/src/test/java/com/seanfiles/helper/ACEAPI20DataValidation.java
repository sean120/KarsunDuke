package com.seanfiles.helper;


import static com.seanfiles.elements.ACEElements.*;
import static com.seanfiles.services.BaseData.*;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.seanfiles.db.ACEAPI20DB;
import com.seanfiles.db.ACEAPI20DBBase;
import com.seanfiles.db.GFSDB;
import com.seanfiles.elements.ACEAPI20Elements;
import com.seanfiles.services.ACEAPI20AARequestData;
import com.seanfiles.services.ACEAPI20AAResponseData;
import com.seanfiles.services.ACEAPI20DerivedData;
import com.seanfiles.services.ACEAPI20HVERequestData;
import com.seanfiles.services.ACEAPI20HVEResponseData;
import com.seanfiles.services.ACEAPI20RequestData;
import com.seanfiles.services.ACEAPI20ResponseData;
import com.seanfiles.services.GFSGETResponseData;
import com.seanfiles.services.GFSPOSTRequestData;

public class ACEAPI20DataValidation {
	
	public static void verifyData(String src, String tgt) {
		if(src.contains("GFS")) {
			if(src.contains("HVE")) {
				//	verify "GFS GET response HVE" data is copied into "grandfathered HVE (HVE container-1)" data
				assertTrue("No GFS GET response HVE data", HVEDataGrandfathered());
				compareAARequestAndGETResponse("HVE1");
			}
			else {
				//	verify "GFS GET response PV" data is copied into "original PV (PV container-1)" data
				assertTrue("No GFS GET response PV data", PVDataGrandfathered());
				compareAARequestAndGETResponse("PV1");
			}
		}
		else {
			if(src.contains("HVE")) {
				//	verify "current HVE" data is copied into "grandfathered HVE (HVE container-1)" data
				compareAARequestContainer1AndContainer2("HVE1");
			}
			else {
				//	verify "current PV" data is copied into "original PV (PV container-1)" data
				compareAARequestContainer1AndContainer2("PV1");
			}
		}		
	}

	private static boolean PVDataGrandfathered() {
		GFSGETResponseData currentGFSGETResponse=GFSGETResponseData.getCurrentGETResponse();
		if(currentGFSGETResponse == null) {
			try {
				//ACEAPI20DB.getCorrespondingDoc(GFS_GET_Response);
				GFSDB.getCorrespondingACEAPI20GFSDocs(GFSDB.GFSGetResponse);
				currentGFSGETResponse=GFSGETResponseData.getCurrentGETResponse();
			} catch (Exception e) {
				return false;
			}
			if(currentGFSGETResponse == null) {
				TestScenario.writeToScenario("ERROR: GFS GET Response is null");
				return false;
			}
		}
		return currentGFSGETResponse.hasPVData();
	}

	private static boolean HVEDataGrandfathered() {
		GFSGETResponseData currentGFSGETResponse=GFSGETResponseData.getCurrentGETResponse();
		if(currentGFSGETResponse == null) {
			try {
				//ACEAPI20DB.getCorrespondingDoc(GFS_GET_Response);
				GFSDB.getCorrespondingACEAPI20GFSDocs(GFSDB.GFSGetResponse);
				currentGFSGETResponse=GFSGETResponseData.getCurrentGETResponse();
			} catch (Exception e) {
				return false;
			}
			if(currentGFSGETResponse == null) {
				TestScenario.writeToScenario("ERROR: GFS GET Response is null");
				return false;
			}
		}
		return currentGFSGETResponse.hasACEData();
	}

	public static void verifyDBDoc(String type) {
		switch(type) {
		case(ACEAPI20_Request):
			verifyACEAPI20RequestWithDatabase();			
			break;
		case(ACEAPI20_Response):
			verifyACEAPI20ResponseWithDatabase();			
			break;
		case(HVE_Request):
			compareHVERequestToACEAPIRequest();
			compareHVERequestToACEAPIDerivedData();	
			break;
		case(HVE_Response):
			compareHVEResponseToHVERequest();
			break;
		case AA_Request:
			verifyServiceDataGroup(AA_Request, "HVE2");
			verifyServiceDataGroup(AA_Request, "HVE1");
			verifyServiceDataGroup(AA_Request, "PV2");
			verifyServiceDataGroup(AA_Request, "PV1");
			verifyServiceDataGroup(AA_Request, "address");
			verifyServiceDataGroup(AA_Request, "loan");
			verifyServiceDataGroup(AA_Request, "derived");
			verifyServiceDataGroup(AA_Request, "sellerPV");
			break;
		}		
	}

	public static void verifyDataElements(String src, String tgt) {
		if(src.equalsIgnoreCase(ACEAPI20_Request) && tgt.equalsIgnoreCase(AA_Request)) {
			verifyServiceDataGroup(AA_Request, "address");
			verifyServiceDataGroup(AA_Request, "loan");
			verifyServiceDataGroup(AA_Request, "derived");
			verifyServiceDataGroup(AA_Request, "PV2");
			verifyServiceDataGroup(AA_Request, "sellerPV");
		}		
	}

	public static void verifyServiceDataGroup(String tgt, String group) {
		switch(tgt) {
		case AA_Request:
			if(group.equalsIgnoreCase("HVE2")) {
				compareAARequestToHVEResponse();
			}
			else if(group.equalsIgnoreCase("sellerPV")) {
				compareAARequestToACEAPIRequest(group);
			}
			else if(group.equalsIgnoreCase("PV2")) {
				compareAARequestToACEAPIRequest(group);
			}
			else if(group.equalsIgnoreCase("PV1")) {
				checkAARequestOriginalPV();
			}
			else if(group.equalsIgnoreCase("HVE1")) {
				checkAARequestHVE1();
			}
			else if(group.equalsIgnoreCase("address")) {
				compareAARequestToACEAPIRequest(group);
			}
			else if(group.equalsIgnoreCase("loan")) {
				compareAARequestToACEAPIRequest(group);
			}
			else if(group.equalsIgnoreCase("derived")) {
				compareAARequestToACEAPIDerivedData();
			}
			break;
		}
	}

	private static void checkAARequestHVE1() {
		ACEAPI20RequestData currentRequest=ACEAPI20RequestData.getCurrentRequest();
		if(currentRequest.getGrandfatheringIndicator()) {
			GFSGETResponseData currentGFSGETResponse=GFSGETResponseData.getCurrentGETResponse();
			if(currentGFSGETResponse == null) {
				try {
					//ACEAPI20DB.getCorrespondingDoc(GFS_GET_Response);
					GFSDB.getCorrespondingACEAPI20GFSDocs(GFSDB.GFSGetResponse);
					currentGFSGETResponse=GFSGETResponseData.getCurrentGETResponse();
				} catch (Exception e) {
					return;
				}
				if(currentGFSGETResponse == null) {
					TestScenario.writeToScenario("ERROR: GFS GET Response is null");
					return;
				}
			}
			boolean GFHVEDataAvailable=currentGFSGETResponse.hasACEData();
			if(GFHVEDataAvailable == false) {
				TestScenario.writeToScenario("No grandfathered HVE/FDR data in GFS GET Response");
				// verify AA request HVE1 and HVE2 are same
				compareAARequestContainer1AndContainer2("HVE1");
			}
			else {
				TestScenario.writeToScenario("Received Grandfathered HVE/FDR data in GFS GET Response");
				// verify GFS GET response HVE and AA request HVE1 are same
				compareAARequestAndGETResponse("HVE1");
			}
		}
		else {
			// verify AA request HVE1 and HVE2 are same
			compareAARequestContainer1AndContainer2("HVE1");
		}
	}

	private static void checkAARequestOriginalPV() {
		ACEAPI20RequestData currentRequest=ACEAPI20RequestData.getCurrentRequest();
		if(currentRequest.getGrandfatheringIndicator()) {
			GFSGETResponseData currentGFSGETResponse=GFSGETResponseData.getCurrentGETResponse();
			if(currentGFSGETResponse == null) {
				try {
					//ACEAPI20DB.getCorrespondingDoc(GFS_GET_Response);
					GFSDB.getCorrespondingACEAPI20GFSDocs(GFSDB.GFSGetResponse);
					currentGFSGETResponse=GFSGETResponseData.getCurrentGETResponse();
				} catch (Exception e) {
					return;
				}
				if(currentGFSGETResponse == null) {
					TestScenario.writeToScenario("ERROR: GFS GET Response is null");
					return;
				}
			}
			boolean GFPVDataAvailable=currentGFSGETResponse.hasPVData();
			if(GFPVDataAvailable == false ) {
				TestScenario.writeToScenario("No grandfathered PV data in GFS GET Response");
				// verify AA request current and original PV are same
				compareAARequestContainer1AndContainer2("PV1");
			}
			else {
				TestScenario.writeToScenario("Received Grandfathered PV data in GFS GET Response");
				// verify GFS GET response PV and AA request original PV are same
				compareAARequestAndGETResponse("PV1");
			}
		}
		else {
			// verify AA request current and original PV are same
			compareAARequestContainer1AndContainer2("PV1");
		}
	}

	private static void compareAARequestAndGETResponse(String group) {
		boolean compareStatus=false;
		ACEAPI20AARequestData currentAARequestData=ACEAPI20AARequestData.getCurrentRequest();
		if(currentAARequestData == null) {
			try {
				ACEAPI20DB.getCorrespondingDoc(AA_Request);
				currentAARequestData=ACEAPI20AARequestData.getCurrentRequest();
			} catch (Exception e) {
				return;
			}
			if(currentAARequestData == null) {
				TestScenario.writeToScenario("ERROR: current AA Request is null");
				return;
			}
		}
		GFSGETResponseData currentGFSGETResponse=GFSGETResponseData.getCurrentGETResponse();
		if(currentGFSGETResponse == null) {
			try {
				//ACEAPI20DB.getCorrespondingDoc(GFS_GET_Response);
				GFSDB.getCorrespondingACEAPI20GFSDocs(GFSDB.GFSGetResponse);
				currentGFSGETResponse=GFSGETResponseData.getCurrentGETResponse();
			} catch (Exception e) {
				return;
			}
			if(currentGFSGETResponse == null) {
				TestScenario.writeToScenario("ERROR: GFS GET Response is null");
				return;
			}
		}
		if(group.equalsIgnoreCase("PV1")) {
			compareStatus=compareGETResponsePV(currentAARequestData, currentGFSGETResponse);
		}
		else if(group.equalsIgnoreCase("HVE1")) {
			compareStatus=compareGETResponseHVE(currentAARequestData, currentGFSGETResponse);
		}
		TestScenario.writeToScenario(currentAARequestData.getComparisonLog());
		currentAARequestData.resetComparisonLog();
		assertTrue("AA Request Verification with GFS GET Response Failed for "+group, compareStatus);
	}

	private static boolean compareGETResponseHVE(ACEAPI20AARequestData currentAARequestData,
			GFSGETResponseData currentGFSGETResponse) {
		List<String> skipElements=new ArrayList<String>();
//		skipElements.add(ACEAPI20Elements.HomeValueExplorerAssessmentDateTime);
		
		currentAARequestData.addToComparisonLog("Comparing AARequest HVE1 and GFS GET Response HVE data");
		if(! currentAARequestData.compareData(currentGFSGETResponse.getHVEData(), "GFS GET Response HVE", currentAARequestData.getHVE(), "AARequest HVE1",  skipElements)) {
			return false;
		}
		currentAARequestData.addToComparisonLog("AARequest HVE1 data matches GFS GET Response HVE data");
		return true;
	}

	private static boolean compareGETResponsePV(ACEAPI20AARequestData currentAARequestData,
			GFSGETResponseData currentGFSGETResponse) {
		List<String> skipElements=new ArrayList<String>();
//		skipElements.add(ACEAPI20Elements.HomeValueExplorerAssessmentDateTime);
		
		currentAARequestData.addToComparisonLog("Comparing AARequest Original PV and GFS GET Response PV data");
		if(! currentAARequestData.compareData(currentGFSGETResponse.getPVData(), "GFS GET Response PV", currentAARequestData.getPV1(), "AARequest Original PV",  skipElements)) {
			return false;
		}
		currentAARequestData.addToComparisonLog("AARequest Original PV data matches GFS GET Response PV data");
		return true;
	}

	private static void compareAARequestContainer1AndContainer2(String group) {
		boolean compareStatus=false;
		ACEAPI20AARequestData currentAARequestData=ACEAPI20AARequestData.getCurrentRequest();
		if(currentAARequestData == null) {
			try {
				ACEAPI20DB.getCorrespondingDoc(AA_Request);
				currentAARequestData=ACEAPI20AARequestData.getCurrentRequest();
			} catch (Exception e) {
				return;
			}
			if(currentAARequestData == null) {
				TestScenario.writeToScenario("ERROR: current AA Request is null");
				return;
			}
		}
		compareStatus=compareAARequestContainer1AndContainer2(currentAARequestData, group);
		
		TestScenario.writeToScenario(currentAARequestData.getComparisonLog());
		currentAARequestData.resetComparisonLog();
		assertTrue("AA Request Container1 And Container2 Verification failed for "+group, compareStatus);
	}
	
	private static boolean compareAARequestContainer1AndContainer2(ACEAPI20AARequestData currentAARequestData,
			String group) {
		List<String> skipElements=new ArrayList<String>();
		if(group.equalsIgnoreCase("PV1")) {
			skipElements.add(PropertyValuationType);
			
			currentAARequestData.addToComparisonLog("Comparing AARequest Current PV and Original PV");
			if(! currentAARequestData.compareData(currentAARequestData.getPV2(), "AARequest current PV", currentAARequestData.getPV1(), "AARequest original PV", skipElements)) {
				return false;
			}
			currentAARequestData.addToComparisonLog("AARequest Current PV data matches Original PV");
		}
		else if(group.equalsIgnoreCase("HVE1")) {
			skipElements.add(HomeValueExplorerOptionType);
			
			currentAARequestData.addToComparisonLog("Comparing AARequest HVE1 and HVE2");
			if(! currentAARequestData.compareData(currentAARequestData.getHVE(), "AARequest HVE1", currentAARequestData.getHVE2(), "AARequest HVE2", skipElements, "HVE2")) {
				return false;
			}
			currentAARequestData.addToComparisonLog("AARequest HVE2 data matches HVE1");
		}
		return true;
	}

	private static void compareAARequestToACEAPIRequest(String group) {
		boolean compareStatus=false;
		ACEAPI20AARequestData currentAARequestData=ACEAPI20AARequestData.getCurrentRequest();
		if(currentAARequestData == null) {
			TestScenario.writeToScenario("ERROR: current AA Request is null");
			return;
		}
		ACEAPI20RequestData currentACEAPIRequestData=ACEAPI20RequestData.getCurrentRequest();
		if(currentACEAPIRequestData == null) {
			TestScenario.writeToScenario("ERROR: current ACEAPI Request is null");
			return;
		}
		if(group.equalsIgnoreCase("address")) {
			compareStatus=compareAddress(currentAARequestData, currentACEAPIRequestData);
		}
		else if(group.equalsIgnoreCase("loan")) {
			compareStatus=compareLoanData(currentAARequestData, currentACEAPIRequestData);
		}
		else if(group.equalsIgnoreCase("PV2")) {
			compareStatus=compareCurrentPVData(currentAARequestData, currentACEAPIRequestData);
		}
		else if(group.equalsIgnoreCase("sellerPV")) {
			if(currentAARequestData.hasSellerPV()) {
				compareStatus=compareSellerPVData(currentAARequestData, currentACEAPIRequestData);
			}
			else {
				TestScenario.writeToScenario("No seller PV data in AA Request");
				return;
			}
		}
		TestScenario.writeToScenario(currentAARequestData.getComparisonLog());
		currentAARequestData.resetComparisonLog();
		assertTrue("AA Request Verification with ACEAPIRequest Failed for "+group, compareStatus);
	}
	
	private static boolean compareCurrentPVData(ACEAPI20AARequestData currentAARequestData, ACEAPI20RequestData currentACEAPIRequestData) {
		List<String> skipElements=new ArrayList<String>();
//		skipElements.add(ACEAPI20Elements.HomeValueExplorerAssessmentDateTime);
		
		currentAARequestData.addToComparisonLog("Comparing AARequest Current PV and ACEAPIRequest...");
		if(! currentAARequestData.compareData(currentAARequestData.getPV2(), "AARequest Current PV", currentACEAPIRequestData.getRequestMap(), "ACEAPIRequest", skipElements)) {
			return false;
		}
		currentAARequestData.addToComparisonLog("AARequest Current PV data matches ACEAPIRequest");
		return true;
	}
	
	private static boolean compareSellerPVData(ACEAPI20AARequestData currentAARequestData, ACEAPI20RequestData currentACEAPIRequestData) {
		List<String> skipElements=new ArrayList<String>();
		
		currentAARequestData.addToComparisonLog("Comparing AARequest Seller PV and ACEAPIRequest...");
		if(! currentAARequestData.compareData(currentAARequestData.getSellerPV(), "AARequest SellerPV", currentACEAPIRequestData.getRequestMap(), "ACEAPIRequest", skipElements)) {
			return false;
		}
		currentAARequestData.addToComparisonLog("AARequest Seller PV data matches ACEAPIRequest");
		return true;
	}
	
	private static boolean compareAddress(ACEAPI20AARequestData currentAARequestData, ACEAPI20RequestData currentACEAPIRequestData) {
		List<String> skipElements=new ArrayList<String>();
//		skipElements.add(ACEAPI20Elements.HomeValueExplorerAssessmentDateTime);
		
		currentAARequestData.addToComparisonLog("Comparing AARequest Address and ACEAPIRequest...");
		if(! currentAARequestData.compareData(currentAARequestData.getAddress(), "AARequest Address", currentACEAPIRequestData.getRequestMap(), "ACEAPIRequest", skipElements)) {
			return false;
		}
		currentAARequestData.addToComparisonLog("AARequest Address data matches ACEAPIRequest");
		return true;
	}
	
	private static boolean compareLoanData(ACEAPI20AARequestData currentAARequestData, ACEAPI20RequestData currentACEAPIRequestData) {
		List<String> skipElements=new ArrayList<String>();
		skipElements.add(PropertyCategoryType);
		
		currentAARequestData.addToComparisonLog("Comparing AARequest Loan Data and ACEAPIRequest...");
		if(! currentAARequestData.compareData(currentAARequestData.getLoanData(), "AARequest Loan Data", currentACEAPIRequestData.getRequestMap(), "ACEAPIRequest", skipElements)) {
			return false;
		}
		
		if(currentAARequestData.hasSellerPV()) {
			
		}
		
		currentAARequestData.addToComparisonLog("AARequest Loan data matches ACEAPIRequest");
		return true;
	}
	
	private static void compareAARequestHVE1ToHVEResponse() {
		boolean compareStatus=false;
		ACEAPI20AARequestData currentAARequestData=ACEAPI20AARequestData.getCurrentRequest();
		if(currentAARequestData == null) {
			TestScenario.writeToScenario("ERROR: current AA Request is null");
			return;
		}
		ACEAPI20HVEResponseData currentHVEResponse=ACEAPI20HVEResponseData.getCurrentResponse();
		if(currentHVEResponse == null) {
			try {
				ACEAPI20DB.getCorrespondingDoc(HVE_Response);
				currentHVEResponse=ACEAPI20HVEResponseData.getCurrentResponse();
			} catch (Exception e) {
				return;
			}
			if(currentHVEResponse == null) {
				TestScenario.writeToScenario("ERROR: HVE Response is null");
				return;
			}
		}
		if(! currentHVEResponse.isHVEDataAvailable()) {
			TestScenario.writeToScenario("ERROR: HVE Data Not Available");
			return;
		}
		
		compareStatus=compareAAHVE1(currentAARequestData, currentHVEResponse);
		TestScenario.writeToScenario(currentAARequestData.getComparisonLog());
		currentAARequestData.resetComparisonLog();
		assertTrue("AA Request Verification with HVEResponse Failed", compareStatus);
	}

	private static boolean compareAAHVE1(ACEAPI20AARequestData currentAARequestData, ACEAPI20HVEResponseData currentHVEResponse) {
		List<String> skipElements=new ArrayList<String>();
		skipElements.add(ACEAPI20Elements.HomeValueExplorerAssessmentDateTime);
		skipElements.add(ACEAPI20Elements.PropertyValuationEffectiveDateTime);
		skipElements.add(ACEAPI20Elements.HomeValueExplorerOptionType);
		
		currentAARequestData.addToComparisonLog("Comparing AARequest HVE1 and HVEResponse...");
		if(! currentAARequestData.compareData(currentAARequestData.getHVE(), "AARequest HVE1", currentHVEResponse.getHVE(), "HVEResponse HVE", skipElements)) {
			return false;
		}
		currentAARequestData.addToComparisonLog("AARequest HVE1 data matches HVEResponse");
		return true;
	}
	
	private static void compareAARequestToHVEResponse() {
		boolean compareStatus=false;
		ACEAPI20AARequestData currentAARequestData=ACEAPI20AARequestData.getCurrentRequest();
		if(currentAARequestData == null) {
			try {
				ACEAPI20DB.getCorrespondingDoc(AA_Request);
				currentAARequestData=ACEAPI20AARequestData.getCurrentRequest();
			} catch (Exception e) {
				return;
			}
			if(currentAARequestData == null) {
				TestScenario.writeToScenario("ERROR: current AA Request is null");
				return;
			}
		}
		ACEAPI20HVEResponseData currentHVEResponse=ACEAPI20HVEResponseData.getCurrentResponse();
		if(currentHVEResponse == null) {
			try {
				ACEAPI20DB.getCorrespondingDoc(HVE_Response);
				currentHVEResponse=ACEAPI20HVEResponseData.getCurrentResponse();
			} catch (Exception e) {
				return;
			}
			if(currentHVEResponse == null) {
				TestScenario.writeToScenario("ERROR: HVE Response is null");
				return;
			}
		}
		if(! currentHVEResponse.isHVEDataAvailable()) {
			TestScenario.writeToScenario("ERROR: HVE Data Not Available");
			return;
		}
		
		compareStatus=compare(currentAARequestData, currentHVEResponse);
		TestScenario.writeToScenario(currentAARequestData.getComparisonLog());
		currentAARequestData.resetComparisonLog();
		assertTrue("AA Request Verification with HVEResponse Failed", compareStatus);
	}

	private static void verifyACEAPI20RequestWithDatabase() {
		boolean compareStatus=false;
		ACEAPI20RequestData currentACEAPIRequestData=ACEAPI20RequestData.getCurrentRequest();
		if(currentACEAPIRequestData == null) {
			TestScenario.writeToScenario("ERROR: current ACEAPI Request is null");
			return;
		}
		ACEAPI20DBBase dbACEAPIRequestData=ACEAPI20DB.getCurrentACEAPIDBDocuments().getACEAPIRequest();
		if(dbACEAPIRequestData == null) {
			TestScenario.writeToScenario("ERROR: DB doc for ACEAPI Request is null");
			return;
		}
		compareStatus=compare(currentACEAPIRequestData, dbACEAPIRequestData);
		TestScenario.writeToScenario(currentACEAPIRequestData.getComparisonLog());
		currentACEAPIRequestData.resetComparisonLog();
		assertTrue("ACEAPIRequest DB Document Verification Failed", compareStatus);
	}	
	
	private static void verifyACEAPI20ResponseWithDatabase() {
		boolean compareStatus=false;
		ACEAPI20ResponseData currentACEAPIResponseData=ACEAPI20ResponseData.getCurrentResponse();
		if(currentACEAPIResponseData == null) {
			TestScenario.writeToScenario("ERROR: current ACEAPI Response is null");
			return;
		}
		ACEAPI20DBBase dbACEAPIResponseData=ACEAPI20DB.getCurrentACEAPIDBDocuments().getACEAPIResponse();
		if(dbACEAPIResponseData == null) {
			TestScenario.writeToScenario("ERROR: DB doc for ACEAPI Response is null");
			return;
		}
		compareStatus=compare(currentACEAPIResponseData, dbACEAPIResponseData);
		TestScenario.writeToScenario(currentACEAPIResponseData.getComparisonLog());
		currentACEAPIResponseData.resetComparisonLog();
		assertTrue("ACEAPIResponse DB Document Verification Failed", compareStatus);
	}	
	
	private static void compareHVERequestToACEAPIDerivedData() {
		boolean compareStatus=false;
		ACEAPI20DerivedData derivedData=ACEAPI20DerivedData.getDerivedData();
		if(derivedData == null) {
			TestScenario.writeToScenario("ERROR: derivedData is null");
			return;
		}
		ACEAPI20HVERequestData currentHVERequest=ACEAPI20HVERequestData.getCurrentRequest();
		if(currentHVERequest == null) {
			TestScenario.writeToScenario("ERROR: HVE Request is null");
			return;
		}
		compareStatus=compare(currentHVERequest, derivedData);
		TestScenario.writeToScenario(currentHVERequest.getComparisonLog());
		currentHVERequest.resetComparisonLog();
		assertTrue("HVE Request Verification Failed", compareStatus);
	}	
	
	private static void compareAARequestToACEAPIDerivedData() {
		boolean compareStatus=false;
		ACEAPI20DerivedData derivedData=ACEAPI20DerivedData.getDerivedData();
		if(derivedData == null) {
			TestScenario.writeToScenario("ERROR: derivedData is null");
			return;
		}
		ACEAPI20AARequestData currentAARequestData=ACEAPI20AARequestData.getCurrentRequest();
		if(currentAARequestData == null) {
			TestScenario.writeToScenario("ERROR: current AA Request is null");
			return;
		}
		compareStatus=compare(currentAARequestData, derivedData);
		TestScenario.writeToScenario(currentAARequestData.getComparisonLog());
		currentAARequestData.resetComparisonLog();
		assertTrue("AA Request Verification Failed", compareStatus);
	}	
	
	private static boolean compare(ACEAPI20AARequestData currentAARequestData, ACEAPI20DerivedData derivedData) {
		List<String> skipElements=new ArrayList<String>();
		
		// Not working for ULAD and LQA
//		skipElements.add(ACEAPI20Elements.EDS_SubscriberIdentifier);
//		skipElements.add(ACEAPI20Elements.EDS_ServiceName);
//		skipElements.add(ACEAPI20Elements.EDS_ServiceRequestIdentifier);
		
		currentAARequestData.addToComparisonLog("Comparing AARequest and DerivedData ...");
		if(! currentAARequestData.compareData(derivedData.getEDSRequestData(), "DerivedData", currentAARequestData.getDerivedData(), "AARequest", skipElements)) {
			return false;
		}
		currentAARequestData.addToComparisonLog("AARequest matches DerivedData ");
		return true;
	}

	private static boolean compare(ACEAPI20HVERequestData currentHVERequest, ACEAPI20DerivedData derivedData) {
		currentHVERequest.addToComparisonLog("Comparing HVERequest and DerivedData ...");
		if(! currentHVERequest.compareData(derivedData.getHVERequestData(), "DerivedData", currentHVERequest.getRequestMap(), "HVERequest")) {
			return false;
		}
		currentHVERequest.addToComparisonLog("HVERequest matches DerivedData ");
		return true;
	}

	private static void compareHVEResponseToHVERequest() {
		boolean compareStatus=false;
		ACEAPI20HVERequestData currentHVERequest=ACEAPI20HVERequestData.getCurrentRequest();
		if(currentHVERequest == null) {
			TestScenario.writeToScenario("ERROR: HVE Request is null");
			return;
		}
		
		ACEAPI20HVEResponseData currentHVEResponse=ACEAPI20HVEResponseData.getCurrentResponse();
		if(currentHVEResponse == null) {
			TestScenario.writeToScenario("ERROR: HVE Response is null");
			return;
		}
		
		if(! currentHVEResponse.isHVEDataAvailable()) {
			TestScenario.writeToScenario("ERROR: HVE Data Not Available");
			return;
		}
		
		compareStatus=compare(currentHVEResponse, currentHVERequest);
		TestScenario.writeToScenario(currentHVEResponse.getComparisonLog());
		currentHVEResponse.resetComparisonLog();
		assertTrue("HVE Response Verification Failed", compareStatus);
	}	
	
	private static boolean compare(ACEAPI20HVEResponseData currentHVEResponse,
			ACEAPI20HVERequestData currentHVERequest) {
		currentHVEResponse.addToComparisonLog("Comparing HVEResponse and HVERequest ...");
		if(! currentHVEResponse.compareData(currentHVEResponse.getAddress(), "HVEResponse Address", currentHVERequest.getRequestMap(), "HVERequest Address")) {
			return false;
		}
		if(! currentHVEResponse.compareData(currentHVEResponse.getRequestElements(), "HVEResponse RequestData", currentHVERequest.getRequestMap(), "HVERequest RequestData")) {
			return false;
		}
		currentHVEResponse.addToComparisonLog("HVEResponse matches HVERequest ");
		return true;
	}

	private static void compareHVERequestToACEAPIRequest() {
		boolean compareStatus=false;
		ACEAPI20RequestData currentACEAPIRequestData=ACEAPI20RequestData.getCurrentRequest();
		if(currentACEAPIRequestData == null) {
			TestScenario.writeToScenario("ERROR: current ACEAPI Request is null");
			return;
		}
		ACEAPI20HVERequestData currentHVERequest=ACEAPI20HVERequestData.getCurrentRequest();
		if(currentHVERequest == null) {
			TestScenario.writeToScenario("ERROR: HVE Request is null");
			return;
		}
		compareStatus=compare(currentHVERequest, currentACEAPIRequestData);
		TestScenario.writeToScenario(currentHVERequest.getComparisonLog());
		currentHVERequest.resetComparisonLog();
		assertTrue("HVE Request Verification Failed", compareStatus);
	}	
	
	private static boolean compare(ACEAPI20RequestData currentACEAPIRequestData, ACEAPI20DBBase dbACEAPIRequestData) {
		currentACEAPIRequestData.addToComparisonLog("Comparing ACEAPI2.0Request with DB document");
		if(! currentACEAPIRequestData.compareToDB(currentACEAPIRequestData.getRequestMap(), "ACEAPI2.0Request", dbACEAPIRequestData)) {
			return false;
		}
		currentACEAPIRequestData.addToComparisonLog("ACEAPI2.0Request matches with DB document");
		return true;
	}
	
	private static boolean compare(ACEAPI20HVERequestData currentHVERequest, ACEAPI20RequestData currentACEAPIRequestData) {
		currentHVERequest.addToComparisonLog("Comparing HVERequest and ACEAPIRequest ...");
		if(! currentHVERequest.compareData(currentACEAPIRequestData.getHVERequestElementsMap(), "ACEAPIRequest", currentHVERequest.getRequestMap(), "HVERequest")) {
			return false;
		}
		currentHVERequest.addToComparisonLog("HVERequest matches ACEAPIRequest ");
		return true;
	}

	private static boolean compare(ACEAPI20ResponseData currentACEAPIResponseData, ACEAPI20DBBase dbACEAPIResponseData) {
		List<String> skipElements=new ArrayList<String>();
		skipElements.add(ACEAPI20Elements.AlternateAppraisalReasonList);
		skipElements.add(ACEAPI20Elements.MessageCodeList);
		
		currentACEAPIResponseData.addToComparisonLog("Comparing ACEAPI2.0Response with DB document");
		if(! currentACEAPIResponseData.compareToDB(currentACEAPIResponseData.getResponseMap(), "ACEAPI2.0Response", dbACEAPIResponseData, skipElements)) {
			return false;
		}
		currentACEAPIResponseData.addToComparisonLog("ACEAPI2.0Response matches with DB document");
		return true;
	}
	
	private static boolean compare(ACEAPI20AARequestData currentAARequestData, ACEAPI20HVEResponseData currentHVEResponse) {
		List<String> skipElements=new ArrayList<String>();
		skipElements.add(ACEAPI20Elements.HomeValueExplorerAssessmentDateTime);
		skipElements.add(ACEAPI20Elements.PropertyValuationEffectiveDateTime);
		skipElements.add(ACEAPI20Elements.HomeValueExplorerOptionType);
		
		currentAARequestData.addToComparisonLog("Comparing AARequest and HVEResponse...");
		if(! currentAARequestData.compareData(currentAARequestData.getCurrentHVE(), "AARequest Current HVE", currentHVEResponse.getHVE(), "HVEResponse HVE", skipElements)) {
			return false;
		}
		currentAARequestData.addToComparisonLog("AARequest current HVE/FDR data matches HVEResponse");
		return true;
	}

	public static void verifyAAResponse() {
		boolean compareStatus=false;
		ACEAPI20AAResponseData AAResponse=ACEAPI20AAResponseData.getCurrentResponse();
		if(AAResponse == null) {
			TestScenario.writeToScenario("ERROR: EDS/AA Response is null");
			return;
		}
		ACEAPI20ResponseData APIResponse=ACEAPI20ResponseData.getCurrentResponse();
		if(APIResponse == null) {
			TestScenario.writeToScenario("ERROR: ACEAPI 2.0 Response is null");
			return;
		}
		compareStatus=compare(AAResponse, APIResponse);
		TestScenario.writeToScenario(AAResponse.getComparisonLog());
		AAResponse.resetComparisonLog();
		assertTrue("AA Response Verification Failed", compareStatus);
	}

	private static boolean compare(ACEAPI20AAResponseData AAResponse, ACEAPI20ResponseData APIResponse) {
		AAResponse.addToComparisonLog("Comparing EDS/AA Response with ACEAPI Response");
		if(! AAResponse.compareData(AAResponse.getAAData(), "EDS/AA Response data", APIResponse.getResponseMap(), "ACEAPI Response")) {
			return false;
		}
		if(! AAResponse.compareData(AAResponse.getMessages(), "EDS/AA Response Messages", APIResponse.getMessages(), "ACEAPI Response Messages")) {
			return false;
		}
		AAResponse.addToComparisonLog("ACEAPI Response matches EDS/AA Response");
		return true;
	}

	public static void verifyACEAPIResponse(Map<String, String> dataMap) {
		ACEAPI20ResponseData currentACEAPI20Response=ACEAPI20ResponseData.getCurrentResponse();
		if(currentACEAPI20Response == null) {
			TestScenario.writeToScenario("ACEAPI20 Response is empty or null");
			return;
		}
		boolean compareStatus=false;
		compareStatus=compareResponseData(currentACEAPI20Response, dataMap);
		TestScenario.writeToScenario(currentACEAPI20Response.getComparisonLog());
		currentACEAPI20Response.resetComparisonLog();
		assertTrue("ACEAPI20 Response Verification Failed", compareStatus);
	}

	private static boolean compareResponseData(ACEAPI20ResponseData currentACEAPI20Response,
			Map<String, String> dataMap) {
		List<String> multiValueElements=new ArrayList<String>();
		multiValueElements.add(AlternateAppraisalReasonList);
		multiValueElements.add(MessageCodeList);
		currentACEAPI20Response.setMultiValueElements(multiValueElements);
		
		currentACEAPI20Response.addToComparisonLog("Comparing ACEAPI20 Response and expected values ...");
		if(! currentACEAPI20Response.compareData(dataMap, "Expected values", currentACEAPI20Response.getResponseMap(), "ACEAPI20 Response value")) {
			return false;
		}
		currentACEAPI20Response.addToComparisonLog("ACEAPI20 Response matches expected values ");
		return true;
	}

	public static void verifyAARequestData(Map<String, String> dataMap) {
		ACEAPI20AARequestData currentAARequest=ACEAPI20AARequestData.getCurrentRequest();
		if(currentAARequest == null) {
			TestScenario.writeToScenario("ACEAPI20 EDS/AA Request is empty or null");
			return;
		}
		boolean compareStatus=false;
		compareStatus=compareAARequestData(currentAARequest, dataMap);
		TestScenario.writeToScenario(currentAARequest.getComparisonLog());
		currentAARequest.resetComparisonLog();
		assertTrue("ACEAPI20 EDS/AA Request Verification Failed", compareStatus);
	}

	private static boolean compareAARequestData(ACEAPI20AARequestData currentAARequest,
			Map<String, String> dataMap) {
		
		currentAARequest.addToComparisonLog("Comparing ACEAPI20 EDS/AA Request and expected values ...");
		if(! currentAARequest.compareData(dataMap, "Expected values", currentAARequest.getHVE2(), "ACEAPI20 EDS/AA Request value")) {
			return false;
		}
		currentAARequest.addToComparisonLog("ACEAPI20 EDS/AA Request matches expected values ");
		return true;
	}

	public static void verifyDataPresence(String expected, String type) {
		boolean status=false;
		switch(type) {
		case HVE_Response:
			ACEAPI20HVEResponseData currentHVEResponse=ACEAPI20HVEResponseData.getCurrentResponse();
			if(currentHVEResponse == null) {
				TestScenario.writeToScenario("ERROR: HVE Response is null");
				return;
			}
			
			status=currentHVEResponse.isHVEDataAvailable();
			boolean expectedStatus=false;
			switch(expected) {
			case "NoHVSData":
				expectedStatus=false;
				if(status == expectedStatus) {
					TestScenario.writeToScenario("HVE Data Not Available");
				}
				else {
					TestScenario.writeToScenario("HVE Data Available");
					assertTrue(false);
				}
				break;
			case "HVSDataAvailable":
				expectedStatus=true;
				if(status == expectedStatus) {
					TestScenario.writeToScenario("HVE Data Available");
				}
				else {
					TestScenario.writeToScenario("HVE Data Not Available");
					assertTrue(false);
				}
				break;
			}
			return;
		}
	}

	public static void verifyAARequestCommunicationFailureData(Map<String, String> dataMap) {
		ACEAPI20AARequestData currentAARequest=ACEAPI20AARequestData.getCurrentRequest();
		if(currentAARequest == null) {
			TestScenario.writeToScenario("ACEAPI20 EDS/AA Request is empty or null");
			return;
		}
		boolean compareStatus=false;
		compareStatus=compareAARequestCommunicationFailureData(currentAARequest, dataMap);
		TestScenario.writeToScenario(currentAARequest.getComparisonLog());
		currentAARequest.resetComparisonLog();
		assertTrue("ACEAPI20 EDS/AA Request Verification Failed", compareStatus);
	}

	private static boolean compareAARequestCommunicationFailureData(ACEAPI20AARequestData currentAARequest,
			Map<String, String> dataMap) {
		
		currentAARequest.addToComparisonLog("Comparing ACEAPI20 EDS/AA Request and expected values ...");
		if(! currentAARequest.compareData(dataMap, "Expected values", currentAARequest.getCommunicationFailureData(), "ACEAPI20 EDS/AA Request value")) {
			return false;
		}
		currentAARequest.addToComparisonLog("ACEAPI20 EDS/AA Request matches expected values ");
		return true;
	}

	public static void verifyServiceData(String type, Map<String, String> dataMap) {
		switch (type) {
		case HVE_Response:
			ACEAPI20HVEResponseData currentHVEResponse = ACEAPI20HVEResponseData.getCurrentResponse();
			if (currentHVEResponse == null) {
				TestScenario.writeToScenario("ERROR: HVE Response is null");
				return;
			}
			verifyHVEResponse(dataMap);
			break;
		}
	}

	private static void verifyHVEResponse(Map<String, String> dataMap) {
		ACEAPI20HVEResponseData currentHVEResponse = ACEAPI20HVEResponseData.getCurrentResponse();
		String srcName = "Expected Values";
		Map<String, String> srcMap = dataMap;
		String tgtName = "HVS Response";
		Map<String, String> tgtMap = currentHVEResponse.getHVE();

		boolean compareStatus = currentHVEResponse.compare(srcMap, srcName, tgtMap, tgtName);
		assertTrue("HVS Response Verification Failed", compareStatus);
	}

	public static void compareServiceData(String src, String tgt, List<String> elementsList) {
		switch (src) {
		case HVE_Response:
			ACEAPI20HVEResponseData currentHVEResponse = ACEAPI20HVEResponseData.getCurrentResponse();
			if (currentHVEResponse == null) {
				TestScenario.writeToScenario("ERROR: HVE Response is null");
				return;
			}
			switch (tgt) {
			case AA_Request:
				ACEAPI20AARequestData currentAARequestData = ACEAPI20AARequestData.getCurrentRequest();
				if (currentAARequestData == null) {
					TestScenario.writeToScenario("ERROR: current AA Request is null");
					return;
				}
				compareHVSResponseAARequest(elementsList);
				break;
			case GFSDB.GFSPostData:
				GFSPOSTRequestData currentGFSPOSTRequest = GFSPOSTRequestData.getCurrentRequest();
				if (currentGFSPOSTRequest == null) {
					TestScenario.writeToScenario("ERROR: GFS POST Request is null");
					assertTrue("GFSPOST Verification failed", false);
					return;
				}
				compareHVSResponseGFSPost(elementsList);
				break;
			}
		}
	}

	private static void compareHVSResponseGFSPost(List<String> elementsList) {
		ACEAPI20HVEResponseData currentHVEResponse = ACEAPI20HVEResponseData.getCurrentResponse();
		GFSPOSTRequestData currentGFSPOSTRequest = GFSPOSTRequestData.getCurrentRequest();

		String srcName = "HVS Response";
		Map<String, String> srcMap = currentHVEResponse.getHVE();
		String tgtName = "GFS POST data";
		Map<String, String> tgtMap = currentGFSPOSTRequest.getHVE2Map();

		boolean compareStatus = false;
		if (elementsList != null) {
			srcMap = currentHVEResponse.filterElements(srcMap, elementsList);
			tgtMap = currentGFSPOSTRequest.filterElements(tgtMap, elementsList, "HVE2");
			compareStatus = currentHVEResponse.compare(srcMap, srcName, tgtMap, tgtName, null, "HVE2");
		} else {
			List<String> skipElements = new ArrayList<String>();
			skipElements.add(ACEAPI20Elements.HomeValueExplorerAssessmentDateTime);
			skipElements.add(ACEAPI20Elements.PropertyValuationEffectiveDateTime);
			compareStatus = currentHVEResponse.compare(srcMap, srcName, tgtMap, tgtName, skipElements, "HVE2");
		}

		assertTrue("HVS Response - GFS POST data Verification Failed", compareStatus);

	}

	private static void compareHVSResponseAARequest(List<String> elementsList) {
		ACEAPI20HVEResponseData currentHVEResponse = ACEAPI20HVEResponseData.getCurrentResponse();
		ACEAPI20AARequestData currentAARequestData = ACEAPI20AARequestData.getCurrentRequest();

		String srcName = "HVS Response";
		Map<String, String> srcMap = currentHVEResponse.getHVE();
		String tgtName = "EDS AA Request HVE2";
		Map<String, String> tgtMap = currentAARequestData.getHVE2();

		boolean compareStatus = false;

		if (elementsList != null) {
			srcMap = currentHVEResponse.filterElements(srcMap, elementsList);
			tgtMap = currentHVEResponse.filterElements(tgtMap, elementsList, "HVE2");
			compareStatus = currentHVEResponse.compare(srcMap, srcName, tgtMap, tgtName, null, "HVE2");
		} else {
			List<String> skipElements = new ArrayList<String>();
			skipElements.add(ACEAPI20Elements.HomeValueExplorerAssessmentDateTime);
			skipElements.add(ACEAPI20Elements.PropertyValuationEffectiveDateTime);
			compareStatus = currentHVEResponse.compare(srcMap, srcName, tgtMap, tgtName, skipElements, "HVE2");
		}
		
		assertTrue("HVS Response - AA Request Verification Failed", compareStatus);
	}

	public static void compareServiceDataWithContName(String src, String tgt, String tgtContName,
			List<String> elementsList) {
		switch (src) {
		case HVE_Response:
			ACEAPI20HVEResponseData currentHVEResponse = ACEAPI20HVEResponseData.getCurrentResponse();
			if (currentHVEResponse == null) {
				TestScenario.writeToScenario("ERROR: HVE Response is null");
				return;
			}
			switch (tgt) {
			case AA_Request:
				ACEAPI20AARequestData currentAARequestData = ACEAPI20AARequestData.getCurrentRequest();
				if (currentAARequestData == null) {
					TestScenario.writeToScenario("ERROR: current AA Request is null");
					return;
				}
				compareHVSResponseAARequest(elementsList);
				break;
			}
			break;
		}
	}

	public static void compareServiceDataWithBothContName(String src, String srcContName, String tgt,
			String tgtContName, List<String> elementsList) {
		ACEAPI20AARequestData currentAARequestData=null;
		switch (src) {
		case AA_Request:
			currentAARequestData = ACEAPI20AARequestData.getCurrentRequest();
			if (currentAARequestData == null) {
				TestScenario.writeToScenario("ERROR: current AA Request is null");
				return;
			}
			switch(tgt) {
			case AA_Request:
				compareAARequestHVEContainers(elementsList);
				break;
			case GFSDB.GFSPostData:
				GFSPOSTRequestData currentGFSPOSTRequest=GFSPOSTRequestData.getCurrentRequest();
				if(currentGFSPOSTRequest == null) {
					TestScenario.writeToScenario("ERROR: GFS POST Request is null");
					assertTrue("GFSPOST Verification failed", false);
					return;
				}
				if(srcContName.contains("HVE1")) {
					compareAARequestAndGFSPOSTHVSData1(elementsList);
				}
				else if(srcContName.contains("HVE2")) {
					compareAARequestAndGFSPOSTHVSData2(elementsList);
				}
				break;
			}
			break;
		case GFSDB.GFSGetResponse:
			if(srcContName.contains("HVE")) {
				assertTrue("No GFS GET response HVE data", HVEDataGrandfathered());
				currentAARequestData = ACEAPI20AARequestData.getCurrentRequest();
				if (currentAARequestData == null) {
					TestScenario.writeToScenario("ERROR: current AA Request is null");
					return;
				}
				compareAARequestAndGFSGETResponseHVSData(elementsList);
			}
		}
	}
	
	private static void compareAARequestAndGFSPOSTHVSData1(List<String> elementsList) {
		ACEAPI20AARequestData currentAARequestData=ACEAPI20AARequestData.getCurrentRequest();
		GFSPOSTRequestData currentGFSPOSTRequest=GFSPOSTRequestData.getCurrentRequest();
		
		String srcName = "EDS AA Request HVE1";
		Map<String, String> srcMap = currentAARequestData.getHVE();
		String tgtName = "GFS POST Data HVE1";
		Map<String, String> tgtMap = currentGFSPOSTRequest.getHVEMap();

		if (elementsList != null) {
			srcMap = currentAARequestData.filterElements(srcMap, elementsList);
			tgtMap = currentAARequestData.filterElements(tgtMap, elementsList);
		}
		
		boolean compareStatus = currentAARequestData.compare(srcMap, srcName, tgtMap, tgtName);
		
		assertTrue("EDS AA Request HVE1 - GFS POST HVE1 Verification Failed", compareStatus);
	}

	private static void compareAARequestAndGFSPOSTHVSData2(List<String> elementsList) {
		ACEAPI20AARequestData currentAARequestData=ACEAPI20AARequestData.getCurrentRequest();
		GFSPOSTRequestData currentGFSPOSTRequest=GFSPOSTRequestData.getCurrentRequest();
		
		String srcName = "EDS AA Request HVE2";
		Map<String, String> srcMap = currentAARequestData.getHVE2();
		String tgtName = "GFS POST Data HVE2";
		Map<String, String> tgtMap = currentGFSPOSTRequest.getHVE2Map();

		if (elementsList != null) {
			srcMap = currentAARequestData.filterElements(srcMap, elementsList, "HVE2");
			tgtMap = currentAARequestData.filterElements(tgtMap, elementsList, "HVE2");
		}
		boolean compareStatus = currentAARequestData.compare(srcMap, srcName, tgtMap, tgtName);
		
		assertTrue("EDS AA Request HVE2 - GFS POST HVE2 Verification Failed", compareStatus);
	}

	private static void compareAARequestAndGFSGETResponseHVSData(List<String> elementsList) {
		ACEAPI20AARequestData currentAARequestData=ACEAPI20AARequestData.getCurrentRequest();
		GFSGETResponseData currentGFSGETResponse=GFSGETResponseData.getCurrentGETResponse();
		
		String srcName = "GFS GET Response HVE";
		Map<String, String> srcMap = currentGFSGETResponse.getHVEData();
		String tgtName = "EDS AA Request HVE1";
		Map<String, String> tgtMap = currentAARequestData.getHVE();

		boolean compareStatus = false;

		if (elementsList != null) {
			srcMap = currentAARequestData.filterElements(srcMap, elementsList);
			tgtMap = currentAARequestData.filterElements(tgtMap, elementsList);
			compareStatus = currentAARequestData.compare(srcMap, srcName, tgtMap, tgtName);
		} else {
			List<String> skipElements = new ArrayList<String>();
//			skipElements.add(ACEAPI20Elements.HomeValueExplorerAssessmentDateTime);
//			skipElements.add(ACEAPI20Elements.PropertyValuationEffectiveDateTime);
			compareStatus = currentAARequestData.compare(srcMap, srcName, tgtMap, tgtName, skipElements, null);
		}
		
		assertTrue("GFS GET Response HVE - EDS AA Request HVE1 Verification Failed", compareStatus);		
	}
	
	private static void compareAARequestHVEContainers(List<String> elementsList) {
		ACEAPI20AARequestData currentAARequestData = ACEAPI20AARequestData.getCurrentRequest();

		String srcName = "EDS AA Request HVE2";
		Map<String, String> srcMap = currentAARequestData.getHVE2();
		String tgtName = "EDS AA Request HVE1";
		Map<String, String> tgtMap = currentAARequestData.getHVE();

		boolean compareStatus = false;

		if (elementsList != null) {
			srcMap = currentAARequestData.filterElements(srcMap, elementsList, "HVE2");
			tgtMap = currentAARequestData.filterElements(tgtMap, elementsList);
			compareStatus = currentAARequestData.compare(tgtMap, tgtName, srcMap, srcName, null, "HVE2");
		} else {
			List<String> skipElements = new ArrayList<String>();
//			skipElements.add(ACEAPI20Elements.HomeValueExplorerAssessmentDateTime);
//			skipElements.add(ACEAPI20Elements.PropertyValuationEffectiveDateTime);
			compareStatus = currentAARequestData.compare(tgtMap, tgtName, srcMap, srcName, skipElements, "HVE2");
		}
		
		assertTrue("EDS AA Request HVE1-HVE2 Verification Failed", compareStatus);
	}

}
