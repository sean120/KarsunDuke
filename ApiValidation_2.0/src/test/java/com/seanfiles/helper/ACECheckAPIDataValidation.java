package com.seanfiles.helper;

import static com.seanfiles.elements.ACECheckAPIElements.*;
import static com.seanfiles.services.BaseData.*;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jayway.jsonpath.DocumentContext;
import com.seanfiles.db.ACECheckAPIDB;
import com.seanfiles.db.ACECheckAPIDBBase;
import com.seanfiles.db.GFSDB;
import com.seanfiles.db.GFSDBBase;
import com.seanfiles.elements.ACECheckAPIElements;
import com.seanfiles.services.ACECheckAPIAARequestData;
import com.seanfiles.services.ACECheckAPIAAResponseData;
import com.seanfiles.services.ACECheckAPIHVERequestData;
import com.seanfiles.services.ACECheckAPIHVEResponseData;
import com.seanfiles.services.ACECheckAPIRequestData;
import com.seanfiles.services.ACECheckAPIResponseData;
import com.seanfiles.services.BaseData;
import com.seanfiles.utils.JSONUtilities;

public class ACECheckAPIDataValidation {
	
	private static Logger log = Logger.getLogger(ACECheckAPIDataValidation.class);

	public static void verifyDBDoc(String type) {
		switch(type) {
		case(ACEAPI_Request):
			verifyACEAPIRequestWithDatabase();			
			break;
		case(ACEAPI_Response):
			verifyACEAPIResponseWithDatabase();			
			break;
		}		
	}

	public static void verifyServiceData(String tgt, String src) {
		switch(tgt) {
		case HVE_Request:
			compareHVERequestToACEAPIRequest();
			break;
		case AA_Request:
			compareAARequestToHVEResponse();
			break;
		case ACEAPI_Response:
			compareAAResponseToACEAPIResponse();
			break;
		}
	}

	private static void compareAAResponseToACEAPIResponse() {
		boolean compareStatus=false;
		ACECheckAPIAAResponseData currentAAResponseData=ACECheckAPIAAResponseData.getCurrentResponse();
		if(currentAAResponseData == null) {
			log.info("ERROR: current AA Response is null");
			TestScenario.writeToScenario("ERROR: current AA Response is null");
			return;
		}
		ACECheckAPIResponseData currentACEAPIResponse=ACECheckAPIResponseData.getCurrentResponse();
		if(currentACEAPIResponse == null) {
			log.info("ERROR: ACEAPI Response is null");
			TestScenario.writeToScenario("ERROR: ACEAPI Response is null");
			return;
		}
		compareStatus=compare(currentAAResponseData, currentACEAPIResponse);
		log.info(currentAAResponseData.getComparisonLog());
		TestScenario.writeToScenario(currentAAResponseData.getComparisonLog());
		currentAAResponseData.resetComparisonLog();
		assertTrue("ACEAPI Response Verification Failed", compareStatus);
	}

	private static void compareAARequestToHVEResponse() {
		boolean compareStatus=false;
		ACECheckAPIAARequestData currentAARequestData=ACECheckAPIAARequestData.getCurrentRequest();
		if(currentAARequestData == null) {
			log.info("ERROR: current AA Request is null");
			TestScenario.writeToScenario("ERROR: current AA Request is null");
			return;
		}
		ACECheckAPIHVEResponseData currentHVEResponse=ACECheckAPIHVEResponseData.getCurrentResponse();
		if(currentHVEResponse == null) {
			log.info("ERROR: HVE Response is null");
			TestScenario.writeToScenario("ERROR: HVE Response is null");
			return;
		}
		compareStatus=compare(currentAARequestData, currentHVEResponse);
		log.info(currentAARequestData.getComparisonLog());
		TestScenario.writeToScenario(currentAARequestData.getComparisonLog());
		currentAARequestData.resetComparisonLog();
		assertTrue("AA Request Verification Failed", compareStatus);
	}

