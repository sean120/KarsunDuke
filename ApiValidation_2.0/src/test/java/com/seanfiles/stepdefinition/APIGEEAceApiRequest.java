package com.seanfiles.stepdefinition;

import static com.jayway.jsonpath.JsonPath.compile;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.seanfiles.helper.ACEAPIHelperMethods;
import com.seanfiles.root.LoadTestConfig;
import com.seanfiles.utils.DateUtils;
import com.seanfiles.utils.ElementJsonPaths;
import com.seanfiles.utils.Messages;
import com.seanfiles.utils.ReusableConstants;
import com.seanfiles.utils.ReusableUtilities;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lpaPages.FeedbackSummaryPage;
import lpaPages.LoginHomePage;
import lpaPages.LpaHome;

public class APIGEEAceApiRequest {

	private static Logger log = Logger.getLogger(APIGEEAceApiRequest.class);
	public static Scenario scenario;
	public Properties props;
	private int respCodes;
	private String respmsgcodes;
	private String operation = "";
	private String accesstoken = "";
	private String invalidtokentype = "";
	private String validationType = "";
	public ACEAPIHelperMethods aceapimethods;
	public LoadTestConfig aceapiconfig;
	private String apigeebaseurl = "";
	private String aceapirequestfilename = "";
	private String respmsgcodejsonpath = "$.deals[0].messages[0].messageCode";
	private String respmsgtextjsonpath = "$.deals[0].messages[0].messageText";
	private String acejsonrequest = "";
	private ContentType contentType = ContentType.JSON;
	public RequestSpecification reqspec = null;
	private String Resourcepath;
	

