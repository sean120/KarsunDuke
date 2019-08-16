package com.seanfiles.helper;

import static com.seanfiles.services.BaseData.AA_Request;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.seanfiles.db.ACEAPI20DB;
import com.seanfiles.elements.ACECheckAPIElements;
import com.seanfiles.services.ACEAPI20AARequestData;
import com.seanfiles.services.ACEAPI20AAResponseData;
import com.seanfiles.services.ACEAPI20HVEResponseData;
import com.seanfiles.services.BaseData;
import com.seanfiles.services.GFSGETResponseData;
import com.seanfiles.services.GFSPOSTRequestData;

public class ACEGFSDataValidation {
	
	public static void verifyGETResponse(Map<String, String> dataMap) {
		GFSGETResponseData currentGFSGETResponse=GFSGETResponseData.getCurrentGETResponse();
		if(currentGFSGETResponse == null) {
			TestScenario.writeToScenario("ERROR: GFS GET Response is null");
			return;
		}
		
		boolean compareStatus=false;
		compareStatus=compareGETResponse(currentGFSGETResponse, dataMap);
		
		TestScenario.writeToScenario(currentGFSGETResponse.getComparisonLog());
		currentGFSGETResponse.resetComparisonLog();
		assertTrue("GET Response Verification failed", compareStatus);
	}
	
	private static boolean compareGETResponse(GFSGETResponseData currentGFSGETResponse, Map<String, String> dataMap) {
		List<String> skipElements=new ArrayList<String>();
//		skipElements.add(ACEAPI20Elements.HomeValueExplorerAssessmentDateTime);
		
		currentGFSGETResponse.addToComparisonLog("Verifying actual GET Response with expected values");
		if(! currentGFSGETResponse.compareData(dataMap, "Expected value", currentGFSGETResponse.getResponseDataMap(), "GFS GET Response",  skipElements)) {
			return false;
		}
		currentGFSGETResponse.addToComparisonLog("GET Response matches expected values");
		return true;
	}

	public static void verifyGFSData(String src, String srcCont, String dest, String destCont) {
		boolean compareStatus=false;
		
		GFSPOSTRequestData currentGFSPOSTRequest=GFSPOSTRequestData.getCurrentRequest();
		if(currentGFSPOSTRequest == null) {
			TestScenario.writeToScenario("ERROR: GFS POST Request is null");
			assertTrue("GFSPOST Verification failed", false);
			return;
		}
		switch(dest) {
			case BaseData.GFS_GET_Response:
				GFSGETResponseData currentGFSGETResponse=GFSGETResponseData.getCurrentGETResponse();
				if(currentGFSGETResponse == null) {
					TestScenario.writeToScenario("ERROR: GFS GET Response is null");
					return;
				}
				
				compareStatus=compareGETResponse(currentGFSGETResponse, currentGFSPOSTRequest, srcCont);
				
				TestScenario.writeToScenario(currentGFSGETResponse.getComparisonLog());
				currentGFSGETResponse.resetComparisonLog();
				assertTrue("GET Response Verification failed", compareStatus);
				break;
			case BaseData.AA_Request:
				ACEAPI20AARequestData currentAARequestData=ACEAPI20AARequestData.getCurrentRequest();
				if(currentAARequestData == null) {
					TestScenario.writeToScenario("ERROR: current AA Request is null");
					return;
				}
				compareStatus=compareGFSPOSTwithEDSRequest(currentAARequestData, currentGFSPOSTRequest, srcCont);
				
				TestScenario.writeToScenario(currentAARequestData.getComparisonLog());
				currentAARequestData.resetComparisonLog();
				assertTrue("GFSPOST - EDSRequest Verification failed", compareStatus);
				break;
			case BaseData.HVE_Response:
				ACEAPI20HVEResponseData currentHVEResponseData=ACEAPI20HVEResponseData.getCurrentResponse();
				if(currentHVEResponseData == null) {
					TestScenario.writeToScenario("ERROR: current HVS Response is null");
					return;
				}
				compareStatus=compareGFSPOSTwithHVSResponse(currentHVEResponseData, currentGFSPOSTRequest, srcCont);
				
				TestScenario.writeToScenario(currentHVEResponseData.getComparisonLog());
				currentHVEResponseData.resetComparisonLog();
				assertTrue("GFSPOST - HVS Response Verification failed", compareStatus);
				break;
			case BaseData.AA_Response:
				ACEAPI20AAResponseData currentAAResponseData=ACEAPI20AAResponseData.getCurrentResponse();
				if(currentAAResponseData == null) {
					TestScenario.writeToScenario("ERROR: current AA Response is null");
					return;
				}
				compareStatus=compareGFSPOSTwithAAResponse(currentAAResponseData, currentGFSPOSTRequest, srcCont);
				
				TestScenario.writeToScenario(currentAAResponseData.getComparisonLog());
				currentAAResponseData.resetComparisonLog();
				assertTrue("GFSPOST - AA Response Verification failed", compareStatus);
				break;
		}
	}
	
