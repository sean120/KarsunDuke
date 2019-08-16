package com.seanfiles.utils;

import java.util.HashMap;

public class Ace2_0Mapping {
	
	
	
	public HashMap<String, String> aceRequestToHVSRequest() {
		HashMap<String, String> aceRequestMap = new HashMap<String, String>();

		aceRequestMap.put(("AddressLineText").toLowerCase(),"addressLineText");
		aceRequestMap.put(("CityName").toLowerCase(),"cityName");
		aceRequestMap.put(("FIPSStateAlphaCode").toLowerCase(),"stateCode");
		aceRequestMap.put(("PostalCode").toLowerCase(),"postalCode");
		aceRequestMap.put(("ZIPPlusFourCode").toLowerCase(),"zipPlusFourCode");
		aceRequestMap.put(("AlternateLoanIdentifier").toLowerCase(),"loanIdentifier1");
		aceRequestMap.put(("LoanIdentifierType").toLowerCase(),"loanIdentifierType1");
		aceRequestMap.put(("CensusTractBaseIdentifier").toLowerCase(),"censusTractBaseIdentifier");
		aceRequestMap.put(("CoreBasedStatisticalAreaCode").toLowerCase(),"coreBasedStatisticalAreaCode");
		aceRequestMap.put(("FIPSStateNumericCode").toLowerCase(),"FIPSStateNumericCode");
		aceRequestMap.put(("LatitudeNumber").toLowerCase(),"latitudeNumber");
		aceRequestMap.put(("LongitudeNumber").toLowerCase(),"longitudeNumber");
		aceRequestMap.put(("FIPSCountyThreeDigitCode").toLowerCase(),"FIPSCountyThreeDigitCode");
		aceRequestMap.put(("AccountIdentifier").toLowerCase(),"NA_lasaceapi_fa");
		aceRequestMap.put(("RequestType").toLowerCase(),"NA_03");
		aceRequestMap.put(("ClientSchemaVersionIdentifier").toLowerCase(),"NA_2.0");
		aceRequestMap.put(("RequestorIdentifier").toLowerCase(),"NA_ACEAPI_FA");
		aceRequestMap.put(("SchemaVersionIdentifier").toLowerCase(),"NA_1.0");
		aceRequestMap.put(("ServiceName").toLowerCase(),"NA_HVSWS");
		aceRequestMap.put(("ServiceRequestDateTime").toLowerCase(),"ServiceRequestDateTime");
		aceRequestMap.put(("ServiceRequestOperationName").toLowerCase(),"NA_GetHVSData");
		aceRequestMap.put(("SubscriberRequestCorrelationIdentifier").toLowerCase(),"loanIdentifier");
		
		return aceRequestMap;
	}
	
