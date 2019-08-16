package com.seanfiles.db;

import org.bson.Document;

public class GFSDBDocuments {
	public static final String collectionGFSACEData="grandFatheringAceData";
	public static final String collectionGFSPVData="grandFatheringPresentValueData";
	public static final String collectionGFSPVRequest="grandFatheringPresentValueRequest";
	public static final String collectionGFSPVResponse="grandFatheringPresentValueResponse";
	public static final String collectionGFSACERequest="grandFatheringAceRequest";
	public static final String collectionGFSACEResponse="grandFatheringAceResponse";
	
	public GFSDBBase getDbRequestACE() {
		return dbRequestACE;
	}
	public void setDbRequestACE(GFSDBBase dbRequestACE) {
		this.dbRequestACE = dbRequestACE;
	}
	public GFSDBBase getDbResponseACE() {
		return dbResponseACE;
	}
	public void setDbResponseACE(GFSDBBase dbResponseACE) {
		this.dbResponseACE = dbResponseACE;
	}
	public GFSDBBase getDbRequestPV() {
		return dbRequestPV;
	}
	public void setDbRequestPV(GFSDBBase dbRequestPV) {
		this.dbRequestPV = dbRequestPV;
	}
	public GFSDBBase getDbResponsePV() {
		return dbResponsePV;
	}
	public void setDbResponsePV(GFSDBBase dbResponsePV) {
		this.dbResponsePV = dbResponsePV;
	}
	public GFSDBBase getDbDataACE() {
		return dbDataACE;
	}
	public void setDbDataACE(GFSDBBase dbDataACE) {
		this.dbDataACE = dbDataACE;
	}
	public GFSDBBase getDbDataPV() {
		return dbDataPV;
	}
	public void setDbDataPV(GFSDBBase dbDataPV) {
		this.dbDataPV = dbDataPV;
	}
	
	public void setDBDoc(Document dbDoc, String collectionName) {
		switch(collectionName) {
		case collectionGFSACEData:
			setDbDataACE(new GFSDBBase(dbDoc, collectionGFSACEData));
			break;
		case collectionGFSPVData:
			setDbDataPV(new GFSDBBase(dbDoc, collectionGFSPVData));
			break;
		case collectionGFSPVRequest:
			setDbRequestPV(new GFSDBBase(dbDoc, collectionGFSPVRequest));
			break;
		case collectionGFSPVResponse:
			setDbResponsePV(new GFSDBBase(dbDoc, collectionGFSPVResponse));
			break;
		case collectionGFSACERequest:
			setDbRequestACE(new GFSDBBase(dbDoc, collectionGFSACERequest));
			break;
		case collectionGFSACEResponse:
			setDbResponseACE(new GFSDBBase(dbDoc, collectionGFSACEResponse));
			break;
		}
	}
	
	public String getDocID(String dataGroup) {
		String GFSDocID=null;
		if(dataGroup.equalsIgnoreCase(collectionGFSACERequest)) {
			GFSDocID=dbRequestACE.getObjectID();
		}
		else if(dataGroup.equalsIgnoreCase(collectionGFSPVRequest)) {
			GFSDocID=dbRequestPV.getObjectID();
		}
		if(dataGroup.equalsIgnoreCase(collectionGFSACEData)) {
			GFSDocID=dbDataACE.getObjectID();
		}
		else if(dataGroup.equalsIgnoreCase(collectionGFSPVData)) {
			GFSDocID=dbDataPV.getObjectID();
		}
		if(dataGroup.equalsIgnoreCase(collectionGFSACEResponse)) {
			GFSDocID=dbResponseACE.getObjectID();
		}
		else if(dataGroup.equalsIgnoreCase(collectionGFSPVResponse)) {
			GFSDocID=dbResponsePV.getObjectID();
		}
		return GFSDocID;
	}

	
	private GFSDBBase dbRequestACE;
	private GFSDBBase dbResponseACE;
	private GFSDBBase dbRequestPV;
	private GFSDBBase dbResponsePV;
	private GFSDBBase dbDataACE;
	private GFSDBBase dbDataPV;
}
