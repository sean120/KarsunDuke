package com.seanfiles.stepdefinition;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import org.apache.log4j.Logger;
import org.bson.Document;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.seanfiles.db.GFSDB;
import com.seanfiles.db.GFSDBDocuments;
import com.seanfiles.elements.ACEElements;
import com.seanfiles.elements.ACEGFSElementsPaths;
import com.seanfiles.elements.GFSElementPaths;
import com.seanfiles.helper.ACEAPI10;
import com.seanfiles.helper.ACEAPI20;
import com.seanfiles.helper.ACEAPIHelperMethods;
import com.seanfiles.root.LoadTestConfig;
import com.seanfiles.services.ACEAPI20AARequestData;
import com.seanfiles.services.ACEAPI20AAResponseData;
import com.seanfiles.services.ACEAPI20RequestData;
import com.seanfiles.services.ACEAPI20ResponseData;
import com.seanfiles.services.BaseData;
import com.seanfiles.utils.ACEAPI2_0DataElements;
import com.seanfiles.utils.JSONUtilities;
import com.seanfiles.utils.MongoDBUtils;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
@SuppressWarnings("deprecation")
public class ACEAPI2_0ValidateRequestAndResponse   {


	private static Logger log = Logger.getLogger(ACEAPI2_0ValidateRequestAndResponse.class);
	ReadContext reqPayloadJson=null;
	Map<String, String> map=null;
	Map<String, String> requestParamsMap=null;
	Map<String, String> paramsMap = null;
	private String collectionName;
	ACEAPI20Tests test;
	static Document latestACE2Post;
	static Document latestACE2Response;
	static Document latestACE2Request;
	static Document latestServiceCallTracesRequest;
	static Document latestServiceCallTracesResponse;
	ReadContext DBDataJson;
	ReadContext DBDataJsonPost;
	ReadContext DBDataJsonResponse;
	ReadContext DBDataServiceCallTracesRequest;
	ReadContext DBDataServicesCallTracesResponse;
	ReadContext DBjsonPostMessage;
	String requestJSONStr;
	ReadContext requestJSON;
	String responseJsonStr;
	String jsonPayload;
	public Scenario scenario;
	Properties props;
	public LoadTestConfig aceconfig;
	public static Response response=null;
	ACEAPI2_0DataElements ACEPath;
	ACEAPIHelperMethods aceAPIHelper;
	ACEAPI10 ACE10Request;
	private static JSONUtilities jsonUtils;
	String lastPostedDocKeyname;
	String lastPostedDocKeyvalue;
	ACEAPI2_0ValidateRequestAndResponse reuseMethods;
	ACEGFSElementsPaths GFSPath;
	static GFSDB gfsdb;
	GFSElementPaths GFS;
	GFSDBDocuments GFSDBDocs;
	public static String UserRoleName = "$.attributes.UserRoleName";
	
	
	
	@Before
	public void loadTestScenario(Scenario scenario) {
		this.scenario = scenario;
		this.test = new ACEAPI20Tests();
		this.reuseMethods = new ACEAPI2_0ValidateRequestAndResponse();
		this.aceconfig = new LoadTestConfig();
		this.props = aceconfig.loadProperties();
		this.jsonUtils = new JSONUtilities();
		this.ACEPath = new ACEAPI2_0DataElements();
		this.aceAPIHelper = new ACEAPIHelperMethods();
		this.GFSPath = new ACEGFSElementsPaths();
		this.GFS = new GFSElementPaths();
		this.GFSDBDocs = new GFSDBDocuments();
		this.ACE10Request = new ACEAPI10();
		
	}

