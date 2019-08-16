package com.seanfiles.stepdefinition;

import static com.jayway.jsonpath.JsonPath.compile;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.junit.Assert.assertThat;

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

public class ACEAPIGFSIntegration {

	private static Logger log = Logger.getLogger(ACEAPIGFSIntegration.class);
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
	private String gfsRequest = "";
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

	@Given("^Our request use \"([^\"]*)\" and \"([^\"]*)\" combinations to get ACE API Access Token at \"([^\"]*)\" path$")
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
	
	@Given("^APIGEE Developer Portal For ACE API and a GFS \"([^\"]*)\"$")
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

	@When("^Sending gfs request to ACE API with the access token$")
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
	 * Check Mongo DB for HVE GFS response
	 * 
	 * 
	 * @param 
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 * @throws Exception
	 */
	
	@Then("^Check Mongo DB for HVE GFS response$")
	public void checkHVEGFSResponce() throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For HVE Responce *************** ");
		JSONResponse = aceapimethods.getHVEResponseMostRecent();
		scenario.write("HVE Responce is : " + JSONResponse);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For HVE Responce  *************** ");
	}
	
	/**
	 * Save HVE GFS Response code in xml file
	 * 
	 * 
	 * @param String filename
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 * @throws Exception
	 */
	
	@Then("^HVE GFS Response Save code in xml file \"([^\"]*)\"$")
	public void saveHveGFSResponseXml(String filename) throws Exception {
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
	 * HVE GFS Response, Verify the value for below xpath elements
	 * 
	 * 
	 * @param String filename, DataTable elementnvalue
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 * @throws IOException
	 */
	
	@Then("^HVE GFS Response, Verify the value for below xpath elements in \"([^\"]*)\"$")
	public void verifyHveGFSResponseElements(String filename, DataTable elementnvalue) throws IOException {
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
//					Assert.assertEquals(data.get(i).get("value").trim(), value.trim());
					if (data.get(i).get("value").equalsIgnoreCase("string")) {
//						scenario.write("Value type is " + value.getClass().getName() + " and should be " + data.get(i).get("value").toString());
						Assert.assertTrue("Value of "+ data.get(i).get("xpath") +" not matches String: expected is " + data.get(i).get("value").toString()+" but was " + value.getClass().getName() ,value.getClass().getName().contains(data.get(i).get("value").toString()));
					}else if(data.get(i).get("value").equalsIgnoreCase("numeric")) {
//						scenario.write("Value matches numeric: " + value.matches("-?\\d+(\\.\\d+)?"));
						Assert.assertTrue("Value of "+ data.get(i).get("xpath") +" not matches Numeric", value.matches("-?\\d+(\\.\\d+)?"));
					}else if(data.get(i).get("value").equalsIgnoreCase("boolean")) {
						if(value.equalsIgnoreCase("true")||value.equalsIgnoreCase("false")){
							scenario.write("Value matches boolean type");
						}else {
							Assert.fail("Value of " + data.get(i).get("xpath") + " not matches " + data.get(i).get("value"));
						}
					}else {
						Assert.fail("Value of " + data.get(i).get("xpath") + " not matches " + data.get(i).get("value"));
					}
				}
			}
		}
		log.info("*********** DONE  ---    in THEN  ---   HVE Response, Verify the value for below xpath elements  *************** ");
	}
	
	/**
	 * Check MongoDB for EDS GFS Request
	 * 
	 * 
	 * @param 
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 * @throws Exception
	 */
	
	@Then("^Check MongoDB for EDS GFS Request$")
	public void checkEDSGFSRequest() throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For EDS GFS Request *************** ");
		JSONResponse = aceapimethods.getEDSRequestMostRecent();
		scenario.write("EDS GFS Request is : " + JSONResponse);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For EDS GFS Request  *************** ");
	}
	
	/**
	 * Save EDS GFS Request code in xml file
	 * 
	 * 
	 * @param String filename
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 * @throws Exception
	 */
	
	@Then("^EDS GFS Request Save code in xml file \"([^\"]*)\"$")
	public void saveEDSGFSResponseXml(String filename) throws Exception {
		log.info("*********** in THEN  ---   EDS GFS Request Save code in xml file *************** ");
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
		log.info("*********** DONE  ---    in THEN  ---   EDS GFS Request Save code in xml file  *************** ");
	}
	
	/**
	 * EDS Request, Verify the value for below xpath elements
	 * 
	 * 
	 * @param String filename, DataTable elementnvalue
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 * @throws IOException
	 */
	
	@Then("^EDS Request, Verify the value for below xpath elements in \"([^\"]*)\"$")
	public void verifyEDSGFSResponseElements(String filename, DataTable elementnvalue) throws IOException {
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
//					Assert.assertEquals(data.get(i).get("value").trim(), value.trim());
					if (data.get(i).get("value").equalsIgnoreCase("string")) {
//						scenario.write("Value type is " + value.getClass().getName() + " and should be " + data.get(i).get("value").toString());
						Assert.assertTrue("Value of "+ data.get(i).get("xpath") +" not matches String: expected is " + data.get(i).get("value").toString()+" but was " + value.getClass().getName() ,value.getClass().getName().contains(data.get(i).get("value").toString()));
					}else if(data.get(i).get("value").equalsIgnoreCase("numeric")) {
//						scenario.write("Value matches numeric: " + value.matches("-?\\d+(\\.\\d+)?"));
						Assert.assertTrue("Value of "+ data.get(i).get("xpath") +" not matches Numeric", value.matches("-?\\d+(\\.\\d+)?"));
					}else if(data.get(i).get("value").equalsIgnoreCase("boolean")) {
						if(value.equalsIgnoreCase("true")||value.equalsIgnoreCase("false")){
							scenario.write("Value matches boolean type");
						}else {
							Assert.fail("Value of " + data.get(i).get("xpath") + " not matches " + data.get(i).get("value"));
						}
					}else {
						Assert.fail("Value of " + data.get(i).get("xpath") + " not matches " + data.get(i).get("value"));
					}
				}
			}
		}
		log.info("*********** DONE  ---    in THEN  ---   HVE Response, Verify the value for below xpath elements  *************** ");
	}
	
	/**
	 * Check MongoDB for GFS Request
	 * 
	 * 
	 * @param
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 * @throws Exception
	 */
	
	@Then("^Check Mongo DB for GFS Request$")
	public void checkGFSRequest() throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For GFS Request *************** ");
		
		gfsRequest = aceapimethods.getGFSRequestMostRecent();
