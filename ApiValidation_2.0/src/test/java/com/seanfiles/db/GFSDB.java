package com.seanfiles.db;

import static com.seanfiles.db.GFSDBDocuments.*;
import static com.seanfiles.elements.ACEGFSElements.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.seanfiles.db.GFSDBBase;
import com.seanfiles.elements.ACEAPI20Elements;
import com.seanfiles.elements.ACEGFSElements;
import com.seanfiles.elements.ACEGFSElementsPaths;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.services.ACEAPI20RequestData;
import com.seanfiles.services.GFSGETRequestData;
import com.seanfiles.services.GFSGETResponseData;
import com.seanfiles.services.GFSPOSTRequestData;
import com.seanfiles.utils.MongoDBUtils;

import cucumber.api.DataTable;
public class GFSDB {
	public static GFSDBDocuments dbDocs=new GFSDBDocuments();
	static final String GFSSortCriteria = "{createdDate:-1}";
	private static Logger log = Logger.getLogger(GFSDB.class);
	
	public static final String GFSPostData="GFSPostData";
	public static final String GFSGetRequest="GFSGetRequest";
	public static final String GFSGetResponse="GFSGetResponse";
	public static final String GFS_ACE_Request="GFSACERequest";
	public static final String GFS_PV_Request="GFSPVRequest";
	public static final String GFS_ACE_Data="GFSACEData";
	public static final String GFS_PV_Data="GFSPVData";
	public static final String GFS_ACE_Response="GFSACEResponse";
	public static final String GFS_PV_Response="GFSPVResponse";
	public static final String LPA="LPA";
	public static final String ACEAPI="ACEAPI";
	

	public static void clearDBDocs() {
		dbDocs=new GFSDBDocuments();
	}
	
	public static GFSDBDocuments getCurrentGFSDBDocuments() {
		return dbDocs;
	}
	
	public static Document retriveGFAceRequestByLPT(String LPT) throws Exception {
		return retriveByLPT(GFSDBDocuments.collectionGFSACERequest,LPT);
	}
	
	public static Document retriveGFPVRequestByLPT(String LPT) throws Exception {
		return retriveByLPT(GFSDBDocuments.collectionGFSPVRequest,LPT);
	}
	
	public static Document retriveGFAceResponseByReqID() throws Exception {
		return retriveByReqID(GFSDBDocuments.collectionGFSACEResponse,dbDocs.getDocID(collectionGFSACERequest));
	}
	
	public static Document retriveGFPVResponseByReqID() throws Exception {
		return retriveByReqID(GFSDBDocuments.collectionGFSPVResponse,dbDocs.getDocID(collectionGFSPVRequest));
	}
	
	public static Document retriveGFPVResponseByACEReqID() throws Exception {
		return retriveByReqID(GFSDBDocuments.collectionGFSPVResponse,dbDocs.getDocID(collectionGFSACERequest));
	}
	
	public static Document retriveGFPVRequestByID() throws Exception {
		return retriveByDocID(GFSDBDocuments.collectionGFSPVRequest,dbDocs.getDocID(collectionGFSACERequest));
	}
	
	public static Document retriveGFPVDataByID() throws Exception {
		return retriveByDocID(GFSDBDocuments.collectionGFSPVData,dbDocs.getDocID(collectionGFSACEData));
	}
	
	public static Document retriveGFAceDataByACEAPIID(String ACEAPIID) throws Exception {
		return retriveByACEAPIID(GFSDBDocuments.collectionGFSACEData,ACEAPIID);
	}
	
	public static Document retriveGFAceDataByLPT(String LPT) throws Exception {
		return retriveByLPT(GFSDBDocuments.collectionGFSACEData,LPT);
	}
	
	public static Document retriveGFPVDataByLPT(String LPT) throws Exception {
		return retriveByLPT(GFSDBDocuments.collectionGFSPVData,LPT);
	}
	
