package com.seanfiles.stepdefinition;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.junit.Assert;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.seanfiles.helper.ACEAPIHelperMethods;
import com.seanfiles.root.LoadTestConfig;
import com.seanfiles.utils.ElementJsonPaths;
import com.seanfiles.utils.Messages;
import com.seanfiles.utils.ReusableConstants;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ACEApi2_0Service {

	private static Logger log = Logger.getLogger(ACEApi2_0Service.class);
	public Scenario scenario;
	public Properties props;

	public ACEAPIHelperMethods aceapimethods;
	public LoadTestConfig aceapiconfig;

	protected RequestSpecification reqspec;
	protected RequestSpecification aceRequestSpec;
	private String aceRequest = "";
	private String aceApiRequest = "";

	// ================================Framework_SETUP================================\\

	@Before
	public void loadTestScenario(Scenario scenario) {
		this.scenario = scenario;
		this.aceapiconfig = new LoadTestConfig();
		this.props = aceapiconfig.loadProperties();
		this.aceapimethods = new ACEAPIHelperMethods();
	}


	// ================================Framework_SETUP================================\\

	public Response response;
	static Response apiResponse;
	public String aceApiUrl = "";
	public String requestFolder = "";
	
	
	
	/**
	 * send ace api 2.0 request with specified file from the request files,
	 * and check for expected api response code
	 * 
	 * @param request
	 *            file name and response code
	 * 
	 * @return
	 * @author C46063 - Dmitriy - LAS - ACE API 2.0
	 * @return 
	 */


	

	@When("^User sends \"([^\"]*)\" ACE API 2.0 request  \"([^\"]*)\" and validates response code \"([^\"]*)\"$")
	public Response sendAIMAPIGEERequestUATReceivesCode(String testEnvironment, String requestFile, int responseCode) throws Exception {
		log.info("***********SENDING ACE API2.0 REQUEST*************");
		RestAssured.reset();
		aceRequest = ACEAPIHelperMethods.generateStringFromResource(
				System.getProperty("user.dir").concat(props.getProperty(testEnvironment.toLowerCase(), "RequestFiles").concat(requestFile +",json"))
	);
		RestAssured.baseURI = props.getProperty(testEnvironment.toLowerCase() + "uatACE2_0BaseUrl");
		scenario.write("request is " + aceRequest);
		String Resourcepath = props.getProperty("ACE2_0ResourcePath");
		aceRequestSpec = RestAssured
				.given()
				.auth()
				.preemptive()
				.basic("acestst1", "aceSpring%01Spring")
				.contentType(ACEAPIHelperMethods.getcontenttype("application/json"))
				.body(Resourcepath)
				.log()
				.all();
		
	for (int i=0; i<10; i++){
		try{
			apiResponse = aceRequestSpec
					.when()
					.post(Resourcepath);
					log.info("ace api 2.0 response is:" + apiResponse.then().extract().asString());
					break;		
		}catch (Exception e){
			
			Thread.sleep(1000);
			System.out.println("API conenction is Reset. Retrying attempt");
		}
	}
	
	
	for (int i =1; i<4; i++){
		if(!(apiResponse.statusCode()== responseCode)){
			Thread.sleep(5000);
			reqspec = RestAssured
					.given()
					.contentType(ACEAPIHelperMethods.getcontenttype("application/json"));
			apiResponse = aceRequestSpec.when().post(Resourcepath);	
	}else {break;}
		
	
		Assert.assertTrue(apiResponse.statusCode() == responseCode);
		scenario.write("ACE API 2.0 response is : " + apiResponse.then().extract().asString());
		log.info("ACE API 2.0 response is : " + apiResponse.then().extract().asString());
		
	}
	return apiResponse;
	}
	
	

	@Then("User validates that the ACE API 2.0 response element \"([^\"]*)\" equals \"([^\"]*)\"$")
	public void validateAIMApiIvanElementValue(String elementName, String expectedValue) {
		log.info("******************Validating ACE 2.0 Response*************************");

		ElementJsonPaths elementJasonPaths = new ElementJsonPaths();
		String jsonPath = elementJasonPaths.returnACE2_0ResponseElementJasonPath(elementName);
		String jsonresp = apiResponse.then().extract().asString();
		ReadContext JSONContext = JsonPath.parse(jsonresp);
		String stringRetrievedValue = null;
		try{
			stringRetrievedValue = JSONContext.read(jsonPath).toString();
			scenario.write(jsonPath + "    value is :  " + stringRetrievedValue);
			
		}catch(Exception exception){
			Assert.assertNotNull(stringRetrievedValue);
		}
			stringRetrievedValue = JSONContext.read(jsonPath).toString();
			scenario.write(jsonPath + "    value is :  " + stringRetrievedValue);
			if (!expectedValue.equals("NoValue")) {
				
				Assert.assertTrue(stringRetrievedValue.equals(expectedValue));
			}
	}
	
	/**
	 * Validate any value in the ACE 2.0 response
	 * 
	 * @param expected
	 *            value
	 * @return
	 * @author C46063 - Dmitriy - LAS - ACE API 2.0
	 */
	@Then("^User sees the expected \"([^\"]*)\" value in the ACE 2.0 response$")
	public void validateValueFromAce2_0Response(String expectedValue) {
		String responseAsString = "";

		if (apiResponse != null) {
			responseAsString = apiResponse.getBody().asString();
		}
		Assert.assertEquals(responseAsString.contains(expectedValue), true);
		if (responseAsString.contains(expectedValue)){
			scenario.write("Ace 2.0 Response contains expected value" +expectedValue);
			log.info("Ace 2.0 Response contains expected value" +expectedValue);
		}else{
			scenario.write("Ace 2.0 Response does not contain the expected value" +expectedValue);
			log.info("Ace 2.0 Response does not contain the expected value" +expectedValue);
		}
	}
	
	/**
	 * validate message text for response element
	 * 
	 * @param message
	 *            code name
	 * @return
	 * @author C46063 - Dmitriy - LAS - ACE API 2.0
	 */

	@Then("^User validates the expected message \"([^\"]*)\" and associated text in the response$")
	public void validateMessageResponse(String messageCodeName) {
		String responseAsString = "";
		String messageText = "";
		Messages respmessages = new Messages();

		messageText = respmessages.returnMessageText(messageCodeName);
		if (response != null) {
			responseAsString = response.getBody().asString();
		}

		Assert.assertEquals(responseAsString.contains(messageCodeName), true);
		Assert.assertEquals(responseAsString.contains(messageText), true);
		scenario.write("Error Response Message is : " + messageCodeName + " - " + messageText);
		log.info("Error Response Message is : " + messageCodeName + " - " + messageText);
	}


}
