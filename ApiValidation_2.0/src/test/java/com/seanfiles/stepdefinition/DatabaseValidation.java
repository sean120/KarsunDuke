package com.seanfiles.stepdefinition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.junit.Assert;
import org.junit.rules.ErrorCollector;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.seanfiles.helper.ACEAPIHelperMethods;
import com.seanfiles.root.LoadTestConfig;
import com.seanfiles.utils.Ace2_0Mapping;
import com.seanfiles.utils.EDSXPaths;
import com.seanfiles.utils.ElementJsonPaths;
import com.seanfiles.utils.HVEXPaths;
import com.seanfiles.utils.JsonUtils;
import com.seanfiles.utils.MongoQueries;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import mongoCollections.ACEFULLAssessmentRequests;
import mongoCollections.GFRequestsElements;
import mongoCollections.GrandFatheringAceData;
import mongoCollections.GrandFatheringAceRequest;
import mongoCollections.GrandFatheringAceResponse;
import mongoCollections.GrandFatheringPresentValueData;
import mongoCollections.GrandFatheringPresentValueRequest;
import mongoCollections.GrandFatheringPresentValueResponse;


public class DatabaseValidation {

	private static Logger log = Logger.getLogger(DatabaseValidation.class);
	public static Scenario scenario;
	public Properties props;
	public ACEAPIHelperMethods aceapimethods;
	public LoadTestConfig aceapiconfig;
	protected RequestSpecification reqspec;
	private static String xmlLog;
	private static String jsonLog;
	private static String IVANResponse;
	private static String mongoValue;
	private static String aceRequest;
	private static Response aceResponseJson;
	private static String elementXpath = "";
	private static String jsonString = "";
	private static String initialTimeStamp;

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

	public Response response;

	/**
	 * Retrieve BCVS Report summary level message codes from MongoDB
	 * 
	 * 
	 * @param String bcvsCode
	 *            
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */

	@When("^Retrieve \"([^\"]*)\" BCVS Report summary level message codes from Mongo DB$")
	public void Retrieve_New_BCVS_Report_summary_level_message_codes_from_Mongo_DB(String bcvsCode) throws Exception {
		Thread.sleep(17000);
		boolean bcvsMessageCode = false;
		xmlLog = MongoQueries.getServiceRequestsResponses("ACE", "REQUEST");
		if (!xmlLog.contains("ASCSCVRetrieveCapacityVerificationReportResponse")) {
			Thread.sleep(10000);
			xmlLog = MongoQueries.getServiceRequestsResponses("ACE", "REQUEST");
		}

		System.out.println(xmlLog);

		if (xmlLog.contains("<ns2:MessageCode>" + bcvsCode + "</ns2:MessageCode>")) {
			bcvsMessageCode = true;
		}

		Assert.assertTrue(xmlLog.contains("<ns2:MessageCode>" + bcvsCode + "</ns2:MessageCode>"));

	}
	
	/**
	 * Get JSON Response from MongoDB
	 * 
	 * 
	 * @param
	 *            
	 * 
	 * @return jsonLog
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */

	@When("^Return Json response from Mongo$")
	public String returnLatestResponseFromMongo() throws Exception {
		jsonLog = MongoQueries.getCdmJsonByMostRecent("automatedCollateralEvaluationResponse", "ELIGIBLE");

		System.out.println(jsonLog);

		return jsonLog;
	}
	
	/**
	 * Get JSON HVE Response from MongoDB
	 * 
	 * 
	 * @param
	 *            
	 * 
	 * @return jsonLog
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */

	@When("^Return Json HVE response from Mongo$")
	public String returnLatestHVEJsonResponseFromMongo() throws Exception {
		jsonLog = MongoQueries.getCdmJsonByMostRecent("automatedCollateralEvaluationResponse", "ELIGIBLE");

		System.out.println(jsonLog);

		return jsonLog;
	}
	
	/**
	 * Get JSON EDS Response from MongoDB
	 * 
	 * 
	 * @param
	 *            
	 * 
	 * @return jsonLog
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */

