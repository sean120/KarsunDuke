package com.seanfiles.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.jayway.jsonpath.InvalidPathException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.seanfiles.root.LoadTestConfig;
import com.seanfiles.utils.Base64Util;
import com.seanfiles.utils.ReusableConstants;

import cucumber.api.Scenario;
import io.restassured.http.ContentType;

public class ACEAPIHelperMethods extends LoadTestConfig {

	public Scenario scenario;
	public LoadTestConfig aceapiconfig;
	public Properties props;
	// private static String searchLabelCloseTag =
	// ReusableConstants.EMPTY_STRING;
	// private static String searchLoanCloseTag =
	// ReusableConstants.EMPTY_STRING;
	private static Logger log = Logger.getLogger(ACEAPIHelperMethods.class);
	private static final String userName = "aceapidmng1";
	private static final String mongoUri = ReusableConstants.mongoSITDBUri.trim();
	private static final String encodedPassword = "JHV5Z2h1cjMzVQ==";
	private static final String decodedPassword = Base64Util.decodeString(encodedPassword).trim();

	public static ContentType getcontenttype(String contenttype) {
		ContentType contType = ContentType.JSON;
		if (contenttype.equalsIgnoreCase(ReusableConstants.APPLICATION_JSON)
				|| contenttype.equalsIgnoreCase(ReusableConstants.APPLICATION_JAVASCRIPT)
				|| contenttype.equalsIgnoreCase(ReusableConstants.TEXT_JAVASCRIPT)
				|| contenttype.equalsIgnoreCase(ReusableConstants.JSON)) {
			contType = ContentType.JSON;
		} else if (contenttype.equalsIgnoreCase(ReusableConstants.ANY)) {
			contType = ContentType.ANY;
		} else if (contenttype.equalsIgnoreCase(ReusableConstants.TEXT_PLAIN)
				|| contenttype.equalsIgnoreCase(ReusableConstants.TEXT)) {
			contType = ContentType.TEXT;
		} else if (contenttype.equalsIgnoreCase(ReusableConstants.APPLICATION_XML)
				|| contenttype.equalsIgnoreCase(ReusableConstants.TEXT_XML)
				|| contenttype.equalsIgnoreCase(ReusableConstants.APPLICATION_XHTML)
				|| contenttype.equalsIgnoreCase(ReusableConstants.XML)) {
			contType = ContentType.XML;
		} else if (contenttype.equalsIgnoreCase(ReusableConstants.HTML)) {
			contType = ContentType.HTML;
		} else if (contenttype.equalsIgnoreCase(ReusableConstants.URLENC)
				|| contenttype.equalsIgnoreCase(ReusableConstants.APPLICATION_URLENCODED)) {
			contType = ContentType.URLENC;
		} else if (contenttype.equalsIgnoreCase(ReusableConstants.BINARY)
				|| contenttype.equalsIgnoreCase(ReusableConstants.APPLICATION_OCTET)) {
			contType = ContentType.BINARY;
		}
		return contType;
	}

	/*
	 * Converts content of file to String
	 * 
	 * @param String filepath
	 * 
	 * @return String - file content
	 * 
	 * @author c41181 - COGNIZANT - LAS ACE API
	 */
	public static String generateStringFromResource(String filePath) throws IOException {
		@SuppressWarnings("deprecation")
		String retStrValue = FileUtils.readFileToString(new File(filePath)).trim();
		log.info("Content generated from -" + filePath);
		return retStrValue;
	}

	/*
	 * Converts content of file to String
	 * 
	 * @param Int Array httperrorcode
	 * 
	 * @param int statuscode
	 * 
	 * @return int - statuscode
	 * 
	 * @author c41181 - COGNIZANT - LAS ACE API
	 */

	public int contains(int[] httperrorcode, int statusCode) {
		for (int n : httperrorcode) {
			if (statusCode == n)
				return statusCode;
		}
		return ReusableConstants.HTTPSUCCESSCODE;
	}

	/*
	 * Creates the mongo Query Criteria for fetching the transformed collection
	 * 
	 * @param
	 * 
	 * @return String EDSResponse
	 */
	public String getEDSResponseMostRecent() throws Exception {
		Document selection = new Document();
		String idACERequest = getACERequestIDMostRecent();
		selection.put("contextMap._id", idACERequest);
		selection.put("contextMap.serviceName", "EDS");
		selection.put("contextMap.requestType", "RESPONSE");
		Document EDSQueryResp = fetchqueryResult("serviceCallTraces", selection);//, sort);
		String serviceRespResult = EDSQueryResp.getString("message");
		return serviceRespResult;
	}

