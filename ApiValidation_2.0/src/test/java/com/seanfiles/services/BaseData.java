package com.seanfiles.services;

import static com.seanfiles.elements.ACEElements.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import com.seanfiles.db.DBBase;
import com.seanfiles.db.GFSDBBase;
import com.seanfiles.helper.TestScenario;

public class BaseData {
	
	private static Logger log = Logger.getLogger(BaseData.class);
	
	public static final String ACEAPI_Request="ACEAPIRequest";
	public static final String ACEAPI_Response="ACEAPIResponse";
	public static final String ACEAPI20_Request="ACEAPI2.0Request";
	public static final String ACEAPI20_Response="ACEAPI2.0Response";
	public static final String HVE_Request="HVERequest";
	public static final String HVE_Response="HVEResponse";
	public static final String AA_Request="AARequest";
	public static final String AA_Response="AAResponse";
	public static final String GFS_GET_Request="GFSGETRequest";
	public static final String GFS_GET_Response="GFSGETResponse";
	public static final String GFS_POST_Request="GFSPOSTRequest";
	public static final String GFS_POST_Response="GFSPOSTResponse";
	
	private StringBuffer comparisonLog;
	
	List<String> multiValueElements;
	
	public void setMultiValueElements(List<String> list) {
		multiValueElements=list;
	}
	
	public BaseData() {
		comparisonLog=new StringBuffer();
	}
	
	public void resetComparisonLog() {
		comparisonLog.setLength(0);
	}
	
	public String getComparisonLog() {
		return comparisonLog.toString();
	}
	
	public void addToComparisonLog(String msg) {
		comparisonLog.append(msg+"\n");
	}
	
	public boolean compareData(Map<String, String> container1, String container1Name, 
			Map<String, String> container2, String container2Name) {
		return compareData(container1, container1Name, container2, container2Name, null);
	}
	
	public boolean compareToDB(Map<String, String> container, String containerName, DBBase db) {
		return compareToDB(container, containerName, db, null);
	}
	
	public boolean compareToDB(Map<String, String> container, String containerName, DBBase db, List<String> skipElements) {
		return compareToDB(container, containerName, db, skipElements, null);
	}
	
	public boolean compareToDB(Map<String, String> container, String containerName, DBBase db, List<String> skipElements, String Container2ElementNamePrefix) {
		for (Map.Entry<String, String> mapItem : container.entrySet())
		{
		    String elementName=mapItem.getKey();
		    if(skipElements != null) {
		    	if(skipElements.contains(elementName)) {
		    		addToComparisonLog("Skipped comparing Element: "+elementName);
		    		continue;
		    	}
		    }
		    String actualElementValue=mapItem.getValue();
		    if(Container2ElementNamePrefix != null) {
		    	elementName=Container2ElementNamePrefix+elementName;
		    }
		    String expectedElementValue=db.getElementValue(elementName);
		    if(actualElementValue !=null) {
			    if(actualElementValue.equalsIgnoreCase("__NULL_VALUE__") || actualElementValue.equalsIgnoreCase("__NO_ELEMENT__")) {
			    	actualElementValue=null;
			    }
		    }
		    if(expectedElementValue !=null) {
			    if(expectedElementValue.equalsIgnoreCase("__NULL_VALUE__") || expectedElementValue.equalsIgnoreCase("__NO_ELEMENT__")) {
			    	expectedElementValue=null;
			    }
		    }
		    addToComparisonLog("Element: "+elementName+"; value in "+containerName+": "+actualElementValue);
		    addToComparisonLog("Element: "+elementName+"; value in DB: "+expectedElementValue);
		    if((expectedElementValue == null || expectedElementValue.length() == 0 ) && (actualElementValue == null || actualElementValue.length() == 0)) {
		    	continue;
		    }
		    if(expectedElementValue == null || expectedElementValue.length() == 0 ) {
		    	return false;
		    }
		    if(actualElementValue == null || actualElementValue.length() == 0 ) {
		    	return false;
		    }
		    if(expectedElementValue.compareTo(actualElementValue) !=0) {
		    	if(isNumberElement(elementName)) {
		    		if(numbersEqual(expectedElementValue, actualElementValue)) {
		    			continue;
		    		}
		    	}
			    return false;
		    }
		}
		return true;
	}

