package com.seanfiles.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.seanfiles.helper.ACEAPIHelperMethods;

import cucumber.api.Scenario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.parser.ParseException;


//import com.google.gson.Gson;


public class JsonUtils {
	private static final Configuration configuration = Configuration.builder()
		    .jsonProvider(new JacksonJsonNodeJsonProvider())
		    .mappingProvider(new JacksonMappingProvider())
		    .build();
	private String jsonrequest;
	public ACEAPIHelperMethods bcapimethods;
	public Properties props;
	private ContentType contentType = ContentType.JSON;
	private String contType = "application/json";
	protected RequestSpecification reqspec;
	public Scenario scenario;
	public Response response;
	private static final String userName = "bcapidmng2";
	private static final String uatMongoUserName = "C48461";
	private static final String mongoUri = ReusableConstants.mongoSITDBUri.trim();
	private static final String uatMongoUri = ReusableConstants.mongoUATDBUri.trim();
	private static final String encodedPassword = Base64Util.encodeString(ReusableConstants.uatUserpwd).trim();
	private static final String decodedPassword = Base64Util.decodeString(encodedPassword).trim();
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	public static String jsonPrettyPrintString;
	
public static void getJsonElementswithPath(String filePath,String jPath) throws IOException{
	File jsonFile = new File(filePath);	
	Configuration conf = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider())
	        .options(Option.ALWAYS_RETURN_LIST, Option.SUPPRESS_EXCEPTIONS).build();
	ArrayNode jsonMessageNodes = JsonPath.using(conf).parse(jsonFile).read(jPath);
	for (Iterator<JsonNode> it = jsonMessageNodes.elements() ; it.hasNext() ; ) {
	    JsonNode node = it.next();
	    for (Iterator<String> it1 = node.fieldNames(); it1.hasNext(); ) {
	        final String s = it1.next();
	        System.out.println(s);
	    }
	}
}

public static JsonNode getJsonElementValuewithPath(String filePath,String jPath) throws IOException{
	@SuppressWarnings("deprecation")
	//String jsonStr = FileUtils.readFileToString(new File(filePath));
	JsonNode nodeval = JsonPath.using(configuration).parse(filePath).read(jPath);	 
	System.out.println(nodeval);
	return nodeval;	
}

public static JsonNode getJsonElementsValuewithPath(String filePath,String jPath) throws IOException{
	@SuppressWarnings("deprecation")
	String jsonStr = FileUtils.readFileToString(new File(filePath));
	JsonNode nodeval = JsonPath.using(configuration).parse(jsonStr).read(jPath);	 
	System.out.println(nodeval);
	return nodeval;	
}
public static void parseJson(String filePath) throws IOException{
	@SuppressWarnings("deprecation")
	String jsonStr = FileUtils.readFileToString(new File(filePath));
	ObjectMapper objectMapper = new ObjectMapper();
	Map<String, Object> jsonMap = objectMapper.readValue(jsonStr,
		    new TypeReference<Map<String,Object>>(){});
	System.out.println(jsonMap);    
}

public static void jsontoXML(String filePath) throws IOException{
	@SuppressWarnings("deprecation")
	String jsonStr = FileUtils.readFileToString(new File(filePath));
	ObjectMapper objectMapper = new ObjectMapper();
	Map<String, Object> jsonMap = objectMapper.readValue(jsonStr,
		    new TypeReference<Map<String,Object>>(){});
	JSONObject json = new JSONObject(jsonMap);	
	String xml = XML.toString(json);	
		System.out.println(xml);		
}

public static String xmltoJson(String filePath) throws IOException{
	@SuppressWarnings("deprecation")
	String xmlStr = filePath;
	 try {
         JSONObject xmlJSONObj = XML.toJSONObject(xmlStr);
         jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
         System.out.println(jsonPrettyPrintString);
     } catch (JSONException je) {
         System.out.println(je.toString());
     }
	return jsonPrettyPrintString;	
}

public static void editJsonElement(String jsonFileName, String elementPath, String newValue) throws IOException, ParseException{
	@SuppressWarnings("deprecation")
	String jsonStr = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\RequestFiles\\ReqSchemaVal\\"+jsonFileName));
	JsonNode updatedJson = JsonPath.using(configuration).parse(jsonStr).set(elementPath, newValue).json();
	//System.out.println(updatedJson.toString());		
	try {  

        // Writing to a file  
        File file=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\RequestFiles\\ReqSchemaVal\\SIT_IT1\\BCAPIResponse.json");  
        file.createNewFile();  
        FileWriter fileWriter = new FileWriter(file);  
        System.out.println("Writing JSON object to file");  
        System.out.println("-----------------------");        

        fileWriter.write(updatedJson.toString());  
        fileWriter.flush();  
        fileWriter.close(); 
    } catch (IOException e) {  
        e.printStackTrace();  
    }  
}