	@When("^Return Json EDS response from Mongo$")
	public String returnLatestEDSJsonResponseFromMongo() throws Exception {
		jsonLog = MongoQueries.getCdmJsonByMostRecent("automatedCollateralEvaluationResponse", "ELIGIBLE");

		System.out.println(jsonLog);

		return jsonLog;
	}

	// @When("^Validate EDS Json \"([^\"]*)\" value in the response from
	// Mongo$")
	// public void validateEDSJsonValues(String expectedValue) throws Exception{
	// Assert.assertTrue(EDSServiceJSonLog.contains(expectedValue));
	// }
	//
	// @When("^Validate HVE Json \"([^\"]*)\" value in the response from
	// Mongo$")
	// public void validateJsonValues(String expectedValue) throws Exception{
	// Assert.assertTrue(HVEServiceJSonLog.contains(expectedValue));
	// }
	//
	
	/**
	 * Validate values in JSON Response from MongoDB with expected values
	 * 
	 * 
	 * @param String expectedValue
	 *            
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 * @throws Exception
	 */
	
	@When("^Validate Json \"([^\"]*)\" value in the response from Mongo$")
	public void validateHVEJsonValues(String expectedValue) throws Exception {
		scenario.write("Validate the value "+ expectedValue);
		Assert.assertTrue(jsonLog.contains(expectedValue));
	}
	
	@When("^Validate any \"([^\"]*)\" value in the response from Mongo$")
	public void validateHVEValues(String expectedValue) throws Exception {
		scenario.write("Validate the value "+ expectedValue);
		Assert.assertTrue(mongoValue.contains(expectedValue));
	}
	
	@When("^Validate \"([^\"]*)\" \"([^\"]*)\" element \"([^\"]*)\" with value \"([^\"]*)\" from Mongo$")
	public void validateHVEValuesXpath(String requestType, String serviceName, String elementName,  String expectedValue) throws Exception {
		String returnedXpath = "";
		returnedXpath = returnElementXpath(requestType, serviceName, elementName);
		
		String elementValue = new XmlPath(mongoValue).getString(returnedXpath);	
		scenario.write("Expected value "+elementValue+" equals expected value "+ expectedValue);
		Assert.assertTrue(elementValue.equals(expectedValue));
	}
	
	@When("^Validate HVS Response to EDS Request Mapping$")
	public void validateHVSResponsetoEDSMapping() throws Exception {
		String returnedEDSValue = "";
		String returnedEDSXpath = "";
		String returnedHVSValue = "";
		String returnedHVSXpath = "";
		
		Ace2_0Mapping ace2_Mapping = new Ace2_0Mapping();
		HashMap<String, String> mapping = ace2_Mapping.hvsResponseToEdsRequest();
		String edsRequest = returnMongoValueFromUATMongo("REQUEST", "EDS");
		String hvsResponse = returnMongoValueFromUATMongo("RESPONSE", "HVS");
		for (Entry<String, String> entry : mapping.entrySet()) {
		    String edsRequestElement = entry.getKey();
		    String hvsResponseElement = entry.getValue();
		    
			System.out.println("EDS element: "+ edsRequestElement);
			System.out.println("HVS Element: "+ hvsResponseElement);
		    
			returnedEDSXpath = returnElementXpath("REQUEST", "EDS", edsRequestElement);	
			returnedEDSValue = new XmlPath(edsRequest).getString(returnedEDSXpath);	
			

			returnedHVSXpath = returnElementXpath("RESPONSE", "HVS", hvsResponseElement);	
			returnedHVSValue = new XmlPath(hvsResponse).getString(returnedHVSXpath);	

    		
			System.out.println("EDS value: "+ returnedEDSValue);
			System.out.println("HVS value: "+ returnedHVSValue);
			scenario.write("HVS response value "+returnedHVSValue+" equals expected EDS request value "+ returnedEDSValue);
			Assert.assertTrue(returnedEDSValue.contains(returnedHVSValue));

		}
		

	}
	
