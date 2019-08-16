package com.seanfiles.helper;

import java.util.HashMap;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.seanfiles.utils.ReusableConstants;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ACECheckAPIGEE {
	private static final String apigeeContentType = "application/x-www-form-urlencoded";
	private static final String apigeeGrantType = "password";
	private static final String apigeeAccessTokenPath = "$.access_token";
	
	private static ACECheckAPIGEE apigee=null;
	
	private Map<String, String> accessTokenMap=new HashMap<String, String>();
	private String sellerID;
	private String APIGEEUserName=null;
	private String APIGEEUserPassword=null;
	
	private String getAccessTokenFromMap() {
		return accessTokenMap.get(sellerID);
	}
	
	private void addAccessTokenToMap(String accessToken) {
		accessTokenMap.put(sellerID, accessToken);
	}
	
	public static String getAccessToken() {
		if(apigee == null) {
			apigee=new ACECheckAPIGEE();
		}
		String accessToken=apigee.getAccessTokenFromMap();
		if(accessToken == null) {
			accessToken=apigee.generateNewAccessToken();
			apigee.addAccessTokenToMap(accessToken);
		}
		return accessToken;
	}

	public static String getAccessToken(String sellerID) {
		if(apigee == null) {
			apigee=new ACECheckAPIGEE();
		}
		apigee.sellerID=sellerID;
		return getAccessToken();
	}

	private void setAPIGEEUserCredentials() {
		String propNameUser="APIGEEUserName";
		String propNamePassword="APIGEEUserPassword";
		
		APIGEEUserName=TestConfig.getProperty(propNameUser);
		APIGEEUserPassword=TestConfig.getProperty(propNamePassword);
		
		if(sellerID != null) {
			propNameUser=propNameUser+"_"+sellerID;
			propNamePassword=propNamePassword+"_"+sellerID;
			String sellerAPIGEEUserName=TestConfig.getProperty(propNameUser);
			String sellerAPIGEEUserPassword=TestConfig.getProperty(propNamePassword);
			if(sellerAPIGEEUserName != null) {
				APIGEEUserName=sellerAPIGEEUserName;
				APIGEEUserPassword=sellerAPIGEEUserPassword;
			}
		}
	}
	
	private String generateNewAccessToken() {
		RestAssured.reset();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = TestConfig.getProperty("ACECheckAPIBaseUrl");
		RestAssured.useRelaxedHTTPSValidation();
		setAPIGEEUserCredentials();
		RestAssured.proxy(ReusableConstants.InternetProxyHost, ReusableConstants.InternetProxyPort);
		RequestSpecification reqspec = RestAssured.given().contentType(apigeeContentType).formParam("grant_type", apigeeGrantType)
				.formParam("client_id", TestConfig.getProperty("APIGEEClientID")).formParam("client_secret", TestConfig.getProperty("APIGEEClientSecret"))
				.formParam("username", APIGEEUserName).formParam("password", APIGEEUserPassword);
		reqspec.log().all();
		String Resourcepath = TestConfig.getProperty("APIGEEAccessTokenResourcePath");
		TestScenario.writeToScenario("ACE Check API APIGEE request URL: "+TestConfig.getProperty("ACECheckAPIBaseUrl")+TestConfig.getProperty("APIGEEAccessTokenResourcePath"));
		Response apigeeResponse = reqspec.when().post(Resourcepath);
		apigeeResponse.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
		String jsonResp = apigeeResponse.then().extract().asString();
		TestScenario.writeToScenario("Apigee Response is :" + jsonResp);
		ReadContext JSONContext = JsonPath.parse(jsonResp);
		String accessTokenInResponse = JSONContext.read(apigeeAccessTokenPath);
		return accessTokenInResponse;
	}
}
