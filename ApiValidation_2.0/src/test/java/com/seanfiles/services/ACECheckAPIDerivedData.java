package com.seanfiles.services;

public class ACECheckAPIDerivedData {
	
	String ACEAPI10RequestID=null;
	
	public ACECheckAPIDerivedData(String paramACEAPI10RequestID) {
		ACEAPI10RequestID=paramACEAPI10RequestID;
	}
	
	public String getACEAPI10RequestID() {
		return ACEAPI10RequestID;
	}
	
	private static ACECheckAPIDerivedData derivedData=null;
	
	public static ACECheckAPIDerivedData getDerivedData() {
		return derivedData;
	}
	
	public static ACECheckAPIDerivedData getNewACEAPI10DerivedData(String ACEAPI10RequestID) {
		derivedData=new ACECheckAPIDerivedData(ACEAPI10RequestID);
		return derivedData;
	}
	
	public static void clear() {
		derivedData=null;
	}


}