	@Then("^User retrieves the lates data stored in Mongo DB collection \"([^\"]*)\"$")
	    public Document retrieveData(String collection) throws Throwable { 
		log.info("Retrieving ACE API 2.0 POST document from MongoDB Collection "+collection);		
		String sortCriteria = "{createdDate:-1}";
		this.collectionName = collection;
		if(lastPostedDocKeyvalue==null) {
			if(collection.equals("ACEFullAssessmentResponses")) {
				sortCriteria = "{lastModifiedDate:-1}";
			}
		
			latestACE2Post = MongoDBUtils.getMostRecentDocument(collection, sortCriteria);
		}
		else {
		String dbQryElementPath=GFSPath.getGFSDBElementPath(lastPostedDocKeyname, collection);
		latestACE2Post=MongoDBUtils.getDocByField(collection, dbQryElementPath, lastPostedDocKeyvalue, sortCriteria);
		}
		scenario.write("GFS POST document in MongoDB Collection "+collection+" : " + latestACE2Post.toString());
		log.info("GFS POST document in MongoDB Collection "+collection+" : " + latestACE2Post.toString());
		String latestGFSPostJson=latestACE2Post.toJson();
		DBDataJson =JsonPath.parse(latestGFSPostJson);	
	
	    
           return latestACE2Post;
	}
	@Then("^User validates the \"([^\"]*)\" values in request payload and the DB document$")
	   public void validatePayload(String dataElementsGroup) throws Throwable{
		List<String> elementsList=GFS.getElementsList(dataElementsGroup);	
		System.out.println("ElementList : " +elementsList);
		for(String elementName : elementsList ) {
			
			String elementValueExpected = "";
			Map<String, String> elements = ACEAPI20RequestData.getCurrentRequest().getRequestMap();
			
			try {
				elementValueExpected=elements.get(elementName);
			}
			catch(Exception e) {
				log.info("*************Exception in retrieving value for "+elementName);
				log.info("Element "+elementName+ " not present in the payload; skipped checking in DB doc");
				continue;
			}
			log.info(elementName+" value expected :" + elementValueExpected);

			String jsonPathDB=GFSPath.getGFSDBElementPath(elementName,collectionName);
			String elementValueInDB="";
			elementValueInDB=JSONUtilities.getJsonElementValue(DBDataJson,jsonPathDB);
			try {
				elementValueInDB=JSONUtilities.getJsonElementValue(DBDataJson,jsonPathDB);
				
			}
			catch(Exception e) {
				log.info("*************Exception in retrieving value for Database "+jsonPathDB);
			}
			log.info(elementName+" value in the DB :" + elementValueInDB);

			assertTrue(elementValueInDB.compareTo(elementValueExpected) == 0);	
		}
		
		
	}	
	@When("^User validates the \"([^\"]*)\" is \"([^\"]*)\" in \"([^\"]*)\"$")
	    public void validatesourceAppName (String elementName, String expectedValue, String collectionName) throws Throwable {
		String jsonPathDB=GFSPath.getGFSDBElementPath(elementName,collectionName);
	    String elementValueInDB="";
	    elementValueInDB=JSONUtilities.getJsonElementValue(DBDataJson,jsonPathDB);
	    
	    log.info(elementName+" value in the DB :" + elementValueInDB);
	    assertTrue(elementValueInDB.compareTo(expectedValue) == 0);
	 
	}
	
	@Then("^User verifies \"([^\"]*)\" data is mapped correctly to grandfathering POST Call$") 
	    public void verifyPostCall(String EDSData) throws Throwable { 
		comparePostCall(EDSData);
		
	}
	
	@Given("^User submits modified ACEAPI request \"([^\"]*)\"$")
		public void submitRequest(String fileName, DataTable data)throws Throwable{ 
		test.setACEAPIRequest(fileName);
		test.updateACEAPIRequest(data);
	    test.submitACEAPIRequest();
	    
	}

	@Then("^Check the response in \"([^\"]*)\" collection$")
	    public void checkResponse(String collectionName, DataTable data) throws Throwable { 
		map=data.asMaps(String.class,String.class).get(0);
		String latestACE20ResponseData =latestACE2Response.toJson();
		DBDataJsonResponse = JsonPath.parse(latestACE20ResponseData);
		String elementValueInDB;
		String jsonPathDB;
		String elementName = "";
		String elementValue = "";
		for (Map.Entry<String, String> requestParam : map.entrySet()) {	
			elementName = requestParam.getKey();		
			elementValue = requestParam.getValue();
			System.out.println("fff" + elementValue);
			jsonPathDB=GFSPath.getGFSDBElementPath(elementName,collectionName); 
			elementValueInDB=JSONUtilities.getJsonElementValue(DBDataJsonResponse,jsonPathDB);
		   // assertTrue(elementValueInDB.compareTo(elementValue)==0);
			log.info(elementName + " in DB has a value: " +elementValueInDB );
			
		}
	}

