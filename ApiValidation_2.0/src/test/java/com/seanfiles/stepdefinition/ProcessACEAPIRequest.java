package com.seanfiles.stepdefinition;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.seanfiles.helper.ACEAPIHelperMethods;
import com.seanfiles.root.LoadTestConfig;
import com.seanfiles.utils.ElementJsonPaths;
import com.seanfiles.utils.Messages;
import com.seanfiles.utils.ReusableConstants;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProcessACEAPIRequest {

	private static Logger log = Logger.getLogger(ProcessACEAPIRequest.class);
	public Scenario scenario;
	public Properties props;
	private String operation;
	private String jsonrequest;
	private String tctype;
	public ACEAPIHelperMethods aceapimethods;
	public LoadTestConfig aceapiconfig;
	private String UserName = "";
	private String Password = "";
	private String ProxyMSGID = "";
	private ContentType contentType = ContentType.JSON;
	private String contType = "application/json";
	protected RequestSpecification reqspec;

	// ================================Framework_SETUP================================\\

	@Before
	public void loadTestScenario(Scenario scenario) {
		this.scenario = scenario;
		this.aceapiconfig = new LoadTestConfig();
		this.props = aceapiconfig.loadProperties();
		this.aceapimethods = new ACEAPIHelperMethods();
	}

	// @After
	// public void Teardown() {
	// try {
	// String htmlreport = System.getProperty("user.dir")
	// .concat("\\target\\cucumber-report-html\\cucumber-html-reports\\feature-overview.html");
	// Desktop.getDesktop().browse(new URL(htmlreport).toURI());
	// } catch (Exception ex) {
	// log.error(ex.getMessage());
	// }
	// }

	// ================================Framework_SETUP================================\\

	public Response response;

	/**
	 * Prepare a valid request by replacing the valid values for User name,
	 * proxymsgid and requestfiletype
	 * 
	 * 
	 * @param String
	 *            requestfiletype , String username and String proxymsgid
	 * 
	 * @return Request Specification
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 * @throws IOException
	 */

	@Given("^Valid JSON \"([^\"]*)\" ACE API request with \"([^\"]*)\" and \"([^\"]*)\" passed as header$")
	public RequestSpecification aceapirequest(String requestfiletype, String username, String proxymsgid)
			throws IOException {

		log.info("*********** in GIVEN  ---   Preparing the ACE API Request  *****************");

		jsonrequest = aceapimethods.generateStringFromResource(
				System.getProperty("user.dir").concat(props.getProperty("aceapirequestfile")));
		this.UserName = username;
		this.ProxyMSGID = proxymsgid;
		this.contentType = aceapimethods.getcontenttype(contType);
		RestAssured.baseURI = props.getProperty("ACEAPIBaseUrl");
		RestAssured.useRelaxedHTTPSValidation();
		reqspec = RestAssured.given().contentType(contentType).header("X-Forwarded-UserName", UserName).and()
				.header("X-Forwarded-ProxyMessageId", ProxyMSGID).body(jsonrequest);
		reqspec.log().all();
		return reqspec;

	}
	
	/**
	 * Prepare a valid request by replacing the valid values for User name,
	 * requestUrl and password
	 * 
	 * 
	 * @param String
	 *            requestUrl , String username and String password
	 * 
	 * @return Request Specification
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 * @throws IOException
	 */

	@Given("^A Valid url \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" we send a request to apagee in order to receive the token$")
	public RequestSpecification apegeeRequestToken(String requestUrl, String username, String password)
			throws IOException {

		log.info("*********** in GIVEN  ---   Preparing the ACE API Request  *****************");

		jsonrequest = aceapimethods.generateStringFromResource(
				System.getProperty("user.dir").concat(props.getProperty("aceapirequestfile")));
		this.UserName = username;
		this.Password = password;
		this.contentType = aceapimethods.getcontenttype(requestUrl);
		RestAssured.baseURI = props.getProperty("apageeBaseUri");
		RestAssured.useRelaxedHTTPSValidation();
		reqspec = RestAssured.given().contentType(contentType).formParam("grant_type", "password")
				.formParam("client_id", "m3qmWiMPGc1egD0py4KcDp1aTqCpKPO7")
				.formParam("client_secret", "TFLuyrXWf3n16GCE").formParam("username", username)
				.formParam("password", password);
		reqspec.log().all();
		return reqspec;

	}
	
	/**
	 * Prepare a valid request by replacing the valid values for User name,
	 * requestIrl and requestfiletype
	 * 
	 * 
	 * @param String
	 *            requestfiletype , String username and String requestUrl
	 * 
	 * @return Request Specification
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 * @throws IOException
	 */

	@Given("^Valid url \"([^\"]*)\" and valid JSON \"([^\"]*)\" ACE API request with \"([^\"]*)\" and \"([^\"]*)\" passed as header$")
	public RequestSpecification aceapirequestAnyURL(String requestUrl, String requestfiletype, String username,
			String proxymsgid) throws IOException {

		log.info("*********** in GIVEN  ---   Preparing the ACE API Request  *****************");

		jsonrequest = aceapimethods.generateStringFromResource(
				System.getProperty("user.dir").concat(props.getProperty("aceapirequestfile")));
		this.UserName = username;
		this.ProxyMSGID = proxymsgid;
		this.contentType = aceapimethods.getcontenttype(contType);
		RestAssured.baseURI = props.getProperty(requestUrl);
		RestAssured.useRelaxedHTTPSValidation();
		reqspec = RestAssured.given().contentType(contentType).header("X-Forwarded-UserName", UserName).and()
				.header("X-Forwarded-ProxyMessageId", ProxyMSGID).body(jsonrequest);
		reqspec.log().all();
		return reqspec;

	}
	
	/**
	 * Prepare a invalid request by replacing the values for User name,
	 * proxymsgid and requestfiletype
	 * 
	 * 
	 * @param String
	 *            requestfiletype , String username and String proxymsgid
	 * 
	 * @return Request Specification
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 * @throws IOException
	 */

	@Given("^invalid JSON \"([^\"]*)\" ACE API request with \"([^\"]*)\" and \"([^\"]*)\" passed as header$")
	public RequestSpecification aceapiinvalidrequest(String requestfiletype, String username, String proxymsgid)
			throws IOException {

		log.info("*********** in GIVEN  ---   Preparing the ACE API Request  *****************");

		jsonrequest = aceapimethods.generateStringFromResource(
				System.getProperty("user.dir").concat(props.getProperty("aceapirequestfile")));
		this.UserName = username;
		this.ProxyMSGID = proxymsgid;
		this.contentType = aceapimethods.getcontenttype(contType);
		RestAssured.baseURI = props.getProperty("ACEAPIBaseUrl");
		RestAssured.useRelaxedHTTPSValidation();
		reqspec = RestAssured.given().contentType(contentType).header("X-Forwarded-UserName", UserName).and()
				.header("X-Forwarded-ProxyMessageId", ProxyMSGID).body(jsonrequest);
		reqspec.log().all();
		return reqspec;

	}
	
	/**
	 * Executing certain test case
	 * 
	 * 
	 * @param String testcasetype
	 * 
	 * @return
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 * @throws
	 */

	@Given("^executing \"([^\"]*)\" test case$")
	public void typeoftesting(String testcasetype) {
		this.tctype = testcasetype;
	}
	
	/**
	 * Request is performing operation on ACE API
	 * 
	 * 
	 * @param String Op
	 * 
	 * @return response
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 * @throws
	 */

	@When("^Request is performing \"([^\"]*)\" on ACE API$")
	public Response post_Request_toACEAPIService(String Op) {
		log.info("*********** in WHEN  ---   posting the request to ACE API URL  *****************  ");
		String Resourcepath = props.getProperty("ACEAPIResourcePath");
		this.operation = Op;
		log.info("Operation is :" + operation);
		if (operation.equalsIgnoreCase(ReusableConstants.GET)) {
			scenario.write("Performing GET Operation");
			response = reqspec.when().get(Resourcepath);
		}
		if (operation.equalsIgnoreCase(ReusableConstants.POST)) {
			scenario.write("Performing POST Operation");
			response = reqspec.when().post(Resourcepath);
			log.info("Heelllo i am in POST Operation completed");

		}
		log.info("*********** in WHEN  ---   Done with posting the request to ACE API URL  *****************  ");
		return response;
	}
	
	/**
	 * Request is performing operation on Apagee Gateway
	 * 
	 * 
	 * @param String Op
	 * 
	 * @return response
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 * @throws
	 */

	@When("^Request is performing \"([^\"]*)\" on Apagee Gateway$")
	public Response post_Request_toApageeService(String Op) {
		log.info("*********** in WHEN  ---   posting the request to ACE API URL  *****************  ");
		String Resourcepath = props.getProperty("apageeResourcePath");
		this.operation = Op;
		log.info("Operation is :" + operation);
		if (operation.equalsIgnoreCase(ReusableConstants.GET)) {
			scenario.write("Performing GET Operation");
			response = reqspec.when().get(Resourcepath);
		}
		if (operation.equalsIgnoreCase(ReusableConstants.POST)) {
			scenario.write("Performing POST Operation");
			response = reqspec.when().post(Resourcepath);
			log.info("Heelllo i am in POST Operation completed");

		}
		log.info("*********** in WHEN  ---   Done with posting the request to ACE API URL  *****************  ");
		return response;
	}

	/**
	 * Check the ace api service response code and body
	 * 
	 * @param
	 * @return
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 */

	@Then("^Service sends back responsecode$")
	public void validateResponse() {
		if (tctype.equalsIgnoreCase("positive")) {
			log.info("In Positive validate Response");
			scenario.write("response code received is :" + response.getStatusCode());
			if (response != null) {
				scenario.write(response.then().extract().asString());
				log.info(" ACE API response is : " + response.then().extract().asString());
			}
			response.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
		}
		if (tctype.equalsIgnoreCase("negative")) {
			log.info("In negative validate Response");
			scenario.write("negative response code received is :" + response.getStatusCode());
			if (response != null) {
				scenario.write(response.then().extract().asString());
				log.info(" ACE API response is : " + response.then().extract().asString());
			}
			response.then().assertThat()
					.statusCode(aceapimethods.contains(ReusableConstants.HTTPERRORCODE, response.getStatusCode()));
		}
	}

	/**
	 * Validate any value on JSON response
	 * 
	 * @param
	 * @return
	 * @author C46063 - Dmitriy - LAS - ACE API
	 */

	@Then("^User sees the expected \"([^\"]*)\" value in the response$")
	public void validateValueFromResponse(String expectedValue) {
		String responseAsString = "";

		if (response != null) {
			scenario.write(response.then().extract().body().asString());
			log.info(" ACE API response is : " + response.then().extract().asString());
			responseAsString = response.getBody().asString();
		}
		Assert.assertEquals(responseAsString.contains(expectedValue), true);
	}

	/**
	 * Validate any message text in JSON response according to messageCodeName
	 * 
	 * @param String messageCodename
	 * @return
	 * @author C46063 - Dmitriy - LAS - ACE API
	 * @throws
	 */

	@Then("^User sees the expected message code \"([^\"]*)\" and associated text in the response$")
	public void validateMessageCodeResponse(String messageCodeName) {
		String responseAsString = "";
		String messageText = "";

		Messages messages = new Messages();
		messageText = messages.returnMessageText(messageCodeName);

		if (response != null) {
			scenario.write(response.then().extract().body().asString());
			log.info(" ACE API response is : " + response.then().extract().asString());
			responseAsString = response.getBody().asString();
		}
		log.info("Message code is : " + messageCodeName + " - " + messageText);
		Assert.assertEquals(responseAsString.contains(messageCodeName + " - " + messageText), true);
	}
	
	/**
	 * Validate value in JSON response according to expected value
	 * 
	 * @param String elementName, String expectedValue
	 * @return
	 * @author C46063 - Dmitriy - LAS - ACE API
	 * @throws
	 */

	@Then("Validate that the JSON response value \"([^\"]*)\" equals \"([^\"]*)\"$")
	public void validateJsonPathExact(String elementName, String expectedValue) {
		log.info("In the validate json received values method");

		ElementJsonPaths elementJasonPaths = new ElementJsonPaths();
		String jsonResponse = response.then().extract().asString();
		String jsonPath = elementJasonPaths.returnResponseElementJasonPath(elementName);
		ReadContext JSONContext = JsonPath.parse(jsonResponse);
		String retrievedValue = JSONContext.read(jsonPath);
		scenario.write(jsonPath + "    value is :  " + retrievedValue);
		if (!expectedValue.equals("NoValue")) {
			Assert.assertTrue(retrievedValue.equals(expectedValue));
		}
	}

}
