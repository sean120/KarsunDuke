package com.seanfiles.db;

import static com.seanfiles.services.BaseData.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.seanfiles.elements.ACEAPI20Elements;
import com.seanfiles.elements.ACEAPI20ElementsPaths;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.services.ACEAPI20AARequestData;
import com.seanfiles.services.ACEAPI20AAResponseData;
import com.seanfiles.services.ACEAPI20DerivedData;
import com.seanfiles.services.ACEAPI20HVERequestData;
import com.seanfiles.services.ACEAPI20HVEResponseData;
import com.seanfiles.services.ACEAPI20RequestData;
import com.seanfiles.services.GFSGETResponseData;
import com.seanfiles.utils.MongoDBUtils;

public class ACEAPI20DB {
	public static ACEAPI20DBDocuments dbDocs=new ACEAPI20DBDocuments();
	static final String ACEAPI20SortCriteria = "{lastModifiedDate:-1}";
	static final String ACEAPI20ServicesSortCriteria = "{date:-1}";
	
	public static void clear() {
		dbDocs=new ACEAPI20DBDocuments();
	}
	
	public static ACEAPI20DBDocuments getCurrentACEAPIDBDocuments() {
		return dbDocs;
	}
	
	public static Document retriveByReqID(String collectionName, String reqID) throws Exception {
		Document ACEAPIDoc=null;
		if(reqID != null) {
			ACEAPIDoc=MongoDBUtils.getDocByField(collectionName,ACEAPI20Elements.DOC_ID, Long.valueOf(reqID), ACEAPI20SortCriteria);
		}
		
		if(ACEAPIDoc != null) {
			TestScenario.writeJSONToScenario(ACEAPIDoc.toJson().toString(), collectionName+" docuemnt for reqID "+reqID);
			dbDocs.setDBDoc(ACEAPIDoc, collectionName);
		}
		else {
			TestScenario.writeToScenario("Error: "+collectionName+" docuemnt for reqID "+reqID+" not found");
		}
		return ACEAPIDoc;
	}
	
	public static Document retriveLatest(String collectionName) throws Exception {
		Document ACEAPIDoc=MongoDBUtils.getMostRecentDocument(collectionName, ACEAPI20SortCriteria);
		if(ACEAPIDoc != null) {
			TestScenario.writeJSONToScenario(ACEAPIDoc.toJson().toString(), collectionName+" latest docuemnt");
			dbDocs.setDBDoc(ACEAPIDoc, collectionName);
		}
		else {
			TestScenario.writeToScenario("Error: "+collectionName+" latest docuemnt not found");
		}
		return ACEAPIDoc;
	}

	public static void getLatestDoc(String type) throws Exception {
		switch(type) {
		case ACEAPI_Request:
			retriveLatest(ACEAPI20DBDocuments.collectionACE20Requests);	
			break;
		case ACEAPI_Response:
			retriveLatest(ACEAPI20DBDocuments.collectionACE20Responses);	
			break;
		}
	}

