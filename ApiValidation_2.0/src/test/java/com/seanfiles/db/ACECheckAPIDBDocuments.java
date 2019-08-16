package com.seanfiles.db;

import org.bson.Document;

public class ACECheckAPIDBDocuments {
	public static final String collectionACERequests="ACERequests";
	public static final String collectionACEResponses="ACEResponses";
	public static final String collectionServiceCallTraces="serviceCallTraces";
	public static final String dbACEAPI="ACEAPI";
	public static final String dbHVE="HVS";
	public static final String dbAA="EDS";
	public static final String dbRequest="REQUEST";
	public static final String dbResponse="RESPONSE";
	
	
	public void setDBDoc(Document dbDoc, String collectionName) {
		setDBDoc(dbDoc, collectionName, "", "");
	}
	
	public void setDBDoc(Document dbDoc, String collectionName, String serviceName, String type) {
		switch(collectionName) {
		case collectionACERequests:
			setACEAPIRequest(new ACECheckAPIDBBase(dbDoc, collectionACERequests));
			break;
		case collectionACEResponses:
			setACEAPIResponse(new ACECheckAPIDBBase(dbDoc, collectionACEResponses));
			break;
		case collectionServiceCallTraces:
			switch(serviceName) {
			case dbHVE:
				switch(type) {
				case dbRequest:
					setHVERequest(new ACECheckAPIDBBase(dbDoc, collectionServiceCallTraces));
					break;
				case dbResponse:
					setHVEResponse(new ACECheckAPIDBBase(dbDoc, collectionServiceCallTraces));
					break;
				}
				break;
			case dbAA:
				switch(type) {
				case dbRequest:
					setEDSRequest(new ACECheckAPIDBBase(dbDoc, collectionServiceCallTraces));
					break;
				case dbResponse:
					setEDSResponse(new ACECheckAPIDBBase(dbDoc, collectionServiceCallTraces));
					break;
				}
				break;
			}
			break;
		}
	}
	
	public ACECheckAPIDBBase getACEAPIRequest() {
		return ACEAPIRequest;
	}

	public void setACEAPIRequest(ACECheckAPIDBBase aCEAPIRequest) {
		ACEAPIRequest = aCEAPIRequest;
	}

	public ACECheckAPIDBBase getACEAPIResponse() {
		return ACEAPIResponse;
	}

	public void setACEAPIResponse(ACECheckAPIDBBase aCEAPIResponse) {
		ACEAPIResponse = aCEAPIResponse;
	}

	public ACECheckAPIDBBase getHVERequest() {
		return HVERequest;
	}

	public void setHVERequest(ACECheckAPIDBBase hVERequest) {
		HVERequest = hVERequest;
	}

	public ACECheckAPIDBBase getHVEResponse() {
		return HVEResponse;
	}

	public void setHVEResponse(ACECheckAPIDBBase hVEResponse) {
		HVEResponse = hVEResponse;
	}

	public ACECheckAPIDBBase getEDSRequest() {
		return EDSRequest;
	}

	public void setEDSRequest(ACECheckAPIDBBase eDSRequest) {
		EDSRequest = eDSRequest;
	}

	public ACECheckAPIDBBase getEDSResponse() {
		return EDSResponse;
	}

	public void setEDSResponse(ACECheckAPIDBBase eDSResponse) {
		EDSResponse = eDSResponse;
	}


	private ACECheckAPIDBBase ACEAPIRequest;
	private ACECheckAPIDBBase ACEAPIResponse;
	private ACECheckAPIDBBase HVERequest;
	private ACECheckAPIDBBase HVEResponse;
	private ACECheckAPIDBBase EDSRequest;
	private ACECheckAPIDBBase EDSResponse;
}
