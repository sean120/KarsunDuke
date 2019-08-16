package com.seanfiles.db;

import static com.seanfiles.services.BaseData.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.seanfiles.elements.ACEAPI20Elements;
import com.seanfiles.elements.ACECheckAPIElements;
import com.seanfiles.elements.ACECheckAPIElementsPaths;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.services.ACEAPI20AARequestData;
import com.seanfiles.services.ACEAPI20AAResponseData;
import com.seanfiles.services.ACEAPI20HVERequestData;
import com.seanfiles.services.ACEAPI20HVEResponseData;
import com.seanfiles.services.ACECheckAPIAARequestData;
import com.seanfiles.services.ACECheckAPIAAResponseData;
import com.seanfiles.services.ACECheckAPIDerivedData;
import com.seanfiles.services.ACECheckAPIHVERequestData;
import com.seanfiles.services.ACECheckAPIHVEResponseData;
import com.seanfiles.services.ACECheckAPIRequestData;
import com.seanfiles.utils.MongoDBUtils;

public class ACECheckAPIDB {
	public static ACECheckAPIDBDocuments dbDocs=new ACECheckAPIDBDocuments();
	static final String ACEAPISortCriteria = "{lastModifiedDate:-1}";
	static final String ACEAPIServicesSortCriteria = "{date:-1}";
	
	private static Logger log = Logger.getLogger(ACECheckAPIDB.class);
	

	public static void clear() {
		dbDocs=new ACECheckAPIDBDocuments();
	}
	
	public static ACECheckAPIDBDocuments getCurrentACEAPIDBDocuments() {
		return dbDocs;
	}
	
	public static Document retriveByReqID(String collectionName, String reqID) throws Exception {
		Document ACEAPIDoc=null;
		if(reqID != null) {
			ACEAPIDoc=MongoDBUtils.getDocByField(collectionName,ACECheckAPIElements.DOC_ID, Long.valueOf(reqID), ACEAPISortCriteria);
		}
		
		if(ACEAPIDoc != null) {
			//log.info(collectionName+" docuemnt for reqID "+reqID+" : "+ ACEAPIDoc.toString());
			//TestScenario.writeToScenario(collectionName+" docuemnt for reqID "+reqID+" : "+ ACEAPIDoc.toJson().toString());
			TestScenario.writeJSONToScenario(ACEAPIDoc.toJson().toString(), collectionName+" document for reqID "+reqID);
			dbDocs.setDBDoc(ACEAPIDoc, collectionName);
		}
		else {
			log.info("Error: "+collectionName+" docuemnt for reqID "+reqID+" not found");
		}
		return ACEAPIDoc;
	}
	
	public static Document retriveLatest(String collectionName) throws Exception {
		Document ACEAPIDoc=MongoDBUtils.getMostRecentDocument(collectionName, ACEAPISortCriteria);
		if(ACEAPIDoc != null) {
			//log.info(collectionName+" latest docuemnt : "+ ACEAPIDoc.toString());
			TestScenario.writeToScenario(collectionName+" latest docuemnt : "+ ACEAPIDoc.toJson().toString());
			dbDocs.setDBDoc(ACEAPIDoc, collectionName);
		}
		else {
			log.info("Error: "+collectionName+" latest docuemnt not found");
		}
		return ACEAPIDoc;
	}

	public static void getLatestDoc(String type) throws Exception {
		switch(type) {
		case ACEAPI_Request:
			retriveLatest(ACECheckAPIDBDocuments.collectionACERequests);	
			break;
		case ACEAPI_Response:
			retriveLatest(ACECheckAPIDBDocuments.collectionACEResponses);	
			break;
		}
	}

