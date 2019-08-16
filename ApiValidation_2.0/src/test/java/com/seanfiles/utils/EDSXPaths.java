package com.seanfiles.utils;

import java.util.HashMap;

public class EDSXPaths {

	public String returnEDSReqXpaths(String requestElement) {
		HashMap<String, String> EDSRequestXpath = new HashMap<String, String>();
		
		//..EDS Request Elements
		EDSRequestXpath.put(("AddressLineText").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.AddressLineText");
		EDSRequestXpath.put(("AddressUnitDesignatorType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.AddressUnitDesignatorType");
		EDSRequestXpath.put(("AddressUnitIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.AddressUnitIdentifier");
		EDSRequestXpath.put(("HighwayContractIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.HighwayContractIdentifier");
		EDSRequestXpath.put(("PostalCode").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.PostalCode");
		EDSRequestXpath.put(("PostOfficeBoxIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.PostOfficeBoxIdentifier");
		EDSRequestXpath.put(("RuralRouteIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.RuralRouteIdentifier");
		EDSRequestXpath.put(("FIPSStateAlphaCode").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.FIPSStateAlphaCode");
		EDSRequestXpath.put(("StreetName").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.StreetName");
		EDSRequestXpath.put(("StreetPostDirectionalText").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.StreetPostDirectionalText");
		EDSRequestXpath.put(("StreetPreDirectionalText").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.StreetPreDirectionalText");
		EDSRequestXpath.put(("StreetPrimaryNumberText").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.StreetPrimaryNumberText");
		EDSRequestXpath.put(("StreetSuffixText").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.StreetSuffixText");
		EDSRequestXpath.put(("ZipPlusFourCode").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.ZipPlusFourCode");
		EDSRequestXpath.put(("AlternateLoanIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.AlternativeLoanIdentifications.AlternativeLoanIdentification.AlternateLoanIdentifier");
//		EDSRequestXpath.put(("AlternateLoanIdentifier2").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.AlternativeLoanIdentifications.AlternativeLoanIdentification.AlternateLoanIdentifier[1]");
//		EDSRequestXpath.put(("AlternateLoanIdentifier3").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.AlternativeLoanIdentifications.AlternativeLoanIdentification.AlternateLoanIdentifier[2]");
//		EDSRequestXpath.put(("AlternateLoanIdentifier4").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.AlternativeLoanIdentifications.AlternativeLoanIdentification.AlternateLoanIdentifier[3]");
//		EDSRequestXpath.put(("AlternateLoanIdentifier5").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.AlternativeLoanIdentifications.AlternativeLoanIdentification.AlternateLoanIdentifier[4]");
//		EDSRequestXpath.put(("AlternateLoanIdentifier6").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.AlternativeLoanIdentifications.AlternativeLoanIdentification.AlternateLoanIdentifier[5]");
//		EDSRequestXpath.put(("AlternateLoanIdentifier7").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.AlternativeLoanIdentifications.AlternativeLoanIdentification.AlternateLoanIdentifier[6]");
		EDSRequestXpath.put(("AppraisalIdentifier1").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyValuations.PropertyValuationContainer.Appraisal[0].AppraisalIdentifier");
		EDSRequestXpath.put(("DerivationRiskClassType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanRiskAssessments.LoanRiskAssessmentContainer.CreditRiskDerivation.DerivationRiskClassType");
		EDSRequestXpath.put(("DerivedCalculationSourceType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanRiskAssessments.LoanRiskAssessmentContainer.CreditRiskDerivation.DerivedCalculationSourceType");
		EDSRequestXpath.put(("LoanQualityAdvisorResultType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.LoanQualityResult.LoanQualityAdvisorResultType");
		EDSRequestXpath.put(("StrategicOfferingCreditRiskType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanRiskAssessments.LoanRiskAssessmentContainer.CreditRiskDerivation.StrategicOfferingCreditRiskType");
		EDSRequestXpath.put(("ConstructionLoanIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.Loan.ConstructionLoanIndicator");
		EDSRequestXpath.put(("LoanProcessingStageType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.Loan.LoanProcessingStageType");
		EDSRequestXpath.put(("LoanPurposeType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.Loan.LoanPurposeType");
		EDSRequestXpath.put(("LoanRefinanceCashOutDeterminationType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.Loan.LoanRefinanceCashOutDeterminationType");
		EDSRequestXpath.put(("MortgageType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.Loan.MortgageType");
		EDSRequestXpath.put(("LoanConformityType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanAcquisitionContainer.LoanAcquisition.LoanConformityType");
		EDSRequestXpath.put(("SellerAccountIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanAcquisitionContainer.LoanAcquisition.SellerAccountIdentifier");
		EDSRequestXpath.put(("SellerLoanIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanAcquisitionContainer.LoanAcquisition.SellerLoanIdentifier");
		EDSRequestXpath.put(("BorrowerPropertyPurchasePriceAmount").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanApplications.LoanApplicationContainer.LoanApplication.BorrowerPropertyPurchasePriceAmount");
		EDSRequestXpath.put(("LoanApplicationBaseLoanAmount").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanApplications.LoanApplicationContainer.LoanApplication.LoanApplicationBaseLoanAmount");
		EDSRequestXpath.put(("MIAndFundingFeeFinancedAmount").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanApplications.LoanApplicationContainer.LoanApplication.MIAndFundingFeeFinancedAmount");
		EDSRequestXpath.put(("LoanScopeType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanRiskAssessments.LoanRiskAssessmentContainer.LoanCollateralRiskAssessments.LoanCollateralRiskAssessmentContainer.LoanCollateralRiskAssessment.LoanScopeType");
		EDSRequestXpath.put(("LTVRatioPercent").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanRiskAssessments.LoanRiskAssessmentContainer.LoanCollateralRiskAssessments.LoanCollateralRiskAssessmentContainer.LoanCollateralRiskAssessment.LTVRatioPercent");
		EDSRequestXpath.put(("LTVSourceType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanRiskAssessments.LoanRiskAssessmentContainer.LoanCollateralRiskAssessments.LoanCollateralRiskAssessmentContainer.LoanCollateralRiskAssessment.LTVSourceType");
		EDSRequestXpath.put(("ConstructionLoanType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanConversionRules.LoanConversionRuleContainer.LoanConversionRule.ConstructionLoanType");
		EDSRequestXpath.put(("MIPremiumFinancedAmount").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.InsurancePolicyContainer.LoanMI.MIPremiumFinancedAmount");
		EDSRequestXpath.put(("InvestorCollateralProgramType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanOriginationContainer.LoanOrigination.InvestorCollateralProgramType");
		EDSRequestXpath.put(("LoanOriginationSystemVersionIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanOriginationContainer.LoanOrigination.LoanOriginationSystemVersionIdentifier");
		EDSRequestXpath.put(("LoanPropertyUsageType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanOriginationContainer.LoanOrigination.LoanPropertyUsageType");
		EDSRequestXpath.put(("NoteAmount").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanOriginationContainer.LoanOrigination.NoteAmount");
		EDSRequestXpath.put(("FinancedUnitCount").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanPropertyCollaterals.LoanPropertyCollateralContainer.LoanPropertyCollateral.FinancedUnitCount");
	   			
														                       	
		EDSRequestXpath.put(("AutomatedUnderwritingSystemType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanOriginationContainer.LoanUnderwritingCases.LoanUnderwritingCaseContainer.LoanUnderwritingCase.AutomatedUnderwritingSystemType");
		EDSRequestXpath.put(("LoanUnderwritingCaseIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanOriginationContainer.LoanUnderwritingCases.LoanUnderwritingCaseContainer.LoanUnderwritingDecisions.LoanUnderwritingDecisionContainer.LoanUnderwritingDecision.LoanUnderwritingCaseIdentifier");
		EDSRequestXpath.put(("LoanUnderwritingDecisionDefaultProbabilityRate").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanOriginationContainer.LoanUnderwritingCases.LoanUnderwritingCaseContainer.LoanUnderwritingDecisions.LoanUnderwritingDecisionContainer.LoanUnderwritingDecision.LoanUnderwritingDecisionDefaultProbabilityRate");
		EDSRequestXpath.put(("ProductDescription").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.ProductContainer.ProductDefinitions.ProductDefinition.ProductDescription");
		EDSRequestXpath.put(("ProductIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.ProductContainer.ProductDefinitions.ProductDefinition.ProductIdentifier");
		EDSRequestXpath.put(("ProductType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.ProductContainer.ProductDefinitions.ProductDefinition.ProductType");
		EDSRequestXpath.put(("ProgramType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.Programs[0].Program.ProgramType");
		EDSRequestXpath.put(("RefinanceProgramType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.Programs[0].Program.RefinanceProgramType");
		EDSRequestXpath.put(("IntentToOccupyType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.Property.IntentToOccupyType");
		EDSRequestXpath.put(("PropertyCategoryType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.Property.PropertyCategoryType");
		EDSRequestXpath.put(("PropertyEstateType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyTitles.PropertyTitle.PropertyEstateType");
		
		EDSRequestXpath.put(("PropertyValuationAmount").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyValuations.PropertyValuationContainer.PropertyValuation.PropertyValuationAmount");
		EDSRequestXpath.put(("PropertyValuationEffectiveDateTime").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyValuations.PropertyAppraiserValuationContainer.PropertyValuation.PropertyValuationEffectiveDateTime");

		EDSRequestXpath.put(("PropertyValuationMethodType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyValuations.PropertyAppraiserValuationContainer.PropertyValuation.PropertyValuationMethodType");
		EDSRequestXpath.put(("PropertyValuationType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyValuations.PropertyValuationContainer.PropertyValuation.PropertyValuationType");
		EDSRequestXpath.put(("RDSCommunicationFailureType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.CommunicationFailureDerivedContainer.RDSCommunicationFailureDerivation.RDSCommunicationFailureType");
		EDSRequestXpath.put(("ProjectAttachmentType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.RealEstateProject.ProjectAttachmentType");
		EDSRequestXpath.put(("ProjectLegalStructureType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.RealEstateProject.ProjectLegalStructureType");
		EDSRequestXpath.put(("ServiceName").toLowerCase(),".EnterpriseDecisionService.ServiceRequestContainer.ServiceRequest.ServiceName");
		EDSRequestXpath.put(("ServiceRequestOperationName").toLowerCase(),".EnterpriseDecisionService.ServiceRequestContainer.ServiceRequest.ServiceRequestOperationName");
		EDSRequestXpath.put(("SubscriberIdentifier").toLowerCase(),".EnterpriseDecisionService.ServiceRequestContainer.ServiceRequest.SubscriberIdentifier");
		EDSRequestXpath.put(("SubscriberRequestCorrelationIdentifier").toLowerCase(),".EnterpriseDecisionService.ServiceRequestContainer.ServiceRequest.SubscriberRequestCorrelationIdentifier");
		EDSRequestXpath.put(("ConstructionMethodType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.StructureContainer.Structure.ConstructionMethodType");
		//
		//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResultMessagesEvaluationResultMessage[*]/EvaluationResultMessageIdentifier
		//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResultMessagesEvaluationResultMessage[*]/EvaluationResultMessageSourceType
		//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResultMessagesEvaluationResultMessage[*]/BusinessDecisionIdentifier
		//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResultMessagesEvaluationResultMessage[*]/EvaluationResultMessageText
		EDSRequestXpath.put(("EvaluationResultMessageCode").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultMessages.EvaluationResultMessage.EvaluationResultMessageCode");
		EDSRequestXpath.put(("EvaluationResultMessageText").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultMessages.EvaluationResultMessage.EvaluationResultMessageText");	
		EDSRequestXpath.put(("EvaluationResultMessageIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultMessages.EvaluationResultMessage.EvaluationResultMessageIdentifier");
		EDSRequestXpath.put(("EvaluationResultMessageSourceType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultMessages.EvaluationResultMessage.EvaluationResultMessageSourceType");
		EDSRequestXpath.put(("BusinessDecisionResultIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResult.BusinessDecisionResultIdentifier");
		EDSRequestXpath.put(("BusinessDecisionIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResult.BusinessDecisionResultIdentifier");
		EDSRequestXpath.put(("BusinessDecisionResultSourceType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResult.BusinessDecisionResultSourceType");
		EDSRequestXpath.put(("EDSSchemaVersionIdentifier").toLowerCase(),".EnterpriseDecisionService.ServiceRequestContainer.ServiceRequest.EDSSchemaVersionIdentifier");
		EDSRequestXpath.put(("LoanIdentifierType").toLowerCase(), ".EnterpriseDecisionService.loanDeliveryStructure.LoanContainer.AlternativeLoanIdentifications.AlternativeLoanIdentification.loanIdentifierType");
		EDSRequestXpath.put(("CarrierRouteType").toLowerCase(),"EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.CarrierRouteType");
		EDSRequestXpath.put(("EvaluationResultMessageCode").toLowerCase(), ".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultMessages.EvaluationResultMessage.EvaluationResultMessageCode");
		EDSRequestXpath.put(("ClientSchemaVersionIdentifier").toLowerCase(),".EnterpriseDecisionService.ServiceRequestContainer.ServiceRequest.ClientSchemaVersionIdentifier");
		EDSRequestXpath.put(("CityName").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.CityName");
		EDSRequestXpath.put(("PropertyValuationSequenceNumber").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyValuations.PropertyAppraiserValuationContainer.PropertyValuation.PropertyValuationSequenceNumber");
		EDSRequestXpath.put(("AddressMatchLevelType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.AddressMatchLevelType");
		EDSRequestXpath.put(("AddressSourceType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.AddressSourceType");
		EDSRequestXpath.put(("ServiceRequestDateTime").toLowerCase(),".EnterpriseDecisionService.ServiceRequestContainer.ServiceRequest.ServiceRequestDateTime");
		EDSRequestXpath.put(("ServiceRequestIdentifier").toLowerCase(),".EnterpriseDecisionService.ServiceRequestContainer.ServiceRequest.ServiceRequestIdentifier");
		EDSRequestXpath.put(("ServiceRequestDateTime").toLowerCase(),".EnterpriseDecisionService.ServiceRequestContainer.ServiceRequest.ServiceRequestDateTime");
		EDSRequestXpath.put(("ServiceRequestType").toLowerCase(),".EnterpriseDecisionService.ServiceRequestContainer.ServiceRequest.ServiceRequestType");
		EDSRequestXpath.put(("LoanRoleType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer[0].LoanDetailContainer.LoanRelationships.LoanRelationship.LoanRoleType");
		EDSRequestXpath.put(("PropertyIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.AddressContainers.AddressContainer.Address.PropertyIdentifier");
		EDSRequestXpath.put(("PropertyAssessmentSourceType").toLowerCase(), ".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyValuations.PropertyAppraiserValuationContainer.PropertyAssessment.PropertyAssessmentSourceType");
		EDSRequestXpath.put(("LoanApplicationIdentifier").toLowerCase(), ".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.LoanStates.LoanStateContainer.LoanDetailContainer.LoanApplications.LoanApplicationContainer.LoanApplication.LoanApplicationIdentifier");
		EDSRequestXpath.put(("StructureIdentifier").toLowerCase(), ".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.StructureContainer.Structure.StructureIdentifier");
		//..HVS Response EDS Request Elements
		EDSRequestXpath.put(("CRACommunicationFailureType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.CommunicationFailureDerivedContainer.CRACommunicationFailureDerivation.CRACommunicationFailureType");	
		
		EDSRequestXpath.put(("HomeValueExplorerForecastStandardDeviationRate").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyValuations.PropertyAppraiserValuationContainer.PropertyValuationAssessment.HomeValueExplorerForecastStandardDeviationRate[0]");	
		EDSRequestXpath.put(("ActiveMLSListingIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.ActiveMLSListingIndicator[0]");
		EDSRequestXpath.put(("AppraisalCompletionAsIsIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.AppraisalCompletionAsIsIndicator[0]");
		EDSRequestXpath.put(("AppraisalExistenceIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.AppraisalExistenceIndicator[0]");
		EDSRequestXpath.put(("ARMsLengthContractIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.ARMsLengthContractIndicator[0]");
		EDSRequestXpath.put(("DaysOnMarketIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.DaysOnMarketIndicator[0]");
		EDSRequestXpath.put(("DistressedIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.DistressedIndicator[0]");
		EDSRequestXpath.put(("HighestBestUseIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.HighestBestUseIndicator[0]");
		EDSRequestXpath.put(("LegalityIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.LegalityIndicator[0]");
		EDSRequestXpath.put(("MissingDataIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.MissingDataIndicator[0]");
		EDSRequestXpath.put(("MultipleListingIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.MultipleListingIndicator[0]");
		EDSRequestXpath.put(("NeighborhoodConformityIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.NeighborhoodConformityIndicator[0]");
		EDSRequestXpath.put(("PhysicalDeficiencyIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.PhysicalDeficiencyIndicator[0]");
		EDSRequestXpath.put(("PriceGrowthThresholdAmount").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.PriceGrowthThresholdAmount[0]");
		EDSRequestXpath.put(("PropertyAgeCount").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.PropertyAgeCount[0]");
		EDSRequestXpath.put(("PropertyConditionIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.PropertyConditionIndicator[0]");
		EDSRequestXpath.put(("PublicRecordExistenceIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.PublicRecordExistenceIndicator[0]");
		EDSRequestXpath.put(("StructureBuildingMaterialQualityRatingType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.StructureContainer.PropertyStructureAnalyses.PropertyStructureAnalysisContainer.PropertyStructureAnalysis.StructureBuildingMaterialQualityRatingType[0]");
		EDSRequestXpath.put(("StructureBuildingMaterialQualityRatingIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.StructureContainer.PropertyStructureAnalyses.PropertyStructureAnalysisContainer.PropertyStructureAnalysisDerivation.StructureBuildingMaterialQualityRatingIdentifier[0]");
		EDSRequestXpath.put(("QualityAgeIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.QualityAgeIndicator[0]");
		EDSRequestXpath.put(("QualityIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.QualityIndicator[0]");
		EDSRequestXpath.put(("HomeValueExplorerAssessmenntDateTime").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyValuations.PropertyAppraiserValuationContainer.PropertyValuationAssessment.HomeValueExplorerAssessmenntDateTime[0]");
		EDSRequestXpath.put(("HomeValueExplorerConfidenceLevelType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyValuations.PropertyAppraiserValuationContainer.PropertyValuationAssessment.HomeValueExplorerConfidenceLevelType[0]");
		EDSRequestXpath.put(("HVEStandardDeviationNumber").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyValuations.PropertyAppraiserValuationContainer.PropertyValuationAssessment.HVEStandardDeviationNumber[0]");
		EDSRequestXpath.put(("HomeValueExplorerMaximumValueAmount").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyValuations.PropertyAppraiserValuationContainer.PropertyValuationAssessment.HomeValueExplorerMaximumValueAmount[0]");
		EDSRequestXpath.put(("HomeValueExplorerMidPointValueEstimateAmount").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyValuations.PropertyAppraiserValuationContainer.PropertyValuationAssessment.HomeValueExplorerMidPointValueEstimateAmount[0]");
		EDSRequestXpath.put(("HomeValueExplorerMinimumValueAmount").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyValuations.PropertyAppraiserValuationContainer.PropertyValuationAssessment.HomeValueExplorerMinimumValueAmount[0]");
		EDSRequestXpath.put(("PropertyValuationEffectiveDateTime").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.PropertyValuations.PropertyAppraiserValuationContainer.PropertyValuationAssessment.PropertyValuationEffectiveDateTime[0]");
		EDSRequestXpath.put(("PropertyLandUseIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.PropertyLandUseIndicator[0]");
		EDSRequestXpath.put(("PropertyPurchaseLotSizeOneAcreIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.PropertyPurchaseLotSizeOneAcreIndicator[0]");
		EDSRequestXpath.put(("PropertyRefinanceLotSizeTwoAcreIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.PropertyRefinanceLotSizeTwoAcreIndicator[0]");
		EDSRequestXpath.put(("PropertyUnitIndicator").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.BusinessDecisions.BusinessDecisionContainer.BusinessDecisionResultContainer.BusinessDecisionResultDetails.BusinessDecisionResultDetailContainer.PropertyInspectionWaiverResult.PropertyUnitIndicator[0]");
		
		
		String edsRequestXpath = EDSRequestXpath.get(requestElement.toLowerCase());
		return edsRequestXpath;
	}
	
	public String returnEDSResXpathsLPA(String responseElement) {
		HashMap<String, String> EDSResponseXpath = new HashMap<String, String>();

		//.. XML input
	//	.. values----------------------------------------------------------------------------------------------------------------------------------------------------
		EDSResponseXpath.put(("AttrVal2").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].AttrValContainer.AttrValWrapper.AttrVal[2]");
		EDSResponseXpath.put(("DecisionVal1").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[6].DecisionValueContainer.DecisionValWrapper[0].DecisionVal");
		EDSResponseXpath.put(("DecisionVal2").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[16].DecisionValueContainer.DecisionValWrapper[0].DecisionVal");
		EDSResponseXpath.put(("DecisionVal3").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[14].DecisionValueContainer.DecisionValWrapper[0].DecisionVal");
		EDSResponseXpath.put(("DecisionVal4").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].DecisionValueContainer.DecisionValWrapper[0].DecisionVal");
		EDSResponseXpath.put(("DecisionVal5").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[13].DecisionValueContainer.DecisionValWrapper[0].DecisionVal");
		EDSResponseXpath.put(("AttrVal1").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].AttrValContainer.AttrValWrapper.AttrVal");
		EDSResponseXpath.put(("AttrKey1").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].AttrValContainer.AttrValWrapper[0].AttrKey");
		EDSResponseXpath.put(("MessageCode1").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[0].MessageCode[0]");
		EDSResponseXpath.put(("MessageGroup1a").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[0].MessageGroup1[0]");
		EDSResponseXpath.put(("MessageGroup2a").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[0].MessageGroup2[0]");
		EDSResponseXpath.put(("MessageHeader1").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[0].MessageHeader[0]");
		EDSResponseXpath.put(("MessageCategory1").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[0].MessageCategory[0]");	
		EDSResponseXpath.put(("MessageCode2").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[1].MessageCode[0]");
		EDSResponseXpath.put(("MessageGroup1b").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[1].MessageGroup1[0]");
		EDSResponseXpath.put(("MessageGroup2b").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[1].MessageGroup2[0]");
		EDSResponseXpath.put(("MessageHeader2").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[1].MessageHeader[0]");
		EDSResponseXpath.put(("MessageCategory2").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[1].MessageCategory[0]");	
		EDSResponseXpath.put(("MessageCode3").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[2].MessageCode[0]");
		EDSResponseXpath.put(("MessageGroup1c").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[2].MessageGroup1[0]");
		EDSResponseXpath.put(("MessageGroup2c").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[2].MessageGroup2[0]");
		EDSResponseXpath.put(("MessageHeader3").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[2].MessageHeader[0]");
		EDSResponseXpath.put(("MessageCategory3").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[2].MessageCategory[0]");	
		EDSResponseXpath.put(("MessageCode4").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[3].MessageCode[0]");
		EDSResponseXpath.put(("MessageGroup1d").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[3].MessageGroup1[0]");
		EDSResponseXpath.put(("MessageGroup2d").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[3].MessageGroup2[0]");
		EDSResponseXpath.put(("MessageHeader4").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[3].MessageHeader[0]");
		EDSResponseXpath.put(("MessageCategory4").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[3].MessageCategory[0]");	
		
		//incorrect xpath for display flag
														
		EDSResponseXpath.put(("DisplayFlag1").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[0].DisplayFlag");
		EDSResponseXpath.put(("MessageId1").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[0].MessageId");
		EDSResponseXpath.put(("MessageType1").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[0].MessageType");
		EDSResponseXpath.put(("SequenceNumber1").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[0].SequenceNumber");
		EDSResponseXpath.put(("DisplayFlag2").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[1].DisplayFlag");
		EDSResponseXpath.put(("MessageId2").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[1].MessageId");
		EDSResponseXpath.put(("MessageType2").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[1].MessageType");
		EDSResponseXpath.put(("SequenceNumber2").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[1].SequenceNumber");
		EDSResponseXpath.put(("DisplayFlag3").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[2].DisplayFlag");
		EDSResponseXpath.put(("MessageId3").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[2].MessageId");
		EDSResponseXpath.put(("MessageType3").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[2].MessageType");
		EDSResponseXpath.put(("SequenceNumber3").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[2].SequenceNumber");
		EDSResponseXpath.put(("DisplayFlag4").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[3].DisplayFlag");
		EDSResponseXpath.put(("MessageId4").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[3].MessageId");
		EDSResponseXpath.put(("MessageType4").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[3].MessageType");
		EDSResponseXpath.put(("SequenceNumber4").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[1].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[3].SequenceNumber");

		String edsResponseXpath = EDSResponseXpath.get(responseElement.toLowerCase());
		return edsResponseXpath;
	}
	
	public String returnEDSResXpaths(String responseElement) {
		HashMap<String, String> EDSResponseXpath = new HashMap<String, String>();

		//.. XML input
	//	.. values----------------------------------------------------------------------------------------------------------------------------------------------------
		EDSResponseXpath.put(("AttrVal2").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].AttrValContainer.AttrValWrapper.AttrVal[2]");
		EDSResponseXpath.put(("DecisionVal1").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].DecisionContainer.Decision[6].DecisionValueContainer.DecisionValWrapper[0].DecisionVal");
		EDSResponseXpath.put(("DecisionVal2").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].DecisionContainer.Decision[16].DecisionValueContainer.DecisionValWrapper[0].DecisionVal");
		EDSResponseXpath.put(("DecisionVal3").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].DecisionContainer.Decision[15].DecisionValueContainer.DecisionValWrapper[0].DecisionVal");
		EDSResponseXpath.put(("DecisionVal4").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].DecisionContainer.Decision[18].DecisionValueContainer.DecisionValWrapper[0].DecisionVal");
		EDSResponseXpath.put(("DecisionVal5").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].DecisionContainer.Decision[14].DecisionValueContainer.DecisionValWrapper[0].DecisionVal");
		EDSResponseXpath.put(("AttrVal1").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].AttrValContainer.AttrValWrapper.AttrVal");
		EDSResponseXpath.put(("AttrKey1").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].AttrValContainer.AttrValWrapper[0].AttrKey");
		EDSResponseXpath.put(("MessageCode").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[0].MessageCode");
		EDSResponseXpath.put(("MessageGroup1").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[0].MessageGroup1[0]");
		EDSResponseXpath.put(("MessageGroup2").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[0].MessageGroup2[0]");
		EDSResponseXpath.put(("MessageHeader").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[0].MessageHeader");
		EDSResponseXpath.put(("MessageCategory").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].DecisionContainer.Decision[17].MessageContainer.MessageWrapper[0].MessageCategory");	
		//incorrect xpath for display flag
														
		EDSResponseXpath.put(("DisplayFlag").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].DecisionContainer.Decision[18].MessageContainer.MessageWrapper[1].DisplayFlag");
		EDSResponseXpath.put(("MessageId").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].DecisionContainer.Decision[18].MessageContainer.MessageWrapper[1].MessageId");
		EDSResponseXpath.put(("MessageType").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].DecisionContainer.Decision[18].MessageContainer.MessageWrapper[1].MessageType");
		EDSResponseXpath.put(("SequenceNumber").toLowerCase(), ".EDSRuleResponse.AnswerContainer.Answer[0].DecisionContainer.Decision[18].MessageContainer.MessageWrapper[1].SequenceNumber");

		String edsResponseXpath = EDSResponseXpath.get(responseElement.toLowerCase());
		return edsResponseXpath;
	}

}