	@When("^User retrieves the latest data from MongoDB \"([^\"]*)\" collections$") 
	   public Document retrieveLatestData(String collection) throws Exception {
		log.info("Retrieving ACE API 2.0 POST document from MongoDB Collection "+collection);		
		String sortCriteria = "{createdDate:-1}";
		this.collectionName = collection;
		if(lastPostedDocKeyvalue==null) {
			latestACE2Response = MongoDBUtils.getMostRecentDocument(collection, sortCriteria);
		}
		else {
		String dbQryElementPath=GFSPath.getGFSDBElementPath(lastPostedDocKeyname, collectionName);
		latestACE2Response=MongoDBUtils.getDocByField(collectionName, dbQryElementPath, lastPostedDocKeyvalue, sortCriteria);
		}
		scenario.write("GFS POST document in MongoDB Collection "+collection+" : " + latestACE2Response.toString());
		log.info("GFS POST document in MongoDB Collection "+collection+" : " + latestACE2Response.toString());
		String latestGFSPostJson=latestACE2Response.toJson();
		DBDataJson =JsonPath.parse(latestGFSPostJson);	
	    
	    
           return latestACE2Response;
	}	


	@Then("^Verify that \"([^\"]*)\" container is grandfathered in response and contains corresponding elements$") 
	    public void verifyContainer(String container, DataTable data) {		
		compareHVE1andHVE2(container);

	}
	
	@Then("^Verify that latest grandFatheringResponse and grandFatheringData contains grandFathering elements$")
		 public void verifyMatchingElements(DataTable data) { 
				
				String collectionPost = "grandFatheringAceData";
				String collectionResponse = "grandFatheringAceResponse";
				map=data.asMaps(String.class,String.class).get(0);
				String latestACE20ResponseData =latestACE2Response.toJson();
				String latestACE20PostData = latestACE2Post.toJson();

				DBDataJsonResponse = JsonPath.parse(latestACE20ResponseData);
				DBDataJsonPost = JsonPath.parse(latestACE20PostData);
				String elementValueInDBPost;
				String elementValueInDBResponse;
				String jsonPathDBPost;
				String jsonPathDBResponse;
				String elementName = "";
				for (Map.Entry<String, String> each : map.entrySet()) {			
					elementName = each.getKey();
					jsonPathDBPost=GFSPath.getGFSDBElementPath(elementName,collectionPost);
					jsonPathDBResponse=GFSPath.getGFSDBElementPath(elementName,collectionResponse);
					elementValueInDBPost=JSONUtilities.getJsonElementValue(DBDataJsonPost,jsonPathDBPost);
					elementValueInDBResponse=JSONUtilities.getJsonElementValue(DBDataJsonResponse,jsonPathDBResponse);
					log.info(elementName + " in Post Collection : " +elementValueInDBPost+ " has the same value in Response Collection : " +  elementValueInDBResponse );
					assertTrue(elementValueInDBPost.compareTo(elementValueInDBResponse) == 0);
				
				}
	}
	@Then("^Verify that latest grandFatheringPresentValueResponse and grandFatheringPresentValueData contains grandFathering elements$") 
	 public void verifyPVData(DataTable data) {
		String collectionPost = "grandFatheringPresentValueData";
		String collectionResponse = "grandFatheringPresentValueResponse";
		map=data.asMaps(String.class,String.class).get(0);
		String latestACE20ResponseData =latestACE2Response.toJson();
		String latestACE20PostData = latestACE2Post.toJson();		
		DBDataJsonResponse = JsonPath.parse(latestACE20ResponseData);
		DBDataJsonPost = JsonPath.parse(latestACE20PostData);
		String elementValueInDBPost;
		String elementValueInDBResponse;
		String jsonPathDBPost;
		String jsonPathDBResponse;
		String elementName = "";
		for (Map.Entry<String, String> each : map.entrySet()) {
			elementName = each.getKey();
			jsonPathDBPost=GFSPath.getGFSDBElementPath(elementName,collectionPost);
			jsonPathDBResponse=GFSPath.getGFSDBElementPath(elementName,collectionResponse); 
			elementValueInDBPost=JSONUtilities.getJsonElementValue(DBDataJsonPost,jsonPathDBPost);
			elementValueInDBResponse=JSONUtilities.getJsonElementValue(DBDataJsonResponse,jsonPathDBResponse);
			log.info(elementName + " in Post Collection : " +elementValueInDBPost+ " has the same value in Response Collection : " +  elementValueInDBResponse );
			assertTrue(elementValueInDBPost.compareTo(elementValueInDBResponse) == 0);
		
		}
		
		
	}
	
