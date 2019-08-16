package com.seanfiles.helper;

import static com.seanfiles.services.BaseData.*;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import com.seanfiles.db.ACECheckAPIDB;
import com.seanfiles.db.GFSDB;
import com.seanfiles.elements.ACEElements;
import com.seanfiles.services.ACECheckAPIAARequestData;
import com.seanfiles.services.ACECheckAPIAAResponseData;
import com.seanfiles.services.ACECheckAPIDerivedData;
import com.seanfiles.services.ACECheckAPIHVERequestData;
import com.seanfiles.services.ACECheckAPIHVEResponseData;
import com.seanfiles.services.ACECheckAPIRequestData;
import com.seanfiles.services.ACECheckAPIResponseData;
import com.seanfiles.utils.ReusableConstants;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ACECheckAPI {
	
	public static Response aceApiResponse = null;
	
	public static void submitACEAPIRequestFile(String JSONFileName) {
		clearServiceData();
		String JSONStr=ACECheckAPIRequestData.getNewACEAPIRequest(JSONFileName).getRequestJSONStr();
		assertTrue("Not able to read JSON file", JSONStr != null);	
		TestScenario.writeToScenario("ACEAPI request JSON: "+JSONStr);
		submitACEAPI(JSONStr);
	}

	public static void createFromDBDoc(String type) {
		switch(type) {
		case ACEAPI_Request:
			ACECheckAPIRequestData.createACEAPI10RequestFromDBDoc();
			break;
		case ACEAPI_Response:
			ACECheckAPIResponseData.createACEAPI10ResponseFromDBDoc();
			break;
		}
	}

	public static void submitACEAPIData(Map<String, String> dataMap) throws Throwable {
		clearServiceData();
		String JSONStr=ACECheckAPIRequestData.getNewACEAPIRequest(dataMap).getRequestJSONStr();		
		assertTrue("Not able to build JSON request", JSONStr != null);	
		TestScenario.writeToScenario("ACEAPI request JSON: "+JSONStr);
		submitACEAPI(JSONStr);
		getACECheckAPIResponse();
	}

	public static void submitACECheckAPIData(Map<String, String> dataMap) throws Throwable {
		clearServiceData();
		String JSONStr=ACECheckAPIRequestData.getNewACEAPIRequest(dataMap).getRequestJSONStr();		
		assertTrue("Not able to build JSON request", JSONStr != null);	
		TestScenario.writeToScenario("ACEAPI request JSON: "+JSONStr);
		submitACEAPI(JSONStr);
	}
	
	public static void submitACECheckAPINoAPIGEE(Map<String, String> dataMap) throws Throwable {
		clearServiceData();
		String JSONStr=ACECheckAPIRequestData.getNewACEAPIRequest(dataMap).getRequestJSONStr();		
		assertTrue("Not able to build JSON request", JSONStr != null);	
		TestScenario.writeToScenario("ACEAPI request JSON: "+JSONStr);
		submitACEAPINoAPIGEE(JSONStr);
		getACECheckAPIResponse();
	}
	
	public static void submitACECheckAPINoAPIGEEData(Map<String, String> dataMap) throws Throwable {
		clearServiceData();
		String JSONStr=ACECheckAPIRequestData.getNewACEAPIRequest(dataMap).getRequestJSONStr();		
		assertTrue("Not able to build JSON request", JSONStr != null);	
		TestScenario.writeToScenario("ACEAPI request JSON: "+JSONStr);
		submitACEAPINoAPIGEE(JSONStr);
	}
	
	public static void setACEAPIRequest(Map<String, String> dataMap) throws Throwable {
		clearServiceData();
		String JSONStr=ACECheckAPIRequestData.getNewACEAPIRequest(dataMap).getRequestJSONStr();		
		assertTrue("Not able to build JSON request", JSONStr != null);	
		TestScenario.writeToScenario("ACEAPI request JSON: "+JSONStr);
	}
	
	
	private static String getSellerID() {
		String sellerID=null;
		ACECheckAPIRequestData currrentRequest=ACECheckAPIRequestData.getCurrentRequest();
		if(currrentRequest != null) {
			sellerID=currrentRequest.getValue(ACEElements.PartyRoleIdentifier);
		}
		return sellerID;
	}

	private static void submitACEAPI(String JSONStr) {
		String apigeeToken=null;
		String sellerID=getSellerID();
		if(sellerID != null) {
			apigeeToken=ACECheckAPIGEE.getAccessToken(sellerID);
			TestScenario.writeToScenario("APIGEE access token for sellerID: "+sellerID+" is: "+apigeeToken);
		}
		else {
			apigeeToken=ACECheckAPIGEE.getAccessToken();
			TestScenario.writeToScenario("APIGEE access token is: "+apigeeToken);
		}
		assertTrue("No ApigeeToken", apigeeToken != null && apigeeToken.length() != 0);	
		
		RestAssured.reset();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = TestConfig.getProperty("ACECheckAPIBaseUrl");
		RestAssured.proxy(ReusableConstants.InternetProxyHost, ReusableConstants.InternetProxyPort);
		RestAssured.config = RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

		RequestSpecification reqspec = RestAssured.given().contentType(ReusableConstants.APPLICATION_JSON)
				.header("Authorization", "Bearer ".concat(apigeeToken)).body(JSONStr);
		reqspec.log().all();
		String Resourcepath = TestConfig.getProperty("ACECheckAPIResourcePath");
		TestScenario.writeToScenario("ACE Check API request URL: "+TestConfig.getProperty("ACECheckAPIBaseUrl")+TestConfig.getProperty("ACECheckAPIResourcePath"));
		aceApiResponse = reqspec.when().post(Resourcepath);
	}
	
	private static void submitACEAPINoAPIGEE(String JSONStr) {
		RestAssured.reset();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = TestConfig.getProperty("ACECheckAPINoAPIGEEBaseUrl");
		RestAssured.config = RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

		RequestSpecification reqspec = RestAssured.given().contentType(ReusableConstants.APPLICATION_JSON)
				.header("X-Forwarded-UserName", TestConfig.getProperty("ACECheckAPINoAPIGEEUserName") ).body(JSONStr);
		reqspec.log().all();
		String resourcepath = TestConfig.getProperty("ACECheckAPINoAPIGEEResourcePath");
		TestScenario.writeToScenario("ACE Check API (No APIGEE) request URL: "+TestConfig.getProperty("ACECheckAPINoAPIGEEBaseUrl")+TestConfig.getProperty("ACECheckAPINoAPIGEEResourcePath"));
		aceApiResponse = reqspec.when().post(resourcepath);
	}
	
	public static void getACECheckAPIResponse() {
		aceApiResponse.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
		
		String jsonResp=aceApiResponse.then().extract().asString();
		TestScenario.writeJSONToScenario(jsonResp, "ACE Check API Response");
		ACECheckAPIResponseData.setACEAPIResponse(jsonResp);
	}
	
	public static void checkACEAPIReturnCode(int expectedReturnCode) {
		aceApiResponse.then().assertThat().statusCode(expectedReturnCode);
		TestScenario.writeToScenario("ACE Check API returned HTTPS status code is "+aceApiResponse.statusCode());
		String jsonResp=aceApiResponse.then().extract().asString();
		TestScenario.writeJSONToScenario(jsonResp, "ACE Check API Response");
		ACECheckAPIResponseData.setACEAPIResponse(jsonResp);
	}

	public static void clearServiceData() {
		ACECheckAPIRequestData.clear();
		ACECheckAPIDB.clear();
		GFSDB.clearDBDocs();
		ACECheckAPIDerivedData.clear();
		ACECheckAPIResponseData.clear();
		ACECheckAPIHVERequestData.clear();
		ACECheckAPIHVEResponseData.clear();
		ACECheckAPIAARequestData.clear();
		ACECheckAPIAAResponseData.clear();
	}

}