	public static void getCorrespondingDoc(String type) throws Exception {
		Document doc=null;
		switch(type) {
		case ACEAPI20_Request:
			if( getACEAPI20RequestID() != null) {
				doc=retriveByReqID(ACEAPI20DBDocuments.collectionACE20Requests, getACEAPI20RequestID());
				setACEAPI20RequestID();
			}
			else {
				doc=retriveCorresponding(ACEAPI20RequestData.getCurrentRequest(), ACEAPI20DBDocuments.collectionACE20Requests);
				setACEAPI20RequestID();
			}
			break;
		case ACEAPI20_Response:
			if(getACEAPI20RequestID() == null) {
				getCorrespondingDoc(ACEAPI20_Request);
			}
			doc=retriveByReqID(ACEAPI20DBDocuments.collectionACE20Responses, getACEAPI20RequestID());	
			break;
		case HVE_Request:
			if(getACEAPI20RequestID() == null) {
				getCorrespondingDoc(ACEAPI20_Request);
			}
			doc=retriveCorrespondingServiceCall(ACEAPI20DBDocuments.collectionServiceCallTraces, getACEAPI20RequestID(), ACEAPI20DBDocuments.dbHVE, ACEAPI20DBDocuments.dbRequest);
			if(doc != null) {
				String HVERequestXML=dbDocs.getHVERequest().getElementValue(ACEAPI20Elements.Message);
				if(HVERequestXML != null) {
					ACEAPI20HVERequestData.setHVERequest(HVERequestXML);
					TestScenario.writeXMLToScenario(HVERequestXML, "HVE Request XML");
				}
			}
			break;
		case HVE_Response:
			if(getACEAPI20RequestID() == null) {
				getCorrespondingDoc(ACEAPI20_Request);
			}
			doc=retriveCorrespondingServiceCall(ACEAPI20DBDocuments.collectionServiceCallTraces, getACEAPI20RequestID(), ACEAPI20DBDocuments.dbHVE, ACEAPI20DBDocuments.dbResponse);	
			if(doc != null) {
				String HVERsponseXML=dbDocs.getHVEResponse().getElementValue(ACEAPI20Elements.Message);
				if(HVERsponseXML != null) {
					ACEAPI20HVEResponseData.setHVEResponse(HVERsponseXML);
					TestScenario.writeXMLToScenario(HVERsponseXML, "HVE Response XML");
				}
			}
			break;
		case AA_Request:
			if(getACEAPI20RequestID() == null) {
				getCorrespondingDoc(ACEAPI20_Request);
			}
			doc=retriveCorrespondingServiceCall(ACEAPI20DBDocuments.collectionServiceCallTraces, getACEAPI20RequestID(), ACEAPI20DBDocuments.dbAA, ACEAPI20DBDocuments.dbRequest);	
			if(doc != null) {
				String AARequestXML=dbDocs.getEDSRequest().getElementValue(ACEAPI20Elements.Message);
				if(AARequestXML != null) {
					ACEAPI20AARequestData.setAARequest(AARequestXML);
					TestScenario.writeXMLToScenario(AARequestXML, "AA Request XML");
				}
			}
			break;
		case AA_Response:
			if(getACEAPI20RequestID() == null) {
				getCorrespondingDoc(ACEAPI20_Request);
			}
			doc=retriveCorrespondingServiceCall(ACEAPI20DBDocuments.collectionServiceCallTraces, getACEAPI20RequestID(), ACEAPI20DBDocuments.dbAA, ACEAPI20DBDocuments.dbResponse);	
			if(doc != null) {				
				String AARsponseXML=dbDocs.getEDSResponse().getElementValue(ACEAPI20Elements.Message);
				if(AARsponseXML != null) {
					ACEAPI20AAResponseData.setAAResponse(AARsponseXML);
					TestScenario.writeXMLToScenario(AARsponseXML, "AA Response XML");
				}
			}
			break;
		case GFS_GET_Response:
			if( getACEAPI20RequestID() == null) {
				getCorrespondingDoc(ACEAPI20_Request);	
			}
			doc=retriveCorrespondingServiceCall(ACEAPI20DBDocuments.collectionServiceCallTraces, getACEAPI20RequestID(), ACEAPI20DBDocuments.dbGFSGET, ACEAPI20DBDocuments.dbResponse);
			if(doc != null) {
				String GFSGETResponseJSON=dbDocs.getGFSGETResponse().getElementValue(ACEAPI20Elements.Message);
				if(GFSGETResponseJSON != null) {
					GFSGETResponseData.setCurrentGETResponse(GFSGETResponseJSON);
					TestScenario.writeJSONToScenario(GFSGETResponseJSON, "GFS GET Response JSON");
				}
			}
			break;
		case GFS_GET_Request:
			if( getACEAPI20RequestID() == null) {
				getCorrespondingDoc(ACEAPI20_Request);	
			}
			doc=retriveCorrespondingServiceCall(ACEAPI20DBDocuments.collectionServiceCallTraces, getACEAPI20RequestID(), ACEAPI20DBDocuments.dbGFSGET, ACEAPI20DBDocuments.dbRequest);
			if(doc != null) {
				String GFSGETRequestJSON=dbDocs.getGFSGETRequest().getElementValue(ACEAPI20Elements.Message);
				if(GFSGETRequestJSON != null) {
					//GFSGETRequestData.setCurrentGETRequest(GFSGETRequestJSON);
					TestScenario.writeJSONToScenario(GFSGETRequestJSON, "GFS GET Request JSON");
				}
			}
			break;
		case GFS_POST_Request:
			if( getACEAPI20RequestID() == null) {
				getCorrespondingDoc(ACEAPI20_Request);	
			}
			doc=retriveCorrespondingServiceCall(ACEAPI20DBDocuments.collectionServiceCallTraces, getACEAPI20RequestID(), ACEAPI20DBDocuments.dbGFSPOST, ACEAPI20DBDocuments.dbRequest);
			if(doc != null) {
				String GFSPOSTRequestJSON=dbDocs.getGFSPOSTRequest().getElementValue(ACEAPI20Elements.Message);
				if(GFSPOSTRequestJSON != null) {
					//GFSPOSTRequestData.setCurrentPOSTRequest(GFSPOSTRequestJSON);
					TestScenario.writeJSONToScenario(GFSPOSTRequestJSON, "GFS POST Request JSON");
				}
			}
			break;
		case GFS_POST_Response:
			if( getACEAPI20RequestID() == null) {
				getCorrespondingDoc(ACEAPI20_Request);	
			}
			doc=retriveCorrespondingServiceCall(ACEAPI20DBDocuments.collectionServiceCallTraces, getACEAPI20RequestID(), ACEAPI20DBDocuments.dbGFSPOST, ACEAPI20DBDocuments.dbResponse);
			if(doc != null) {
				String GFSPOSTResponseJSON=dbDocs.getGFSPOSTResponse().getElementValue(ACEAPI20Elements.Message);
				if(GFSPOSTResponseJSON != null) {
					//GFSPOSTResponseData.setCurrentPOSTResponse(GFSPOSTResponseJSON);
					TestScenario.writeJSONToScenario(GFSPOSTResponseJSON, "GFS POST Response JSON");
				}
			}
			break;
		}
	}

