package com.seanfiles.services;

import static com.seanfiles.elements.ACEGFSElements.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.seanfiles.db.GFSDB;
import com.seanfiles.db.GFSDBBase;
import com.seanfiles.elements.ACEGFSElementsPaths;
import com.seanfiles.helper.TestConfig;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.utils.JSONUtilities;

import net.minidev.json.JSONObject;

public class GFSGETRequestData extends BaseData {

	Map<String, String> requestMap;
	private String JSONStr=null;
	String requestSrc;
	GFSDBBase dbACEAPIRequestData;
	DocumentContext requestJSON;
	
	public static final String RequestJSONTypePAYLOAD="PAYLOADJSON";
	public static final String RequestJSONTypeDB="DBJSON";
	
	public GFSGETRequestData(String requestJSONFileName) {
		requestMap=new HashMap<String, String>();
		if (requestJSONFileName != null) {

			String JSONFilePath=System.getProperty("user.dir").concat(TestConfig.getProperty("ACEGFSReqFiles").concat(requestJSONFileName));
			try {
				JSONStr = FileUtils.readFileToString(new File(JSONFilePath),Charset.defaultCharset()).trim();
				parseACEGFSRequestJSON();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public GFSGETRequestData(GFSDBBase dbACEAPIRequestData) {
		requestMap=new HashMap<String, String>();
		if (dbACEAPIRequestData != null) {
			parseGFSDBJSON(dbACEAPIRequestData);
		}
	}
	public GFSGETRequestData() {
		requestMap=new HashMap<String, String>();
	}

	private void parseGFSDBJSON(GFSDBBase dbACEAPIRequestData) {
		requestSrc=RequestJSONTypeDB;
		this.dbACEAPIRequestData=dbACEAPIRequestData;
		parseJSON();
	}
	
	private void setValue(String elementName) {		
		if(requestSrc.equalsIgnoreCase(RequestJSONTypePAYLOAD)) {
			setValue(requestJSON, elementName);
		}
		else {
			setValue(dbACEAPIRequestData, elementName);
		}
		
	}
	
	private void setValue(GFSDBBase dbACEAPIRequestData, String elementName) {
		setValue(elementName, dbACEAPIRequestData.getElementValue(elementName));
		
	}
	
	private void parseJSON() {
		setValue(sourceApplicationName);
		
		setValue(LoanID_LPKey);
		setValue(LoanID_LPT);
	
		setValue(PartyID_SELLER);		
		
		setValue(AddressLineText);
		setValue(PostalCode);
}
	
	private void parseACEGFSRequestJSON() {
		DocumentContext requestJSON= JsonPath.parse(getRequestJSONStr());
		requestSrc=RequestJSONTypePAYLOAD;
		this.requestJSON=requestJSON;
		parseJSON();
	}
	
	private void setValue(DocumentContext requestJSON, String elementName) {
		setValue(elementName, 
				JSONUtilities.getJsonElementValue(requestJSON, 
						ACEGFSElementsPaths.getGFSElementsPaths().getGFSElementPath(elementName)));
	}
	
	private void setValue(String fieldName, String fieldValue) {
		requestMap.put(fieldName, fieldValue);		
	}
	
	public String getRequestElementValue(String fieldName) {
		return requestMap.get(fieldName);		
	}
	
	public String getRequestJSONStr() {
		return JSONStr;
	}
	
	public Map<String, String> getRequestMap() {
		return requestMap;
	}
	
	private static GFSGETRequestData currentRequest=null;
	
	public static GFSGETRequestData getCurrentRequest() {
		return currentRequest;
	}
	
	public static GFSGETRequestData getNewACEGFSRequest(String requestJSONFileName) {
		currentRequest=new GFSGETRequestData(requestJSONFileName);
		return currentRequest;
	}
	
	public static void clear() {
		currentRequest=null;
	}
	public String getValue(String fieldName) {
		return requestMap.get(fieldName);
	}
	
	public static GFSGETRequestData createACEGFSRequestFromDBDoc() {
		GFSDBBase dbACEAPIRequestData=GFSDB.getCurrentGFSDBDocuments().getDbRequestACE();
		if(dbACEAPIRequestData == null) {
			TestScenario.writeToScenario("ERROR: DB doc for ACEAPI Request is null");
			return null;
		}
		currentRequest=new GFSGETRequestData(dbACEAPIRequestData);
		return currentRequest;
	}
	
	public void updateACEGFSRequest(Map<String, String> dataMap) {
		DocumentContext jsonData=JsonPath.parse(JSONStr);
		for (Map.Entry<String, String> dataMapItem : dataMap.entrySet())
		{
			String elementName=dataMapItem.getKey().trim();
			String elementValue=dataMapItem.getValue();
			//System.out.println("#####element: "+elementName+"*");
			if(elementValue != null) {
				elementValue=elementValue.trim();
			}
			String jsonPath=ACEGFSElementsPaths.getGFSElementsPaths().getGFSElementPath(elementName);
			if(jsonPath == null || jsonPath == "") {
				System.out.println("#####jsonPath not found for element: "+elementName);
			}
			if(JSONUtilities.modifyValueAtJsonPath(jsonData, jsonPath, elementValue)) {
				currentRequest.setValue(elementName, elementValue);
			}
		}
		JSONStr=jsonData.jsonString();
	}

	@SuppressWarnings("unchecked")
	public static void setCurrentGETRequest(GFSDBBase dbRequestACE) {
		ObjectMapper mapper=new ObjectMapper();
		JSONObject jsonObject2=new JSONObject();
		if(dbRequestACE != null ) {
			String reqACE=dbRequestACE.getDBDoc().toJson().toString();
			JSONObject jsonObject1=null;
			try {
				jsonObject1 = mapper.readValue(reqACE, JSONObject.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(jsonObject1 !=null) {
				jsonObject2.putAll((Map<? extends String, ? extends Object>)jsonObject1.get("aceRequest"));
			}
			setCurrentGETRequest(jsonObject2.toJSONString());
			TestScenario.writeJSONToScenario(GFSGETRequestData.getCurrentRequest().getRequestJSONStr(), "GFS GET Request");
		}
		
	}

	private static void setCurrentGETRequest(String requestJSONStr) {
		currentRequest=new GFSGETRequestData();
		currentRequest.JSONStr=requestJSONStr;
		currentRequest.parseACEGFSRequestJSON();
		
	}	

}