	public boolean compareData(Map<String, String> container1, String container1Name, 
			Map<String, String> container2, String container2Name,
			List<String> skipElements) {
		return compareData(container1, container1Name, container2, container2Name, skipElements, null);
	}
	
	public boolean compareData(Map<String, String> container1, String container1Name, 
			Map<String, String> container2, String container2Name,
			List<String> skipElements, String Container2ElementNamePrefix) {
		for (Map.Entry<String, String> mapItem : container1.entrySet())
		{
		    String elementName=mapItem.getKey();
		    if(skipElements != null) {
		    	if(skipElements.contains(elementName)) {
		    		addToComparisonLog("Skipped comparing Element: "+elementName);
		    		continue;
		    	}
		    }
		    
		    String valueInContainer1=mapItem.getValue();
		    if(valueInContainer1 !=null) {
			    if(valueInContainer1.equalsIgnoreCase("__NULL_VALUE__") || valueInContainer1.equalsIgnoreCase("__NO_ELEMENT__")) {
			    	valueInContainer1=null;
			    }
		    }
		    addToComparisonLog("Element: "+elementName+"; value in "+container1Name+": "+valueInContainer1);
		    
		    if(Container2ElementNamePrefix != null) {
		    	elementName=Container2ElementNamePrefix+elementName;
		    }
		    String valueInContainer2=container2.get(elementName);
		    if(valueInContainer2 != null) {
			    if(valueInContainer2.equalsIgnoreCase("__NULL_VALUE__") || valueInContainer2.equalsIgnoreCase("__NO_ELEMENT__")) {
			    	valueInContainer2=null;
			    }
		    }
		    addToComparisonLog("Element: "+elementName+"; value in "+container2Name+": "+valueInContainer2);
		    
		    if((valueInContainer2 == null || valueInContainer2.length() == 0 ) && (valueInContainer1 == null || valueInContainer1.length() == 0)) {
		    	continue;
		    }
		    if(valueInContainer2 == null || valueInContainer2.length() == 0 ) {
		    	return false;
		    }
		    if(valueInContainer1 == null || valueInContainer1.length() == 0 ) {
		    	return false;
		    }
		    if(valueInContainer2.compareTo(valueInContainer1) !=0) {
		    	if(isNumberElement(elementName)) {
		    		if(numbersEqual(valueInContainer2, valueInContainer1)) {
		    			continue;
		    		}
		    	}
		    	else if(multiValueElements != null && multiValueElements.contains(elementName)) {
		    		if(compareMultiValueElements(valueInContainer1, valueInContainer2) == true) {
		    			continue;
		    		}
		    	}
			    return false;
		    }
		}
		return true;
	}
	
	private boolean compareMultiValueElements(String valueInContainer1, String valueInContainer2) {
		String arr1[] = valueInContainer1.split (",");
		String arr2[] = valueInContainer2.split (",");
		String bigArr[], smallArr[];
		if(arr1.length > arr2.length) {
			return false;
//			bigArr=new String[arr1.length];
//			for (int i = 0; i < arr1.length; i++) {
//				bigArr[i] = arr1[i].trim();
//			}
//			smallArr=new String[arr2.length];
//			for (int i = 0; i < arr2.length; i++) {
//				smallArr[i] = arr2[i].trim();
//			}
		}
		else {
			smallArr=new String[arr1.length];
			for (int i = 0; i < arr1.length; i++) {
				smallArr[i] = arr1[i].trim();
			}
			bigArr=new String[arr2.length];
			for (int i = 0; i < arr2.length; i++) {
				bigArr[i] = arr2[i].trim();
			}
		}
		for(String value: smallArr) {
			if(!ArrayUtils.contains(bigArr, value)) {
				return false;
			}
		}
		return true;
	}