	private static void verifyACEAPIRequestWithDatabase() {
		boolean compareStatus=false;
		ACECheckAPIRequestData currentACEAPIRequestData=ACECheckAPIRequestData.getCurrentRequest();
		if(currentACEAPIRequestData == null) {
			log.info("ERROR: current ACEAPI Request is null");
			TestScenario.writeToScenario("ERROR: current ACEAPI Request is null");
			return;
		}
		ACECheckAPIDBBase dbACEAPIRequestData=ACECheckAPIDB.getCurrentACEAPIDBDocuments().getACEAPIRequest();
		if(dbACEAPIRequestData == null) {
			log.info("ERROR: DB doc for ACEAPI Request is null");
			TestScenario.writeToScenario("ERROR: DB doc for ACEAPI Request is null");
			return;
		}
		compareStatus=compare(currentACEAPIRequestData, dbACEAPIRequestData);
		log.info(currentACEAPIRequestData.getComparisonLog());
		TestScenario.writeToScenario(currentACEAPIRequestData.getComparisonLog());
		currentACEAPIRequestData.resetComparisonLog();
		assertTrue("ACEAPIRequest DB Document Verification Failed", compareStatus);
	}	
	
	private static void verifyACEAPIResponseWithDatabase() {
		boolean compareStatus=false;
		ACECheckAPIResponseData currentACEAPIResponseData=ACECheckAPIResponseData.getCurrentResponse();
		if(currentACEAPIResponseData == null) {
			log.info("ERROR: current ACEAPI Response is null");
			TestScenario.writeToScenario("ERROR: current ACEAPI Response is null");
			return;
		}
		ACECheckAPIDBBase dbACEAPIResponseData=ACECheckAPIDB.getCurrentACEAPIDBDocuments().getACEAPIResponse();
		if(dbACEAPIResponseData == null) {
			log.info("ERROR: DB doc for ACEAPI Response is null");
			TestScenario.writeToScenario("ERROR: DB doc for ACEAPI Response is null");
			return;
		}
		compareStatus=compare(currentACEAPIResponseData, dbACEAPIResponseData);
		log.info(currentACEAPIResponseData.getComparisonLog());
		TestScenario.writeToScenario(currentACEAPIResponseData.getComparisonLog());
		currentACEAPIResponseData.resetComparisonLog();
		assertTrue("ACEAPIResponse DB Document Verification Failed", compareStatus);
	}

	private static void compareHVERequestToACEAPIRequest() {
		boolean compareStatus=false;
		ACECheckAPIRequestData currentACEAPIRequestData=ACECheckAPIRequestData.getCurrentRequest();
		if(currentACEAPIRequestData == null) {
			log.info("ERROR: current ACEAPI Request is null");
			TestScenario.writeToScenario("ERROR: current ACEAPI Request is null");
			return;
		}
		ACECheckAPIHVERequestData currentHVERequest=ACECheckAPIHVERequestData.getCurrentRequest();
		if(currentHVERequest == null) {
			log.info("ERROR: HVE Request is null");
			TestScenario.writeToScenario("ERROR: HVE Request is null");
			return;
		}
		compareStatus=compare(currentHVERequest, currentACEAPIRequestData);
		log.info(currentHVERequest.getComparisonLog());
		TestScenario.writeToScenario(currentHVERequest.getComparisonLog());
		currentHVERequest.resetComparisonLog();
		assertTrue("HVE Request Verification Failed", compareStatus);
	}	
	
	private static boolean compare(ACECheckAPIRequestData currentACEAPIRequestData, ACECheckAPIDBBase dbACEAPIRequestData) {
		currentACEAPIRequestData.addToComparisonLog("Comparing ACEAPIRequest with DB document");
		if(! currentACEAPIRequestData.compareToDB(currentACEAPIRequestData.getRequestMap(), "ACEAPIRequest", dbACEAPIRequestData)) {
			return false;
		}
		currentACEAPIRequestData.addToComparisonLog("ACEAPIRequest matches with DB document");
		return true;
	}
	
	private static boolean compare(ACECheckAPIHVERequestData currentHVERequest, ACECheckAPIRequestData currentACEAPIRequestData) {
		currentHVERequest.addToComparisonLog("Comparing HVERequest Address and ACEAPIRequest Address...");
		if(! currentHVERequest.compareData(currentHVERequest.getAddress(), "HVERequest Address", currentACEAPIRequestData.getRequestMap(), "ACEAPIRequest Address")) {
			return false;
		}
		currentHVERequest.addToComparisonLog("HVERequest Address matches ACEAPIRequest Address");
		return true;
	}

