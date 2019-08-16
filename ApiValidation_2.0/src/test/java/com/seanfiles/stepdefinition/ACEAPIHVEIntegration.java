package com.seanfiles.stepdefinition;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
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

public class ACEAPIHVEIntegration {

	private static Logger log = Logger.getLogger(ACEAPIHVEIntegration.class);
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
	private String JSONRequest = "";
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

	@Given("^HVE \"([^\"]*)\" and \"([^\"]*)\" combinations to get ACE API Access Token at \"([^\"]*)\" path$")
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
		}else {
			scenario.write("We received status code - " + apigeeresponse.statusCode());
		}
		log.info("*********** Done ---  in GIVEN  ---   getting Apigee ACE API Access Token *************** ");

	}
	
	/**
	 * getting Apigee ACE API request file content
	 * 
	 * 
	 * @param String jsonfilename
	 *            
	 * 
	 * 
	 * @author
	 * @throws Exception
	 */
	
	@Given("^APIGEE Developer Portal For ACE API and a Valid HVE \"([^\"]*)\"$")
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
	 * @throws Exception
	 */

	@When("^Sending request to ACE API with the access token$")
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
		if (aceapiresponse != null) {
			scenario.write("Received Response is :" + aceapiresponse.then().extract().asString());
			scenario.write("response code received is :" + aceapiresponse.getStatusCode());
			aceapiresponse.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
		}
		log.info("*********** Done - in WHEN  --- sending request to APIGEE ACE API *************** ");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//dd/MM/yyyy
	    Date now = new Date();
	    strDate = sdfDate.format(now);

	}
	
	/**
	 * Sending request to APIGEE ACE API with missing data and checking for error code
	 * 
	 * 
	 * @param int errorCode
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws
	 */
	
	@Then("^Sending request with missing data to ACE API with the access token and checking for \"([^\"]*)\"$")
	public void sendWrongRequestAceApi(int errorCode) {

		log.info("*********** in WHEN  --- Sending request with missing data to ACE API with the access token *************** ");

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
		if (aceapiresponse != null) {
			scenario.write("Received Response is :" + aceapiresponse.then().extract().asString());
			scenario.write("response code received is :" + aceapiresponse.getStatusCode());
			aceapiresponse.then().assertThat().statusCode(errorCode);
		}
		log.info("*********** Done - in WHEN  --- Sending request with missing data to ACE API with the access token *************** ");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//dd/MM/yyyy
	    Date now = new Date();
	    strDate = sdfDate.format(now);

	}

	/**
	 * Checking MongoDB for HVE request to be present
	 * 
	 * 
	 * @param 
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */
	
	@Then("^Check Mongo DB for HVE request$")
	public void checkHVERequest() throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For HVE Request *************** ");
		JSONRequest = aceapimethods.getHVERequestMostRecent();
		scenario.write("HVE Request is : " + JSONRequest);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For HVE Request  *************** ");
	}
	
	/**
	 * Saving HVE Request in xml file 
	 * 
	 * 
	 * @param String filename
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */
	
	@Then("^HVE Request Save code in xml file \"([^\"]*)\"$")
	public void saveHveRequestXml(String filename) throws Exception {
		String filepath;
		this.Filename=filename;
		scenario.write("Saving the HVE API request");
		if (!(JSONRequest.equalsIgnoreCase(""))){
			filepath = System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
			aceapimethods.writeToFile(JSONRequest, filepath);
			log.info("Write the fetched request in file '"+ filename +"'.");
			scenario.write("Write the fetched request in file '"+ filename +"'.");
		} else {
			  log.info("Request is Blank");
			  scenario.write("Request is Blank");
			  Assert.fail("Request is Blank");
		  }
	}
	
	/**
	 * Verify xpaths to be present and their value in HVE Request
	 * 
	 * 
	 * @param String filename, DataTable elementnvalue
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws IOException
	 */
	
	@Then("^HVE Request, Verify the value for below xpath elements in \"([^\"]*)\"$")
	public void verifyHveRequestElements(String filename, DataTable elementnvalue) throws IOException {
		String value;
		String filepath;
		this.Filename=filename;
		this.datatab=elementnvalue;
		log.info("*********** in THEN  ---  API HVE Request element verification scenarios  --- *****************");
		filepath=System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
		List<Map<String,String>> data = datatab.asMaps(String.class,String.class);
		for(int i = 0; i < data.size(); i++) {
			value="Unable to retrive";
			value = aceapimethods.getXpathValue(data.get(i).get("xpath"), filepath);
			if(value.equals("No xpath exist")) {
				log.info("X-path '"+data.get(i).get("xpath")+"' not found in file".concat(Filename));
				scenario.write("X-path '"+data.get(i).get("xpath")+"' not found in file".concat(Filename));
				Assert.fail("X-path '"+data.get(i).get("xpath")+"' not found in file".concat(Filename));
			}
			if(data.get(i).get("value").equalsIgnoreCase("YYYY-MM-DDThh:mm:ss.SSS")){
				log.info("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
				scenario.write("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
			}else{
			log.info("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
			scenario.write("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
			Assert.assertEquals(data.get(i).get("value").trim(), value.trim());
			}
		}
	}
	
	
	/**
	 * Checking MongoDB for HVE Response
	 * 
	 * 
	 * @param String filename
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */
	
	@Then("^Check Mongo DB for HVE response$")
	public void checkHVEResponce() throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For HVE Responce *************** ");
		JSONResponse = aceapimethods.getHVEResponseMostRecent();
		scenario.write("HVE Responce is : " + JSONResponse);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For HVE Responce  *************** ");
	}
	
	/**
	 * Saving HVE Response in xml file 
	 * 
	 * 
	 * @param String filename
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */
	
	@Then("^HVE Response Save code in xml file \"([^\"]*)\"$")
	public void saveHveResponseXml(String filename) throws Exception {
		log.info("*********** in THEN  ---   HVE Response Save code in xml file *************** ");
		String filepath;
		this.Filename=filename;
		scenario.write("Saving the HVE API response");
		if (!(JSONResponse.equalsIgnoreCase(""))){
			filepath = System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
			aceapimethods.writeToFile(JSONResponse, filepath);
			log.info("Write the fetched request in file '"+ filename +"'.");
			scenario.write("Write the fetched request in file '"+ filename +"'.");
		} else {
			  log.info("Response is Blank");
			  scenario.write("Response is Blank");
			  Assert.fail("Response is Blank");
		  }
		log.info("*********** DONE  ---    in THEN  ---   HVE Response Save code in xml file  *************** ");
	}
	
	/**
	 * Verifying xpaths to be present and their value in HVE Response
	 * 
	 * 
	 * @param String filename, DataTable elementnvalue
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws IOException
	 */

	@Then("^HVE Response, Verify the value for below xpath elements in \"([^\"]*)\"$")
	public void verifyHveResponseElements(String filename, DataTable elementnvalue) throws IOException {
		String value;
		String filepath;
		this.Filename=filename;
		this.datatab=elementnvalue;
		log.info("*********** in THEN  ---  HVE Response, Verify the value for below xpath elements  --- *****************");
		filepath=System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
		List<Map<String,String>> data = datatab.asMaps(String.class,String.class);
		for(int i = 0; i < data.size(); i++) {
			value="Unable to retrive";
			value = aceapimethods.getXpathValue(data.get(i).get("xpath"), filepath);
			if(value.equals("No xpath exist")) {
				log.info("X-path '"+data.get(i).get("xpath")+"' not found in file".concat(Filename));
				scenario.write("X-path '"+data.get(i).get("xpath")+"' not found in file".concat(Filename));
				Assert.fail("X-path '"+data.get(i).get("xpath")+"' not found in file".concat(Filename));
			}else if (value.equals("Multiple xpaths")) {
				log.info("X-path '"+data.get(i).get("xpath")+"' multiple in file".concat(Filename));
				scenario.write("X-path '"+data.get(i).get("xpath")+"' multiple in file".concat(Filename));
				Assert.fail("X-path '"+data.get(i).get("xpath")+"' multiple in file".concat(Filename));
			}
			
			if(data.get(i).get("value").equalsIgnoreCase("YYYY-MM-DDThh:mm:ss.SSS")){
				log.info("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
				scenario.write("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
			}else{
			log.info("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
			scenario.write("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
			if(!data.get(i).get("value").equals("")) {
				Assert.assertEquals(data.get(i).get("value").trim(), value.trim());
			}
			}
		}
		log.info("*********** DONE  ---    in THEN  ---   HVE Response, Verify the value for below xpath elements  *************** ");
	}
	
	/**
	 * Checking message for Error Code
	 * 
	 * 
	 * @param String errorCode, String filename
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws 
	 */
	
	@Then("^Checking Message Error Code \"([^\"]*)\" in \"([^\"]*)\"$")
	public void verifyMessageErrorCode(String errorCode, String filename) {
		log.info("*********** in THEN  ---  Checking Message Error Code  --- *****************");
		String filepath;
		String value;
		this.Filename=filename;
		filepath=System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
		value = aceapimethods.getXpathValue("//HVSResponseContainers/HVSResponseContainer/Messages/Message/MessageCode", filepath);
		Assert.assertTrue(value.equals(errorCode));
		scenario.write("Message code - " + value);	
		log.info("*********** DONE  ---    in THEN  ---   Checking Message Error Code  *************** ");
	}
	

}