	public HashMap<String, String> hvsResponseToEdsRequest() {
		HashMap<String, String> hvsResponse = new HashMap<String, String>();

//not seeing element		hvsResponse.put(("HVSCommunicationFailureDerivation").toLowerCase(),"NA");
		hvsResponse.put(("ActiveMLSListingIndicator").toLowerCase(),"ActiveMLSListingIndicator");
		hvsResponse.put(("AppraisalCompletionAsIsIndicator").toLowerCase(),"AppraisalCompletionAsIsIndicator");
		hvsResponse.put(("AppraisalExistenceIndicator").toLowerCase(),"AppraisalExistenceIndicator");
		hvsResponse.put(("ARMsLengthContractIndicator").toLowerCase(),"ARMsLengthContractIndicator");
		hvsResponse.put(("DaysOnMarketIndicator").toLowerCase(),"DaysOnMarketIndicator");
		hvsResponse.put(("DistressedIndicator").toLowerCase(),"DistressedIndicator");
		hvsResponse.put(("HighestBestUseIndicator").toLowerCase(),"HighestBestUseIndicator");
		hvsResponse.put(("LegalityIndicator").toLowerCase(),"LegalityIndicator");
		hvsResponse.put(("MissingDataIndicator").toLowerCase(),"MissingDataIndicator");
		hvsResponse.put(("MultipleListingIndicator").toLowerCase(),"MultipleListingIndicator");
		hvsResponse.put(("NeighborhoodConformityIndicator").toLowerCase(),"NeighborhoodConformityIndicator");
		hvsResponse.put(("PhysicalDeficiencyIndicator").toLowerCase(),"PhysicalDeficiencyIndicator");
		hvsResponse.put(("PriceGrowthThresholdAmount").toLowerCase(),"PriceGrowthThresholdAmount");
		hvsResponse.put(("PropertyAgeCount").toLowerCase(),"PropertyAgeCount");
		hvsResponse.put(("PropertyConditionIndicator").toLowerCase(),"PropertyConditionIndicator");
		hvsResponse.put(("PublicRecordExistenceIndicator").toLowerCase(),"PublicRecordExistenceIndicator");
		hvsResponse.put(("StructureBuildingMaterialQualityRatingType").toLowerCase(),"StructureBuildingMaterialQualityRatingType");
		hvsResponse.put(("StructureBuildingMaterialQualityRatingIdentifier").toLowerCase(),"StructureBuildingMaterialQualityRatingIdentifier");
		hvsResponse.put(("QualityAgeIndicator").toLowerCase(),"QualityAgeIndicator");
		hvsResponse.put(("QualityIndicator").toLowerCase(),"QualityIndicator");
		hvsResponse.put(("HomeValueExplorerAssessmenntDateTime").toLowerCase(),"NA");
		hvsResponse.put(("HomeValueExplorerConfidenceLevelType").toLowerCase(),"AVMConfidenceScoreValue");
		hvsResponse.put(("HomeValueExplorerForecastStandardDeviationRate").toLowerCase(),"HVEStandardDeviationNumber");
		hvsResponse.put(("HomeValueExplorerMaximumValueAmount").toLowerCase(),"AVMHighValueRangeAmount");
		hvsResponse.put(("HomeValueExplorerMidPointValueEstimateAmount").toLowerCase(),"AVMValueAmount");
		hvsResponse.put(("HomeValueExplorerMinimumValueAmount").toLowerCase(),"AVMLowValueRangeAmount");
		hvsResponse.put(("PropertyValuationEffectiveDateTime").toLowerCase(),"ResponseDateTime");
		hvsResponse.put(("PropertyLandUseIndicator").toLowerCase(),"PropertyLandUseIndicator");
		hvsResponse.put(("PropertyPurchaseLotSizeOneAcreIndicator").toLowerCase(),"PropertyPurchaseLotSizeOneAcreIndicator");
		hvsResponse.put(("PropertyRefinanceLotSizeTwoAcreIndicator").toLowerCase(),"PropertyRefinanceLotSizeTwoAcreIndicator");
		hvsResponse.put(("PropertyUnitIndicator").toLowerCase(),"PropertyUnitIndicator");
		//hvsResponse.put(key, value)

		
		return hvsResponse;
	}
	
