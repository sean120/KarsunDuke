package com.seanfiles.services;

import static com.seanfiles.elements.ACECheckAPIElements.*;

import java.util.HashMap;
import java.util.Map;

import com.seanfiles.utils.XMLParserUtil;

public class ACECheckAPIAAResponseData  extends BaseData{
	
	private XMLParserUtil AAResponseXML=null;
	Map<String, String> AAData;
	Map<String, String> GFSAAData;
	
	public ACECheckAPIAAResponseData(String responseJSONStr) {
		AAResponseXML=new XMLParserUtil(responseJSONStr);
		loadAAData();
		loadGFSAAData();
	}
	
	private void loadGFSAAData() {
		GFSAAData= new HashMap<String, String>();
		GFSAAData.put(AlternateAppraisalDecisionEffectiveDatetime, 
				AAResponseXML.getElementValueByXPath("//Answer/AttrValContainer/AttrValWrapper[AttrKey='PropertyValuationEffectiveDateTime']/AttrVal/text()"));
		GFSAAData.put(AlternateAppraisalDecisionExpirationDatetime, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Calculate Appraisal Alternative Prequalification Eligibility Expiration Date']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
		GFSAAData.put(AlternateAppraisalDecisionStatusType, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Appraisal Alternative Data Status Type']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
		GFSAAData.put(AlternateAppraisalEligibilityDecision, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Appraisal Alternative Prequalification Eligibility']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
		GFSAAData.put(MaximumAuthorizedLoanAmount, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Calculate Appraisal Alternative Prequalification Loan Limit Amount']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
		
		normalizeValues(GFSAAData);
	}
	
	private void loadAAData() {
		AAData= new HashMap<String, String>();
		AAData.put(AlternateAppraisalDecisionEffectiveDatetime, 
				AAResponseXML.getElementValueByXPath("//Answer/AttrValContainer/AttrValWrapper[AttrKey='PropertyValuationEffectiveDateTime']/AttrVal/text()",1));
		AAData.put(AppraisalWaiverPrescreenExpirationDate, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Calculate Appraisal Alternative Prequalification Eligibility Expiration Date']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
		AAData.put(AlternateAppraisalDecisionStatusType, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Appraisal Alternative Data Status Type']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
		AAData.put(AppraisalWaiverPrescreenEligibilityType, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Determine Appraisal Alternative Prequalification Eligibility']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
		AAData.put(MaximumAuthorizedLoanAmount, 
				AAResponseXML.getElementValueByXPath("//Decision[DecisionName='Calculate Appraisal Alternative Prequalification Loan Limit Amount']/DecisionValueContainer/DecisionValWrapper[DecisionKey='Conclusion']/DecisionVal/text()"));
		
		normalizeValues(AAData);
	}

	public Map<String, String> getAAData() {
		return AAData;
	}

	public Map<String, String> getGFSAAData() {
		return GFSAAData;
	}

	private static ACECheckAPIAAResponseData currentResponse=null;
	
	public static ACECheckAPIAAResponseData getCurrentResponse() {
		return currentResponse;
	}
	
	public static void setAAResponse(String responseJSONStr) {
		currentResponse=new ACECheckAPIAAResponseData(responseJSONStr);		
	}

	public static void clear() {
		currentResponse=null;
	}
}
