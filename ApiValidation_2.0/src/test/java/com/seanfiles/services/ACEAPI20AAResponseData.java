package com.seanfiles.services;

import static com.seanfiles.elements.ACEAPI20Elements.*;
import static com.seanfiles.elements.ACEElements.AA_MessageCode;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.seanfiles.helper.TestConfig;
import com.seanfiles.utils.XMLParserUtil;

public class ACEAPI20AAResponseData  extends BaseData{
	
	private XMLParserUtil AAResponseXML=null;
	Map<String, String> AAData;
	Map<String, String> AADataGFS;
	Map<String, String> GFSAAData;
	List<Map<String,String>> messages = new ArrayList<Map<String,String>>();
	
	private static String MessageWrapperPath="//DecisionContainer/Decision/MessageContainer/MessageWrapper";
	
	public ACEAPI20AAResponseData(String responseXMLStr) {
		AAResponseXML=new XMLParserUtil(responseXMLStr);
		loadAAData();
		loadAADataGFS();
	//	loadGFSAAData();
	//	loadMessages();
	//	loadAllMessages();
		loadAllMessagesToDisplay();
	}
	
	private void loadAllMessagesToDisplay() {
		int messageNum=0;
		String messageCodeList=null;
		Map<String,String> message = null;		
		String sequenceNumber=AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "SequenceNumber");
		while(sequenceNumber != null) {
			String displayFlag=AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "DisplayFlag");
			if(displayFlag.equalsIgnoreCase("YES")) {
				message = new HashMap<String,String>();
				message.put(AA_MessageCategory, AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "MessageCategory"));
				message.put(AA_MessageCode, AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "MessageCode"));
				message.put(AA_MessageDisplayFlag, displayFlag);
				message.put(AA_MessageGroup1, AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "MessageGroup1"));
				message.put(AA_MessageGroup2, AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "MessageGroup2"));
				message.put(AA_MessageId, AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "MessageId"));
				message.put(AA_MessageHeader, AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "MessageHeader"));
				message.put(AA_MessageType, AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "MessageType"));
				message.put(AA_SequenceNumber, sequenceNumber);
				addMessageBySplitPipes(message);
				if(messageCodeList == null) {
					messageCodeList=message.get(AA_MessageCode);
				}
				else {
					messageCodeList=messageCodeList+","+message.get(AA_MessageCode);
				}
			}
			messageNum++;
			sequenceNumber=AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "SequenceNumber");
		}
		AAData.put(MessageCodeList,	messageCodeList.replace("|", ","));
	}
	
	private void addMessageBySplitPipes(Map<String, String> message) {
		String messageCode=message.get(AA_MessageCode);
		if(messageCode != null && messageCode.contains("|")) {
			String messageGroup1=message.get(AA_MessageGroup1);
			String messageGroup2=message.get(AA_MessageGroup2);
			String messageHeader=message.get(AA_MessageHeader);
			String[] messageCodes=messageCode.split("\\|");
			String[] messageGroups1=null;
			String[] messageGroups2=null;
			String[] messageHeaders=null;
			if(messageGroup1 != null) {
				messageGroups1=messageGroup1.split("\\|");
			}
			if(messageGroup2 != null) {
				messageGroups2=messageGroup2.split("\\|");
			}
			if(messageHeader != null) {
				messageHeaders=messageHeader.split("\\|");
			}
			Map<String,String> splitMessage = null;
			for(int i=0;i<messageCodes.length;i++) {
				splitMessage = new HashMap<String,String>();
				splitMessage.put(AA_MessageCategory, message.get(AA_MessageCategory));
				splitMessage.put(AA_MessageDisplayFlag, message.get(AA_MessageDisplayFlag));
				splitMessage.put(AA_MessageId, message.get(AA_MessageId));
				splitMessage.put(AA_MessageType, message.get(AA_MessageType));
				splitMessage.put(AA_SequenceNumber, message.get(AA_SequenceNumber));

				splitMessage.put(AA_MessageCode, messageCodes[i]);
				
				splitMessage.put(AA_MessageGroup1, getArrayElement(messageGroups1, i));
				splitMessage.put(AA_MessageGroup2, getArrayElement(messageGroups2, i));
				splitMessage.put(AA_MessageHeader, getArrayElement(messageHeaders, i));
				
				messages.add(splitMessage);	
			}
		}
		else {
			messages.add(message);		
		}
	}

	private String getArrayElement(String[] strArr, int i) {
		if(strArr != null) {
			if( i < strArr.length ) {
				return strArr[i];
			}
			else {
				return strArr[0];
			}
		}
		return null;
	}

	private void loadAllMessages() {
		int messageNum=0;
		Map<String,String> message = null;		
		String sequenceNumber=AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "SequenceNumber");
		while(sequenceNumber != null) {
			message = new HashMap<String,String>();
			message.put(AA_MessageCategory, AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "MessageCategory"));
			message.put(AA_MessageCode, AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "MessageCode"));
			message.put(AA_MessageDisplayFlag, AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "DisplayFlag"));
			message.put(AA_MessageGroup1, AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "MessageGroup1"));
			message.put(AA_MessageGroup2, AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "MessageGroup2"));
			message.put(AA_MessageId, AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "MessageId"));
			message.put(AA_MessageHeader, AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "MessageHeader"));
			message.put(AA_MessageType, AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "MessageType"));
			message.put(AA_SequenceNumber, sequenceNumber);
			messages.add(message);
			messageNum++;
			sequenceNumber=AAResponseXML.getElementValueByParentXPath(MessageWrapperPath, messageNum, "SequenceNumber");
		}
	}
	
	private void loadMessages() {
		int messageNum=1;
		Map<String,String> message = null;		
		String sequenceNumber=AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Minimum Loan Assessment Form Type']/MessageContainer/MessageWrapper["+messageNum+"]/SequenceNumber/text()");
		while(sequenceNumber != null) {
			message = new HashMap<String,String>();
			message.put(AA_MessageCategory, AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Minimum Loan Assessment Form Type']/MessageContainer/MessageWrapper["+messageNum+"]/MessageCategory/text()"));
			message.put(AA_MessageCode, AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Minimum Loan Assessment Form Type']/MessageContainer/MessageWrapper["+messageNum+"]/MessageCode/text()"));
			message.put(AA_MessageDisplayFlag, AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Minimum Loan Assessment Form Type']/MessageContainer/MessageWrapper["+messageNum+"]/DisplayFlag/text()"));
			message.put(AA_MessageGroup1, AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Minimum Loan Assessment Form Type']/MessageContainer/MessageWrapper["+messageNum+"]/MessageGroup1/text()"));
			message.put(AA_MessageGroup2, AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Minimum Loan Assessment Form Type']/MessageContainer/MessageWrapper["+messageNum+"]/MessageGroup2/text()"));
			message.put(AA_MessageId, AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Minimum Loan Assessment Form Type']/MessageContainer/MessageWrapper["+messageNum+"]/MessageId/text()"));
			message.put(AA_MessageHeader, AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Minimum Loan Assessment Form Type']/MessageContainer/MessageWrapper["+messageNum+"]/MessageHeader/text()"));
			message.put(AA_MessageType, AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Minimum Loan Assessment Form Type']/MessageContainer/MessageWrapper["+messageNum+"]/MessageType/text()"));
			message.put(AA_SequenceNumber, sequenceNumber);
			messages.add(message);
			messageNum++;
			sequenceNumber=AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Minimum Loan Assessment Form Type']/MessageContainer/MessageWrapper["+messageNum+"]/SequenceNumber/text()");
		}
	}
	
	public List<Map<String,String>> getMessages() {
		return messages;
	}

	private void loadGFSAAData() {
//		GFSAAData= new HashMap<String, String>();
//		GFSAAData.put(AlternateAppraisalDecisionEffectiveDatetime, 
//				AAResponseXML.getElementValueByXPath("//Answer/AttrValContainer/AttrValWrapper[AttrKey='PropertyValuationEffectiveDateTime']/AttrVal/text()",1));
//		GFSAAData.put(AlternateAppraisalDecisionExpirationDatetime, 
//				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Calculate Appraisal Alternative Prequalification Eligibility Expiration Date']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
//		GFSAAData.put(AlternateAppraisalDecisionStatusType, 
//				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Appraisal Alternative Data Status Type']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
//		GFSAAData.put(AlternateAppraisalEligibilityDecision, 
//				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Appraisal Alternative Prequalification Eligibility']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
//		GFSAAData.put(MaximumAuthorizedLoanAmount, 
//				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Calculate Appraisal Alternative Prequalification Loan Limit Amount']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
//		
//		normalizeValues(GFSAAData);
	}
	
	private void loadAAData() {
		AAData= new HashMap<String, String>();
		AAData.put(AlternateAppraisalDecisionExpirationDatetime, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Calculate Appraisal Alternative Eligibility Expiration Date']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
		AAData.put(AlternateAppraisalDecisionStatusType, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Appraisal Alternative Data Status Type']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
		AAData.put(AlternateAppraisalEligibilityDecision, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Appraisal Alternative Eligibility']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
		AAData.put(MinimumLoanAssessmentFormType, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Minimum Loan Assessment Form Type']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
		AAData.put(PropertyValuationEffectiveDateTime, 
				AAResponseXML.getElementValueByXPath("//Answer/AttrValContainer/AttrValWrapper[AttrKey='PropertyValuationEffectiveDateTime']/AttrVal/text()"));
		
		AAData.put(AlternateAppraisalReasonList, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Appraisal Alternative Reason List']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));

		//normalizeValues(AAData);
	}

	private void loadAADataGFS() {
		AADataGFS= new HashMap<String, String>();
		AADataGFS.put(AlternateAppraisalDecisionExpirationDatetime, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Calculate Appraisal Alternative Eligibility Expiration Date']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
		AADataGFS.put(AlternateAppraisalDecisionStatusType, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Appraisal Alternative Data Status Type']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
		AADataGFS.put(AlternateAppraisalEligibilityDecision, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Appraisal Alternative Eligibility']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
		AADataGFS.put(AlternateAppraisalDecisionEffectiveDatetime, 
				AAResponseXML.getElementValueByXPath("//Answer/AttrValContainer/AttrValWrapper[AttrKey='PropertyValuationEffectiveDateTime']/AttrVal/text()"));
	}
	
	public Map<String, String> getAAData() {
		return AAData;
	}

	public Map<String, String> getAADataGFS() {
		return AADataGFS;
	}

	public Map<String, String> getGFSAAData() {
		return GFSAAData;
	}

	private static ACEAPI20AAResponseData currentResponse=null;
	
	public static ACEAPI20AAResponseData getCurrentResponse() {
		return currentResponse;
	}
	
	public static void setAAResponse(String responseXMLStr) {
		currentResponse=new ACEAPI20AAResponseData(responseXMLStr);		
	}

	public static void clear() {
		currentResponse=null;
	}

}
