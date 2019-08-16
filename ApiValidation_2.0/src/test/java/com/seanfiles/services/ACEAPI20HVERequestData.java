package com.seanfiles.services;

import static com.seanfiles.elements.ACEAPI20Elements.*;

import java.util.HashMap;
import java.util.Map;

import com.seanfiles.utils.XMLParserUtil;

public class ACEAPI20HVERequestData  extends BaseData{
	private XMLParserUtil HVERequestXML=null;
	
	Map<String, String> requestMap;
	
	public ACEAPI20HVERequestData(String requestXMLStr) {
		HVERequestXML=new XMLParserUtil(requestXMLStr);
		loadrequestMapElementsMap();
	}

	private void loadrequestMapElementsMap() {
		requestMap= new HashMap<String, String>();
		requestMap.put(AddressLineText, 
				HVERequestXML.getElementValueByXPath("//ADDRESS/AddressLineText/text()"));
		requestMap.put(CityName, 
				HVERequestXML.getElementValueByXPath("//ADDRESS/CityName/text()"));
		requestMap.put(PostalCode, 
				HVERequestXML.getElementValueByXPath("//ADDRESS/PostalCode/text()"));
		requestMap.put(StateCode, 
				HVERequestXML.getElementValueByXPath("//ADDRESS/FIPSStateAlphaCode/text()"));
		requestMap.put(ZipPlusFourCode, 
				HVERequestXML.getElementValueByXPath("//ADDRESS/ZIPPlusFourCode/text()"));
		requestMap.put(CensusTractBaseIdentifier, 
				HVERequestXML.getElementValueByXPath("//GEOCODE_INFORMATION/CensusTractBaseIdentifier/text()"));
		requestMap.put(CoreBasedStatisticalAreaCode, 
				HVERequestXML.getElementValueByXPath("//GEOCODE_INFORMATION/CoreBasedStatisticalAreaCode/text()"));
		requestMap.put(FIPSStateNumericCode, 
				HVERequestXML.getElementValueByXPath("//GEOCODE_INFORMATION/FIPSStateNumericCode/text()"));
		requestMap.put(LatitudeNumber, 
				HVERequestXML.getElementValueByXPath("//GEOCODE_INFORMATION/LatitudeNumber/text()"));
		requestMap.put(LongitudeNumber, 
				HVERequestXML.getElementValueByXPath("//GEOCODE_INFORMATION/LongitudeNumber/text()"));
		requestMap.put(FIPSCountyThreeDigitCode, 
				HVERequestXML.getElementValueByXPath("//GEOCODE_INFORMATION_DERIVATION/FIPSCountyThreeDigitCode/text()"));
		
		requestMap.put(SubscriberRequestCorrelationIdentifier, 
				HVERequestXML.getElementValueByXPath("//ServiceRequest/SubscriberRequestCorrelationIdentifier/text()"));
		
		requestMap.put(LoanID_LPT, 
				HVERequestXML.getElementValueByXPath("//AlternativeLoanIdentifications/AlternativeLoanIdentification[LoanIdentifierType='LoanProspectorTransaction']/AlternateLoanIdentifier/text()"));
		requestMap.put(LoanID_LPUL, 
				HVERequestXML.getElementValueByXPath("//AlternativeLoanIdentifications/AlternativeLoanIdentification[LoanIdentifierType='LoanProspectorUniqueLoan']/AlternateLoanIdentifier/text()"));
		requestMap.put(LoanID_LQA, 
				HVERequestXML.getElementValueByXPath("//AlternativeLoanIdentifications/AlternativeLoanIdentification[LoanIdentifierType='LoanQualityAdvisor']/AlternateLoanIdentifier/text()"));
		
		requestMap.put(HVS_AccountIdentifier, 
				HVERequestXML.getElementValueByXPath("//HVSRequest/AccountIdentifier/text()"));
		requestMap.put(HVS_RequestType, 
				HVERequestXML.getElementValueByXPath("//HVSRequestContainer/RequestType/text()"));
		requestMap.put(HVS_ClientSchemaVersionIdentifier, 
				HVERequestXML.getElementValueByXPath("//ServiceRequest/ClientSchemaVersionIdentifier/text()"));
		requestMap.put(HVS_RequestorIdentifier, 
				HVERequestXML.getElementValueByXPath("//ServiceRequest/RequestorIdentifier/text()"));
		requestMap.put(HVS_SchemaVersionIdentifier, 
				HVERequestXML.getElementValueByXPath("//ServiceRequest/SchemaVersionIdentifier/text()"));
		requestMap.put(HVS_ServiceName, 
				HVERequestXML.getElementValueByXPath("//ServiceRequest/ServiceName/text()"));
		requestMap.put(HVS_ServiceRequestDateTime, 
				HVERequestXML.getElementValueByXPath("//ServiceRequest/ServiceRequestDateTime/text()"));
		requestMap.put(HVS_ServiceRequestOperationName, 
				HVERequestXML.getElementValueByXPath("//ServiceRequest/ServiceRequestOperationName/text()"));
	}

	public Map<String, String> getRequestMap() {
		return requestMap;
	}

	private static ACEAPI20HVERequestData currentRequest=null;
	
	public static ACEAPI20HVERequestData getCurrentRequest() {
		return currentRequest;
	}
	
	public static void setHVERequest(String requestXMLStr) {
		currentRequest=new ACEAPI20HVERequestData(requestXMLStr);
	}

	public static void clear() {
		currentRequest=null;
	}

}