	@Before
	public void loadTestScenario(Scenario scenario) {
		this.scenario = scenario;
		this.aceapiconfig = new LoadTestConfig();
		this.props = aceapiconfig.loadProperties();
		this.aceapimethods = new ACEAPIHelperMethods();
		this.Resourcepath = props.getProperty("APIGEEACEAPIResourcePath");


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

	private static Response response;
	public Response apigeeresponse;
//	private String Resourcepath = props.getProperty("APIGEEACEAPIResourcePath");
	String appraisalWaiverPrescreenEligibilityTypeResponseValue = "Fail";
	String addressLineTextResponseValue = "";
	String cityNameResponseValue = "";
	String postalCodeResponseValue = "";
	String stateCodeResponseValue = "";
	String scrubbedAddress = "";
	String scrubbedAddress1 = "Fail";
	String scrubbedAddress2 = "Fail";
	WebDriver driver;
	String projectDirectory = System.getProperty("user.dir");
	String lpaLoanFileDirectory = projectDirectory + "\\src\\test\\resources\\LPALoanFiles\\";
	// String lpaLoanFilePath = lpaLoanFileDirectory +
	// "152_S2S_MSGS_Credit&Liabilities_01.xml";


	/**
	 * Prepare APIGEE Access Token Request
	 * 
	 * 
	 * @param String requestfile
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws IOException
	 */
	
	@Given("^APIGEE Developer Portal ACE API AccessToken and \"([^\"]*)\"$")
	public void getApigeeACERequestURL(String requestfile) throws IOException {

		log.info("*********** in GIVEN  ---   getting APIGEE Request URL and Access Token *************** ");
		this.apigeebaseurl = props.getProperty("APIGEEAccessTokenBaseUrl");
		this.accesstoken = props.getProperty("apigeeaccesstoken");
		if (requestfile != null) {
			this.aceapirequestfilename = requestfile;
		}

		if (aceapirequestfilename != null) {

			acejsonrequest = aceapimethods.generateStringFromResource(System.getProperty("user.dir")
					.concat(props.getProperty("schemavalfile").concat(aceapirequestfilename)));

		}
		scenario.write("Reqeust is : " + acejsonrequest);
		log.info("*********** Done ---  in GIVEN  ---   getting APIGEE Request URL and Access Token *************** ");

	}
	
	/**
	 * Prepare APIGEE expired Access Token request
	 * 
	 * 
	 * @param String typeoftoken, String requestfile
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws IoException
	 */

	@Given("^APIGEE Developer Portal \"([^\"]*)\" ACE API AccessToken and \"([^\"]*)\"$")
	public void getApigeeACERequestURLexpiredtoken(String typeoftoken, String requestfile) throws IOException {

		log.info("*********** in GIVEN  ---   getting APIGEE Request URL and Access Token *************** ");
		this.apigeebaseurl = props.getProperty("APIGEEAccessTokenBaseUrl");

		this.invalidtokentype = typeoftoken;

		if (typeoftoken.equalsIgnoreCase("expired"))
			this.accesstoken = props.getProperty("expiredapigeeaccesstoken");
		else if(typeoftoken.equalsIgnoreCase("BC API")) {
			setBCAPIaccesstokenproperty();
			scenario.write("bc api token - " + props.getProperty("bcapiproductapigeeaccesstoken"));
			this.accesstoken = props.getProperty("bcapiproductapigeeaccesstoken");
		}
		else
			this.accesstoken = props.getProperty("invalidapigeeaccesstoken");
		if (requestfile != null) {
			this.aceapirequestfilename = requestfile;
		}

		if (aceapirequestfilename != null) {

			acejsonrequest = aceapimethods.generateStringFromResource(System.getProperty("user.dir")
					.concat(props.getProperty("schemavalfile").concat(aceapirequestfilename)));

		}
		scenario.write("Request is : " + acejsonrequest);
		log.info("*********** Done ---  in GIVEN  ---   getting APIGEE Request URL and Access Token *************** ");

	}
	
	/**
	 * Prepare APIGEE invalid Access Token request
	 * 
	 * 
	 * @param String requestfile, String validatonName
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws IOException
	 */

	@Given("^APIGEE Developer Portal ACE API AccessToken and \"([^\"]*)\" for \"([^\"]*)\"$")
	public void getApigeeInvalidACERequest(String requestfile, String validatonName) throws IOException {

		log.info(
				"*********** in GIVEN  ---   getting APIGEE Request URL , Access Token , Json Request and  Scenario Name *************** ");
		this.apigeebaseurl = props.getProperty("APIGEEAccessTokenBaseUrl");
		this.accesstoken = props.getProperty("apigeeaccesstoken");
		this.validationType = validatonName;
		if (requestfile != null) {
			this.aceapirequestfilename = requestfile;
		}
		if (aceapirequestfilename != null) {
			acejsonrequest = aceapimethods.generateStringFromResource(System.getProperty("user.dir")
					.concat(props.getProperty("schemavalfile").concat(aceapirequestfilename)));

		}
		scenario.write("Reqeust is : " + acejsonrequest);
		log.info("*********** Done ---  in GIVEN  ---   getting APIGEE Request URL and Access Token *************** ");
	}
	
	/**
	 * Getting APIGEE ACE API Request Content Type 
	 * 
	 * 
	 * @param String contType
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Given("^content type is \"([^\"]*)\" for APIGEE Developer Portal ACE API Request$")
	public void getApigeeACERequestContType(String contType) {

		log.info("*********** in GIVEN  ---   getting APIGEE ACE API Request ContentType *************** ");

		this.contentType = aceapimethods.getcontenttype(contType);

		log.info("*********** Done  ---   in GIVEN  ---   getting APIGEE ACE API Request ContentType *************** ");
	}
	
	/**
	 *  Setting APIGEE Request URL by user
	 * 
	 * 
	 * @param String requestUrl
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws IOException
	 */

	@Given("^User sets APIGEE Portal ACE API request \"([^\"]*)\" url$")
	public void getApigeeRequestURL(String requestUrl) throws IOException {

		log.info("*********** in GIVEN  ---   getting APIGEE Request URL *************** ");
		this.apigeebaseurl = props.getProperty(requestUrl);
		log.info("*********** Done ---  in GIVEN  ---   getting APIGEE Request URL *************** ");

	}
	
	@Given("^User sets UAT resource path$")
	public void setResourcePath() throws IOException {

		Resourcepath = props.getProperty("uatAPIGEEACEAPIResourcePath");
		
	}
	
	/**
	 * Request is performing operation on APIGEE Gateway ACE API
	 * 
	 * 
	 * @param String oper
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws Exception
	 */

	@When("^Request is performing \"([^\"]*)\" on APIGEE Gateway ACE API$")
	public void sendApigeeACEReq(String oper) throws Exception {
		log.info("*********** in WHEN  --- Request is performing  sending request to APIGEE ACE API *************** ");
		Thread.sleep(1000);
		RestAssured.reset();
		RestAssured.baseURI = apigeebaseurl;
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.proxy(ReusableConstants.InternetProxyHost, ReusableConstants.InternetProxyPort);
		RestAssured.config = RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
		// RestAssured.config =
		// RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8").appendDefaultContentCharsetToContentTypeIfUndefined(false));
		reqspec = RestAssured.given().contentType(contentType).header("Authorization", "Bearer ".concat(accesstoken))
				.body(acejsonrequest);
		reqspec.log().all();
		String Resourcepath = props.getProperty("APIGEEACEAPIResourcePath");
		this.operation = oper;
		log.info("Operation is :" + operation);
		if (operation.equalsIgnoreCase(ReusableConstants.GET)) {
			scenario.write("Performing GET Operation");
			response = reqspec.when().get(Resourcepath);
		}
		if (operation.equalsIgnoreCase(ReusableConstants.POST)) {
			scenario.write("Performing POST Operation");
			response = reqspec.when().post(Resourcepath);
			log.info("Hello i am in POST Operation completed");

		}

		log.info(
				"*********** Done ---   in WHEN  ---   Request is performing  sending request to APIGEE ACE API *************** ");
	}
	
	
	@When("^User sends \"([^\"]*)\" request to ACE API through APIGEE$")
	public void sendAceAPIGEERequest(String oper) throws Exception {
		log.info("*********** in WHEN  --- Request is performing  sending request to APIGEE ACE API *************** ");
		Thread.sleep(1000);
		RestAssured.reset();
		RestAssured.baseURI = apigeebaseurl;
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.proxy(ReusableConstants.InternetProxyHost, ReusableConstants.InternetProxyPort);
		RestAssured.config = RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
		// RestAssured.config =
		// RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8").appendDefaultContentCharsetToContentTypeIfUndefined(false));
		reqspec = RestAssured.given().contentType(contentType).header("Authorization", "Bearer ".concat(accesstoken))
				.body(acejsonrequest);
		reqspec.log().all();
//		String Resourcepath = props.getProperty("APIGEEACEAPIResourcePath");
		this.operation = oper;
		log.info("Operation is :" + operation);
		if (operation.equalsIgnoreCase(ReusableConstants.GET)) {
			scenario.write("Performing GET Operation");
			response = reqspec.when().get(Resourcepath);
		}
		if (operation.equalsIgnoreCase(ReusableConstants.POST)) {
			scenario.write("Performing POST Operation");
			for(int i=0; i<4; i++){
				response = reqspec.when().post(Resourcepath);
				if(!(response.statusCode() == 200)){
					Thread.sleep(10000);
				}else{
					break;
				}
			}

			log.info("Hello i am in POST Operation completed");

		}

		log.info(
				"*********** Done ---   in WHEN  ---   Request is performing  sending request to APIGEE ACE API *************** ");
	}
	
	@When("^User sends \"([^\"]*)\" request to ACE API through APIGEE and verifies status code \"([^\"]*)\"$")
	public void sendAceAPIGEERequestCode(String oper, int statusCode) throws Exception {
		log.info("*********** in WHEN  --- Request is performing  sending request to APIGEE ACE API *************** ");
		Thread.sleep(1000);
		RestAssured.reset();
		RestAssured.baseURI = apigeebaseurl;
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.proxy(ReusableConstants.InternetProxyHost, ReusableConstants.InternetProxyPort);
		RestAssured.config = RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
		// RestAssured.config =
		// RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8").appendDefaultContentCharsetToContentTypeIfUndefined(false));
		reqspec = RestAssured.given().contentType(contentType).header("Authorization", "Bearer ".concat(accesstoken))
				.body(acejsonrequest);
		reqspec.log().all();
//		String Resourcepath = props.getProperty("APIGEEACEAPIResourcePath");
		this.operation = oper;
		log.info("Operation is :" + operation);
		if (operation.equalsIgnoreCase(ReusableConstants.GET)) {
			scenario.write("Performing GET Operation");
			response = reqspec.when().get(Resourcepath);
		}
		if (operation.equalsIgnoreCase(ReusableConstants.POST)) {
			scenario.write("Performing POST Operation");
			for(int i=0; i<4; i++){
				response = reqspec.when().post(Resourcepath);
				if(!(response.statusCode() == statusCode)){
					Thread.sleep(10000);
				}else{
					break;
				}
			}

			log.info("Hello i am in POST Operation completed");

		}

		log.info(
				"*********** Done ---   in WHEN  ---   Request is performing  sending request to APIGEE ACE API *************** ");
	}
	
	/**
	 * Checking received APIGEE ACE API Response and asserting status code
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Then("^Service sends back ResponseCode$")
	public void apigeeaceapiresp() {
		log.info("*********** in THEN  ---   APIGEE Ace API Response *************** ");
		if (response != null) {
			scenario.write("Received Response is :" + response.then().extract().asString());
			scenario.write("response code received is :" + response.getStatusCode());
			response.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
		}
		log.info("*********** DONE  ---    in THEN  ---   APIGEE ACE API Response *************** ");
	}
	
	/**
	 * Checking received APIGEE ACE API Response and asserting status code
	 * 
	 * 
	 * @param String statscode
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Then("^Service sends back \"([^\"]*)\"$")
	public void apigeeaceapiinvalidresp(int statscode) {
		log.info("*********** in THEN  ---   APIGEE Ace API Response *************** ");
		this.respCodes = statscode;
		scenario.write("Received Response is :" + response.then().extract().asString());
		scenario.write("response code received is :" + response.getStatusCode());
		response.then().assertThat().statusCode(respCodes);
		log.info("*********** DONE  ---    in THEN  ---   APIGEE ACE API Response *************** ");
	}
	
	@Then("User validates that the date is formatted correctly$")
	public static void validateJavaDate() throws ParseException	   {
	
		String jsonresp = response.then().extract().asString();
		ReadContext JSONContext = JsonPath.parse(jsonresp);
		ElementJsonPaths elementJasonPaths = new ElementJsonPaths();
		String jsonPath = elementJasonPaths.returnResponseElementJasonPath("appraisalWaiverPrescreenExpirationDate");
		String returnedDate = JSONContext.read(jsonPath);		
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
	    dateFormat.setLenient(false);
	    Boolean dateFormattedCorrectly = null;
	    try{
			Date javaDate = dateFormat.parse(returnedDate); 
	    	dateFormattedCorrectly = true;    	
	    }catch(Exception e){
	    	dateFormattedCorrectly = false;
	    }
	    if(dateFormattedCorrectly == true){
	    	scenario.write(returnedDate + " Formatted correctly");
	    	log.info(returnedDate + " Formatted correctly");
	    	
	    }else{
	    	scenario.write(returnedDate + " Formatted incorrectly");
	    	log.info(returnedDate + " Formatted incorrectly");
	    }
	    Assert.assertTrue(dateFormattedCorrectly);
	 
	   }
	
	
	/**
	 * Validating Message Code and Text in ACE API Response
	 * 
	 * 
	 * @param String MsgCodes
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Then("^validate the message code \"([^\"]*)\" and associated message in ACE API response$")
	public void apigeeaceapirespmsg(String MsgCodes) {
		log.info("*********** in THEN  ---   APIGEE Ace API Response msg code and text validation *************** ");
		this.respmsgcodes = MsgCodes;
		Messages respcodemessages = new Messages();
		String aceapirespmsgcode = "";
		String aceapirespmsgtext = "";
		if (respCodes == 200) {
			if (response != null) {
				String jsonresp = response.then().extract().asString();
				ReadContext JSONContext = JsonPath.parse(jsonresp);
				assertThat(JSONContext, withJsonPath(compile(respmsgcodejsonpath)));
				String acerespmsgcode = JSONContext.read(respmsgcodejsonpath);
				String acerespmsgtxt = JSONContext.read(respmsgtextjsonpath);
				scenario.write("ACE Response Message Code path : " + respmsgcodejsonpath + "   and   value is :  "
						+ acerespmsgcode);
				scenario.write("ACE Response Message text path : " + respmsgtextjsonpath + "   and   value is :  "
						+ acerespmsgtxt);
				aceapirespmsgcode = respcodemessages.returnMessageText(respmsgcodes.concat("MSGCode"));
				aceapirespmsgtext = respcodemessages.returnMessageText(respmsgcodes.concat("MSGText"));
				Assert.assertEquals(aceapirespmsgcode, acerespmsgcode);
				Assert.assertEquals(aceapirespmsgtext, acerespmsgtxt);

			}

		}

		log.info(
				"*********** DONE  ---    in THEN  ---   APIGEE Ace API Response msg code and text validation *************** ");
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
	
	@Then("^User sees the expected \"([^\"]*)\" value in the APIGEE ace response$")
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
	 * Validating Eligibility in ACE API Response
	 * 
	 * 
	 * @param String MsgCodes
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Then("^Validate the ACE API \"([^\"]*)\"$")
	public void validateaceapiresp(String aceeligible) {
		log.info("*********** in THEN  ---  validate APIGEE ACE API  Response *************** ");
		int statscode = response.getStatusCode();
		if (statscode == 200) {
			String jsonresp = response.then().extract().asString();
			ReadContext JSONContext = JsonPath.parse(jsonresp);
			assertThat(JSONContext, withJsonPath(compile(aceeligible)));
			String eligibility = JSONContext.read(aceeligible);
			scenario.write(aceeligible + "    value is :  " + eligibility);
		}
		log.info("***********  Done  ---    in THEN  ---  validate APIGEE ACE API  Response *************** ");

	}
	
	/**
	 * Validating Error Code and Text in ACE API Response
	 * 
	 * 
	 * @param String MsgCodes
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Then("^Service responds error \"([^\"]*)\" and \"([^\"]*)\"$")
	public void apigeeaceerrorresp(String apigeeerrstatuscode, String errorjsonpath) {
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
	
	/**
	 * Validating JSON Response value are same with expected values
	 * 
	 * 
	 * @param String elementName, String expectedValue
	 * 
	 * @return 
	 * @author C46063 - Dmitriy Advolodkin - LAS - ACE API 
	 * @throws
	 */

	@Then("Validate that the JSON response value \"([^\"]*)\" equals \"([^\"]*)\" in the response$")
	public void validateJsonPathExact(String elementName, String expectedValue) {
		log.info("In the validate json received values method");
		String retrievedValueString = "";
		Double retrievedValueDouble = null;
		ElementJsonPaths elementJasonPaths = new ElementJsonPaths();
		String jsonResponse = response.then().extract().asString();
		String jsonPath = elementJasonPaths.returnResponseElementJasonPath(elementName);
		ReadContext JSONContext = JsonPath.parse(jsonResponse);
		try{
			retrievedValueDouble = JSONContext.read(jsonPath);
		}catch(Exception e){
			log.info("Element not a double");
		}
		
		try{
			retrievedValueString = JSONContext.read(jsonPath);
		}catch(Exception e){
			log.info("Element not a string");
		}
		

		if (!expectedValue.equals("NoValue")) {
			if(retrievedValueString.equals("")){
				Assert.assertTrue(retrievedValueDouble.equals(Double.parseDouble(expectedValue)));
				scenario.write(jsonPath + "    value is :  " + retrievedValueDouble);
			}else{
				Assert.assertTrue(retrievedValueString.equals(expectedValue));
				scenario.write(jsonPath + "    value is :  " + retrievedValueString);
			}

		}
	}
	
	
	
	/**
	 * Getting Eligibility and Address values from response
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C46063 - Dmitriy Advolodkin - LAS - ACE API 
	 * @throws
	 */

	@Then("Return the appraisalWaiverPrescreenEligibilityType and scrubbed Address from the response$")
	public void returnAceEligibilityAndScrubbedAddress() {
		log.info("In the validate json received values method");

		ElementJsonPaths elementJasonPaths = new ElementJsonPaths();
		String jsonResponse = response.then().extract().asString();
		String jsonPathAddressLineText = elementJasonPaths.returnResponseElementJasonPath("addressLineText");
		String jsonPathCityName = elementJasonPaths.returnResponseElementJasonPath("cityName");
		String jsonPathPostalCode = elementJasonPaths.returnResponseElementJasonPath("postalCode");
		String jsonPathStateCode = elementJasonPaths.returnResponseElementJasonPath("stateCode");
		String jsonPathappraisalWaiverPrescreenEligibilityType = elementJasonPaths
				.returnResponseElementJasonPath("appraisalWaiverPrescreenEligibilityType");
		ReadContext JSONContext = JsonPath.parse(jsonResponse);
		addressLineTextResponseValue = JSONContext.read(jsonPathAddressLineText);
		cityNameResponseValue = JSONContext.read(jsonPathCityName);
		postalCodeResponseValue = JSONContext.read(jsonPathPostalCode);
		stateCodeResponseValue = JSONContext.read(jsonPathStateCode);
		appraisalWaiverPrescreenEligibilityTypeResponseValue = JSONContext
				.read(jsonPathappraisalWaiverPrescreenEligibilityType);

		scrubbedAddress1 = addressLineTextResponseValue;
		scrubbedAddress2 = cityNameResponseValue + " " + stateCodeResponseValue + " " + postalCodeResponseValue;
		scenario.write("Scrubbed address is: " + scrubbedAddress1 + " " + scrubbedAddress2);
		scenario.write("Eligibility Status is: " + appraisalWaiverPrescreenEligibilityTypeResponseValue);
		log.info("Scrubbed address is: " + scrubbedAddress1 + " " + scrubbedAddress2);
		log.info("Eligibility Status is: " + appraisalWaiverPrescreenEligibilityTypeResponseValue);
	}
	
	/**
	 * Login the LPA application
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C46063 - Dmitriy Advolodkin - LAS - ACE API 
	 * @throws
	 */

	@Then("Login the LPA application$")
	public void loginLpaApplication() {
		log.info("User is logging in");

		ReusableUtilities utilities = new ReusableUtilities();
		driver = utilities.launchDriver();

		LoginHomePage loginHomePO = new LoginHomePage(driver);
		loginHomePO.loginApplication();

		scenario.write("User successfully logged in application");
	}
	
	/**
	 * Uploading Loan Application in the LPA system
	 * 
	 * 
	 * @param String loanFileNAme
	 * 
	 * @return 
	 * @author C46063 - Dmitriy Advolodkin - LAS - ACE API 
	 * @throws InterruptedException
	 */

	@Then("User uploads loan application \"([^\"]*)\" in the LPA system$")
	public void uploadLPALoanFile(String loanFileName) throws InterruptedException {
		log.info("User is uploading the loan application");

		LpaHome lpaPO = new LpaHome(driver);
		lpaPO.uploadLoanFile(lpaLoanFileDirectory + loanFileName + ".xml");
		lpaPO.waitForSubmittedFileToLoad();

		scenario.write("User uploads loan file" + loanFileName + " into the LPA system");
	}
	
	/**
	 * Validation Ace Eligibility and scrubbed address, to match expected values, in LPA
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C46063 - Dmitriy Advolodkin - LAS - ACE API 
	 * @throws InterruptedException
	 */

	@And("User validates the expected Ace Eligibility and scrubbed address in LPA$")
	public void validateExpectedEligibilityAndLoanAmount() throws InterruptedException {

		FeedbackSummaryPage feedbackSummaryPO = new FeedbackSummaryPage(driver);
		feedbackSummaryPO.validateEligibility(appraisalWaiverPrescreenEligibilityTypeResponseValue);
		scenario.write("Eligibility Status in LPA matches the Eligibility Status in Ace API");
		log.info("Eligibility Status in LPA matches the Eligibility Status in Ace API");

		feedbackSummaryPO.validateScrubbedAddress(scrubbedAddress1, scrubbedAddress2);
		scenario.write("Scrubbed address in LPA matches the scrubbed address in Ace API");
		log.info("Scrubbed address in LPA matches the scrubbed address in Ace API");
	}
	
	@And("User validates Loan Not Ace Eligible$")
	public void validateNotEligible() throws InterruptedException {

		FeedbackSummaryPage feedbackSummaryPO = new FeedbackSummaryPage(driver);
		feedbackSummaryPO.validateNotEligibile();
		scenario.write("Eligibility is not eligible in LPA");
		log.info("Eligibility Status in LPA matches the Eligibility Status in Ace API");

//		feedbackSummaryPO.validateScrubbedAddress(scrubbedAddress1, scrubbedAddress2);
//		scenario.write("Scrubbed address in LPA matches the scrubbed address in Ace API");
//		log.info("Scrubbed address in LPA matches the scrubbed address in Ace API");
//	
		}
	
	
	/**
	 * Closing and exit browser
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C46063 - Dmitriy Advolodkin - LAS - ACE API 
	 * @throws InterruptedException
	 */

	@And("User closes and exits the browser$")
	public void closeBrowser() throws InterruptedException {
		log.info("User closes browser");
		driver.close();
		driver.quit();
		scenario.write("User closes browser");
	}

	/**
	 * Validate that the JSON response value of element equals null in the response
	 * 
	 * 
	 * @param String elementName
	 * 
	 * @return 
	 * @author C46063 - Dmitriy Advolodkin - LAS - ACE API 
	 * @throws
	 */
	
	@Then("Validate that the JSON response value \"([^\"]*)\" equals null in the response$")
	public void validateJsonPathExactNull(String elementName) {
		log.info("In the validate json received values method");

		ElementJsonPaths elementJasonPaths = new ElementJsonPaths();
		String jsonResponse = response.then().extract().asString();
		String jsonPath = elementJasonPaths.returnResponseElementJasonPath(elementName);
		ReadContext JSONContext = JsonPath.parse(jsonResponse);
		String retrievedValue = JSONContext.read(jsonPath);
		scenario.write(jsonPath + "    value is :  " + retrievedValue);

		Assert.assertNull(retrievedValue);
	}

	/**
	 * Validate any message text in JSON response according to messageCodeName
	 * 
	 * @param String messageCodeName
	 * @return
	 * @author C46063 - Dmitriy Advolodkin - ACE API
	 * @throws
	 */
	
	@Then("^User validates the expected message code \"([^\"]*)\" and associated text in the response$")
	public void validateMessageCodeResponse(String messageCodeName) {
		String responseAsString = "";
		String messageText = "";
		String[] errorresponsecodes;
		boolean multiplecodes = false;
		Messages respmessages = new Messages();
		if (messageCodeName.contains(",")) {
			multiplecodes = true;
			errorresponsecodes = messageCodeName.split(",");
			scenario.write(response.then().extract().body().asString());
			for (int i = 0; i < errorresponsecodes.length; i++) {
				messageText = respmessages.returnMessageText(errorresponsecodes[i]);
				if (response != null) {
					log.info(" ACE API response is : " + response.then().extract().asString());
					responseAsString = response.getBody().asString();
				}
				Assert.assertEquals(responseAsString.contains(errorresponsecodes[i] + " - " + messageText), true);
				scenario.write("Error Response Message is : " + errorresponsecodes[i] + " - " + messageText);
			}

		}

		if (!multiplecodes) {
			messageText = respmessages.returnMessageText(messageCodeName);
			if (response != null) {
				scenario.write(response.then().extract().body().asString());
				log.info(" ACE API response is : " + response.then().extract().asString());
				responseAsString = response.getBody().asString();
			}

			Assert.assertEquals(responseAsString.contains(messageCodeName + " - " + messageText), true);
			scenario.write("Error Response Message is : " + messageCodeName + " - " + messageText);
		}
	}

	/**
	 * Validate the expiration date of the request file
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C45888 --> Saule Beisenova
	 * @throws
	 */
	
	@Then ("^validate that request is valid for hundred twenty days$") 
	public void validate_that_request_is_valid_for_hundred_twenty_days () {
		DateUtils date = new DateUtils();
		System.out.println(response.getBody().asString());
		date.validateExpDateOfRequestFile(response.getBody().asString());
	
	}
	
	public void setBCAPIaccesstokenproperty() {
		this.apigeebaseurl = props.getProperty("APIGEEAccessTokenBaseUrl");
		// this.contentType = aceapimethods.getcontenttype(contType);
		String grant_type = ReusableConstants.apigeegrantType;
		String ClientId = "ndnhoCqig0Hc2DlvClaSrwNjPfujlFYl";
		String ClientSecret = "r1xGjitkdUPgDx3o";
		String UserName = "tklcc_lasapisys2sys";
		String Password = "#@e5Mqrzl6Aov5WqZt5H";
		String accesstokenresppath = "$.access_token";
		String accesstokencontType = "application/x-www-form-urlencoded";
		RestAssured.baseURI = apigeebaseurl;
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.proxy("nzenpxy.fhlmc.com", 9400);
		reqspec = RestAssured.given().contentType(accesstokencontType).formParam("grant_type", grant_type)
				.formParam("client_id", ClientId).formParam("client_secret", ClientSecret)
				.formParam("username", UserName).formParam("password", Password);
		reqspec.log().all();
		String Resourcepath = props.getProperty("APIGEEAccessTokenResourcePath");
		scenario.write("Performing POST Operation");
		apigeeresponse = reqspec.when().post(Resourcepath);
		if (apigeeresponse != null) {
			scenario.write("Apigee Response Code is : " +apigeeresponse.getStatusCode());
			scenario.write(apigeeresponse.then().extract().asString());
		}

		if (apigeeresponse.statusCode() == 200) {
			apigeeresponse.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
			String jsonresp = apigeeresponse.then().extract().asString();
			ReadContext JSONContext = JsonPath.parse(jsonresp);
			String accesstokenresppathvalue = JSONContext.read(accesstokenresppath);
			props.setProperty("bcapiproductapigeeaccesstoken", accesstokenresppathvalue);
			scenario.write(accesstokenresppath + "    value is :  " + accesstokenresppathvalue);
		}else {
			scenario.write("We received status code - " + apigeeresponse.statusCode());
		}
	} 
}
