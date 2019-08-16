package com.seanfiles.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.seanfiles.helper.ACEAPIHelperMethods;
import com.seanfiles.root.LoadTestConfig;
import com.seanfiles.stepdefinition.ACEAPIRequestschemaVal;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class MongoQueries {

	private static Logger log = Logger.getLogger(ACEAPIRequestschemaVal.class);
	public Scenario scenario;
	public static Properties props;
	private int StatusCodes;
	private String operation;
	private String jsonrequest;
	private String tctype;
	private String scenariosname;
	private String fileName;
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
		MongoQueries.props = aceapiconfig.loadProperties();
		this.aceapimethods = new ACEAPIHelperMethods();
	}

	private static final String userName = "C48461";
	private static final String mongoUri = "he3qlxvtdbs558.fhlmc.com:27000/aceuat01db?authsource=$external&replicaSet=copa-uat-rs&authMechanism=PLAIN";
	private static final String encodedPassword = "";
	private static final String decodedPassword = Base64Util.decodeString(encodedPassword).trim();

	/*
	 * Creates the mongo Query Criteria for fetching the transformed CDM Json
	 * from "loanEvaluationRequests" collection
	 * 
	 * @param String transactionID
	 * 
	 * @return String cdmJson
	 */
	public static String getCdmJson(String jsonArray, String transactionID) throws Exception {

		String json = "{_id:0," + jsonArray + ":1}";
		Bson projection = BasicDBObject.parse(json);

		Document selection = new Document();
		selection.put("trxExecContext.transactionId", transactionID);

		Document doc = fetchqueryResult("loanEvaluationRequests", selection, projection);
		String cdmJson = doc.toJson().toString();

		return cdmJson;
	}

	/*
	 * Creates the mongo Query Criteria for fetching the transformed CDM Json
	 * from "loanEvaluationRequests" collection
	 * 
	 * @param String transactionID
	 * 
	 * @return String cdmJson
	 */
	public static String getCdmJsonByMostRecent(String jsonArray, String eligibilityStatus) throws Exception {

		String json = "{_id:0," + jsonArray + ":1}";
		String sortValue = "{lastModifiedDate:-1}";
		Bson projection = BasicDBObject.parse(json);
		Bson sort = BasicDBObject.parse(sortValue);

		Document selection = new Document();
		selection.put("automatedCollateralEvaluationResponse.aceDecision", eligibilityStatus);

		Document doc = fetchqueryResultLatest("ACEResponses", selection, projection, sort);
		String cdmJson = doc.toJson().toString();

		return cdmJson;
	}

	/*
	 * Creates the Query criteria for fetching Ancillary Service Requests and
	 * Responses as part of ULAD Transaction
	 * 
	 * @param String transactionID
	 * 
	 * @param String serviceName
	 * 
	 * @param String requestType
	 * 
	 * @return String Artifact(Request/Response) for the Service requested.
	 * 
	 * @author C41181 - COGNIZANT - LAS ACE API
	 */
	public static String getServiceRequestsResponses(String serviceName, String requestType) throws Exception {

		Document selection = new Document();

		selection.put("contextMap.serviceName", serviceName);
		selection.put("contextMap.requestType", requestType);

		String json = "{_id:0, message:1}";
		Bson projection = BasicDBObject.parse(json);

		Document doc = fetchqueryResult("serviceCallTraces", selection, projection);
		String serviceArtifact = doc.getString("message");

		return serviceArtifact;

	}

	/*
	 * Fetch CDM JSON file from DB base on Connection
	 * 
	 * @param String collectionName , BasicDBObject searchObject, BasicDBObject
	 * fieldObject
	 * 
	 * @return String - resultSet
	 * 
	 * @author C41181 - COGNIZANT - LAS ACE API
	 */
	@SuppressWarnings("resource")
	public static Document fetchqueryResult(String collectionName, Document selection, Bson projection)
			throws Exception {

		Document doc = null;
		try {

			MongoClient mongoClient = null;

			String Password = URLEncoder.encode(decodedPassword.trim(), "UTF-8");
			String finalMongoUri = "mongodb://" + userName + ":" + Password + "@" + mongoUri;

			MongoClientURI uri = new MongoClientURI(finalMongoUri);
			mongoClient = new MongoClient(uri);
			MongoDatabase db = mongoClient.getDatabase("aceuat01db");
			MongoCollection<Document> collection = db.getCollection(collectionName);
			FindIterable<Document> resultSubset = collection.find(selection).projection(projection);

			if (resultSubset == null) {
				log.error("The query returned and empty result.");

			}
			for (Document resdoc : resultSubset) {
				doc = resdoc;
			}

		} catch (UnsupportedEncodingException e) {
			log.error("Unknown host Exception.");
		}

		return doc;

	}

	/*
	 * Fetch CDM JSON file from DB base on Connection
	 * 
	 * @param String collectionName , BasicDBObject searchObject, BasicDBObject
	 * fieldObject
	 * 
	 * @return String - resultSet
	 * 
	 * @author C41181 - COGNIZANT - LAS ACE API
	 */
	@SuppressWarnings("resource")
	public static Document fetchqueryResultLatest(String collectionName, Document selection, Bson projection,
			Bson sortValue) throws Exception {

		Document doc = null;
		try {

			MongoClient mongoClient = null;

			String Password = URLEncoder.encode(decodedPassword.trim(), "UTF-8");
			String finalMongoUri = "mongodb://" + userName + ":" + Password + "@" + mongoUri;

			MongoClientURI uri = new MongoClientURI(finalMongoUri);
			mongoClient = new MongoClient(uri);
			MongoDatabase db = mongoClient.getDatabase("aceuat01db");
			MongoCollection<Document> collection = db.getCollection(collectionName);
			FindIterable<Document> resultSubset = collection.find(selection).projection(projection).sort(sortValue);

			if (resultSubset == null) {
				log.error("The query returned and empty result.");

			}
			for (Document resdoc : resultSubset) {
				doc = resdoc;
			}

		} catch (UnsupportedEncodingException e) {
			log.error("Unknown host Exception.");
		}

		return doc;

	}
}
