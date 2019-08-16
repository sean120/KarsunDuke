package com.seanfiles.stepdefinition;

import org.junit.Assert;
import org.junit.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

import org.junit.Test;

import com.seanfiles.helper.ACEAPIHelperMethods;

import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.codehaus.groovy.testng.*;

public class ACE2_0TestNGRequest {

	private static Logger log = Logger.getLogger(ACE2_0TestNGRequest.class);
	public static RequestSpecification Ace10_requestSpec;
	//public static RequestSpecification Ace10_requestSpec;
//	public static String requestFile= System.getProperty("user.dir")+"\\src\\test\\resources\\RequestFiles\\UATHVsRequestFile\\012_LPARefinanceAceEligible.json";
	public static ResponseSpecification responseSpec;
	
	public static String uat1_uri = "https://las-api-ace-uat03.ext-uat.ocp.paasfhlmcnp.com/las-ace-api";
	
	public static String UserName = "webslr1_lasapisys2sys";
			
	@BeforeClass
	
	public static void Setup(){
	Ace10_requestSpec = new RequestSpecBuilder()
			.setBaseUri(uat1_uri)
			.addHeader("UserName", UserName)
			.addHeader("Content-Type", "application/json")
			.addHeader("Accept", "application/json")
	
			.build();
	
	
	
	RestAssured.requestSpecification = Ace10_requestSpec;
	
	responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
	
	}
}	