package com.seanfiles.stepdefinition;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

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

public class ACEAPIRequestschemaVal {

	private static Logger log = Logger.getLogger(ACEAPIRequestschemaVal.class);
	public Scenario scenario;
	public Properties props;
	private int StatusCodes;
	private String operation;
	private String jsonrequest;
	private String tctype;
	private String scenariosname;
	private String fileName;
	public ACEAPIHelperMethods aceapimethods;
	public LoadTestConfig aceapiconfig;
	private String UserName = "";
	private String ProxyMSGID = "";
	private ContentType contentType = ContentType.JSON;
	private String contType = "application/json";
	protected RequestSpecification reqspec = null;

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
	 * Prepare a valid request schema validation
	 * 
	 * 
	 * @param String
	 *            filename, String scenario, String username and String
	 *            proxymsgid
	 * 
	 * @return Request Specification
	 * @author C36503 - COGNIZANT - LAS - ACE API
	 * @throws IOException
	 */

	@Given("^Read input file from json \"([^\"]*)\" for \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\" as header$")
	public RequestSpecification foraceapirequest(String filename, String scenarios, String username, String proxymsgid)
			throws IOException {
		this.scenariosname = scenarios;
		log.info(
				"*********** in GIVEN  ---   Preparing the ACE API Request for '" + scenariosname + "'  *****************");
		this.fileName = filename;
		scenario.write("testing for scenario  :  " + scenariosname);
		jsonrequest = aceapimethods.generateStringFromResource(
				System.getProperty("user.dir").concat(props.getProperty("schemavalfile").concat(fileName)));
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

	// @Given("^Valid JSON \"([^\"]*)\" ACE API request with \"([^\"]*)\" and
	// \"([^\"]*)\" passed as header$")
	// public RequestSpecification aceapirequest(String requestfiletype , String
	// username, String proxymsgid ) throws IOException {
	//
	// log.info("*********** in GIVEN --- Preparing the ACE API Request
	// *****************");
	//
	// jsonrequest =
	// aceapimethods.generateStringFromResource(System.getProperty("user.dir").concat(props.getProperty("aceapirequestfile")));
	// this.UserName = username;
	// this.ProxyMSGID= proxymsgid;
	// this.contentType = aceapimethods.getcontenttype(contType);
	// RestAssured.baseURI = props.getProperty("ACEAPIBaseUrl");
	// RestAssured.useRelaxedHTTPSValidation();
	// reqspec =
	// RestAssured.given().contentType(contentType).header("X-Forwarded-UserName",
	// UserName).and().header("X-Forwarded-ProxyMessageId",
	// ProxyMSGID).body(jsonrequest);
	// reqspec.log().all();
	// return reqspec;
	// }

	/**
	 * Executing certain test case
	 * 
	 * 
	 * @param String testcasetype
	 *           
	 *            
	 * 
	 * @return
	 * @author C36503 - COGNIZANT - LAS - ACE API
	 * @throws 
	 */
	
	@Given("^executing \"([^\"]*)\" test case type$")
	public void typeoftesting(String testcasetype) {
		this.tctype = testcasetype;
	}
	
	/**
	 * Posting the request to ACE API URL
	 * 
	 * 
	 * @param String Op
	 *            
	 *            
	 * 
	 * @return response
	 * @author C36503 - COGNIZANT - LAS - ACE API
	 * @throws
	 */

	@When("^Request is performing \"([^\"]*)\" on ACE API URL$")
	public Response post_request_ACEAPIService(String Op) {
		log.info("*********** in WHEN  ---   posting the request to ACE API URL  *****************  ");
		String Resourcepath = props.getProperty("ACEAPIResourcePath");
		this.operation = Op;
		if (operation.equalsIgnoreCase(ReusableConstants.GET)) {
			scenario.write("Performing GET Operation");
			response = reqspec.when().get(Resourcepath);
		}
		if (operation.equalsIgnoreCase(ReusableConstants.POST)) {
			scenario.write("Performing POST Operation");
			response = reqspec.when().post(Resourcepath);
		}
		log.info("*********** in WHEN  ---   Done with posting the request to ACE API URL  *****************  ");
		return response;
	}
	
	/**
	 * Checking Status Code accroding to Test case type
	 * 
	 * 
	 * @param int stscode
	 *            
	 *            
	 * 
	 * 
	 * @author C36503 - COGNIZANT - LAS - ACE API
	 * @throws 
	 */

	@Then("^Verify service sends back \"([^\"]*)\" code$")
	public void validateResponse(int stscode) {
		this.StatusCodes = stscode;
		if (tctype.equalsIgnoreCase("positive")) {
			log.info("In Positive validate Response");
			scenario.write("response code received is :" + response.getStatusCode());
			response.then().assertThat().statusCode(StatusCodes);
		}
		if (tctype.equalsIgnoreCase("negative")) {
			log.info("In negative validate Response");
			scenario.write("negative response code received is :" + response.getStatusCode());
			// response.then().assertThat()
			// .statusCode(aceapimethods.contains(ReusableConstants.HTTPERRORCODE,
			// response.getStatusCode()));
			response.then().assertThat().statusCode(StatusCodes);
		}
	}
	
	/**
	 * Verifying that Element sends back response code
	 * 
	 * 
	 * @param String element
	 *           
	 *            
	 * 
	 * @return
	 * @author C36503 - COGNIZANT - LAS - ACE API
	 * @throws 
	 */

	@Then("^Verify Element sends back \"([^\"]*)\" code$")
	public void validateElementResponse(String element) {
		String Substr[] = element.split(",");
		if (tctype.equalsIgnoreCase("positive")) {
			log.info("In Positive validate Response");
			scenario.write("response code received is :" + response.getStatusCode());
			if (response != null) {
				scenario.write(response.then().extract().asString());
				log.info(" ACE API response is : " + response.then().extract().asString());
				for (int i = 0; i < Substr.length - 1; i++) {
					//// response.then().assertThat().statusCode(element);
				}
			}

		}
		if (tctype.equalsIgnoreCase("negative")) {
			log.info("In negative validate Response");
			scenario.write("negative response code received is :" + response.getStatusCode());
			if (response != null) {
				scenario.write(response.then().extract().asString());
				log.info(" ACE API response is : " + response.then().extract().asString());
			}
			// response.then().assertThat()
			// .statusCode(aceapimethods.contains(ReusableConstants.HTTPERRORCODE,
			// response.getStatusCode()));
			for (int i = 0; i < Substr.length - 1; i++) {
				//// response.then().assertThat().statusCode(element);
			}

		}
	}
}
