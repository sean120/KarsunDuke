package com.seanfiles.services;

import static com.seanfiles.elements.ACEAPI20Elements.*;
import static com.seanfiles.elements.ACEElements.PropertyLandUseIndicator;
import static com.seanfiles.elements.ACEElements.PropertyPurchaseLotSizeOneAcreIndicator;
import static com.seanfiles.elements.ACEElements.PropertyRefinanceLotSizeTwoAcreIndicator;
import static com.seanfiles.elements.ACEElements.PropertyUnitIndicator;
import static com.seanfiles.services.BaseData.HVE_Response;

import java.util.HashMap;
import java.util.Map;

import com.seanfiles.db.ACEAPI20DB;
import com.seanfiles.utils.XMLParserUtil;

public class ACEAPI20HVEResponseData  extends BaseData{
	public ACEAPI20HVEResponseData(String responseXMLStr) {
		HVEResponseXML=new XMLParserUtil(responseXMLStr);
		if(hasHVSValuation(HVEResponseXML)) {
			HVSDataFound=true;
			loadAddressElementsMap();
			loadRequestElementsMap();
			loadHVEData();
		}
		else {
			HVSDataFound=false;
		}
	}
	
	private void loadRequestElementsMap() {
		requestElementsMap= new HashMap<String, String>();	
		requestElementsMap.put(LoanID_LPT, 
				HVEResponseXML.getElementValueByXPath("//AlternativeLoanIdentifications/AlternativeLoanIdentification[LoanIdentifierType='LoanProspectorTransaction']/AlternateLoanIdentifier/text()"));
		requestElementsMap.put(LoanID_LPUL, 
				HVEResponseXML.getElementValueByXPath("//AlternativeLoanIdentifications/AlternativeLoanIdentification[LoanIdentifierType='LoanProspectorUniqueLoan']/AlternateLoanIdentifier/text()"));
		requestElementsMap.put(LoanID_LQA, 
				HVEResponseXML.getElementValueByXPath("//AlternativeLoanIdentifications/AlternativeLoanIdentification[LoanIdentifierType='LoanQualityAdvisor']/AlternateLoanIdentifier/text()"));
		requestElementsMap.put(SubscriberRequestCorrelationIdentifier, 
				HVEResponseXML.getElementValueByXPath("//ServiceRequest/SubscriberRequestCorrelationIdentifier/text()"));
		requestElementsMap.put(HVS_ClientSchemaVersionIdentifier, 
				HVEResponseXML.getElementValueByXPath("//ServiceRequest/ClientSchemaVersionIdentifier/text()"));
		requestElementsMap.put(HVS_RequestorIdentifier, 
				HVEResponseXML.getElementValueByXPath("//ServiceRequest/RequestorIdentifier/text()"));
		requestElementsMap.put(HVS_SchemaVersionIdentifier, 
				HVEResponseXML.getElementValueByXPath("//ServiceRequest/SchemaVersionIdentifier/text()"));
		requestElementsMap.put(HVS_ServiceName, 
				HVEResponseXML.getElementValueByXPath("//ServiceRequest/ServiceName/text()"));
		requestElementsMap.put(HVS_ServiceRequestDateTime, 
				HVEResponseXML.getElementValueByXPath("//ServiceRequest/ServiceRequestDateTime/text()"));
		requestElementsMap.put(HVS_ServiceRequestOperationName, 
				HVEResponseXML.getElementValueByXPath("//ServiceRequest/ServiceRequestOperationName/text()"));
	}

	private boolean hasHVSValuation(XMLParserUtil hVEResponseXML2) {
		if(HVEResponseXML.getElementValueByXPath("//Address/AddressLineText/text()") != null) {
			return true;
		}
		return false;
	}
	
	public boolean isHVEDataAvailable() {
		return HVSDataFound;
	}

	private XMLParserUtil HVEResponseXML=null;
	Map<String, String> address;
	Map<String, String> requestElementsMap;
	Map<String, String> HVE;
	boolean HVSDataFound=false;