	@When("^Validate EDS to ACE 2.0 Response Mapping$")
	public void validateEDStoAceMapping() throws Exception {
		String returnedEDSXpath = "";
		String returnedEDSValue = "";
		String returnedACEValue= "";
		String returnedACEJsonPath = "";
		
		Ace2_0Mapping ace2_Mapping = new Ace2_0Mapping();
		HashMap<String, String> mapping = ace2_Mapping.edsResponseToAceResponse();
		aceResponseJson = ACEApi2_0Service.apiResponse;
		String aceResponse = aceResponseJson.then().extract().asString();
		String edsResponse = returnMongoValueFromUATMongo("RESPONSE", "EDS");
		
		for (Entry<String, String> entry : mapping.entrySet()) {
			returnedACEValue = "empty";
			returnedEDSValue = "empty";
			Boolean elementExists;
		    String aceResponseElement = entry.getKey();
		    String edsResponseElement = entry.getValue();
			System.out.println("ACE Element: "+ aceResponseElement);
			System.out.println("EDS Element: "+ edsResponseElement);
				
			ACEFULLAssessmentRequests aceRequestMapping = new ACEFULLAssessmentRequests();
			returnedACEJsonPath = aceRequestMapping.returnAceResponseXpath(aceResponseElement);

			try{
				
			ReadContext JSONContext = JsonPath.parse(aceResponse);
			returnedACEValue = JSONContext.read(returnedACEJsonPath);
			elementExists = true;
			}catch(Exception e){
				System.out.println(aceResponseElement+ " Element " + "  json path "+ returnedACEJsonPath +" does not exist.");
				elementExists = false;
			}
			
			returnedEDSXpath = returnElementXpath("RESPONSE", "EDS", edsResponseElement);	
			returnedEDSValue = new XmlPath(edsResponse).getString(returnedEDSXpath);	

			System.out.println("ACE VALUE: "+ returnedACEValue);
			System.out.println("EDS Value: "+ returnedEDSValue);
			if (elementExists == true){
				scenario.write("Ace api 2.0 response value "+returnedACEValue+" equals expected EDS response value "+ returnedEDSValue);
				Assert.assertTrue(returnedEDSValue.contains(returnedACEValue));
			}
		}
		

	}
	
	@When("^User compares ACE2.0 response to LPA EDS \"([^\"]*)\" response$")
	public void validateAceResponseBasedOnEds(String edsResponseFile) throws Exception {
		String returnedEDSXpath = "";
		String returnedEDSValue = "";
		String returnedACEValue= "";
		String returnedACEJsonPath = "";
		
		Ace2_0Mapping ace2_Mapping = new Ace2_0Mapping();
		HashMap<String, String> mapping = ace2_Mapping.aceResponsetoEDSResponse();
		aceResponseJson = ACEApi2_0Service.apiResponse;
		String aceResponse = aceResponseJson.then().extract().asString();
		String edsResponse = ACEAPIHelperMethods.generateStringFromResource(
				System.getProperty("user.dir").concat(props.getProperty("uatRequestFiles").concat(edsResponseFile + ".xml")));
		
		for (Entry<String, String> entry : mapping.entrySet()) {
			returnedACEValue = "empty";
			returnedEDSValue = "empty";
			Boolean elementExists;
		    String aceResponseElement = entry.getKey();
		    String edsResponseElement = entry.getValue();
			System.out.println("ACE 2.0 Response Element: "+ aceResponseElement);
			System.out.println("ACE 2.0 Response Element: "+ edsResponseElement);
				
			ACEFULLAssessmentRequests aceRequestMapping = new ACEFULLAssessmentRequests();
			returnedACEJsonPath = aceRequestMapping.returnAceResponseXpath(aceResponseElement);

			try{
				
			ReadContext JSONContext = JsonPath.parse(aceResponse);
			returnedACEValue = JSONContext.read(returnedACEJsonPath);
			elementExists = true;
			}catch(Exception e){
				System.out.println(aceResponseElement+ " Element " + "  json path "+ returnedACEJsonPath +" does not exist.");
				elementExists = false;
			}
			
//			if (returnedACEValue.equals("Property & Appraisal")){
//				returnedACEValue = "Property &amp; Appraisal";
//			}

			returnedEDSXpath = returnElementXpath("RESPONSE", "EDSLPA", edsResponseElement);	
			returnedEDSValue = new XmlPath(edsResponse).getString(returnedEDSXpath);	
			System.out.println("ACE RESPONSE VALUE: "+ returnedACEValue);
			System.out.println("EDS RESPONSE VALUE: "+ returnedEDSValue);
			if (elementExists == true){
				scenario.write("Ace api 2.0 response value "+returnedACEValue+" equals expected LPA EDS response value "+ returnedEDSValue);
				Assert.assertTrue(returnedEDSValue.contains(returnedACEValue));
			}
		}
		

	}
	
