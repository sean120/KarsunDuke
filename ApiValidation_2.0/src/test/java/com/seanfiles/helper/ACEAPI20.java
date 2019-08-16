package com.seanfiles.helper;

import static com.seanfiles.services.BaseData.*;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import com.seanfiles.db.ACEAPI20DB;
import com.seanfiles.db.GFSDB;
import com.seanfiles.services.ACEAPI20AARequestData;
import com.seanfiles.services.ACEAPI20AAResponseData;
import com.seanfiles.services.ACEAPI20DerivedData;
import com.seanfiles.services.ACEAPI20HVERequestData;
import com.seanfiles.services.ACEAPI20HVEResponseData;
import com.seanfiles.services.ACEAPI20RequestData;
import com.seanfiles.services.ACEAPI20ResponseData;
import com.seanfiles.services.GFSGETResponseData;
import com.seanfiles.utils.ReusableConstants;

import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ACEAPI20 {
	

	
	private static Response aceApi20Response=null;

	public static void setACEAPI20RequestFile(String JSONFileName) {
		clearServiceData();
		String JSONStr=ACEAPI20RequestData.getNewACEAPI20Request(JSONFileName).getRequestJSONStr();
		assertTrue("Not able to read JSON file", JSONStr != null);	
		TestScenario.writeJSONToScenario(JSONStr, "ACEAPI2.0 request payload in the file");
	}

	public static void submitACEAPI20() {		
		String JSONStr=ACEAPI20RequestData.getCurrentRequest().getRequestJSONStr();		
		TestScenario.writeJSONToScenario(JSONStr, "ACEAPI2.0 request payload JSON");
		RestAssured.reset();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = TestConfig.getProperty("ACEAPI20_BaseUrl");
		RequestSpecification reqspec = RestAssured.given().auth().preemptive().basic(TestConfig.getProperty("ACEAPI20_Username"), TestConfig.getProperty("ACEAPI20_Password")).contentType(ReusableConstants.APPLICATION_JSON)
				.body(JSONStr);
	//	reqspec.log().all();
		String Resourcepath = TestConfig.getProperty("ACEAPI20_ResourcePath");
		TestScenario.writeToScenario("ACEAPI2.0 request URL: "+TestConfig.getProperty("ACEAPI20_BaseUrl")+TestConfig.getProperty("ACEAPI20_ResourcePath"));
		aceApi20Response = reqspec.when().post(Resourcepath);		
	}
	
	public static void clearServiceData() {
		ACEAPI20DerivedData.clear();
		ACEAPI20RequestData.clear();
		ACEAPI20DB.clear();
		GFSDB.clearDBDocs();
		ACEAPI20ResponseData.clear();
		ACEAPI20HVERequestData.clear();
		ACEAPI20HVEResponseData.clear();
		ACEAPI20AARequestData.clear();
		ACEAPI20AAResponseData.clear();
		GFSGETResponseData.clear();
		//GFSPOSTResponseData.clear();
		//GFSGETRequestData.clear();
		//GFSPOSTRequestData.clear();
	}

	public static void getACEAPI20Response() {
		aceApi20Response.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
		
		String jsonResp=aceApi20Response.then().extract().asString();
		TestScenario.writeJSONToScenario(jsonResp, "ACEAPI2.0 Response");
		
		ACEAPI20ResponseData.setACEAPI20Response(jsonResp);
	}

	public static void checkACEAPIReturnCode(int expectedReturnCode) {
		aceApi20Response.then().assertThat().statusCode(expectedReturnCode);
		if(expectedReturnCode == ReusableConstants.HTTPSUCCESSCODE) {
			String jsonResp=aceApi20Response.then().extract().asString();
			TestScenario.writeJSONToScenario(jsonResp, "ACEAPI2.0 Response");
			
			ACEAPI20ResponseData.setACEAPI20Response(jsonResp);
		}
		else {
			TestScenario.writeToScenario("ACEAPI20 returned HTTPS status code is "+aceApi20Response.statusCode());
		}
	}

	public static void createFromDBDoc(String type) {
		switch(type) {
		case ACEAPI20_Request:
			ACEAPI20RequestData.createACEAPI20RequestFromDBDoc();
			break;
		case ACEAPI20_Response:
			ACEAPI20ResponseData.createACEAPI20ResponseFromDBDoc();
			break;
		}
	}

	public static void updateACEAPI20Request(Map<String, String> dataMap) {
		ACEAPI20RequestData.getCurrentRequest().updateACEAPI20Request(dataMap);
		
	}

	public static void updateACEAPI20RequestWithExcelData(Map<String, String> excelDataMap) {
		ACEAPI20RequestData.getCurrentRequest().updateACEAPI20RequestWithExcelData(excelDataMap);		
	}


}