	public static void getCorrespondingDoc(String type) throws Exception {
		Document doc=null;
		switch(type) {
		case ACEAPI_Request:
			if( getACEAPI10RequestID() != null) {
				doc=retriveByReqID(ACECheckAPIDBDocuments.collectionACERequests, getACEAPI10RequestID());	
			}
			else {
				doc=retriveCorresponding(ACECheckAPIRequestData.getCurrentRequest(), ACECheckAPIDBDocuments.collectionACERequests);
				setACEAPI10RequestID();
			}
			break;
		case ACEAPI_Response:
			if(getACEAPI10RequestID() == null ) {
				getCorrespondingDoc(ACEAPI_Request);
			}
			doc=retriveByReqID(ACECheckAPIDBDocuments.collectionACEResponses, getACEAPI10RequestID());	
			break;
		case HVE_Request:
			if(getACEAPI10RequestID() == null ) {
				getCorrespondingDoc(ACEAPI_Request);
			}
			doc=retriveCorrespondingServiceCall(ACECheckAPIDBDocuments.collectionServiceCallTraces, getACEAPI10RequestID(), ACECheckAPIDBDocuments.dbHVE, ACECheckAPIDBDocuments.dbRequest);
			if(doc != null) {
				String HVERequestXML=dbDocs.getHVERequest().getElementValue(ACEAPI20Elements.Message);
				if(HVERequestXML != null) {
					ACEAPI20HVERequestData.setHVERequest(HVERequestXML);
					ACECheckAPIHVERequestData.setHVERequest(HVERequestXML);
					TestScenario.writeXMLToScenario(HVERequestXML, "HVE Request XML");
				}
			}
			break;
		case HVE_Response:
			if(getACEAPI10RequestID() == null ) {
				getCorrespondingDoc(ACEAPI_Request);
			}
			doc=retriveCorrespondingServiceCall(ACECheckAPIDBDocuments.collectionServiceCallTraces, getACEAPI10RequestID(), ACECheckAPIDBDocuments.dbHVE, ACECheckAPIDBDocuments.dbResponse);	
			if(doc != null) {
				String HVERsponseXML=dbDocs.getHVEResponse().getElementValue(ACEAPI20Elements.Message);
				if(HVERsponseXML != null) {
					ACECheckAPIHVEResponseData.setHVEResponse(HVERsponseXML);
					TestScenario.writeXMLToScenario(HVERsponseXML, "HVE Response XML");
				}
			}
			break;
		case AA_Request:
			if(getACEAPI10RequestID() == null ) {
				getCorrespondingDoc(ACEAPI_Request);
			}
			doc=retriveCorrespondingServiceCall(ACECheckAPIDBDocuments.collectionServiceCallTraces, getACEAPI10RequestID(), ACECheckAPIDBDocuments.dbAA, ACECheckAPIDBDocuments.dbRequest);	
			if(doc != null) {
				String AARequestXML=dbDocs.getEDSRequest().getElementValue(ACEAPI20Elements.Message);
				if(AARequestXML != null) {
					ACECheckAPIAARequestData.setAARequest(AARequestXML);
					TestScenario.writeXMLToScenario(AARequestXML, "AA Request XML");
				}
			}
			break;
		case AA_Response:
			if(getACEAPI10RequestID() == null ) {
				getCorrespondingDoc(ACEAPI_Request);
			}
			doc=retriveCorrespondingServiceCall(ACECheckAPIDBDocuments.collectionServiceCallTraces, getACEAPI10RequestID(), ACECheckAPIDBDocuments.dbAA, ACECheckAPIDBDocuments.dbResponse);	
			if(doc != null) {				
				String AARsponseXML=dbDocs.getEDSResponse().getElementValue(ACEAPI20Elements.Message);
				if(AARsponseXML != null) {
					ACECheckAPIAAResponseData.setAAResponse(AARsponseXML);
					TestScenario.writeXMLToScenario(AARsponseXML, "AA Response XML");
				}
			}
			break;
		}
	}

	private static Document retriveCorrespondingServiceCall(String collectionName, String aceRequestID, String service,
			String type) throws Exception {
		Map<String, String> qryMap=new HashMap<String, String>();
		qryMap.put(ACECheckAPIElements.ContextMapId, aceRequestID);
		qryMap.put(ACECheckAPIElements.ContextMapServiceName, service);
		qryMap.put(ACECheckAPIElements.ContextMapRequestType, type);
		
		return retriveLatestWithQry(collectionName, qryMap, service, type);
	}

	private static Document retriveLatestWithQry(String collectionName, Map<String, String> qryMap, String service,
			String type) throws Exception {
		qryMap=normaliseElementNames(qryMap, collectionName);
		Document ACEAPIDoc=MongoDBUtils.getDocByQry(collectionName, qryMap, ACEAPISortCriteria);
		if(ACEAPIDoc != null) {
			//log.info(collectionName+" docuemnt for Qry "+qryMap+" : "+ ACEAPIDoc.toString().substring(0, 1000));
			TestScenario.writeToScenario(collectionName+" docuemnt for Qry "+qryMap+" : "+ ACEAPIDoc.toJson().toString());
			dbDocs.setDBDoc(ACEAPIDoc, collectionName, service, type);
		}
		else {
			log.info("Error: "+collectionName+" docuemnt for Qry "+qryMap+" not found");
		}
		return ACEAPIDoc;
	}