	@Then("^Verify that sourceType is \"([^\"]*)\" in both collections$") 
	    public void verifySourceType(String arg1) {
		String postSourceType = GFS.getSourceType(latestACE2Post);
		String responseSourceType = GFS.getSourceType(latestACE2Response);
		log.info("SourceType in Post Collection is : "+ postSourceType + " and sourceType in Response Collection is : " + responseSourceType );
		assertTrue(postSourceType.compareTo(responseSourceType) == 0);
		
	}
	
	public String getPostValue() {	
		String latestACE20PostData = latestACE2Post.toJson();
		DBDataJsonPost = JsonPath.parse(latestACE20PostData);		
		List<String> HVE_2 = GFS.getElementsList("HVE2");	
		Collections.sort(HVE_2);
		System.out.println(HVE_2);
		String elementValuePost;
		String jsonPathPost;
		for (String element : HVE_2) {			
			jsonPathPost=GFSPath.getGFSDBElementPath(element ,GFSDBDocuments.collectionGFSACEData);
			System.out.println("JSONPathPost : " + jsonPathPost);
			elementValuePost=JSONUtilities.getJsonElementValue(DBDataJsonPost,jsonPathPost);
			System.out.println("VALUE : " + elementValuePost);
			return elementValuePost;
		}
		return null;
	}
	public boolean compareHVE1andHVE2(String container) {
	
		String latestACE20ResponseData =latestACE2Response.toJson();
	    DBDataJsonResponse = JsonPath.parse(latestACE20ResponseData);
        String latestACE20PostData =latestACE2Post.toJson();
		DBDataJsonPost = JsonPath.parse(latestACE20PostData);
		String prefix = "HVE2";		
		List<String> HVE_1 = GFS.getElementsList(container);	
		Collections.sort(HVE_1);
		String elementValueResponse;
		String jsonPathResponse;
		String jsonPathPost;
		String elementValuePost;
		for (String element : HVE_1) {
			
			try {
				if(container.equals("HVE2")) {
					System.out.println("CONTAINER");
					assertTrue(container.equals("HVE2"));
			jsonPathPost=GFSPath.getGFSDBElementPath( prefix+element ,GFSDBDocuments.collectionGFSACEData);
			System.out.println("JSONPathPost : " + jsonPathPost);
			elementValuePost=JSONUtilities.getJsonElementValue(DBDataJsonPost,jsonPathPost);			
			jsonPathResponse=GFSPath.getGFSDBElementPath(element,GFSDBDocuments.collectionGFSACEResponse);
			System.out.println("JSONPathResponse : " + jsonPathResponse);
			elementValueResponse=JSONUtilities.getJsonElementValue(DBDataJsonResponse,jsonPathResponse);
			log.info(element + " value in Response: " + elementValueResponse + " is the same as element value in Post :" + elementValuePost );
			assertTrue(elementValuePost.compareTo(elementValueResponse)==0);			
			}
				else {
					jsonPathPost=GFSPath.getGFSDBElementPath(element ,GFSDBDocuments.collectionGFSACEData);
				System.out.println("JSONPathPost : " + jsonPathPost);
				elementValuePost=JSONUtilities.getJsonElementValue(DBDataJsonPost,jsonPathPost);
							
			jsonPathResponse=GFSPath.getGFSDBElementPath(element,GFSDBDocuments.collectionGFSACEResponse);
			System.out.println("JSONPathResponse : " + jsonPathResponse);
			elementValueResponse=JSONUtilities.getJsonElementValue(DBDataJsonResponse,jsonPathResponse);
			log.info(element + " value in Response: " + elementValueResponse + " is the same as element value in Post :" + elementValuePost );
			assertTrue(elementValuePost.compareTo(elementValueResponse)==0);
			}
				}
			catch(Exception e) {
				System.out.println("----Exception:EDS does not return this element for this address : " + element);
			}
	}
		
		return true;
		
		
		
	}
	public void comparePostCall(String Data) {
		String jsoStr = GFSDB.getCurrentGFSDBDocuments().getDbDataACE().getDBDoc().toJson().toString();
		DBjsonPostMessage = JsonPath.parse(jsoStr);
		System.out.println("DBJsonPostMessage : " + DBjsonPostMessage.jsonString());
		List<String> elements = GFS.getAllElements();
		int o = elements.size();
		System.out.println("elements " + elements + o);
		String jsonPath;
		String elementValue;
		int i = 0;
		List<String> skipElms=new ArrayList<String>();
		skipElms.add(ACEElements.PropertyValuationEffectiveDateTime);
		skipElms.add(ACEElements.SalesContractPriceAmount);
		skipElms.add(ACEElements.EstimatedPropertyValueAmount);
		skipElms.add(ACEElements.MaximumAuthorizedLoanAmount);
		for (String element : elements) {
			System.out.println(element);
			if(skipElms.contains(element)) {
			//	System.out.println("skipped checking "+element);
				continue;
			}		
			jsonPath=GFSPath.getGFSDBElementPath(element ,GFSDBDocuments.collectionGFSACEData);
			elementValue=JSONUtilities.getJsonElementValue(DBjsonPostMessage,jsonPath);

		Map<String, String> AllMap = GFS.getAllMapACE(Data);
		String elementValueEDS = AllMap.get(element);
		if(element.equalsIgnoreCase(ACEElements.AlternateAppraisalDecisionEffectiveDatetime)) {
			elementValueEDS=AllMap.get(ACEElements.PropertyValuationEffectiveDateTime);
			
		}
//		if(elementValueEDS.equalsIgnoreCase("HewlettPackardLoanProspectorTransaction")|| 
//			elementValueEDS.equalsIgnoreCase("ExtendedHewlettPackardLoanProspectorTransaction")||
//            elementValueEDS.equalsIgnoreCase("HewlettPackardLoanProspectorTransaction")||
//            elementValueEDS.equalsIgnoreCase("LoanProspectorKey")||
//            elementValueEDS.equalsIgnoreCase("LoanProspectorUniqueLoan")){
//			elementValue.equalsIgnoreCase(elementValueEDS);
//		}
		boolean compareResult=false;
		if(elementValue != null) {
			if(elementValue.compareTo(elementValueEDS)==0) {
				compareResult=true;
			}
			if(!compareResult) {
				if(GFSElementPaths.isNumberElement(element)){
					compareResult=GFSElementPaths.numbersEqual(elementValue, elementValueEDS);
				}
			}
//			i++;
//			System.out.println(i);
			assertTrue(compareResult);
			log.info("Element value in POST "+element+" : "+elementValue+ "  is matching with "+element+" : "+" from EDS " + elementValueEDS );
		}
		else {
			log.info("There is no elements or the data is derived for " +element+" : " + elementValue+" "+ element+" : " +elementValueEDS);
		}	  		
		}
		
	}
	