	@When("^Validate ACE 2.0 to HVS Request Mapping$")
	public void validateHVSValues() throws Exception {
		String returnedXpath = "";
		String retrievedValue = "";
		String expectedValue= "";
		String jsonPath = "";
		
		Ace2_0Mapping ace2_Mapping = new Ace2_0Mapping();
		HashMap<String, String> mapping = ace2_Mapping.aceRequestToHVSRequest();
		Document firstCollectionRecord = JsonUtils.getFirstRecordByCollection("ACEFullAssessmentRequests","{lastModifiedDate:-1}");	
		aceRequest = firstCollectionRecord.toJson().toString();
		for (Entry<String, String> entry : mapping.entrySet()) {
		    String hvsElementName = entry.getKey();
		    String aceRequestElementName = entry.getValue();
			System.out.println("HVS Element: "+ hvsElementName);
			System.out.println("ACE Element: "+ aceRequestElementName);
		    
			returnedXpath = returnElementXpath("REQUEST", "HVS", hvsElementName);	
			retrievedValue = new XmlPath(mongoValue).getString(returnedXpath);	
			
			if(aceRequest.contains("LPAv2")&&(aceRequestElementName.equals("loanIdentifier1"))){
				aceRequestElementName = "loanIdentifier2";
			}
			
			if(aceRequest.contains("LPAv2")&&(aceRequestElementName.equals("loanIdentifierType1"))){
				aceRequestElementName = "loanIdentifierType2";
			}
			
		    if (!aceRequestElementName.contains("NA_")){
				ACEFULLAssessmentRequests aceRequestPath = new ACEFULLAssessmentRequests();
	    		jsonPath = aceRequestPath.returnAceRequestJsonPath(aceRequestElementName);
		    }
 
    		
			if(!aceRequestElementName.contains("NA_")){
				if(!hvsElementName.equals("servicerequestdatetime")){
					ReadContext JSONContext = JsonPath.parse(aceRequest);
					expectedValue = JSONContext.read(jsonPath);
				}
				

			}else{
				expectedValue = aceRequestElementName.replaceAll("NA_", "");
			}
		
			if(!hvsElementName.equals("servicerequestdatetime")){
				System.out.println("Retrieved value: "+ retrievedValue);
				System.out.println("Retrieved value: "+ expectedValue);
				scenario.write("Ace request value "+expectedValue+" equals expected HVS request value "+ retrievedValue);
			Assert.assertTrue(retrievedValue.equals(expectedValue));
			}else{
				validateJavaDateAIMApi("REQUEST", "HVS", hvsElementName);
			}
		}
		

	}
	
