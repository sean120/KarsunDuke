package com.seanfiles.services;

import static com.seanfiles.elements.ACECheckAPIElements.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.seanfiles.db.ACECheckAPIDB;
import com.seanfiles.db.ACECheckAPIDBBase;
import com.seanfiles.elements.ACECheckAPIElementsPaths;
import com.seanfiles.helper.TestConfig;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.utils.JSONUtilities;

public class ACECheckAPIRequestData extends BaseData{

	Map<String, String> requestMap;
	private String JSONStr=null;
	
	public ACECheckAPIRequestData(String requestJSONFileName) {
		requestMap=new HashMap<String, String>();
		if (requestJSONFileName != null) {

			String JSONFilePath=System.getProperty("user.dir").concat(TestConfig.getProperty("ACEAPI10ReqFiles").concat(requestJSONFileName));
			try {
				JSONStr = FileUtils.readFileToString(new File(JSONFilePath),Charset.defaultCharset()).trim();
				parseACEAPIRequestJSON();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ACECheckAPIRequestData(Map<String, String> requestDataMap) {
		requestMap=new HashMap<String, String>();
		//default values
		setValue(PartyRoleType, "LoanSeller");
		setValue(RequestBatchIdentifier, "1234");
		setValue(RequestTransactionIdentifier, "1234");
		
		for (Map.Entry<String, String> dataMapItem : requestDataMap.entrySet())
		{
			String elementName=dataMapItem.getKey().trim();
			String elementValue=dataMapItem.getValue().trim();
			setValue(elementName, elementValue);
		}
		
		JSONStr=buildACEAPIRequestJSON();
	}
	
	public ACECheckAPIRequestData(ACECheckAPIDBBase dbACEAPIRequestData) {
		requestMap=new HashMap<String, String>();
		if (dbACEAPIRequestData != null) {
			parseACEAPIDBJSON(dbACEAPIRequestData);
		}
	}

	private void parseACEAPIDBJSON(ACECheckAPIDBBase dbACEAPIRequestData) {
		setValue(dbACEAPIRequestData, PartyRoleType);
		setValue(dbACEAPIRequestData, PartyRoleIdentifier);
		setValue(dbACEAPIRequestData, AddressLineText);
		setValue(dbACEAPIRequestData, CityName);
		setValue(dbACEAPIRequestData, PostalCode);
		setValue(dbACEAPIRequestData, StateCode);
		setValue(dbACEAPIRequestData, LoanPurposeType);
		setValue(dbACEAPIRequestData, RequestBatchIdentifier);
		setValue(dbACEAPIRequestData, SalesContractAmount);
		setValue(dbACEAPIRequestData, PropertyEstimatedValueAmount);
		setValue(dbACEAPIRequestData, RequestTransactionIdentifier);
	}

	private void setValue(ACECheckAPIDBBase dbACEAPIRequestData, String elementName) {
		setValue(elementName, dbACEAPIRequestData.getElementValue(elementName));
	}

	private void parseACEAPIRequestJSON() {
		DocumentContext requestJSON= JsonPath.parse(getRequestJSONStr());		
		setValue(requestJSON, PartyRoleType);
		setValue(requestJSON, PartyRoleIdentifier);
		setValue(requestJSON, AddressLineText);
		setValue(requestJSON, CityName);
		setValue(requestJSON, PostalCode);
		setValue(requestJSON, StateCode);
		setValue(requestJSON, LoanPurposeType);
		setValue(requestJSON, RequestBatchIdentifier);
		setValue(requestJSON, SalesContractAmount);
		setValue(requestJSON, PropertyEstimatedValueAmount);
		setValue(requestJSON, RequestTransactionIdentifier);
	}
	
	private String buildACEAPIRequestJSON() {
		JSONObject partyJson = new JSONObject();
		partyJson.put(PartyRoleType, getValue(PartyRoleType));
		partyJson.put(PartyRoleIdentifier, getValue(PartyRoleIdentifier));		
		
		JSONObject addressJson = new JSONObject();
		addressJson.put(AddressLineText, getValue(AddressLineText));
		addressJson.put(CityName, getValue(CityName));
		addressJson.put(PostalCode, getValue(PostalCode));
		addressJson.put(StateCode, getValue(StateCode));
		
		JSONObject loanJson = new JSONObject();
		String loanPurposeType=getValue(LoanPurposeType);
		loanJson.put(LoanPurposeType,loanPurposeType);
		if(loanPurposeType.equalsIgnoreCase("Purchase")) {
			loanJson.put(SalesContractAmount, Integer.valueOf(getValue(SalesContractAmount)));
		}
		else if(loanPurposeType.equalsIgnoreCase("Refinance")) {
			loanJson.put(PropertyEstimatedValueAmount, Integer.valueOf(getValue(PropertyEstimatedValueAmount)));
		}
		
		JSONObject dealJson = new JSONObject();
		dealJson.put(RequestTransactionIdentifier, getValue(RequestTransactionIdentifier));
		dealJson.put(Address,addressJson);
		dealJson.put(LoanInformation,loanJson);

		JSONArray dealsArrayJson = new JSONArray();
		dealsArrayJson.put(dealJson);

		JSONObject aceAPIReqJson = new JSONObject();
		aceAPIReqJson.put(RequestBatchIdentifier, getValue(RequestBatchIdentifier));
		aceAPIReqJson.put(Party, partyJson);
		aceAPIReqJson.put(Deals, dealsArrayJson);
		
		String jsonString = aceAPIReqJson.toString();
		return jsonString;
	}
	
	private void setValue(DocumentContext requestJSON, String elementName) {
		setValue(elementName, 
				JSONUtilities.getJsonElementValue(requestJSON, 
						ACECheckAPIElementsPaths.getACEAPIElementsPaths().getElementJSONPath(elementName)));
	}

	public String getRequestJSONStr() {
		return JSONStr;
	}
	
	public Map<String, String> getRequestMap() {
		return requestMap;
	}

	private void setValue(String fieldName, String fieldValue) {
		requestMap.put(fieldName, fieldValue);
	}	

	public String getValue(String fieldName) {
		return requestMap.get(fieldName);
	}	
	
	private static ACECheckAPIRequestData currentRequest=null;
	
	public static ACECheckAPIRequestData getCurrentRequest() {
		return currentRequest;
	}
	
	public static ACECheckAPIRequestData getNewACEAPIRequest(String requestJSONFileName) {
		currentRequest=new ACECheckAPIRequestData(requestJSONFileName);
		return currentRequest;
	}
	
	public static ACECheckAPIRequestData getNewACEAPIRequest(Map<String, String> requestDataMap) {
		currentRequest=new ACECheckAPIRequestData(requestDataMap);
		
		return currentRequest;
	}
	
	public static void clear() {
		currentRequest=null;
	}

	public static ACECheckAPIRequestData createACEAPI10RequestFromDBDoc() {
		ACECheckAPIDBBase dbACEAPIRequestData=ACECheckAPIDB.getCurrentACEAPIDBDocuments().getACEAPIRequest();
		if(dbACEAPIRequestData == null) {
			TestScenario.writeToScenario("ERROR: DB doc for ACEAPI Request is null");
			return null;
		}
		currentRequest=new ACECheckAPIRequestData(dbACEAPIRequestData);
		return currentRequest;
	}

}