	private static boolean compare(ACECheckAPIResponseData currentACEAPIResponseData, ACECheckAPIDBBase dbACEAPIResponseData) {
		currentACEAPIResponseData.addToComparisonLog("Comparing ACEAPIResponse with DB document");
		if(! currentACEAPIResponseData.compareToDB(currentACEAPIResponseData.getResponseMap(), "ACEAPIResponse", dbACEAPIResponseData)) {
			return false;
		}
		currentACEAPIResponseData.addToComparisonLog("ACEAPIResponse matches with DB document");
		return true;
	}
	
	private static boolean compare(ACECheckAPIAARequestData currentAARequestData, ACECheckAPIHVEResponseData currentHVEResponse) {
		List<String> skipElements=new ArrayList<String>();
		skipElements.add(ACECheckAPIElements.HomeValueExplorerAssessmentDateTime);
		skipElements.add(ACECheckAPIElements.PropertyValuationEffectiveDateTime);
		skipElements.add(ACECheckAPIElements.HomeValueExplorerOptionType);
		
		currentAARequestData.addToComparisonLog("Comparing AARequest and HVEResponse...");
		if(! currentAARequestData.compareData(currentAARequestData.getAddress(), "AARequest Address", currentHVEResponse.getAddress(), "HVEResponse Address")) {
			return false;
		}
		if(! currentAARequestData.compareData(currentAARequestData.getHVE(), "AARequest HVE", currentHVEResponse.getHVE(), "HVEResponse HVE", skipElements)) {
			return false;
		}
		currentAARequestData.addToComparisonLog("AARequest matches HVEResponse");
		return true;
	}
	private static boolean compare(ACECheckAPIAAResponseData currentAAResponseData, ACECheckAPIResponseData currentACEAPIResponse) {
		currentAAResponseData.addToComparisonLog("Comparing AAResponse and ACEAPIResponse...");
		if(! currentAAResponseData.compareData(currentAAResponseData.getAAData(), "AAResponse", currentACEAPIResponse.getResponseMap(), "ACEAPIResponse")) {
			return false;
		}
		currentAAResponseData.addToComparisonLog("AAResponse matches ACEAPIResponse");
		return true;
	}

	public static void verifyServiceData(String srcContainer, String src, String tgt) {
		if(src.equalsIgnoreCase("HVEResponse") && tgt.equalsIgnoreCase("GFSPostData")) {
			verifyHVEResponseWithGFSPOSTData(srcContainer);		
		}
		else if(src.equalsIgnoreCase("AAResponse") && tgt.equalsIgnoreCase("GFSPostData")) {
			verifyAAResponseWithGFSPOSTData(srcContainer);		
		}
	}

	private static void verifyAAResponseWithGFSPOSTData(String srcContainerName) {
		ACECheckAPIAAResponseData responseAA=ACECheckAPIAAResponseData.getCurrentResponse();
		if(responseAA == null) {
			log.info("ERROR: current AAResponse is null");
			TestScenario.writeToScenario("ERROR: current AAResponse is null");
			return;
		}
		GFSDBBase dbGFSPOSTData=GFSDB.getCurrentGFSDBDocuments().getDbDataACE();
		if(dbGFSPOSTData == null) {
			log.info("ERROR: GFSPostData DB Doc is null");
			TestScenario.writeToScenario("ERROR: GFSPostData DB Doc is null");
			//assertTrue("GFSPOST Verification failed", false);
			return;
		}
		boolean compareStatus=false;
		try {
			responseAA.addToComparisonLog("Comparing AAResponse "+srcContainerName+ " with GFSPOSTData");
			switch(srcContainerName) {
				case "AA":
					List<String> skipElements=new ArrayList<String>();
					//skipElements.add(AlternateAppraisalDecisionEffectiveDatetime);

					compareStatus=responseAA.compareToDB(responseAA.getGFSAAData(), "AAResponse "+srcContainerName, dbGFSPOSTData, skipElements);
					break;
			}
		}
		catch(Exception e) {
		}
		if(compareStatus) {
			responseAA.addToComparisonLog("AAResponse "+srcContainerName+" matches with GFSPOSTData");
		}
		log.info(responseAA.getComparisonLog());
		TestScenario.writeToScenario(responseAA.getComparisonLog());
		responseAA.resetComparisonLog();
		assertTrue("AAResponse-"+srcContainerName+ " GFSPOSTData Verification Failed", compareStatus);
	}