	private static Document retriveCorrespondingServiceCall(String collectionName, String aceRequestID, String service,
			String type) throws Exception {
		Map<String, String> qryMap=new HashMap<String, String>();
		qryMap.put(ACEAPI20Elements.ContextMapId, aceRequestID);
		qryMap.put(ACEAPI20Elements.ContextMapServiceName, service);
		qryMap.put(ACEAPI20Elements.ContextMapRequestType, type);
		
		return retriveLatestWithQry(collectionName, qryMap, service, type);
	}
	
	public static void listAllACE20RequestDocIDsByElementValueStartsWith(String elementName, String elementValueStartsWith) {
		try {
			String collectionName=ACEAPI20DBDocuments.collectionACE20Requests;
			String elementPath=normaliseElementName(elementName, collectionName);
			List<String> docIds=MongoDBUtils.getDocIDsByFieldValueStartsWith(collectionName,elementPath, elementValueStartsWith);
			System.out.println("Number of documentIds:" +docIds.size());
			for(String docId: docIds ) {
				System.out.println("|"+docId+"|");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Document retriveLatestWithQry(String collectionName, Map<String, String> qryMap, String service,
			String type) throws Exception {
		qryMap=normaliseElementNames(qryMap, collectionName);
		Document ACEAPIDoc=MongoDBUtils.getDocByQry(collectionName, qryMap, ACEAPI20SortCriteria);
		if(ACEAPIDoc != null) {
			TestScenario.writeJSONToScenario(ACEAPIDoc.toJson().toString(), collectionName+" docuemnt for Qry "+qryMap);
			dbDocs.setDBDoc(ACEAPIDoc, collectionName, service, type);
		}
		else {
			TestScenario.writeToScenario("Error: "+collectionName+" docuemnt for Qry "+qryMap+" not found");
		}
		return ACEAPIDoc;
	}

	public static void setACEAPI20RequestID() {
		ACEAPI20DBBase req=dbDocs.getACEAPIRequest();
		if(req != null) {
			String ACEAPI20RequestID=String.valueOf(req.getLongElementValue(ACEAPI20Elements.DOC_ID));			
			ACEAPI20DerivedData.getNewACEAPI20DerivedData(ACEAPI20RequestID);
			
			ACEAPI20RequestData currentACEAPI20Request=ACEAPI20RequestData.getCurrentRequest();
			if(currentACEAPI20Request == null) {
				ACEAPI20RequestData.createACEAPI20RequestFromDBDoc();
			}
			
		}
	}

	public static String getACEAPI20RequestID() {
		String ACEAPI20RequestID=null;
		ACEAPI20DerivedData derivedData=ACEAPI20DerivedData.getDerivedData();
		if(derivedData != null) {
			ACEAPI20RequestID=derivedData.getACEAPI20RequestID();
		}
		return ACEAPI20RequestID;
	}

	private static Document retriveCorresponding(ACEAPI20RequestData currentRequest, String collectionName) throws Exception {
		if(currentRequest == null) {
			System.out.println("No ACEAPIRequest");
			return null;
		}
		
		Map<String, String> qryMap=new HashMap<String, String>();
		String SubscriberIdentifier=currentRequest.getValue(ACEAPI20Elements.SubscriberIdentifier);
		if(SubscriberIdentifier != null) {
			qryMap.put(ACEAPI20Elements.SubscriberIdentifier, SubscriberIdentifier);
		}
		
		String SubscriberRequestCorrelationIdentifier=currentRequest.getValue(ACEAPI20Elements.SubscriberRequestCorrelationIdentifier);
		if(SubscriberRequestCorrelationIdentifier != null) {
			qryMap.put(ACEAPI20Elements.SubscriberRequestCorrelationIdentifier, SubscriberRequestCorrelationIdentifier);
		}

		return retriveLatestWithQry(collectionName, qryMap);
	}

	public static void getLatestDoc(String type, Map<String, String> dataMap) throws Exception {
		switch(type) {
		case ACEAPI_Request:
			retriveLatestWithQry(ACEAPI20DBDocuments.collectionACE20Requests, dataMap);	
			break;
		}
	}

	private static Document retriveLatestWithQry(String collectionName, Map<String, String> dataMap) throws Exception {
		dataMap=normaliseElementNames(dataMap, collectionName);
		Document ACEAPIDoc=MongoDBUtils.getDocByQry(collectionName, dataMap, ACEAPI20SortCriteria);
		if(ACEAPIDoc != null) {
			TestScenario.writeJSONToScenario(ACEAPIDoc.toJson().toString(), collectionName+" docuemnt for Qry "+dataMap);
			dbDocs.setDBDoc(ACEAPIDoc, collectionName);
		}
		else {
			TestScenario.writeToScenario("Error: "+collectionName+" docuemnt for Qry "+dataMap+" not found");
		}
		return ACEAPIDoc;
	}
	
	private static String normaliseElementName(String elementName, String collectionName) {
		return ACEAPI20ElementsPaths.getACEAPI20ElementsPaths().getDBQueryElementPath(elementName, collectionName);
	}

	private static Map<String, String> normaliseElementNames(Map<String, String> dataMap, String collectionName) {
		Map<String, String> newMap=new HashMap<String, String>();		
		for (Map.Entry<String, String> qryMapItem : dataMap.entrySet())
		{
			String elementName=qryMapItem.getKey();
			String elementPath=ACEAPI20ElementsPaths.getACEAPI20ElementsPaths().getDBQueryElementPath(elementName, collectionName);
			newMap.put(elementPath, qryMapItem.getValue());
		}
		return newMap;
	}

	public static void getCorrespondingDocs() {
		try {
			getCorrespondingDoc(ACEAPI20_Request);
			getCorrespondingDoc(ACEAPI20_Response);
			getCorrespondingDoc(HVE_Request);
			getCorrespondingDoc(HVE_Response);
			getCorrespondingDoc(AA_Request);
			getCorrespondingDoc(AA_Response);
			
			ACEAPI20DBBase req=dbDocs.getACEAPIRequest();
			if(req != null) {
				String grandfatheringIndicator=req.getElementValue(ACEAPI20Elements.GrandfatheringIndicator);
				if(grandfatheringIndicator.equalsIgnoreCase("Yes")) {
					GFSDB.getCorrespondingACEAPI20GFSDocs(GFSDB.GFSGetRequest);
					GFSDB.getCorrespondingACEAPI20GFSDocs(GFSDB.GFSGetResponse);
					GFSDB.getCorrespondingACEAPI20GFSDocs(GFSDB.GFSPostData);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
}
