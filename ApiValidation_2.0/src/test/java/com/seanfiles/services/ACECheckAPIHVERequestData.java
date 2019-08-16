package com.seanfiles.services;

import static com.seanfiles.elements.ACECheckAPIElements.*;

import java.util.HashMap;
import java.util.Map;

import com.seanfiles.utils.XMLParserUtil;

public class ACECheckAPIHVERequestData  extends BaseData{
	private XMLParserUtil HVERequestXML=null;
	
	Map<String, String> address;
	
	public ACECheckAPIHVERequestData(String requestXMLStr) {
		HVERequestXML=new XMLParserUtil(requestXMLStr);
		loadAddressElementsMap();
	}

	private void loadAddressElementsMap() {
		address= new HashMap<String, String>();
		address.put(AddressLineText, 
				HVERequestXML.getElementValueByXPath("//ADDRESS/AddressLineText/text()"));
		address.put(CityName, 
				HVERequestXML.getElementValueByXPath("//ADDRESS/CityName/text()"));
		address.put(PostalCode, 
				HVERequestXML.getElementValueByXPath("//ADDRESS/PostalCode/text()"));
		address.put(StateCode, 
				HVERequestXML.getElementValueByXPath("//ADDRESS/FIPSStateAlphaCode/text()"));
	}

	public Map<String, String> getAddress() {
		return address;
	}

	private static ACECheckAPIHVERequestData currentRequest=null;
	
	public static ACECheckAPIHVERequestData getCurrentRequest() {
		return currentRequest;
	}
	
	public static void setHVERequest(String requestXMLStr) {
		currentRequest=new ACECheckAPIHVERequestData(requestXMLStr);
	}

	public static void clear() {
		currentRequest=null;
	}

}