	private static void verifyHVEResponseWithGFSPOSTData(String srcContainerName) {
		ACECheckAPIHVEResponseData responseHVE=ACECheckAPIHVEResponseData.getCurrentResponse();
		if(responseHVE == null) {
			log.info("ERROR: current HVEResponse is null");
			TestScenario.writeToScenario("ERROR: current HVEResponse is null");
			return;
		}
		GFSDBBase dbGFSPOSTData=GFSDB.getCurrentGFSDBDocuments().getDbDataACE();
		if(dbGFSPOSTData == null) {
			log.info("ERROR: GFSPostData DB Doc is null");
			TestScenario.writeToScenario("ERROR: GFSPostData DB Doc is null");
			//assertTrue("GFSPOST Verification failed", false);
			return;
		}
		boolean compareStatus=false;
		try {
			responseHVE.addToComparisonLog("Comparing HVEResponse "+srcContainerName+ " with GFSPOSTData");
			switch(srcContainerName) {
				case "HVE1":
					List<String> skipElements=new ArrayList<String>();
					skipElements.add(HomeValueExplorerAssessmentDateTime);		
					skipElements.add(PropertyValuationEffectiveDateTime);		
					compareStatus=responseHVE.compareToDB(responseHVE.getHVE(), "HVEResponse "+srcContainerName, dbGFSPOSTData, skipElements);
					break;
				case "Address":
					compareStatus=responseHVE.compareToDB(responseHVE.getAddress(), "HVEResponse "+srcContainerName, dbGFSPOSTData);
					break;
			}
			if(compareStatus) {
				responseHVE.addToComparisonLog("HVEResponse "+srcContainerName+" matches with GFSPOSTData");
			}
		}
		catch(Exception e) {
		}
		log.info(responseHVE.getComparisonLog());
		TestScenario.writeToScenario(responseHVE.getComparisonLog());
		responseHVE.resetComparisonLog();
		assertTrue("HVEResponse-"+srcContainerName+ " GFSPOSTData Verification Failed", compareStatus);
	}

	public static void verifyACECheckAPIResponse(Map<String, String> dataMap) {
		boolean compareStatus=false;
		ACECheckAPIResponseData currentACEAPIResponse=ACECheckAPIResponseData.getCurrentResponse();
		if(currentACEAPIResponse == null) {
			log.info("ERROR: ACEAPI Response is null");
			TestScenario.writeToScenario("ERROR: ACEAPI Response is null");
			return;
		}
		compareStatus=compare(dataMap, currentACEAPIResponse);
		log.info(currentACEAPIResponse.getComparisonLog());
		TestScenario.writeToScenario(currentACEAPIResponse.getComparisonLog());
		currentACEAPIResponse.resetComparisonLog();
		assertTrue("ACEAPI Response Verification Failed", compareStatus);
	}

	private static boolean compare(Map<String, String> dataMap, ACECheckAPIResponseData currentACEAPIResponse) {
		currentACEAPIResponse.addToComparisonLog("Comparing expected values and ACEAPIResponse...");
		if(! currentACEAPIResponse.compareData(dataMap, "Expected Values", currentACEAPIResponse.getResponseMap(), "ACECheckAPI Response")) {
			return false;
		}
		currentACEAPIResponse.addToComparisonLog("ACECheckAPIResponse matches expected values");
		return true;
	}

