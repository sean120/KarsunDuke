package mongoCollections;

import java.util.HashMap;

public class ACEFULLAssessmentRequests {
	
	public String returnAceRequestJsonPath(String elementName) {
		HashMap<String, String> elementJsonPath = new HashMap<String, String>();
		elementJsonPath.put(("addressMatchLevelType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].addressMatchLevelType");		
		elementJsonPath.put(("addressSourceType").toLowerCase(), "$.automatedCollateralEvaluationRequestx.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].addressSourceType");		
	
		elementJsonPath.put(("addressLineText").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].addressLineText");
		elementJsonPath.put(("cityName").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].cityName");
		elementJsonPath.put(("stateCode").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].stateCode");
		elementJsonPath.put(("postalCode").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].postalCode");
		elementJsonPath.put(("zipPlusFourCode").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].zipPlusFourCode");
		elementJsonPath.put(("loanIdentifier1").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[0].loanIdentifier");	
		elementJsonPath.put(("loanIdentifier2").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[1].loanIdentifier");	
		elementJsonPath.put(("loanIdentifier3").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[2].loanIdentifier");	
		elementJsonPath.put(("loanIdentifier4").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[3].loanIdentifier");	
		elementJsonPath.put(("loanIdentifier5").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[4].loanIdentifier");	
		elementJsonPath.put(("loanIdentifier6").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[5].loanIdentifier");			
		elementJsonPath.put(("loanIdentifierType1").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[0].loanIdentifierType");
		elementJsonPath.put(("loanIdentifierType2").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[1].loanIdentifierType");
		elementJsonPath.put(("loanIdentifierType3").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[2].loanIdentifierType");
		elementJsonPath.put(("loanIdentifierType4").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[3].loanIdentifierType");
		elementJsonPath.put(("loanIdentifierType5").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[4].loanIdentifierType");
		elementJsonPath.put(("loanIdentifierType6").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[5].loanIdentifierType");
		elementJsonPath.put(("partyRoleIdentifier").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.partyRoles.partyRole[0].partyRoleIdentifier");
		elementJsonPath.put(("partyRoleType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.singleFamilyLoanBorrower[0].partyRoleType");	
		elementJsonPath.put(("censusTractBaseIdentifier").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].censusTractBaseIdentifier");
		elementJsonPath.put(("coreBasedStatisticalAreaCode").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].coreBasedStatisticalAreaCode");
		elementJsonPath.put(("FIPSStateNumericCode").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].fIPSStateNumericCode");
		elementJsonPath.put(("latitudeNumber").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].latitudeNumber");
		elementJsonPath.put(("longitudeNumber").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].longitudeNumber");
		elementJsonPath.put(("FIPSCountyThreeDigitCode").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].fIPSCountyThreeDigitCode");
		elementJsonPath.put(("geocodingIndicator").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].geocodingIndicator");
		
		elementJsonPath.put(("AccountIdentifier").toLowerCase(), "$");
		elementJsonPath.put(("RequestType").toLowerCase(), "$");
		elementJsonPath.put(("ClientSchemaVersionIdentifier").toLowerCase(), "$");
		elementJsonPath.put(("RequestorIdentifier").toLowerCase(), "$");
		elementJsonPath.put(("SchemaVersionIdentifier").toLowerCase(), "$");
		elementJsonPath.put(("ServiceName").toLowerCase(), "$.automatedCollateralEvaluationRequest.serviceRequestDetails.serviceName");
		elementJsonPath.put(("ServiceRequestOperationName").toLowerCase(), "$.automatedCollateralEvaluationRequest.serviceRequestDetails.serviceRequestOperationName");
		
		elementJsonPath.put(("grandfatheringIndicator").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.grandfatheringIndicator");
		elementJsonPath.put(("addressUnitDesignatorType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].addressUnitDesignatorType");
		elementJsonPath.put(("addressUnitIdentifier").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].addressUnitIdentifier");
		elementJsonPath.put(("highwayContractIdentifier").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].highwayContractIdentifier");
		elementJsonPath.put(("postalCode").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].postalCode");
		elementJsonPath.put(("postOfficeBoxIdentifier").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].postOfficeBoxIdentifier");
		elementJsonPath.put(("ruralRouteIdentifier").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].ruralRouteIdentifier");
		elementJsonPath.put(("stateCode").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].stateCode");
		elementJsonPath.put(("streetName").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].streetName");
		elementJsonPath.put(("carrierRouteType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].carrierRouteType");
		elementJsonPath.put(("streetPostDirectionalText").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].streetPostDirectionalText");
			      
		elementJsonPath.put(("craCommunicationFailureType").toLowerCase(), "$.automatedCollateralEvaluationRequest");
		elementJsonPath.put(("streetPreDirectionalText").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].streetPreDirectionalText");
		elementJsonPath.put(("streetPrimaryNumberText").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].streetPrimaryNumberText");
		elementJsonPath.put(("streetSuffixText").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].streetSuffixText");
		elementJsonPath.put(("zipPlusFourCode").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails.address[0].zipPlusFourCode");
		elementJsonPath.put(("loanIdentifier1").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[0].loanIdentifier");
		elementJsonPath.put(("loanIdentifier2").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[1].loanIdentifier");
		elementJsonPath.put(("loanIdentifier3").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[2].loanIdentifier");
		elementJsonPath.put(("loanIdentifier4").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[3].loanIdentifier");
		elementJsonPath.put(("loanIdentifier5").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[4].loanIdentifier");
		elementJsonPath.put(("loanIdentifier6").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[5].loanIdentifier");
		elementJsonPath.put(("loanIdentifier7").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[6].loanIdentifier");
		elementJsonPath.put(("appraisalIdentifier").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.appraisal.appraisalIdentifier");
		elementJsonPath.put(("derivedCalculationSourceType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation.[0].derivedCalculationSourceType");
		elementJsonPath.put(("loanQualityAdvisorResultType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[0].loanQualityAdvisorResultType");
		elementJsonPath.put(("strategicOfferingCreditRiskType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[0].strategicOfferingCreditRiskType");
		elementJsonPath.put(("messageCode").toLowerCase(), "$..messageCode");
		elementJsonPath.put(("messageText").toLowerCase(), "$..messageText");
		elementJsonPath.put(("constructionLoanIndicator").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.constructionLoanIndicator");
		elementJsonPath.put(("loanProcessingStageType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.loanProcessingStageType");
		elementJsonPath.put(("loanPurposeType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.loanPurposeType");
		elementJsonPath.put(("loanRefinanceCashOutDeterminationType").toLowerCase(), "$.automatedCollateralEvaluationRequestx.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.loanRefinanceCashOutDeterminationType");
		elementJsonPath.put(("mortgageType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.mortgageType");
		elementJsonPath.put(("loanConformityType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanAcquisitionDetails.loanAcquisition.loanConformityType");
		elementJsonPath.put(("sellerAccountIdentifier").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanAcquisitionDetails.loanAcquisition.sellerAccountIdentifier");
		elementJsonPath.put(("sellerLoanIdentifier").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanAcquisitionDetails.loanAcquisition.sellerLoanIdentifier");
		elementJsonPath.put(("borrowerPropertyPurchasePriceAmount").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanApplicationDetails.loanApplication.borrowerPropertyPurchasePriceAmount");		
		elementJsonPath.put(("loanApplicationBaseLoanAmount").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanApplicationDetails.loanApplication.loanApplicationBaseLoanAmount");
		elementJsonPath.put(("miAndFundingFeeFinancedAmount").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanApplicationDetails.loanApplication.miAndFundingFeeFinancedAmount");
		elementJsonPath.put(("loanScopeType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[0].loanCollateralRiskAssessment.loanScopeType");
		elementJsonPath.put(("ltvRatioPercent").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[0].loanCollateralRiskAssessment.ltvRatioPercent");
		elementJsonPath.put(("ltvSourceType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[0].loanCollateralRiskAssessment.ltvSourceType");
		elementJsonPath.put(("constructionLoanType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanConversionRuleDetails.loanConversionRule.constructionLoanType");
		elementJsonPath.put(("miPremiumFinancedAmount").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.insurancePolicyDetails.loanMI.miPremiumFinancedAmount");		
		elementJsonPath.put(("miPremiumFinancedIndicator").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.insurancePolicyDetails.loanMI.miPremiumFinancedIndicator");			
		elementJsonPath.put(("investorCollateralProgramType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanOrigination.investorCollateralProgramType");
		elementJsonPath.put(("loanOriginationSystemVersionIdentifier").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanOrigination.loanOriginationSystemVersionIdentifier");
		elementJsonPath.put(("loanPropertyUsageType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanOrigination.loanPropertyUsageType");
		elementJsonPath.put(("noteAmount").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanOrigination.noteAmount");
		elementJsonPath.put(("financedUnitCount").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanPropertyCollaterals.loanPropertyCollateralDetails.loanPropertyCollateral.financedUnitCount");
		elementJsonPath.put(("automatedUnderwritingSystemType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanUnderwritingDecision.automatedUnderwritingSystemType");
		elementJsonPath.put(("loanUnderwritingCaseIdentifier").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanUnderwritingDecision.loanUnderwritingCaseIdentifier");		
		elementJsonPath.put(("loanUnderwritingDecisionDefaultProbabilityRate").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanUnderwritingDecision.loanUnderwritingDecisionDefaultProbabilityRate");
		elementJsonPath.put(("productDescription").toLowerCase(), "$..productDescription");
		elementJsonPath.put(("productIdentifier").toLowerCase(), "$..productIdentifier");
		elementJsonPath.put(("productType").toLowerCase(), "$..productType");
		elementJsonPath.put(("programType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.programs[0].program.programType");
		elementJsonPath.put(("refinanceProgramType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.loanDetailInfo.programs[0].program.refinanceProgramType");
		elementJsonPath.put(("IntentToOccupyType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.singleFamilyLoanBorrower[0].intentToOccupyType");		
		elementJsonPath.put(("propertyCategoryType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.property.propertyCategoryType");
		elementJsonPath.put(("propertyEstateType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyTitles.propertyEstateType");
		elementJsonPath.put(("propertyIdentifier1").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[0].propertyIdentifier");
		elementJsonPath.put(("propertyIdentifier2").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[1].propertyIdentifier");
		elementJsonPath.put(("propertyIdentifier3").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[2].propertyIdentifier");
		elementJsonPath.put(("propertyIdentifier4").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[3].propertyIdentifier");
		elementJsonPath.put(("propertyIdentifier5").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[4].propertyIdentifier");
		
		elementJsonPath.put(("propertyValuationAmount1").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[0].propertyValuationAmount");
		elementJsonPath.put(("propertyValuationAmount2").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[1].propertyValuationAmount");
		elementJsonPath.put(("propertyValuationAmount3").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[2].propertyValuationAmount");
		elementJsonPath.put(("propertyValuationAmount4").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[3].propertyValuationAmount");
		elementJsonPath.put(("propertyValuationAmount5").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[4].propertyValuationAmount");
//		elementJsonPath.put(("propertyValuationEffectiveDateTime").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[0].propertyValuationEffectiveDateTime");
		elementJsonPath.put(("propertyValuationMethodType1").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[1].propertyValuationMethodType");
		elementJsonPath.put(("propertyValuationMethodType2").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[1].propertyValuationMethodType");
		elementJsonPath.put(("propertyValuationMethodType3").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[2].propertyValuationMethodType");
		elementJsonPath.put(("propertyValuationMethodType4").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[3].propertyValuationMethodType");
		elementJsonPath.put(("propertyValuationMethodType5").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[4].propertyValuationMethodType");
		
		elementJsonPath.put(("propertyValuationSequenceNumber1").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[0].propertyValuationSequenceNumber");
		elementJsonPath.put(("propertyValuationSequenceNumber2").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[1].propertyValuationSequenceNumber");
		elementJsonPath.put(("propertyValuationSequenceNumber3").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[2].propertyValuationSequenceNumber");
		elementJsonPath.put(("propertyValuationSequenceNumber4").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[3].propertyValuationSequenceNumber");
		elementJsonPath.put(("propertyValuationSequenceNumber5").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[4].propertyValuationSequenceNumber");
		
		elementJsonPath.put(("propertyValuationType1").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[0].propertyValuationType");
		elementJsonPath.put(("propertyValuationType2").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[1].propertyValuationType");
		elementJsonPath.put(("propertyValuationType3").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[2].propertyValuationType");
		elementJsonPath.put(("propertyValuationType4").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[3].propertyValuationType");
		elementJsonPath.put(("propertyValuationType5").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails.propertyValuation[4].propertyValuationType");
		
		
		elementJsonPath.put(("rdsCommunicationFailureType").toLowerCase(), "$..rdsCommunicationFailureType");		
		elementJsonPath.put(("projectAttachmentType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.realEstateProject.projectAttachmentType");
		elementJsonPath.put(("projectLegalStructureType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.realEstateProject.projectLegalStructureType");
		elementJsonPath.put(("serviceName").toLowerCase(), "$.automatedCollateralEvaluationRequest.serviceRequestDetails.serviceName");
		elementJsonPath.put(("serviceRequestOperationName").toLowerCase(), "$.automatedCollateralEvaluationRequest.serviceRequestDetails.serviceRequestOperationName");
        elementJsonPath.put(("subscriberIdentifier").toLowerCase(), "$.automatedCollateralEvaluationRequest.serviceRequestDetails.subscriberIdentifier");
		elementJsonPath.put(("subscriberRequestCorrelationIdentifier").toLowerCase(), "$.automatedCollateralEvaluationRequest.serviceRequestDetails.subscriberRequestCorrelationIdentifier");
		elementJsonPath.put(("constructionMethodType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.structureDetails.structure.constructionMethodType");
		elementJsonPath.put(("propertyRightsType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.propertyRights.propertyRightsType");
		
		
		elementJsonPath.put(("borrowerClassificationType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.singleFamilyLoanBorrower[0].borrowerClassificationType");
		elementJsonPath.put(("borrowerIdentifier").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.singleFamilyLoanBorrower[0].borrowerIdentifier");
		
		elementJsonPath.put(("projectClassificationType").toLowerCase(), "$.automatedCollateralEvaluationRequest.loanDeliveryStructure.loanDetails.propertyDetails.realEstateProject.projectClassificationType");
		
		
		String messageText = elementJsonPath.get(elementName.toLowerCase());
		return messageText;
		
	}
	
	public String returnAceResponseXpath(String elementName) {
		HashMap<String, String> elementJsonPath = new HashMap<String, String>();
 
		elementJsonPath.put(("propertyValuationEffectiveDateTime").toLowerCase(), "$.aceAssessmentDecision.propertyValuationEffectiveDateTime");
		elementJsonPath.put(("alternateAppraisalDecisionExpirationDatetime").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalDecisionExpirationDatetime");
		elementJsonPath.put(("alternateAppraisalDecisionStatusType").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalDecisionStatusType");
		elementJsonPath.put(("alternateAppraisalEligibilityDecision").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalEligibilityDecision");
		elementJsonPath.put(("minimumLoanAssessmentFormType").toLowerCase(), "$.aceAssessmentDecision.minimumLoanAssessmentFormType");
		elementJsonPath.put(("listText5").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalReasonList[0].listText");
		elementJsonPath.put(("listText6").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalReasonList[1].listText");
		elementJsonPath.put(("listText7").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalReasonList[2].listText");
		elementJsonPath.put(("listText8").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalReasonList[3].listText");
		elementJsonPath.put(("listText9").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalReasonList[4].listText");
		elementJsonPath.put(("listText10").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalReasonList[5].listText");
		elementJsonPath.put(("listText11").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalReasonList[6].listText");
		elementJsonPath.put(("listText12").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalReasonList[7].listText");
		elementJsonPath.put(("listText13").toLowerCase(), "$.aceAssessmentDecision.alternateAppraisalReasonList[8].listText");
		elementJsonPath.put(("error").toLowerCase(), "$.errors.error.errorResponse.details.error");
		elementJsonPath.put(("code").toLowerCase(), "$.errors.error.errorResponse.code");
		elementJsonPath.put(("message").toLowerCase(), "$.errors.error.errorResponse.message");
		elementJsonPath.put(("loanIdentifier1").toLowerCase(), "$.loanIdentifications.loanIdentification[0].loanIdentifier");
		elementJsonPath.put(("loanIdentifier2").toLowerCase(), "$.loanIdentifications.loanIdentification[1].loanIdentifier");
		elementJsonPath.put(("loanIdentifier3").toLowerCase(), "$.loanIdentifications.loanIdentification[2].loanIdentifier");
		elementJsonPath.put(("loanIdentifier4").toLowerCase(), "$.loanIdentifications.loanIdentification[3].loanIdentifier");
		elementJsonPath.put(("loanIdentifier5").toLowerCase(), "$.loanIdentifications.loanIdentification[4].loanIdentifier");
		elementJsonPath.put(("loanIdentifier6").toLowerCase(), "$.loanIdentifications.loanIdentification[5].loanIdentifier");
		elementJsonPath.put(("loanIdentifier7").toLowerCase(), "$.loanIdentifications.loanIdentification[6].loanIdentifier");
		elementJsonPath.put(("loanIdentifier8").toLowerCase(), "$.loanIdentifications.loanIdentification[7].loanIdentifier");
		elementJsonPath.put(("loanIdentifier9").toLowerCase(), "$.loanIdentifications.loanIdentification[8].loanIdentifier");
		
		elementJsonPath.put(("loanIdentifierType").toLowerCase(), "$.loanIdentifications.loanIdentification[0].loanIdentifierType");
		elementJsonPath.put(("messageListText1").toLowerCase(), "$.aceDecisionMessages.messages[0].messageCodeList[0].listText");
		elementJsonPath.put(("messageListText2").toLowerCase(), "$.aceDecisionMessages.messages[0].messageGroup1List[0].listText");
		elementJsonPath.put(("messageListText3").toLowerCase(), "$.aceDecisionMessages.messages[0].messageGroup2List[0].listText");
		elementJsonPath.put(("messageListText4").toLowerCase(), "$.aceDecisionMessages.messages[0].messageHeaderList[0].listText");
		elementJsonPath.put(("messageListText5").toLowerCase(), "$.aceDecisionMessages.messages[1].messageCodeList[0].listText");
		elementJsonPath.put(("messageListText6").toLowerCase(), "$.aceDecisionMessages.messages[1].messageGroup1List[0].listText");
		elementJsonPath.put(("messageListText7").toLowerCase(), "$.aceDecisionMessages.messages[1].messageGroup2List[0].listText");
		elementJsonPath.put(("messageListText8").toLowerCase(), "$.aceDecisionMessages.messages[1].messageHeaderList[0].listText");
		elementJsonPath.put(("messageListText9").toLowerCase(), "$.aceDecisionMessages.messages[2].messageCodeList[0].listText");
		elementJsonPath.put(("messageListText10").toLowerCase(), "$.aceDecisionMessages.messages[2].messageGroup1List[0].listText");
		elementJsonPath.put(("messageListText11").toLowerCase(), "$.aceDecisionMessages.messages[2].messageGroup2List[0].listText");
		elementJsonPath.put(("messageListText12").toLowerCase(), "$.aceDecisionMessages.messages[2].messageHeaderList[0].listText");
		elementJsonPath.put(("messageListText13").toLowerCase(), "$.aceDecisionMessages.messages[3].messageCodeList[0].listText");
		elementJsonPath.put(("messageListText14").toLowerCase(), "$.aceDecisionMessages.messages[3].messageGroup1List[0].listText");
		elementJsonPath.put(("messageListText15").toLowerCase(), "$.aceDecisionMessages.messages[3].messageGroup2List[0].listText");
		elementJsonPath.put(("messageListText16").toLowerCase(), "$.aceDecisionMessages.messages[3].messageHeaderList[0].listText");
																		

		elementJsonPath.put(("messageCategory1").toLowerCase(), "$.aceDecisionMessages.messages[0].messageCategory");
		elementJsonPath.put(("messageDisplayFlag1").toLowerCase(), "$.aceDecisionMessages.messages[0].messageDisplayFlag");
		elementJsonPath.put(("messageId1").toLowerCase(), "$.aceDecisionMessages.messages[0].messageId");
		elementJsonPath.put(("messageType1").toLowerCase(), "$.aceDecisionMessages.messages[0].messageType");
		elementJsonPath.put(("sequenceNumber1").toLowerCase(), "$.aceDecisionMessages.messages[0].sequenceNumber");
		elementJsonPath.put(("messageCategory2").toLowerCase(), "$.aceDecisionMessages.messages[1].messageCategory");
		elementJsonPath.put(("messageDisplayFlag2").toLowerCase(), "$.aceDecisionMessages.messages[1].messageDisplayFlag");
		elementJsonPath.put(("messageId2").toLowerCase(), "$.aceDecisionMessages.messages[1].messageId");
		elementJsonPath.put(("messageType2").toLowerCase(), "$.aceDecisionMessages.messages[1].messageType");
		elementJsonPath.put(("sequenceNumber2").toLowerCase(), "$.aceDecisionMessages.messages[1].sequenceNumber");
		elementJsonPath.put(("messageCategory3").toLowerCase(), "$.aceDecisionMessages.messages[2].messageCategory");
		elementJsonPath.put(("messageDisplayFlag3").toLowerCase(), "$.aceDecisionMessages.messages[2].messageDisplayFlag");
		elementJsonPath.put(("messageId3").toLowerCase(), "$.aceDecisionMessages.messages[2].messageId");
		elementJsonPath.put(("messageType3").toLowerCase(), "$.aceDecisionMessages.messages[2].messageType");
		elementJsonPath.put(("sequenceNumber3").toLowerCase(), "$.aceDecisionMessages.messages[2].sequenceNumber");
		elementJsonPath.put(("messageCategory4").toLowerCase(), "$.aceDecisionMessages.messages[3].messageCategory");
		elementJsonPath.put(("messageDisplayFlag4").toLowerCase(), "$.aceDecisionMessages.messages[3].messageDisplayFlag");
		elementJsonPath.put(("messageId4").toLowerCase(), "$.aceDecisionMessages.messages[3].messageId");
		elementJsonPath.put(("messageType4").toLowerCase(), "$.aceDecisionMessages.messages[3].messageType");
		elementJsonPath.put(("sequenceNumber4").toLowerCase(), "$.aceDecisionMessages.messages[3].sequenceNumber");


		String messageText = elementJsonPath.get(elementName.toLowerCase());
		return messageText;
		
	}

}
