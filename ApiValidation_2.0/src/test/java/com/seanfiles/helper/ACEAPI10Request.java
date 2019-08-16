package com.seanfiles.helper;

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
import com.seanfiles.root.LoadTestConfig;
import com.seanfiles.utils.JsonUtils;


public class ACEAPI10Request {

	public static final String PartyRoleType="partyRoleType";
	public static final String PartyRoleIdentifier="partyRoleIdentifier";
	public static final String AddressLineText="addressLineText";
	public static final String CityName="cityName";
	public static final String PostalCode="postalCode";
	public static final String StateCode="stateCode";
	public static final String LoanPurposeType="loanPurposeType";
	public static final String Address="address";
	public static final String LoanInformation="loanInformation";
	public static final String RequestBatchIdentifier="requestBatchIdentifier";
	public static final String Party="party";
	public static final String Deals="deals";
	public static final String SalesContractAmount="salesContractAmount";
	public static final String PropertyEstimatedValueAmount="propertyEstimatedValueAmount";
	public static final String RequestTransactionIdentifier="requestTransactionIdentifier";	
	
	
	class JSONPath {
		public static final String PartyRoleType="$.party.partyRoleType";
		public static final String PartyRoleIdentifier="$.party.partyRoleIdentifier";
		public static final String AddressLineText="$.deals[0].address.addressLineText";
		public static final String CityName="$.deals[0].address.cityName";
		public static final String PostalCode="$.deals[0].address.postalCode";
		public static final String StateCode="$.deals[0].address.stateCode";
		public static final String LoanPurposeType="$.deals[0].loanInformation.loanPurposeType";
		public static final String RequestBatchIdentifier="$.requestBatchIdentifier";
		public static final String SalesContractAmount="$.deals[0].loanInformation.salesContractAmount";
		public static final String PropertyEstimatedValueAmount="$.deals[0].loanInformation.propertyEstimatedValueAmount";
		public static final String RequestTransactionIdentifier="$.deals[0].requestTransactionIdentifier";	
	}
	
	class DataType {
		public static final String PartyRoleType="String";
		public static final String PartyRoleIdentifier="String";
		public static final String AddressLineText="String";
		public static final String CityName="String";
		public static final String PostalCode="String";
		public static final String StateCode="String";
		public static final String LoanPurposeType="String";
		public static final String RequestBatchIdentifier="String";
		public static final String SalesContractAmount="Integer";
		public static final String PropertyEstimatedValueAmount="Integer";
		public static final String RequestTransactionIdentifier="String";	
	}
	
	Map<String, String> requestMap;
	
	private static ACEAPI10Request currentRequest=null;
	private String JSONStr=null;
	
	private ACEAPI10Request() {
		requestMap=new HashMap<String, String>();
	}
	
	public static ACEAPI10Request getCurrentRequest() {
		return currentRequest;
	}
	
	public static ACEAPI10Request getNewACEAPIRequest(String JSONFileName) {
		currentRequest=new ACEAPI10Request();
		if (JSONFileName != null) {

			String JSONFilePath=System.getProperty("user.dir").concat(LoadTestConfig.getProperty("schemavalfile").concat(JSONFileName));
			try {
				currentRequest.JSONStr = FileUtils.readFileToString(new File(JSONFilePath),Charset.defaultCharset()).trim();
				currentRequest.parseACEAPIRequestJSON();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return currentRequest;
	}
	
	private void parseACEAPIRequestJSON() {
		DocumentContext requestJSON= JsonPath.parse(getRequestJSONStr());		
		currentRequest.setValue(PartyRoleType, JsonUtils.getJsonElementValue(requestJSON,JSONPath.PartyRoleType, DataType.PartyRoleType));
		currentRequest.setValue(PartyRoleIdentifier, JsonUtils.getJsonElementValue(requestJSON,JSONPath.PartyRoleIdentifier, DataType.PartyRoleIdentifier));
		currentRequest.setValue(AddressLineText, JsonUtils.getJsonElementValue(requestJSON,JSONPath.AddressLineText, DataType.AddressLineText));
		currentRequest.setValue(CityName, JsonUtils.getJsonElementValue(requestJSON,JSONPath.CityName, DataType.CityName));
		currentRequest.setValue(PostalCode, JsonUtils.getJsonElementValue(requestJSON,JSONPath.PostalCode, DataType.PostalCode));
		currentRequest.setValue(StateCode, JsonUtils.getJsonElementValue(requestJSON,JSONPath.StateCode, DataType.StateCode));
		currentRequest.setValue(LoanPurposeType, JsonUtils.getJsonElementValue(requestJSON,JSONPath.LoanPurposeType, DataType.LoanPurposeType));
		currentRequest.setValue(RequestBatchIdentifier, JsonUtils.getJsonElementValue(requestJSON,JSONPath.RequestBatchIdentifier, DataType.RequestBatchIdentifier));
		currentRequest.setValue(SalesContractAmount, String.valueOf(JsonUtils.getJsonElementValue(requestJSON,JSONPath.SalesContractAmount, DataType.SalesContractAmount)));
		currentRequest.setValue(PropertyEstimatedValueAmount, String.valueOf(JsonUtils.getJsonElementValue(requestJSON,JSONPath.PropertyEstimatedValueAmount, DataType.PropertyEstimatedValueAmount)));
		currentRequest.setValue(RequestTransactionIdentifier, JsonUtils.getJsonElementValue(requestJSON,JSONPath.RequestTransactionIdentifier, DataType.RequestTransactionIdentifier));
	}

	public static ACEAPI10Request getNewACEAPIRequest(Map<String, String> dataMap) {
		currentRequest=new ACEAPI10Request();
		
		//default values
		currentRequest.setValue(PartyRoleType, "LoanSeller");
		currentRequest.setValue(RequestBatchIdentifier, "1234");
		currentRequest.setValue(RequestTransactionIdentifier, "1234");
		
		for (Map.Entry<String, String> dataMapItem : dataMap.entrySet())
		{
			String elementName=dataMapItem.getKey().trim();
			String elementValue=dataMapItem.getValue().trim();
			currentRequest.setValue(elementName, elementValue);
		}
		
		currentRequest.JSONStr=currentRequest.buildACEAPIRequestJSON();
		return currentRequest;
	}
	
	public String getRequestJSONStr() {
		return JSONStr;
	}
	
	private void setValue(String fieldName, String fieldValue) {
		requestMap.put(fieldName, fieldValue);
	}	

	public String getValue(String fieldName) {
		return requestMap.get(fieldName);
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
		loanJson.put(LoanPurposeType, loanPurposeType);		
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

	
}