	public HashMap<String, String> aceRequestToEDSRequest() {
		HashMap<String, String> aceRequestMap = new HashMap<String, String>();

		aceRequestMap.put(("AddressLineText").toLowerCase(),"addressLineText");
		aceRequestMap.put(("AddressMatchLevelType").toLowerCase(),"addressMatchLevelType");
		aceRequestMap.put(("AddressSourceType").toLowerCase(),"addressLineText");		
		aceRequestMap.put(("AddressUnitDesignatorType").toLowerCase(),"addressUnitDesignatorType");
		aceRequestMap.put(("AddressUnitIdentifier").toLowerCase(),"addressUnitIdentifier");
		aceRequestMap.put(("CarrierRouteType").toLowerCase(),"carrierRouteType");
		aceRequestMap.put(("CityName").toLowerCase(),"cityName");
		aceRequestMap.put(("HighwayContractIdentifier").toLowerCase(),"highwayContractIdentifier");
		aceRequestMap.put(("PostalCode").toLowerCase(),"postalCode");
		aceRequestMap.put(("PostOfficeBoxIdentifier").toLowerCase(),"postOfficeBoxIdentifier");
		aceRequestMap.put(("RuralRouteIdentifier").toLowerCase(),"ruralRouteIdentifier");
		aceRequestMap.put(("FIPSStateAlphaCode").toLowerCase(),"stateCode");
		aceRequestMap.put(("StreetName").toLowerCase(),"streetName");
		aceRequestMap.put(("StreetPostDirectionalText").toLowerCase(),"streetPostDirectionalText");
		aceRequestMap.put(("StreetPreDirectionalText").toLowerCase(),"streetPreDirectionalText");
		aceRequestMap.put(("StreetPrimaryNumberText").toLowerCase(),"streetPrimaryNumberText");
		aceRequestMap.put(("StreetSuffixText").toLowerCase(),"streetSuffixText");
		aceRequestMap.put(("ZipPlusFourCode").toLowerCase(),"zipPlusFourCode");
		aceRequestMap.put(("AlternateLoanIdentifier").toLowerCase(),"loanIdentifier1");
		aceRequestMap.put(("AlternateLoanIdentifier").toLowerCase(),"loanIdentifier2");
		aceRequestMap.put(("AlternateLoanIdentifier").toLowerCase(),"loanIdentifier3");
		aceRequestMap.put(("AlternateLoanIdentifier").toLowerCase(),"loanIdentifier4");
		aceRequestMap.put(("AlternateLoanIdentifier").toLowerCase(),"loanIdentifier5");
		aceRequestMap.put(("AlternateLoanIdentifier").toLowerCase(),"loanIdentifier6");
		aceRequestMap.put(("AlternateLoanIdentifier").toLowerCase(),"loanIdentifier7");
		aceRequestMap.put(("LoanIdentifierType").toLowerCase(),"loanIdentifierType1");
		aceRequestMap.put(("LoanIdentifierType").toLowerCase(),"loanIdentifierType2");
		aceRequestMap.put(("LoanIdentifierType").toLowerCase(),"loanIdentifierType3");
		aceRequestMap.put(("LoanIdentifierType").toLowerCase(),"loanIdentifierType4");
		aceRequestMap.put(("LoanIdentifierType").toLowerCase(),"loanIdentifierType5");
		aceRequestMap.put(("LoanIdentifierType").toLowerCase(),"loanIdentifierType6");
		aceRequestMap.put(("LoanIdentifierType").toLowerCase(),"loanIdentifierType7");
		aceRequestMap.put(("CRACommunicationFailureType").toLowerCase(),"craCommunicationFailureType");
		aceRequestMap.put(("LoanIdentifier").toLowerCase(),"loanIdentifier");
		aceRequestMap.put(("AppraisalIdentifier1").toLowerCase(),"appraisalIdentifier");
		aceRequestMap.put(("DerivationRiskClassType").toLowerCase(),"derivationRiskClassType");
		aceRequestMap.put(("DerivedCalculationSourceType").toLowerCase(),"derivedCalculationSourceType");
		aceRequestMap.put(("LoanQualityAdvisorResultType").toLowerCase(),"loanQualityAdvisorResultType");
		aceRequestMap.put(("StrategicOfferingCreditRiskType").toLowerCase(),"strategicOfferingCreditRiskType");
		
		aceRequestMap.put(("EvaluationResultMessageCode").toLowerCase(),"messageCode");
		aceRequestMap.put(("EvaluationResultMessageText").toLowerCase(),"messageText");
		aceRequestMap.put(("ConstructionLoanIndicator").toLowerCase(),"constructionLoanIndicator");
		aceRequestMap.put(("LoanProcessingStageType").toLowerCase(),"loanProcessingStageType");
		aceRequestMap.put(("LoanPurposeType").toLowerCase(),"loanPurposeType");
		aceRequestMap.put(("LoanRefinanceCashOutDeterminationType").toLowerCase(),"loanRefinanceCashOutDeterminationType");
		aceRequestMap.put(("MortgageType").toLowerCase(),"mortgageType");
		aceRequestMap.put(("LoanConformityType").toLowerCase(),"loanConformityType");
		aceRequestMap.put(("SellerAccountIdentifier").toLowerCase(),"sellerAccountIdentifier");
		aceRequestMap.put(("SellerLoanIdentifier").toLowerCase(),"sellerLoanIdentifier");
		aceRequestMap.put(("BorrowerPropertyPurchasePriceAmount").toLowerCase(),"borrowerPropertyPurchasePriceAmount");
		aceRequestMap.put(("LoanApplicationBaseLoanAmount").toLowerCase(),"loanApplicationBaseLoanAmount");
		aceRequestMap.put(("MIAndFundingFeeFinancedAmount").toLowerCase(),"miAndFundingFeeFinancedAmount");
		aceRequestMap.put(("LoanScopeType").toLowerCase(),"loanScopeType");
		aceRequestMap.put(("LTVRatioPercent").toLowerCase(),"ltvRatioPercent");
		aceRequestMap.put(("LTVSourceType").toLowerCase(),"ltvSourceType");
		aceRequestMap.put(("ConstructionLoanType").toLowerCase(),"constructionLoanType");
		aceRequestMap.put(("MIPremiumFinancedAmount").toLowerCase(),"miPremiumFinancedAmount");
		aceRequestMap.put(("InvestorCollateralProgramType").toLowerCase(),"investorCollateralProgramType");
		aceRequestMap.put(("LoanOriginationSystemVersionIdentifier").toLowerCase(),"loanOriginationSystemVersionIdentifier");
		aceRequestMap.put(("LoanPropertyUsageType").toLowerCase(),"loanPropertyUsageType");
		aceRequestMap.put(("NoteAmount").toLowerCase(),"noteAmount");
		aceRequestMap.put(("FinancedUnitCount").toLowerCase(),"financedUnitCount");
		aceRequestMap.put(("AutomatedUnderwritingSystemType").toLowerCase(),"automatedUnderwritingSystemType");
		aceRequestMap.put(("LoanUnderwritingCaseIdentifier").toLowerCase(),"loanUnderwritingCaseIdentifier");
		aceRequestMap.put(("LoanUnderwritingDecisionDefaultProbabilityRate").toLowerCase(),"loanUnderwritingDecisionDefaultProbabilityRate");
		aceRequestMap.put(("ProductDescription").toLowerCase(),"productDescription");
		aceRequestMap.put(("ProductIdentifier").toLowerCase(),"productIdentifier");
		aceRequestMap.put(("ProductType").toLowerCase(),"productType");
		aceRequestMap.put(("ProgramType").toLowerCase(),"programType");
		aceRequestMap.put(("RefinanceProgramType").toLowerCase(),"refinanceProgramType");
		aceRequestMap.put(("IntentToOccupyType").toLowerCase(),"IntentToOccupyType");
		aceRequestMap.put(("PropertyCategoryType").toLowerCase(),"propertyCategoryType");
		aceRequestMap.put(("PropertyEstateType").toLowerCase(),"propertyEstateType");
		aceRequestMap.put(("PropertyValuationAmount").toLowerCase(),"propertyValuationAmount");
		aceRequestMap.put(("PropertyValuationEffectiveDateTime").toLowerCase(),"propertyValuationEffectiveDateTime");		
		aceRequestMap.put(("PropertyValuationMethodType").toLowerCase(),"propertyValuationMethodType");
		aceRequestMap.put(("RDSCommunicationFailureType").toLowerCase(),"rdsCommunicationFailureType");
		aceRequestMap.put(("ProjectAttachmentType").toLowerCase(),"projectAttachmentType");
		aceRequestMap.put(("ProjectLegalStructureType").toLowerCase(),"projectLegalStructureType");
		aceRequestMap.put(("ServiceName").toLowerCase(),"serviceName");
		aceRequestMap.put(("ServiceRequestOperationName").toLowerCase(),"serviceRequestOperationName");
		aceRequestMap.put(("SubscriberIdentifier").toLowerCase(),"subscriberIdentifier");
		aceRequestMap.put(("SubscriberRequestCorrelationIdentifier").toLowerCase(),"subscriberRequestCorrelationIdentifier");
		aceRequestMap.put(("ConstructionMethodType").toLowerCase(),"constructionMethodType");
	//	aceRequestMap.put(("PropertyPurchaseLotSizeOneAcreIndicator").toLowerCase(),"PropertyPurchaseLotSizeOneAcreIndicator");
		aceRequestMap.put(("Predicted Condition Score Value").toLowerCase(), "Predicted Condition Score Value");
		aceRequestMap.put(("FEMADisasterName").toLowerCase(), "FEMADisasterName");
		//HVS Response	HVSResponse/HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/DisasterPropertyResult/FEMADisasterNam
		aceRequestMap.put(("AppraisalAlternativeDisasterEligibilityDescription").toLowerCase(), "AppraisalAlternativeDisasterEligibilityDescription");
	
		return aceRequestMap;
	}
	