	public static void prepareDataForScript() {
		//String header="aceapiid|alternateappraisaleligibilitydecision|propertyagecount|homevalueexplorermidpointvalueestimateamount|createddate|sourceapplicationname|aceapiid_ace|requesttype|propertyagecount_ace|avmvalueamount|servicename|objectid|acecreatedate|message";
		//log.info(header);
		
		String values="";
		GFSDBBase dbGFSPOSTData=GFSDB.getCurrentGFSDBDocuments().getDbDataACE();
		ACECheckAPIDBBase dbHVSResponse=ACECheckAPIDB.getCurrentACEAPIDBDocuments().getHVEResponse();
		DocumentContext dbGFSPOSTDataJson=dbGFSPOSTData.getDBData();
		DocumentContext dbHVSResponseSvcTraceDataJson=dbHVSResponse.getDBData();
		ACECheckAPIHVEResponseData responseHVE=ACECheckAPIHVEResponseData.getCurrentResponse();
		Map<String, String> HVE=responseHVE.getHVE();
		
		//values+=ACECheckAPIDerivedData.getDerivedData().getACEAPI10RequestID();
		values+=JSONUtilities.getJsonElementValue(dbHVSResponseSvcTraceDataJson,"contextMap._id");
		values+="|";
		values+=dbGFSPOSTData.getElementValue(AlternateAppraisalEligibilityDecision);
		values+="|";
		String propertyagecount=dbGFSPOSTData.getElementValue(PropertyAgeCount);
		if(propertyagecount == null || propertyagecount.length() ==0) {
			propertyagecount="NULL";
		}
		else {
			propertyagecount=String.valueOf((int) Double.parseDouble(propertyagecount));
		}
		values+=propertyagecount;
		values+="|";
		values+=dbGFSPOSTData.getElementValue(HomeValueExplorerMidPointValueEstimateAmount);
		values+="|";
		values+=dbGFSPOSTData.getDate("createdDate", "yyyy-MM-dd hh:mm:ss.SSS");
		values+="|";
		values+=JSONUtilities.getJsonElementValue(dbGFSPOSTDataJson,"sourceApplicationName");
		values+="|";
		values+=JSONUtilities.getJsonElementValue(dbGFSPOSTDataJson,"aceApiId");
		values+="|";
		values+=JSONUtilities.getJsonElementValue(dbHVSResponseSvcTraceDataJson,"contextMap.requestType");
		values+="|";
		String propertyagecount_ace=HVE.get(PropertyAgeCount);
		if(propertyagecount_ace == null || propertyagecount_ace.length() ==0) {
			propertyagecount_ace="NULL";
		}
		else {
			propertyagecount_ace=String.valueOf((int) Double.parseDouble(propertyagecount_ace));
		}
		values+=propertyagecount_ace;
		values+="|";
		values+=HVE.get(HomeValueExplorerMidPointValueEstimateAmount);
		values+="|";
		values+=JSONUtilities.getJsonElementValue(dbHVSResponseSvcTraceDataJson,"contextMap.serviceName");
		values+="|";
		values+=dbGFSPOSTData.getObjectID();
		values+="|";
		values+=dbHVSResponse.getDate("date", "yyyy-MM-dd hh:mm:ss.SSS");
		values+="|";
		values+=dbHVSResponse.getElementValue(Message);
		
		log.info("Data to compare and fix: "+values);

	}

