package com.seanfiles.utils;

import java.util.HashMap;

public class ElementJsonPaths {
	
	public String returnACE2_0ResponseElementJasonPath(String elementName) {
		HashMap<String, String> ACE2_0JSONPath = new HashMap<String, String>();

		//Old response schema
//		ACE2_0JSONPath.put(("loanIdentifier").toLowerCase(), "$.loanIdentifications.loanIdentification[0].loanIdentifier");
//		ACE2_0JSONPath.put(("loanIdentifierType").toLowerCase(), "$.loanIdentifications.loanIdentification[0].loanIdentifierType");
//		ACE2_0JSONPath.put(("alternateAppraisalDecisionEffectiveDatetime").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalDecisionEffectiveDatetime");
//		ACE2_0JSONPath.put(("alternateAppraisalDecisionExpirationDatetime").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalDecisionExpirationDatetime");
//		ACE2_0JSONPath.put(("alternateAppraisalDecisionStatusType").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalDecisionStatusType");
//		ACE2_0JSONPath.put(("alternateAppraisalEligibilityDecision").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalEligibilityDecision");
//		ACE2_0JSONPath.put(("alternateAppraisalType").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalType");		
//		ACE2_0JSONPath.put(("messageCategory").toLowerCase(), "$.aceDecisionMessageContainer.messages[0].messageCategory");
//		ACE2_0JSONPath.put(("messageCode").toLowerCase(), "$.aceDecisionMessageContainer.messages[0].messageCode");
//		ACE2_0JSONPath.put(("messageGroup1").toLowerCase(), "$.aceDecisionMessageContainer.messages[0].messageGroup1");
//		ACE2_0JSONPath.put(("messageGroup2").toLowerCase(), "$.aceDecisionMessageContainer.messages[0].messageGroup2");
//		ACE2_0JSONPath.put(("messageId").toLowerCase(), "$.aceDecisionMessageContainer.messages[0].messageId");
//		ACE2_0JSONPath.put(("messageHeader").toLowerCase(), "$.aceDecisionMessageContainer.messages[0].messageHeader");
//		ACE2_0JSONPath.put(("messageType").toLowerCase(), "$.aceDecisionMessageContainer.messages[0].messageType");
//		
		//New response schema
		ACE2_0JSONPath.put(("propertyValuationEffectiveDateTime").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalDecisionEffectiveDatetime");
		ACE2_0JSONPath.put(("alternateAppraisalDecisionExpirationDatetime").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalDecisionExpirationDatetime");
		ACE2_0JSONPath.put(("alternateAppraisalDecisionStatusType").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalDecisionStatusType");
		ACE2_0JSONPath.put(("alternateAppraisalEligibilityDecision").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalEligibilityDecision");
		ACE2_0JSONPath.put(("minimumLoanAssessmentFormType").toLowerCase(), "$.aceAssessmentDecision.minimumLoanAssessmentFormType");
	    ACE2_0JSONPath.put(("aceFACTExpirationDatetime").toLowerCase(), "$.aceFACTDecision.aceFACTExpirationDatetime");
	    ACE2_0JSONPath.put(("aceFACTDecisionStatusType").toLowerCase(), "$.aceFACTDecision.aceFACTDecisionStatusType");
	    ACE2_0JSONPath.put(("aceFACTEligibilityDecision").toLowerCase(), "$.aceFACTDecision.aceFACTEligibilityDecision");
	    ACE2_0JSONPath.put(("aceFACTPropertyValuationEffectiveDateTime").toLowerCase(), "$.aceFACTDecision.aceFACTPropertyValuationEffectiveDateTime");
	    ACE2_0JSONPath.put(("listText1").toLowerCase(), "$.aceFACTDecision.aceFACTReasonList.listText");
	    ACE2_0JSONPath.put(("error").toLowerCase(), "$.errors.error.errorResponse.details.error");
	    ACE2_0JSONPath.put(("code").toLowerCase(), "$.errors.error.errorResponse.code");
	    ACE2_0JSONPath.put(("message").toLowerCase(), "$.errors.error.errorResponse.message");
	    ACE2_0JSONPath.put(("loanIdentifier").toLowerCase(), "$.loanIdentifications.loanIdentification[0].loanIdentifier");
	    ACE2_0JSONPath.put(("loanIdentifierType").toLowerCase(), "$.loanIdentifications.loanIdentification[0].loanIdentifierType");
	    ACE2_0JSONPath.put(("listText2").toLowerCase(), "$.aceDecisionMessageContainer.messages.messageCodeList.listText");
	    ACE2_0JSONPath.put(("listText3").toLowerCase(), "$.aceFACTMessageContainer.messages.messageCodeList.listText");
	    ACE2_0JSONPath.put(("listText4").toLowerCase(), "$.aceDecisionMessageContainer.messages.messageGroup1List.listText");
	    ACE2_0JSONPath.put(("listText5").toLowerCase(), "$.aceFACTMessageContainer.messages.messageGroup1List.listText");
	    ACE2_0JSONPath.put(("listText6").toLowerCase(), "$.aceDecisionMessageContainer.messages.messageGroup2List.listText");
	    ACE2_0JSONPath.put(("listText7").toLowerCase(), "$.aceFACTMessageContainer.messages.messageGroup2List.listText");
	    ACE2_0JSONPath.put(("listText8").toLowerCase(), "$.aceDecisionMessageContainer.messages.messageHeaderList.listText");
	    ACE2_0JSONPath.put(("listText9").toLowerCase(), "$.aceFACTMessageContainer.messages.messageHeaderList.listText");
	    ACE2_0JSONPath.put(("messageCategory1").toLowerCase(), "$.aceDecisionMessageContainer.messages[0].messageCategory");
	    ACE2_0JSONPath.put(("messageDisplayFlag1").toLowerCase(), "$.aceDecisionMessageContainer.messages.messageDisplayFlag");
	    ACE2_0JSONPath.put(("messageId1").toLowerCase(), "$.aceDecisionMessageContainer.messages[0].messageId");
	    ACE2_0JSONPath.put(("messageType1").toLowerCase(), "$.aceDecisionMessageContainer.messages[0].messageType");
	    ACE2_0JSONPath.put(("sequenceNumber1").toLowerCase(), "$.aceDecisionMessageContainer.messages.sequenceNumber");
	    ACE2_0JSONPath.put(("messageCategory2").toLowerCase(), "$.aceFACTMessageContainer.messages.messageCategory");
	    ACE2_0JSONPath.put(("messageDisplayFlag2").toLowerCase(), "$.aceFACTMessageContainer.messages.messageDisplayFlag");
	    ACE2_0JSONPath.put(("messageId2").toLowerCase(), "$.aceFACTMessageContainer.messages.messageId");
	    ACE2_0JSONPath.put(("messageType2").toLowerCase(), "$.aceFACTMessageContainer.messages.messageType");
	    ACE2_0JSONPath.put(("sequenceNumber2").toLowerCase(), "$.aceFACTMessageContainer.messages.sequenceNumber");


		String jsonPath = ACE2_0JSONPath.get(elementName.toLowerCase());
		return jsonPath;
		
		
	}
	
	public String returnIVANResponseElementJasonPath(String elementName) {
		HashMap<String, String> IVANJSONPath = new HashMap<String, String>();

		
		
		// BCVS Response JSON
		// Json Elements----------------------------------------------------------
		IVANJSONPath.put(("version").toLowerCase(), "$.HEADER.version");
		IVANJSONPath.put(("encoding").toLowerCase(), "$.HEADER.encoding");
		IVANJSONPath.put(("SubmissionDate").toLowerCase(), "$.HEADER.SubmissionDate");
		IVANJSONPath.put(("ExpirationDate").toLowerCase(), "$.HEADER.ExpirationDate");
		IVANJSONPath.put(("AssetSummaryAssetHolderFullName").toLowerCase(), "$.ASSET_SUMMAIRES[0].ASSET_SUMMARY[0].AssetHolderFullName");
		IVANJSONPath.put(("AssetSummaryAssetAccountIdentifier").toLowerCase(), "$.ASSET_SUMMAIRES[0].ASSET_SUMMARY[0].AssetAccountIdentifier");
		IVANJSONPath.put(("AssetSummaryAssetType").toLowerCase(), "$.ASSET_SUMMAIRES[0].ASSET_SUMMARY[0].AssetType");
		IVANJSONPath.put(("AssetSummaryAssetAsOfDate").toLowerCase(), "$.ASSET_SUMMAIRES[0].ASSET_SUMMARY[0].AssetAsOfDate");
		IVANJSONPath.put(("AssetSummaryAccountOwnerNames").toLowerCase(), "$.ASSET_SUMMAIRES[0].ASSET_SUMMARY[0].AccountOwnerNames");
		IVANJSONPath.put(("AssetSummaryMatchedBorrowerNames").toLowerCase(), "$.ASSET_SUMMAIRES[0].ASSET_SUMMARY[0].MatchedBorrowerNames");
		IVANJSONPath.put(("AssetSummaryAssetAvailableBalanceAmount").toLowerCase(), "$.ASSET_SUMMAIRES[0].ASSET_SUMMARY[0].AssetAvailableBalanceAmount");
		IVANJSONPath.put(("AssetSummaryEligibleAssetBalance").toLowerCase(), "$.ASSET_SUMMAIRES[0].ASSET_SUMMARY[0].EligibleAssetBalance");
		IVANJSONPath.put(("LargeDepositAssetTransactionDate").toLowerCase(), "$.LARGE_DEPOSITS[0].LARGE_DEPOSIT[0].AssetTransactionDate");
		IVANJSONPath.put(("LargeDepositAssetTransactionDescription").toLowerCase(), "$.LARGE_DEPOSITS[0].LARGE_DEPOSIT[0].AssetTransactionDescription");
		IVANJSONPath.put(("LargeDepositAssetTransactionAmount").toLowerCase(), "$.LARGE_DEPOSITS[0].LARGE_DEPOSIT[0].AssetTransactionAmount");
		IVANJSONPath.put(("LargeDepositAssetHolderFullName").toLowerCase(), "$.LARGE_DEPOSITS[0].LARGE_DEPOSIT[0].AssetHolderFullName");
		IVANJSONPath.put(("LargeDepositAssetAccountIdentifier").toLowerCase(), "$.LARGE_DEPOSITS[0].LARGE_DEPOSIT[0].AssetAccountIdentifier");
		IVANJSONPath.put(("BorrowerIdentifier").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY");
		IVANJSONPath.put(("BorrowerXlink").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY");
		IVANJSONPath.put(("BorrowerFullName").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY");
		IVANJSONPath.put(("AKABorrowerFullName").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY");
		IVANJSONPath.put(("SubmittedIncomeSource").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY");
		IVANJSONPath.put(("SubmittedAKAIncomeSource").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY");
		IVANJSONPath.put(("IncomeSourceMatch").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY");
		IVANJSONPath.put(("IncomeSource").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY");
		IVANJSONPath.put(("DataProvider").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY");
		IVANJSONPath.put(("IncomeXlink").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY");
		IVANJSONPath.put(("IncomeType").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY");
		IVANJSONPath.put(("SubmittedIncomeAmount").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY");
		IVANJSONPath.put(("FMDerivedIncomeAmount").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY");
		
		IVANJSONPath.put(("BorrowerDetailBorrowerIdentifier").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY[0].BORROWER_DETAIL.BorrowerIdentifier");
		IVANJSONPath.put(("BorrowerDetailBorrowerFullName").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY[0].BORROWER_DETAIL.BorrowerFullName");
		IVANJSONPath.put(("BorrowerDetailAKABorrowerFullName").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY[0].BORROWER_DETAIL.AKABorrowerFullName");
		IVANJSONPath.put(("IncomeDetailEmploymentXLink").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY[0].INCOME_DETAIL[0].EmploymentXLink");
		IVANJSONPath.put(("IncomeDetailSubmittedIncomeSource").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY[0].INCOME_DETAIL[0].SubmittedIncomeSource");
		IVANJSONPath.put(("IncomeDetailSubmittedAKAIncomeSource").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY[0].INCOME_DETAIL[0].SubmittedAKAIncomeSource");
		IVANJSONPath.put(("IncomeDetailIncomeSourceMatch").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY[0].INCOME_DETAIL[0].IncomeSourceMatch");
		IVANJSONPath.put(("IncomeDetailIncomeSource").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY[0].INCOME_DETAIL[0].IncomeSource");
		IVANJSONPath.put(("IncomeDetailDataProvider").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY[0].INCOME_DETAIL[0].DataProvider");
		IVANJSONPath.put(("IncomeTypesIncomeXLink").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY[0].INCOME_DETAIL[0].INCOME_TYPES[0].IncomeXLink");
		IVANJSONPath.put(("IncomeTypesIncomeType").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY[0].INCOME_DETAIL[0].INCOME_TYPES[0].IncomeType");
		IVANJSONPath.put(("IncomeTypesSubmittedIncomeAmount").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY[0].INCOME_DETAIL[0].INCOME_TYPES[0].SubmittedIncomeAmount");
		IVANJSONPath.put(("IncomeTypesFMDerivedIncomeAmount").toLowerCase(), "$.INCOME_SUMMARIES.INCOME_SUMMARY[0].INCOME_DETAIL[0].INCOME_TYPES[0].FMDerivedIncomeAmount");
				
		
		
		
		
		IVANJSONPath.put(("CreditTransactionEmploymentXlink").toLowerCase(), "$.CREDIT_TRANSACTIONS[0].CREDIT_TRANSACTION[0].EmploymentXlink");
		IVANJSONPath.put(("CreditTransactionAssetTransactionPaidByName").toLowerCase(), "$.CREDIT_TRANSACTIONS[0].CREDIT_TRANSACTION[0].AssetTransactionPaidByName");
		IVANJSONPath.put(("CreditTransactionAssetTransactionDescription").toLowerCase(), "$.CREDIT_TRANSACTIONS[0].CREDIT_TRANSACTION[0].AssetTransactionDescription");
		IVANJSONPath.put(("CreditTransactionFinancialInstitutionName").toLowerCase(), "$.CREDIT_TRANSACTIONS[0].CREDIT_TRANSACTION[0].FinancialInstitutionName");
		IVANJSONPath.put(("CreditTransactionAssetAccountIdentifier").toLowerCase(), "$.CREDIT_TRANSACTIONS[0].CREDIT_TRANSACTION[0].AssetAccountIdentifier");
		IVANJSONPath.put(("CreditTransactionAssetTransactionPostDate").toLowerCase(), "$.CREDIT_TRANSACTIONS[0].CREDIT_TRANSACTION[0].AssetTransactionPostDate");
		IVANJSONPath.put(("CreditTransactionAssetTransactionAmount").toLowerCase(), "$.CREDIT_TRANSACTIONS[0].CREDIT_TRANSACTION[0].AssetTransactionAmount");
		IVANJSONPath.put(("CreditTransactionIncomeSourceIdentified").toLowerCase(), "$.CREDIT_TRANSACTIONS[0].CREDIT_TRANSACTION[0].AssetTransactionAmount");
		IVANJSONPath.put(("EmbeddedContentXML").toLowerCase(), "$.FOREIGN_OBJECTS.FOREIGN_OBJECT.EmbeddedContentXML");
		IVANJSONPath.put(("CharacterEncodingSetType").toLowerCase(), "$.FOREIGN_OBJECTS.FOREIGN_OBJECT.CharacterEncodingSetType");
		IVANJSONPath.put(("MIMETypeIdentifier").toLowerCase(), "$.FOREIGN_OBJECTS.FOREIGN_OBJECT.MIMETypeIdentifier");
		IVANJSONPath.put(("ObjectEncodingType").toLowerCase(), "$.FOREIGN_OBJECTS.FOREIGN_OBJECT.ObjectEncodingType");
		IVANJSONPath.put(("StatusCode").toLowerCase(), "$.STATUSES.STATUS[0].StatusCode");
		IVANJSONPath.put(("StatusDescription").toLowerCase(), "$.STATUSES.STATUS[0].StatusDescription");
		IVANJSONPath.put(("MESSAGES").toLowerCase(), "$.MESSAGES.MESSAGE");
		IVANJSONPath.put(("MessageCode").toLowerCase(), "$needPath");	
		IVANJSONPath.put(("MessageDescription").toLowerCase(), "$needPath");
		IVANJSONPath.put(("MESSAGE").toLowerCase(), "$needPath");
		IVANJSONPath.put(("VendorOrderIdentifier").toLowerCase(), "$.SERVICE_PRODUCT_FULFILLMENT.SERVICE_PRODUCT_FULFILLMENT_DETAIL.VendorOrderIdentifier");
		IVANJSONPath.put(("ReportingInformationIdentifier").toLowerCase(), "$.REPORTING_INFORMATION.ReportingInformationIdentifier");
		
		String jsonPath = IVANJSONPath.get(elementName.toLowerCase());
		return jsonPath;
		
		
	}

	public String returnResponseElementJasonPath(String elementName) {
		HashMap<String, String> aceJsonPath = new HashMap<String, String>();

		// HVE Response JSON
		// Elements----------------------------------------------------------
		aceJsonPath.put(("AlternateLoanIdentifier").toLowerCase(),
				"$.HVSResponse.HVSResponseContainers.HVSResponseContainer.AlternativeLoanIdentifications.AlternativeLoanIdentification.AlternateLoanIdentifier");
		aceJsonPath.put(("LoanIdentifier").toLowerCase(),
				"$.HVSResponse.HVSResponseContainers.HVSResponseContainer.AlternativeLoanIdentifications.AlternativeLoanIdentification.LoanIdentifier");
		aceJsonPath.put(("LoanIdentifierType").toLowerCase(),
				"$.HVSResponse.HVSResponseContainers/HVSResponseContainer/AlternativeLoanIdentifications/AlternativeLoanIdentification/LoanIdentifierType");
		aceJsonPath.put(("HVSTransactionIdentifier").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSTransactionIdentifier");
		aceJsonPath.put(("BillingType").toLowerCase(), "$.HVSResponseContainers/HVSResponseContainer/BillingType");
		aceJsonPath.put(("FDRVersion").toLowerCase(), "$.HVSResponseContainers/HVSResponseContainer/FDRVersion");
		aceJsonPath.put(("AddressLineText").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/AddressLineText");
		aceJsonPath.put(("CityName").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/CityName");
		aceJsonPath.put(("FIPSStateAlphaCode").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/FIPSStateAlphaCode");
		aceJsonPath.put(("PostalCode").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/PostalCode");
		aceJsonPath.put(("ZIPPlusFourCode").toLowerCase(),
				"$/HVSResponse/HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/ZIPPlusFourCode");
		aceJsonPath.put(("AVMConfidenceScoreValue").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/AVMConfidenceScoreValue");
		aceJsonPath.put(("AVMHighValueRangeAmount").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/AVMHighValueRangeAmount");
		aceJsonPath.put(("AVMLowValueRangeAmount").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/AVMLowValueRangeAmount");
		aceJsonPath.put(("AVMModelNameType").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/AVMModelNameType");
		aceJsonPath.put(("AVMValueAmount").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/AVMValueAmount");
		aceJsonPath.put(("HVEStandardDeviationNumber").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/HVEStandardDeviationNumber");
		aceJsonPath.put(("ActiveMLSListingFlag").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/ActiveMLSListingIndicator");
		aceJsonPath.put(("ArmsLengthRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/ARMsLengthContractIndicator");
		aceJsonPath.put(("CompletedAslsRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/AppraisalCompletionAsIsIndicator");
		aceJsonPath.put(("ConditionRule").toLowerCase(), "$.Update.");
		aceJsonPath.put(("DaysOnMarketRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/DaysOnMarketIndicator");
		aceJsonPath.put(("DistressedRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/DistressedIndicator");
		aceJsonPath.put(("HighestBestUseRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/LegalityIndicator");
		aceJsonPath.put(("LegalityRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/LegalityIndicator");
		aceJsonPath.put(("MissingDataFlag").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/MissingDataIndicator");
		aceJsonPath.put(("MultipleListingRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/MultipleListingIndicator");
		aceJsonPath.put(("NeighborhoodConformity").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/NeighborhoodConformityIndicator");
		aceJsonPath.put(("PhysicalDeficiencyRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PhysicalDeficiencyIndicator");
		aceJsonPath.put(("PropertyAge").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PropertyAgeCount");
		aceJsonPath.put(("PublicRecordExistenceFlag").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PublicRecordExistenceIndicator");
		aceJsonPath.put(("PublicRecordQualityRating").toLowerCase(), "$.Update.");
		aceJsonPath.put(("QualityAgeRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/QualityAgeIndicator");
		aceJsonPath.put(("QualityRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/QualityIndicator");
		aceJsonPath.put(("MessageCode").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/Messages/Message/MessageCode");
		aceJsonPath.put(("MessageText").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/Messages/Message/MessageText");
		aceJsonPath.put(("MessageType").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/Messages/Message/MessageType");

		// EDS Response JSON
		// Elements----------------------------------------------------------
		aceJsonPath.put(("AttrKey").toLowerCase(), "$.AttrValContainer.AttrValWrapper.AttrKey");
		aceJsonPath.put(("AttrVal").toLowerCase(), "$.AttrValContainer.AttrValWrapper.AttrVal");
		aceJsonPath.put(("AttrType").toLowerCase(), "$.AttrValContainer.AttrValWrapper.AttrType");

		aceJsonPath.put(("RowId").toLowerCase(), "$.DecisionContainer.Decision.RowIdContainer.RowIdWrapper.RowId");
		aceJsonPath.put(("RuleFamilyName").toLowerCase(),
				"$.DecisionContainer.Decision.RowIdContainer.RowIdWrapper.RuleFamilyName");
		aceJsonPath.put(("SequenceNumber").toLowerCase(),
				"$.DecisionContainer.Decision.RowIdContainer.RowIdWrapper.SequenceNumber");
		aceJsonPath.put(("Conclusion").toLowerCase(),
				"$.DecisionContainer.Decision.RowIdContainer.RowIdWrapper.Conclusion");

		aceJsonPath.put(("DecisionName").toLowerCase(), "$.DecisionContainer.Decision.DecisionName");
		aceJsonPath.put(("SLEName").toLowerCase(), "$.DecisionContainer.Decision.SLEName");

		aceJsonPath.put(("DecisionKey").toLowerCase(),
				"$.DecisionContainer.Decision.DecisionValueContainer.DecisionValWrapper.DecisionKey");
		aceJsonPath.put(("DecisionVal").toLowerCase(),
				"$.DecisionContainer.Decision.DecisionValueContainer.DecisionValWrapper.DecisionVal");
		aceJsonPath.put(("DecisionType").toLowerCase(),
				"$.DecisionContainer.Decision.DecisionValueContainer.DecisionValWrapper.DecisionType");

		aceJsonPath.put(("CriticalMessage").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.Message");
		aceJsonPath.put(("CriticalRuleFamilyName").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.RuleFamilyName");
		aceJsonPath.put(("CriticalRowId").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.RowId");
		aceJsonPath.put(("CriticalDisplayFlag").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.DisplayFlag");
		aceJsonPath.put(("CriticalSequenceNumber").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.SequenceNumber");
		aceJsonPath.put(("CriticalMessageCategory").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageCategory");
		aceJsonPath.put(("CriticalMessageCode").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageCode");
		aceJsonPath.put(("CriticalMessageType").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageType");
		aceJsonPath.put(("CriticalMessageHeader").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageHeader");
		aceJsonPath.put(("CriticalMessageId").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageId");
		aceJsonPath.put(("CriticalMessageGroup1").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageGroup1");
		aceJsonPath.put(("CriticalMessageGroup2").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageGroup2");
		aceJsonPath.put(("CriticalMessageGroup3").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageGroup3");

		aceJsonPath.put(("InfoMessage").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.Message");
		aceJsonPath.put(("InfoRuleFamilyName").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.RuleFamilyName");
		aceJsonPath.put(("InfoRowId").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.RowId");
		aceJsonPath.put(("InfoDisplayFlag").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.DisplayFlag");
		aceJsonPath.put(("InfoSequenceNumber").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.SequenceNumber");
		aceJsonPath.put(("InfoMessageCategory").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageCategory");
		aceJsonPath.put(("InfoMessageCode").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageCode");
		aceJsonPath.put(("InfoMessageType").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageType");
		aceJsonPath.put(("InfoMessageHeader").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageHeader");
		aceJsonPath.put(("InfoMessageId").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageId");
		aceJsonPath.put(("InfoMessageGroup1").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageGroup1");
		aceJsonPath.put(("InfoMessageGroup2").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageGroup2");
		aceJsonPath.put(("InfoMessageGroup3").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageGroup3");

		aceJsonPath.put(("MessageMessage").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.Message");
		aceJsonPath.put(("MessageRuleFamilyName").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.RuleFamilyName");
		aceJsonPath.put(("MessageRowId").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.RowId");
		aceJsonPath.put(("MessageDisplayFlag").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.DisplayFlag");
		aceJsonPath.put(("MessageSequenceNumber").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.SequenceNumber");
		aceJsonPath.put(("MessageMessageCategory").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageCategory");
		aceJsonPath.put(("MessageMessageCode").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageCode");
		aceJsonPath.put(("MessageMessageType").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageType");
		aceJsonPath.put(("MessageMessageHeader").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageHeader");
		aceJsonPath.put(("MessageMessageId").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageId");
		aceJsonPath.put(("MessageMessageGroup1").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageGroup1");
		aceJsonPath.put(("MessageMessageGroup2").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageGroup2");
		aceJsonPath.put(("MessageMessageGroup3").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageGroup3");

		// ACE Response JSON
		// Elements----------------------------------------------------------
		aceJsonPath.put(("appraisalWaiverPrescreenEligibilityType").toLowerCase(),
				"$.deals[0].loanInformation.appraisalWaiverPrescreenEligibilityType");
		aceJsonPath.put(("appraisalWaiverPrescreenExpirationDate").toLowerCase(),
				"$.deals[0].loanInformation.appraisalWaiverPrescreenExpirationDate");
		aceJsonPath.put(("maximumAuthorizedLoanAmount").toLowerCase(), "$.deals[0].loanInformation.maximumAuthorizedLoanAmount");

		aceJsonPath.put(("maximumAuthorizedLoanAmountNotApplicable").toLowerCase(), "$.maximumAuthorizedLoanAmount");

		aceJsonPath.put(("partyRoleType").toLowerCase(), "$.party.partyRoleType");
		aceJsonPath.put(("partyRoleIdentifier").toLowerCase(), "$.party.partyRoleIdentifier");
		aceJsonPath.put(("stateCode").toLowerCase(), "$.deals[0].address.stateCode");
		aceJsonPath.put(("aceDecision").toLowerCase(), "$[0].aceDecision");
		aceJsonPath.put(("decisionExpirationDateTime").toLowerCase(), "$[0].decisionExpirationDateTime");
		aceJsonPath.put(("appraisalWaiverPrescreenExpirationDate").toLowerCase(), "$.deals[0].loanInformation.appraisalWaiverPrescreenExpirationDate");		
		aceJsonPath.put(("addressLineText").toLowerCase(), "$.deals[0].address.addressLineText");
		aceJsonPath.put(("cityName").toLowerCase(), "$.deals[0].address.cityName");
		aceJsonPath.put(("postalCode").toLowerCase(), "$.deals[0].address.postalCode");
		aceJsonPath.put(("fipsstateAlphaCode").toLowerCase(), "$[0].address.fipsstateAlphaCode");
		aceJsonPath.put(("messageCode1").toLowerCase(), "$.deals[0].messages[0].messageCode");
		aceJsonPath.put(("messageText").toLowerCase(), "$.deals[0].messages[0].messageText");
		aceJsonPath.put(("messageCodeNotApplicable").toLowerCase(), "$.messages[0].messageCode");
		aceJsonPath.put(("messageTextNotApplicable").toLowerCase(), "$.messages[0].messageText");
		aceJsonPath.put(("messageCode2").toLowerCase(), "$[0].messages[1].messageCode");
		aceJsonPath.put(("messageDescription2").toLowerCase(), "$[0].messages[1].messageDescription");
		aceJsonPath.put(("propertyValueAmount").toLowerCase(), "$[0].propertyValueAmount");
		aceJsonPath.put(("address").toLowerCase(), "$.deals[0].address");
		aceJsonPath.put(("addressNew").toLowerCase(), "$.address");
		aceJsonPath.put(("maximumAuthorizedLoanAmountNull").toLowerCase(), "$.deals[0].loanInformation.maximumAuthorizedLoanAmount");
		aceJsonPath.put(("requestTransactionIdentifier").toLowerCase(), "$.deals[0].requestTransactionIdentifier");
		aceJsonPath.put(("requestBatchIdentifier").toLowerCase(), "$.requestBatchIdentifier");
		
		
		String messageText = aceJsonPath.get(elementName.toLowerCase());
		return messageText;
	}

	public String returnMongoElementJasonPath(String elementName) {
		HashMap<String, String> aceJsonPath = new HashMap<String, String>();

		// HVE Response JSON
		// Elements----------------------------------------------------------

		aceJsonPath.put(("AlternateLoanIdentifier").toLowerCase(),
				"$.HVSResponse.HVSResponseContainers.HVSResponseContainer.AlternativeLoanIdentifications.AlternativeLoanIdentification.AlternateLoanIdentifier");
		aceJsonPath.put(("LoanIdentifier").toLowerCase(),
				"$.HVSResponse.HVSResponseContainers.HVSResponseContainer.AlternativeLoanIdentifications.AlternativeLoanIdentification.LoanIdentifier");
		aceJsonPath.put(("LoanIdentifierType").toLowerCase(),
				"$.HVSResponse.HVSResponseContainers/HVSResponseContainer/AlternativeLoanIdentifications/AlternativeLoanIdentification/LoanIdentifierType");
		aceJsonPath.put(("HVSTransactionIdentifier").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSTransactionIdentifier");
		aceJsonPath.put(("BillingType").toLowerCase(), "$.HVSResponseContainers/HVSResponseContainer/BillingType");
		aceJsonPath.put(("FDRVersion").toLowerCase(), "$.HVSResponseContainers/HVSResponseContainer/FDRVersion");
		aceJsonPath.put(("AddressLineText").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/AddressLineText");
		aceJsonPath.put(("CityName").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/CityName");
		aceJsonPath.put(("FIPSStateAlphaCode").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/FIPSStateAlphaCode");
		aceJsonPath.put(("PostalCode").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/PostalCode");
		aceJsonPath.put(("ZIPPlusFourCode").toLowerCase(),
				"$/HVSResponse/HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/ZIPPlusFourCode");
		aceJsonPath.put(("AVMConfidenceScoreValue").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/AVMConfidenceScoreValue");
		aceJsonPath.put(("AVMHighValueRangeAmount").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/AVMHighValueRangeAmount");
		aceJsonPath.put(("AVMLowValueRangeAmount").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/AVMLowValueRangeAmount");
		aceJsonPath.put(("AVMModelNameType").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/AVMModelNameType");
		aceJsonPath.put(("AVMValueAmount").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/AVMValueAmount");
		aceJsonPath.put(("HVEStandardDeviationNumber").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/HVEStandardDeviationNumber");
		aceJsonPath.put(("ActiveMLSListingFlag").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/ActiveMLSListingIndicator");
		aceJsonPath.put(("ArmsLengthRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/ARMsLengthContractIndicator");
		aceJsonPath.put(("CompletedAslsRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/AppraisalCompletionAsIsIndicator");
		aceJsonPath.put(("ConditionRule").toLowerCase(), "$.Update.");
		aceJsonPath.put(("DaysOnMarketRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/DaysOnMarketIndicator");
		aceJsonPath.put(("DistressedRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/DistressedIndicator");
		aceJsonPath.put(("HighestBestUseRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/LegalityIndicator");
		aceJsonPath.put(("LegalityRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/LegalityIndicator");
		aceJsonPath.put(("MissingDataFlag").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/MissingDataIndicator");
		aceJsonPath.put(("MultipleListingRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/MultipleListingIndicator");
		aceJsonPath.put(("NeighborhoodConformity").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/NeighborhoodConformityIndicator");
		aceJsonPath.put(("PhysicalDeficiencyRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PhysicalDeficiencyIndicator");
		aceJsonPath.put(("PropertyAge").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PropertyAgeCount");
		aceJsonPath.put(("PublicRecordExistenceFlag").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PublicRecordExistenceIndicator");
		aceJsonPath.put(("PublicRecordQualityRating").toLowerCase(), "$.Update.");
		aceJsonPath.put(("QualityAgeRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/QualityAgeIndicator");
		aceJsonPath.put(("QualityRule").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/QualityIndicator");
		aceJsonPath.put(("MessageCode").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/Messages/Message/MessageCode");
		aceJsonPath.put(("MessageText").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/Messages/Message/MessageText");
		aceJsonPath.put(("MessageType").toLowerCase(),
				"$.HVSResponseContainers/HVSResponseContainer/Messages/Message/MessageType");

		// EDS Response JSON
		// Elements----------------------------------------------------------
		aceJsonPath.put(("AttrKey").toLowerCase(), "$.AttrValContainer.AttrValWrapper.AttrKey");
		aceJsonPath.put(("AttrVal").toLowerCase(), "$.AttrValContainer.AttrValWrapper.AttrVal");
		aceJsonPath.put(("AttrType").toLowerCase(), "$.AttrValContainer.AttrValWrapper.AttrType");

		aceJsonPath.put(("RowId").toLowerCase(), "$.DecisionContainer.Decision.RowIdContainer.RowIdWrapper.RowId");
		aceJsonPath.put(("RuleFamilyName").toLowerCase(),
				"$.DecisionContainer.Decision.RowIdContainer.RowIdWrapper.RuleFamilyName");
		aceJsonPath.put(("SequenceNumber").toLowerCase(),
				"$.DecisionContainer.Decision.RowIdContainer.RowIdWrapper.SequenceNumber");
		aceJsonPath.put(("Conclusion").toLowerCase(),
				"$.DecisionContainer.Decision.RowIdContainer.RowIdWrapper.Conclusion");

		aceJsonPath.put(("DecisionName").toLowerCase(), "$.DecisionContainer.Decision.DecisionName");
		aceJsonPath.put(("SLEName").toLowerCase(), "$.DecisionContainer.Decision.SLEName");

		aceJsonPath.put(("DecisionKey").toLowerCase(),
				"$.DecisionContainer.Decision.DecisionValueContainer.DecisionValWrapper.DecisionKey");
		aceJsonPath.put(("DecisionVal").toLowerCase(),
				"$.DecisionContainer.Decision.DecisionValueContainer.DecisionValWrapper.DecisionVal");
		aceJsonPath.put(("DecisionType").toLowerCase(),
				"$.DecisionContainer.Decision.DecisionValueContainer.DecisionValWrapper.DecisionType");

		aceJsonPath.put(("CriticalMessage").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.Message");
		aceJsonPath.put(("CriticalRuleFamilyName").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.RuleFamilyName");
		aceJsonPath.put(("CriticalRowId").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.RowId");
		aceJsonPath.put(("CriticalDisplayFlag").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.DisplayFlag");
		aceJsonPath.put(("CriticalSequenceNumber").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.SequenceNumber");
		aceJsonPath.put(("CriticalMessageCategory").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageCategory");
		aceJsonPath.put(("CriticalMessageCode").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageCode");
		aceJsonPath.put(("CriticalMessageType").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageType");
		aceJsonPath.put(("CriticalMessageHeader").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageHeader");
		aceJsonPath.put(("CriticalMessageId").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageId");
		aceJsonPath.put(("CriticalMessageGroup1").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageGroup1");
		aceJsonPath.put(("CriticalMessageGroup2").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageGroup2");
		aceJsonPath.put(("CriticalMessageGroup3").toLowerCase(),
				"$.DecisionContainer.Decision.CriticalMessageContainer.MessageWrapper.MessageGroup3");

		aceJsonPath.put(("InfoMessage").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.Message");
		aceJsonPath.put(("InfoRuleFamilyName").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.RuleFamilyName");
		aceJsonPath.put(("InfoRowId").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.RowId");
		aceJsonPath.put(("InfoDisplayFlag").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.DisplayFlag");
		aceJsonPath.put(("InfoSequenceNumber").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.SequenceNumber");
		aceJsonPath.put(("InfoMessageCategory").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageCategory");
		aceJsonPath.put(("InfoMessageCode").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageCode");
		aceJsonPath.put(("InfoMessageType").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageType");
		aceJsonPath.put(("InfoMessageHeader").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageHeader");
		aceJsonPath.put(("InfoMessageId").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageId");
		aceJsonPath.put(("InfoMessageGroup1").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageGroup1");
		aceJsonPath.put(("InfoMessageGroup2").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageGroup2");
		aceJsonPath.put(("InfoMessageGroup3").toLowerCase(),
				"$.DecisionContainer.Decision.InfoMessageContainer.MessageWrapper.MessageGroup3");

		aceJsonPath.put(("MessageMessage").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.Message");
		aceJsonPath.put(("MessageRuleFamilyName").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.RuleFamilyName");
		aceJsonPath.put(("MessageRowId").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.RowId");
		aceJsonPath.put(("MessageDisplayFlag").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.DisplayFlag");
		aceJsonPath.put(("MessageSequenceNumber").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.SequenceNumber");
		aceJsonPath.put(("MessageMessageCategory").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageCategory");
		aceJsonPath.put(("MessageMessageCode").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageCode");
		aceJsonPath.put(("MessageMessageType").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageType");
		aceJsonPath.put(("MessageMessageHeader").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageHeader");
		aceJsonPath.put(("MessageMessageId").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageId");
		aceJsonPath.put(("MessageMessageGroup1").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageGroup1");
		aceJsonPath.put(("MessageMessageGroup2").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageGroup2");
		aceJsonPath.put(("MessageMessageGroup3").toLowerCase(),
				"$.DecisionContainer.Decision.MessageContainer.MessageWrapper.MessageGroup3");

		// ACE Response JSON
		// Elements----------------------------------------------------------
		aceJsonPath.put(("aceDecision").toLowerCase(), "$.aceDecision");
		aceJsonPath.put(("decisionExpirationDateTime").toLowerCase(), "$.decisionExpirationDateTime");
		aceJsonPath.put(("addressLineText").toLowerCase(), "$.address.addressLineText");
		aceJsonPath.put(("cityName").toLowerCase(), "$.address.cityName");
		aceJsonPath.put(("postalCode").toLowerCase(), "$.address.postalCode");
		aceJsonPath.put(("fipsstateAlphaCode").toLowerCase(), "$.address.fipsstateAlphaCode");
		aceJsonPath.put(("messageCode1").toLowerCase(), "$.messages[0].messageCode");
		aceJsonPath.put(("messageDescription1").toLowerCase(), "$.messages[0].messageDescription");
		aceJsonPath.put(("messageCode2").toLowerCase(), "$.messages[1].messageCode");
		aceJsonPath.put(("messageDescription2").toLowerCase(), "$.messages[1].messageDescription");

		String messageText = aceJsonPath.get(elementName.toLowerCase());
		return messageText;
	}

	// public String returnACEElementJasonPath(String elementName){
	// HashMap<String, String> aceJsonPath = new HashMap<String, String>();
	//
	//// .XML input
	// values----------------------------------------------------------------------------------------------------------------------------------------------------
	// aceJsonPath.put(("aceDecision").toLowerCase(), "$.aceDecision");
	// aceJsonPath.put(("decisionExpirationDateTime").toLowerCase(),
	// "$.decisionExpirationDateTime");
	// aceJsonPath.put(("addressLineText").toLowerCase(),
	// "$.address.addressLineText");
	// aceJsonPath.put(("cityName").toLowerCase(), "$.address.cityName");
	// aceJsonPath.put(("postalCode").toLowerCase(), "$.address.postalCode");
	// aceJsonPath.put(("fipsstateAlphaCode").toLowerCase(),
	// "$.address.fipsstateAlphaCode");
	// aceJsonPath.put(("messageCode1").toLowerCase(),
	// "$.messages[0].messageCode");
	// aceJsonPath.put(("messageDescription1").toLowerCase(),
	// "$.messages[0].messageDescription");
	// aceJsonPath.put(("messageCode2").toLowerCase(),
	// "$.messages[1].messageCode");
	// aceJsonPath.put(("messageDescription2").toLowerCase(),
	// "$.messages[1].messageDescription");
	//
	// String messageText = aceJsonPath.get(elementName.toLowerCase());
	// return messageText;
	// }

}
