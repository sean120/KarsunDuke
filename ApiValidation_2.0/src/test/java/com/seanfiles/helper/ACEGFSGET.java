package com.seanfiles.helper;

import static com.seanfiles.elements.ACEGFSElements.*;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import com.seanfiles.services.GFSGETRequestData;
import com.seanfiles.services.GFSGETResponseData;
import com.seanfiles.utils.ReusableConstants;

import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ACEGFSGET {
	
	private static Response GFSGETResponse=null;

	public static void submitGETRequest(Map<String, String> dataMap) {
		setGFSGETRequest("GFS-GET-Request.json", dataMap);
		GFSGETResponseData.clear();
		submitGFSGET();
		getGETResponse();
	}

	private static void setGFSGETRequest(String GFSGETTempl, Map<String, String> dataMap) {
		String JSONStr=GFSGETRequestData.getNewACEGFSRequest(GFSGETTempl).getRequestJSONStr();
		assertTrue("Not able to read JSON file", JSONStr != null);	
		GFSGETRequestData.getCurrentRequest().updateACEGFSRequest(dataMap);		
	}
	
	public static void submitGFSGET() {
		String JSONStr=GFSGETRequestData.getCurrentRequest().getRequestJSONStr();
		TestScenario.writeJSONToScenario(JSONStr, "GFS GET request payload JSON");
		RestAssured.reset();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = TestConfig.getProperty("ACEGFS_BaseUrl");
		RequestSpecification reqspec = RestAssured.given().auth().preemptive().basic(TestConfig.getProperty("ACEGFS_Username"), TestConfig.getProperty("ACEGFS_Password")).param("compositeRequestJson", JSONStr);
		//reqspec.log().all();
		String Resourcepath = TestConfig.getProperty("ACEGFS_GET_ResourcePath");
		TestScenario.writeToScenario("ACEGFS GET request URL: "+TestConfig.getProperty("ACEGFS_BaseUrl")+TestConfig.getProperty("ACEGFS_GET_ResourcePath"));
		GFSGETResponse = reqspec.when().get(Resourcepath);		
	}

	private static void getGETResponse() {
		GFSGETResponse.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
		
		String jsonResp=GFSGETResponse.then().extract().asString();
		TestScenario.writeJSONToScenario(jsonResp, "GFS GET Response");
		GFSGETResponseData.setCurrentGETResponse(jsonResp);
	}
}