//		if (!(JSONResponse.equalsIgnoreCase(""))){
//			
//		} else {
//			  log.info("GFS Response is Blank");
//			  scenario.write("GFS Response is Blank");
//			  Assert.fail("GFS Response is Blank");
//		}
//		
		scenario.write("GFS Request is:" + gfsRequest);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For GFS Request  *************** ");
	}
	
	/**
	 * Check MongoDB for GFS Request
	 * 
	 * 
	 * @param
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 * @throws Exception
	 */
	
	@Then("^Check Mongo DB for not having GFS Request$")
	public void checkGFSRequestAWP004() throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For GFS Request *************** ");
		
		gfsRequest = aceapimethods.getGFSRequestMostRecent();
		scenario.write("GFS Request is:" + gfsRequest);
		if (JSONResponse.equalsIgnoreCase("")){
			  log.info("GFS Request is not present in MongoDB");
			  scenario.write("GFS Request is not present in MongoDB");
		} else {
			  log.info("GFS Request is present in MongoDB even with AWP0004");
			  scenario.write("GFS Request is present in MongoDB even with AWP0004");
			  Assert.fail("GFS Request is present in MongoDB even with AWP0004");
		  }
		
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For GFS Request  *************** ");
	}
	
	/**
	 * Validate GFS Response Status
	 * 
	 * 
	 * @param String responseStatus
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 * @throws Exception
	 */
	
	@Then("^Check GFS \"([^\"]*)\" be required$")
	public void checkGFSStatus(String responseStatus) throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For GFS Status *************** ");
