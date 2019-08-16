package com.seanfiles.helper;



import static org.junit.Assert.assertTrue;

import java.util.Map;

import com.seanfiles.helper.ACEAPI10Request;
import com.seanfiles.helper.Apigee;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.root.LoadTestConfig;
import com.seanfiles.utils.ReusableConstants;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ACEAPI10 {

	//public static String jsonResp;
	public static Response aceApiResponse = null;
	public static Response aceApiResponse20 = null;
	
	
	public static void submitACEAPIData(Map<String, String> dataMap) throws Throwable {
		String JSONStr=ACEAPI10Request.getNewACEAPIRequest(dataMap).getRequestJSONStr();		
		assertTrue("Not able to build JSON request", JSONStr != null);	
		TestScenario.writeToScenario("ACEAPI request JSON: "+JSONStr);
		submitACEAPI(JSONStr);
	}

	public static void submitACEAPI(String JSONStr) {
		String apigeeToken=Apigee.getAccessToken();
		assertTrue("No ApigeeToken", apigeeToken != null && apigeeToken.length() != 0);	
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = LoadTestConfig.getProperty("APIGEEAccessTokenBaseUrl");
		RestAssured.proxy(ReusableConstants.InternetProxyHost, ReusableConstants.InternetProxyPort);
		RestAssured.config = RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

		RequestSpecification reqspec = RestAssured.given().contentType(ReusableConstants.APPLICATION_JSON)
				.header("Authorization", "Bearer ".concat(apigeeToken)).body(JSONStr);
		reqspec.log().all();
		String Resourcepath = LoadTestConfig.getProperty("APIGEEACEAPIResourcePath");
		aceApiResponse = reqspec.when().post(Resourcepath);
		String jsonResp=aceApiResponse.then().extract().asString();
		System.out.println("Response " + jsonResp);
		TestScenario.writeToScenario("ACEAPI Response is :" + jsonResp);
		aceApiResponse.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
	}

	public static String getJsonResponse() {		
		aceApiResponse.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
			
			String jsonResp=aceApiResponse.then().extract().asString();
			TestScenario.writeJSONToScenario(jsonResp, "ACEAPI2.0 Response");
		return jsonResp;
	}
	
	public static void submit20(String fileName) {
		String body = System.getProperty("user.dir").concat(TestConfig.getProperty("ACEAPI20ReqFiles").concat(fileName));
		System.out.println("aaaaaaaaaaaa" + body);
		//RestAssured.reset();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = TestConfig.getProperty("ACEAPI20_BaseUrl");
		RequestSpecification reqspec = RestAssured.given().auth().preemptive().basic(TestConfig.getProperty("ACEAPI20_Username"), TestConfig.getProperty("ACEAPI20_Password")).contentType("application/json")
				.body(body);
		reqspec.log().all();
		String Resourcepath = TestConfig.getProperty("ACEAPI20_ResourcePath");
		aceApiResponse20 = reqspec.when().post(Resourcepath);
		String jsonResp=aceApiResponse20.then().extract().asString();
		System.out.println("Response " + jsonResp);
		TestScenario.writeToScenario("ACEAPI Response is :" + jsonResp);
		aceApiResponse20.then().assertThat().statusCode(200);
		
	}
	
	public static String getJsonResponse20() {
		aceApiResponse20.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
		
		String jsonResp=aceApiResponse20.then().extract().asString();
		TestScenario.writeJSONToScenario(jsonResp, "ACEAPI2.0 Response");
	return jsonResp;
		
	}
	
	
}
	