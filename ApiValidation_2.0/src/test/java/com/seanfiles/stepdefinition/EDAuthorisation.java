package com.seanfiles.stepdefinition;

import static com.jayway.jsonpath.JsonPath.compile;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.junit.Assert.assertThat;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.seanfiles.helper.ACEAPIHelperMethods;
import com.seanfiles.root.LoadTestConfig;
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

public class EDAuthorisation {

	private static Logger log = Logger.getLogger(EDAuthorisation.class);
	public Scenario scenario;
	public Properties props;
	private String operation;
	private String tctype;
	public ACEAPIHelperMethods aceapimethods;
	public LoadTestConfig aceapiconfig;
	private String UserName = "";
	private String Password = "";
	private String Appln = "";
	private String PDI = "";
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

	private Response response;

	/**
	 * Prepare a valid request by replacing the valid values for User name
	 * password App and policyDirectorIdentifier
	 * 
	 * 
	 * @param String
	 *            Username Header , String Password Header ,String App , String
	 *            policyDirectorIdentifier
	 * @return Request Specification
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 */

	@Given("^Valid ACE API request with the \"([^\"]*)\" and \"([^\"]*)\" passed as header and the request has valid combination of app as \"([^\"]*)\" and policyDirectorIdentifier as \"([^\"]*)\"$")
	public RequestSpecification authorization_Request(String username, String password, String App,
			String policyDirectorIdentifier) {

		log.info("*********** in GIVEN  ---   Preparing the ED Authorisation Request  *****************");
		this.UserName = username;
		this.Password = password;
		this.Appln = App;
		this.PDI = policyDirectorIdentifier;
		RestAssured.reset();
		this.contentType = aceapimethods.getcontenttype(contType);
		RestAssured.baseURI = props.getProperty("EDAuthBaseUrl2");
		RestAssured.useRelaxedHTTPSValidation();
		reqspec = RestAssured.given().auth().preemptive().basic(UserName, Password).contentType(contentType)
				.param("app", Appln).param("policyDirectorIdentifier", PDI);
		return reqspec;

	}
	
	/**
	 * Prepare a invalid request by replacing the valid values for User name
	 * password App and policyDirectorIdentifier
	 * 
	 * 
	 * @param String
	 *            Username Header , String Password Header ,String App , String
	 *            policyDirectorIdentifier
	 * @return Request Specification
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 */

	@Given("^Invalid ACE API request with the \"([^\"]*)\" and \"([^\"]*)\" passed as header and the request has invalid combination of app as \"([^\"]*)\" and policyDirectorIdentifier as \"([^\"]*)\"$")
	public RequestSpecification unauthorization_Request(String username, String password, String App,
			String policyDirectorIdentifier) {

		log.info("*********** in GIVEN  ---   Preparing the ED Authorisation Invalid Request  *****************");
		this.UserName = username;
		this.Password = password;
		this.Appln = App;
		this.PDI = policyDirectorIdentifier;
		this.contentType = aceapimethods.getcontenttype(contType);
		RestAssured.baseURI = props.getProperty("EDAuthBaseUrl2");
		RestAssured.useRelaxedHTTPSValidation();
		reqspec = RestAssured.given().auth().preemptive().basic(UserName, Password).contentType(contentType)
				.param("app", Appln).param("policyDirectorIdentifier", PDI);
		return reqspec;

	}

	/**
	 * @param testcasetype
	 *            either positive or negative
	 * @return
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 */

	@Given("^executing \"([^\"]*)\" type of test case$")
	public void typeoftesting(String testcasetype) {
		this.tctype = testcasetype.trim();
	}

	/**
	 * Send the request to ED authorization service
	 * 
	 * @param Operation
	 * @return Response
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 */

