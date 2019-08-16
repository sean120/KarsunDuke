package com.seanfiles.elements;

import static com.seanfiles.elements.ACEElements.AlternateAppraisalDecisionExpirationDatetime;
import static com.seanfiles.elements.ACEElements.AlternateAppraisalDecisionStatusType;
import static com.seanfiles.elements.ACEElements.AlternateAppraisalEligibilityDecision;
import static com.seanfiles.elements.ACEElements.PropertyValuationEffectiveDateTime;
import static com.seanfiles.elements.ACEGFSElements.ErrorCodeACE;
import static com.seanfiles.elements.ACEGFSElements.ErrorCodePV;
import static com.seanfiles.elements.ACEGFSElements.ErrorMessageACE;
import static com.seanfiles.elements.ACEGFSElements.ErrorMessagePV;
import static com.seanfiles.elements.ACEGFSElements.LoanIDType_ACEAPI;
import static com.seanfiles.elements.ACEGFSElements.LoanIDType_LPKey;
import static com.seanfiles.elements.ACEGFSElements.LoanIDType_LPT;
import static com.seanfiles.elements.ACEGFSElements.LoanIDType_LPUL;
import static com.seanfiles.elements.ACEGFSElements.LoanID_ACEAPI;
import static com.seanfiles.elements.ACEGFSElements.LoanID_LPKey;
import static com.seanfiles.elements.ACEGFSElements.LoanID_LPT;
import static com.seanfiles.elements.ACEGFSElements.LoanID_LPUL;
import static com.seanfiles.elements.ACEGFSElements.PartyIDType_SELLER;
import static com.seanfiles.elements.ACEGFSElements.PartyID_SELLER;
import static com.seanfiles.elements.GFSElements.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.seanfiles.db.ACEAPI20DBDocuments;
import com.seanfiles.db.GFSDBDocuments;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.services.ACEAPI20AARequestData;
import com.seanfiles.services.ACEAPI20AAResponseData;
import com.seanfiles.utils.MongoDBUtils;


public class GFSElementPaths extends ACEElements{