//		TODO
//		Getting response code and validate it
		scenario.write("GFS Response status is:" + "valid");
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For GFS Status  *************** ");
	}
	
	/**
	 * Check GFS Request, Verify the value for below xpath elements
	 * 
	 * 
	 * @param DataTable elementnvalue
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 * @throws Exception
	 */
	
	@Then("^Check GFS Request$")
	public void checkGFSmapping(DataTable elementnvalue) throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For GFS mapping *************** ");
		this.datatab=elementnvalue;
		List<Map<String,String>> data = datatab.asMaps(String.class,String.class);
		ReadContext JSONContext = JsonPath.parse(gfsRequest);
		for(int i = 0; i < data.size(); i++) {
			assertThat(JSONContext, withJsonPath(compile(data.get(i).get("xpath"))));
//			String value = JSONContext.read(data.get(i).get("xpath")).getClass().toString();
			String as = JSONContext.read(data.get(i).get("xpath")).toString();
			scenario.write(data.get(i).get("xpath") + "  value is :  " + as);
			if (!data.get(i).get("value").equals("")) {
				if (data.get(i).get("value").equalsIgnoreCase("string")) {
					Assert.assertTrue("Value of "+ data.get(i).get("xpath") +" not matches String: expected is " + data.get(i).get("value").toString()+" but was " + as.getClass().getName() ,as.getClass().getName().contains(data.get(i).get("value").toString()));
				}else if(data.get(i).get("value").equalsIgnoreCase("numeric")) {
					Assert.assertTrue("Value of "+ data.get(i).get("xpath") +" not matches Numeric", as.matches("-?\\d+(\\.\\d+)?"));
				}else if(data.get(i).get("value").equalsIgnoreCase("boolean")) {
					if(as.equalsIgnoreCase("true")||as.equalsIgnoreCase("false")){
						scenario.write("Value matches boolean type");
					}else {
						Assert.fail("Value of " + data.get(i).get("xpath") + " not matches " + data.get(i).get("value"));
					}
				}else {
					Assert.fail("Value of " + data.get(i).get("xpath") + " not matches " + data.get(i).get("value"));
				}
			}
		}
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For GFS mapping  *************** ");
	}
	
	/**
	 * Compare GFS and HVE values to be the same
	 * 
	 * 
	 * @param String filename, DataTable elementnvalue
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 * @throws Exception
	 */
	
	@Then("^Compare GFS and HVE file \"([^\"]*)\" values$")
	public void compareGFSHVEValues(String filename, DataTable elementnvalue) throws Exception {
		log.info("*********** in THEN  ---   Compare GFS and HVE values *************** ");
		String filepath;
		String hveValue;
		this.Filename=filename;
		this.datatab=elementnvalue;
		List<Map<String,String>> data = datatab.asMaps(String.class,String.class);
		filepath=System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
		ReadContext JSONContext = JsonPath.parse(gfsRequest);
		for(int i = 0; i < data.size(); i++) {
			hveValue="Unable to retrive";
			assertThat(JSONContext, withJsonPath(compile(data.get(i).get("gfs"))));
			String gfsValue = JSONContext.read(data.get(i).get("gfs")).toString();
			hveValue = aceapimethods.getXpathValue(data.get(i).get("hve"), filepath);
			Assert.assertEquals(" Values of xpath: " + data.get(i).get("gfs") + " not same, GFS value is " + gfsValue + " and HVE value is " + hveValue, hveValue, gfsValue);
		}
		log.info("*********** DONE  ---    in THEN  ---   Compare GFS and HVE values  *************** ");
	}
	
	/**
	 * Compare GFS and EDS values to be the same
	 * 
	 * 
	 * @param String filename, DataTable elementnvalue
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 * @throws Exception
	 */
	
	@Then("^Compare GFS and EDS file \"([^\"]*)\" values$")
	public void compareGFSEDSValues(String filename, DataTable elementnvalue) throws Exception {
		log.info("*********** in THEN  ---   Compare GFS and EDS values *************** ");
		String filepath;
		String edsValue;
		this.Filename=filename;
		this.datatab=elementnvalue;
		List<Map<String,String>> data = datatab.asMaps(String.class,String.class);
		filepath=System.getProperty("user.dir").concat(props.getProperty("HVERequestfile").concat(Filename));
		ReadContext JSONContext = JsonPath.parse(gfsRequest);
		for(int i = 0; i < data.size(); i++) {
			edsValue="Unable to retrive";
			assertThat(JSONContext, withJsonPath(compile(data.get(i).get("gfs"))));
			String gfsValue = JSONContext.read(data.get(i).get("gfs")).toString();
			edsValue = aceapimethods.getXpathValue(data.get(i).get("eds"), filepath);
			Assert.assertEquals("Values of xpath: " + data.get(i).get("gfs") + " not same, GFS value is " + gfsValue + " and EDS value is " + edsValue, edsValue, gfsValue);
		}
		log.info("*********** DONE  ---    in THEN  ---   Compare GFS and EDS values  *************** ");	
	}
	
	@Then("^Compare GFS and ACE Request values$")
	public void compareACEGFSRequest() throws Exception {
		ReadContext gfsContext = JsonPath.parse(gfsRequest);
		ReadContext aceContext = JsonPath.parse(acejsonrequestcontent);
		Assert.assertTrue("partyRoleIdentifier not same in GFS and ACE Request: "+aceContext.read("$.party.partyRoleIdentifier")+"and"+gfsContext.read("$.grandFatheringData.parties.party[0].partyIdentifier"),aceContext.read("$.party.partyRoleIdentifier").equals(gfsContext.read("$.grandFatheringData.parties.party[0].partyIdentifier")));
		Assert.assertTrue("partyRoleType is not 'SELLER'",gfsContext.read("$.grandFatheringData.parties.party[0].partyRoleType ").equals("SELLER"));
		
	}

}
