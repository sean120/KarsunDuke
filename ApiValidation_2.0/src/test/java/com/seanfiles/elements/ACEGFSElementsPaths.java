package com.seanfiles.elements;
import static com.seanfiles.elements.ACEElements.MaximumAuthorizedLoanAmount;
import static com.seanfiles.elements.ACEGFSElements.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.seanfiles.db.GFSDBDocuments;

public class ACEGFSElementsPaths {
	
	Map<String, String> jsonPaths ;
	
	List<String> loanDataElements;
	List<String> aceDataElements;
	List<String> errorContainerElements;
	List<String> presentValueDataElements;
	
	private static ACEGFSElementsPaths paths=null;
	
	public static ACEGFSElementsPaths getGFSElementsPaths() {
		if(paths == null ) {
			paths= new ACEGFSElementsPaths();
		}
		return paths;
	}
	
	public ACEGFSElementsPaths() {
		jsonPaths = new HashMap<String, String>();		

		jsonPaths.put(sourceApplicationName, "$.sourceApplicationName");
		
		//--LOAN_IDENTIFICATIONS--//
		jsonPaths.put(LoanIdentifier, "$.loanIdentifications.loanIdentification[0].loanIdentifier");
		jsonPaths.put(LoanIdentifierType, "$.loanIdentifications.loanIdentification[0].loanIdentifierType");

		//--parties--//
		jsonPaths.put(PartyIdentifier, "$.parties.party[0].partyIdentifier");
		jsonPaths.put(PartyRoleType,"$.parties.party[0].partyRoleType" );

		//--address--//
		jsonPaths.put(AddressLineText,"$.address.addressLineText" );
		jsonPaths.put(AddressSourceType,"$.address.addressSourceType" );
		jsonPaths.put(AddressMatchLevelType,"$.address.addressMatchLevelType" );
		jsonPaths.put(CityName,"$.address.cityName" );
		jsonPaths.put(PostalCode,"$.address.postalCode" );
		jsonPaths.put(StreetName,"$.address.streetName" );
		jsonPaths.put(StreetPrimaryNumberText,"$.address.streetPrimaryNumberText" );
		jsonPaths.put(StreetSuffixText,"$.address.streetSuffixText" );
		jsonPaths.put(ZipPlusFourCode,"$.address.zipPlusFourCode" );
		jsonPaths.put(StateCode,"$.address.stateCode" );
		
		jsonPaths.put(AddressUnitDesignatorType,"$.address.addressUnitDesignatorType" );
		jsonPaths.put(AddressUnitIdentifier,"$.address.addressUnitIdentifier" );

		//--ALTERNATEAPPRAISALDECISIONDATA--//
		jsonPaths.put(AlternateAppraisalDecisionStatusType,"$.alternateAppraisalDecisionData.alternateAppraisalDecisionStatusType" );
		jsonPaths.put(AlternateAppraisalDecisionEffectiveDatetime,"$.alternateAppraisalDecisionData.alternateAppraisalDecisionEffectiveDatetime" );
		jsonPaths.put(AlternateAppraisalDecisionExpirationDatetime,"$.alternateAppraisalDecisionData.alternateAppraisalDecisionExpirationDatetime" );
		jsonPaths.put(AlternateAppraisalEligibilityDecision,"$.alternateAppraisalDecisionData.alternateAppraisalEligibilityDecision" );
		
		jsonPaths.put(EstimatedPropertyValueAmount,"$.alternateAppraisalDecisionData.estimatedPropertyValueAmount" );
		jsonPaths.put(MaximumAuthorizedLoanAmount,"$.alternateAppraisalDecisionData.maximumAuthorizedLoanAmount" );
		jsonPaths.put(SalesContractPriceAmount,"$.alternateAppraisalDecisionData.salesContractPriceAmount" );

		//--PV--//
		jsonPaths.put(PropertyValuationAmount,"$.propertyValuationData.propertyValuationAmount" );
		jsonPaths.put(PropertyValuationMethodType,"$.propertyValuationData.propertyValuationMethodType" );
		jsonPaths.put(PropertyValuationType,"$.propertyValuationData.propertyValuationType" );

	//	jsonPaths.put((PropertyValuationEffectiveDateTime,"$.propertyValuationData.propertyValuationEffectiveDateTime" );
	//	jsonPaths.put((PropertyAssessmentSourceType,"$.propertyValuationData.propertyAssessmentSourceType" );
		jsonPaths.put(LoanProcessingStageType,"$.propertyValuationData.loanProcessingStageType" );

		//--HVE1--//
		//propertyAppraiserValuationContainer
		jsonPaths.put(HomeValueExplorerMidPointValueEstimateAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerMidPointValueEstimateAmount" );
		jsonPaths.put(HomeValueExplorerMaximumValueAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerMaximumValueAmount" );
		jsonPaths.put(HomeValueExplorerMinimumValueAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerMinimumValueAmount" );
		jsonPaths.put(HomeValueExplorerForecastStandardDeviationRate,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerForecastStandardDeviationRate" );
		jsonPaths.put(HomeValueExplorerConfidenceLevelType,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerConfidenceLevelType" );
		jsonPaths.put(PropertyValuationEffectiveDateTime,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.propertyValuationEffectiveDateTime" );
		jsonPaths.put(HomeValueExplorerAssessmentDateTime,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerAssessmentDateTime" );
//		jsonPaths.put(HomeValueExplorerOptionType,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerOptionType" );

		//propertyInspectionWaiverResult
		jsonPaths.put(AppraisalExistenceIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.appraisalExistenceIndicator" );
		jsonPaths.put(PriceGrowthThresholdAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.priceGrowthThresholdAmount" );
		jsonPaths.put(PropertyConditionIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyConditionIndicator" );
		jsonPaths.put(LegalityIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.legalityIndicator" );
		jsonPaths.put(PhysicalDeficiencyIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.physicalDeficiencyIndicator" );
		jsonPaths.put(HighestBestUseIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.highestBestUseIndicator" );
		jsonPaths.put(AppraisalCompletionAsIsIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.appraisalCompletionAsIsIndicator" );
		jsonPaths.put(NeighborhoodConformityIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.neighborhoodConformityIndicator" );
		jsonPaths.put(ARMsLengthContractIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.armsLengthContractIndicator" );
		jsonPaths.put(QualityAgeIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.qualityAgeIndicator" );
		jsonPaths.put(DaysOnMarketIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.daysOnMarketIndicator" );
		jsonPaths.put(DistressedIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.distressedIndicator" );
		jsonPaths.put(PropertyAgeCount,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyAgeCount" );
		jsonPaths.put(PublicRecordExistenceIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.publicRecordExistenceIndicator" );
		jsonPaths.put(MissingDataIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.missingDataIndicator" );
		jsonPaths.put(ActiveMLSListingIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.activeMLSListingIndicator" );
		jsonPaths.put(MultipleListingIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.multipleListingIndicator" );
		jsonPaths.put(QualityIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.qualityIndicator" );
		jsonPaths.put(StructureBuildingMaterialQualityRatingIdentifier,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.structureBuildingMaterialQualityRatingIdentifier" );
		jsonPaths.put(StructureBuildingMaterialQualityRatingType,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.structureBuildingMaterialQualityRatingType" );
		jsonPaths.put(PropertyLandUseIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyLandUseIndicator");
	    jsonPaths.put(PropertyPurchaseLotSizeOneAcreIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyPurchaseLotSizeOneAcreIndicator");
	    jsonPaths.put(PropertyRefinanceLotSizeTwoAcreIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyRefinanceLotSizeTwoAcreIndicator");
	    jsonPaths.put(PropertyUnitIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyUnitIndicator");
		//--HVE2--//
		//propertyAppraiserValuationContainer
		jsonPaths.put(HVE2HomeValueExplorerMidPointValueEstimateAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.homeValueExplorerMidPointValueEstimateAmount" );
		jsonPaths.put(HVE2HomeValueExplorerMaximumValueAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.homeValueExplorerMaximumValueAmount" );
		jsonPaths.put(HVE2HomeValueExplorerMinimumValueAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.homeValueExplorerMinimumValueAmount" );
		jsonPaths.put(HVE2HomeValueExplorerForecastStandardDeviationRate,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.homeValueExplorerForecastStandardDeviationRate" );
		jsonPaths.put(HVE2HomeValueExplorerConfidenceLevelType,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.homeValueExplorerConfidenceLevelType" );
		jsonPaths.put(HVE2PropertyValuationEffectiveDateTime,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.propertyValuationEffectiveDateTime" );
		jsonPaths.put(HVE2HomeValueExplorerAssessmentDateTime,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.homeValueExplorerAssessmentDateTime" );

		jsonPaths.put(HVE2HomeValueExplorerOptionType,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.homeValueExplorerOptionType" );

		//propertyInspectionWaiverResult
		jsonPaths.put(HVE2AppraisalExistenceIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.appraisalExistenceIndicator" );
		jsonPaths.put(HVE2PriceGrowthThresholdAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.priceGrowthThresholdAmount" );
		jsonPaths.put(HVE2PropertyConditionIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.propertyConditionIndicator" );
		jsonPaths.put(HVE2LegalityIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.legalityIndicator" );
		jsonPaths.put(HVE2PhysicalDeficiencyIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.physicalDeficiencyIndicator" );
		jsonPaths.put(HVE2HighestBestUseIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.highestBestUseIndicator" );
		jsonPaths.put(HVE2AppraisalCompletionAsIsIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.appraisalCompletionAsIsIndicator" );
		jsonPaths.put(HVE2NeighborhoodConformityIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.neighborhoodConformityIndicator" );
		jsonPaths.put(HVE2ARMsLengthContractIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.armsLengthContractIndicator" );
		jsonPaths.put(HVE2QualityAgeIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.qualityAgeIndicator" );
		jsonPaths.put(HVE2DaysOnMarketIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.daysOnMarketIndicator" );
		jsonPaths.put(HVE2DistressedIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.distressedIndicator" );
		jsonPaths.put(HVE2PropertyAgeCount,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.propertyAgeCount" );
		jsonPaths.put(HVE2PublicRecordExistenceIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.publicRecordExistenceIndicator" );
		jsonPaths.put(HVE2MissingDataIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.missingDataIndicator" );
		jsonPaths.put(HVE2ActiveMLSListingIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.activeMLSListingIndicator" );
		jsonPaths.put(HVE2MultipleListingIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.multipleListingIndicator" );
		jsonPaths.put(HVE2QualityIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.qualityIndicator" );
		jsonPaths.put(HVE2StructureBuildingMaterialQualityRatingIdentifier,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.structureBuildingMaterialQualityRatingIdentifier" );
		jsonPaths.put(HVE2StructureBuildingMaterialQualityRatingType,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.structureBuildingMaterialQualityRatingType" );
		jsonPaths.put(HVE2PropertyLandUseIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.propertyLandUseIndicator");
	    jsonPaths.put(HVE2PropertyPurchaseLotSizeOneAcreIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.propertyPurchaseLotSizeOneAcreIndicator");
	    jsonPaths.put(HVE2PropertyRefinanceLotSizeTwoAcreIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.propertyRefinanceLotSizeTwoAcreIndicator");
	    jsonPaths.put(HVE2PropertyUnitIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.propertyUnitIndicator");
	
	    // New Elements for HVE/FDR-CCE-Disaster
	    jsonPaths.put(PredictedConditionScoreValue,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.predictedConditionScoreValue");
	    jsonPaths.put(FemaDisasterName,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].disasterPropertyResult.femaDisasterName");
	    jsonPaths.put(AppraisalAlternativeDisasterEligibilityDescription,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].disasterPropertyResult.appraisalAlternativeDisasterEligibilityDescription");

	    jsonPaths.put(HVE2PredictedConditionScoreValue,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.predictedConditionScoreValue");
	    jsonPaths.put(HVE2FemaDisasterName,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].disasterPropertyResult.femaDisasterName");
	    jsonPaths.put(HVE2AppraisalAlternativeDisasterEligibilityDescription,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].disasterPropertyResult.appraisalAlternativeDisasterEligibilityDescription");
	    
		// GFS GET REQUEST QUERY PARAMS
		jsonPaths.put(LPKey, "$.loanIdentifications.loanIdentification[0].loanIdentifier");
		
		// GFS POST REQUEST PAYLOAD ELEMENTS
		jsonPaths.put(LoanID_LPKey, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorKey\")].loanIdentifier");
		jsonPaths.put(LoanID_LPUL, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorUniqueLoan\")].loanIdentifier");
		jsonPaths.put(LoanID_LPT, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorTransaction\")].loanIdentifier");
		
		jsonPaths.put(LoanID_ACEAPI, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"ACEAPI\")].loanIdentifier");
		jsonPaths.put(LoanIDType_ACEAPI, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"ACEAPI\")].loanIdentifierType");

		
		jsonPaths.put(LoanIDType_LPKey, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorKey\")].loanIdentifierType");
		jsonPaths.put(LoanIDType_LPUL, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorUniqueLoan\")].loanIdentifierType");
		jsonPaths.put(LoanIDType_LPT, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorTransaction\")].loanIdentifierType");
		
		jsonPaths.put(PartyID_SELLER, "$.parties.party[?(@.partyRoleType == \"SELLER\")].partyIdentifier");
		jsonPaths.put(PartyIDType_SELLER,"$.parties.party[?(@.partyRoleType == \"SELLER\")].partyRoleType" );

		jsonPaths.put(ErrorCodeACE, "$.aceServiceData.errors.error[0].errorResponse.code");
		jsonPaths.put(ErrorMessageACE, "$.aceServiceData.errors.error[0].errorResponse.message");
		jsonPaths.put(ErrorCodePV, "$.presentValueServiceData.errors.error[0].errorResponse.code");
		jsonPaths.put(ErrorMessagePV, "$.presentValueServiceData.errors.error[0].errorResponse.message");
		
		
		loanDataElements=new ArrayList<String>();
		aceDataElements=new ArrayList<String>();
		presentValueDataElements=new ArrayList<String>();
		errorContainerElements=new ArrayList<String>();
		
		for (Map.Entry<String, String> elementsPath : jsonPaths.entrySet())
		{
		    String elementName=elementsPath.getKey();
		    String elementPath=elementsPath.getValue();
		    
			if(elementPath.startsWith("$.loanIdentifications") || 
					elementPath.startsWith("$.parties") ||
					elementPath.startsWith("$.address") ) {
					loanDataElements.add(elementName);
				}
			else if(elementPath.startsWith("$.propertyValuationData") ) {
					presentValueDataElements.add(elementName);
				}
			else if(elementPath.startsWith("$.alternateAppraisalDecisionData") || 
					elementPath.startsWith("$.homeValueExplorerDataContainer")) {
					aceDataElements.add(elementName);
			}
			else if(elementPath.contains(".errors.error")) {
				errorContainerElements.add(elementName);
			}
		}
		//
		jsonPaths.put(RequestType,"$.requestType" );
		
		jsonPaths.put(PostAcknowledgementMessage, "$.message");
		jsonPaths.put(PostAcknowledgementCode, "$.code");
		jsonPaths.put(PostAcknowledgementLoanID, "$.loanIdentifier");
		jsonPaths.put(ReqIDToRespLink, "$.transactionContext._id");
		jsonPaths.put(ReqLoanProcessingStageType,"$.loanInformation.loanProcessingStageType" );
		jsonPaths.put(ReqGETType,"$.requestType" );


	}	
	
	public List<String> getLoanDataElements() {
		return loanDataElements;
	}

	public List<String> getAceDataElements() {
		return aceDataElements;
	}

	public List<String> getPresentValueDataElements() {
		return presentValueDataElements;
	}
	
	public List<String> getErrorContainerElements() {
		return errorContainerElements;
	}
	
	public List<String> getElementsList(String elementsGroup) {
		List<String> elementsList=null;
		if(elementsGroup.equalsIgnoreCase("loanData")) {
			elementsList=getLoanDataElements();
		}
		else if(elementsGroup.equalsIgnoreCase("aceData")) {
			elementsList=getAceDataElements();
		}
		else if(elementsGroup.equalsIgnoreCase("presentValueData")) {
			elementsList=getPresentValueDataElements();
		}
		return elementsList;
	}
	

	public String getGFSElementPath(String elementName) {
		return jsonPaths.get(elementName);
	}
	
	public String getGFSDBElementPath(String elementName, String collectionName) {
		String elementPath=getGFSElementPath(elementName);

		switch(collectionName) {
			case GFSDBDocuments.collectionGFSACERequest:
				elementPath=elementPath.replace("$.", "$.aceRequest.");
				break;
			case GFSDBDocuments.collectionGFSACEResponse:
				if(isLoanDataElement(elementName) ) {
					elementPath=elementPath.replace("$.", "$.loanData.");
				}
				else if(isErrorContainerElement(elementName) ) {
					elementPath=elementPath.replace("$.aceServiceData.", "$.aceResponse.");
				}
				else {
					elementPath=elementPath.replace("$.", "$.aceResponse.aceResponseData.");				}
				break;
			case GFSDBDocuments.collectionGFSPVResponse:
				if(isLoanDataElement(elementName) ) {
					elementPath=elementPath.replace("$.", "$.loanData.");
				}
				else if(isErrorContainerElement(elementName) ) {
					elementPath=elementPath.replace("$.presentValueServiceData.", "$.presentValueResponse.");
				}
				else {
					elementPath=elementPath.replace("$.", "$.presentValueResponse.presentValueResponseData.");				}
				break;
			case GFSDBDocuments.collectionGFSPVRequest:
				elementPath=elementPath.replace("$.", "$.presentValueRequest.");
				break;
			case GFSDBDocuments.collectionGFSACEData:
				if(isLoanDataElement(elementName) ) {
					elementPath=elementPath.replace("$.", "$.loanData.");
				}
				else {
					elementPath=elementPath.replace("$.", "$.aceData.");				}
				break;
			case GFSDBDocuments.collectionGFSPVData:
				if(isLoanDataElement(elementName)) {
					elementPath=elementPath.replace("$.", "$.loanData.");
				}
				else {
					elementPath=elementPath.replace("$.", "$.presentValueData.");
				}
				break;
		}
		
		return elementPath;
	}
	
	public String getGFSResponseElementPath(String elementName) {		
		String elementPath=getGFSElementPath(elementName);
		if(isAceDataElement(elementName)) {
			elementPath=elementPath.replace("$.", "$.aceServiceData.aceResponseData.");
		}
		else if(isPVDataElement(elementName)) {
			elementPath=elementPath.replace("$.", "$.presentValueServiceData.presentValueResponseData.");
		}
		else if(isLoanDataElement(elementName)) {
			elementPath=elementPath.replace("$.", "$.loanData.");
		}
		return elementPath;
	}
	
	public String getDBQueryElementPath(String elementName, String collectionName) {		
		String elementPath=getGFSElementPath(elementName);
		if(elementPath == null) {
			return elementName;
		}
		if(elementPath.contains("[")) {
			elementPath=elementPath.substring(0,elementPath.indexOf("[")) +
					elementPath.substring(elementPath.indexOf("]")+1,elementPath.length());
		}
		switch(collectionName) {
		case GFSDBDocuments.collectionGFSACEData:
			if(isAceDataElement(elementName)) {
				elementPath=elementPath.replace("$.", "aceData.");
			}
			else if(isLoanDataElement(elementName)) {
				elementPath=elementPath.replace("$.", "loanData.");
			}
			else if(elementName.equalsIgnoreCase(sourceApplicationName)) {
				elementPath=elementPath.replace("$.", "");
			}
			break;
		case GFSDBDocuments.collectionGFSACERequest:
			elementPath=elementPath.replace("$.", "aceRequest.");
			break;
		case GFSDBDocuments.collectionGFSACEResponse:
			if(isAceDataElement(elementName)) {
				elementPath=elementPath.replace("$.", "aceResponse.");
			}
			else if(isLoanDataElement(elementName)) {
				elementPath=elementPath.replace("$.", "loanData.");
			}
			else {
				elementPath=elementPath.replace("$.", "");
			}
			break;
		case GFSDBDocuments.collectionGFSPVResponse:
			if(isPVDataElement(elementName)) {
				elementPath=elementPath.replace("$.", "presentValueResponse.");
			}
			else if(isLoanDataElement(elementName)) {
				elementPath=elementPath.replace("$.", "loanData.");
			}
			else {
				elementPath=elementPath.replace("$.", "");
			}
			break;
		case GFSDBDocuments.collectionGFSPVRequest:
			elementPath=elementPath.replace("$.", "presentValueRequest.");
			break;
		case GFSDBDocuments.collectionGFSPVData:
			if(isPVDataElement(elementName)) {
				elementPath=elementPath.replace("$.", "presentValueData.");
			}
			else if(isLoanDataElement(elementName)) {
				elementPath=elementPath.replace("$.", "loanData.");
			}
			else if(elementName.equalsIgnoreCase(sourceApplicationName)) {
				elementPath=elementPath.replace("$.", "");
			}
			break;
		}
		
		return elementPath;
	}
	
	boolean isAceDataElement(String elementName) {
		if(aceDataElements.contains(elementName)) {
			return true;
		}
		return false;		
	}
	
	boolean isLoanDataElement(String elementName) {
		if(loanDataElements.contains(elementName)) {
			return true;
		}
		return false;		
	}
	
	boolean isErrorContainerElement(String elementName) {
		if(errorContainerElements.contains(elementName)) {
			return true;
		}
		return false;		
	}
	
	boolean isPVDataElement(String elementName) {
		if(presentValueDataElements.contains(elementName)) {
			return true;
		}
		return false;		
	}	
}
