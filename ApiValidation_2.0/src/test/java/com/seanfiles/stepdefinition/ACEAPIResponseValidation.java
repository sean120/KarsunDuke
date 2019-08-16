package com.seanfiles.stepdefinition;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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

public class ACEAPIResponseValidation {
	
	
	private static Logger log = Logger.getLogger(ACEAPIHVEIntegration.class);
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
	private Document AceResponse;
	private JSONObject aceresponseobj;
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
	
	@Given("^\"([^\"]*)\" and \"([^\"]*)\" combinations to get ACE Access Token at \"([^\"]*)\" path$")
	public void getApigeeAccessToken1(String apigeeuname, String apigeepwd, String accesstokenpath) throws Exception {
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
	
	@Given("^APIGEE Portal For ACE and a Valid \"([^\"]*)\"$")
	public void getInputFileContent1(String jsonfilename) throws Exception {
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
	 * Sending request to ACE API with access token
	 * 
	 * 
	 * @param 
	 *            
	 * 
	 * 
	 * @author
	 * @throws 
	 */

	@When("^Sending request to ACE with the valid access token$")
	public void sendRequestAceApi1() {

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
	 * Getting and saving ACE API Response
	 * 
	 * 
	 * @param
	 *            
	 * 
	 * 
	 * @author
	 * @throws Exception
	 */
	
	@Then("^Save ACE Response$")
	public void getAceResponse() throws Exception {
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
	 * Validating Eligibility indicator, address, message containers and Expiration Date are present in ACE Response
	 * 
	 * 
	 * @param DataTable elementnvalue
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws 
	 */
	
	@SuppressWarnings("unchecked")
	@Then("^Check for containers in ACE Response$")
	public void checkForContainersAceResponse(DataTable elementnvalue) {
		log.info("*********** in THEN  ---   Check for containers in ACE Response *************** ");
		this.datatab=elementnvalue;
		Set<String> loanInformation = ((JSONObject) ((JSONObject) ((JSONArray) aceresponseobj.get("deals")).get(0)).get("loanInformation")).keySet();
		Set<String> address = ((JSONObject) ((JSONObject) ((JSONArray) aceresponseobj.get("deals")).get(0)).get("address")).keySet();
		Set<String> message = ((JSONObject)((JSONArray)((JSONObject) ((JSONArray) aceresponseobj.get("deals")).get(0)).get("messages")).get(0)).keySet();
		Set<String> party = ((JSONObject)aceresponseobj.get("party")).keySet();
		Set<String> allKeys = new HashSet<>();
		allKeys.addAll(loanInformation);
		allKeys.addAll(address);
		allKeys.addAll(message);
		allKeys.addAll(party);
		scenario.write("List of Response Elements - " + allKeys);
		List<Map<String,String>> data = datatab.asMaps(String.class,String.class);
		for(int i = 0; i < data.size(); i++) {
			Assert.assertTrue(allKeys.contains((data.get(i).get("Element"))));
		}
		log.info("*********** DONE  ---    in THEN  ---   Check for containers in ACE Response  *************** ");
	}

}