	@When("^Validate ACE 2.0 to \"([^\"]*)\" Request Mapping$")
	public void validateAce2_0Mapping(String service) throws Exception {
		 switch (service.toLowerCase()) {
		 case "hvs":{
			 validateHVSValues();
			 break;
		 }
		 case "eds":{
			 validateEDSValues();
			 break;
		 }
		 
		 }
	}
	
	
	@When("^Validate ACE 2.0 to EDS Request Mapping$")
	public void validateEDSValues() throws Exception {
		String returnedXpath = "";
		String edsRequestValue = "";
		String aceRequestValue= "";
		String jsonPath = "";
		
		Document firstCollectionRecord = JsonUtils.getFirstRecordByCollection("ACEFullAssessmentRequests","{lastModifiedDate:-1}");	
		aceRequest = firstCollectionRecord.toJson().toString();
		Ace2_0Mapping ace2_Mapping = new Ace2_0Mapping();
		HashMap<String, String> mapping = ace2_Mapping.aceRequestToEDSRequest();
		for (Entry<String, String> entry : mapping.entrySet()) {
			aceRequestValue= "Nothing";
			edsRequestValue = "Nothing";
		    String edsElementName = entry.getKey();
		    String aceRequestElementName = entry.getValue();
		   	    
			returnedXpath = returnElementXpath("REQUEST", "EDS", edsElementName);	
			System.out.println(edsElementName + " -----------------------");
			edsRequestValue = new XmlPath(mongoValue).getString(returnedXpath).toString();	
			if(edsRequestValue.equals("")){
				edsRequestValue = "Nothing";
			}
			
			ACEFULLAssessmentRequests aceRequestPath = new ACEFULLAssessmentRequests();
	    	jsonPath = aceRequestPath.returnAceRequestJsonPath(aceRequestElementName);

    		
			ReadContext JSONContext = JsonPath.parse(aceRequest);
			System.out.println("Element Name is: "+ aceRequestElementName);
			try{
				aceRequestValue = JSONContext.read(jsonPath).toString();
			}catch(Exception e){
				System.out.println("json path not found for element "+ aceRequestElementName + " -----------------------------------------------");
			}

			if (aceRequestElementName.equals("serviceName")){
				aceRequestValue = "EDSAppraisalAlternativeAPP";
			}	
			

			if(aceRequestElementName.equals("constructionLoanIndicator")&!((aceRequestValue.equals("true")||(aceRequestValue.equals("Y"))))){
				aceRequestValue= "false";
			}
			
			if ((aceRequestElementName.equals("subscriberIdentifier"))& (aceRequestValue.equals("LQAACE"))){
				aceRequestValue = "LoanQualityAdvisor";
			}	else if ((aceRequestElementName.equals("subscriberIdentifier"))& (aceRequestValue.equals("LPAv2"))){
				aceRequestValue = "LoanProductAdvisor";
			}
			

			if(!(aceRequestValue.equals("Nothing")||(aceRequestValue.equals("[]")))){
				System.out.println("edsRequestValue "+ edsRequestValue);
				System.out.println("aceRequestValue "+ aceRequestValue);
				scenario.write("Ace request value "+aceRequestValue+" equals expected EDS request value "+ edsRequestValue);
				Assert.assertTrue(edsRequestValue.contains(edsRequestValue));
			}
			

		}

	}
	
	public static String returnElementXpath(String requestType, String serviceName, String elementName) throws Exception {
		if(serviceName.toLowerCase().equals("eds")){
			EDSXPaths serviceClass = new EDSXPaths();
			if(requestType.toLowerCase().equals("request")){
				elementXpath = serviceClass.returnEDSReqXpaths(elementName);
			}else if(requestType.toLowerCase().equals("response")){
				elementXpath = serviceClass.returnEDSResXpaths(elementName);
			}
		}else if(serviceName.toLowerCase().equals("hvs")){
			HVEXPaths serviceClass = new HVEXPaths();
			if(requestType.toLowerCase().equals("request")){
				elementXpath = serviceClass.returnHveReqXpaths(elementName);
			}else if(requestType.toLowerCase().equals("response")){
				elementXpath = serviceClass.returnHveResXpaths(elementName);
			}
		}else if(serviceName.toLowerCase().equals("edslpa")){
			EDSXPaths serviceClass = new EDSXPaths();
			elementXpath = serviceClass.returnEDSResXpathsLPA(elementName);

		}
		
		return elementXpath;
	}
	