public static void delJsonElement(String jsonFileName,String newJsonFileName, String elementPath) throws IOException, ParseException{
	@SuppressWarnings("deprecation")
	String jsonStr = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\RequestFiles\\ReqSchemaVal\\"+jsonFileName));
	DocumentContext updatedJson = JsonPath.using(configuration).parse(jsonStr);
	updatedJson.delete(elementPath);
	System.out.println(updatedJson.jsonString());		
	try {  

        // Writing to a file  
        File file=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\RequestFiles\\ReqSchemaVal\\SIT_IT1\\BCAPIResponse.json");  
        file.createNewFile();  
        FileWriter fileWriter = new FileWriter(file);  
        System.out.println("Writing JSON object to file");  
        System.out.println("-----------------------");        

        fileWriter.write(updatedJson.jsonString());  
        fileWriter.flush();  
        fileWriter.close();  

    } catch (IOException e) {  
        e.printStackTrace();  
    }  
}

	public static void readJsonFile(String filePath) {
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(filePath));
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println("Record:\t" + sCurrentLine);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	//Common API Methods
	
public RequestSpecification bcapirequest(String requestfiletype , String username ) throws IOException {	
		
		jsonrequest = bcapimethods.generateStringFromResource(System.getProperty("user.dir")
				.concat(props.getProperty("schemavalfile").concat(requestfiletype)));		
		RestAssured.proxy(ReusableConstants.InternetProxyHost, ReusableConstants.InternetProxyPort);
		this.contentType = bcapimethods.getcontenttype(contType);
		RestAssured.baseURI = props.getProperty("APIGEEAccessTokenBaseUrl");
		RestAssured.useRelaxedHTTPSValidation();
		reqspec = RestAssured.given().contentType(contentType).header("X-Forwarded-UserName", username).body(jsonrequest);
		reqspec.log().all();
		return reqspec;

	}

public Response post_Request_toBCAPIService(String Op) {
	
	String Resourcepath = props.getProperty("BCAPIResourcePath");		
	if (Op.equalsIgnoreCase(ReusableConstants.GET)) {
		scenario.write("Performing GET Operation");
		response = reqspec.when().get(Resourcepath);
	}
	if (Op.equalsIgnoreCase(ReusableConstants.POST)) {
		scenario.write("Performing POST Operation");
		response = reqspec.when().post(Resourcepath);		
	}	
	return response;
}

//Mongo Common methods

public String getMostRecentRecordOnlySort(String collectionName, String sortCriteria) throws Exception {
	Document selection = new Document();	
	//sortCriteria = "{lastModifiedDate:-1}";
	Bson sort = BasicDBObject.parse(sortCriteria);
	Document BCAPIQueryResp = fetchqueryResult(collectionName, selection, sort);
	String serviceReqResult = BCAPIQueryResp.toString();
	return serviceReqResult;
}

public static String getMostRecentRecordwithSelectionSort(List<String>select,List<String>filter,String collection,String sortCriteria) throws Exception {
	Document selection = new Document();
	for(int i = 0;i<select.size();i++){
	selection.put(select.get(i),filter.get(i));
	}
	//selection.put("contextMap.requestType", "REQUEST");
	//sortCriteria = "{lastModifiedDate:-1}";
	Bson sort = BasicDBObject.parse(sortCriteria);
	Document BCAPIQueryResp = fetchqueryUATMongoResult(collection, selection, sort);
	//String serviceReqResult = BCAPIQueryResp.toString();
	String messageData = BCAPIQueryResp.getString("message");
	return messageData;
}

public static Document getFirstRecordByCollection(String collection,String sortCriteria) throws Exception {
	Bson sort = BasicDBObject.parse(sortCriteria);
	Document BCAPIQueryResp = fetchqueryUATMongoResult2(collection, sort);
	return BCAPIQueryResp;
}

public static void returnCollection(List<String>select,List<String>filter,String collection) throws Exception {
	Document selection = new Document();
	for(int i = 0;i<select.size();i++){
	selection.put(select.get(i),filter.get(i));
	}
	deleteCollectionByFilter(collection, selection);
}

public static Document retrieveCollection(List<String>select,List<String>filter,String collection) throws Exception {
	Document selection = new Document();
	for(int i = 0;i<select.size();i++){
	selection.put(select.get(i),filter.get(i));
	}
	Bson sort = BasicDBObject.parse("{createdDate:-1}");
	Document mongoResult =fetchqueryUATMongoResult(collection,selection, sort);
	return mongoResult;
}






