package com.seanfiles.helper;

import org.apache.log4j.Logger;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.root.LoadTestConfig;
import com.seanfiles.utils.ReusableConstants;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Apigee {

	public static final String apigeeContentType = "application/x-www-form-urlencoded";
	public static final String apigeeGrantType = "password";
	public static final String apigeeClientID = "QnqaRPWTQtgManGMEgXdq4opZXjW7WS7";
	public static final String apigeeClientSecret = "6Wu1GMd5zGOrShMy";
	// for sellerID 121898
//	public static final String apigeeUserName = "baofam_lasapisys2sys4";
//	public static final String apigeePassword = "fJ6%@9o=[EwB2Pr$VjUp";
	
	// for sellerID 000601
	public static final String apigeeUserName = "peb_lasapisys2sys";
	public static final String apigeePassword = "PeEbpv~0R@$8gYux(UaF";
	public static final String apigeeAccessTokenPath = "$.access_token";
	
	private static String accessToken=null;
	
	public static String getAccessToken() {
		if(accessToken == null) {
			getNewAccessToken();
		}
		return accessToken;
	}

	public static String getNewAccessToken() {
		accessToken=generateNewAccessToken();
		return accessToken;
	}

	private static String generateNewAccessToken() {
		RestAssured.baseURI = LoadTestConfig.getProperty("APIGEEAccessTokenBaseUrl");
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.proxy(ReusableConstants.InternetProxyHost, ReusableConstants.InternetProxyPort);
		RequestSpecification reqspec = RestAssured.given().contentType(apigeeContentType).formParam("grant_type", apigeeGrantType)
				.formParam("client_id", apigeeClientID).formParam("client_secret", apigeeClientSecret)
				.formParam("username", apigeeUserName).formParam("password", apigeePassword);
		reqspec.log().all();
		String Resourcepath = LoadTestConfig.getProperty("APIGEEAccessTokenResourcePath");
		Response apigeeResponse = reqspec.when().post(Resourcepath);
		apigeeResponse.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
		String jsonResp = apigeeResponse.then().extract().asString();
		TestScenario.writeToScenario("Apigee Response is :" + jsonResp);
		ReadContext JSONContext = JsonPath.parse(jsonResp);
		String accessTokenInResponse = JSONContext.read(apigeeAccessTokenPath);
		return accessTokenInResponse;
	}
	
	
	
	
}