	@Then("^User retrieves \"([^\"]*)\" for \"([^\"]*)\" from UAT Mongo DB$")
	public String returnMongoValueFromUATMongo(String requestType, String serviceName) throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For IVAN Request *************** ");
			List<String> select = new ArrayList<String>();
			select.add("contextMap.requestType");
			select.add("contextMap.serviceName");
			List<String> filter = new ArrayList<String>();
			filter.add(requestType.toUpperCase());
			filter.add(serviceName.toUpperCase());
			mongoValue = JsonUtils.getMostRecentRecordwithSelectionSort(select,filter,"serviceCallTraces","{date:-1}");			
			scenario.write(requestType +" "+serviceName+ " equals " + mongoValue);
		log.info("IVAN Response is :" + mongoValue);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For BCAPI Response  *************** ");
		return mongoValue;

	}
	
	@Then("^User retrieves first record from the \"([^\"]*)\" collection in the database$")
	public String returnFirstJsonDocFromCollection(String collectionName) throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For GFS Request *************** ");
			Document firstCollectionRecord = JsonUtils.getFirstRecordByCollection(collectionName,"{createdDate:-1}");	
			mongoValue = firstCollectionRecord.toJson().toString();
			scenario.write("Database value is : " + mongoValue);
		log.info("GFS Get request is :" + mongoValue);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB for GFS Request  *************** ");
		return mongoValue;
	}
	
	@Then("^User retreives \"([^\"]*)\" data that matches the \"([^\"]*)\" address in the database$")
	public String returnFirstJsonDocFromCollection(String dbCollection, String address) throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For IVAN Request *************** ");
			List<String> select = new ArrayList<String>();
			select.add("loanData.address.addressLineText");
			List<String> filter = new ArrayList<String>();
			filter.add(address);
			mongoValue = JsonUtils.retrieveCollection(select,filter,dbCollection).toJson().toString();			
			scenario.write("Database value is: " + mongoValue);
			log.info("IVAN Response is :" + mongoValue);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For BCAPI Response  *************** ");
		return mongoValue;

	}
	
	@Then("^User retreives ace \"([^\"]*)\" data that matches the \"([^\"]*)\" address in the database$")
	public String returnJsonDocFromCollection(String dbCollection, String address) throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For IVAN Request *************** ");
			List<String> select = new ArrayList<String>();
			select.add("aceRequest.address.addressLineText");
			List<String> filter = new ArrayList<String>();
			filter.add(address);
			mongoValue = JsonUtils.retrieveCollection(select,filter,dbCollection).toJson().toString();			
			scenario.write("Database value is: " + mongoValue);
			log.info("IVAN Response is :" + mongoValue);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For BCAPI Response  *************** ");
		return mongoValue;

	}
	
	@Then("^User retrieves first record from \"([^\"]*)\" Ace Requests in the database$")
	public String returnFirstJsonDocFromCollectionByLastModifiedDate(String collectionName) throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For GFS Request *************** ");
			Document firstCollectionRecord = JsonUtils.getFirstRecordByCollection(collectionName,"{lastModifiedDate:-1}");	
			mongoValue = firstCollectionRecord.toJson().toString();
			scenario.write("ACE api 2.0 request is : " + mongoValue);
		log.info("Ace api 2.0 request is :" + mongoValue);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB for GFS Request  *************** ");
		return mongoValue;

	}
	
	@Then("Validate that the grandfathered element \"([^\"]*)\" equals \"([^\"]*)\" in the \"([^\"]*)\"  Database collection$")
	public void validateFileByXpath(String elementName, String expectedValue, String collectionName) {
		log.info("In the validate json received values method");
		String jsonPath = "";

		        switch (collectionName) {
		            case "grandFatheringAceData":
		            	GrandFatheringAceData gfsData = new GrandFatheringAceData();
		        		jsonPath = gfsData.returnGrandFatheringAceDataJsonPath(elementName);
		                     break;
		            case "grandFatheringAceRequest":
		            	GrandFatheringAceRequest gfsAceRequest = new GrandFatheringAceRequest();
		        		jsonPath = gfsAceRequest.returnGrandFatheringAceDataJsonPath(elementName);
		                     break;
		            case "grandFatheringAceResponse":
		            	GrandFatheringAceResponse gfsAceResponse = new GrandFatheringAceResponse();
		        		jsonPath = gfsAceResponse.returnGrandFatheringAceDataJsonPath(elementName);
		                     break;
		            case "grandFatheringPresentValueData":
		            	GrandFatheringPresentValueData elementJsonPath = new GrandFatheringPresentValueData();
		        		jsonPath = elementJsonPath.returnGrandFatheringAceDataJsonPath(elementName);
		                     break;
		            case "grandFatheringPresentValueRequest":
		            	GrandFatheringPresentValueRequest gfsPvRequest = new GrandFatheringPresentValueRequest();
		        		jsonPath = gfsPvRequest.returnGrandFatheringAceDataJsonPath(elementName);
	                     break;
		            case "grandFatheringPresentValueResponse":
		            	GrandFatheringPresentValueResponse gfsPvResponse = new GrandFatheringPresentValueResponse();
		        		jsonPath = gfsPvResponse.returnGrandFatheringAceDataJsonPath(elementName);
	                     break;
		        }
		
		ReadContext JSONContext = JsonPath.parse(mongoValue);
		String retrievedValue = JSONContext.read(jsonPath).toString();
		scenario.write(jsonPath + "    value is :  " + retrievedValue);

		System.out.println("Retrieved value: "+ retrievedValue);
		System.out.println("Retrieved value: "+ expectedValue);
		if (!expectedValue.equals("NoValue")) {
			scenario.write("Retrieved response value "+retrievedValue+" equals expected value "+ expectedValue);
			Assert.assertTrue(retrievedValue.equals(expectedValue));
		}

	}
	
	
	@Then("Return initial time stamp$")
	public void returnPropertyEffictiveDate() {
		log.info("In the validate json received values method");
		
		GrandFatheringAceData gfsData = new GrandFatheringAceData();
		String jsonPath = gfsData.returnGrandFatheringAceDataJsonPath("propertyValuationEffectiveDateTime");

		ReadContext JSONContext = JsonPath.parse(mongoValue);
		String retrievedValue = JSONContext.read(jsonPath).toString();
		scenario.write(jsonPath + "    value is :  " + retrievedValue);

		initialTimeStamp =	retrievedValue;
		scenario.write("Initial time stamp "+ initialTimeStamp);
		System.out.println(initialTimeStamp);

	}
	
	@Then("Validate the expected time stamp$")
	public void validatePropertyEffictiveDate() {
		GrandFatheringAceData gfsData = new GrandFatheringAceData();
		String jsonPath = gfsData.returnGrandFatheringAceDataJsonPath("propertyValuationEffectiveDateTime");

		ReadContext JSONContext = JsonPath.parse(mongoValue);
		String retrievedValue = JSONContext.read(jsonPath).toString();
		scenario.write(jsonPath + "    value is :  " + retrievedValue);		
		String expectedTimeStamp = retrievedValue;
		initialTimeStamp.equals(expectedTimeStamp);
		scenario.write(initialTimeStamp + " Equals expected time stamp "+ expectedTimeStamp);
		System.out.println("Expected timestamp: "+ expectedTimeStamp);

	}
	
	
	@Then("^User deletes \"([^\"]*)\" data that matches the \"([^\"]*)\" address in the database$")
	public String deleteAllCollectionRecords(String dbCollection, String address) throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For IVAN Request *************** ");
			List<String> select = new ArrayList<String>();
			select.add("loanData.address.addressLineText");
			List<String> filter = new ArrayList<String>();
			filter.add(address);
			JsonUtils.returnCollection(select,filter,dbCollection);			
		log.info("IVAN Response is :" + mongoValue);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For BCAPI Response  *************** ");
		return mongoValue;

	}
	
	
	
	@Then("User retrieves \"([^\"]*)\" for \"([^\"]*)\" service and validates the date for \"([^\"]*)\" is formatted correctly$")
	public static void validateJavaDateAIMApi(String requestType, String serviceName, String elementName) throws Exception {
		String returnedXpath = "";
		returnedXpath = returnElementXpath(requestType, serviceName, elementName);
		String elementValue = new XmlPath(mongoValue).getString(returnedXpath);	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		dateFormat.setLenient(false);
		Boolean dateFormattedCorrectly = null;
		try {
			Date javaDate = dateFormat.parse(elementValue);
			dateFormattedCorrectly = true;
		} catch (Exception e) {
			dateFormattedCorrectly = false;
		}
		if (dateFormattedCorrectly == true) {
			scenario.write(elementValue + " Formatted correctly");
			log.info(elementValue + " Formatted correctly");

		} else {
			scenario.write(elementValue + " Formatted incorrectly");
			log.info(elementValue + " Formatted incorrectly");
		}
	Assert.assertTrue(dateFormattedCorrectly);

	}
	
	
	
	@Then("^User goes in to the UAT Mongo Database and retrieves IVAN response$")
	public String returnIVANResponseFromUATMongo() throws Exception {
		log.info("*********** in THEN  ---   Verify Mongo DB For IVAN Request *************** ");
			List<String> select = new ArrayList<String>();
			select.add("contextMap.requestType");
			select.add("contextMap.serviceName");
			List<String> filter = new ArrayList<String>();
			filter.add("RESPONSE");
			filter.add("EDS");
			IVANResponse = JsonUtils.getMostRecentRecordwithSelectionSort(select,filter,"serviceCallTraces","{date:-1}");			
			scenario.write("IVAN Response is : " + IVANResponse);
		log.info("IVAN Response is :" + IVANResponse);
		log.info("*********** DONE  ---    in THEN  ---   Verify Mongo DB For BCAPI Response  *************** ");
		return IVANResponse;

	}

	
	@Then("Validate that the IVAN element \"([^\"]*)\" equals \"([^\"]*)\" in Mongo DB$")
	public void validateIvanElementValue(String elementName, String expectedValue) {
		log.info("In the validate json received values method");

				ElementJsonPaths elementJasonPaths = new ElementJsonPaths();
			    String jsonPath = elementJasonPaths.returnIVANResponseElementJasonPath(elementName);
				ReadContext JSONContext = JsonPath.parse(IVANResponse);
				String stringRetrievedValue;
				int intRetrievedValue;
				double doubleRetrievedValue;
				try{
					stringRetrievedValue = JSONContext.read(jsonPath);
					scenario.write(jsonPath +"    value is :  " +stringRetrievedValue);
					if(!expectedValue.equals("NoValue")){
						Assert.assertTrue(stringRetrievedValue.equals(expectedValue));
					}
				}catch(Exception e){
					try{
						doubleRetrievedValue = JSONContext.read(jsonPath);
						scenario.write(jsonPath +"    value is :  " +doubleRetrievedValue);
						if(!expectedValue.equals("NoValue")){
							Assert.assertTrue(Double.toString(doubleRetrievedValue).equals(expectedValue));
						}
						
					}catch(Exception f){
						intRetrievedValue = JSONContext.read(jsonPath);
						scenario.write(jsonPath +"    value is :  " +intRetrievedValue);
						if(!expectedValue.equals("NoValue")){
							Assert.assertTrue(Integer.toString(intRetrievedValue).equals(expectedValue));
						}
					}

				}
	}
	/**
	 * Validate that element value from JSON Response equals expected value
	 * 
	 * 
	 * @param Strign elementName, String expectedValue
	 *            
	 * 
	 * @return 
	 * @author C41181 - COGNIZANT - LAS - ACE API
	 * @throws 
	 */

	@Then("Validate that the stored JSON value \"([^\"]*)\" equals \"([^\"]*)\" in Mongo DB$")
	public void validateJsonPathExact(String elementName, String expectedValue) {
		log.info("In the validate json received values method");

		ElementJsonPaths elementJasonPaths = new ElementJsonPaths();
		String jsonPath = elementJasonPaths.returnMongoElementJasonPath(elementName);
		ReadContext JSONContext = JsonPath.parse(jsonLog);
		String retrievedValue = JSONContext.read(jsonPath);
		scenario.write(jsonPath + "    value is :  " + retrievedValue);

		if (!expectedValue.equals("NoValue")) {
			Assert.assertTrue(retrievedValue.equals(expectedValue));
		}

	}
}
