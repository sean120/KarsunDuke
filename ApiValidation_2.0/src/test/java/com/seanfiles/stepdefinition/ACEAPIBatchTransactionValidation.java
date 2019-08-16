package com.seanfiles.stepdefinition;

import java.io.IOException;
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
import org.json.simple.parser.ParseException;
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

public class ACEAPIBatchTransactionValidation {
	
	
	private static Logger log = Logger.getLogger(ACEAPIHVEIntegration.class);
	private Scenario scenario;
	private Properties props;
	private  ACEAPIHelperMethods aceapimethods;
	private LoadTestConfig aceapiconfig;
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
	private String contType = "application/json";
	private String accesstokencontType = "application/x-www-form-urlencoded";
	private RequestSpecification reqspec = null;

	@Before
	public void loadTestScenario(Scenario scenario) {
		this.scenario = scenario;
		this.aceapiconfig = new LoadTestConfig();
		this.props = aceapiconfig.loadProperties();
		this.aceapimethods = new ACEAPIHelperMethods();
	}

	private Response apigeeresponse;
	private Response aceapiresponse;
	
	/**
	 * Getting Apigee ACE API Access Token
	 * 
	 * 
	 * @param String apigeeuname, String apigeepwd, String accesstokenpath
	 *            
	 * 
	 * 
	 * @author
	 * @throws NullPointerException
	 */
	