	public void normalizeValues(Map<String, String> container) {
		for (Map.Entry<String, String> mapItem : container.entrySet())
		{
		    String elementName=mapItem.getKey();
		    if(elementName.endsWith(HomeValueExplorerConfidenceLevelType)) {
		    	container.put(elementName, adjustHVEConfidenceLevelType(mapItem.getValue()));
		    }
		    else if(elementName.endsWith(PreScrub_AddressSourceType)) {
	    	container.put(elementName, updateAddressSourceType(mapItem.getValue()));
	    }
//		    else if(elementName.endsWith(AddressMatchLevelType)) {
//		    	container.put(elementName, updateAddressMatchLevelType(mapItem.getValue()));
//		    }
//		    else if(elementName.endsWith(CarrierRouteType)) {
//		    	container.put(elementName, updateCarrierRouteType(mapItem.getValue()));
//		    }
//		    else if(elementName.endsWith(AddressUnitDesignatorType)) {
//		    	container.put(elementName, updateAddressUnitDesignatorType(mapItem.getValue()));
//		    }
		    else if(elementName.endsWith(AppraisalWaiverPrescreenExpirationDate)) {
		    	container.put(elementName, adjustDateFormat(mapItem.getValue()));
		    }
		}
	}

	private String updateAddressSourceType(String addressSourceType) {
		if(addressSourceType == null) {
			return null;
		}
		if(addressSourceType.equalsIgnoreCase("LoanProspector")) {
			addressSourceType="LoanProductAdvisor";
		}
		return addressSourceType;
	}

	private String updateAddressUnitDesignatorType(String value) {
		String valueModified=value;
		if(value == null) {
			return null;
		}
		if(value.equalsIgnoreCase("Apartment")) {
			valueModified="APARTMENT";
		}
		return valueModified;
	}

	private String updateCarrierRouteType(String value) {
		String valueModified=value;
		if(value == null) {
			return null;
		}
		if(value.equalsIgnoreCase("CityRoute")) {
			valueModified="CITY_ROUTE";
		}
		return valueModified;
	}

	private String updateAddressMatchLevelType(String value) {
		String valueModified=value;
		if(value == null) {
			return null;
		}
		if(value.equalsIgnoreCase("ExactMatch")) {
			valueModified="EXACT_MATCH";
		}
		else if(value.equalsIgnoreCase("TightMatch")) {
			valueModified="TIGHT_MATCH";
		}
		return valueModified;
	}

	private String adjustDateFormat(String in) {
		String out=in;
		if(in != null) {
			int ind = in.indexOf("-");
			if( ind>=0 ) {
				String[] array=in.split("-");
				out=array[1]+array[2]+array[0];
			}
		}
		return out;
	}

	public String adjustHVEConfidenceLevelType(String in) {
		if(in != null) {
			return in.substring(0, 1);
		}
		return in;
	}
	
//	public String adjustTimeZoneFormat(String in) {
//		String out=in;
//		if(in != null) {
//			int ind = in.lastIndexOf(":");
//			if( ind>=0 ) {
//			    out = new StringBuilder(in).replace(ind, ind+1,"").toString();
//			}
//		}
//		return out;
//	}
//	
//	public String adjustZeroAfterDecimalPoint(String in) {
//		String out=in;
//		if(in != null) {
//			if(in.endsWith(".0") || in.endsWith(".00")) {
//				int ind = in.indexOf(".");
//				out=in.substring(0,ind);
//			}
//			int ind=in.indexOf(".");
//			if(ind> 0) {
//				out=in.substring(0,ind+3);
//			}
//		}
//		return out;
//	}
//	
	private boolean isNumberElement(String elementName) {
		if(elementName.endsWith("Amount") || 
				elementName.endsWith("Count") || elementName.endsWith("Percent") || elementName.endsWith("Percent1") || elementName.endsWith("Percent2") || elementName.endsWith("Rate") ) {
			return true;
		}
		return false;
	}
	
	private boolean numbersEqual(String num1, String num2) {
		Double dval1=Double.valueOf(num1);
		Double dval2=Double.valueOf(num2);
		dval1=(double) Math.round(dval1*100.0)/100.0;
		dval2=(double) Math.round(dval2*100.0)/100.0;
		if(dval1.compareTo(dval2) == 0) {
			return true;
		}
		return false;
	}	
	