public static String getMostRecentSITRecordwithSelectionSort(List<String>select,List<String>filter,String collection,String sortCriteria) throws Exception {
	Document selection = new Document();
	for(int i = 0;i<select.size();i++){
	selection.put(select.get(i),filter.get(i));
	}
	//selection.put("contextMap.requestType", "REQUEST");
	//sortCriteria = "{lastModifiedDate:-1}";
	Bson sort = BasicDBObject.parse(sortCriteria);
	Document BCAPIQueryResp = fetchquerySITMongoResult(collection, selection, sort);
	//String serviceReqResult = BCAPIQueryResp.toString();
	String messageData = BCAPIQueryResp.getString("message");
	return messageData;
}

public static String getMostRecentSITBCRecordwithSelectionSort(List<String>select,List<String>filter,String collection,String sortCriteria) throws Exception {
	Document selection = new Document();
	for(int i = 0;i<select.size();i++){
	selection.put(select.get(i),filter.get(i));
	}
	//selection.put("contextMap.requestType", "REQUEST");
	//sortCriteria = "{lastModifiedDate:-1}";
	Bson sort = BasicDBObject.parse(sortCriteria);
	Document BCAPIQueryResp = fetchquerySITMongoResult(collection, selection, sort);
	String serviceReqResult = BCAPIQueryResp.toJson();
	//System.out.println(BCAPIQueryResp.toString());
	//String messageData = BCAPIQueryResp.getString("comment");
	return serviceReqResult;
}

public static Document fetchqueryResult(String collectionName, Document selection, Bson sortMethod)
		throws Exception {
	Document doc = null;
	MongoClient mongoClient = null;
	try {
		//String Password = URLEncoder.encode(decodedPassword.trim(), "UTF-8");
		String Password = decodedPassword.trim();
		String bcapiMongoUri = "mongodb://" + userName + ":" + Password + "@" + mongoUri;
		MongoClientURI uri = new MongoClientURI(bcapiMongoUri);
		mongoClient = new MongoClient(uri);
		MongoDatabase db = mongoClient.getDatabase("bcci01db");
		MongoCollection<Document> collection = db.getCollection(collectionName);
		FindIterable<Document> mongoresultSubset = collection.find(selection).sort(sortMethod);
		if (mongoresultSubset == null) {
			System.out.println("The query returned and empty result.");
		} else {
			doc = mongoresultSubset.first();
		}
	} finally {
		if (mongoClient != null) {
			mongoClient.close();
		}
	}
	return doc;
}

public static Document fetchquerySITMongoResult(String collectionName, Document selection, Bson sortMethod)
		throws Exception {
	Document doc = null;
	MongoClient mongoClient = null;
	try {
		//String Password = URLEncoder.encode(decodedPassword.trim(), "UTF-8");
		String Password = decodedPassword.trim();
		String bcapiMongoUri = "mongodb://" + userName + ":" + Password + "@" + mongoUri;
		MongoClientURI uri = new MongoClientURI(bcapiMongoUri);
		mongoClient = new MongoClient(uri);
		MongoDatabase db = mongoClient.getDatabase("bcci01db");
		MongoCollection<Document> collection = db.getCollection(collectionName);
		FindIterable<Document> mongoresultSubset = collection.find(selection).sort(sortMethod);
		if (mongoresultSubset == null) {
			System.out.println("The query returned an empty result.");
		} else {
			doc = mongoresultSubset.first();
		}
	} finally {
		if (mongoClient != null) {
			mongoClient.close();
		}
	}
	return doc;
}

public static Document fetchqueryUATMongoResult(String collectionName, Document selection, Bson sortMethod)
		throws Exception {
	Document doc = null;
	MongoClient mongoClient = null;
	try {
		//String Password = URLEncoder.encode(decodedPassword.trim(), "UTF-8");
		String Password = decodedPassword.trim();
		String bcapiMongoUri = "mongodb://" + uatMongoUserName + ":" + Password + "@" + uatMongoUri;
		MongoClientURI uri = new MongoClientURI(bcapiMongoUri);
		mongoClient = new MongoClient(uri);
		MongoDatabase db = mongoClient.getDatabase("aceu01db");
		MongoCollection<Document> collection = db.getCollection(collectionName);
		FindIterable<Document> mongoresultSubset = collection.find(selection).sort(sortMethod);
		if (mongoresultSubset == null) {
			System.out.println("The query returned and empty result.");
		} else {
			doc = mongoresultSubset.first();
		}
	} finally {
		if (mongoClient != null) {
			mongoClient.close();
		}
	}
	return doc;
}