	public static final String ACEAPI="ACEAPI";
	public static final String serviceCallTraces = "serviceCallTraces";
	public static final String SortCriteria = "{date : -1}";
	List<String> HVE1;
	List<String> HVE2;
	List<String> requestDataElements;
	List<String> loanDataElements;
	List<String> matchingElements;
	List<String> aceDataElements;
	List<String> errorContainerElements;
	List<String> presentValueDataElements;
	private static GFSElementPaths GFSPath=null;
	Map<String, String> GFSJsonPath;
	
	
	public GFSElementPaths() {
		GFSJsonPath = new HashMap<String, String>();		
		
		//--LOAN_IDENTIFICATIONS--//
		GFSJsonPath.put(LoanID_LPKey,"$.loanIdentifications.loanIdentification[0].loanIdentifier" );
		GFSJsonPath.put(LoanID_LPT, "$.loanIdentifications.loanIdentification[0].loanIdentifierType");
		GFSJsonPath.put(LoanID_LPKey, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorKey\")].loanIdentifier");
		GFSJsonPath.put(LoanID_LPUL, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorUniqueLoan\")].loanIdentifier");
		GFSJsonPath.put(LoanID_LPT, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorTransaction\")].loanIdentifier");
		
//		GFSJsonPath.put(LoanID_ACEAPI, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"ACEAPI\")].loanIdentifier");
//		GFSJsonPath.put(LoanIDType_ACEAPI, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"ACEAPI\")].loanIdentifierType");

		
//		GFSJsonPath.put(LoanIDType_LPKey, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorKey\")].loanIdentifierType");
//		GFSJsonPath.put(LoanIDType_LPUL, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorUniqueLoan\")].loanIdentifierType");
//		GFSJsonPath.put(LoanIDType_LPT, "$.loanIdentifications.loanIdentification[?(@.loanIdentifierType == \"LoanProspectorTransaction\")].loanIdentifierType");
		
//		GFSJsonPath.put(PartyID_SELLER, "$.parties.party[?(@.partyRoleType == \"SELLER\")].partyIdentifier");
//		GFSJsonPath.put(PartyIDType_SELLER,"$.parties.party[?(@.partyRoleType == \"SELLER\")].partyRoleType" );

		GFSJsonPath.put(ErrorCodeACE, "$.aceServiceData.errors.error[0].errorResponse.code");
		GFSJsonPath.put(ErrorMessageACE, "$.aceServiceData.errors.error[0].errorResponse.message");
		GFSJsonPath.put(ErrorCodePV, "$.presentValueServiceData.errors.error[0].errorResponse.code");
		GFSJsonPath.put(ErrorMessagePV, "$.presentValueServiceData.errors.error[0].errorResponse.message");
		//--parties--//
	//	GFSJsonPath.put(PartyIdentifier, "$.parties.party[0].partyIdentifier");
	//	GFSJsonPath.put(PartyRoleType,"$.parties.party[0].partyRoleType" );

		//--address--//
		GFSJsonPath.put(AddressLineText,"$.address.addressLineText" );
		GFSJsonPath.put(AddressSourceType,"$.address.addressSourceType" );
		GFSJsonPath.put(AddressMatchLevelType,"$.address.addressMatchLevelType" );
		GFSJsonPath.put(CityName,"$.address.cityName" );
		GFSJsonPath.put(PostalCode,"$.address.postalCode" );
		GFSJsonPath.put(StreetName,"$.address.streetName" );
		GFSJsonPath.put(StreetPrimaryNumberText,"$.address.streetPrimaryNumberText" );
		GFSJsonPath.put(StreetSuffixText,"$.address.streetSuffixText" );
		GFSJsonPath.put(ZipPlusFourCode,"$.address.zipPlusFourCode" );
		GFSJsonPath.put(StateCode,"$.address.stateCode" );
		
		
	//	GFSJsonPath.put(AddressUnitDesignatorType,"$.address.addressUnitDesignatorType" );
	//	GFSJsonPath.put(AddressUnitIdentifier,"$.address.addressUnitIdentifier" );

		//--ALTERNATEAPPRAISALDECISIONDATA--//
		GFSJsonPath.put(AlternateAppraisalDecisionStatusType,"$.alternateAppraisalDecisionData.alternateAppraisalDecisionStatusType" );
		GFSJsonPath.put(AlternateAppraisalDecisionEffectiveDatetime,"$.alternateAppraisalDecisionData.alternateAppraisalDecisionEffectiveDatetime" );
		GFSJsonPath.put(AlternateAppraisalDecisionExpirationDatetime,"$.alternateAppraisalDecisionData.alternateAppraisalDecisionExpirationDatetime" );
		GFSJsonPath.put(AlternateAppraisalEligibilityDecision,"$.alternateAppraisalDecisionData.alternateAppraisalEligibilityDecision" );
		
		GFSJsonPath.put(EstimatedPropertyValueAmount,"$.alternateAppraisalDecisionData.estimatedPropertyValueAmount" );
		GFSJsonPath.put(MaximumAuthorizedLoanAmount,"$.alternateAppraisalDecisionData.maximumAuthorizedLoanAmount" );
		GFSJsonPath.put(SalesContractPriceAmount,"$.alternateAppraisalDecisionData.salesContractPriceAmount" );

		//--PV--//
		GFSJsonPath.put(PropertyValuationAmount,"$.propertyValuationData.propertyValuationAmount" );
		GFSJsonPath.put(PropertyValuationMethodType,"$.propertyValuationData.propertyValuationMethodType" );
		GFSJsonPath.put(PropertyValuationType,"$.propertyValuationData.propertyValuationType" );

//		GFSJsonPath.put(("PropertyValuationEffectiveDateTime").toLowerCase(),"$.propertyValuationData.propertyValuationEffectiveDateTime" );
//		GFSJsonPath.put(("PropertyAssessmentSourceType").toLowerCase(),"$.propertyValuationData.propertyAssessmentSourceType" );
		GFSJsonPath.put(LoanProcessingStageType,"$.propertyValuationData.loanProcessingStageType" );

		//--HVE1--//
		//propertyAppraiserValuationContainer
		GFSJsonPath.put(HomeValueExplorerMidPointValueEstimateAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerMidPointValueEstimateAmount" );
		GFSJsonPath.put(HomeValueExplorerMaximumValueAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerMaximumValueAmount" );
		GFSJsonPath.put(HomeValueExplorerMinimumValueAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerMinimumValueAmount" );
		GFSJsonPath.put(HomeValueExplorerForecastStandardDeviationRate,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerForecastStandardDeviationRate" );
		GFSJsonPath.put(HomeValueExplorerConfidenceLevelType,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerConfidenceLevelType" );
		GFSJsonPath.put(PropertyValuationEffectiveDateTime,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.propertyValuationEffectiveDateTime" );
//		GFSJsonPath.put(HomeValueExplorerAssessmentDateTime,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerAssessmentDateTime" );

		//GFSJsonPath.put(HomeValueExplorerOptionType,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerOptionType" );

		//propertyInspectionWaiverResult
		GFSJsonPath.put(AppraisalExistenceIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.appraisalExistenceIndicator" );
		GFSJsonPath.put(PriceGrowthThresholdAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.priceGrowthThresholdAmount" );
		GFSJsonPath.put(PropertyConditionIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyConditionIndicator" );
		GFSJsonPath.put(LegalityIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.legalityIndicator" );
		GFSJsonPath.put(PhysicalDeficiencyIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.physicalDeficiencyIndicator" );
		GFSJsonPath.put(HighestBestUseIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.highestBestUseIndicator" );
		GFSJsonPath.put(AppraisalCompletionAsIsIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.appraisalCompletionAsIsIndicator" );
		GFSJsonPath.put(NeighborhoodConformityIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.neighborhoodConformityIndicator" );
		GFSJsonPath.put(ARMsLengthContractIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.armsLengthContractIndicator" );
		GFSJsonPath.put(QualityAgeIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.qualityAgeIndicator" );
		GFSJsonPath.put(DaysOnMarketIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.daysOnMarketIndicator" );
		GFSJsonPath.put(DistressedIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.distressedIndicator" );
		GFSJsonPath.put(PropertyAgeCount,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyAgeCount" );
		GFSJsonPath.put(PublicRecordExistenceIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.publicRecordExistenceIndicator" );
		GFSJsonPath.put(MissingDataIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.missingDataIndicator" );
		GFSJsonPath.put(ActiveMLSListingIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.activeMLSListingIndicator" );
		GFSJsonPath.put(MultipleListingIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.multipleListingIndicator" );
		GFSJsonPath.put(QualityIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.qualityIndicator" );
//		GFSJsonPath.put(StructureBuildingMaterialQualityRatingIdentifier,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.structureBuildingMaterialQualityRatingIdentifier" );
//		GFSJsonPath.put(StructureBuildingMaterialQualityRatingType,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.structureBuildingMaterialQualityRatingType" );
		GFSJsonPath.put(PropertyLandUseIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyLandUseIndicator");
	    GFSJsonPath.put(PropertyPurchaseLotSizeOneAcreIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyPurchaseLotSizeOneAcreIndicator");
	    GFSJsonPath.put(PropertyRefinanceLotSizeTwoAcreIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyRefinanceLotSizeTwoAcreIndicator");
	    GFSJsonPath.put(PropertyUnitIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyUnitIndicator");
		//--HVE2--//
		//propertyAppraiserValuationContainer
		GFSJsonPath.put(HVE2HomeValueExplorerMidPointValueEstimateAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.homeValueExplorerMidPointValueEstimateAmount" );
		GFSJsonPath.put(HVE2HomeValueExplorerMaximumValueAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.homeValueExplorerMaximumValueAmount" );
		GFSJsonPath.put(HVE2HomeValueExplorerMinimumValueAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.homeValueExplorerMinimumValueAmount" );
		GFSJsonPath.put(HVE2HomeValueExplorerForecastStandardDeviationRate,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.homeValueExplorerForecastStandardDeviationRate" );
		GFSJsonPath.put(HVE2HomeValueExplorerConfidenceLevelType,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.homeValueExplorerConfidenceLevelType" );
		GFSJsonPath.put(HVE2PropertyValuationEffectiveDateTime,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.propertyValuationEffectiveDateTime" );
//		GFSJsonPath.put(HVE2HomeValueExplorerAssessmentDateTime,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.homeValueExplorerAssessmentDateTime" );

	//	GFSJsonPath.put(HVE2HomeValueExplorerOptionType,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.homeValueExplorerOptionType" );

		//propertyInspectionWaiverResult
		GFSJsonPath.put(HVE2AppraisalExistenceIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.appraisalExistenceIndicator" );
		GFSJsonPath.put(HVE2PriceGrowthThresholdAmount,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.priceGrowthThresholdAmount" );
		GFSJsonPath.put(HVE2PropertyConditionIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.propertyConditionIndicator" );
		GFSJsonPath.put(HVE2LegalityIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.legalityIndicator" );
		GFSJsonPath.put(HVE2PhysicalDeficiencyIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.physicalDeficiencyIndicator" );
		GFSJsonPath.put(HVE2HighestBestUseIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.highestBestUseIndicator" );
		GFSJsonPath.put(HVE2AppraisalCompletionAsIsIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.appraisalCompletionAsIsIndicator" );
		GFSJsonPath.put(HVE2NeighborhoodConformityIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.neighborhoodConformityIndicator" );
		GFSJsonPath.put(HVE2ARMsLengthContractIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.armsLengthContractIndicator" );
		GFSJsonPath.put(HVE2QualityAgeIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.qualityAgeIndicator" );
		GFSJsonPath.put(HVE2DaysOnMarketIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.daysOnMarketIndicator" );
		GFSJsonPath.put(HVE2DistressedIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.distressedIndicator" );
		GFSJsonPath.put(HVE2PropertyAgeCount,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.propertyAgeCount" );
		GFSJsonPath.put(HVE2PublicRecordExistenceIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.publicRecordExistenceIndicator" );
		GFSJsonPath.put(HVE2MissingDataIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.missingDataIndicator" );
		GFSJsonPath.put(HVE2ActiveMLSListingIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.activeMLSListingIndicator" );
		GFSJsonPath.put(HVE2MultipleListingIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.multipleListingIndicator" );
		GFSJsonPath.put(HVE2QualityIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.qualityIndicator" );
//		GFSJsonPath.put(HVE2StructureBuildingMaterialQualityRatingIdentifier,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.structureBuildingMaterialQualityRatingIdentifier" );
//		GFSJsonPath.put(HVE2StructureBuildingMaterialQualityRatingType,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.structureBuildingMaterialQualityRatingType" );
		GFSJsonPath.put(HVE2PropertyLandUseIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.propertyLandUseIndicator");
	    GFSJsonPath.put(HVE2PropertyPurchaseLotSizeOneAcreIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.propertyPurchaseLotSizeOneAcreIndicator");
	    GFSJsonPath.put(HVE2PropertyRefinanceLotSizeTwoAcreIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.propertyRefinanceLotSizeTwoAcreIndicator");
	    GFSJsonPath.put(HVE2PropertyUnitIndicator,"$.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyInspectionWaiverResult.propertyUnitIndicator");
	    GFSJsonPath.put(SourceApplicationName, "$.sourceApplicationName");
	    GFSJsonPath.put(ErrorMessage, "$.aceResponse.errors.error[0].errorResponse.message"); 
		// GFS GET REQUEST QUERY PARAMS
		//GFSJsonPath.put(, "$.loanIdentifications.loanIdentification[0].loanIdentifier");
	    
	    GFSJsonPath.put(AA_MessageCategory,"$.aceDecisionMessages.messages[0].messageCategory");             
	    GFSJsonPath.put(AA_MessageCode,"$.aceDecisionMessages.messages[0].messageCodeList[0].listText");
	    GFSJsonPath.put(AA_MessageDisplayFlag,"$.aceDecisionMessages.messages[0].messageDisplayFlag");           
	    GFSJsonPath.put(AA_MessageGroup1,"$.aceDecisionMessages.messages[0].messageGroup1List[0].listText");
	    GFSJsonPath.put(AA_MessageGroup2,"$.aceDecisionMessages.messages[0].messageGroup2List[0].listText");
	    GFSJsonPath.put(AA_MessageId,"$.aceDecisionMessages.messages[0].messageId");
	    GFSJsonPath.put(AA_MessageHeader,"$.aceDecisionMessages.messages[0].messageHeaderList[0].listText");
	    GFSJsonPath.put(AA_MessageType,"$.aceDecisionMessages.messages[0].messageType");
	    GFSJsonPath.put(AA_SequenceNumber,"$.aceDecisionMessages.messages[0].sequenceNumber");
	    GFSJsonPath.put(MinimumLoanAssessmentFormType,"$.aceAssessmentDecision.minimumLoanAssessmentFormType");
	    GFSJsonPath.put(PropertyValuationEffectiveDateTime,"$.aceAssessmentDecision.propertyValuationEffectiveDateTime");
	    GFSJsonPath.put(AlternateAppraisalDecisionExpirationDatetime,"$.aceAssessmentDecision.alternateAppraisalDecisionExpirationDatetime");
	    GFSJsonPath.put(AlternateAppraisalDecisionStatusType,"$.aceAssessmentDecision.alternateAppraisalDecisionStatusType");
	    GFSJsonPath.put(AlternateAppraisalEligibilityDecision,"$.aceAssessmentDecision.alternateAppraisalEligibilityDecision");
	    
	    
	    //serviceCallTracesData
	    
		HVE1 = new ArrayList<String>();
		HVE2 = new ArrayList<String>();
	    requestDataElements = new ArrayList<String>();
		matchingElements = new ArrayList<String>();
		loanDataElements=new ArrayList<String>();
		aceDataElements=new ArrayList<String>();
		presentValueDataElements=new ArrayList<String>();
		errorContainerElements=new ArrayList<String>();
		
		
		
		for (Map.Entry<String, String> elementsPath : GFSJsonPath.entrySet()) {
		
		    String elementName=elementsPath.getKey();
		    String elementPath=elementsPath.getValue();
		    if(elementName.equals("addressLineText")||
		    		elementName.equals("LoanID_LPKey")||
		    		elementName.equals("addressSourceType")||
		    		elementName.equals("addressMatchLevelType")||
		    		elementName.equals("cityName")||
		    		elementName.equals("postalCode")||
		    		elementName.equals("stateCode")||
		    		elementName.equals("streetName")||
		    		elementName.equals("streetPrimaryNumberText")||
		    		elementName.equals("streetSuffixText")||
		    		elementName.equals("zipPlusFourCode")||
		    		elementName.equals("addressUnitDesignatorType")||
		    		elementName.equals("addressUnitIdentifier")) {
		    	requestDataElements.add(elementName);
		    }
		  
		    else if(elementPath.startsWith("$.loanIdentifications") || 
					elementPath.startsWith("$.parties") ||
					elementPath.startsWith("$.address") ) {
					loanDataElements.add(elementName);
				}
			else if(elementPath.startsWith("$.propertyValuationData") ) {
					presentValueDataElements.add(elementName);
				}
			else if(elementPath.startsWith("$.homeValueExplorerDataContainer.homeValueExplorerData")) {
				HVE1.add(elementName);
			}
			else if(elementPath.startsWith("$.alternateAppraisalDecisionData") || 
					elementPath.startsWith("$.homeValueExplorerDataContainer")) {
					aceDataElements.add(elementName);
			}
			else if(elementPath.contains("$.aceServiceData.errors.error[0].errorResponse.message")) {
				errorContainerElements.add(elementName);
			}
			else if(elementPath.equals("$.loanIdentifications.loanIdentification[0].loanIdentifier") || 
					elementPath.equals("$.address.addressLineText") || 
				    elementPath.equals("$.address.postalCode")) {
				matchingElements.add(elementName);
				
			 }
		}
		   
	}
	   
	    public List<String> getRequestDataElements() {
		    return requestDataElements;
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
		public List<String> get4Elements(){
			return matchingElements;
		}
		public List<String> getHVE1Container() {
			return HVE1;
		}
	
		
		public List<String> getElementsList(String elementsGroup) {
			List<String> elementsList= null;
			if(elementsGroup.equalsIgnoreCase("loanData")) {
				elementsList=getLoanDataElements();
			}
			else if(elementsGroup.equalsIgnoreCase("aceData")) {
				elementsList=getAceDataElements();
			}
			else if(elementsGroup.equalsIgnoreCase("presentValueData")) {
				elementsList=getPresentValueDataElements();
			}
			else if(elementsGroup.equalsIgnoreCase("matchingElements")) {
				elementsList = get4Elements();
		    }
			else if(elementsGroup.equalsIgnoreCase("requestDataElements")) {
				elementsList = getRequestDataElements();	
		    }
			else if(elementsGroup.equalsIgnoreCase("HVE2")) { 
					elementsList = getHVE1Container(); 
		    }
			else if(elementsGroup.equalsIgnoreCase("HVE1")) { 
				elementsList = getHVE1Container(); 
			
			}
			return elementsList;
}

		
		
		public String getGFSElementPath(String elementName) {
			return GFSJsonPath.get(elementName);
		}
		
		public String getGFSDBElementPath(String elementName, String collectionName) {
			String elementPath=getGFSElementPath(elementName);

			switch(collectionName) {
				case "grandFatheringAceRequest":
					elementPath=elementPath.replace("$.", "$.aceRequest.");
					break;
				case "grandFatheringAceResponse":
					if(isLoanDataElement(elementName) ) {
						elementPath=elementPath.replace("$.", "$.loanData.");
					}
					else if(isErrorContainerElement(elementName) ) {
						elementPath=elementPath.replace("$.aceServiceData.", "$.aceResponse.");
					}
					else {
						elementPath=elementPath.replace("$.", "$.aceResponse.aceResponseData.");				}
					break;
				case "grandFatheringPresentValueResponse":
					if(isLoanDataElement(elementName) ) {
						elementPath=elementPath.replace("$.", "$.loanData.");
					}
					else if(isErrorContainerElement(elementName) ) {
						elementPath=elementPath.replace("$.presentValueServiceData.", "$.presentValueResponse.");
					}
					else {
						elementPath=elementPath.replace("$.", "$.presentValueResponse.presentValueResponseData.");				}
					break;
				case "grandFatheringPresentValueRequest":
					elementPath=elementPath.replace("$.", "$.presentValueRequest.");
					break;
				case "grandFatheringAceData":
					if(isLoanDataElement(elementName) ) {
						elementPath=elementPath.replace("$.", "$.loanData.");
					}
					else {
						elementPath=elementPath.replace("$.", "$.aceData.");				}
					break;
				case "grandFatheringPresentValueData":
					if(isLoanDataElement(elementName)) {
						elementPath=elementPath.replace("$.", "$.loanData.");
					}
					else {
						elementPath=elementPath.replace("$.", "$.presentValueData.");
					}
//				case "serviceCallTraces":
//					if (isEDSResponseData(elementName)) {
//						elementPath = element
//					}
//					break;
			}
			
			return elementPath;
		}
		
		boolean isAceDataElement(String elementName) {
			if(aceDataElements.contains(elementName.toLowerCase())) {
				return true;
			}
			return false;		
		}
		
		boolean isLoanDataElement(String elementName) {
			if(loanDataElements.contains(elementName.toLowerCase())) {
				return true;
			}
			return false;		
		}
		
		boolean isErrorContainerElement(String elementName) {
			if(errorContainerElements.contains(elementName.toLowerCase())) {
				return true;
			}
			return false;		
		}
		
		boolean isPVDataElement(String elementName) {
			if(presentValueDataElements.contains(elementName.toLowerCase())) {
				return true;
			}
			return false;		
		}	
		
		public String getSourceType(Document doc) {
			Document sourceContext=(Document) doc.get("transactionContext");
			return sourceContext.getString("sourceType");
		
		}
		
	
		public Map<String, String> getAllMapACE(String parameter) {
			Map<String, String> AllMap = new HashMap<>();
			
//		if(parameter.equals("PVData")) {
//			AllMap.putAll(ACEAPI20AAResponseData.getCurrentResponse().getAAData());
//			
//		}else	
			AllMap.putAll(ACEAPI20AARequestData.getCurrentRequest().getLoanData());
//			AllMap.putAll(ACEAPI20AARequestData.getCurrentRequest().getDerivedData());
			AllMap.putAll(ACEAPI20AARequestData.getCurrentRequest().getAddress());
//			AllMap.putAll(ACEAPI20AARequestData.getCurrentRequest().getPV1());
//			AllMap.putAll(ACEAPI20AARequestData.getCurrentRequest().getSellerPV());
//			AllMap.putAll(ACEAPI20AARequestData.getCurrentRequest().getPV2());
			AllMap.putAll(ACEAPI20AARequestData.getCurrentRequest().getHVE());
			AllMap.putAll(ACEAPI20AARequestData.getCurrentRequest().getCurrentHVE());
			AllMap.putAll(ACEAPI20AARequestData.getCurrentRequest().getHVE2());
			AllMap.putAll(ACEAPI20AAResponseData.getCurrentResponse().getAAData());
			return AllMap;
			
		}
		
//	public Map<String, String> getResponseData(){
//		Map<String, String > responseMap = new HashMap<>();
//		responseMap.putAll(ACEAPI20AAResponseData.getCurrentResponse().getAAData());
//			return getResponseData();
//		}
		
		public List<String> getAllElements(){
			List<String> allElements = new ArrayList();
			allElements.addAll(getRequestDataElements());
			allElements.addAll(getLoanDataElements());
		//	allElements.addAll(getPresentValueDataElements());
			allElements.addAll(getHVE1Container());
	        allElements.addAll(getAceDataElements());
			return allElements;
		}
		
		public List<String> getPresentValue() {
			
			List<String> presentValueElements = new ArrayList();
			presentValueElements.addAll(getPresentValueDataElements());
	//		presentValueElements.addAll(getLoanDataElements());
			return presentValueElements;
		}
		
		
		public Map< String, String> getAllMapPV() {
			Map<String, String> PVMap = new HashMap<>();
			PVMap.putAll(ACEAPI20AARequestData.getCurrentRequest().getPV2());
			PVMap.putAll(ACEAPI20AARequestData.getCurrentRequest().getLoanData());
			PVMap.putAll(ACEAPI20AARequestData.getCurrentRequest().getAddress());
			return PVMap;
		}
	
		public static boolean isNumberElement(String elementName) {
			if(elementName.endsWith("Amount") || 
					elementName.endsWith("Count") || elementName.endsWith("Percent") || elementName.endsWith("Percent1") || elementName.endsWith("Percent2") || elementName.endsWith("Rate") ) {
				return true;
			}
			return false;
		}
		
		public static boolean numbersEqual(String num1, String num2) {
			if((Double.valueOf(num1)).compareTo(Double.valueOf(num2)) == 0) {
				return true;
			}
			return false;
		}	
		
		public static String getEDMessage() throws Exception {
			
			Document doc = retriveLatestWithQry();
			ReadContext message = getMessage(doc);			
		return message.jsonString();
		}
	
		public static Document retriveLatestWithQry() throws Exception {
			Map<String, String> qryMap=new HashMap<String, String>();
			qryMap.put("contextMap.requestType", "RESPONSE");
			qryMap.put("contextMap.serviceName", "ED");			
			Document GFSDoc=MongoDBUtils.getDocByQry(serviceCallTraces, qryMap, SortCriteria);			
			return GFSDoc;
			}
		
		
		public static ReadContext getMessage(Document doc) {
//			System.out.println("doc message " + doc.getString("message")); 
			String message = doc.getString("message");
			ReadContext context = JsonPath.parse(message);
//			System.out.println("my doc " + context.jsonString());
			return context;
		}
		
}
	
		
	