	public boolean compareData(List<Map<String, String>> messages1, String container1Name,
			List<Map<String, String>> messages2, String container2Name) {
		if(messages1.size() != messages2.size()) {
			addToComparisonLog("Number of messages in "
						+container1Name+" ("
						+messages1.size()+") is not matching with the number of messages in "
						+container2Name+" ("
						+messages2.size()+")");
			addToComparisonLog("Messages in "+ container1Name+": "+messages1);
			addToComparisonLog("Messages in "+ container2Name+": "+messages2);
			return false;
		}
		int msgCnt=1;
		for (Map<String, String> AAMessage : messages1) {
			Map<String, String> ACEAPIMessage=messages2.get(msgCnt-1);
			if(ACEAPIMessage == null) {
				return false;
			}
			addToComparisonLog("Message: "+msgCnt);
			for (Map.Entry<String, String> entry : AAMessage.entrySet()) {
				String elementName = entry.getKey();
				String expectedElementValue = entry.getValue();
				addToComparisonLog("Element: "+elementName+"; value in "+container1Name+": "+expectedElementValue);
				String actualElementValue = ACEAPIMessage.get(elementName);
				addToComparisonLog("Element: "+elementName+"; value in "+container2Name+": "+actualElementValue);
				//System.out.println("Message: "+msgCnt+" elementName: " + elementName + " expectedElementValue: " + expectedElementValue+" actualElementValue: "+actualElementValue);
			    if((expectedElementValue == null || expectedElementValue.length() == 0 ) && (actualElementValue == null || actualElementValue.length() == 0)) {
			    	continue;
			    }
			    if(expectedElementValue == null || expectedElementValue.length() == 0 ) {
			    	return false;
			    }
			    if(actualElementValue == null || actualElementValue.length() == 0 ) {
			    	return false;
			    }
				if(expectedElementValue.compareTo(actualElementValue) !=0 ) {
			    	if(isNumberElement(elementName)) {
			    		if(numbersEqual(expectedElementValue, actualElementValue)) {
			    			continue;
			    		}
			    		else {
			    			return false;
			    		}
			    	}
			    	else if(actualElementValue.contains("&amp;")) {
						actualElementValue=actualElementValue.replace("&amp;", "&");
						if(expectedElementValue.compareTo(actualElementValue) !=0 ) {
							System.out.println("Not matching");
							return false;
						}
					}
					else {
						System.out.println("Not matching");
						return false;
					}
				}
			}
			msgCnt++;
		}
		System.out.println("Matching");
		return true;
	}
	
	public Map<String, String> filterElements(Map<String, String> dataMap, List<String> elementsList) {
		Map<String, String> filteredMap=new HashMap<String, String>();		
		for(String elementName: elementsList) {
			if(elementName != null) {
				elementName=elementName.trim();
				if(dataMap.containsKey(elementName)) {
					filteredMap.put(elementName, dataMap.get(elementName));
				}
			}
		}		
		return filteredMap;
	}
	
	public Map<String, String> filterElements(Map<String, String> dataMap, List<String> elementsList, String elementNamePrefix) {
		Map<String, String> filteredMap=new HashMap<String, String>();		
		for(String elementName: elementsList) {
			if(elementName != null) {
				String tgtElementName=elementNamePrefix+elementName.trim();
				if(dataMap.containsKey(tgtElementName)) {
					filteredMap.put(tgtElementName, dataMap.get(tgtElementName));
				}
			}
		}		
		return filteredMap;
	}
	
	public boolean compare(Map<String, String> srcMap, String srcName, Map<String, String> tgtMap, String tgtName,
			List<String> skipElements, String container2ElementNamePrefix) {
		addToComparisonLog("Comparing "+srcName+" and "+tgtName+"...");
		boolean compareStatus=compareData(srcMap, srcName, tgtMap, tgtName, skipElements, container2ElementNamePrefix);
		if(compareStatus == true) {
			addToComparisonLog(srcName+" matches "+tgtName);
		}
		log.info(getComparisonLog());
		TestScenario.writeToScenario(getComparisonLog());
		resetComparisonLog();
		return compareStatus;
	}
	
	public boolean compare(Map<String, String> srcMap,
			String srcName, Map<String, String> tgtMap, String tgtName) {
		return compare(srcMap, srcName, tgtMap, tgtName, null, null);	
	}	

	public static Map<String, String> filterElements(GFSDBBase dbGFSPOSTData, List<String> elementsList) {
		Map<String, String> filteredMap=new HashMap<String, String>();		
		for(String elementName: elementsList) {
			if(elementName != null) {
				elementName=elementName.trim();
				String elementValue=dbGFSPOSTData.getElementValue(elementName);
				filteredMap.put(elementName, elementValue);
			}
		}		
		return filteredMap;
	}
	
}
