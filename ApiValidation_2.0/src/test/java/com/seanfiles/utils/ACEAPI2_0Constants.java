package com.seanfiles.utils;

public class ACEAPI2_0Constants {

	//response body
	//loanidentifications
	public static final String LoanIdentifier = "loanIdentifier";
	public static final String LoanIdentifierType = "loanIdentifierType";
	
	
	//aceAssesmentDecision
	public static final String AlternateAppraisalDecisionEffectiveDatetime = "alternateAppraisalDecisionEffectiveDatetime";
	public static final String AlternateAppraisalDecisionExpirationDatetime = "alternateAppraisalDecisionExpirationDatetime";
	public static final String AlternateAppraisalDecisionStatusType = "alternateAppraisalDecisionStatusType";
	public static final String AlternateAppraisalEligibilityDecision = "alternateAppraisalEligibilityDecision";
	public static final String AlternateAppraisalType = "alternateAppraisalType";
	public static final String ListText = "listText";
	public static final String MinimunLoanAssessmentFormType = "minimumLoanAssessmentFormType";
	public static final String PropertyValuationEffectiveDateTimeRequest = "propertyValuationEffectiveDateTime";
	
	
	//aceDecisionMessageContainer
	public static final String MessageCategory = "messageCategory";
	public static final String MessageCode = "messageCode";
	public static final String MessageGroup1 = "messageGroup1";
	public static final String MessageGroup2 = "messageGroup2";
	public static final String MessageId = "messageId";
	public static final String MessageHeader = "messageHeader";
	public static final String MessageType = "messageType";
	
	//--aceFACTDecision--//
	public static final String AceFACTExpirationDatetime = "aceFACTExpirationDatetime";
	public static final String AceFACTDecisionStatusType = "aceFACTDecisionStatusType";
	public static final String AceFACTEligibilityDecision = "aceFACTEligibilityDecision";
	public static final String AceFACTPropertyValuationEffectiveDateTime = "aceFACTPropertyValuationEffectiveDateTime";
	
	
	
	//error//
	public static final String Error = "error";
	
	//request body
	
    //--loanPropertyCollaterals--//
	public static final String CommunicationFailureDerivedDetails = "communicationFailureDerivedDetails";
	public static final String RdsCommunicationFailureDerivation = "rdsCommunicationFailureDerivation";
	public static final String RdsCommunicationFailureType = "rdsCommunicationFailureType";
	public static final String LoanIdentifierRequest = "loanIdentifier";
	public static final String LoanIdentifierTypeRequest = "loanIdentifierType";
	public static final String CraCommunicationFailureType = "craCommunicationFailureType";
	
	public static final String MiPremiumFinancedAmount = "MiPremiumFinancedAmount";
	
	//--loanStates--//
	public static final String MiPremiumFinancedIndicator = "miPremiumFinancedIndicator";
	
	//--loan container--//
	public static final String ConstructionLoanIndicator = "constructionLoanIndicator";
	public static final String GrandfatheringIndicator = "grandfatheringIndicator";
	public static final String LoanProcessingStageType = "loanProcessingStageType";
	public static final String LoanPurposeType = "loanPurposeType";
	public static final String LoanRefinanceCashOutDeterminationType = "loanRefinanceCashOutDeterminationType";
	public static final String MortgageType = "mortgageType";
	
	//--loanAcquisition--//
	public static final String LoanConformityType = "loanConformityType";
	public static final String SellerAccountIdentifier = "sellerAccountIdentifier";
	public static final String SellerLoanIdentifier = "sellerLoanIdentifier";
	
	
	//--loanapplication--//
	public static final String BorrowerPropertyPurchasePriceAmount = "borrowerPropertyPurchasePriceAmount";
	public static final String LoanApplicationBaseLoanAmount = "loanApplicationBaseLoanAmount";
	public static final String LoanApplicationIdentifier = "loanApplicationIdentifier";
	public static final String MiAndFundingFeeFinancedAmount = "miAndFundingFeeFinancedAmount";
	
	
	//--loanConversionRule--//
	public static final String constructionLoanType = "constructionLoanType";
	
	//--loanOrigination--//
	public static final String InvestorCollateralProgramType = "investorCollateralProgramType";
	public static final String LoanOriginationSystemVersionIdentifier = "loanOriginationSystemVersionIdentifier";
	public static final String LoanPropertyUsageType = "loanPropertyUsageType";
	public static final String NoteAmount = "noteAmount";
	
	//--loanUnderwritingDecision--//
	public static final String AutomatedUnderwritingSystemType = "automatedUnderwritingSystemType";
	public static final String LoanUnderwritingCaseIdentifier = "loanUnderwritingCaseIdentifier";
	public static final String LoanUnderwritingDecisionDefaultProbabilityRate = "loanUnderwritingDecisionDefaultProbabilityRate";
	