	public static void setACEAPI10RequestID() {
		ACECheckAPIDBBase req=dbDocs.getACEAPIRequest();
		if(req != null) {
			String ACEAPI10RequestID=String.valueOf(req.getLongElementValue(ACECheckAPIElements.DOC_ID));
			ACECheckAPIDerivedData.getNewACEAPI10DerivedData(ACEAPI10RequestID);
		}
	}

	public static String getACEAPI10RequestID() {
		String ACEAPI10RequestID=null;
		ACECheckAPIDerivedData derivedData=ACECheckAPIDerivedData.getDerivedData();
		if(derivedData != null) {
			ACEAPI10RequestID=derivedData.getACEAPI10RequestID();
		}
		return ACEAPI10RequestID;
	}

	private static Document retriveCorresponding(ACECheckAPIRequestData currentRequest, String collectionName) throws Exception {
		if(currentRequest == null) {
			System.out.println("No ACEAPIRequest");
			return null;
		}
		
		Map<String, String> qryMap=new HashMap<String, String>();
		qryMap.put(ACECheckAPIElements.PartyRoleIdentifier, currentRequest.getValue(ACECheckAPIElements.PartyRoleIdentifier));
		qryMap.put(ACECheckAPIElements.AddressLineText, currentRequest.getValue(ACECheckAPIElements.AddressLineText));
		qryMap.put(ACECheckAPIElements.RequestBatchIdentifier, currentRequest.getValue(ACECheckAPIElements.RequestBatchIdentifier));
		qryMap.put(ACECheckAPIElements.RequestTransactionIdentifier, currentRequest.getValue(ACECheckAPIElements.RequestTransactionIdentifier));
		
		return retriveLatestWithQry(collectionName, qryMap);
	}

	public static void getLatestDoc(String type, Map<String, String> dataMap) throws Exception {
		switch(type) {
		case ACEAPI_Request:
			retriveLatestWithQry(ACECheckAPIDBDocuments.collectionACERequests, dataMap);	
			break;
		}
	}

	private static Document retriveLatestWithQry(String collectionName, Map<String, String> dataMap) throws Exception {
		dataMap=normaliseElementNames(dataMap, collectionName);
		Document ACEAPIDoc=MongoDBUtils.getDocByQry(collectionName, dataMap, ACEAPISortCriteria);
		if(ACEAPIDoc != null) {
			//log.info(collectionName+" docuemnt for Qry "+dataMap+" : "+ ACEAPIDoc.toString());
			//TestScenario.writeToScenario(collectionName+" docuemnt for Qry "+dataMap+" : "+ ACEAPIDoc.toJson().toString());
			TestScenario.writeJSONToScenario(ACEAPIDoc.toJson().toString(), collectionName+" docuemnt for Qry "+dataMap);
			dbDocs.setDBDoc(ACEAPIDoc, collectionName);
		}
		else {
			log.info("Error: "+collectionName+" docuemnt for Qry "+dataMap+" not found");
		}
		return ACEAPIDoc;
	}
	
	private static Map<String, String> normaliseElementNames(Map<String, String> dataMap, String collectionName) {
		Map<String, String> newMap=new HashMap<String, String>();		
		for (Map.Entry<String, String> qryMapItem : dataMap.entrySet())
		{
			String elementName=qryMapItem.getKey();
			String elementPath=ACECheckAPIElementsPaths.getACEAPIElementsPaths().getDBQueryElementPath(elementName, collectionName);
			newMap.put(elementPath, qryMapItem.getValue());
		}
		return newMap;
	}

	private static String normaliseElementName(String elementName, String collectionName) {
		return ACECheckAPIElementsPaths.getACEAPIElementsPaths().getDBQueryElementPath(elementName, collectionName);
	}
	
	public static void getCorrespondingDocs() {
		try {
			getCorrespondingDoc(ACEAPI_Request);
			getCorrespondingDoc(ACEAPI_Response);
			getCorrespondingDoc(HVE_Request);
			getCorrespondingDoc(HVE_Response);
			getCorrespondingDoc(AA_Request);
			getCorrespondingDoc(AA_Response);
			GFSDB.getCorrespondingDoc(GFSDB.GFSPostData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void listAllACECheckRequestDocIDsByElementValueStartsWith(String elementName, String elementValueStartsWith) {
		try {
			String collectionName=ACECheckAPIDBDocuments.collectionACERequests;
			String elementPath=normaliseElementName(elementName, collectionName);
			List<String> docIds=MongoDBUtils.getDocIDsByFieldValueStartsWith(collectionName,elementPath, elementValueStartsWith);
			System.out.println("Number of documentIds:" +docIds.size());
			for(String docId: docIds ) {
				System.out.println("|"+docId+"|");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
}
