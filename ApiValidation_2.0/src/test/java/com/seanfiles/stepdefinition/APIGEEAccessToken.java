package com.seanfiles.stepdefinition;

import static com.jayway.jsonpath.JsonPath.compile;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.junit.Assert.assertThat;

import java.io.IOException;
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
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIGEEAccessToken {

	private static Logger log = Logger.getLogger(APIGEEAccessToken.class);
	public Scenario scenario;
	public Properties props;
	private String operation;
	public ACEAPIHelperMethods aceapimethods;
	public LoadTestConfig aceapiconfig;
	private String apigeebaseurl = "";
	private String UserName = "";
	private String Password = "";
	private String ClientId = "";
	private String ClientSecret = "";
	private String grant_type = "";
	private ContentType contentType = ContentType.JSON;
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

	private Response response;

	/**
	 * Prepare a APIGEE Portal ACE API Access Token Request
	 * 
	 * 
	 * @param String requestUrl
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws IoException
	 */

	@Given("^User sets APIGEE Portal ACE API Access Token request \"([^\"]*)\" url$")
	public void getApigeeRequestURL(String requestUrl) throws IOException {

		log.info("*********** in GIVEN  ---   getting APIGEE Request URL *************** ");
		this.apigeebaseurl = props.getProperty(requestUrl);
		log.info("*********** Done ---  in GIVEN  ---   getting APIGEE Request URL *************** ");

	}
	
	/**
	 * Prepare a APIGEE Developer Portal ACE API Access Token Request with APIGEE Access Token Base URL
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws IOException
	 */

	@Given("^APIGEE Developer Portal ACE API Access Token request$")
	public void getApigeeRequestURL() throws IOException {

		log.info("*********** in GIVEN  ---   getting APIGEE Request URL *************** ");
		this.apigeebaseurl = props.getProperty("APIGEEAccessTokenBaseUrl");
		log.info("*********** Done ---  in GIVEN  ---   getting APIGEE Request URL *************** ");

	}
	
	/**
	 * Prepare a APIGEE Developer Portal Invalid ACE API Token Request with APIGEE Access Token Base URL
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws IOException
	 */

	@Given("^APIGEE Developer Portal Invalid ACE API Access Token request$")
	public void getApigeeInvliadRequestURL() throws IOException {

		log.info("*********** in GIVEN  ---   getting APIGEE Request URL *************** ");
		this.apigeebaseurl = props.getProperty("APIGEEAccessTokenBaseUrl");
		log.info("*********** Done ---  in GIVEN  ---   getting APIGEE Request URL *************** ");

	}
	
	/**
	 * Getting Apigee Request Content Type for Apigee Developer Portal
	 * 
	 * 
	 * @param String contType
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Given("^content type is \"([^\"]*)\" for APIGEE Developer Portal$")
	public void getApigeeRequestContType(String contType) {

		log.info("*********** in GIVEN  ---   getting APIGEE Request ContentType *************** ");

		this.contentType = aceapimethods.getcontenttype(contType);

		log.info("*********** Done  ---   in GIVEN  ---   getting APIGEE Request ContentType *************** ");
	}
	
	/**
	 * Passing granttype as APIGEE Request Parameter
	 * 
	 * 
	 * @param String grantType
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Given("^passing granttype \"([^\"]*)\" parameter$")
	public void getApigeeGrantType(String grantType) {
		log.info("*********** in GIVEN  ---   getting APIGEE Request Parameters *************** ");

		this.grant_type = grantType;

		log.info("*********** Done ----  in GIVEN  ---   getting APIGEE Request Parameters *************** ");
	}
	
	/**
	 * Passing Client ID as APIGEE Request Parameter
	 * 
	 * 
	 * @param String clientID
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Given("^passing clientid \"([^\"]*)\" parameter$")
	public void getApigeeClientId(String clientID) {
		log.info("*********** in GIVEN  ---   getting APIGEE Request Parameters *************** ");

		this.ClientId = clientID;

		log.info("*********** Done ----  in GIVEN  ---   getting APIGEE Request Parameters *************** ");
	}
	
	/**
	 * Passing Client Secret as APIGEE Request Parameter
	 * 
	 * 
	 * @param String clientSecret
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Given("^passing clientsecret \"([^\"]*)\" parameter$")
	public void getApigeeClientSecret(String clientSecret) {
		log.info("*********** in GIVEN  ---   getting APIGEE Request Parameters *************** ");

		this.ClientSecret = clientSecret;

		log.info("*********** Done ----  in GIVEN  ---   getting APIGEE Request Parameters *************** ");
	}
	
	/**
	 * Passing username as APIGEE Request Parameter
	 * 
	 * 
	 * @param String uname
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Given("^passing username \"([^\"]*)\" parameter$")
	public void getApigeeUname(String uname) {
		log.info("*********** in GIVEN  ---   getting APIGEE Request Parameters *************** ");

		this.UserName = uname;

		log.info("*********** Done ----  in GIVEN  ---   getting APIGEE Request Parameters *************** ");
	}
	
	/**
	 * Passing password as APIGEE Request Parameter
	 * 
	 * 
	 * @param String pwd
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Given("^passing pwd \"([^\"]*)\" parameter$")
	public void getApigeePwd(String pwd) {
		log.info("*********** in GIVEN  ---   getting APIGEE Request Parameters *************** ");
		log.info("Password is :" + pwd);
		this.Password = pwd;

		log.info("*********** Done ----  in GIVEN  ---   getting APIGEE Request Parameters *************** ");
	}
	
	/**
	 * Sending request to APIGEE to get Access Token
	 * 
	 * 
	 * @param String oper
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@When("^Request is performing \"([^\"]*)\" on APIGEE Gateway$")
	public void sendApigeeReq(String oper) throws IOException {
		log.info("*********** in WHEN  ---   sending request to APIGEE for ACCESS TOKEN *************** ");

		RestAssured.baseURI = apigeebaseurl;
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.proxy(ReusableConstants.InternetProxyHost, ReusableConstants.InternetProxyPort);
		// RestAssured.config =
		// RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8").appendDefaultContentCharsetToContentTypeIfUndefined(false));
		reqspec = RestAssured.given().contentType(contentType).formParam("grant_type", grant_type)
				.formParam("client_id", ClientId).formParam("client_secret", ClientSecret)
				.formParam("username", UserName).formParam("password", Password);
		reqspec.log().all();
		String Resourcepath = props.getProperty("APIGEEAccessTokenResourcePath");
		this.operation = oper;
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

		log.info("*********** Done ---   in WHEN  ---   sending request to APIGEE for ACCESS TOKEN *************** ");
	}

	/**
	 * Validate any value on JSON response
	 * 
	 * 
	 * @param String expectedValue
	 * 
	 * @return 
	 * @author C46063 - Dmitriy - LAS - ACE API
	 * @throws
	 */
	
	@Then("^User sees the expected \"([^\"]*)\" value in the APIGEE response$")
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
	 * Asserting APIGEE Access Token response for success
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Then("^Service sends back Access Token$")
	public void apigeeAccessToken() {
		log.info("*********** in THEN  ---   APIGEE ACCESS TOKEN Response *************** ");
		if (response != null) {
			scenario.write(response.then().extract().asString());
			response.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
		}
		log.info("*********** DONE  ---    in THEN  ---   APIGEE ACCESS TOKEN Response *************** ");
	}
	
	/**
	 * Getting and storing APIGEE Access Token in property file
	 * 
	 * 
	 * @param String accesstokepath
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Then("^Store the \"([^\"]*)\" in ACE API Local$")
	public void storeaccesstoken(String accesstokenpath) {
		log.info("*********** in THEN  ---  Store APIGEE ACCESS TOKEN From Json Response *************** ");
		String accesstoken = "";
		int statscode = response.getStatusCode();
		if (statscode == 200) {
			String jsonresp = response.then().extract().asString();
			ReadContext JSONContext = JsonPath.parse(jsonresp);
			accesstoken = JSONContext.read(accesstokenpath);
			scenario.write(accesstokenpath + "    value is :  " + accesstoken);
			assertThat(JSONContext, withJsonPath(compile(accesstokenpath)));

		}
		if (!accesstoken.equalsIgnoreCase("")) {

			aceapiconfig.storeProperties("apigeeaccesstoken", accesstoken);

		}

	}
	
	/**
	 * Checking APIGEE ACE API Response for error status code
	 * 
	 * 
	 * @param String apigeestatuscode, String errorjsonpath
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Then("^Service sends error \"([^\"]*)\" and \"([^\"]*)\"$")
	public void apigeeerrorresp(String apigeeerrstatuscode, String errorjsonpath) {
		log.info("*********** in THEN  ---  Extract APIGEE Error Response *************** ");
		String errordetails = "";
		int statscode = response.getStatusCode();
		if (response != null) {
			scenario.write(response.then().extract().asString());
			response.then().assertThat().statusCode(Integer.parseInt(apigeeerrstatuscode));
		}
		if (statscode == aceapimethods.contains(ReusableConstants.HTTPERRORCODE, statscode)) {
			String jsonresp = response.then().extract().asString();
			ReadContext JSONContext = JsonPath.parse(jsonresp);
			errordetails = JSONContext.read(errorjsonpath);
			scenario.write(errorjsonpath + "    value is :  " + errordetails);
			assertThat(JSONContext, withJsonPath(compile(errorjsonpath)));

		}
		log.info("*********** Done  ---  in THEN  ---  Extract APIGEE Error Response *************** ");

	}

}