	public static Document retriveByDocID(String collectionName, String docID) throws Exception {
		Document GFSDoc=null;
		if(docID != null) {
			GFSDoc=MongoDBUtils.getDocByID(collectionName,docID);
		}
		
		if(GFSDoc != null) {
			//log.info(collectionName+" docuemnt for docID "+docID+" : "+ GFSDoc.toJson().toString());
			TestScenario.writeToScenario(collectionName+" docuemnt for docID "+docID+" : "+ GFSDoc.toJson().toString());
			dbDocs.setDBDoc(GFSDoc, collectionName);
		}
		else {
			log.info("Error: "+collectionName+" docuemnt for docID "+docID+" not found");
		}
		return GFSDoc;
	}
	
	public static Document retriveByReqID(String collectionName, String reqID) throws Exception {
		Document GFSDoc=null;
		if(reqID != null) {
			String ReqIDToRespLinkElement=ACEGFSElementsPaths.getGFSElementsPaths().getDBQueryElementPath(ReqIDToRespLink, collectionName);
			log.info("test: "+ReqIDToRespLinkElement);
			GFSDoc=MongoDBUtils.getDocByID(collectionName,reqID,ReqIDToRespLinkElement);
		}
		
		if(GFSDoc != null) {
			//log.info(collectionName+" docuemnt for reqID "+reqID+" : "+ GFSDoc.toJson().toString());
			TestScenario.writeToScenario(collectionName+" docuemnt for reqID "+reqID+" : "+ GFSDoc.toJson().toString());
			dbDocs.setDBDoc(GFSDoc, collectionName);
		}
		else {
			log.info("Error: "+collectionName+" docuemnt for reqID "+reqID+" not found");
		}
		return GFSDoc;
	}
	
	public static Document retriveByACEAPIID(String collectionName, String ACEAPIID) throws Exception {
		String elementName=LoanID_ACEAPI;
		String elementValue=ACEAPIID;
		String elementPath=ACEGFSElementsPaths.getGFSElementsPaths().getDBQueryElementPath(elementName, collectionName);

		Document GFSDoc=null;
		if(elementValue != null) {
			GFSDoc=MongoDBUtils.getDocByField(collectionName, elementPath, elementValue, GFSSortCriteria);
		}
		
		if(GFSDoc != null) {
			//log.info(collectionName+" docuemnt for ACEAPIID "+ACEAPIID+" : "+ GFSDoc.toJson().toString());
			TestScenario.writeToScenario(collectionName+" docuemnt for ACEAPIID "+ACEAPIID+" : "+ GFSDoc.toJson().toString());
			dbDocs.setDBDoc(GFSDoc, collectionName);
		}
		else {
			log.info("Error: "+collectionName+" docuemnt for ACEAPIID "+ACEAPIID+" not found");
		}
		return GFSDoc;
	}

	public static Document retriveByLPT(String collectionName, String LPT) throws Exception {
		String elementName="LoanID_LPT";
		String elementValue=LPT;
		String elementPath=ACEGFSElementsPaths.getGFSElementsPaths().getDBQueryElementPath(elementName, collectionName);

		Document GFSDoc=null;
		if(elementValue != null) {
			GFSDoc=MongoDBUtils.getDocByField(collectionName, elementPath, elementValue, GFSSortCriteria);
		}
		
		if(GFSDoc != null) {
			//log.info(collectionName+" docuemnt for LPT "+LPT+" : "+ GFSDoc.toJson().toString());
			TestScenario.writeToScenario(collectionName+" docuemnt for LPT "+LPT+" : "+ GFSDoc.toJson().toString());
			dbDocs.setDBDoc(GFSDoc, collectionName);
		}
		else {
			log.info("Error: "+collectionName+" docuemnt for LPT "+LPT+" not found");
		}
		return GFSDoc;
	}

	public static Document retriveLatest(String collectionName) throws Exception {
		Document GFSDoc=MongoDBUtils.getMostRecentDocument(collectionName, GFSSortCriteria);
		if(GFSDoc != null) {
			//log.info(collectionName+" latest docuemnt : "+ GFSDoc.toJson().toString());
			TestScenario.writeToScenario(collectionName+" latest docuemnt : "+ GFSDoc.toJson().toString());
			dbDocs.setDBDoc(GFSDoc, collectionName);
		}
		else {
			log.info("Error: "+collectionName+" latest docuemnt not found");
		}
		return GFSDoc;
	}

