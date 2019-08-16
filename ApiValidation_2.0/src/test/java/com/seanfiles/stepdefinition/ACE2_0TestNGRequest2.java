package com.seanfiles.stepdefinition;


import java.util.Properties;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.seanfiles.helper.ACEAPIHelperMethods;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.root.LoadTestConfig;
import com.seanfiles.stepdefinition.EndPointsACE2_0TestNGRequest3;
import com.seanfiles.utils.ElementJsonPaths;
import com.seanfiles.utils.Messages;
import com.seanfiles.utils.ReusableConstants;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class ACE2_0TestNGRequest2 extends ACE2_0TestNGRequest {
String resourcePath= System.getProperty("user.dir")+ "\\src\\test\\resources\\RequestFiles\\ReqSchemaVal\\ACEAPIPurchaseAceEligibleUAT.json";	
	
	@Test
	public void myTest(){
		
		given().log().all()
		.body(resourcePath).basePath(baseURI)
		.when().post(EndPointsACE2_0TestNGRequest3.Ace1_0Api)
		.then();
		TestScenario.writeToScenario("ACEAPI request JSON: "+resourcePath);
		
		System.out.println("Api Connected!!!");
	
	}
	
	
	

}

	