	public HashMap<String, String> edsResponseToAceResponse() {
		HashMap<String, String> aceResponse = new HashMap<String, String>();

		aceResponse.put(("propertyValuationEffectiveDateTime").toLowerCase(),"AttrVal2");
		aceResponse.put(("alternateAppraisalDecisionExpirationDatetime").toLowerCase(),"DecisionVal1");
		aceResponse.put(("alternateAppraisalDecisionStatusType").toLowerCase(),"DecisionVal2");
		aceResponse.put(("alternateAppraisalEligibilityDecision").toLowerCase(),"DecisionVal3");
		aceResponse.put(("minimumLoanAssessmentFormType").toLowerCase(),"DecisionVal4");
		aceResponse.put(("listText5").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText6").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText7").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText8").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText9").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText10").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText11").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText12").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText13").toLowerCase(),"DecisionVal5");
		aceResponse.put(("error").toLowerCase(),"NA");
		aceResponse.put(("code").toLowerCase(),"NA");
		aceResponse.put(("message").toLowerCase(),"NA");
		aceResponse.put(("loanIdentifier1").toLowerCase(),"AttrVal1");
		aceResponse.put(("loanIdentifier2").toLowerCase(),"AttrVal1");
		aceResponse.put(("loanIdentifier3").toLowerCase(),"AttrVal1");
		aceResponse.put(("loanIdentifier4").toLowerCase(),"AttrVal1");
		aceResponse.put(("loanIdentifier5").toLowerCase(),"AttrVal1");
		aceResponse.put(("loanIdentifier6").toLowerCase(),"AttrVal1");
		aceResponse.put(("loanIdentifier7").toLowerCase(),"AttrVal1");
		aceResponse.put(("loanIdentifier8").toLowerCase(),"AttrVal1");
		aceResponse.put(("loanIdentifier9").toLowerCase(),"AttrVal1");
		aceResponse.put(("loanIdentifierType").toLowerCase(),"AttrKey1");
		aceResponse.put(("listText1").toLowerCase(),"MessageCode");
		aceResponse.put(("listText2").toLowerCase(),"MessageGroup1");
		aceResponse.put(("listText3").toLowerCase(),"MessageGroup2");
		aceResponse.put(("listText4").toLowerCase(),"MessageHeader");
		aceResponse.put(("messageCategory").toLowerCase(),"MessageCategory");
		aceResponse.put(("messageDisplayFlag").toLowerCase(),"DisplayFlag");
		aceResponse.put(("messageId").toLowerCase(),"MessageId");
		aceResponse.put(("messageType").toLowerCase(),"MessageType");
		aceResponse.put(("sequenceNumber").toLowerCase(),"SequenceNumber");

		return aceResponse;
	}
	
	public HashMap<String, String> aceResponsetoEDSResponse() {
		HashMap<String, String> aceResponse = new HashMap<String, String>();
		aceResponse.put(("alternateAppraisalEligibilityDecision").toLowerCase(),"DecisionVal3");
		aceResponse.put(("minimumLoanAssessmentFormType").toLowerCase(),"DecisionVal4");
		aceResponse.put(("listText5").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText6").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText7").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText8").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText9").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText10").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText11").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText12").toLowerCase(),"DecisionVal5");
		aceResponse.put(("listText13").toLowerCase(),"DecisionVal5");
		aceResponse.put(("messageListText1").toLowerCase(),"MessageCode1");
		aceResponse.put(("messageListText2").toLowerCase(),"MessageGroup1a");
		aceResponse.put(("messageListText3").toLowerCase(),"MessageGroup2a");
		aceResponse.put(("messageListText4").toLowerCase(),"MessageHeader1");
		aceResponse.put(("messageListText5").toLowerCase(),"MessageCode2");
		aceResponse.put(("messageListText6").toLowerCase(),"MessageGroup1b");
		aceResponse.put(("messageListText7").toLowerCase(),"MessageGroup2b");
		aceResponse.put(("messageListText8").toLowerCase(),"MessageHeader2");
		aceResponse.put(("messageListText9").toLowerCase(),"MessageCode3");
		aceResponse.put(("messageListText10").toLowerCase(),"MessageGroup1c");
		aceResponse.put(("messageListText11").toLowerCase(),"MessageGroup2c");
		aceResponse.put(("messageListText12").toLowerCase(),"MessageHeader3");
		aceResponse.put(("messageListText13").toLowerCase(),"MessageCode4");
		aceResponse.put(("messageListText14").toLowerCase(),"MessageGroup1d");
		aceResponse.put(("messageListText15").toLowerCase(),"MessageGroup2d");
		aceResponse.put(("messageListText16").toLowerCase(),"MessageHeader4");
		aceResponse.put(("messageCategory1").toLowerCase(),"MessageCategory1");
		aceResponse.put(("messageDisplayFlag1").toLowerCase(),"DisplayFlag1");
		aceResponse.put(("messageId1").toLowerCase(),"MessageId1");
		aceResponse.put(("messageType1").toLowerCase(),"MessageType1");
		aceResponse.put(("messageCategory2").toLowerCase(),"MessageCategory2");
		aceResponse.put(("messageDisplayFlag2").toLowerCase(),"DisplayFlag2");
		aceResponse.put(("messageId2").toLowerCase(),"MessageId2");
		aceResponse.put(("messageType2").toLowerCase(),"MessageType2");
		aceResponse.put(("messageCategory3").toLowerCase(),"MessageCategory3");
		aceResponse.put(("messageDisplayFlag3").toLowerCase(),"DisplayFlag3");
		aceResponse.put(("messageId3").toLowerCase(),"MessageId3");
		aceResponse.put(("messageType3").toLowerCase(),"MessageType3");
		aceResponse.put(("messageCategory4").toLowerCase(),"MessageCategory4");
		aceResponse.put(("messageDisplayFlag4").toLowerCase(),"DisplayFlag4");
		aceResponse.put(("messageId4").toLowerCase(),"MessageId4");
		aceResponse.put(("messageType4").toLowerCase(),"MessageType4");

		return aceResponse;
	}
	

}