	@When("^Request is performing \"([^\"]*)\" Sys-to-Sys service$")
	public Response post_Request_toEDService(String Op) {
		log.info("*********** in WHEN  ---   posting the request to ED URL  *****************  ");
		String Resourcepath = props.getProperty("EDAuthResourcePath");
		this.operation = Op;
		reqspec.log().all();
		if (operation.equalsIgnoreCase(ReusableConstants.GET)) {
			scenario.write("Performing GET Operation");
			response = reqspec.when().get(Resourcepath);
		}
		if (operation.equalsIgnoreCase(ReusableConstants.POST)) {
			scenario.write("Performing POST Operation");
			response = reqspec.when().post(Resourcepath);
		}
		log.info("*********** in WHEN  ---   Done with posting the request to ED URL  *****************  ");
		return response;
	}

	/**
	 * Check the ED authorization service response code and body
	 * 
	 * @param
	 * @return
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 */

	@Then("^Service validates the combination with ED and responds with an Acknowledement contains counterpartyid and userroles$")
	public void validateResponse() {
		if (tctype.equalsIgnoreCase("positive")) {
			scenario.write("response code received is :" + response.getStatusCode());
			if (response != null) {
				scenario.write(response.then().extract().asString());
				log.info(" ED response is : " + response.then().extract().asString());
			}
			response.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
		}
		if (tctype.equalsIgnoreCase("negative")) {
			scenario.write("response code received is :" + response.getStatusCode());
			if (response != null) {
				scenario.write(response.then().extract().asString());
				log.info(" ED response is : " + response.then().extract().asString());
			}
			response.then().assertThat()
					.statusCode(aceapimethods.contains(ReusableConstants.HTTPERRORCODE, response.getStatusCode()));
		}
	}

	/**
	 * Check the ED authorization service response body with user roles and
	 * Counter Party ID
	 * 
	 * @param jsonpath
	 * @return
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 */
	@Then("^\"([^\"]*)\" in response is correct for ED Authorisation$")
	public void validatejsonpath(String jsonpath) {
		log.info("In the validate json received values method");
		if (null != response) {
			String jsonresponse = response.then().extract().asString();
			ReadContext JSONContext = JsonPath.parse(jsonresponse);
			String temp = JSONContext.read(jsonpath);
			scenario.write(jsonpath + "    value is :  " + temp);
			assertThat(JSONContext, withJsonPath(compile(jsonpath)));
		}
	}

	/**
	 * Prepare a valid request by replacing the valid values for User name
	 * password App and policyDirectorIdentifier
	 * 
	 * 
	 * @param String
	 *            baseRequestUrl, User name Header , String Password Header
	 *            ,String App , String policyDirectorIdentifier
	 * @return Request Specification
	 * @author C46063 - Dmitriy - LAS - ACE API
	 */

	@Given("^Valid ACE API request with base url \"([^\"]*)\" and with the \"([^\"]*)\" and \"([^\"]*)\" passed as header and the request has valid combination of app as \"([^\"]*)\" and policyDirectorIdentifier as \"([^\"]*)\"$")
	public RequestSpecification authorization_Request_dynamicUrl(String baseURL, String username, String password,
			String App, String policyDirectorIdentifier) {

		log.info("*********** in GIVEN  ---   Preparing the ED Authorisation Request  *****************");
		this.UserName = username;
		this.Password = password;
		this.Appln = App;
		this.PDI = policyDirectorIdentifier;
		this.contentType = aceapimethods.getcontenttype(contType);
		RestAssured.baseURI = props.getProperty(baseURL);
		RestAssured.useRelaxedHTTPSValidation();
		reqspec = RestAssured.given().auth().preemptive().basic(UserName, Password).contentType(contentType)
				.param("app", Appln).param("policyDirectorIdentifier", PDI);
		return reqspec;

	}

	/**
	 * Validate any value in JSON response
	 * 
	 * @param
	 * @return
	 * @author C46063 - Dmitriy - LAS - ACE API
	 */
	@SuppressWarnings("deprecation")
	@Then("^The \"([^\"]*)\" value is valid in the ED Authorization response$")
	public void validateJsonResponseRelative(String expectedValue) {
		String responseAsString = "";
		if (response != null) {
			scenario.write(response.then().extract().body().asString());
			log.info(" ACE API response is : " + response.then().extract().asString());
			responseAsString = response.getBody().asString();
		}

	}
}