	//--creditRiskDerivation--//
	public static final String DerivationRiskClassType = "derivationRiskClassType";
	public static final String DerivedCalculationSourceType = "derivedCalculationSourceType";
	public static final String StrategicOfferingCreditRiskType = "strategicOfferingCreditRiskType";
	public static final String LoanQualityAdvisorResultType = "loanQualityAdvisorResultType";
	public static final String MessageCodeRequest = "messageCodeRequest";
	public static final String MessageTextRequest = "messageText";
	//--loanCollateralRiskAssessmentDetails--//
	public static final String LoanScopeType = "loanScopeType";
	public static final String LtvRatioPercent = "ltvRatioPercent";
	public static final String LtvSourceType = "ltvSourceType";
	
	//--program--//
	public static final String ProgramType = "programType";
	public static final String RefinanceProgramType = "refinanceProgramType";
	
	//--loanState--//
	public static final String LoanStateDate = "loanStateDate";
	public static final String LoanStateType = "loanStateType";
	
	//--partyRole--//
	public static final String PartyRoleIdentifier = "partyRoleIdentifier";
	public static final String PartyRoleType = "partyRoleType";
	
	//--productDefinition--//
	public static final String ProductDescription = "productDescription";
	public static final String ProductIdentifier = "productIdentifier";
	public static final String ProductType = "productType";
	
	//--address--//
	public static final String FIPSCountyThreeDigitCode ="FIPSCountyThreeDigitCode";
	public static final String FIPSStateNumericCode = "FIPSStateNumericCode";
	public static final String AddressLineText = "addressLineText";
	public static final String AddressMatchLevelType = "addressMatchLevelType";
	public static final String AddressSourceType = "addressSourceType";
	public static final String AddressUnitDesignatorType = "addressUnitDesignatorType";
	public static final String AddressUnitIdentifier = "addressUnitIdentifier";
	public static final String CarrierRouteType = "carrierRouteType";
	public static final String CensusTractBaseIdentifier = "censusTractBaseIdentifier";
	public static final String CityName = "cityName";
	public static final String CoreBasedStatisticalAreaCode ="coreBasedStatisticalAreaCode";
	public static final String HighwayContractIdentifier = "highwayContractIdentifier";
	public static final String LatitudeNumber = "latitudeNumber";
	public static final String LongitudeNumber = "longitudeNumber";
	public static final String PostOfficeBoxIdentifier = "postOfficeBoxIdentifier";
	public static final String PostalCode = "postalCode";
	public static final String RuralRouteIdentifier = "ruralRouteIdentifier";
	public static final String StateCode = "stateCode";
	public static final String StreetName = "streetName";
	public static final String StreetPostDirectionalText = "streetPostDirectionalText";
	public static final String StreetPreDirectionalText = "streetPreDirectionalText";
	public static final String StreetPrimaryNumberText = "streetPrimaryNumberText";
	public static final String StreetSuffixText = "streetSuffixText";
	public static final String ZipPlusFourCode = "zipPlusFourCode";
	
	//--property--//
	public static final String PropertyCategoryType = "propertyCategoryType";
	public static final String PropertyOccupancyType = "propertyOccupancyType";
	
	//--propertyAssessment--//
	public static final String PropertyAssessmentSourceType = "propertyAssessmentSourceType";
	
	//--propertyRights--//
	public static final String PropertyRightsType = "propertyRightsType";
	
	//--propertyTitles--//
	public static final String PropertyEstateType = "propertyEstateType";
	//--appraisal--//
	public static final String AppraisalIdentifier = "appraisalIdentifier";
	//--propertyValuation--//
	public static final String PropertyIdentifier = "propertyIdentifier";
	public static final String PropertyValuationAmount = "propertyValuationAmount";
	public static final String PropertyValuationEffectiveDateTime = "propertyValuationEffectiveDateTime";
	public static final String PropertyValuationMethodType = "propertyValuationMethodType";
	public static final String PropertyValuationSequenceNumber = "propertyValuationSequenceNumber";
	public static final String PropertyValuationType = "propertyValuationType";
	
	//--realEstateProject--//
	public static final String ProjectAttachmentType = "projectAttachmentType";
	public static final String ProjectClassificationType = "projectClassificationType";
	public static final String ProjectLegalStructureType = "projectLegalStructureType";
	
	//--structure--//
	public static final String ConstructionMethodType = "constructionMethodType";
	
	//--serviceRequestDetails--//
	public static final String ServiceName = "serviceName";
	public static final String ServiceRequestOperationName = "serviceRequestOperationName";
	public static final String SubscriberIdentifier = "subscriberIdentifier";
	public static final String SubscriberRequestCorrelationIdentifier ="subscriberRequestCorrelationIdentifier";
	
	public static final String FinancedUnitCount = "financedUnitCount";
	
	public static final String BorrowerClassificationType = "borrowerClassificationType";
	public static final String BorrowerIdentifier = "borrowerIdentifier";
	public static final String IntentToOccupeType = "IntentToOccupeType";
	public static final String PatryRoleTypeBorrower = "partyRoleType";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
