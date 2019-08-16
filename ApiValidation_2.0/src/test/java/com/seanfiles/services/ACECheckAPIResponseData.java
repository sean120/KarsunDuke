package com.seanfiles.services;


import static com.seanfiles.elements.ACECheckAPIElements.*;

import java.util.HashMap;
import java.util.Map;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.seanfiles.db.ACECheckAPIDB;
import com.seanfiles.db.ACECheckAPIDBBase;
import com.seanfiles.elements.ACECheckAPIElementsPaths;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.utils.JSONUtilities;

public class ACECheckAPIResponseData extends BaseData{

	Map<String, String> responseMap;
	
	public ACECheckAPIResponseData(String responseJSONStr) {
		responseMap=new HashMap<String, String>();
		parseResponseJSON(responseJSONStr);
	}
	
	public ACECheckAPIResponseData(ACECheckAPIDBBase dbACEAPIResponseData) {
		responseMap=new HashMap<String, String>();
		parseDBJSON(dbACEAPIResponseData);
	}

	private void parseDBJSON(ACECheckAPIDBBase dbACEAPIResponseData) {
		setValue(dbACEAPIResponseData, PartyRoleType);
		setValue(dbACEAPIResponseData, PartyRoleIdentifier);
		
		setValue(dbACEAPIResponseData, AddressLineText);
		setValue(dbACEAPIResponseData, CityName);
		setValue(dbACEAPIResponseData, PostalCode);
		setValue(dbACEAPIResponseData, StateCode);
		
		setValue(dbACEAPIResponseData, MessageCode1);
		setValue(dbACEAPIResponseData, MessageText1);
		
		setValue(dbACEAPIResponseData, AppraisalWaiverPrescreenEligibilityType);
		setValue(dbACEAPIResponseData, AppraisalWaiverPrescreenExpirationDate);
		setValue(dbACEAPIResponseData, MaximumAuthorizedLoanAmount);
		
		setValue(dbACEAPIResponseData, RequestBatchIdentifier);
		setValue(dbACEAPIResponseData, RequestTransactionIdentifier);
	}

	private void setValue(ACECheckAPIDBBase dbACEAPIResponseData, String elementName) {
		try {
			setValue(elementName, dbACEAPIResponseData.getElementValue(elementName) );
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void parseResponseJSON(String responseJSONStr) {
		DocumentContext responseJSON= JsonPath.parse(responseJSONStr);		
		setValue(responseJSON, PartyRoleType);
		setValue(responseJSON, PartyRoleIdentifier);
		
		setValue(responseJSON, AddressLineText);
		setValue(responseJSON, CityName);
		setValue(responseJSON, PostalCode);
		setValue(responseJSON, StateCode);
		
		setValue(responseJSON, MessageCode1);
		setValue(responseJSON, MessageText1);
		
		setValue(responseJSON, AppraisalWaiverPrescreenEligibilityType);
		setValue(responseJSON, AppraisalWaiverPrescreenExpirationDate);
		setValue(responseJSON, MaximumAuthorizedLoanAmount);
		
		setValue(responseJSON, RequestBatchIdentifier);
		setValue(responseJSON, RequestTransactionIdentifier);
		
	}
	
	private void setValue(DocumentContext responseJSON, String elementName) {
		try {
			setValue(elementName, 
					JSONUtilities.getJsonElementValue(responseJSON, 
							ACECheckAPIElementsPaths.getACEAPIElementsPaths().getElementJSONPath(elementName)));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void setValue(String fieldName, String fieldValue) {
		responseMap.put(fieldName, fieldValue);
	}	

	public String getValue(String fieldName) {
		return responseMap.get(fieldName);
	}

	private static ACECheckAPIResponseData currentResponse=null;
	
	public static ACECheckAPIResponseData getCurrentResponse() {
		return currentResponse;
	}
	
	public static void setACEAPIResponse(String responseJSONStr) {
		currentResponse=new ACECheckAPIResponseData(responseJSONStr);
	}
	
	public static void clear() {
		currentResponse=null;
	}

	public Map<String, String> getResponseMap() {
		return responseMap;
	}

	public static void createACEAPI10ResponseFromDBDoc() {
		ACECheckAPIDBBase dbACEAPIResponseData=ACECheckAPIDB.getCurrentACEAPIDBDocuments().getACEAPIResponse();
		if(dbACEAPIResponseData == null) {
			TestScenario.writeToScenario("ERROR: DB doc for ACEAPI Response is null");
			return;
		}
		currentResponse=new ACECheckAPIResponseData(dbACEAPIResponseData);
	}
	
}