	public static Document getEDResponseData() throws Exception {
		Document doc = GFSElementPaths.retriveLatestWithQry();
		String json = doc.toJson().toString();
		System.out.println( "jsont" + json );
		return doc;
	}

	public void verifyPvData() { 
		int i = 0;
		String jsonStr = GFSDB.getCurrentGFSDBDocuments().getDbDataPV().getDBDoc().toJson().toString();
		DBjsonPostMessage = JsonPath.parse(jsonStr);
		List<String> elements = GFS.getPresentValue();
		String jsonPath;
		String elementValue;
		for (String element : elements) {
			jsonPath=GFSPath.getGFSDBElementPath(element ,GFSDBDocuments.collectionGFSPVData);
			elementValue=JSONUtilities.getJsonElementValue(DBjsonPostMessage,jsonPath);		
		Map<String, String> AllMap = GFS.getAllMapPV();
		String elementValueEDS = AllMap.get(element);
		boolean compareResult=false;
		i++;
		if(elementValue != null) {
			if(elementValue.compareTo(elementValueEDS)==0) {
				compareResult=true;
			}
			if(!compareResult) {
				if(GFSElementPaths.isNumberElement(element)){
					compareResult=GFSElementPaths.numbersEqual(elementValue, elementValueEDS);
				}
			}
			System.out.println(i);
			assertTrue(compareResult);		
			log.info("Element value in POST "+element+" : "+elementValue+ "  is matching with "+element+" : "+" from EDS " + elementValueEDS );
		}
		else {
			log.info("There is no elements or the data is derived for " +element+" : " + elementValue+" "+ element+" : " +elementValueEDS);
		}

		}
		
	}
	@Given("^User submits new ACEAPI request$")  
			public void submitACEAPIData(DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACEAPI10.submitACEAPIData(dataMap);
	}
	
			
	@Then("^User verifies \"([^\"]*)\" data is mapped correctly to grandfathering PVPOST Call$")
	 public void verifyPVData(String arg1) {
		verifyPvData();
	}
	
