package com.seanfiles.stepdefinition;

import static com.jayway.jsonpath.JsonPath.compile;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.junit.Assert.assertThat;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Assert;

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
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CheckEDAuthorisation {

	private static Logger log = Logger.getLogger(CheckEDAuthorisation.class);
	public Scenario scenario;
	public Properties props;
	public ACEAPIHelperMethods aceapimethods;
	public LoadTestConfig aceapiconfig;
	private String apigeebaseurl = "";
	private String UserName = "";
	private String Password = "";
	private String ClientId = "";
	private String ClientSecret = "";
	private String grant_type = "";
	private String accesstokenresppath = "";
	private String accesstokenresppathvalue = "";
	private String acerequestfile = "";
	private String acejsonrequestcontent = "";
	private String EDJSONResponse = "";
	//private String EDCustomer = "Appraisal Waiver Pre-screen Customer";
	private String EDCustomer = "ACE Check API Customer";
	private String contType = "application/json";
	private String reqpartyRoleIdentifierjsonpath = "$.party.partyRoleIdentifier";
	private String reqpartyRoleIdentifier = "";
	private String accesstokencontType = "application/x-www-form-urlencoded";
	public RequestSpecification reqspec = null;

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

	public Response apigeeresponse;
	public Response aceapiresponse;

	/**
	 * Prepare a APIGEE ACCESS Token Request
	 * 
	 * 
	 * @param String apigeeuname, String apigeepwd, String accesstokenpath
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws Exception
	 */

	@Given("^ED \"([^\"]*)\" and \"([^\"]*)\" combinations to get ACE API Access Token at \"([^\"]*)\" path$")
	public void getApigeeAccessToken(String apigeeuname, String apigeepwd, String accesstokenpath) throws Exception {
		log.info("*********** in GIVEN  ---   getting Apigee ACE API Access Token  *************** ");
		this.apigeebaseurl = props.getProperty("APIGEEAccessTokenBaseUrl");
		// this.contentType = aceapimethods.getcontenttype(contType);
		this.grant_type = ReusableConstants.apigeegrantType;
		this.ClientId = ReusableConstants.clientID;
		this.ClientSecret = ReusableConstants.clientSecret;
		this.UserName = apigeeuname;
		this.Password = apigeepwd;
		this.accesstokenresppath = accesstokenpath;
		RestAssured.baseURI = apigeebaseurl;
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.proxy(ReusableConstants.InternetProxyHost, ReusableConstants.InternetProxyPort);
		reqspec = RestAssured.given().contentType(accesstokencontType).formParam("grant_type", grant_type)
				.formParam("client_id", ClientId).formParam("client_secret", ClientSecret)
				.formParam("username", UserName).formParam("password", Password);
		reqspec.log().all();
		String Resourcepath = props.getProperty("APIGEEAccessTokenResourcePath");
		scenario.write("Performing POST Operation");
		apigeeresponse = reqspec.when().post(Resourcepath);
		if (apigeeresponse != null) {
			scenario.write("Apigee Response Code is : " + apigeeresponse.getStatusCode());
			scenario.write(apigeeresponse.then().extract().asString());
		}

		if (apigeeresponse.statusCode() == 200) {
			apigeeresponse.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
			String jsonresp = apigeeresponse.then().extract().asString();
			ReadContext JSONContext = JsonPath.parse(jsonresp);
			this.accesstokenresppathvalue = JSONContext.read(accesstokenresppath);
			scenario.write(accesstokenresppath + "    value is :  " + accesstokenresppathvalue);
		}
		log.info("*********** Done ---  in GIVEN  ---   getting Apigee ACE API Access Token *************** ");

	}
	
	/**
	 * Getting Apigee ACE API Request file content
	 * 
	 * 
	 * @param String jsonfilename
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws Exception
	 */

	@Given("^APIGEE Developer Portal For ACE API and a Valid \"([^\"]*)\"$")
	public void getApigeeAccessToken(String jsonfilename) throws Exception {
		log.info("*********** in GIVEN  ---   getting Apigee ACE API request file content  *************** ");

		if (jsonfilename != null) {
			this.acerequestfile = jsonfilename;

		}

		if (acerequestfile != null) {
			this.acejsonrequestcontent = aceapimethods.generateStringFromResource(
					System.getProperty("user.dir").concat(props.getProperty("schemavalfile").concat(acerequestfile)));
			ReadContext JSONContext = JsonPath.parse(acejsonrequestcontent);
			this.reqpartyRoleIdentifier = JSONContext.read(reqpartyRoleIdentifierjsonpath);
			
		}
		scenario.write("Reqeust is : " + acejsonrequestcontent);
		scenario.write(reqpartyRoleIdentifierjsonpath + "    value in ACE API JSON Request is :  " + reqpartyRoleIdentifier);
		log.info("*********** Done ---  in GIVEN  ---   getting Apigee ACE API request file content *************** ");

	}
	
	/**
	 * Sending request to APIGEE ACE API
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@When("^User Sends request to APIGEE ACE API Gateway with the returned access token$")
	public void sendApigeeAceRequest() {

		log.info("*********** in WHEN  --- sending request to APIGEE ACE API *************** ");

		RestAssured.baseURI = apigeebaseurl;
		// RestAssured.useRelaxedHTTPSValidation();
		RestAssured.proxy(ReusableConstants.InternetProxyHost, ReusableConstants.InternetProxyPort);

		RestAssured.config = RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

		reqspec = RestAssured.given().contentType(contType)
				.header("Authorization", "Bearer ".concat(accesstokenresppathvalue)).body(acejsonrequestcontent);
		reqspec.log().all();
		String Resourcepath = props.getProperty("APIGEEACEAPIResourcePath");
		scenario.write("Performing POST Operation");
		aceapiresponse = reqspec.when().post(Resourcepath);
		log.info("Heelllo i am in POST Operation completed");

		log.info("*********** Done - in WHEN  --- sending request to APIGEE ACE API *************** ");

	}
	
	/**
	 * Checking ACE API Response for Unauthorized User error code
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws 
	 */

	@Then("^ACE API sends back unauthorized user response$")
	public void aceApiunauthorizedresp() {
		log.info("*********** in THEN  ---   APIGEE Ace API unauthorized user Response *************** ");
		scenario.write("response code received is :" + aceapiresponse.getStatusCode());
		if (aceapiresponse != null) {
			scenario.write(aceapiresponse.then().extract().asString());
			log.info(" ED response is : " + aceapiresponse.then().extract().asString());
		}
		aceapiresponse.then().assertThat()
				.statusCode(aceapimethods.contains(ReusableConstants.HTTPERRORCODE, aceapiresponse.getStatusCode()));
		log.info("*********** DONE  ---    in THEN  ---   APIGEE Ace API unauthorized user Response *************** ");
	}
	
	/**
	 * Checking ACE API Response for success code
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Then("^ACE API sends back successful ACE Response$")
	public void aceApiresp() {
		log.info("*********** in THEN  ---   APIGEE Ace API Response *************** ");
		if (aceapiresponse != null) {
			scenario.write("Received Response is :" + aceapiresponse.then().extract().asString());
			scenario.write("response code received is :" + aceapiresponse.getStatusCode());
			aceapiresponse.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
		}
		log.info("*********** DONE  ---    in THEN  ---   APIGEE ACE API Response *************** ");
	}
	
	/**
	 * Checking MongoDB for ED Response
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws Exception
	 */

	@Then("^Check Mongo DB for ED Response$")
	public void checkMongoEDResp() throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For ED Response *************** ");
		EDJSONResponse = aceapimethods.getEDResponseMostRecent();
		scenario.write("ED Response is : " + EDJSONResponse);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For ED Response  *************** ");
	}
	
	/**
	 * Validating ED Response from MongoDB
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws Exception
	 */
	@Then("^Validate the ED Response$")
	public void validateMongoDBEDResp() throws Exception {
		log.info("*********** in THEN  ---   Validate Mongo DB ED Response *************** ");
		String edUserRolejsonpath = "$.resources[1].attributes.UserRoleName";
		String legacyEDIDRespJsonpath = "$.resources[1].attributes.LegacyAccountIdentifier";
		if (EDJSONResponse != null) {
			ReadContext JSONContext = JsonPath.parse(EDJSONResponse);
			String EDUserRoleName = JSONContext.read(edUserRolejsonpath);
			String EDLegacyID = JSONContext.read(legacyEDIDRespJsonpath);
			scenario.write(edUserRolejsonpath + " value is : " + EDUserRoleName);
			assertThat(JSONContext, withJsonPath(compile(edUserRolejsonpath)));
			
			
			scenario.write(legacyEDIDRespJsonpath + " value is : " + EDLegacyID);
			assertThat(JSONContext, withJsonPath(compile(legacyEDIDRespJsonpath)));
			
			Assert.assertEquals(EDCustomer, EDUserRoleName);
			
			Assert.assertEquals(reqpartyRoleIdentifier, EDLegacyID);
			log.info("*********** DONE  ---    in THEN  ---   Validate Mongo DB ED Response  *************** ");
		}
	}
	
	/**
	 * Extracting APIGEE Response with error
	 * 
	 * 
	 * @param String apigeeerrstatuscode, String errorjsonpath
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Then("^Apigee sends error \"([^\"]*)\" and \"([^\"]*)\"$")
	public void apigeedisableduserresp(String apigeeerrstatuscode, String errorjsonpath) {
		log.info("*********** in THEN  ---  Extract APIGEE Error Response *************** ");
		String errordetails = "";
		int statscode = apigeeresponse.getStatusCode();
		if (apigeeresponse != null) {
			scenario.write(apigeeresponse.then().extract().asString());
			apigeeresponse.then().assertThat().statusCode(Integer.parseInt(apigeeerrstatuscode));
		}
		if (statscode == aceapimethods.contains(ReusableConstants.HTTPERRORCODE, statscode)) {
			String jsonresp = apigeeresponse.then().extract().asString();
			ReadContext JSONContext = JsonPath.parse(jsonresp);
			errordetails = JSONContext.read(errorjsonpath);
			assertThat(JSONContext, withJsonPath(compile(errorjsonpath)));
			scenario.write(errorjsonpath + "    value is :  " + errordetails);

		}
		log.info("*********** Done  ---  in THEN  ---  Extract APIGEE Error Response *************** ");

	}

}
