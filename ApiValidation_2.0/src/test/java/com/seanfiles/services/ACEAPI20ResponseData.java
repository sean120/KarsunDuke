package com.seanfiles.services;

import static com.seanfiles.elements.ACEAPI20Elements.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.seanfiles.db.ACEAPI20DB;
import com.seanfiles.db.ACEAPI20DBBase;
import com.seanfiles.elements.ACEAPI20ElementsPaths;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.utils.JSONUtilities;

public class ACEAPI20ResponseData extends BaseData{
	Map<String, String> responseMap;
	List<Map<String,String>> messages = new ArrayList<Map<String,String>>();
	
	public ACEAPI20ResponseData(String responseJSONStr) {
		responseMap=new HashMap<String, String>();
		parseResponseJSON(responseJSONStr);
	}
	
	private void parseResponseJSON(String responseJSONStr) {
		DocumentContext responseJSON= JsonPath.parse(responseJSONStr);
		setValue(responseJSON, AlternateAppraisalDecisionExpirationDatetime);
		setValue(responseJSON, AlternateAppraisalDecisionStatusType);
		setValue(responseJSON, AlternateAppraisalEligibilityDecision);
		setValue(responseJSON, MinimumLoanAssessmentFormType);
		setValue(responseJSON, PropertyValuationEffectiveDateTime);
		
		setValue(responseJSON, ErrorCode);
		setValue(responseJSON, ErrorMessage);
		
		parseAlternateAppraisalReasonList(responseJSON);
		parseMessages(responseJSON);
		
	}

	private void parseAlternateAppraisalReasonList(DocumentContext responseJSON) {
		int cnt=0;
		String alternateAppraisalReasonList=getArrayElementValue(responseJSON, AlternateAppraisalReasonList, cnt);
		cnt++;
		String nextValue=getArrayElementValue(responseJSON, AlternateAppraisalReasonList, cnt);
		while(nextValue !=null) {
			alternateAppraisalReasonList=alternateAppraisalReasonList+","+nextValue;
			cnt++;
			nextValue=getArrayElementValue(responseJSON, AlternateAppraisalReasonList, cnt);
		}
		System.out.println(alternateAppraisalReasonList);
		setValue(AlternateAppraisalReasonList, alternateAppraisalReasonList);
	}

	private String mergeListElementsWithPipe(DocumentContext responseJSON, String elementName, int index) {
		int cnt=0;
		String mergedListStr=getArrayElementValue(responseJSON, elementName, index, cnt);
		cnt++;
		String nextValue=getArrayElementValue(responseJSON, elementName, index, cnt);
		while(nextValue !=null) {
			mergedListStr=mergedListStr+"|"+nextValue;
			cnt++;
			nextValue=getArrayElementValue(responseJSON, elementName, index, cnt);
		}
		return mergedListStr;
	}

	private void parseMessages(DocumentContext responseJSON) {
		int messageNum=0;
		String messageCodeList=null;
		String sequenceNumber=getArrayElementValue(responseJSON, AA_SequenceNumber, messageNum);
		Map<String,String> message = null;	
		while(sequenceNumber != null) {
			message = new HashMap<String,String>();
			message.put(AA_SequenceNumber, sequenceNumber);
			message.put(AA_MessageCategory, getArrayElementValue(responseJSON, AA_MessageCategory, messageNum));
			message.put(AA_MessageCode, getArrayElementValue(responseJSON, AA_MessageCode, messageNum));
			message.put(AA_MessageDisplayFlag, getArrayElementValue(responseJSON, AA_MessageDisplayFlag, messageNum));
			message.put(AA_MessageGroup1, getArrayElementValue(responseJSON, AA_MessageGroup1, messageNum));
			message.put(AA_MessageGroup2, getArrayElementValue(responseJSON, AA_MessageGroup2, messageNum));
			message.put(AA_MessageId, getArrayElementValue(responseJSON, AA_MessageId, messageNum));
			message.put(AA_MessageHeader, getArrayElementValue(responseJSON, AA_MessageHeader, messageNum));
			message.put(AA_MessageType, getArrayElementValue(responseJSON, AA_MessageType, messageNum));
			messages.add(message);
			messageNum++;
			if(messageCodeList == null) {
				messageCodeList=message.get(AA_MessageCode);
			}
			else {
				messageCodeList=messageCodeList+","+message.get(AA_MessageCode);
			}
			sequenceNumber=getArrayElementValue(responseJSON, AA_SequenceNumber, messageNum);
		}
		setValue(MessageCodeList, messageCodeList);
	}