	@When("^Verify that sourceType is \"([^\"]*)\" in Response$") 
	    public void verifyACEAPIsourceType (String sourceType) {
		String responseSourceType = GFS.getSourceType(latestACE2Response);		
		assertTrue(responseSourceType.compareTo(sourceType)==0);
		log.info("The sourceType in Response :"+ responseSourceType);

	}
	
	@When("^User restrieves \"([^\"]*)\" \"([^\"]*)\" from \"([^\"]*)\"$") 
	public void retrieveEDResponse (String service, String requestType, String collection) throws Exception {
		log.info("Getting ED Data from serviceCallTraces");
		GFSElementPaths.getEDMessage();
	
	}

	@Then("^User verify that \"([^\"]*)\" is \"([^\"]*)\"$") 
	  public void verifyElementInED(String elementName, String value) throws Exception {
		log.info("Validating the value");
	boolean check = GFSElementPaths.getEDMessage().contains(value);
	assertTrue(check);
	log.info("UserRoleName in ED response is : " + value);
	}
	
	@Then("^User verifies actual response and DB response are equal$")
		public void verifyResponse(DataTable data) {
			List<String> list = data.asList(String.class);
			String json = latestACE2Post.toJson();
			DBDataJsonResponse = JsonPath.parse(json);
			System.out.println(DBDataJsonResponse.jsonString());
			ACEAPI10.getJsonResponse20();
			ReadContext response = JsonPath.parse(ACEAPI10.getJsonResponse20());
			
			
			for(String jsonPath : list) {
				String elementValue = JSONUtilities.getJsonElementValue(DBDataJsonResponse, jsonPath);
				String elementValueResponse = JSONUtilities.getJsonElementValue(response, jsonPath);
				System.out.println("elementValue " + elementValue);
				System.out.println("elementMap " + elementValueResponse);
				if((elementValue==null) || (elementValueResponse==null)) {
					continue;
				}
	             assertTrue(elementValue.compareTo(elementValueResponse)==0);
	             System.out.println("SAME!");
		}
		
			
	}
	@Given("^Submit Request \"([^\"]*)\"$")
    public void submitAce20(String fileName) {	
	ACEAPI10.submit20(fileName);
		
}


}