	public static void verifyServiceData(String type, Map<String, String> dataMap) {
		switch(type) {
		case HVE_Response:
			ACECheckAPIHVEResponseData currentHVEResponse=ACECheckAPIHVEResponseData.getCurrentResponse();
			if(currentHVEResponse == null) {
				try {
					ACECheckAPIDB.getCorrespondingDoc(HVE_Response);
					currentHVEResponse=ACECheckAPIHVEResponseData.getCurrentResponse();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(currentHVEResponse == null) {
					log.info("ERROR: HVE Response is null");
					TestScenario.writeToScenario("ERROR: HVE Response is null");
					return;
				}
			}
			verifyHVEResponse(dataMap);
			break;
		}
	}

	private static void verifyHVEResponse(Map<String, String> dataMap) {
		ACECheckAPIHVEResponseData currentHVEResponse = ACECheckAPIHVEResponseData.getCurrentResponse();
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
			ACECheckAPIHVEResponseData currentHVEResponse = ACECheckAPIHVEResponseData.getCurrentResponse();
			if (currentHVEResponse == null) {
				log.info("ERROR: HVE Response is null");
				TestScenario.writeToScenario("ERROR: HVE Response is null");
				return;
			}
			switch (tgt) {
			case AA_Request:
				ACECheckAPIAARequestData currentAARequestData = ACECheckAPIAARequestData.getCurrentRequest();
				if (currentAARequestData == null) {
					log.info("ERROR: current AA Request is null");
					TestScenario.writeToScenario("ERROR: current AA Request is null");
					return;
				}
				compareHVSResponseAARequest(elementsList);
				break;
			case GFSDB.GFSPostData:
				GFSDBBase dbGFSPOSTData = GFSDB.getCurrentGFSDBDocuments().getDbDataACE();
				if (dbGFSPOSTData == null) {
					try {
						GFSDB.getCorrespondingDoc(GFSDB.GFSPostData);
						dbGFSPOSTData = GFSDB.getCurrentGFSDBDocuments().getDbDataACE();
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (dbGFSPOSTData == null) {
						log.info("ERROR: GFSPostData DB Doc is null");
						TestScenario.writeToScenario("ERROR: GFSPostData DB Doc is null");
						return;
					}						
				}
				compareHVSResponseGFSPost(elementsList);
				break;
			}
		}
	}

	private static void compareHVSResponseGFSPost(List<String> elementsList) {
		ACECheckAPIHVEResponseData currentHVEResponse = ACECheckAPIHVEResponseData.getCurrentResponse();
		GFSDBBase dbGFSPOSTData = GFSDB.getCurrentGFSDBDocuments().getDbDataACE();

		String srcName = "HVS Response";
		Map<String, String> srcMap = currentHVEResponse.getHVE();
		String tgtName = "GFS POST data";
		Map<String, String> tgtMap = null;

		boolean compareStatus = false;
		if (elementsList != null) {
			srcMap = currentHVEResponse.filterElements(srcMap, elementsList);
			tgtMap = BaseData.filterElements(dbGFSPOSTData, elementsList);
			compareStatus = currentHVEResponse.compare(srcMap, srcName, tgtMap, tgtName);
		} else {
			List<String> skipElements = new ArrayList<String>();
			skipElements.add(HomeValueExplorerAssessmentDateTime);
			skipElements.add(PropertyValuationEffectiveDateTime);
			compareStatus = currentHVEResponse.compareToDB(srcMap, srcName, dbGFSPOSTData, skipElements);
		}

		assertTrue("HVS Response - GFS POST data Verification Failed", compareStatus);
	}

	private static void compareHVSResponseAARequest(List<String> elementsList) {
		ACECheckAPIHVEResponseData currentHVEResponse = ACECheckAPIHVEResponseData.getCurrentResponse();
		ACECheckAPIAARequestData currentAARequestData = ACECheckAPIAARequestData.getCurrentRequest();

		String srcName = "HVS Response";
		Map<String, String> srcMap = currentHVEResponse.getHVE();
		String tgtName = "EDS AA Request";
		Map<String, String> tgtMap = currentAARequestData.getHVE();

		boolean compareStatus = false;

		if (elementsList != null) {
			srcMap = currentHVEResponse.filterElements(srcMap, elementsList);
			tgtMap = currentHVEResponse.filterElements(tgtMap, elementsList);
			compareStatus = currentHVEResponse.compare(srcMap, srcName, tgtMap, tgtName);
		} else {
			List<String> skipElements = new ArrayList<String>();
			skipElements.add(ACECheckAPIElements.HomeValueExplorerAssessmentDateTime);
			skipElements.add(ACECheckAPIElements.PropertyValuationEffectiveDateTime);
			skipElements.add(ACECheckAPIElements.PropertyUnitIndicator);
			skipElements.add(ACECheckAPIElements.PropertyLandUseIndicator);
			skipElements.add(ACECheckAPIElements.PropertyRefinanceLotSizeTwoAcreIndicator);
			skipElements.add(ACECheckAPIElements.PropertyPurchaseLotSizeOneAcreIndicator);
			System.out.println(srcMap);
			System.out.println(tgtMap);

			compareStatus = currentHVEResponse.compare(srcMap, srcName, tgtMap, tgtName, skipElements, null);
		}
		assertTrue("HVS Response - AA Request Verification Failed", compareStatus);
	}
}
