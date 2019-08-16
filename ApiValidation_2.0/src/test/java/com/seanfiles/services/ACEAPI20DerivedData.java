package com.seanfiles.services;

import static com.seanfiles.elements.ACEAPI20Elements.*;

import java.util.HashMap;
import java.util.Map;

public class ACEAPI20DerivedData extends BaseData{
	
	String ACEAPI20RequestID;
	Map<String, String> HVERequestData;
	Map<String, String> EDSRequestData;
	public ACEAPI20DerivedData(String paramACEAPI20RequestID) {
		ACEAPI20RequestID=paramACEAPI20RequestID;
		setHVERequestData();
		setEDSRequestData();
	}
	
	private void setEDSRequestData() {
		EDSRequestData=new HashMap<String, String>();
		
		EDSRequestData.put(EDS_ClientSchemaVersionIdentifier, "1.0.1");
		EDSRequestData.put(EDS_EDSSchemaVersionIdentifier, "1.0.1");
		EDSRequestData.put(EDS_ServiceName, "EDSAppraisalAlternativeAPP");
		EDSRequestData.put(EDS_ServiceRequestIdentifier, "EDS must generate UUID");
		EDSRequestData.put(EDS_ServiceRequestOperationName, "evaluateAppraisalAlternative");
		EDSRequestData.put(EDS_ServiceRequestType, "RuleServiceRequest");
				
		if(ACEAPI20RequestData.getCurrentRequest() == null) {
			return;
		}
		String subscriberIdentifier=ACEAPI20RequestData.getCurrentRequest().getSubscriberIdentifier();
		
		if(subscriberIdentifier.equalsIgnoreCase("LPAv2")) {
	//		EDSRequestData.put(EDS_BusinessDecisionEvaluationType, "AppraisalAlternativeEvaluation");
	//		EDSRequestData.put(EDS_BusinessDecisionResultSourceType, "EnterpriseDecisionService");
			EDSRequestData.put(EDS_LoanRoleType, "SubjectLoan");
			EDSRequestData.put(EDS_RequestorIdentifier, "LoanProductAdvisor");
			EDSRequestData.put(EDS_SubscriberIdentifier, "LPAv2");
			EDSRequestData.put(EDS_SubscriberRequestCorrelationIdentifier, ACEAPI20RequestData.getCurrentRequest().getRequestElementValue(SubscriberRequestCorrelationIdentifier));
		}
		else if(subscriberIdentifier.equalsIgnoreCase("LQAACE")) {
	//		EDSRequestData.put(EDS_BusinessDecisionEvaluationType, "LoanCreditRiskAssessment");
	//		EDSRequestData.put(EDS_BusinessDecisionResultSourceType, "LoanQualityAdvisor");
	//		EDSRequestData.put(EDS_EvaluationResultMessageSourceType, "LoanQualityAdvisor");
			EDSRequestData.put(EDS_LoanRoleType, "SubjectLoan");
			EDSRequestData.put(EDS_RequestorIdentifier, "LoanQualityAdvisor");
			EDSRequestData.put(EDS_SubscriberIdentifier, "LQAACE");
			EDSRequestData.put(EDS_SubscriberRequestCorrelationIdentifier, ACEAPI20RequestData.getCurrentRequest().getRequestElementValue(SubscriberRequestCorrelationIdentifier));
		}
	}

	private void setHVERequestData() {
		HVERequestData=new HashMap<String, String>();
		
		HVERequestData.put(HVS_AccountIdentifier, "lasaceapi_fa");
		HVERequestData.put(HVS_RequestType, "03");
		HVERequestData.put(HVS_ClientSchemaVersionIdentifier, "2.0");
		HVERequestData.put(HVS_RequestorIdentifier, "ACEAPI_FA");
		HVERequestData.put(HVS_SchemaVersionIdentifier, "1.0");
		HVERequestData.put(HVS_ServiceName, "HVSWS");
		HVERequestData.put(HVS_ServiceRequestOperationName, "GetHVSData");
	}
	
	public String getACEAPI20RequestID() {
		return ACEAPI20RequestID;
	}
	
	public Map<String, String> getHVERequestData() {
		return HVERequestData;
	}
	
	public Map<String, String> getEDSRequestData() {
		return EDSRequestData;
	}
	
	private static ACEAPI20DerivedData derivedData=null;
	
	public static ACEAPI20DerivedData getDerivedData() {
		return derivedData;
	}
	
	public static ACEAPI20DerivedData getNewACEAPI20DerivedData(String ACEAPI20RequestID) {
		derivedData=new ACEAPI20DerivedData(ACEAPI20RequestID);
		return derivedData;
	}
	
	public static void clear() {
		derivedData=null;
	}


}
