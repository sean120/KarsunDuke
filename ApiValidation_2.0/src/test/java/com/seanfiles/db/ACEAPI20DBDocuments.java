package com.seanfiles.db;

import org.bson.Document;

public class ACEAPI20DBDocuments {
	public static final String collectionACE20Requests="ACEFullAssessmentRequests";
	public static final String collectionACE20Responses="ACEFullAssessmentResponses";
	public static final String collectionServiceCallTraces="serviceCallTraces";
	public static final String dbACE20API="ACEAPI2.0";
	public static final String dbHVE="HVS_2_0";
	public static final String dbAA="EDS_2_0";
	public static final String dbGFSGET="GFS_GET_2_0";
	public static final String dbGFSPOST="GFS_POST_2_0";
	public static final String dbRequest="REQUEST";
	public static final String dbResponse="RESPONSE";
	
	
	public void setDBDoc(Document dbDoc, String collectionName) {
		setDBDoc(dbDoc, collectionName, "", "");
	}
	
	public void setDBDoc(Document dbDoc, String collectionName, String serviceName, String type) {
		switch(collectionName) {
		case collectionACE20Requests:
			setACEAPIRequest(new ACEAPI20DBBase(dbDoc, collectionACE20Requests));
			break;
		case collectionACE20Responses:
			setACEAPIResponse(new ACEAPI20DBBase(dbDoc, collectionACE20Responses));
			break;
		case collectionServiceCallTraces:
			switch(serviceName) {
			case dbHVE:
				switch(type) {
				case dbRequest:
					setHVERequest(new ACEAPI20DBBase(dbDoc, collectionServiceCallTraces));
					break;
				case dbResponse:
					setHVEResponse(new ACEAPI20DBBase(dbDoc, collectionServiceCallTraces));
					break;
				}
				break;
			case dbAA:
				switch(type) {
				case dbRequest:
					setEDSRequest(new ACEAPI20DBBase(dbDoc, collectionServiceCallTraces));
					break;
				case dbResponse:
					setEDSResponse(new ACEAPI20DBBase(dbDoc, collectionServiceCallTraces));
					break;
				}
				break;
			case dbGFSGET:
				switch(type) {
				case dbRequest:
					setGFSGETRequest(new ACEAPI20DBBase(dbDoc, collectionServiceCallTraces));
					break;
				case dbResponse:
					setGFSGETResponse(new ACEAPI20DBBase(dbDoc, collectionServiceCallTraces));
					break;
				}
				break;
			case dbGFSPOST:
				switch(type) {
				case dbRequest:
					setGFSPOSTRequest(new ACEAPI20DBBase(dbDoc, collectionServiceCallTraces));
					break;
				case dbResponse:
					setGFSPOSTResponse(new ACEAPI20DBBase(dbDoc, collectionServiceCallTraces));
					break;
				}
				break;
			}
			break;
		}
	}
	
	private void setGFSGETResponse(ACEAPI20DBBase aceapi20dbBase) {
		GFSGETResponse=aceapi20dbBase;		
	}

	private void setGFSGETRequest(ACEAPI20DBBase aceapi20dbBase) {
		GFSGETRequest=aceapi20dbBase;
		
	}
	
	public ACEAPI20DBBase getGFSGETRequest() {
		return GFSGETRequest;
	}
	
	public ACEAPI20DBBase getGFSGETResponse() {
		return GFSGETResponse;
	}

	private void setGFSPOSTResponse(ACEAPI20DBBase aceapi20dbBase) {
		GFSPOSTResponse=aceapi20dbBase;		
	}

	private void setGFSPOSTRequest(ACEAPI20DBBase aceapi20dbBase) {
		GFSPOSTRequest=aceapi20dbBase;
		
	}
	
	public ACEAPI20DBBase getGFSPOSTRequest() {
		return GFSPOSTRequest;
	}
	
	public ACEAPI20DBBase getGFSPOSTResponse() {
		return GFSPOSTResponse;
	}

	public ACEAPI20DBBase getACEAPIRequest() {
		return ACEAPIRequest;
	}

	public void setACEAPIRequest(ACEAPI20DBBase aCEAPIRequest) {
		ACEAPIRequest = aCEAPIRequest;
	}

	public ACEAPI20DBBase getACEAPIResponse() {
		return ACEAPIResponse;
	}

	public void setACEAPIResponse(ACEAPI20DBBase aCEAPIResponse) {
		ACEAPIResponse = aCEAPIResponse;
	}

	public ACEAPI20DBBase getHVERequest() {
		return HVERequest;
	}

	public void setHVERequest(ACEAPI20DBBase hVERequest) {
		HVERequest = hVERequest;
	}

	public ACEAPI20DBBase getHVEResponse() {
		return HVEResponse;
	}

	public void setHVEResponse(ACEAPI20DBBase hVEResponse) {
		HVEResponse = hVEResponse;
	}

	public ACEAPI20DBBase getEDSRequest() {
		return EDSRequest;
	}

	public void setEDSRequest(ACEAPI20DBBase eDSRequest) {
		EDSRequest = eDSRequest;
	}

	public ACEAPI20DBBase getEDSResponse() {
		return EDSResponse;
	}

	public void setEDSResponse(ACEAPI20DBBase eDSResponse) {
		EDSResponse = eDSResponse;
	}


	private ACEAPI20DBBase ACEAPIRequest;
	private ACEAPI20DBBase ACEAPIResponse;
	private ACEAPI20DBBase HVERequest;
	private ACEAPI20DBBase HVEResponse;
	private ACEAPI20DBBase EDSRequest;
	private ACEAPI20DBBase EDSResponse;
	private ACEAPI20DBBase GFSGETRequest;
	private ACEAPI20DBBase GFSGETResponse;
	private ACEAPI20DBBase GFSPOSTRequest;
	private ACEAPI20DBBase GFSPOSTResponse;
}