	private void loadHVEData() {
		HVE= new HashMap<String, String>();	
		HVE.put(HomeValueExplorerMidPointValueEstimateAmount, 
				HVEResponseXML.getElementValueByXPath("//AVMValuations/AVMValuation[AVMMethodType='Hybrid']/AVMValueAmount/text()"));
		HVE.put(HomeValueExplorerMaximumValueAmount, 
				HVEResponseXML.getElementValueByXPath("//AVMValuations/AVMValuation[AVMMethodType='Hybrid']/AVMHighValueRangeAmount/text()"));
		HVE.put(HomeValueExplorerMinimumValueAmount, 
				HVEResponseXML.getElementValueByXPath("//AVMValuations/AVMValuation[AVMMethodType='Hybrid']/AVMLowValueRangeAmount/text()"));
		HVE.put(HomeValueExplorerForecastStandardDeviationRate, 
				HVEResponseXML.getElementValueByXPath("//AVMValuations/AVMValuation[AVMMethodType='Hybrid']/HVEStandardDeviationNumber/text()"));
		HVE.put(HomeValueExplorerConfidenceLevelType, 
				HVEResponseXML.getElementValueByXPath("//AVMValuations/AVMValuation[AVMMethodType='Hybrid']/AVMConfidenceScoreValue/text()"));
		HVE.put(PropertyValuationEffectiveDateTime, 
				HVEResponseXML.getElementValueByXPath("//ResponseDateTime/text()"));
		HVE.put(HomeValueExplorerAssessmentDateTime, 
				HVEResponseXML.getElementValueByXPath("//ResponseDateTime/text()"));
		
		HVE.put(AppraisalExistenceIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/AppraisalExistenceIndicator/text()"));
		HVE.put(PriceGrowthThresholdAmount, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/PriceGrowthThresholdAmount/text()"));
		HVE.put(PropertyConditionIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/PropertyConditionIndicator/text()"));
		HVE.put(LegalityIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/LegalityIndicator/text()"));
		HVE.put(PhysicalDeficiencyIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/PhysicalDeficiencyIndicator/text()"));
		HVE.put(HighestBestUseIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/HighestBestUseIndicator/text()"));
		HVE.put(AppraisalCompletionAsIsIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/AppraisalCompletionAsIsIndicator/text()"));
		HVE.put(NeighborhoodConformityIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/NeighborhoodConformityIndicator/text()"));
		HVE.put(ARMsLengthContractIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/ARMsLengthContractIndicator/text()"));
		HVE.put(QualityAgeIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/QualityAgeIndicator/text()"));
		HVE.put(DaysOnMarketIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/DaysOnMarketIndicator/text()"));
		HVE.put(DistressedIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/DistressedIndicator/text()"));
		HVE.put(PropertyAgeCount, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/PropertyAgeCount/text()"));
		HVE.put(PublicRecordExistenceIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/PublicRecordExistenceIndicator/text()"));
		HVE.put(MissingDataIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/MissingDataIndicator/text()"));
		HVE.put(ActiveMLSListingIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/ActiveMLSListingIndicator/text()"));
		HVE.put(MultipleListingIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/MultipleListingIndicator/text()"));
		HVE.put(QualityIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/QualityIndicator/text()"));
		
		HVE.put(PropertyLandUseIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/PropertyLandUseIndicator/text()"));
		HVE.put(PropertyPurchaseLotSizeOneAcreIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/PropertyPurchaseLotSizeOneAcreIndicator/text()"));
		HVE.put(PropertyRefinanceLotSizeTwoAcreIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/PropertyRefinanceLotSizeTwoAcreIndicator/text()"));
		HVE.put(PropertyUnitIndicator, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/PropertyUnitIndicator/text()"));

		HVE.put(StructureBuildingMaterialQualityRatingType, 
				HVEResponseXML.getElementValueByXPath("//PropertyStructureAnalysis/StructureBuildingMaterialQualityRatingType/text()"));		
		HVE.put(StructureBuildingMaterialQualityRatingIdentifier, 
				HVEResponseXML.getElementValueByXPath("//PropertyStructureAnalysisDerivation/StructureBuildingMaterialQualityRatingIdentifier/text()"));
		
		// New Elements for HVE/FDR-CCE-Disaster
		HVE.put(PredictedConditionScoreValue, 
				HVEResponseXML.getElementValueByXPath("//PropertyInspectionWaiverResult/PredictedConditionScoreValue/text()"));
		HVE.put(FemaDisasterName, 
				HVEResponseXML.getElementValueByXPath("//DisasterPropertyResult/FEMADisasterName/text()"));
		HVE.put(AppraisalAlternativeDisasterEligibilityDescription, 
				HVEResponseXML.getElementValueByXPath("//DisasterPropertyResult/AppraisalAlternativeDisasterEligibilityDescription/text()"));
		
		normalizeValues(HVE);
	}

	private void loadAddressElementsMap() {
		address= new HashMap<String, String>();	
		address.put(AddressLineText, 
				HVEResponseXML.getElementValueByXPath("//Address/AddressLineText/text()"));
		address.put(CityName, 
				HVEResponseXML.getElementValueByXPath("//Address/CityName/text()"));
		address.put(PostalCode, 
				HVEResponseXML.getElementValueByXPath("//Address/PostalCode/text()"));
		address.put(StateCode, 
				HVEResponseXML.getElementValueByXPath("//Address/FIPSStateAlphaCode/text()"));
		address.put(ZipPlusFourCode, 
				HVEResponseXML.getElementValueByXPath("//Address/ZIPPlusFourCode/text()"));
	}

	public Map<String, String> getHVE() {
		return HVE;
	}
	
	public Map<String, String> getAddress() {
		return address;
	}
	
	public Map<String, String> getRequestElements() {
		return requestElementsMap;
	}
	
	private static ACEAPI20HVEResponseData currentResponse=null;
	
	public static ACEAPI20HVEResponseData getCurrentResponse() {
		if(currentResponse == null) {
			try {
				ACEAPI20DB.getCorrespondingDoc(HVE_Response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return currentResponse;
	}
	
	public static void setHVEResponse(String responseXMLStr) {
		currentResponse=new ACEAPI20HVEResponseData(responseXMLStr);		
	}

	public static void clear() {
		currentResponse=null;
	}

}
