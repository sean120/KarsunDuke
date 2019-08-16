package com.seanfiles.helper;

import static com.seanfiles.elements.ACEGFSElements.*;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.seanfiles.db.GFSDB;
import com.seanfiles.db.GFSDBBase;
import com.seanfiles.db.GFSDBDocuments;
import com.seanfiles.services.GFSGETRequestData;
import com.seanfiles.services.GFSGETResponseData;
import com.seanfiles.services.GFSPOSTRequestData;
import com.seanfiles.utils.ACEGFSDateUtil;
import com.seanfiles.utils.MongoDBUtils;
import com.seanfiles.utils.ReusableConstants;

import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ACEGFSPOST {
	
	private static Response GFSPOSTResponse=null;
	private static String transDateStr=null;
	private static List<ObjectId> dateModifiedDocIDsACEData=null;
	private static List<ObjectId> dateModifiedDocIDsPVData=null;
	
	private static void setTransDate(Map<String, String> dataMap) {
		if(dataMap.containsKey(transactionDate)) {
			String dtStr=dataMap.get(transactionDate);
			if(dtStr != null && dtStr.length() > 0) {
				transDateStr=dtStr;
			}
			else {
				transDateStr=null;
			}
			dataMap.remove(transactionDate);
		}
		else  {
			transDateStr=null;
		}
	}
	
	private static String getTransDate() {
		return transDateStr;
	}

	public static void submitPOSTRequest(Map<String, String> dataMap) {
		String client=dataMap.get(sourceApplicationName);
		if(client != null) {
			if(client.equalsIgnoreCase("ACEAPI")) {
				ACEAPIPostGFS("ACEAPI-POST-GFS.json", dataMap);
			}
			else {
				LPAPostGFS("LPA-POST-GFS.json", dataMap);
			}
		}
	}

	public static void submitPOSTRequest(String postJSONFile, Map<String, String> dataMap) {
		String client=dataMap.get(sourceApplicationName);
		if(client != null) {
			if(client.equalsIgnoreCase("ACEAPI")) {
				ACEAPIPostGFS(postJSONFile, dataMap);
			}
			else {
				LPAPostGFS(postJSONFile, dataMap);
			}
		}
	}

	private static void LPAPostGFS(String postJSONFile, Map<String, String> dataMap) {
		setTransDate(dataMap);
		setGFSPOSTRequest(postJSONFile, dataMap);
		submitGFSPOST();
		verifyPOSTResponse();
		if(getTransDate() !=null) {
			GFSDB.clearDBDocs();
			try {
				GFSDB.retriveGFAceDataByLPT(dataMap.get(LoanID_LPT));
				GFSDBBase dbDataACE=GFSDB.getCurrentGFSDBDocuments().getDbDataACE();
				if( dbDataACE !=null) {
					updateTransactionDate(dataMap, dbDataACE);
					GFSDB.retriveByDocID(dbDataACE.getCollectionName(), dbDataACE.getObjectID());
				}
				GFSDB.retriveGFPVDataByLPT(dataMap.get(LoanID_LPT));
				GFSDBBase dbDataPV=GFSDB.getCurrentGFSDBDocuments().getDbDataPV();
				if(dbDataPV !=null) {
					updateTransactionDate(dataMap, dbDataPV);
					GFSDB.retriveByDocID(dbDataPV.getCollectionName(), dbDataPV.getObjectID());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void updateTransactionDate(Map<String, String> dataMap, GFSDBBase gfsdbBase) {
		Date dateFieldValue=null;
		if(transDateStr.contains("pre") || transDateStr.contains("post") ) {
			String deploymentDate=TestConfig.getProperty("GFSDeploymentDate");
			dateFieldValue = ACEGFSDateUtil.getTransactionDate(deploymentDate, transDateStr);
		}
		else {
			dateFieldValue = ACEGFSDateUtil.getTransactionDate(transDateStr);
		}
		TestScenario.writeToScenario("Updating database document createdDate as "+ACEGFSDateUtil.convertDateToString(dateFieldValue));
		MongoDBUtils.updateDocument(gfsdbBase.getCollectionName(), gfsdbBase.getDBDoc(), createdDate, dateFieldValue);
		addToDateModifiedDocsList(gfsdbBase.getCollectionName(), gfsdbBase.getDBDoc());
	}

	private static void addToDateModifiedDocsList(String collectionName, Document dbDoc) {
		switch(collectionName) {
			case GFSDBDocuments.collectionGFSACEData:
				if(dateModifiedDocIDsACEData == null) {
					dateModifiedDocIDsACEData=new ArrayList<ObjectId>();
				}
				dateModifiedDocIDsACEData.add(dbDoc.getObjectId("_id"));		
				break;
			case GFSDBDocuments.collectionGFSPVData:
				if(dateModifiedDocIDsPVData == null) {
					dateModifiedDocIDsPVData=new ArrayList<ObjectId>();
				}
				dateModifiedDocIDsPVData.add(dbDoc.getObjectId("_id"));		
				break;
		}
	}

	public static void removeDateModifiedDocs() {
		if(dateModifiedDocIDsACEData != null) {
			TestScenario.writeToScenario("Removing date modified docs "+dateModifiedDocIDsACEData);
			MongoDBUtils.removeDocumentsByIDList(GFSDBDocuments.collectionGFSACEData, dateModifiedDocIDsACEData);
		}
		if(dateModifiedDocIDsPVData != null) {
			TestScenario.writeToScenario("Removing date modified docs "+dateModifiedDocIDsPVData);
			MongoDBUtils.removeDocumentsByIDList(GFSDBDocuments.collectionGFSPVData, dateModifiedDocIDsPVData);
		}
	}

	private static void ACEAPIPostGFS(String postJSONFile, Map<String, String> dataMap) {
		setTransDate(dataMap);
		setGFSPOSTRequest(postJSONFile, dataMap);
		submitGFSPOST();
		verifyPOSTResponse();
		if(getTransDate() !=null) {
			GFSDB.clearDBDocs();
			try {
				//GFSDB.retriveGFAceDataByLPT(dataMap.get(LoanID_ACEAPI));
				GFSDB.retriveGFAceDataByACEAPIID(dataMap.get(LoanID_ACEAPI));
				GFSDBBase dbDataACE=GFSDB.getCurrentGFSDBDocuments().getDbDataACE();
				if( dbDataACE !=null) {
					updateTransactionDate(dataMap, dbDataACE);
					GFSDB.retriveByDocID(dbDataACE.getCollectionName(), dbDataACE.getObjectID());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void setGFSPOSTRequest(String GFSPOSTTempl, Map<String, String> dataMap) {
		String JSONStr=GFSPOSTRequestData.getNewACEGFSRequest(GFSPOSTTempl).getRequestJSONStr();
		assertTrue("Not able to read JSON file", JSONStr != null);	
		GFSPOSTRequestData.getCurrentRequest().updateACEGFSRequest(dataMap);		
	}
	
	public static void submitGFSPOST() {
		String JSONStr=GFSPOSTRequestData.getCurrentRequest().getRequestJSONStr();
		TestScenario.writeJSONToScenario(JSONStr, "GFS POST request payload JSON");
		RestAssured.reset();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = TestConfig.getProperty("ACEGFS_BaseUrl");
		RequestSpecification reqspec = RestAssured.given().auth().preemptive().basic(TestConfig.getProperty("ACEGFS_Username"), TestConfig.getProperty("ACEGFS_Password")).contentType(ReusableConstants.APPLICATION_JSON)
				.body(JSONStr);
		//reqspec.log().all();
		String Resourcepath = TestConfig.getProperty("ACEGFS_POST_ResourcePath");
		TestScenario.writeToScenario("ACEGFS POST request URL: "+TestConfig.getProperty("ACEGFS_BaseUrl")+TestConfig.getProperty("ACEGFS_POST_ResourcePath"));
		GFSPOSTResponse = reqspec.when().post(Resourcepath);		
	}

	private static void verifyPOSTResponse() {
		GFSPOSTResponse.then().assertThat().statusCode(ReusableConstants.HTTPSUCCESSCODE);
		
		String jsonResp=GFSPOSTResponse.then().extract().asString();
		TestScenario.writeJSONToScenario(jsonResp, "GFS POST Response");
	}
	
	public static void clearData() {
		GFSDB.clearDBDocs();
		GFSPOSTRequestData.clear();
		GFSGETRequestData.clear();
		GFSGETResponseData.clear();
	}


}