	public static void getLatestDocs(String type) throws Exception {
		Document GFSDoc=null;
		switch(type) {
		case GFSPostData:
			retriveLatest(GFSDBDocuments.collectionGFSACEData);	
			retriveLatest(GFSDBDocuments.collectionGFSPVData);			
			break;
		case GFSGetRequest:
			retriveLatest(GFSDBDocuments.collectionGFSACERequest);	
			retriveLatest(GFSDBDocuments.collectionGFSPVRequest);			
			break;
		case GFSGetResponse:
			retriveLatest(GFSDBDocuments.collectionGFSACEResponse);	
			retriveLatest(GFSDBDocuments.collectionGFSPVResponse);			
			break;
		case GFS_ACE_Request:
			GFSDoc=retriveLatest(GFSDBDocuments.collectionGFSACERequest);
			if(GFSDoc != null) {
				TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSACERequest+" latest docuemnt");
				dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSACERequest);
			}
			break;
		case GFS_PV_Request:
			GFSDoc=retriveLatest(GFSDBDocuments.collectionGFSPVRequest);
			if(GFSDoc != null) {
				TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSPVRequest+" latest docuemnt");
				dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSPVRequest);
			}
			break;
		case GFS_ACE_Data:
			GFSDoc=retriveLatest(GFSDBDocuments.collectionGFSACEData);
			if(GFSDoc != null) {
				TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSACEData+" latest docuemnt");
				dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSACEData);
			}
			break;
		case GFS_PV_Data:
			GFSDoc=retriveLatest(GFSDBDocuments.collectionGFSPVData);
			if(GFSDoc != null) {
				TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSPVData+" latest docuemnt");
				dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSPVData);
			}
			break;
		}
	}

	public static void getLatestDocs(String type, Map<String, String> dataMap) throws Exception {
		Document GFSDoc=null;
		switch(type) {
		case GFSPostData:
			retriveLatestWithQry(GFSDBDocuments.collectionGFSACEData, dataMap);	
			retriveLatestWithQry(GFSDBDocuments.collectionGFSPVData, dataMap);			
			break;
		case GFSGetRequest:
			retriveLatestWithQry(GFSDBDocuments.collectionGFSACERequest, dataMap);	
			retriveLatestWithQry(GFSDBDocuments.collectionGFSPVRequest, dataMap);			
			break;
		case GFSGetResponse:
			retriveLatestWithQry(GFSDBDocuments.collectionGFSACEResponse, dataMap);	
			retriveLatestWithQry(GFSDBDocuments.collectionGFSPVResponse, dataMap);			
			break;
		case GFS_ACE_Request:
			GFSDoc=retriveLatestWithQry(GFSDBDocuments.collectionGFSACERequest, dataMap);
			if(GFSDoc != null) {
				TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSACERequest+" docuemnt for Qry "+dataMap);
				dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSACERequest);
			}
			else {
				TestScenario.writeToScenario("Could not get GFS ACE Request; retrying after 2 sec...");
				Thread.sleep(2000);
				GFSDoc=retriveLatestWithQry(GFSDBDocuments.collectionGFSACERequest, dataMap);
				if(GFSDoc != null) {
					TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSACERequest+" docuemnt for Qry "+dataMap);
					dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSACERequest);
				}
			}
			break;
		case GFS_PV_Request:
			GFSDoc=retriveLatestWithQry(GFSDBDocuments.collectionGFSPVRequest, dataMap);
			if(GFSDoc != null) {
				TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSPVRequest+" docuemnt for Qry "+dataMap);
				dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSPVRequest);
			}
			else {
				TestScenario.writeToScenario("Could not get GFS PV Request; retrying after 2 sec...");
				Thread.sleep(2000);
				GFSDoc=retriveLatestWithQry(GFSDBDocuments.collectionGFSPVRequest, dataMap);
				if(GFSDoc != null) {
					TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSPVRequest+" docuemnt for Qry "+dataMap);
					dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSPVRequest);
				}
			}
			break;
		case GFS_ACE_Data:
			GFSDoc=retriveLatestWithQry(GFSDBDocuments.collectionGFSACEData, dataMap);
			if(GFSDoc != null) {
				TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSACEData+" docuemnt for Qry "+dataMap);
				dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSACEData);
			}
			else {
				TestScenario.writeToScenario("Could not get GFS ACE data; retrying after 2 sec...");
				Thread.sleep(2000);
				GFSDoc=retriveLatestWithQry(GFSDBDocuments.collectionGFSACEData, dataMap);
				if(GFSDoc != null) {
					TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSACEData+" docuemnt for Qry "+dataMap);
					dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSACEData);
				}
			}
			break;
		case GFS_PV_Data:
			GFSDoc=retriveLatestWithQry(GFSDBDocuments.collectionGFSPVData, dataMap);
			if(GFSDoc != null) {
				TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSPVData+" docuemnt for Qry "+dataMap);
				dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSPVData);
			}
			else {
				TestScenario.writeToScenario("Could not get GFS PV data; retrying after 2 sec...");
				Thread.sleep(2000);
				GFSDoc=retriveLatestWithQry(GFSDBDocuments.collectionGFSPVData, dataMap);
				if(GFSDoc != null) {
					TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSPVData+" docuemnt for Qry "+dataMap);
					dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSPVData);
				}
			}
			break;
		}
	}

	private static Document retriveLatestWithQry(String collectionName, Map<String, String> dataMap) throws Exception {
		dataMap=normaliseElementNames(dataMap, collectionName);
		String src=dataMap.get(ACEGFSElements.sourceApplicationName);
		if(src != null && src.equalsIgnoreCase(ACEAPI)) {
			if(! collectionName.equalsIgnoreCase(GFSDBDocuments.collectionGFSACEData)) {
				log.info("Collection "+collectionName+" is not applicable for "+src);
				return null;
			}
		}
		Document GFSDoc=MongoDBUtils.getDocByQry(collectionName, dataMap, GFSSortCriteria);
		if(GFSDoc != null) {
			//log.info(collectionName+" docuemnt for Qry "+dataMap+" : "+ GFSDoc.toJson().toString());
			TestScenario.writeToScenario(collectionName+" docuemnt for Qry "+dataMap+" : "+ GFSDoc.toJson().toString());
			dbDocs.setDBDoc(GFSDoc, collectionName);
		}
		else {
			log.info("Error: "+collectionName+" docuemnt for Qry "+dataMap+" not found");
		}
		return GFSDoc;
	}
	
	private static Map<String, String> normaliseElementNames(Map<String, String> dataMap, String collectionName) {
		Map<String, String> newMap=new HashMap<String, String>();		
		for (Map.Entry<String, String> qryMapItem : dataMap.entrySet())
		{
			String elementName=qryMapItem.getKey();
			String elementPath=ACEGFSElementsPaths.getGFSElementsPaths().getDBQueryElementPath(elementName, collectionName);
			newMap.put(elementPath, qryMapItem.getValue());
		}
		return newMap;
	}
	
	public static void removeEligibleACEAPITransactions(String sellerId, String addressLineText) {
		Map<String, String> qryMap=new HashMap<String, String>();
		qryMap.put(ACEGFSElements.sourceApplicationName, ACEAPI);
		qryMap.put(ACEGFSElements.customerId, sellerId);
		qryMap.put(ACEGFSElements.AlternateAppraisalEligibilityDecision, "Eligible");
		qryMap.put(ACEGFSElements.AddressLineText, addressLineText);
		qryMap=normaliseElementNames(qryMap, GFSDBDocuments.collectionGFSACEData);
		try {
			MongoDBUtils.removeDocumentsByQuery(GFSDBDocuments.collectionGFSACEData, qryMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void modifyACEAPISellerId(String newSellerId, Map<String, String> dataMap) throws Exception {
		Document GFSDoc=retriveLatestWithQry(GFSDBDocuments.collectionGFSACEData, dataMap);
		if(GFSDoc == null) {
			log.info("Error: Not able to retrieve the document to be updated using the given query");
			return;
		}
		Map<String, String> qryMap=new HashMap<String, String>();
		qryMap.put(ACEGFSElements.customerId, newSellerId);
		qryMap.put("loanData.parties.party.0.partyIdentifier", newSellerId);
		MongoDBUtils.updateDocument(GFSDBDocuments.collectionGFSACEData, GFSDoc, qryMap);		
	}
	public static void checkGETResponseACEGrandFatheredData() {
		GFSDBBase dbResponseACE=dbDocs.getDbResponseACE();
		if(dbResponseACE == null) {
			TestScenario.writeToScenario("Response Doc for ACE data is null");
			log.info("Response Doc for ACE data is null");
			return;
		}
		
		if(dbResponseACE.getElementValue(ErrorCodeACE).compareTo(CodeForSuccess) == 0) {
			TestScenario.writeToScenario("GFS GET Response contains GrandFathered ACE Data");
			log.info("GFS GET Response contains GrandFathered ACE Data");
		}
		else {
			TestScenario.writeToScenario("No GrandFathered ACE Data in GFS GET Response");
			log.info("No GrandFathered ACE Data in GFS GET Response");
		}
		
	}

	public static void checkGETResponsePVGrandFatheredData() {
		GFSDBBase dbResponsePV=dbDocs.getDbResponsePV();
		
		if(dbResponsePV == null) {
			TestScenario.writeToScenario("Response Doc for PV data is null");
			log.info("Response Doc for PV data is null");
			return;
		}
		if(dbResponsePV.getElementValue(ErrorCodePV).compareTo(CodeForSuccess) == 0) {
			TestScenario.writeToScenario("GFS GET Response contains GrandFathered PV Data");
			log.info("GFS GET Response contains GrandFathered PV Data");
		}
		else {
			TestScenario.writeToScenario("No GrandFathered PV Data in GFS GET Response");
			log.info("No GrandFathered PV Data in GFS GET Response");
		}
	}
	
	public static void checkPOSTDataACEGrandFatheredData() {
		GFSDBBase dbDataACE=dbDocs.getDbDataACE();
		if(dbDataACE == null) {
			TestScenario.writeToScenario("POST Data Doc for ACE data is null");
			log.info("POST Data Doc for ACE data is null");
			return;
		}	
		String HVE1Timestamp=dbDataACE.getElementValue(HomeValueExplorerAssessmentDateTime);
		String HVE2Timestamp=dbDataACE.getElementValue(HVE2HomeValueExplorerAssessmentDateTime);
		
		String msg;
				
		if(HVE1Timestamp.equalsIgnoreCase(HVE2Timestamp)) {
			msg="No Grandfathered HVE/FDR data copied to EDS AARequest";	
		}
		else {
			msg="Grandfathered HVE/FDR data copied to EDS AARequest";	
		}
		
		msg += "\n";
		msg += "AADecisionStatusType: "+dbDataACE.getElementValue(AlternateAppraisalDecisionStatusType);
		msg += "\n";
		msg += "AAEligibilityDecision: "+dbDataACE.getElementValue(AlternateAppraisalEligibilityDecision);
		
		TestScenario.writeToScenario(msg);
	}

	public static void getCorrespondingDoc(String type) throws Exception {
		switch(type) {
		case GFSPostData:
			retriveGFAceDataByACEAPIID(ACECheckAPIDB.getACEAPI10RequestID());
			if(dbDocs.getDbDataACE() != null) {
				GFSPOSTRequestData.setCurrentGFSPostData(dbDocs.getDbDataACE(), null);
			}
			else {
				TestScenario.writeToScenario("Could not get GFS POST data; retrying after 2 sec...");
				Thread.sleep(2000);
				retriveGFAceDataByACEAPIID(ACECheckAPIDB.getACEAPI10RequestID());
				if(dbDocs.getDbDataACE() != null) {
					GFSPOSTRequestData.setCurrentGFSPostData(dbDocs.getDbDataACE(), null);
				}
				else {
					GFSPOSTRequestData.clear();
				}
			}

			break;
		}
	}

	public static void removeDocs(List<Map<String, String>> dataTable) {
		for(Map<String, String> dataMap : dataTable) {
			Map<String, String> qryMap=new HashMap<String, String>();
			for (Map.Entry<String, String> dataMapItem : dataMap.entrySet())
			{
				String elementName=dataMapItem.getKey().trim();
				String elementValue=dataMapItem.getValue();
				if(elementValue != null) {
					elementValue=elementValue.trim();
				}
				qryMap.put(elementName, elementValue);
			}
			qryMap=normaliseElementNames(qryMap, GFSDBDocuments.collectionGFSACEData);
			try {
				MongoDBUtils.removeDocumentsByQuery(GFSDBDocuments.collectionGFSACEData, qryMap);
				MongoDBUtils.removeDocumentsByQuery(GFSDBDocuments.collectionGFSPVData, qryMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void getCorrespondingACEAPI20GFSDocs(String type) {
		Document GFSDoc=null;
		switch(type) {
		case GFSGetRequest:
			getCorrespondingACEAPI20GFSDocs(GFS_ACE_Request);
			if(dbDocs.getDbRequestACE() != null) {
				try {
					GFSDoc=retriveGFPVRequestByID();
					if(GFSDoc != null) {
						TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSPVRequest+" docuemnt");
						dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSPVRequest);
					}
					GFSGETRequestData.setCurrentGETRequest(dbDocs.getDbRequestACE());
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
			break;
		case GFSGetResponse:
			getCorrespondingACEAPI20GFSDocs(GFS_ACE_Response);
			if(dbDocs.getDbResponseACE() != null) {
				try {
					GFSDoc=retriveGFPVResponseByACEReqID();
					if(GFSDoc != null) {
						TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSPVResponse+" docuemnt");
						dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSPVResponse);
					}
					GFSGETResponseData.setCurrentGETResponse(dbDocs.getDbResponseACE(), dbDocs.getDbResponsePV());
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
			break;
		case GFSPostData:
			getCorrespondingACEAPI20GFSDocs(GFS_ACE_Data);
			if(dbDocs.getDbDataACE() != null) {
				try {
					GFSDoc=retriveGFPVDataByID();
					if(GFSDoc != null) {
						TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSPVData+" docuemnt");
						dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSPVData);						
					}
					GFSPOSTRequestData.setCurrentGFSPostData(dbDocs.getDbDataACE(), dbDocs.getDbDataPV());
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
			break;
		case GFS_ACE_Request:
		case GFS_PV_Request:
		case GFS_ACE_Data:
		case GFS_PV_Data:
			ACEAPI20RequestData currentACEAPI20Request=ACEAPI20RequestData.getCurrentRequest();
			String LPKey=null;
			String addressLineText=null;
			if(currentACEAPI20Request != null) {
				LPKey=currentACEAPI20Request.getValue(ACEAPI20Elements.LoanID_LPKey);
				addressLineText=currentACEAPI20Request.getValue(ACEAPI20Elements.AddressLineText);
			}
			if(LPKey == null && addressLineText == null) {
				try {
					getLatestDocs(type);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}
			Map<String, String> dataMap=new HashMap<String, String>();
			dataMap.put(ACEAPI20Elements.LoanID_LPKey, LPKey);
			dataMap.put(ACEAPI20Elements.AddressLineText, addressLineText);
			try {
				getLatestDocs(type, dataMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case GFS_ACE_Response:
			if(dbDocs.getDbRequestACE() == null) {
				getCorrespondingACEAPI20GFSDocs(GFS_ACE_Request);
				if(dbDocs.getDbRequestACE() == null) {
					TestScenario.writeToScenario("No GFACERequest");
					return;
				}
			}
			try {
				GFSDoc=retriveGFAceResponseByReqID();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			if(GFSDoc != null) {
				TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSACEResponse+" docuemnt");
				dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSACEResponse);
			}
			break;
		case GFS_PV_Response:
			if(dbDocs.getDbRequestPV() == null) {
				getCorrespondingACEAPI20GFSDocs(GFS_PV_Request);
				if(dbDocs.getDbRequestPV() == null) {
					TestScenario.writeToScenario("No GFPVRequest");
					return;
				}
			}
			try {
				GFSDoc=retriveGFPVResponseByReqID();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(GFSDoc != null) {
				TestScenario.writeJSONToScenario(GFSDoc.toJson().toString(), GFSDBDocuments.collectionGFSPVResponse+" docuemnt");
				dbDocs.setDBDoc(GFSDoc, GFSDBDocuments.collectionGFSPVResponse);
			}
			break;
		}
	}	
}
