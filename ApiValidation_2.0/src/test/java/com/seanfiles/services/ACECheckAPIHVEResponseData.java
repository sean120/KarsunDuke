package com.seanfiles.services;

import static com.seanfiles.elements.ACECheckAPIElements.*;
import static com.seanfiles.services.BaseData.HVE_Response;

import java.util.HashMap;
import java.util.Map;

import com.seanfiles.db.ACECheckAPIDB;
import com.seanfiles.utils.XMLParserUtil;

public class ACECheckAPIHVEResponseData  extends BaseData{
	public ACECheckAPIHVEResponseData(String responseXMLStr) {
		HVEResponseXML=new XMLParserUtil(responseXMLStr);
		
		loadAddressElementsMap();
		loadHVEData();
	}
	
	private XMLParserUtil HVEResponseXML=null;
	Map<String, String> address;
	Map<String, String> HVE;

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
	}

	public Map<String, String> getHVE() {
		return HVE;
	}
	
	public Map<String, String> getAddress() {
		return address;
	}
	
	private static ACECheckAPIHVEResponseData currentResponse=null;
	
	public static ACECheckAPIHVEResponseData getCurrentResponse() {
		if(currentResponse == null) {
			try {
				ACECheckAPIDB.getCorrespondingDoc(HVE_Response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return currentResponse;
	}
	
	public static void setHVEResponse(String responseXMLStr) {
		currentResponse=new ACECheckAPIHVEResponseData(responseXMLStr);		
	}

	public static void clear() {
		currentResponse=null;
	}

}
