package com.seanfiles.stepdefinition;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.seanfiles.helper.ACEAPIHelperMethods;
import com.seanfiles.helper.TestConfig;
import com.seanfiles.root.LoadTestConfig;
import com.seanfiles.utils.ReusableConstants;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ACEAPI2_0HealthCheck {

	private static Logger log = Logger.getLogger(ACEAPI2_0HealthCheck.class);
	public Scenario scenario;
	public static Response response=null;
	Properties props;
	public LoadTestConfig aceapiconfig;
	public static ACEAPIHelperMethods helper;
	public RequestSpecification reqspec = null;
	
	String requestJSONStr;
	
	@Before
	public void loadTestScenario(Scenario scenario) {
	this.scenario=scenario;
	this.aceapiconfig = new LoadTestConfig();
	this.props = aceapiconfig.loadProperties();
	ACEAPIHelperMethods helper = new ACEAPIHelperMethods();
	}
	

	@Given("^Webservice URL for ACE API (\\d+)\\.(\\d+) in the Properties file and payload \"([^\"]*)\"$")
	   
	   public void Given_URL (Integer n, Integer b, String filename) throws IOException{
		RestAssured.reset();
		RestAssured.baseURI = props.getProperty("sitACE2_0BaseUrl");
		RestAssured.useRelaxedHTTPSValidation();
		requestJSONStr = helper.generateStringFromResource(System.getProperty("user.dir")
				.concat((props.getProperty("sitRequestFiles").concat(filename))));
		
	}

	@When("^Invoke GET method on ACE API (\\d+)\\.(\\d+) healthcheck URL$")
	   public void Invoke_GET_Method(Integer a, Integer b) throws Throwable{
	   
		scenario.write("ACE API 2.0 HealthCheck Request is : "+ props.getProperty("sitACE2_0BaseUrl"));
		String url =  props.getProperty("ACE2_0ResourcePath");
		log.info("**********End  ---  Invoke POST method on ACE API 2.0 URL");
	    log.info("**********Payload content :"+ "\n"+ requestJSONStr );
		reqspec = RestAssured.given().auth().preemptive().basic(TestConfig.getProperty("ACEAPI20_Username"), TestConfig.getProperty("ACEAPI20_Password")).contentType("application/json").
				body(requestJSONStr);
		response = reqspec.when().post(url);
        
		System.out.println(response.asString());
		
		scenario.write("GET Response status code: "+ response.statusCode());
		log.info("**********End --- Invoke POST method on ACE API2.0 URL *********");
	}

	@Then("^User validates the response code returned by ACE API (\\d+)\\.(\\d+) matches the expected value (\\d+)$")
	    public void Validate_Response_Code (Integer a, Integer b, Integer responseCode) throws Throwable{
		response.then().assertThat().statusCode(responseCode);
		
	    
	}
	
	
	
}