	/*
	 * Creates the mongo Query Criteria for fetching the transformed collection
	 * 
	 * @param
	 * 
	 * @return String EDResponse
	 */
	public String getEDResponseMostRecent() throws Exception {
		Document selection = new Document();
		String serviceRespResult = "";
		String idACERequest = getACERequestIDMostRecent();
		selection.put("contextMap._id", idACERequest);
		selection.put("contextMap.serviceName", "ED");
		selection.put("contextMap.requestType", "RESPONSE");
		Document EDSQueryResp = fetchqueryResult("serviceCallTraces", selection);//, sort);
		if(EDSQueryResp != null)
		{
		serviceRespResult = EDSQueryResp.getString("message");
		}
		return serviceRespResult;
	}


	/*
	 * Converts String into XML
	 * 
	 * @param XMLString
	 * 
	 * @return XML Document
	 */

	public org.w3c.dom.Document convertStringToDocument(String xmlStr) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(new InputSource(new StringReader(xmlStr)));
	}

	public Writer prettyPrint(org.w3c.dom.Document xml) throws Exception {
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		Writer out = new StringWriter();
		tf.transform(new DOMSource(xml), new StreamResult(out));
		log.info(out.toString());
		return out;
	}

	/*
	 * Fetch Service Call Traces Sorted file from Mongo DB based on Connection
	 * 
	 * @param String collectionName , BasicDBObject searchObject, BasicDBObject
	 * fieldObject
	 * 
	 * @return String - resultSet
	 * 
	 * @author C41181 - COGNIZANT - ACE API
	 */
	@SuppressWarnings("resource")
	public static Document fetchqueryResult(String collectionName, Document selection)//, Bson sortMethod)
			throws Exception {
		Document doc = null;
		MongoClient mongoClient = null;
		try {
			String Password = URLEncoder.encode(decodedPassword.trim(), "UTF-8");
			String finalMongoUri = "mongodb://" + userName + ":" + Password + "@" + mongoUri;
			MongoClientURI uri = new MongoClientURI(finalMongoUri);
			mongoClient = new MongoClient(uri);
			MongoDatabase db = mongoClient.getDatabase("aceci01db");
			MongoCollection<Document> collection = db.getCollection(collectionName);
			FindIterable<Document> mongoresultSubset = collection.find(selection);//.sort(sortMethod);
			if (mongoresultSubset == null) {
				log.error("The query returned and empty result.");
			}
			else
			{
				doc = 	mongoresultSubset.first();
		//	for (Document resdoc : mongoresultSubset) {
		//		doc = resdoc;
		//	}
			}
		} catch (UnsupportedEncodingException e) {
			log.error("Unknown host Exception.");
		} finally {
			if (mongoClient != null) {
				mongoClient.close();
			}
		}
		return doc;
	}
	
	@SuppressWarnings("resource")
	public static Document fetchqueryResultACE(String collectionName, Document selection, Bson sortMethod)
			throws Exception {
		Document doc = null;
		MongoClient mongoClient = null;
		try {
			String Password = URLEncoder.encode(decodedPassword.trim(), "UTF-8");
			String finalMongoUri = "mongodb://" + userName + ":" + Password + "@" + mongoUri;
			MongoClientURI uri = new MongoClientURI(finalMongoUri);
			mongoClient = new MongoClient(uri);
			MongoDatabase db = mongoClient.getDatabase("aceci01db");
			MongoCollection<Document> collection = db.getCollection(collectionName);
			FindIterable<Document> mongoresultSubset = collection.find(selection).sort(sortMethod);
			if (mongoresultSubset == null) {
				log.error("The query returned and empty result.");
			}
			else
			{
				doc = 	mongoresultSubset.first();
		//	for (Document resdoc : mongoresultSubset) {
		//		doc = resdoc;
		//	}
			}
		} catch (UnsupportedEncodingException e) {
			log.error("Unknown host Exception.");
		} finally {
			if (mongoClient != null) {
				mongoClient.close();
			}
		}
		return doc;
	}

	/*
	 * Establishes Mongo DB Connection and retrives the result set
	 * 
	 * @param String DBName , String Transaction ID
	 * 
	 * @return String - Result set collection
	 * 
	 * @author C36503 - COGNIZANT - LAS API
	 */
	public String getMongoDbConnection(String collectionName) {

		String pass = "";
		char[] password = pass.toCharArray();
		String username = "";
		MongoClient mongoclient = null;
		MongoCredential credential;
		String result = "";
		try {
			List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
			serverAddresses.add(new ServerAddress("he3qlxvddbs553.fhlmc.com", 27000));
			credential = MongoCredential.createPlainCredential(username, "$external", password);
			mongoclient = new MongoClient(serverAddresses, Arrays.asList(credential));
			@SuppressWarnings("deprecation")
			DB db = mongoclient.getDB("aceci01db");
			DBCollection cDMWrapper = db.getCollection(collectionName);
			String json = "{date:-1}";
			Bson projection = BasicDBObject.parse(json);
			List<DBObject> cursor = cDMWrapper.find().sort((DBObject) projection).limit(1).toArray();
			result = cursor.get(0).get("message").toString();
		} catch (Exception ex) {
			scenario.write(ex.getMessage());
			log.error("Exception occured: ", ex);
		} finally {
			mongoclient.close();
		}
		return result;
	}
	/*
	 * Checking if file is present
	 * 
	 * @param String path
	 * 
	 * @return boolean flag
	 * 
	 * @author C41181 - COGNIZANT - LAS API
	 */
	
	public boolean isValidPath(String path) {
		boolean flag = false;
		try {
			File f = new File(path);
			if (f.exists()) {
				flag = true;
			}
			// Paths.get(path);
		} catch (InvalidPathException | NullPointerException ex) {
			return false;
		}

		return flag;
	}
	
	/*
	 * Verifying xpaths in file and returning it value
	 * 
	 * @param String xpathval, String filepath
	 * 
	 * @return String rtn
	 * 
	 * @author C41181 - COGNIZANT - LAS API
	 */

	public String getXpathValue(String xpathval, String filepath) {
		String rtn = "";
		try {
			File inputFile = new File(filepath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = xpathval;
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			if (nodeList.getLength() == 0) {
				rtn = "No xpath exist";
//			} else if(nodeList.getLength() > 1) {
//				rtn = "Multiple xpaths";
			} else /*if(nodeList.getLength()== 1)*/{
					Node nNode = nodeList.item(0);
					rtn = nNode.getTextContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}
	
	/*
	 * Verifying xpaths in XML string and returning it value
	 * 
	 * @param String xpathval, String xmlInString
	 * 
	 * @return String rtn
	 * 
	 * @author C41181 - COGNIZANT - LAS API
	 */
	
	public String getXpathValueFromXMLString(String xpathval, String xmlInString ) {
		String rtn = "";
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(new InputSource(new StringReader(xmlInString)));
			doc.getDocumentElement().normalize();
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = xpathval;
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			if (nodeList.getLength() == 0) {
				rtn = "No xpath exist";
//			} else if(nodeList.getLength() > 1) {
//				rtn = "Multiple xpaths";
			} else /*if(nodeList.getLength()== 1)*/{
					Node nNode = nodeList.item(0);
					rtn = nNode.getTextContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}
	
	/*
	 * Writing text to file
	 * 
	 * @param String Text, String filepath
	 * 
	 * @return
	 * 
	 * @author C41181 - COGNIZANT - LAS API
	 */	
	
	public void writeToFile(String Text, String filepath) {
		try {
			File file = new File(filepath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Verifying time difference
	 * 
	 * @param String time1, String time2
	 * 
	 * @return int flag
	 * 
	 * @author C41181 - COGNIZANT - LAS API
	 */	

	public int timeelentverification(String time1, String time2) {
		int flag = 0;
		try {
			if (time2.substring(0, 11).equals(time1.substring(0, 11).replace(" ", "T"))) {
				flag++;
			}
			if (time2.substring(time2.length() - 6).equals("-04:00")) {
				flag++;
			}
			if (timediff(time2.substring(11, 23), time1.substring(11)) < 5000) {
				flag++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/*
	 * Getting time difference
	 * 
	 * @param String time2, String time1
	 * 
	 * @return long diff
	 * 
	 * @author C41181 - COGNIZANT - LAS API
	 */	

	public long timediff(String time2, String time1) throws ParseException {
		long diff;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
		Date date1 = format.parse(time1);
		Date date2 = format.parse(time2);
		diff = date2.getTime() - date1.getTime();
		return diff;
	}
	
	/*
	 * Getting Most recent HVE Request from MongoDB
	 * 
	 * @param
	 * 
	 * @return String serviceRespResult
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 */	

	public String getHVERequestMostRecent() throws Exception{
		Document selection = new Document();
		String serviceRespResult = "";
		String idACERequest = getACERequestIDMostRecent();
		selection.put("contextMap._id", idACERequest);
		selection.put("contextMap.serviceName", "HVS");
		selection.put("contextMap.requestType", "REQUEST");
		Document EDSQueryResp = fetchqueryResult("serviceCallTraces", selection);//, sort);
		if(EDSQueryResp != null)
		{
		serviceRespResult = EDSQueryResp.getString("message");
		}
		return serviceRespResult;
	}
	
	/*
	 * Getting Most recent HVE Response from MongoDB
	 * 
	 * @param
	 * 
	 * @return String serviceRespResult
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 */
	
	public String getHVEResponseMostRecent() throws Exception{
		Document selection = new Document();
		String serviceRespResult = "";
		String idACERequest = getACERequestIDMostRecent();
		selection.put("contextMap._id", idACERequest);
		selection.put("contextMap.serviceName", "HVS");
		selection.put("contextMap.requestType", "RESPONSE");
		Document EDSQueryResp = fetchqueryResult("serviceCallTraces", selection);//, sort);
		if(EDSQueryResp != null)
		{
		serviceRespResult = EDSQueryResp.getString("message");
		}
		return serviceRespResult;
	}
	
	/*
	 * Getting Most recent ACE Response from MongoDB
	 * 
	 * @param
	 * 
	 * @return Document ACEQuesryResp
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 */
	
	public Document getACEResponseMostRecent() throws Exception{
		Document selection = new Document();
		String sortCriteria = "{lastModifiedDate:-1}";
		Bson sort = BasicDBObject.parse(sortCriteria);
		Document ACEQueryResp = fetchqueryResultACE("ACEResponses", selection, sort);
		return ACEQueryResp;
	}
	
	/*
	 * Getting Most recent EDS Request from MongoDB
	 * 
	 * @param
	 * 
	 * @return String serviceRespResult
	 * 
	 * @author C41181 - COGNIZANT - LAS API
	 */
	
	public String getEDSRequestMostRecent() throws Exception {
		Document selection = new Document();
		String serviceRespResult = "";
		String idACERequest = getACERequestIDMostRecent();
		selection.put("contextMap._id", idACERequest);
		selection.put("contextMap.serviceName", "EDS");
		selection.put("contextMap.requestType", "REQUEST");
		Document EDSQueryResp = fetchqueryResult("serviceCallTraces", selection);//, sort);
		if(EDSQueryResp != null)
		{
		serviceRespResult = EDSQueryResp.getString("message");
		}
		return serviceRespResult;
	}
	
	/*
	 * Getting Most recent ACE Request ID from MongoDB
	 * 
	 * @param
	 * 
	 * @return String serviceRespResult
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 */
	
	public String getACERequestIDMostRecent() throws Exception {
		Document selection = new Document();
		String serviceRespResult = null;
		String sortCriteria = "{lastModifiedDate:-1}";
		Bson sort = BasicDBObject.parse(sortCriteria);
		Document EDSQueryResp = fetchqueryResultACE("ACERequests", selection, sort);
		if(EDSQueryResp != null)
		{
		serviceRespResult = EDSQueryResp.getLong("_id").toString();
		}
		return serviceRespResult;
	}
	
	/*
	 * Counting elements in JSON File
	 * 
	 * @param String filepath, String element
	 * 
	 * @return int count
	 * 
	 * @author C47174 - COGNIZANT - LAS API
	 */

	public int getElementCountinJson(String filepath, String element) throws ParseException {

		JSONParser parser = new JSONParser();
		int count = 0;
		try {
			JSONArray obj = (JSONArray) parser.parse(new FileReader(filepath));
			JSONObject json = new JSONObject();
			Assert.assertEquals("Container is repeated", 1, obj.size());
			json = (JSONObject) obj.get(0);
			Set<String> set = json.keySet();
			// System.out.println("++++" + set);
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				if (it.next().toString().equals(element)) {
					count++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/*
	 * Creates the mongoDB Query Criteria for fetching the transformed collection
	 * 
	 * @param
	 * 
	 * @return String GFSRequest
	 */
	public String getGFSRequestMostRecent() throws Exception {
		Document selection = new Document();
		String serviceRespResult = "";
		long idACERequest = Long.parseLong(getACERequestIDMostRecent());
		selection.put("_id", idACERequest);
		Document EDSQueryResp = fetchqueryResult("GFRequests", selection);
		if(EDSQueryResp != null)
		{
			serviceRespResult = EDSQueryResp.toJson();
		}
		return serviceRespResult;
	}
}