	private String getArrayElementValue(DocumentContext responseJSON, String elementName, int index) {
		String jsonPath=ACEAPI20ElementsPaths.getACEAPI20ElementsPaths().getResponseElementJSONPath(elementName);
		if(jsonPath != null) {
			if(jsonPath.contains("<SUB_INDEX>")) {
				return mergeListElementsWithPipe(responseJSON, elementName, index);
			}
			//System.out.println(jsonPath);
			return JSONUtilities.getJsonElementValue(responseJSON, 
					jsonPath.replace("<INDEX>", Integer.toString(index)));
		}
		return null;
	}

	private String getArrayElementValue(DocumentContext responseJSON, String elementName, int index, int subIndex) {
		String jsonPath=ACEAPI20ElementsPaths.getACEAPI20ElementsPaths().getResponseElementJSONPath(elementName);
		if(jsonPath != null) {
			//System.out.println(jsonPath);
			return JSONUtilities.getJsonElementValue(responseJSON, 
					jsonPath.replace("<INDEX>", Integer.toString(index)).replace("<SUB_INDEX>", Integer.toString(subIndex)));
		}
		return null;
	}

	private void setValue(DocumentContext responseJSON, String elementName) {
		try {
			setValue(elementName, 
					JSONUtilities.getJsonElementValue(responseJSON, 
							ACEAPI20ElementsPaths.getACEAPI20ElementsPaths().getResponseElementJSONPath(elementName)));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ACEAPI20ResponseData(ACEAPI20DBBase dbACEAPIResponseData) {
		responseMap=new HashMap<String, String>();
		parseDBJSON(dbACEAPIResponseData);
	}
	
	private void parseDBJSON(ACEAPI20DBBase dbACEAPIResponseData) {
		setValue(dbACEAPIResponseData, AlternateAppraisalDecisionExpirationDatetime);
		setValue(dbACEAPIResponseData, AlternateAppraisalDecisionStatusType);
		setValue(dbACEAPIResponseData, AlternateAppraisalEligibilityDecision);
		setValue(dbACEAPIResponseData, MinimumLoanAssessmentFormType);
		setValue(dbACEAPIResponseData, PropertyValuationEffectiveDateTime);
		
		//parseDBAlternateAppraisalReasonList(dbACEAPIResponseData);
		//parseDBMessages(dbACEAPIResponseData);
	}
	
	private void setValue(ACEAPI20DBBase dbACEAPIResponseData, String elementName) {
		try {
			setValue(elementName, dbACEAPIResponseData.getElementValue(elementName) );
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void setValue(String fieldName, String fieldValue) {
		responseMap.put(fieldName, fieldValue);
	}	

	private static ACEAPI20ResponseData currentResponse=null;
	
	public static ACEAPI20ResponseData getCurrentResponse() {
		return currentResponse;
	}
	
	public static void setACEAPI20Response(String responseJSONStr) {
		currentResponse=new ACEAPI20ResponseData(responseJSONStr);
	}
	
	public static void clear() {
		currentResponse=null;
	}

	public Map<String, String> getResponseMap() {
		return responseMap;
	}

	public List<Map<String,String>> getMessages() {
		return messages;
	}

	public static void createACEAPI20ResponseFromDBDoc() {
		ACEAPI20DBBase dbACEAPIResponseData=ACEAPI20DB.getCurrentACEAPIDBDocuments().getACEAPIResponse();
		if(dbACEAPIResponseData == null) {
			TestScenario.writeToScenario("ERROR: DB doc for ACEAPI Response is null");
			return;
		}
		currentResponse=new ACEAPI20ResponseData(dbACEAPIResponseData);
	}
	

}
