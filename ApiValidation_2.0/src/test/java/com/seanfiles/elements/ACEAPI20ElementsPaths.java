package com.seanfiles.elements;

import static com.seanfiles.elements.ACEAPI20Elements.*;

import java.util.HashMap;
import java.util.Map;

import com.seanfiles.db.ACEAPI20DBDocuments;

public class ACEAPI20ElementsPaths {
	private static ACEAPI20ElementsPaths paths;
	Map<String, String> jsonPaths;
	
	public static ACEAPI20ElementsPaths getACEAPI20ElementsPaths() {
		if(paths == null ) {
			paths= new ACEAPI20ElementsPaths();
		}
		return paths;
	}
	
	public ACEAPI20ElementsPaths() {
		jsonPaths=new HashMap<String, String>();
		jsonPaths.put(DOC_ID, "$._id");
		jsonPaths.put(Message, "$.message");
		
		jsonPaths.put(ErrorCode, "$.errors.error[0].errorResponse.code");
		jsonPaths.put(ErrorMessage, "$.errors.error[0].errorResponse.message");
		
		jsonPaths.put(AddressLineText, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].addressLineText");
		jsonPaths.put(CityName, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].cityName");
		jsonPaths.put(StateCode, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].stateCode");
		jsonPaths.put(PostalCode, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].postalCode");
		jsonPaths.put(ZipPlusFourCode, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].zipPlusFourCode");
		jsonPaths.put(CensusTractBaseIdentifier, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].censusTractBaseIdentifier");
		jsonPaths.put(CoreBasedStatisticalAreaCode, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].coreBasedStatisticalAreaCode");
		jsonPaths.put(FIPSStateNumericCode, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].FIPSStateNumericCode");
		jsonPaths.put(LatitudeNumber, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].latitudeNumber");
		jsonPaths.put(LongitudeNumber, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].longitudeNumber");
		jsonPaths.put(FIPSCountyThreeDigitCode , "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].FIPSCountyThreeDigitCode");
		
		jsonPaths.put(StreetName, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].streetName");
		jsonPaths.put(StreetPrimaryNumberText, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].streetPrimaryNumberText");
		jsonPaths.put(StreetSuffixText, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].streetSuffixText");
		jsonPaths.put(StreetPostDirectionalText, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].streetPostDirectionalText");
		jsonPaths.put(StreetPreDirectionalText, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].streetPreDirectionalText");
		jsonPaths.put(AddressUnitIdentifier, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].addressUnitIdentifier");
		jsonPaths.put(AddressUnitDesignatorType, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].addressUnitDesignatorType");
		
		jsonPaths.put(CarrierRouteType, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].carrierRouteType");
		jsonPaths.put(HighwayContractIdentifier, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].highwayContractIdentifier");
		jsonPaths.put(PostOfficeBoxIdentifier, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].postOfficeBoxIdentifier");
		jsonPaths.put(RuralRouteIdentifier, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].ruralRouteIdentifier");
		
		jsonPaths.put(GeocodingIndicator, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].geocodingIndicator");
		jsonPaths.put(AddressMatchLevelType, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].addressMatchLevelType");
		jsonPaths.put(AddressSourceType, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].addressSourceType");

		jsonPaths.put(PreScrub_AddressLineText, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[1].addressLineText");
		jsonPaths.put(PreScrub_CityName, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[1].cityName");
		jsonPaths.put(PreScrub_StateCode, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[1].stateCode");
		jsonPaths.put(PreScrub_PostalCode, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[1].postalCode");
		jsonPaths.put(PreScrub_ZipPlusFourCode, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[1].zipPlusFourCode");
		jsonPaths.put(PreScrub_AddressSourceType, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[1].addressSourceType");
		jsonPaths.put(PreScrub_GeocodingIndicator, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[1].geocodingIndicator");
		
		jsonPaths.put(LoanID_LPKey, "$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorKey\")].loanIdentifier");
		jsonPaths.put(LoanID_LPUL, "$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorUniqueLoan\")].loanIdentifier");
		jsonPaths.put(LoanID_LPT, "$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorTransaction\")].loanIdentifier");
		jsonPaths.put(LoanID_HPGT, "$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"HewlettPackardGeneratedTransaction\")].loanIdentifier");
		jsonPaths.put(LoanID_HPLPT, "$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"HewlettPackardLoanProspectorTransaction\")].loanIdentifier");
		
		jsonPaths.put(LoanID_LQA, "$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanQualityAdvisor\")].loanIdentifier");
		jsonPaths.put(LoanID_SLS, "$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"SubmissionLinkService\")].loanIdentifier");
		
		jsonPaths.put(LoanIdentifier, "$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[0].loanIdentifier");

		jsonPaths.put(PartyID_SELLER,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanAcquisitionDetails.loanAcquisition.sellerAccountIdentifier");

		jsonPaths.put(SubscriberIdentifier, "$.serviceRequestDetails.subscriberIdentifier");
		jsonPaths.put(SubscriberRequestCorrelationIdentifier, "$.serviceRequestDetails.subscriberRequestCorrelationIdentifier");
		jsonPaths.put(ServiceName, "$.serviceRequestDetails.serviceName");
		jsonPaths.put(ServiceRequestOperationName, "$.serviceRequestDetails.serviceRequestOperationName");
		
		jsonPaths.put(ContextMapId, "$.contextMap._id");
		jsonPaths.put(ContextMapServiceName, "$.contextMap.serviceName");
		jsonPaths.put(ContextMapRequestType, "$.contextMap.requestType");
		
		jsonPaths.put(LoanProcessingStageType,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.loanProcessingStageType");

		jsonPaths.put(AppraisalIdentifier,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.appraisal.appraisalIdentifier");

		jsonPaths.put(ConstructionLoanIndicator,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.constructionLoanIndicator");

		jsonPaths.put(GrandfatheringIndicator,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.grandfatheringIndicator");

		jsonPaths.put(LoanPurposeType, "$.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.loanPurposeType");

		jsonPaths.put(LoanRefinanceCashOutDeterminationType,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.loanRefinanceCashOutDeterminationType");

		jsonPaths.put(MortgageType, "$.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.mortgageType");

		jsonPaths.put(LoanConformityType,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanAcquisitionDetails.loanAcquisition.loanConformityType");

		jsonPaths.put(SellerAccountIdentifier,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanAcquisitionDetails.loanAcquisition.sellerAccountIdentifier");

		jsonPaths.put(SellerLoanIdentifier,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanAcquisitionDetails.loanAcquisition.sellerLoanIdentifier");

		jsonPaths.put(BorrowerPropertyPurchasePriceAmount,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanApplicationDetails.loanApplication.borrowerPropertyPurchasePriceAmount");

		jsonPaths.put(LoanApplicationBaseLoanAmount,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanApplicationDetails.loanApplication.loanApplicationBaseLoanAmount");

		jsonPaths.put(MiAndFundingFeeFinancedAmount,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanApplicationDetails.loanApplication.miAndFundingFeeFinancedAmount");

		jsonPaths.put(LoanScopeType1,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[0].loanCollateralRiskAssessment.loanScopeType");

		jsonPaths.put(LtvRatioPercent1,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[0].loanCollateralRiskAssessment.ltvRatioPercent");

		jsonPaths.put(LtvSourceType1,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[0].loanCollateralRiskAssessment.ltvSourceType");

		jsonPaths.put(LoanScopeType2,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[1].loanCollateralRiskAssessment.loanScopeType");

		jsonPaths.put(LtvRatioPercent2,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[1].loanCollateralRiskAssessment.ltvRatioPercent");

		jsonPaths.put(LtvSourceType2,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[1].loanCollateralRiskAssessment.ltvSourceType");

		jsonPaths.put(ConstructionLoanType,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanConversionRuleDetails.loanConversionRule.constructionLoanType");

		jsonPaths.put(MiPremiumFinancedAmount,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.insurancePolicyDetails.loanMI.miPremiumFinancedAmount");

		jsonPaths.put(MiPremiumFinancedIndicator,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.insurancePolicyDetails.loanMI.miPremiumFinancedIndicator");

		jsonPaths.put(LoanOriginationSystemVersionIdentifier,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanOrigination.loanOriginationSystemVersionIdentifier");

		jsonPaths.put(LoanPropertyUsageType,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanOrigination.loanPropertyUsageType");

		jsonPaths.put(NoteAmount,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanOrigination.noteAmount");

		jsonPaths.put(FinancedUnitCount,
				"$.loanDeliveryStructure.loanDetails.loanPropertyCollaterals.loanPropertyCollateralDetails.loanPropertyCollateral.financedUnitCount");

		jsonPaths.put(LoanUnderwritingDecisionDefaultProbabilityRate,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanUnderwritingDecision.loanUnderwritingDecisionDefaultProbabilityRate");
		jsonPaths.put(LoanUnderwritingCaseIdentifier,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanUnderwritingDecision.loanUnderwritingCaseIdentifier");

		jsonPaths.put(PartyRoleIdentifier,
				"$.loanDeliveryStructure.loanDetails.partyRoles.partyRole[0].partyRoleIdentifier");

		jsonPaths.put(PartyRoleType, "$.loanDeliveryStructure.loanDetails.partyRoles.partyRole[0].partyRoleType");

		jsonPaths.put(ProductDescription,
				"$.loanDeliveryStructure.loanDetails.productDetails.productDefinition.productDescription");

		jsonPaths.put(ProductIdentifier,
				"$.loanDeliveryStructure.loanDetails.productDetails.productDefinition.productIdentifier");

		jsonPaths.put(ProductType, "$.loanDeliveryStructure.loanDetails.productDetails.productDefinition.productType");
		jsonPaths.put(ProgramType, "$.loanDeliveryStructure.loanDetails.loanDetailInfo.programs[0].program.programType");

		jsonPaths.put(RefinanceProgramType,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.programs[0].program.refinanceProgramType");
		jsonPaths.put(ProgramType2, "$.loanDeliveryStructure.loanDetails.loanDetailInfo.programs[1].program.programType");

		jsonPaths.put(RefinanceProgramType2,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.programs[1].program.refinanceProgramType");
		jsonPaths.put(IntentToOccupyType,
				"$.loanDeliveryStructure.loanDetails.singleFamilyLoanBorrower[0].intentToOccupyType");
		jsonPaths.put(BorrowerClassificationType,
				"$.loanDeliveryStructure.loanDetails.singleFamilyLoanBorrower[0].borrowerClassificationType");

		jsonPaths.put(BorrowerIdentifier,
				"$.loanDeliveryStructure.loanDetails.singleFamilyLoanBorrower[0].borrowerIdentifier");

		jsonPaths.put(PropertyCategoryType,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.property.propertyCategoryType");
		jsonPaths.put(PropertyEstateType,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyTitles.propertyEstateType");
		jsonPaths.put(PropertyValuationAmount,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[0].propertyValuationAmount");
		jsonPaths.put(PropertyAssessmentSourceType,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[0].propertyAssessmentSourceType");
		jsonPaths.put(PropertyIdentifier,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[0].propertyIdentifier");
		jsonPaths.put(PropertyValuationEffectiveDateTime,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[0].propertyValuationEffectiveDateTime");
		jsonPaths.put(PropertyValuationMethodType,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[0].propertyValuationMethodType");
		jsonPaths.put(PropertyValuationSequenceNumber,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[0].propertyValuationSequenceNumber");
		jsonPaths.put(PropertyValuationType,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[0].propertyValuationType");

		jsonPaths.put(EST_O_PropertyValuationSequenceNumber,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"3\")].propertyValuationSequenceNumber");
		jsonPaths.put(EST_O_PropertyValuationAmount,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"3\")].propertyValuationAmount");
		jsonPaths.put(EST_O_PropertyAssessmentSourceType,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"3\")].propertyAssessmentSourceType");
		jsonPaths.put(EST_O_PropertyValuationEffectiveDateTime,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"3\")].propertyValuationEffectiveDateTime");
		jsonPaths.put(EST_O_PropertyValuationMethodType,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"3\")].propertyValuationMethodType");
		jsonPaths.put(EST_O_PropertyValuationType,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"3\")].propertyValuationType");

		jsonPaths.put(EST_S_PropertyValuationSequenceNumber,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"4\")].propertyValuationSequenceNumber");
		jsonPaths.put(EST_S_PropertyValuationAmount,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"4\")].propertyValuationAmount");
		jsonPaths.put(EST_S_PropertyAssessmentSourceType,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"4\")].propertyAssessmentSourceType");
		jsonPaths.put(EST_S_PropertyValuationEffectiveDateTime,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"4\")].propertyValuationEffectiveDateTime");
		jsonPaths.put(EST_S_PropertyValuationMethodType,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"4\")].propertyValuationMethodType");
		jsonPaths.put(EST_S_PropertyValuationType,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"4\")].propertyValuationType");

		jsonPaths.put(FR_PropertyValuationSequenceNumber,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"7\")].propertyValuationSequenceNumber");
		jsonPaths.put(FR_PropertyValuationAmount,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"7\")].propertyValuationAmount");
		jsonPaths.put(FR_PropertyValuationEffectiveDateTime,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"7\")].propertyValuationEffectiveDateTime");
		jsonPaths.put(FR_PropertyValuationMethodType,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"7\")].propertyValuationMethodType");

		jsonPaths.put(CraCommunicationFailureType,
				"$.loanDeliveryStructure.loanDetails.communicationFailureDerivedDetails.craCommunicationFailureDerivation.craCommunicationFailureType");
		jsonPaths.put(RdsCommunicationFailureType,
				"$.loanDeliveryStructure.loanDetails.communicationFailureDerivedDetails.rdsCommunicationFailureDerivation.rdsCommunicationFailureType");

		jsonPaths.put(ProjectAttachmentType,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.realEstateProject.projectAttachmentType");
		jsonPaths.put(ProjectLegalStructureType,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.realEstateProject.projectLegalStructureType");

		jsonPaths.put(ConstructionMethodType,
				"$.loanDeliveryStructure.loanDetails.propertyDetails.structureDetails.structure.constructionMethodType");

		jsonPaths.put(AutomatedUnderwritingSystemType,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanUnderwritingDecision.automatedUnderwritingSystemType");

		jsonPaths.put(DerivationRiskClassType,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[0].derivationRiskClassType");

		jsonPaths.put(DerivedCalculationSourceType,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[0].derivedCalculationSourceType");

		jsonPaths.put(StrategicOfferingCreditRiskType,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[0].strategicOfferingCreditRiskType");

		jsonPaths.put(LoanQualityAdvisorResultType,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[0].loanQualityAdvisorResultType");

		jsonPaths.put(MessageCode,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[0].messageCode");
		jsonPaths.put(MessageText,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[0].messageText");

		jsonPaths.put(InvestorCollateralProgramType,
				"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanOrigination.investorCollateralProgramType");

		jsonPaths.put(AA_MessageCategory, "$.aceDecisionMessages.messages[<INDEX>].messageCategory");
		jsonPaths.put(AA_MessageCode, "$.aceDecisionMessages.messages[<INDEX>].messageCodeList[<SUB_INDEX>].listText");
		jsonPaths.put(AA_MessageDisplayFlag, "$.aceDecisionMessages.messages[<INDEX>].messageDisplayFlag");
		jsonPaths.put(AA_MessageGroup1, "$.aceDecisionMessages.messages[<INDEX>].messageGroup1List[<SUB_INDEX>].listText");
		jsonPaths.put(AA_MessageGroup2, "$.aceDecisionMessages.messages[<INDEX>].messageGroup2List[<SUB_INDEX>].listText");
		jsonPaths.put(AA_MessageId, "$.aceDecisionMessages.messages[<INDEX>].messageId");
		jsonPaths.put(AA_MessageHeader, "$.aceDecisionMessages.messages[<INDEX>].messageHeaderList[<SUB_INDEX>].listText");
		jsonPaths.put(AA_MessageType, "$.aceDecisionMessages.messages[<INDEX>].messageType");
		jsonPaths.put(AA_SequenceNumber, "$.aceDecisionMessages.messages[<INDEX>].sequenceNumber");
		
		jsonPaths.put(AlternateAppraisalDecisionExpirationDatetime, "$.aceAssessmentDecision.alternateAppraisalDecisionExpirationDatetime");
		jsonPaths.put(AlternateAppraisalDecisionStatusType, "$.aceAssessmentDecision.alternateAppraisalDecisionStatusType");
		jsonPaths.put(AlternateAppraisalEligibilityDecision, "$.aceAssessmentDecision.alternateAppraisalEligibilityDecision");
		jsonPaths.put(AlternateAppraisalReasonList, "$.aceAssessmentDecision.alternateAppraisalReasonList[<INDEX>].listText");
		jsonPaths.put(MinimumLoanAssessmentFormType, "$.aceAssessmentDecision.minimumLoanAssessmentFormType");
		jsonPaths.put(PropertyValuationEffectiveDateTime, "$.aceAssessmentDecision.propertyValuationEffectiveDateTime");

		jsonPaths.put(LoanDeliveryStructure_Object,"$.loanDeliveryStructure");
		jsonPaths.put(LoanDetails_Object,"$.loanDeliveryStructure.loanDetails");
		jsonPaths.put(LoanDetailInfo_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo");
		jsonPaths.put(insurancePolicyDetails_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.insurancePolicyDetails");
		jsonPaths.put(LoanMI_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.insurancePolicyDetails.loanMI");
		jsonPaths.put(Loan_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loan");
		jsonPaths.put(LoanAcquisitionDetails_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanAcquisitionDetails");
		jsonPaths.put(LoanAcquisition_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanAcquisitionDetails.loanAcquisition");
		jsonPaths.put(LoanApplicationDetails_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanApplicationDetails");
		jsonPaths.put(LoanApplication_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanApplicationDetails.loanApplication");
		jsonPaths.put(LoanConversionRuleDetails_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanConversionRuleDetails");
		jsonPaths.put(LoanConversionRule_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanConversionRuleDetails.loanConversionRule");
		jsonPaths.put(LoanOriginationDetails_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails");
		jsonPaths.put(LoanOrigination_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanOrigination");
		jsonPaths.put(LoanUnderwritingDecision_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanUnderwritingDecision");
		jsonPaths.put(LoanRiskAssessments_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments");
		jsonPaths.put(LoanRiskAssessmentDetails_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails");
		jsonPaths.put(LoanCollateralRiskAssessments_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments");
		jsonPaths.put(LoanCollateralRiskAssessment_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[0].loanCollateralRiskAssessment");
		jsonPaths.put(LoanCollateralRiskAssessment_Object2,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[1].loanCollateralRiskAssessment");
		jsonPaths.put(Program_Object,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.programs[0].program");
		jsonPaths.put(Program_Object2,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.programs[1].program");
		jsonPaths.put(LoanIdentifications_Object,"$.loanDeliveryStructure.loanDetails.loanIdentifications");
		jsonPaths.put(LoanPropertyCollaterals_Object,"$.loanDeliveryStructure.loanDetails.loanPropertyCollaterals");
		jsonPaths.put(LoanPropertyCollateralDetails_Object,"$.loanDeliveryStructure.loanDetails.loanPropertyCollaterals.loanPropertyCollateralDetails");
		jsonPaths.put(LoanPropertyCollateral_Object,"$.loanDeliveryStructure.loanDetails.loanPropertyCollaterals.loanPropertyCollateralDetails.loanPropertyCollateral");
		jsonPaths.put(PartyRoles_Object,"$.loanDeliveryStructure.loanDetails.partyRoles");
		jsonPaths.put(ProductDetails_Object,"$.loanDeliveryStructure.loanDetails.productDetails");
		jsonPaths.put(ProductDefinition_Object,"$.loanDeliveryStructure.loanDetails.productDetails.productDefinition");
		jsonPaths.put(PropertyDetails_Object,"$.loanDeliveryStructure.loanDetails.propertyDetails");
		jsonPaths.put(AddressDetails_Object,"$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails");
		jsonPaths.put(Property_Object,"$.loanDeliveryStructure.loanDetails.propertyDetails.property");
		jsonPaths.put(PropertyRights_Object,"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyRights");
		jsonPaths.put(PropertyTitles_Object,"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyTitles");
		jsonPaths.put(PropertyValuations_Object,"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations");
		jsonPaths.put(Appraisal_Object,"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.appraisal");
		jsonPaths.put(PropertyValuationDetails_Object,"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails");
		jsonPaths.put(RealEstateProject_Object,"$.loanDeliveryStructure.loanDetails.propertyDetails.realEstateProject");
		jsonPaths.put(StructureDetails_Object,"$.loanDeliveryStructure.loanDetails.propertyDetails.structureDetails");
		jsonPaths.put(Structure_Object,"$.loanDeliveryStructure.loanDetails.propertyDetails.structureDetails.structure");
		jsonPaths.put(ServiceRequestDetails_Object,"$.serviceRequestDetails");
		jsonPaths.put(CreditRiskDerivation_Array,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation");
		jsonPaths.put(LoanCollateralRiskAssessmentDetails_Array,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails");
		jsonPaths.put(Programs_Array,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.programs");
		jsonPaths.put(LoanIdentification_Array,"$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification");
		jsonPaths.put(PartyRole_Array,"$.loanDeliveryStructure.loanDetails.partyRoles.partyRole");
		jsonPaths.put(Address_Array,"$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address");
		jsonPaths.put(PropertyValuation_Array,"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation");
		jsonPaths.put(SingleFamilyLoanBorrower_Array,"$.loanDeliveryStructure.loanDetails.singleFamilyLoanBorrower");
		
		jsonPaths.put(CreditRiskDerivation_ArrayElementObject,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[0]");
		jsonPaths.put(CreditRiskDerivation_ArrayElementObject2,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[1]");
		jsonPaths.put(LoanCollateralRiskAssessmentDetails_ArrayElementObject,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[0]");
		jsonPaths.put(LoanCollateralRiskAssessmentDetails_ArrayElementObject2,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[1]");
		jsonPaths.put(Programs_ArrayElementObject,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.programs[0]");
		jsonPaths.put(Programs_ArrayElementObject2,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.programs[1]");
		jsonPaths.put(LoanIdentification_ArrayElementObject_LPKey,"$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorKey\")]");
		jsonPaths.put(LoanIdentification_ArrayElementObject_LPUL,"$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorUniqueLoan\")]");
		jsonPaths.put(LoanIdentification_ArrayElementObject_LPT,"$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorTransaction\")]");
		jsonPaths.put(LoanIdentification_ArrayElementObject_HPGT,"$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"HewlettPackardGeneratedTransaction\")]");
		jsonPaths.put(LoanIdentification_ArrayElementObject_HPLPT,"$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"HewlettPackardLoanProspectorTransaction\")]");
		jsonPaths.put(LoanIdentification_ArrayElementObject_LQA,"$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanQualityAdvisor\")]");
		jsonPaths.put(LoanIdentification_ArrayElementObject_SLS,"$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"SubmissionLinkService\")]");
		jsonPaths.put(PartyRole_ArrayElementObject,"$.loanDeliveryStructure.loanDetails.partyRoles.partyRole[0]");
		jsonPaths.put(Address_ArrayElementObject,"$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0]");
		jsonPaths.put(Address_ArrayElementObject_PreScrub,"$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[1]");
		jsonPaths.put(PropertyValuation_ArrayElementObject,"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[0]");
		jsonPaths.put(PropertyValuation_ArrayElementObject_EST_O,"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"3\")]");
		jsonPaths.put(PropertyValuation_ArrayElementObject_EST_S,"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"4\")]");
		jsonPaths.put(PropertyValuation_ArrayElementObject_FR,"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[?(@.propertyValuationSequenceNumber == \"7\")]");
		jsonPaths.put(SingleFamilyLoanBorrower_ArrayElementObject,"$.loanDeliveryStructure.loanDetails.singleFamilyLoanBorrower[0]");
		jsonPaths.put(SingleFamilyLoanBorrower_ArrayElementObject2,"$.loanDeliveryStructure.loanDetails.singleFamilyLoanBorrower[1]");

		jsonPaths.put(PropertyValuationAssessment_Object,"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuationAssessment");
		jsonPaths.put(HVEOptionType,"$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuationAssessment.homeValueExplorerOptionType");
		
		jsonPaths.put(CRACommunicationFailureDerivation_Object,"$.loanDeliveryStructure.loanDetails.communicationFailureDerivedDetails.craCommunicationFailureDerivation");
		jsonPaths.put(RDSCommunicationFailureDerivation_Object,"$.loanDeliveryStructure.loanDetails.communicationFailureDerivedDetails.rdsCommunicationFailureDerivation");
		jsonPaths.put(CRACommunicationFailureType,"$.loanDeliveryStructure.loanDetails.communicationFailureDerivedDetails.craCommunicationFailureDerivation.craCommunicationFailureType");
		jsonPaths.put(RDSCommunicationFailureType,"$.loanDeliveryStructure.loanDetails.communicationFailureDerivedDetails.rdsCommunicationFailureDerivation.rdsCommunicationFailureType");
	}
	
	public String getElementJSONPath(String elementName) {
		return jsonPaths.get(elementName);
	}
	
	public String getACEAPI20DBElementPath(String elementName, String collectionName) {
		String elementPath=getElementJSONPath(elementName);
		if(elementPath == null) {
			System.out.println("**JSON path not found for element: "+elementName);
			return null;
		}
		switch(collectionName) {
		case ACEAPI20DBDocuments.collectionACE20Requests:
			elementPath=elementPath.replace("$.", "$.automatedCollateralEvaluationRequest.");
			break;
		case ACEAPI20DBDocuments.collectionACE20Responses:
			elementPath=elementPath.replace("$.", "$.automatedCollateralEvaluationResponse.");
			elementPath=elementPath.replace("loanDeliveryStructure.loanDetails.", "");
			break;
		}
		if(elementName.startsWith("FIPS")) {
			elementPath=elementPath.replace("FIPS", "fIPS");
		}
		return elementPath;
	}
	
	public String getDBQueryElementPath(String elementName, String collectionName) {
		String elementPath=getElementJSONPath(elementName);
		if(elementPath == null) {
			return null;
		}
		if(elementPath.contains(".contextMap.")) {
			elementPath=elementPath.replace("$.", "");
			return elementPath;
		}
		elementPath=elementPath.replace("$.", "automatedCollateralEvaluationRequest.");
		elementPath=elementPath.replace("[", ".");
		elementPath=elementPath.replace("]", "");
		
		return elementPath;
	}

	public String getResponseElementJSONPath(String elementName) {
		String elementPath=getElementJSONPath(elementName);
		if(elementPath == null) {
			System.out.println("**JSON path not found for element: "+elementName);
			return null;
		}
		if(elementPath.startsWith("$.loanDeliveryStructure.loanDetails.")) {
			elementPath=elementPath.replace("$.loanDeliveryStructure.loanDetails.", "$.");
		}
		return elementPath;
	}
}
