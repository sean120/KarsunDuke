package com.seanfiles.stepdefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

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
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidateEDSDBResponse {

	private static Logger log = Logger.getLogger(ValidateEDSDBResponse.class);
	public Scenario scenario;
	public Properties props;
	private String EDSXMLResponse;
	private String EDSXMLRequest;
	private String HVEXMLResponse;
	private DataTable datatab;
	private String accesstoken = "";
	public ACEAPIHelperMethods aceapimethods;
	public LoadTestConfig aceapiconfig;
	private String apigeebaseurl = "";
	private String aceapirequestfilename = "";
	private String acejsonrequest = "";
	private String reqsalesContramountjsonpath = "$.deals[0].loanInformation.salesContractAmount";
	private String reqpropertyEstiValueAmountjsonpath = "$.deals[0].loanInformation.propertyEstimatedValueAmount";
	private double reqpropertyEstiValueAmount;
	private double reqsalesContramount;
	private String reqloanpurposetypejsonpath = "$.deals[0].loanInformation.loanPurposeType";
	private String reqloanpurposetype = "";
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
	 * Prepare a APIGEE ACCESS Token Request
	 * 
	 * 
	 * @param String requestfile
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws IOException
	 */

	@Given("^APIGEE Developer Portal ACE API Valid AccessToken and Valid \"([^\"]*)\"$")
	public void apigeeAceRequest(String requestfile) throws IOException {

		log.info("*********** in GIVEN  ---   getting APIGEE Request URL and Access Token *************** ");
		this.apigeebaseurl = props.getProperty("APIGEEAccessTokenBaseUrl");
		this.accesstoken = props.getProperty("apigeeaccesstoken");
		if (requestfile != null) {
			this.aceapirequestfilename = requestfile;
		}
		if (aceapirequestfilename != null) {

			acejsonrequest = aceapimethods.generateStringFromResource(System.getProperty("user.dir")
					.concat(props.getProperty("schemavalfile").concat(aceapirequestfilename)));
			scenario.write("Reqeust is : " + acejsonrequest);
			ReadContext JSONContext = JsonPath.parse(acejsonrequest);
			reqloanpurposetype = JSONContext.read(reqloanpurposetypejsonpath);

			if (reqloanpurposetype.equalsIgnoreCase("Purchase")) {

				reqsalesContramount = JSONContext.read(reqsalesContramountjsonpath);
				scenario.write(reqsalesContramountjsonpath + "    value is :  " + reqsalesContramount);
			}

			if (reqloanpurposetype.equalsIgnoreCase("Refinance")) {
				reqpropertyEstiValueAmount = JSONContext.read(reqpropertyEstiValueAmountjsonpath);
				scenario.write(reqpropertyEstiValueAmountjsonpath + "    value is :  " + reqpropertyEstiValueAmount);
			}

		}
		log.info("*********** Done ---  in GIVEN  ---   getting APIGEE Request URL and Access Token *************** ");
	}
	
	/**
	 * Sending request to APIGEE ACE API
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@When("^User Sends request to APIGEE Gateway ACE API$")
	public void sendApigeeRequest() {

		log.info("*********** in WHEN  --- sending request to APIGEE ACE API *************** ");

		RestAssured.baseURI = apigeebaseurl;
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.proxy(ReusableConstants.InternetProxyHost, ReusableConstants.InternetProxyPort);
		// RestAssured.config =
		// RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8").appendDefaultContentCharsetToContentTypeIfUndefined(false));

		RestAssured.config = RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

		reqspec = RestAssured.given().contentType(contentType).header("Authorization", "Bearer ".concat(accesstoken))
				.body(acejsonrequest);
		reqspec.log().all();
		String Resourcepath = props.getProperty("APIGEEACEAPIResourcePath");
		scenario.write("Performing POST Operation");
		response = reqspec.when().post(Resourcepath);
		log.info("Heelllo i am in POST Operation completed");

		log.info("*********** Done - in WHEN  --- sending request to APIGEE ACE API *************** ");

	}
	
	/**
	 * Receiving ACE API Response
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws
	 */

	@Then("^ACE API sends back ACE Response$")
	public void aceapigeeresp() {
		log.info("*********** in THEN  ---   APIGEE Ace API Response *************** ");
		if (response != null) {
			scenario.write("Received Response is :" + response.then().extract().asString());
			scenario.write("response code received is :" + response.getStatusCode());
			response.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
		}
		log.info("*********** DONE  ---    in THEN  ---   APIGEE ACE API Response *************** ");
	}
	
	/**
	 * Check MongoDB for EDS Response
	 * 
	 * 
	 * @param
	 * 
	 * @return EDS XML Response
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws Exception
	 */

	@Then("^Check Mongo DB for EDS Response$")
	public String checkMongoEDSResp() throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For EDS Response *************** ");
		EDSXMLResponse = "";
		if (response != null) {
			EDSXMLResponse = aceapimethods.getEDSResponseMostRecent();
			scenario.write("EDS Response is : " + EDSXMLResponse);
		}
		log.info("EDS Response is :" + EDSXMLResponse);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For EDS Response  *************** ");
		return EDSXMLResponse;

	}
	
	/**
	 * Checking MongoDB for EDS Request and HVE Response
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws Exception
	 */

	@Then("^Check Mongo DB for HVE Response and EDS Request$")
	public void checkMongoEDSReqHVEResp() throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For EDS Request and HVE Response *************** ");
		EDSXMLResponse = "";
		HVEXMLResponse = "";
		if (response != null) {
			EDSXMLRequest = aceapimethods.getEDSRequestMostRecent();
			HVEXMLResponse = aceapimethods.getHVEResponseMostRecent();
			scenario.write("EDS Request is : " + EDSXMLRequest);
			scenario.write("HVE Response is : " + HVEXMLResponse);
		}
		log.info("EDS Response is :" + EDSXMLResponse);
		log.info(
				"*********** DONE  ---    in THEN  ---   Verify Mongo DB For EDS Request and HVE Response  *************** ");

	}
	
	/**
	 * Verifying EDS Request Default Mappings
	 * 
	 * 
	 * @param DataTaable elementnvalue
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws Exception
	 */
	
	@Then("^Validate default mapping values$")
	public void verifyEDSReqDefaultMappings(DataTable elementnvalue) throws Exception {
		log.info("*********** in THEN  ---   Verify EDS Request Default Mappings *************** ");
		String value = "";
		this.datatab=elementnvalue;
		log.info("*********** in THEN  ---  API HVE Request element verification scenarios  --- *****************");
		List<Map<String,String>> data = datatab.asMaps(String.class,String.class);
		if(EDSXMLRequest != null) {
		for(int i = 0; i < data.size(); i++) {
			value="Unable to retrive";
			value = aceapimethods.getXpathValueFromXMLString(data.get(i).get("xpath"), EDSXMLRequest);
			if(value.equals("No xpath exist")) {
				log.info("X-path '"+data.get(i).get("xpath")+"' not found in EDS Request");
				scenario.write("X-path '"+data.get(i).get("xpath")+"' not found in EDS Request");
				Assert.fail("X-path '"+data.get(i).get("xpath")+"' not found in EDS Request");
			}
			if(data.get(i).get("value").equalsIgnoreCase("YYYY-MM-DDThh:mm:ss.SSS")){
				log.info("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
				scenario.write("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
			}else{
			log.info("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
			scenario.write("Value of xpath "+ data.get(i).get("xpath") + " is : " + value);
			Assert.assertEquals(data.get(i).get("value").trim(), value.trim());
			}
		}}
		log.info("*********** DONE  ---    in THEN  ---   Verify EDS Request Default Mappings  *************** ");

	}
	
	/**
	 * Checking MongoDB for EDS Request
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws Exception
	 */

	@Then("^Check Mongo DB for EDS Request$")
	public String checkMongoEDSReq() throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For EDS Request *************** ");
		EDSXMLRequest = "";
		if (response != null) {
			EDSXMLRequest = aceapimethods.getEDSRequestMostRecent();
			scenario.write("EDS Request is : " + EDSXMLRequest);
		}
		log.info("EDS Request is :" + EDSXMLRequest);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For EDS Request  *************** ");
		return EDSXMLRequest;

	}
	
	/**
	 * Validating MongoDB for EDS Request and HVE Response
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws Exception
	 */

	@Then("^Validate the EDS Request$")
	public void validateMongoDBEDSReq() throws Exception {
		log.info("*********** in THEN  ---   Validate Mongo DB For EDS Request *************** ");

		if (response != null) {
			if (EDSXMLRequest != null) {

				log.info("EDS Request is in validation :   " + EDSXMLRequest);
				Document docmnt = aceapimethods.convertStringToDocument(EDSXMLRequest);
				XPath xPath = XPathFactory.newInstance().newXPath();

				if (reqloanpurposetype.equalsIgnoreCase("Purchase")) {
					XPathExpression expr = xPath
							.compile(ReusableConstants.EDSPropertyPurchasePriceAmount.concat("/text()"));
					Double result = (Double) expr.evaluate(docmnt, XPathConstants.NUMBER);
					log.info("Result is :    " + result);
					scenario.write("Mapped EDS Request property purchase price amount for property purchase is  :    "
							+ result);
				Assert.assertEquals(Double.doubleToLongBits(reqsalesContramount),
							Double.doubleToLongBits(result.doubleValue()));
				}

				if (reqloanpurposetype.equalsIgnoreCase("Refinance")) {

					XPathExpression expr = xPath
							.compile(ReusableConstants.EDSPropertyValuationAmount.concat("/text()"));
					Double result = (Double) expr.evaluate(docmnt, XPathConstants.NUMBER);
					log.info("Result is :    " + result);
					scenario.write("Mapped EDS Request property estimated value amount for property refinance is  :    "
							+ result);
					Assert.assertEquals(Double.doubleToLongBits(reqpropertyEstiValueAmount),
							Double.doubleToLongBits(result.doubleValue()));
				}
				
			}
			log.info("*********** DONE  ---    in THEN  ---   Validate Mongo DB For EDS Request  *************** ");
		}
	}
	
	/**
	 * Validating MongoDB for EDS Response
	 * 
	 * 
	 * @param
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API 
	 * @throws Exception
	 */

	@Then("^Validate the EDS Response$")
	public void validateMongoDBEDSResp() throws Exception {
		log.info("*********** in THEN  ---   Validate Mongo DB For EDS Response *************** ");
		NodeList conclusionnodes = null;
		if (response != null) {
			if (EDSXMLResponse != null) {
				Document doc = aceapimethods.convertStringToDocument(EDSXMLResponse);
				// scenario.write(aceapimethods.prettyPrint(doc).toString());
				XPath xPath = XPathFactory.newInstance().newXPath();
				XPathExpression decisionexpr = xPath.compile(ReusableConstants.decisionfromEDSRespxpath);
				NodeList decisionnodes = (NodeList) decisionexpr.evaluate(doc, XPathConstants.NODESET);
				scenario.write("Decision Nodes Length is : " + decisionnodes.getLength());
				XPathExpression conclusionexpr = xPath.compile(ReusableConstants.conclusionfromEDSRespxpath);
				for (int i = 0; i < decisionnodes.getLength(); i++) {
					conclusionnodes = (NodeList) conclusionexpr.evaluate(decisionnodes.item(i), XPathConstants.NODE);
					if (conclusionnodes.getLength() > 0) {
						scenario.write(" Retrieved Conclusion Value is : " + conclusionnodes.item(0).getTextContent());
					}
				}
				Assert.assertTrue(conclusionnodes.getLength() > 0);

			}
			log.info("*********** DONE  ---    in THEN  ---   Validate Mongo DB For EDS Response  *************** ");
		}
	}
}