	private static boolean compareGFSPOSTwithAAResponse(ACEAPI20AAResponseData currentAAResponseData,
			GFSPOSTRequestData currentGFSPOSTRequest, String srcCont) {
		List<String> skipElements=new ArrayList<String>();
		
		currentAAResponseData.addToComparisonLog("Verifying AA Response with GFS POST values");
		if(! currentAAResponseData.compareData(currentGFSPOSTRequest.getAADataMap(), "GFS POST AA Data",  currentAAResponseData.getAADataGFS(), "AAResponse AA data", skipElements)) {
			return false;
		}
		currentAAResponseData.addToComparisonLog("AA Response matches with GFS POST values");
		return true;
	}

	private static boolean compareGFSPOSTwithHVSResponse(ACEAPI20HVEResponseData currentHVEResponseData,
			GFSPOSTRequestData currentGFSPOSTRequest, String srcCont) {
		List<String> skipElements=new ArrayList<String>();
		
		currentHVEResponseData.addToComparisonLog("Verifying HVS Response with GFS POST values");
		if(srcCont.equalsIgnoreCase("Address")) {
			if(! currentHVEResponseData.compareData(currentHVEResponseData.getAddress(), "HVS Response Address",  currentGFSPOSTRequest.getAddressMap(), "GFS POST Address", skipElements)) {
				return false;
			}
		}
		else {
			skipElements.add(ACECheckAPIElements.HomeValueExplorerOptionType);
			skipElements.add(ACECheckAPIElements.HomeValueExplorerAssessmentDateTime);
			skipElements.add(ACECheckAPIElements.PropertyValuationEffectiveDateTime);
			if(! currentHVEResponseData.compareData(currentHVEResponseData.getHVE(), "HVS Response",  currentGFSPOSTRequest.getHVE2Map(), "GFS POST HVE2", skipElements, "HVE2")) {
				return false;
			}
		}
		currentHVEResponseData.addToComparisonLog("HVS Response matches with GFS POST values");
		return true;
	}

	private static boolean compareGFSPOSTwithEDSRequest(ACEAPI20AARequestData currentAARequestData,
			GFSPOSTRequestData currentGFSPOSTRequest, String srcCont) {
		List<String> skipElements=new ArrayList<String>();
		
		currentAARequestData.addToComparisonLog("Verifying EDS Request with GFS POST values");
		if(srcCont.equalsIgnoreCase("HVE2")) {
			skipElements.add(ACECheckAPIElements.HVE2HomeValueExplorerOptionType);
			if(! currentAARequestData.compareData(currentAARequestData.getHVE2(), "EDS Request HVE2",  currentGFSPOSTRequest.getHVE2Map(), "GFS POST HVE2", skipElements)) {
				return false;
			}
		}
		else if(srcCont.equalsIgnoreCase("PV")) {
			if(! currentAARequestData.compareData(currentGFSPOSTRequest.getPVDataMap(), "GFS POST PV", currentAARequestData.getPV2(), "EDS Request Subsequent PV",  skipElements)) {
				return false;
			}
		}
		else if(srcCont.equalsIgnoreCase("HVE") || srcCont.equalsIgnoreCase("HVE1")) {
			skipElements.add(ACECheckAPIElements.HomeValueExplorerOptionType);
			if(! currentAARequestData.compareData(currentAARequestData.getHVE(), "EDS Request HVE1",  currentGFSPOSTRequest.getHVEMap(), "GFS POST HVE1", skipElements)) {
				return false;
			}
		}
		currentAARequestData.addToComparisonLog("EDS Request matches with GFS POST values");
		return true;
	}

	private static boolean compareGETResponse(GFSGETResponseData currentGFSGETResponse,
			GFSPOSTRequestData currentGFSPOSTRequest, String srcCont) {
		List<String> skipElements=new ArrayList<String>();
//		skipElements.add(ACEAPI20Elements.HomeValueExplorerAssessmentDateTime);
		
		currentGFSGETResponse.addToComparisonLog("Verifying actual GET Response with previously posted values");
		if(srcCont.equalsIgnoreCase("HVE2")) {
			if(! currentGFSGETResponse.compareData(currentGFSGETResponse.getHVEData(), "GFS GET Response HVE",  currentGFSPOSTRequest.getHVE2Map(), "Previously posted value", skipElements, "HVE2")) {
				return false;
			}
		}
		else {
			if(! currentGFSGETResponse.compareData(currentGFSGETResponse.getHVEData(), "GFS GET Response HVE",  currentGFSPOSTRequest.getHVEMap(), "Previously posted value", skipElements)) {
				return false;
			}
		}
		currentGFSGETResponse.addToComparisonLog("GET Response matches previously posted values");
		return true;
	}

}
