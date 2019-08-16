package com.seanfiles.stepdefinition;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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

public class ACEAPIPrefixSignLoanAmount {

	
	private static Logger log = Logger.getLogger(ACEAPIPrefixSignLoanAmount.class);
	public Scenario scenario;
	public Properties props;
	public ACEAPIHelperMethods aceapimethods;
	public LoadTestConfig aceapiconfig;
	private String apigeebaseurl = "";
	//private String Filename = "";
	private String UserName = "";
	private String Password = "";
	private String ClientId = "";
	private String ClientSecret = "";
	private String grant_type = "";
	private String accesstokenresppath = "";
	private String accesstokenresppathvalue = "";
	private String acerequestfile = "";
	private String acejsonrequestcontent = "";
	private JSONObject aceresponseobj;
	public String strDate;
	private String contType = "application/json";
	private String accesstokencontType = "application/x-www-form-urlencoded";
	public RequestSpecification reqspec = null;

	// ================================Framework_SETUP================================\\

	@Before
	public void loadTestScenario(Scenario scenario) {
		this.scenario = scenario;
		this.aceapiconfig = new LoadTestConfig();
		this.props = aceapiconfig.loadProperties();
		this.aceapimethods = new ACEAPIHelperMethods();
	}

//	@After
//	public void Teardown() {
//		try {
//			String htmlreport = System.getProperty("user.dir")
//					.concat("\\target\\cucumber-report-html\\cucumber-html-reports\\feature-overview.html");
//			Desktop.getDesktop().browse(new URL(htmlreport).toURI());
//		} catch (Exception ex) {
//			log.error(ex.getMessage());
//		}
//	}

	// ================================Framework_SETUP================================\\

	public Response apigeeresponse;
	public Response aceapiresponse;

	/**
	 * Getting Apigee ACE API Access Token
	 * 
	 * 
	 * @param String apigeeuname, String apigeepwd, String accesstokenpath
	 *          
	 * 
	 * @author 
	 * @throws Exception
	 */
	
	@Given("^\"([^\"]*)\" and \"([^\"]*)\" combinations to get ACE Access Token at \"([^\"]*)\"$")
	public void getValidApigeeAccessToken(String apigeeuname, String apigeepwd, String accesstokenpath) throws Exception {
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
			scenario.write("Apigee Response Code is : " +apigeeresponse.getStatusCode());
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
	 * 
	 * @author 
	 * @throws Exception
	 */
	
	@Given("^APIGEE For ACE and a Valid \"([^\"]*)\"$")
	public void getInputFileContentToRequest(String jsonfilename) throws Exception {
		log.info("*********** in GIVEN  ---   getting Apigee ACE API request file content  *************** ");

		if (jsonfilename != null) {
			this.acerequestfile = jsonfilename;

		}

		if (acerequestfile != null) {

			this.acejsonrequestcontent = aceapimethods.generateStringFromResource(
					System.getProperty("user.dir").concat(props.getProperty("schemavalfile").concat(acerequestfile)));

		}
		scenario.write("Request is : " + acejsonrequestcontent);
		log.info("*********** Done ---  in GIVEN  ---   getting Apigee ACE API request file content *************** ");

	}
	
	/**
	 * Sending request to APIGEE ACE API
	 * 
	 * 
	 * @param
	 *          
	 * 
	 * @author
	 * @throws
	 */

	@When("^Sending our request to ACE with the valid access token$")
	public void sendRequestToAceApi() {

		log.info("*********** in WHEN  --- sending request to APIGEE ACE API *************** ");

		RestAssured.baseURI = apigeebaseurl;
		RestAssured.proxy(ReusableConstants.InternetProxyHost, ReusableConstants.InternetProxyPort);

		RestAssured.config = RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

		reqspec = RestAssured.given().contentType(contType)
				.header("Authorization", "Bearer ".concat(accesstokenresppathvalue)).body(acejsonrequestcontent);
		reqspec.log().all();
		String Resourcepath = props.getProperty("APIGEEACEAPIResourcePath");
		System.out.println("****************" + Resourcepath);
		scenario.write("Performing POST Operation");
		aceapiresponse = reqspec.when().post(Resourcepath);

		log.info("*********** Done - in WHEN  --- sending request to APIGEE ACE API *************** ");

	}
	
	/**
	 * Getting and saving ACE API Response
	 * 
	 * 
	 * @param
	 *          
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */

	@Then("^Get ACE API response")
	public void getAceResponseFromMongoDB1() throws Exception {
		log.info("*********** in THEN  ---   Get ACE API response *************** ");
		if (aceapiresponse != null) {
			aceapiresponse.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
			JSONParser parser = new JSONParser();
			Object acejson = (Object) parser.parse(aceapiresponse.getBody().asString());
			aceresponseobj = (JSONObject) acejson;
			scenario.write("ACE Response is : " + aceresponseobj);
			
		}
		log.info("ACE Response is :" + aceresponseobj);
		log.info("*********** DONE  ---    in THEN  ---   Get ACE API response *************** ");
	}
	
	/**
	 * Checking Loan Amount to have '$' sign
	 * 
	 * 
	 * @param 
	 *          
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws
	 */
	
	@Then("^Check for element to contain sign or not, according to acceptance criteria$")
	public void checkSignLoanAmountByCriteria() {
		String loanAmount = ((JSONObject) ((JSONObject) ((JSONArray) aceresponseobj.get("deals")).get(0)).get("loanInformation")).get("maximumAuthorizedLoanAmount").toString();
		scenario.write("Loan Amount - " + loanAmount);
		if (loanAmount==null || loanAmount.equals("")) {
			Assert.assertTrue("Loan Amount Does Not contain '$' even if it is null", loanAmount.equalsIgnoreCase("null"));
		}else {
			Assert.assertFalse("'$' Sign is not present in Loan Amount", loanAmount.contains("$"));
			Assert.assertTrue("Loan Amount not matching schema", loanAmount.matches("^[0-9]+[.][0-9][0-9]$"));			
		}
	}
	
	/**
	 * Checking for Loan Amount and Expiration date to be empty strings if eligibility equals 'NotEligible'
	 * 
	 * 
	 * @param
	 *          
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws
	 */
	
	@Then("^Check for element for Eligibility and values$")
	public void checkValuesNotEligibile() throws NullPointerException {
		String eligibility = ((JSONObject) ((JSONObject) ((JSONArray) aceresponseobj.get("deals")).get(0)).get("loanInformation")).get("appraisalWaiverPrescreenEligibilityType").toString();
		scenario.write("Eligibility - '" + eligibility + "'");
		if (eligibility.equals("NotEligible")) {
			if(((JSONObject) ((JSONObject) ((JSONArray) aceresponseobj.get("deals")).get(0)).get("loanInformation")).get("maximumAuthorizedLoanAmount") != null)
			Assert.assertEquals("Loan Amount is not null",((JSONObject) ((JSONObject) ((JSONArray) aceresponseobj.get("deals")).get(0)).get("loanInformation")).get("maximumAuthorizedLoanAmount"),null);
			if(((JSONObject) ((JSONObject) ((JSONArray) aceresponseobj.get("deals")).get(0)).get("loanInformation")).get("appraisalWaiverPrescreenExpirationDate") != null)
			Assert.assertEquals("Expiration date is not null",((JSONObject) ((JSONObject) ((JSONArray) aceresponseobj.get("deals")).get(0)).get("loanInformation")).get("appraisalWaiverPrescreenExpirationDate"),"");
		}else {
			Assert.fail("Eligibility is not matches 'NotEligible', current value is: '" + eligibility + "'");
		}
	}
			
	
}
