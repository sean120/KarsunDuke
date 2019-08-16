package com.seanfiles.stepdefinition;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.seanfiles.helper.ACEAPIHelperMethods;
import com.seanfiles.root.LoadTestConfig;
import com.seanfiles.utils.ReusableConstants;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EDSRequestResponseDB {

	private static Logger log = Logger.getLogger(EDSRequestResponseDB.class);
	public Scenario scenario;
	public Properties props;
	private String operation;
	private String jsonrequest;
	private String fileName;
	private DataTable datatab;
	public String strDate;
	public ACEAPIHelperMethods aceapimethods;
	public LoadTestConfig aceapiconfig;
	private String UserName = "";
	private String ProxyMSGID = "";
	private ContentType contentType = ContentType.JSON;
	private String contType = "application/json";
	protected RequestSpecification reqspec = null;

	// ================================Framework_SETUP================================\\

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
	 * Get the body to post to receive EDS API payload request
	 * 
	 * 
	 * @param String
	 *            filename, String username and String proxymsgid
	 * 
	 * @return Request Specification
	 * @author C36503 - COGNIZANT - LAS - ACE API
	 * @throws IOException
	 */

	@Given("^EDS Read input from json \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\" as header$")
	public RequestSpecification foredsapirequest(String filename, String username, String proxymsgid)
			throws IOException {
		log.info("*********** in GIVEN  ---   Preparing the ACE API Request for  *****************");
		this.fileName = filename;
		scenario.write("Reading the initial input from Json");
		jsonrequest = aceapimethods.generateStringFromResource(
				System.getProperty("user.dir").concat(props.getProperty("EDSRequestfile").concat(fileName)));
		this.UserName = username;
		this.ProxyMSGID = proxymsgid;
		this.contentType = aceapimethods.getcontenttype(contType);
		RestAssured.baseURI = props.getProperty("ACEAPIBaseUrl");
		RestAssured.useRelaxedHTTPSValidation();
		reqspec = RestAssured.given().contentType(contentType).header("X-Forwarded-UserName", UserName).and()
				.header("X-Forwarded-ProxyMessageId", ProxyMSGID).body(jsonrequest);
		reqspec.log().all();
		return reqspec;
	}

	/**
	 * Post the response to EDS API
	 * 
	 * 
	 * @param String
	 *            op
	 * 
	 * @return Response
	 * @author C36503 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */
	@When("^EDS Request is performing \"([^\"]*)\" on EDS API URL$")
	public Response post_request_EDSAPIService(String Op) throws Exception {
		log.info("*********** in WHEN  ---   posting the request to EDS API URL  *****************  ");
		String Resourcepath = props.getProperty("EDSAPIResourcePath");
		this.operation = Op;
		if (operation.equalsIgnoreCase(ReusableConstants.GET)) {
			scenario.write("Performing GET Operation");
			response = reqspec.when().get(Resourcepath);
		}
		if (operation.equalsIgnoreCase(ReusableConstants.POST)) {
			scenario.write("Performing POST Operation");
			response = reqspec.when().post(Resourcepath);
		}

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");// dd/MM/yyyy
		Date now = new Date();
		strDate = sdfDate.format(now);
		log.info("*********** in WHEN  ---   Done with posting the request to EDS API URL at " + strDate
				+ "*****************  ");
		System.out.println("post of request done at time " + strDate);
		return response;
	}

	/**
	 * Save the response as EDS API pauload service request file
	 * 
	 * 
	 * @param String
	 *            filename
	 * 
	 * @return N/A
	 * @author C36503 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */
	@Then("^EDS Save response code in xml \"([^\"]*)\"$")
	public void saveEdsResponse(String filename) throws Exception {
		String filepath;
		this.fileName = filename;
		scenario.write("Saving the EDS API payload request");
		if (response.getStatusCode() == 200) {
			log.info("response code received is :" + response.getStatusCode());
			scenario.write("response code received is :" + response.getStatusCode());
			log.info(" ACE API response is : " + response.then().extract().asString());
			scenario.write(" ACE API response is : " + response.then().extract().asString());
			filepath = System.getProperty("user.dir").concat(props.getProperty("EDSRequestfile").concat(fileName));
			aceapimethods.writeToFile(response.then().extract().asString(), filepath);
			log.info("Write the Request response in file '" + filename + "'.");
			scenario.write("Write the Request response in file '" + filename + "'.");
			/*
			 * Assert.
			 * assertEquals("Request cointain ns2:AlternativeLoanIdentifications/"
			 * , true, response.then().extract().asString().contains(
			 * "<ns2:AlternativeLoanIdentifications/>"));
			 * log.info("Request cointain ns2:AlternativeLoanIdentifications/");
			 * scenario.
			 * write("Request cointain ns2:AlternativeLoanIdentifications/");
			 */
		} else {
			log.info("response code received is :" + response.getStatusCode());
			scenario.write("response code received is :" + response.getStatusCode());
			Assert.fail("response code received is :" + response.getStatusCode());
		}
	}

	/**
	 * Validate API EDS request Payload file elements as per xpath
	 * 
	 * 
	 * @param String
	 *            filename, DataTable xpath and elements value
	 * 
	 * @return N/A
	 * @author C36503 - COGNIZANT - LAS - ACE API
	 * @throws IOException
	 */

	@Then("^EDS Verify the value for below xpath elements in \"([^\"]*)\"$")
	public void verifyedsreqelements(String filename, DataTable elementnvalue) throws IOException {
		String value;
		String filepath;
		this.fileName = filename;
		this.datatab = elementnvalue;
		log.info("*********** in THEN  ---  API EDS Request element verification scenarios  --- *****************");
		filepath = System.getProperty("user.dir").concat(props.getProperty("EDSRequestfile").concat(fileName));
		List<Map<String, String>> data = datatab.asMaps(String.class, String.class);
		for (int i = 0; i < data.size(); i++) {
			value = "Unable to retrive";
			value = aceapimethods.getXpathValue(data.get(i).get("xpath"), filepath);
			if (value.equals("No xpath exist")) {
				log.info("X-path '" + data.get(i).get("xpath") + "' not found in file".concat(fileName));
				scenario.write("X-path '" + data.get(i).get("xpath") + "' not found in file".concat(fileName));
				Assert.fail("X-path '" + data.get(i).get("xpath") + "' not found in file".concat(fileName));
			}

			else {
				log.info("X-path '" + data.get(i).get("xpath") + "' found in file".concat(fileName));
				scenario.write("X-path '" + data.get(i).get("xpath") + "' found in file".concat(fileName));
				// Assert.fail("X-path '"+data.get(i).get("xpath")+"' found in
				// file".concat(fileName));
			}
			/*
			 * if(data.get(i).get("value").equalsIgnoreCase(
			 * "YYYY-MM-DDThh:mm:ss.SSS")){ log.info("Value of xpath "+
			 * data.get(i).get("xpath") + " is : " + value);
			 * scenario.write("Value of xpath "+ data.get(i).get("xpath") +
			 * " is : " + value); int flag1 = 0; flag1 =
			 * aceapimethods.timeelentverification(strDate,value);
			 * Assert.assertEquals(3,flag1); }else
			 * if(data.get(i).get("value").equalsIgnoreCase("YYYY-MM-DD")){
			 * log.info("Value of xpath "+ data.get(i).get("xpath") + " is : " +
			 * value); scenario.write("Value of xpath "+
			 * data.get(i).get("xpath") + " is : " + value);
			 * Assert.assertEquals(strDate.substring(0, 10)+"-04:00",
			 * value.trim()); }else{ log.info("Value of xpath "+
			 * data.get(i).get("xpath") + " is : " + value);
			 * scenario.write("Value of xpath "+ data.get(i).get("xpath") +
			 * " is : " + value);
			 * Assert.assertEquals(data.get(i).get("value").trim(),
			 * value.trim()); }
			 */
		}
	}

	/**
	 * Validate API EDS request Payload file elements as per xpath for Proxy Id
	 * 
	 * 
	 * @param String
	 *            filename, DataTable xpath and elements value
	 * 
	 * @return N/A
	 * @author C36503 - COGNIZANT - LAS - ACE API
	 * @throws IOException
	 */

	@And("^EDS Verify the below xpath elements in \"([^\"]*)\" for proxyId$")
	public void verify_eds_reqelements_for_proxyid(String filename, DataTable elementnvalue) throws IOException {
		String value;
		String filepath;
		String proxyId;
		this.fileName = filename;
		this.datatab = elementnvalue;
		log.info("*********** in THEN  ---  API EDS Request element verification scenarios  --- *****************");
		filepath = System.getProperty("user.dir").concat(props.getProperty("EDSRequestfile").concat(fileName));
		List<Map<String, String>> data1 = datatab.asMaps(String.class, String.class);
		proxyId = aceapimethods.getXpathValue(data1.get(0).get("xpath"), filepath);
		// log.info("Proxy Id: "+proxyId);
		for (int i = 0; i < data1.size(); i++) {
			value = "Unable to retrive";
			value = aceapimethods.getXpathValue(data1.get(i).get("xpath"), filepath);
			log.info("Value of xpath " + data1.get(i).get("xpath") + " is : " + value);
			scenario.write("Value of xpath " + data1.get(i).get("xpath") + " is : " + value);
			Assert.assertEquals(proxyId.trim(), value.trim());
			// log.info("value: "+value);
		}
	}
}