	@Given("^BTI \"([^\"]*)\" and \"([^\"]*)\" combinations to get ACE Access Token at \"([^\"]*)\" path")
	public void getApigeeAccessTokenBTI(String apigeeuname, String apigeepwd, String accesstokenpath) throws NullPointerException {
		log.info("*********** in GIVEN  ---   getting Apigee ACE API Access Token  *************** ");
		this.apigeebaseurl = props.getProperty("APIGEEAccessTokenBaseUrl");
		this.grant_type = ReusableConstants.apigeegrantType;
		this.ClientId = ReusableConstants.clientID;
		this.ClientSecret = ReusableConstants.clientSecret;
		this.UserName = apigeeuname;//asda
		this.Password = apigeepwd;
		this.accesstokenresppath = accesstokenpath;
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
	
	@Given("^BTI APIGEE Portal For ACE and a Valid \"([^\"]*)\"$")
	public void getInputFileContentBTI(String jsonfilename) throws IOException {
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

	@When("^BTI Sending request to ACE with the valid access token$")
	public void sendRequestAceApiBTI() {

		log.info("*********** in WHEN  --- sending request to APIGEE ACE API *************** ");

		RestAssured.baseURI = apigeebaseurl;
		RestAssured.proxy("nzenpxy.fhlmc.com", 9400);

		RestAssured.config = RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

		reqspec = RestAssured.given().contentType(contType)
				.header("Authorization", "Bearer ".concat(accesstokenresppathvalue)).body(acejsonrequestcontent);
		reqspec.log().all();
		String Resourcepath = props.getProperty("APIGEEACEAPIResourcePath");
		scenario.write("Performing POST Operation");
		aceapiresponse = reqspec.when().post(Resourcepath);

		log.info("*********** Done - in WHEN  --- sending request to APIGEE ACE API *************** ");

	}
	
	/**
	 * Getting and saving ACE API Response
	 * 
	 * 
	 * @param String scen
	 *            
	 * 
	 * 
	 * @author
	 * throws ParseException
	 */
	
	@Then("^Save ACE Response BTI and checking for \"([^\"]*)\"$")
	public void getAceResponseBTI( String scen) throws ParseException {
		log.info("*********** in THEN  ---   Save ACE Response from MongoDB *************** ");
		if (aceapiresponse != null) {
			scenario.write("ACE - " + aceapiresponse.getBody().asString());
			JSONParser parser = new JSONParser();
			Object acejson = parser.parse(aceapiresponse.getBody().asString());
			aceresponseobj = (JSONObject) acejson;
		}
		scenario.write("status code - " + aceapiresponse.statusCode());
		if (aceapiresponse.statusCode() == 200 && "positive".equals(scen)) {
			aceapiresponse.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
		}else if(aceapiresponse.statusCode() == 400 && "negative".equals(scen)) {
			aceapiresponse.then().assertThat().statusCode(400);
		}
		log.info("ACE Response is :" + AceResponse);
		log.info("*********** DONE  ---    in THEN  ---   Save ACE Response from MongoDB  *************** ");
	}
	
	/**
	 * Validating Batch and Transaction identifiers are present in ACE Response
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
	@Then("^Check for Batch and Transaction identifiers in ACE Response$")
	public void checkForContainersAceResponseBTI(DataTable elementnvalue) {
		log.info("*********** in THEN  ---   Check for containers in ACE Response *************** ");
		this.datatab=elementnvalue;
		Set<String> aceKeys = aceresponseobj.keySet();
		Set<String> dealsKeys =((JSONObject)((JSONArray)aceresponseobj.get("deals")).get(0)).keySet();
		Set<String> allKeys = new HashSet<>();
		allKeys.addAll(aceKeys);
		allKeys.addAll(dealsKeys);
		scenario.write("Set of Response Elements - " + allKeys);
		List<Map<String,String>> data = datatab.asMaps(String.class,String.class);
		for(int i = 0; i < data.size(); i++) {
			Assert.assertTrue(allKeys.contains(data.get(i).get("Element")));
		}
		log.info("*********** DONE  ---    in THEN  ---   Check for containers in ACE Response  *************** ");
	}
	
	/**
	 * Validating Batch and Transaction identifiers value to be same as in request in ACE Response
	 * 
	 * 
	 * @param String batchvalue, String transactionvalue
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws 
	 */
	
	@Then("^Check for Batch and Transaction identifiers \"([^\"]*)\" and \"([^\"]*)\" to be same$")
	public void checkForValueContainersAceResponseBTI(String batchvalue, String transactionvalue) {
		log.info("*********** in THEN  ---   Check for containers in ACE Response *************** ");

		try {
			String batch = aceresponseobj.get("requestBatchIdentifier").toString();
			String transaction =((JSONObject)((JSONArray)aceresponseobj.get("deals")).get(0)).get("requestTransactionIdentifier").toString();
			scenario.write("Batch Identifier is: " + batch);
			scenario.write("Transaction Identifier is: " + transaction);	
			Assert.assertEquals("Batch identifier is not same in request and response",batch,batchvalue);
			Assert.assertEquals("Transaction identifier is not same in request and response",transaction,transactionvalue);
			log.info("*********** DONE  ---    in THEN  ---   Check for containers in ACE Response  *************** ");
		}
		catch(NullPointerException e){
			Assert.fail("Batch or Transaction identifier is null");
		}
		
	}
	
	/**
	 * Validate the Batch Identifier and Transaction Identifier length
	 * 
	 * @param 
	 * @return
	 * @author C47174 - LAS - ACE API
	 * @throws
	 */
	
	
	@Then("^Validate the Batch Identifier and Transaction Identifier is don't have length more than 45 chars$")
	public void validateBatchTransactionIdentifier() {
		String requestBatchIdentifier = aceresponseobj.get("requestBatchIdentifier").toString();
		String requestTransactonidentifier =  ((JSONObject) ((JSONArray) aceresponseobj.get("deals")).get(0)).get("requestTransactionIdentifier").toString();
		scenario.write("Batch Identifier is: "+requestBatchIdentifier);
		scenario.write("Transaction Identifier is: "+requestTransactonidentifier);
		Assert.assertTrue("Batch Identifier have more than 45 chars",requestBatchIdentifier.length()<46);
		Assert.assertTrue("Transaction Identifier have more than 45 chars",requestTransactonidentifier.length()<46);
	}
	
	/**
	 * Validate any message text in JSON response according to messageCodeName
	 * 
	 * @param String messageCodeName
	 * @return
	 * @author C41181 - LAS - ACE API
	 * @throws
	 */
	
	@Then("^User validates the expected message code \"([^\"]*)\" and associated text in the response BTI$")
	public void validateMessageCodeResponseBTI(String messageCodeName) {
		String responseAsString = "";
		String[] errorresponsecodes;
		boolean multiplecodes = false;
		if (messageCodeName.contains(",")) {
			multiplecodes = true;
			errorresponsecodes = messageCodeName.split(",");
			scenario.write(aceapiresponse.then().extract().body().asString());
			for (int i = 0; i < errorresponsecodes.length; i++) {
				if (aceapiresponse != null) {
					log.info(" ACE API response is : " + aceapiresponse.then().extract().asString());
					responseAsString = aceapiresponse.getBody().asString();
				}
				Assert.assertEquals(responseAsString.contains(errorresponsecodes[i]), true);
				scenario.write("Error Response Message is : " + errorresponsecodes[i]);
			}

		}

		if (!multiplecodes) {
			if (aceapiresponse != null) {
				scenario.write(aceapiresponse.then().extract().body().asString());
				log.info(" ACE API response is : " + aceapiresponse.then().extract().asString());
				responseAsString = aceapiresponse.getBody().asString();
			}

			Assert.assertEquals(responseAsString.contains(messageCodeName), true);
			scenario.write("Error Response Message is : " + messageCodeName);
		}
	}
	
	/**
	 * Validating Batch and Transaction identifiers are present in ACE Response
	 * 
	 * 
	 * @param String missing
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws 
	 */
	
	@SuppressWarnings("unchecked")
	@Then("^Check for Batch or Transaction identifiers in ACE Response for \"([^\"]*)\" element$")
	public void checkForMissingContainersAceResponseBTI(String missing) {
		log.info("*********** in THEN  ---   Check for containers in ACE Response *************** ");
		Boolean multiplemissing=false;
		String[] missingValues;
		Set<String> aceKeys = aceresponseobj.keySet();
		Set<String> dealsKeys =((JSONObject)((JSONArray)aceresponseobj.get("deals")).get(0)).keySet();
		Set<String> allKeys = new HashSet<>();
		allKeys.addAll(aceKeys);
		allKeys.addAll(dealsKeys);
		scenario.write(missing + " should be missing in ACE API Response" );
		if(missing.contains(",")) {
			multiplemissing=true;
			missingValues = missing.split(",");
			for (int i = 0; i < missingValues.length; i++) {
				Assert.assertFalse(missingValues[i]+" is present",allKeys.contains(missingValues[i]));
			}
		}
		
		if(!multiplemissing) {
			Assert.assertFalse(missing+" is present",allKeys.contains(missing));
		}		
		
		log.info("*********** DONE  ---    in THEN  ---   Check for containers in ACE Response  *************** ");
	}


}
