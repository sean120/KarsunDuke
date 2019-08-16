package com.seanfiles.stepdefinition;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
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

public class ACEAPILoanAmountCharacters {

	
	private static Logger log = Logger.getLogger(ACEAPILoanAmountCharacters.class);
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
	private JSONObject aceresponseobj;
	private String EDSXMLResponse;
	private DataTable datatab;
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
	
	@Given("^EDS \"([^\"]*)\" and \"([^\"]*)\" combinations to get ACE Access Token at \"([^\"]*)\"$")
	public void getValidApigeeAccessTokenEDS(String apigeeuname, String apigeepwd, String accesstokenpath) throws Exception {
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
	 * Getting Apigee ACE API request file content
	 * 
	 * 
	 * @param String jsonfilename
	 *          
	 * 
	 * @author 
	 * @throws Exception 
	 */
	
	@Given("^APIGEE For ACE API and a Valid \"([^\"]*)\"$")
	public void getInputFileContentToRequest2(String jsonfilename) throws Exception {
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
	 * @throws Exception 
	 */

	@When("^Sending our request to ACE API with the valid access token$")
	public void sendRequestToAceApi2() {

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
	 * Getting and saving ACE API response
	 * 
	 * 
	 * @param
	 *          
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws Exception 
	 */

	@Then("^Get our ACE API response")
	public void getAceResponse2() throws Exception {
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
	 * Checking MongoDB for EDS Request
	 * 
	 * 
	 * @param
	 *          
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws Exception 
	 */
	
	@Then("^Check MongoDB for our EDS Request$")
	public String checkMongoDBEDSRequest() throws Exception {
		log.info("*********** in THEN  ---   Verify MongoDB For EDS Request *************** ");
		if (aceapiresponse != null) {
			EDSXMLResponse = aceapimethods.getEDSRequestMostRecent();
			scenario.write("EDS Request is : " + EDSXMLResponse);
		}
		log.info("EDS Response is :" + EDSXMLResponse);
		log.info("*********** DONE  ---    in THEN  ---   Verify MongoDB For EDS Request  *************** ");
		return EDSXMLResponse;

	}
	
	/**
	 * Saving EDS Request in xml file
	 * 
	 * 
	 * @param String filename
	 *          
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws Exception 
	 */
	
	@Then("^save EDS Request in our file \"([^\"]*)\"$")
	public void saveEDSRequestXml(String filename) throws Exception {
		String filepath;
		this.Filename=filename;
		scenario.write("Saving the EDS Request");
		if (!(EDSXMLResponse.equalsIgnoreCase(""))){
			filepath = System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
			aceapimethods.writeToFile(EDSXMLResponse, filepath);
			log.info("Write the fetched request in file '"+ filename +"'.");
			scenario.write("Write the fetched request in file '"+ filename +"'.");
		} else {
			  log.info("EDS Request is Blank");
			  scenario.write("EDS Request is Blank");
			  Assert.fail("EDS Request is Blank");
		  }
	}
	
	/**
	 * Checking for Loan Identifiers matching schema(length, contains only digits and to be the same)
	 * 
	 * 
	 * @param String filename, DataTable elementnvalue
	 *          
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws
	 */
	
	@Then("^Check for element characters, according to acceptance criteria in \"([^\"]*)\"$")
	public void checkLoanAmountCharactersLimit(String filename, DataTable elementnvalue) {
		String value;
		String filepath;
		this.Filename=filename;
		this.datatab=elementnvalue;
		List<Map<String,String>> data = datatab.asMaps(String.class,String.class);
		filepath = System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
		String FirstXpathValue = aceapimethods.getXpathValue(data.get(0).get("xpath"), filepath);
		for(int i = 0; i < data.size(); i++) {
			value="Unable to retrive";
			value = aceapimethods.getXpathValue(data.get(i).get("xpath"), filepath);
			if(value.equals("No xpath exist")) {
				log.info("X-path '"+data.get(i).get("xpath")+"' not found in file ".concat(Filename));
				scenario.write("X-path '"+data.get(i).get("xpath")+"' not found in file ".concat(Filename));
				Assert.fail("X-path '"+data.get(i).get("xpath")+"' not found in file ".concat(Filename));
			}else if (value.equals("Multiple xpaths")) {
				log.info("X-path '"+data.get(i).get("xpath")+"' multiple in file ".concat(Filename));
				scenario.write("X-path '"+data.get(i).get("xpath")+"' multiple in file ".concat(Filename));
				Assert.fail("X-path '"+data.get(i).get("xpath")+"' multiple in file ".concat(Filename));
			}
			
			if(data.get(i).get("value").equalsIgnoreCase("YYYY-MM-DDThh:mm:ss.SSS")){
				log.info("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
				scenario.write("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
			}else{
				log.info("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
				scenario.write("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
				Assert.assertTrue("Value is more then 12 characters",value.length()<13);
				Assert.assertTrue("Loan Identifier not matching schema", value.matches("^[0-9]+$"));
				Assert.assertEquals("Loan Identifiers not the same",FirstXpathValue,value);
			}

		}
	}
	
	/**
	 * Checking for Unique ID be equals '1'
	 * 
	 * 
	 * @param String filename, DataTable elementnvalue
	 *          
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws
	 */
	
	@Then("^Check for Unique ID element characters, according to acceptance criteria in \"([^\"]*)\"$")
	public void checkLoanAmountCharactersUniqueID(String filename, DataTable elementnvalue) {
		String value;
		String filepath;
		this.Filename=filename;
		this.datatab=elementnvalue;
		List<Map<String,String>> data = datatab.asMaps(String.class,String.class);
		filepath = System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
		for(int i = 0; i < data.size(); i++) {
			value="Unable to retrive";
			value = aceapimethods.getXpathValue(data.get(i).get("xpath"), filepath);
			if(value.equals("No xpath exist")) {
				log.info("X-path '"+data.get(i).get("xpath")+"' not found in file ".concat(Filename));
				scenario.write("X-path '"+data.get(i).get("xpath")+"' not found in file ".concat(Filename));
				Assert.fail("X-path '"+data.get(i).get("xpath")+"' not found in file ".concat(Filename));
			}else if (value.equals("Multiple xpaths")) {
				log.info("X-path '"+data.get(i).get("xpath")+"' multiple in file ".concat(Filename));
				scenario.write("X-path '"+data.get(i).get("xpath")+"' multiple in file ".concat(Filename));
				Assert.fail("X-path '"+data.get(i).get("xpath")+"' multiple in file ".concat(Filename));
			}
			
			if(data.get(i).get("value").equalsIgnoreCase("YYYY-MM-DDThh:mm:ss.SSS")){
				log.info("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
				scenario.write("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
			}else{
				log.info("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
				scenario.write("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
				Assert.assertEquals("Loan Identifiers Unique ID not equals '1'","1",value);
			}

		}
	}
			
	
}
