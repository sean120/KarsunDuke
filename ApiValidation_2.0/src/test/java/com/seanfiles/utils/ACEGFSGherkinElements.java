package com.seanfiles.utils;

import static com.seanfiles.elements.ACEAPI20Elements.RUN_ID;

import java.util.HashMap;
import java.util.Map;

import com.seanfiles.helper.TestConfig;

public class ACEGFSGherkinElements {
	private static Map<String, String> gherkinElements;
	
	static {
		gherkinElements =new HashMap<String, String>();
		gherkinElements.put("LPKey", "LoanID_LPKey");
		gherkinElements.put("LPT", "LoanID_LPT");
		gherkinElements.put("ACEAPIID", "LoanID_ACEAPI");
		gherkinElements.put("SellerID", "PartyID_SELLER");
		gherkinElements.put("Address", "addressLineText");
		gherkinElements.put("HVE", "homeValueExplorerMidPointValueEstimateAmount");
		gherkinElements.put("HVE1", "homeValueExplorerMidPointValueEstimateAmount");
		gherkinElements.put("HVE2", "HVE2homeValueExplorerMidPointValueEstimateAmount");
		gherkinElements.put("ACEDecision", "alternateAppraisalEligibilityDecision");
		gherkinElements.put("AAStatus", "alternateAppraisalDecisionStatusType");
		gherkinElements.put("PV", "propertyValuationAmount");
		gherkinElements.put("Client","sourceApplicationName");
		gherkinElements.put("Date","transactionDate");
		gherkinElements.put("Stage","loanProcessingStageType");
	}
	
	public static String getGFSDataElement(String gherkinElement) {
		if(gherkinElements.containsKey(gherkinElement)) {
			return gherkinElements.get(gherkinElement);
		}
		return gherkinElement;
	}
	
	public static Map<String, String> updateGherkinElementNames(Map<String, String> dataMap) {
		Map<String, String> dataElementsMap=new HashMap<String, String>();
		for (Map.Entry<String, String> dataMapItem : dataMap.entrySet())
		{
			String elementName=dataMapItem.getKey().trim();
			String elementValue=dataMapItem.getValue();
			if(elementValue != null) {
				elementValue=elementValue.trim();
				if(elementValue.contains(RUN_ID)) {
					elementValue=elementValue.replace(RUN_ID, TestConfig.getRunID());
				}
			}
			dataElementsMap.put(getGFSDataElement(elementName), elementValue);
		}
		return dataElementsMap;
	}
}