public static Document fetchqueryUATMongoResult2(String collectionName, Bson sortMethod)
		throws Exception {
	Document doc = null;
	MongoClient mongoClient = null;
	try {
		//String Password = URLEncoder.encode(decodedPassword.trim(), "UTF-8");
		String Password = decodedPassword.trim();
		String bcapiMongoUri = "mongodb://" + uatMongoUserName + ":" + Password + "@" + uatMongoUri;
		MongoClientURI uri = new MongoClientURI(bcapiMongoUri);
		mongoClient = new MongoClient(uri);
		MongoDatabase db = mongoClient.getDatabase("aceu01db");
		MongoCollection<Document> collection = db.getCollection(collectionName);
		FindIterable<Document> mongoresultSubset = collection.find().sort(sortMethod);
		if (mongoresultSubset == null) {
			System.out.println("The query returned and empty result.");
		} else {
			doc = mongoresultSubset.first();
		}
	} finally {
		if (mongoClient != null) {
			mongoClient.close();
		}
	}
	return doc;
}



public static void deleteCollectionByFilter(String collectionName, Document selection)
		throws Exception {
	MongoClient mongoClient = null;
	try {
		//String Password = URLEncoder.encode(decodedPassword.trim(), "UTF-8");
		String Password = decodedPassword.trim();
		String bcapiMongoUri = "mongodb://" + uatMongoUserName + ":" + Password + "@" + uatMongoUri;
		MongoClientURI uri = new MongoClientURI(bcapiMongoUri);
		mongoClient = new MongoClient(uri);
		MongoDatabase db = mongoClient.getDatabase("aceu01db");
		MongoCollection<Document> collection = db.getCollection(collectionName);
		collection.deleteMany(selection);
		
	} finally {
		
		if (mongoClient != null) {
			mongoClient.close();
		}
	}
}

public static void addJsonfromTemplate() throws IOException{
	String SourcePath = System.getProperty("user.dir")+"\\src\\test\\resources\\RequestFiles\\ReqSchemaVal\\samplereq.json";
	String DestinPath = System.getProperty("user.dir")+"\\src\\test\\resources\\RequestFiles\\ReqSchemaVal\\IncorrectSchemaRequest.json";
	@SuppressWarnings("deprecation")
	String jsonStr = FileUtils.readFileToString(new File(DestinPath));	
	@SuppressWarnings("deprecation")
	String jsonStrSrc = FileUtils.readFileToString(new File(SourcePath));	
	JSONObject mainObject = new JSONObject(jsonStr);	
	JSONObject valuesObject = new JSONObject(jsonStrSrc);
	JSONArray partyArr = mainObject.getJSONObject("DEAL").getJSONObject("PARTIES").getJSONArray("PARTY");	
    JSONArray arr = partyArr.put(valuesObject);       
        System.out.println(arr);
   
	
}

	public static void main(String[] args) throws IOException, ParseException {
	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\RequestFiles\\ReqSchemaVal\\samplereq.json";
	//	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\RequestFiles\\ReqSchemaVal\\BCAPIRequest.json";
		String jPath = "$..ASCSCVInitiateCapacityVerificationReportRequest.ReportKeys.ReportKey[1].TaxpayerIdentificationNumber";
	//	String fileName = "BCVSResponse.xml";
	//	JsonUtils.jsontoXML();
	//	JsonUtils.xmltoJson(fileName);
		JsonUtils.getJsonElementswithPath(filePath,jPath);
		JsonUtils.getJsonElementsValuewithPath(filePath,jPath);
	//	JsonUtils.readJsonFile(filePath);
	//	JsonUtils.editJsonElement("MissPartyRoleIdentifier.json", "$..ADDRESS.AddressLineText", "badstring");
	//	JsonUtils.delJsonElement("MissPartyRoleIdentifier.json", "$..ASSETS.ASSET[0].ASSET_DETAIL");
		//JsonUtils.parseJson(filePath);
	//	JsonUtils.addJsonfromTemplate();
	}
	
	public static String getJsonElementValue(ReadContext jsonData, String jsonPath, String elementDataType) {
		String elementValue="";
		
		if(jsonPath.contains("?")) {
			List<String> elementValues=jsonData.read(jsonPath);
			elementValue=elementValues.get(0);
			return elementValue;
		}
		
		switch(elementDataType) {
		case "String" :
		case "" :
			elementValue=jsonData.read(jsonPath);
			break;
		case "Integer" :
			Integer intValue = jsonData.read(jsonPath,Integer.class);
			elementValue=intValue.toString();
			break;
		case "Double" :
			Double doubleValue = jsonData.read(jsonPath,Double.class);
			elementValue=doubleValue.toString();
			break;
		case "Boolean" :
			Boolean boolValue = jsonData.read(jsonPath,Boolean.class);
			elementValue=boolValue.toString();
			break;
		default:
			elementValue=jsonData.read(jsonPath);
			break;
		}
		return elementValue;
	}
	
}
