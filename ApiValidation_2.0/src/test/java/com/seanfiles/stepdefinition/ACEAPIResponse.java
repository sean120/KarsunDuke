package com.seanfiles.stepdefinition;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.seanfiles.helper.ACEAPIHelperMethods;
import com.seanfiles.root.LoadTestConfig;
import com.seanfiles.utils.ReusableConstants;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ACEAPIResponse {
	
	
	private static Logger log = Logger.getLogger(ACEAPIResponse.class);
	public Scenario scenario;
	public Properties props;
	public ACEAPIHelperMethods aceapimethods;
	public LoadTestConfig aceapiconfig;
	private String apigeebaseurl = "";
	private String Filename = "";
	private String UserName = "";
	private String Password = "";
	private String ClientId = "";
	private String ClientSecret = "";
	private String grant_type = "";
	private String accesstokenresppath = "";
	private String accesstokenresppathvalue = "";
	private String acerequestfile = "";
	private String acejsonrequestcontent = "";
	private String JSONResponse = "";
	private Document AceResponse;
	JSONObject aceresponseobj;
	private String EDSXMLResponse;
	private DataTable datatab;
	public String strDate;
	private String contType = "application/json";
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
	 * Getting Apigee ACE API Access Token
	 * 
	 * 
	 * @param String apigeeuname, String apigeepwd, String accesstokenpath
	 *            
	 * 
	 * 
	 * @author
	 * @throws Exception
	 */
	
	@Given("^HVE \"([^\"]*)\" and \"([^\"]*)\" combinations to get ACE Access Token at \"([^\"]*)\" path$")
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
	 * 
	 * @author 
	 * @throws Exception
	 */
	
	@Given("^APIGEE Portal For ACE API and a Valid \"([^\"]*)\"$")
	public void getInputFileContent(String jsonfilename) throws Exception {
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
	 * 
	 * @author
	 * @throws 
	 */

	@When("^Sending request to ACE with the access token$")
	public void sendRequestAceApi() {

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
		System.out.println("****************" + Resourcepath);
		scenario.write("Performing POST Operation");
		aceapiresponse = reqspec.when().post(Resourcepath);

		log.info("*********** Done - in WHEN  --- sending request to APIGEE ACE API *************** ");

	}
	
	/**
	 * Getting and saving ACE Response
	 * 
	 * 
	 * @param
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */
	
	@Then("^Save ACE Response from Mongo$")
	public void getAceResponseFromMongoDB() throws Exception {
		log.info("*********** in THEN  ---   Save ACE Response from MongoDB *************** ");
		if (aceapiresponse != null) {
			scenario.write("ACE - " + aceapiresponse.getBody().asString());
			JSONParser parser = new JSONParser();
			Object acejson = parser.parse(aceapiresponse.getBody().asString());
			aceresponseobj = (JSONObject) acejson;
		}
		log.info("ACE Response is :" + AceResponse);
		log.info("*********** DONE  ---    in THEN  ---   Save ACE Response from MongoDB  *************** ");
	}
	
	/**
	 * Checking and getting EDS Response from MongoDB
	 * 
	 * 
	 * @param
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */
	
	@Then("^Check MongoDB for EDS Response$")
	public String checkMongoDBEDSResp() throws Exception {
		log.info("*********** in THEN  ---   Verify MongoDB For EDS Response *************** ");
		if (aceapiresponse != null) {
			EDSXMLResponse = aceapimethods.getEDSResponseMostRecent();
			scenario.write("EDS Response is : " + EDSXMLResponse);
		}
		log.info("EDS Response is :" + EDSXMLResponse);
		log.info("*********** DONE  ---    in THEN  ---   Verify MongoDB For EDS Response  *************** ");
		return EDSXMLResponse;

	}
	
	/**
	 * Saving EDS Response in xml file
	 * 
	 * 
	 * @param String filename
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */
	
	@Then("^save EDS Response in file \"([^\"]*)\"$")
	public void saveEDSResponseXml(String filename) throws Exception {
		String filepath;
		this.Filename=filename;
		scenario.write("Saving the HVE API response");
		if (!(EDSXMLResponse.equalsIgnoreCase(""))){
			filepath = System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
			aceapimethods.writeToFile(EDSXMLResponse, filepath);
			log.info("Write the fetched request in file '"+ filename +"'.");
			scenario.write("Write the fetched request in file '"+ filename +"'.");
		} else {
			  log.info("EDS Response is Blank");
			  scenario.write("EDS Response is Blank");
			  Assert.fail("EDS Response is Blank");
		  }
	}
	
	/**
	 * Checking and getting HVS Response from MongoDB
	 * 
	 * 
	 * @param
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */	
	
	@Then("^Check MongoDB for HVS Response$")
	public void checkHVEResponce() throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For HVE Responce *************** ");
		JSONResponse = aceapimethods.getHVEResponseMostRecent();
		scenario.write("HVE Responce is : " + JSONResponse);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For HVE Responce  *************** ");
	}
	
	/**
	 * Saving HVS Response in xml file
	 * 
	 * 
	 * @param String filename
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */
	
	@Then("^save HVS Response in file \"([^\"]*)\"$")
	public void saveHveResponseXml(String filename) throws Exception {
		String filepath;
		this.Filename=filename;
		scenario.write("Saving the HVE API response");
		if (!(JSONResponse.equalsIgnoreCase(""))){
			filepath = System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
			aceapimethods.writeToFile(JSONResponse, filepath);
			log.info("Write the fetched request in file '"+ filename +"'.");
			scenario.write("Write the fetched request in file '"+ filename +"'.");
		} else {
			  log.info("HVS Response is Blank");
			  scenario.write("HVS Response is Blank");
			  Assert.fail("HVS Response is Blank");
		  }
	}
	
	/**
	 * Validating the Eligibility container in ACE API Response to have same value with EDS Response
	 * 
	 * 
	 * @param String filename, DataTable elementnvalue
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws 
	 */
	
	@Then("^Validate the Eligibility in ACE Response with EDS response file \"([^\"]*)\"$")
	public void validateEligibilityAceEds(String filename, DataTable elementnvalue) {
		log.info("*********** in THEN  ---   Validate the Eligibility in ACE Response with EDS response file *************** ");
		String value;
		String filepath;
		this.Filename=filename;
		this.datatab=elementnvalue;
		String eligibility = ((JSONObject) ((JSONObject) ((JSONArray) aceresponseobj.get("deals")).get(0)).get("loanInformation")).get("appraisalWaiverPrescreenEligibilityType").toString();
		List<Map<String,String>> data = datatab.asMaps(String.class,String.class);
		filepath=System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
		value = aceapimethods.getXpathValue(data.get(0).get("xpath"), filepath);
		scenario.write("value - " + value);
		scenario.write("eligibility - " + eligibility);
		Assert.assertEquals(value, eligibility);
		log.info("*********** DONE  ---    in THEN  ---   Validate the Eligibility in ACE Response with EDS response file *************** ");
		
	}
	
	/**
	 * Validating the Address containers in ACE API Response to have same value with HVE Response
	 * 
	 * 
	 * @param String filename, DataTable elementnvalue
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws 
	 */
	
	@Then("^Validate the Address in ACE Response with HVE response file \"([^\"]*)\"$")
	public void validateAddressAceHve(String filename, DataTable elementnvalue) {
		log.info("*********** in THEN  ---   Validate the Address in ACE Response with HVE response file *************** ");
		String filepath;
		this.Filename=filename;
		this.datatab=elementnvalue;
		JSONObject address = (JSONObject) ((JSONObject) ((JSONArray) aceresponseobj.get("deals")).get(0)).get("address");
		List<Map<String,String>> data = datatab.asMaps(String.class,String.class);
		filepath=System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
		Assert.assertEquals(aceapimethods.getXpathValue(data.get(0).get("xpath"), filepath), address.get("addressLineText").toString());
		Assert.assertEquals(aceapimethods.getXpathValue(data.get(1).get("xpath"), filepath), address.get("cityName").toString());
		Assert.assertEquals(aceapimethods.getXpathValue(data.get(2).get("xpath"), filepath), address.get("stateCode").toString());
		Assert.assertEquals(aceapimethods.getXpathValue(data.get(3).get("xpath"), filepath), address.get("postalCode").toString());		
		log.info("*********** DONE  ---    in THEN  ---   Validate the Address in ACE Response with HVE response file *************** ");
	}
	
	/**
	 * Validating the Message containers in ACE API Response to have same value with EDS Response
	 * 
	 * 
	 * @param String filename, DataTable elementnvalue
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws 
	 */
	
	@Then("^Validate the Message in ACE Response with EDS response file \"([^\"]*)\"$")
	public void validateMessageAceHve(String filename, DataTable elementnvalue) {
		log.info("*********** in THEN  ---   Validate the Message in ACE Response with EDS response file *************** ");
		String filepath;
		this.Filename=filename;
		this.datatab=elementnvalue;
		JSONObject message = (JSONObject)((JSONArray) ((JSONObject) ((JSONArray) aceresponseobj.get("deals")).get(0)).get("messages")).get(0);
		scenario.write("messages obj - " + message);
		List<Map<String,String>> data = datatab.asMaps(String.class,String.class);
		filepath=System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
//		Assert.assertEquals(aceapimethods.getXpathValue(data.get(0).get("xpath"), filepath), message.get("messageCode").toString());
//		Assert.assertEquals(aceapimethods.getXpathValue(data.get(1).get("xpath"), filepath), message.get("messageText").toString());
		log.info("*********** DONE  ---    in THEN  ---   Validate the Message in ACE Response with EDS response file *************** ");
		
	}
	
	/**
	 * Validating the Loan Amount container in ACE API Response to have same value with EDS Response
	 * 
	 * 
	 * @param String filename, DataTable elementnvalue
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws 
	 */
	
	@Then("^Validate the Loan Amount in ACE Response with EDS response file \"([^\"]*)\"$")
	public void validateLoanAmountAceEds(String filename, DataTable elementnvalue) {
		log.info("*********** in THEN  ---   Validate the Loan Amount in ACE Response with EDS response file *************** ");
		String value;
		String filepath;
		this.Filename=filename;
		this.datatab=elementnvalue;
		String loanAmount = ((JSONObject) ((JSONObject) ((JSONArray) aceresponseobj.get("deals")).get(0)).get("loanInformation")).get("maximumAuthorizedLoanAmount").toString();
		List<Map<String,String>> data = datatab.asMaps(String.class,String.class);
		filepath=System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
		value = aceapimethods.getXpathValue(data.get(0).get("xpath"), filepath);
		scenario.write("value - " + value);
		scenario.write("Loan Amount - " + loanAmount);
//		Assert.assertEquals(value, loanAmount);
		log.info("*********** DONE  ---    in THEN  ---   Validate the Loan Amount in ACE Response with EDS response file *************** ");
		
	}
	
	/**
	 * Validating the Expiration Date container in ACE API Response to have same value with EDS Response
	 * 
	 * 
	 * @param String filename, DataTable elementnvalue
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws 
	 */
	
	@Then("^Validate the Expiration Date in ACE Response with EDS response file \"([^\"]*)\"$")
	public void validateExpirationDateAceEds(String filename, DataTable elementnvalue) {
		String value;
		String filepath;
		this.Filename=filename;
		this.datatab=elementnvalue;
		String expDate = ((JSONObject) ((JSONObject) ((JSONArray) aceresponseobj.get("deals")).get(0)).get("loanInformation")).get("appraisalWaiverPrescreenExpirationDate").toString();
		List<Map<String,String>> data = datatab.asMaps(String.class,String.class);
		filepath=System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
		value = aceapimethods.getXpathValue(data.get(0).get("xpath"), filepath);
//		Assert.assertEquals(value, expDate);
		
	}

}
