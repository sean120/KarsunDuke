package com.seanfiles.stepdefinition;

import static io.restassured.RestAssured.when;

import java.awt.Desktop;
import java.net.URL;
import java.util.Properties;

import com.seanfiles.helper.ACEAPIHelperMethods;
import com.seanfiles.root.LoadTestConfig;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ACEAPIHealthCheck {

	// ================================Framework SETUP --
	// START================================\\
	public Scenario scenario;
	public Properties p;
	public ACEAPIHelperMethods req;
	public LoadTestConfig Start;

	@Before
	public void keepScenario(Scenario scenario) {
		this.scenario = scenario;
		this.Start = new LoadTestConfig();
		this.p = Start.loadProperties();
		this.req = new ACEAPIHelperMethods();
	}

	@After
	public void Teardown() {

		try {
			String htmlreport = System.getProperty("user.dir")
					+ "\\target\\cucumber-report-html\\cucumber-html-reports\\feature-overview.html";
			Desktop.getDesktop().browse(new URL(htmlreport).toURI());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	// ================================Framework SETUP --
	// stop================================\\

	public Response response;
	public RequestSpecification request;
	public RequestSpecification requestSpec;
	
	/**
	 * ACE API Health Check URL
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author 
	 * @throws Exception
	 */

	@Given("^ACE API Health Check URL$")
	public void a_working_URL_to_Submit_requests_to() throws Exception {

	}
	
	/**
	 * Invoke the HEalth CHeck URL
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author 
	 * @throws
	 */

	@When("^Invoke the Health Check URL$")
	public void user_submits_request() {
		response = when().get(p.getProperty("HealthCheckURL"));
	}
	
	/**
	 * Receiving HTTP Status code
	 * 
	 * 
	 * @param int statusCode
	 * 
	 * @return 
	 * @author 
	 * @throws 
	 */

	@Then("^user receives HTTP Status code (\\d+)$")
	public void verify_status_code(int statusCode) {
		System.out.println("The Status code " + statusCode);
		response.then().statusCode(statusCode);
	}
	
	/**
	 * Verify that response has status
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author 
	 * @throws
	 */

	@Then("^response body has status$")
	public void verify_resoponse_status() {

	}

}
