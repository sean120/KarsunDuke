package com.seanfiles.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



public class ACEAPI2_0DataElements extends ACEAPI2_0Constants{
	Map<String,String> ACEAPIElements;
	Map<String,String> ACEAPIDBPATH;
	List<String> requestElements;
	List<String> dataInRequestGFSCollection;
//	List<String> fullAssessmentData;
//	List<String> assessmentDecisionDataElements;
//	List<String> aceDecisionMessageDataElements;
	List<String> errorsDataElements;
	List<String> responseData;
	List<String> responseLoanDataElements;
	
	public ACEAPI2_0DataElements() {
		
		ACEAPIElements = new HashMap<String, String>();
		ACEAPIDBPATH = new HashMap<String, String>();
		//--RESPONSE--//
		//--loandIdentififcations--//
		
		ACEAPIElements.put(LoanIdentifier, "$.loanIdentifications.loanIdentification[0].loanIdentifier");
		ACEAPIElements.put(LoanIdentifierType, "$.loanIdentifications.loanIdentification[0].loanIdentifierType");
		
		
		//--aceAssessmentDecision--//
		
		ACEAPIElements.put(AlternateAppraisalDecisionEffectiveDatetime, "$.aceAssessmentDecision.alternateAppraisalDecisionEffectiveDatetime");
		ACEAPIElements.put(AlternateAppraisalDecisionExpirationDatetime,"$.aceAssessmentDecision.alternateAppraisalDecisionExpirationDatetime");
		ACEAPIElements.put(AlternateAppraisalDecisionStatusType,"$.aceAssessmentDecision.alternateAppraisalDecisionStatusType");
		ACEAPIElements.put(AlternateAppraisalEligibilityDecision,"$.aceAssessmentDecision.alternateAppraisalEligibilityDecision");
		ACEAPIElements.put(AlternateAppraisalType, "$.aceAssessmentDecision.alternateAppraisalType");
		ACEAPIElements.put(ListText,"$.aceAssessmentDecision.alternateAppraisalReasonList[0].listText");
		ACEAPIElements.put(MinimunLoanAssessmentFormType, "$.aceAssessmentDecision.minimumLoanAssessmentFormType");
		ACEAPIElements.put(PropertyValuationEffectiveDateTimeRequest, "x.aceAssessmentDecision.propertyValuationEffectiveDateTime");
		
		
		
		
		
		//--aceDecisionMessageContainer--//
		ACEAPIElements.put(CraCommunicationFailureType, "$.loanDeliveryStructure.loanDetails.communicationFailureDerivedDetails.craCommunicationFailureDerivation.craCommunicationFailureType");
		ACEAPIElements.put(MessageCategory, "$.aceDecisionMessageContainer.messages[0].messageCategory");
		ACEAPIElements.put(MessageCode,"$.aceDecisionMessageContainer.messages[0].messageCode");
		ACEAPIElements.put(MessageGroup1, "$.aceDecisionMessageContainer.messages[0].messageGroup1");
		ACEAPIElements.put(MessageGroup2, "$.aceDecisionMessageContainer.messages[0].messageGroup2");
		ACEAPIElements.put(MessageId, "$.aceDecisionMessageContainer.messages[0].messageId");
		ACEAPIElements.put(MessageHeader,"$.aceDecisionMessageContainer.messages[0].messageHeader");
		ACEAPIElements.put(MessageType,"$.aceDecisionMessageContainer.messages[0].messageType");
		
		//--aceFACTDecision--//
		ACEAPIElements.put(AceFACTExpirationDatetime, "$.aceFACTDecision.aceFACTExpirationDatetime");
		ACEAPIElements.put(AceFACTDecisionStatusType, "$.aceFACTDecision.aceFACTDecisionStatusType");
		ACEAPIElements.put(AceFACTEligibilityDecision, "$.aceFACTDecision.aceFACTEligibilityDecision");
		ACEAPIElements.put(AceFACTPropertyValuationEffectiveDateTime, "$.aceFACTDecision.aceFACTPropertyValuationEffectiveDateTime");
		
		
		//--errors--//
		ACEAPIElements.put(Error, "$.errors.error[0]");
		
		//--REQUEST--//
		
		ACEAPIElements.put(RdsCommunicationFailureType,"$.loanDeliveryStructure.loanDetails.communicationFailureDerivedDetails.rdsCommunicationFailureDerivation.rdsCommunicationFailureType");
		ACEAPIElements.put(LoanIdentifierRequest,"$.loanIdentifications.loanIdentification[0].loanIdentifier");
		ACEAPIElements.put(LoanIdentifierTypeRequest,"$.loanIdentifications.loanIdentification[0].loanIdentifierType");
		
		//--loanPropertyCollaterals--//
		ACEAPIElements.put(MiPremiumFinancedAmount,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.insurancePolicyDetails.loanMI.miPremiumFinancedAmount");
		
		//--loanStates--//
		ACEAPIElements.put(MiPremiumFinancedIndicator,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.insurancePolicyDetails.loanMI.miPremiumFinancedIndicator");
		
		//--loan--//
		ACEAPIElements.put(ConstructionLoanIndicator,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.constructionLoanIndicator");
		ACEAPIElements.put(GrandfatheringIndicator,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.grandfatheringIndicator");
		ACEAPIElements.put(LoanProcessingStageType,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.loanProcessingStageType");
		ACEAPIElements.put(LoanPurposeType,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.loanPurposeType");
		ACEAPIElements.put(LoanRefinanceCashOutDeterminationType,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.loanRefinanceCashOutDeterminationType");
		ACEAPIElements.put(MortgageType,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loan.mortgageType");
		
		//--loanAcquisition--//
		ACEAPIElements.put(LoanConformityType,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanAcquisitionDetails.loanAcquisition.loanConformityType");
		ACEAPIElements.put(SellerAccountIdentifier,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanAcquisitionDetails.loanAcquision.sellerAccountIdentifier");
		ACEAPIElements.put(SellerLoanIdentifier,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanAcquisitionDetails.loanAcquision.sellerLoanIdentifier");
		
		//--loanApplication--//
		ACEAPIElements.put(BorrowerPropertyPurchasePriceAmount,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanApplicationDetails.loanApplication.borrowerPropertyPurchasePriceAmount");
		ACEAPIElements.put(LoanApplicationBaseLoanAmount,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanApplicationDetails.loanApplication.loanApplicationBaseLoanAmount");
		ACEAPIElements.put(MiAndFundingFeeFinancedAmount,"$.loanApplicationDetails.loanApplication.miAndFundingFeeFinancedAmount");
		//ACEAPIElements.put(LoanApplicationIdentifier,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanApplicationDetails.loanApplication.miAndFundingFeeFinancedAmount");
		
		//--loanConversionRuleDetails--//
		ACEAPIElements.put(constructionLoanType,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanConversionRuleDetails.loanConversionRule.constructionLoanType");
		//--loanOriginationDetails--//
		ACEAPIElements.put(InvestorCollateralProgramType, "$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanOrigination.investorCollateralProgramType");
		ACEAPIElements.put(LoanOriginationSystemVersionIdentifier,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanOrigination.loanOriginationSystemVersionIdentifier");
		ACEAPIElements.put(LoanPropertyUsageType,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanOrigination.loanOriginationSystemVersionIdentifier");
		ACEAPIElements.put(NoteAmount,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanOrigination.noteAmount");
		
		//--loanUnderwritingDecision--//
		ACEAPIElements.put(AutomatedUnderwritingSystemType, "$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanUnderwritingDecision.automatedUnderwritingSystemType");
		ACEAPIElements.put(LoanUnderwritingCaseIdentifier,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanUnderwritingDecision.loanUnderwritingCaseIdentifier");
		ACEAPIElements.put(LoanUnderwritingDecisionDefaultProbabilityRate,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanOriginationDetails.loanUnderwritingDecision.loanUnderwritingDecisionDefaultProbabilityRate");
		
		//--loanRiskAssessments--//
		
		ACEAPIElements.put(DerivationRiskClassType,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[0].derivationRiskClassType");
		ACEAPIElements.put(DerivedCalculationSourceType, "$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[0].derivationRiskClassType");
		ACEAPIElements.put(StrategicOfferingCreditRiskType, "$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[0].strategicOfferingCreditRiskType");
		ACEAPIElements.put(LoanQualityAdvisorResultType, "$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[0].loanQualityAdvisorResultType");
		ACEAPIElements.put(MessageCodeRequest, "$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[0].messageCode");
		ACEAPIElements.put(MessageTextRequest, "$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.creditRiskDerivation[0].messageText");
		
		ACEAPIElements.put(FinancedUnitCount, "$.loanDeliveryStructure.loanDetails.loanIdentifications.loanIdentification[0].loanIdentifierType");
		
		//--loanCollateralRiskAssessments--//
		ACEAPIElements.put(LoanScopeType,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[0].loanCollateralRiskAssessment.loanScopeType");
		ACEAPIElements.put(LtvRatioPercent,"$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[0].loanCollateralRiskAssessment.ltvRatioPercent");
		ACEAPIElements.put(LtvSourceType, "$.loanDeliveryStructure.loanDetails.loanDetailInfo.loanRiskAssessments.loanRiskAssessmentDetails.loanCollateralRiskAssessments.loanCollateralRiskAssessmentDetails[0].loanCollateralRiskAssessment.ltvSourceType");
		
		//--programs--//
		
		ACEAPIElements.put(ProgramType, "$.loanDeliveryStructure.loanDetails.loanDetailInfo.programs[0].program.programType");
		ACEAPIElements.put(RefinanceProgramType, "$.loanDeliveryStructure.loanDetails.loanDetailInfo.programs[0].program.refinanceProgramType");
		
		//--loanState--//
		ACEAPIElements.put(LoanStateDate, "$.loanState.loanStateDate");
		ACEAPIElements.put(LoanStateType, "$.loanState.loanStateType");
		
		//--partyRoles--//
		ACEAPIElements.put(PartyRoleIdentifier, "$.loanDeliveryStructure.loanDetails.partyRoles.partyRole[0].partyRoleIdentifier");
		ACEAPIElements.put(PartyRoleType, "$.loanDeliveryStructure.loanDetails.partyRoles.partyRole[0].partyRoleType");
		
		//--productDetails--//
		ACEAPIElements.put(ProductDescription, "$.loanDeliveryStructure.loanDetails.productDetails.productDefinition.productDescription");
		ACEAPIElements.put(ProductIdentifier, "$.loanDeliveryStructure.loanDetails.productDetails.productDefinition.productIdentifier");
		ACEAPIElements.put(ProductType, "$.loanDeliveryStructure.loanDetails.productDetails.productDefinition.productType");
		
		//--propertyDetails--//
		
		//ACEAPIElements.put(FIPSCountyThreeDigitCode, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].FIPSCountyThreeDigitCode");
		//ACEAPIElements.put(FIPSStateNumericCode, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].FIPSStateNumericCode");
		ACEAPIElements.put(AddressLineText, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].addressLineText");
		ACEAPIElements.put(AddressMatchLevelType, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].addressMatchLevelType");
		ACEAPIElements.put(AddressSourceType, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].addressSourceType");
		ACEAPIElements.put(AddressUnitDesignatorType, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].addressUnitDesignatorType");
		ACEAPIElements.put(AddressUnitIdentifier, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].addressUnitIdentifier");
		ACEAPIElements.put(CarrierRouteType, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].carrierRouteType");
		ACEAPIElements.put(CensusTractBaseIdentifier, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].censusTractBaseIdentifier");
		ACEAPIElements.put(CityName, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].cityName");
		ACEAPIElements.put(CoreBasedStatisticalAreaCode, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].coreBasedStatisticalAreaCode");
		ACEAPIElements.put(HighwayContractIdentifier, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].highwayContractIdentifier");
		ACEAPIElements.put(LatitudeNumber, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].latitudeNumber");
		ACEAPIElements.put(LongitudeNumber, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].longitudeNumber");
		ACEAPIElements.put(PostOfficeBoxIdentifier, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].postOfficeBoxIdentifier");
		ACEAPIElements.put(PostalCode, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].postalCode");
		ACEAPIElements.put(RuralRouteIdentifier, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].ruralRouteIdentifier");
		ACEAPIElements.put(StateCode, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].stateCode");
		ACEAPIElements.put(StreetName, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].streetName");
		ACEAPIElements.put(StreetPostDirectionalText, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].streetPostDirectionalText");
		ACEAPIElements.put(StreetPreDirectionalText, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].streetPreDirectionalText");
		ACEAPIElements.put(StreetPrimaryNumberText, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].streetPrimaryNumberText");
		ACEAPIElements.put(StreetSuffixText, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].streetSuffixText");
		ACEAPIElements.put(ZipPlusFourCode, "$.loanDeliveryStructure.loanDetails.propertyDetails.addressDetails[0].address[0].zipPlusFourCode");
		
		
		//--property--//
		
		ACEAPIElements.put(PropertyCategoryType, "$.loanDeliveryStructure.loanDetails.propertyDetails.property.propertyCategoryType");
		ACEAPIElements.put(PropertyOccupancyType, "$.loanDeliveryStructure.loanDetails.propertyDetails.propertyAssessment.propertyAssessmentSourceType");
		
        //--propertyAssessment--//
		ACEAPIElements.put(PropertyAssessmentSourceType, "$.propertyAssessment.propertyAssessmentSourceType");
		
		//--propertyRights--//
		ACEAPIElements.put(PropertyRightsType, "$.loanDeliveryStructure.loanDetails.propertyDetails.propertyRights.propertyRightsType");
		
		//--propertyTitles--//
		ACEAPIElements.put(PropertyEstateType, "$.loanDeliveryStructure.loanDetails.propertyDetails.propertyTitles.propertyEstateType");
		
		//--propertyValuations--//
		ACEAPIElements.put(AppraisalIdentifier, "$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.appraisal.appraisalIdentifier");
		
		//--propertyValuationDetails--//
		ACEAPIElements.put(PropertyIdentifier, "$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails[0].propertyValuation[0].propertyIdentifier");
		ACEAPIElements.put(PropertyValuationAmount, "$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails[0].propertyValuation[0].propertyValuationAmount");
		ACEAPIElements.put(PropertyValuationEffectiveDateTime, "$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails[0].propertyValuation[0].propertyValuationEffectiveDateTime");
		ACEAPIElements.put(PropertyValuationMethodType, "$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails[0].propertyValuation[0].propertyValuationMethodType");
		ACEAPIElements.put(PropertyValuationSequenceNumber, "$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails[0].propertyValuation[0].propertyValuationSequenceNumber");
		ACEAPIElements.put(PropertyValuationType, "$.loanDeliveryStructure.loanDetails.propertyDetails.propertyValuations.propertyValuationDetails[0].propertyValuation[0].propertyValuationType");
		
		
		//--realEstateProject--//
		ACEAPIElements.put(ProjectAttachmentType, "$.loanDeliveryStructure.loanDetails.propertyDetails.realEstateProject.projectAttachmentType");
		ACEAPIElements.put(ProjectClassificationType, "$.loanDeliveryStructure.loanDetails.propertyDetails.realEstateProject.projectClassificationType");
		ACEAPIElements.put(ProjectLegalStructureType, "$.loanDeliveryStructure.loanDetails.propertyDetails.realEstateProject.projectLegalStructureType");
		
		//--structureDetails--//
		ACEAPIElements.put(ConstructionMethodType, "$.loanDeliveryStructure.loanDetails.propertyDetails.structureDetails.structure.constructionMethodType");
		
		//--serviceRequestDetails--//
		ACEAPIElements.put(ServiceName, "$.serviceRequestDetails.serviceName");
		ACEAPIElements.put(ServiceRequestOperationName, "$.serviceRequestDetails.serviceRequestOperationName");
		ACEAPIElements.put(SubscriberIdentifier, "$.serviceRequestDetails.subscriberIdentifier");
		ACEAPIElements.put(SubscriberRequestCorrelationIdentifier, "$.serviceRequestDetails.subscriberRequestCorrelationIdentifier");
	
		//--singleFamilyLoanBorrower--//
		ACEAPIElements.put(BorrowerClassificationType, "$.loanDeliveryStructure.loanDetails.singleFamilyLoanBorrower[0].borrowerClassificationType");
		ACEAPIElements.put(BorrowerIdentifier, "$.loanDeliveryStructure.loanDetails.singleFamilyLoanBorrower[0].borrowerIdentifier");
		ACEAPIElements.put(IntentToOccupeType, "$.loanDeliveryStructure.loanDetails.singleFamilyLoanBorrower[0].intentToOccupyType");
		ACEAPIElements.put(PatryRoleTypeBorrower, "$.loanDeliveryStructure.loanDetails.singleFamilyLoanBorrower[0].partyRoleType");
		
		
		requestElements=new ArrayList<String>();
		responseLoanDataElements = new ArrayList<String>();
//		assessmentDecisionDataElements = new ArrayList<String>();
//		aceDecisionMessageDataElements = new ArrayList<String>();
		errorsDataElements = new ArrayList<String>();
//		fullAssessmentData = new ArrayList<String>();
		responseData = new ArrayList<String>();
		dataInRequestGFSCollection = new ArrayList<String>();
		
		
		for(Map.Entry<String, String> elementsPath :ACEAPIElements.entrySet()) {
			    String elementName=elementsPath.getKey();
			    String elementPath=elementsPath.getValue();
			
			if(elementPath.startsWith("$.loan")|| elementPath.startsWith("$.serviceRequestDetails")) {
				requestElements.add(elementName);
			}
			else if(elementPath.startsWith("$.loanIdentifications.loanIdentification[0].loanIdentifier")||
					elementPath.startsWith("$.loanIdentifications.loanIdentification[0].loanIdentifierType")) {
				responseLoanDataElements.add(elementName);
			
//			else if(elementPath.startsWith("$.aceAssessmentDecision")) {
//				assessmentDecisionDataElements.add(elementName);
//			}
//			else if(elementPath.startsWith("$.aceDecisionMessageContainer")) {
//				aceDecisionMessageDataElements.add(elementName);
			}
		    else if(elementPath.startsWith("$.errors.error")) {
				errorsDataElements.add(elementName);
		    }
			else if(elementPath.startsWith("$.aceAssessmentDecision")||elementPath.startsWith("$.aceDecisionMessageContainer")
					||elementPath.startsWith("$.aceFACTDecision")) {
				responseData.add(elementName);
			}
//			else{
//				fullAssessmentData.add(elementName);
//			}
		}
		}
		
	
	public String getACEGFSElementPath(String elementName) {
		return ACEAPIElements.get(elementName);
	}
	
	
	public String getACEGFSDBPath(String elementName, String collectionName) {
		
		String elementPath = getACEGFSElementPath(elementName);		
		
		switch(collectionName) {
		case "ACEFullAssessmentRequests":
			elementPath = elementPath.replace("$.", "$.automatedCollateralEvaluationRequest.");
		    break;
		case "ACEFullAssessmentResponses":
			elementPath = elementPath.replace("$.","$.automatedCollateralEvaluationResponse." );
		break;
		}
		
		return elementPath;
			
	}
	
     public String getElementDataType(String elementName) {
    	 String elementPath =  getACEGFSElementPath(elementName);
    	 String elementDataType = "String";
//    	      if(elementPath.endsWith("Count")
//    	         ||elementPath.endsWith("Amount")
//    	    	 ||elementPath.endsWith("propertyIdentifier")
//    			 ||elementPath.endsWith("Percent") 
//    	    	 ||elementPath.endsWith("postalCode"))  {
//    	    	  elementDataType = "Integer";	  
//    	      }
//    	      else if(elementPath.endsWith("propertyValuationAmount")) {
//    	    	  elementDataType = "Double";
//    	      }
    		  return elementDataType;
    	     
    	      }
     
     
     public List<String> getElementList(String elementsGroup) { 
    	 List<String> elementList=null;
    	 if(elementsGroup.equalsIgnoreCase("requestData")) {
    		 elementList=getRequestDataElements();
    	 }
    	
//    	 else if(elementsGroup.equalsIgnoreCase("assessmentDecisionDataElements")) {
//    		 elementList=getAssessmentDecisionElements();
//    	 }
//    	 else if(elementsGroup.equalsIgnoreCase("aceDecisionMessageDataElements")) {
//    		 elementList=getAceDecisonMessageDataElements();
//    	 }
    	 else if(elementsGroup.equalsIgnoreCase("responseLoanDataElements")) {
    		 elementList=getResponseLoanDataElements();
    	 }
    	 else if(elementsGroup.equalsIgnoreCase("responseData")) {
    		 elementList = getResponseDataElements();
    	 }
    	 else {
    		 elementList=getErrorDataElements();
    	 }
    	 return elementList;
     }
     
     
 	public List<String> getRequestDataElements() {
		return requestElements;
	}

 	
//	public List<String> getAceDecisonMessageDataElements() {
//		return aceDecisionMessageDataElements;
//	}
//
//	public List<String>getAssessmentDecisionElements() {
//		return assessmentDecisionDataElements;
//	}
//	
//	public List<String> getFullAssessmentData() {
//		return fullAssessmentData;
//	}
	public List<String> getErrorDataElements() {
		return errorsDataElements;
     
    }
	public List<String> getResponseDataElements(){
		return responseData;
	}
	
	public List<String> getResponseLoanDataElements(){
		return responseLoanDataElements;
	}

}















