package com.seanfiles.services;

import static com.seanfiles.elements.ACECheckAPIElements.*;

import java.util.HashMap;
import java.util.Map;

import com.seanfiles.db.ACECheckAPIDB;
import com.seanfiles.utils.XMLParserUtil;

public class ACECheckAPIAARequestData  extends BaseData{
	
	private XMLParserUtil AARequestXML=null;
	
	Map<String, String> loanIdentifiers;
	Map<String, String> address;
	Map<String, String> PV1;
	Map<String, String> PV2;
	Map<String, String> HVE;
	Map<String, String> HVE2;

	public ACECheckAPIAARequestData(String requestXMLStr) {
		AARequestXML=new XMLParserUtil(requestXMLStr);
		
		loadAddressElementsMap();
		loadHVEData();
	}

	private void loadAddressElementsMap() {
		address= new HashMap<String, String>();		
		address.put(AddressLineText, 
				AARequestXML.getElementValueByXPath("//Address/AddressLineText/text()"));
		address.put(CityName, 
				AARequestXML.getElementValueByXPath("//Address/CityName/text()"));
		address.put(PostalCode, 
				AARequestXML.getElementValueByXPath("//Address/PostalCode/text()"));
		address.put(StateCode, 
				AARequestXML.getElementValueByXPath("//Address/FIPSStateAlphaCode/text()"));
		address.put(StreetName, 
				AARequestXML.getElementValueByXPath("//Address/StreetName/text()"));
		address.put(StreetPrimaryNumberText, 
				AARequestXML.getElementValueByXPath("//Address/StreetPrimaryNumberText/text()"));
		address.put(StreetSuffixText, 
				AARequestXML.getElementValueByXPath("//Address/StreetSuffixText/text()"));
		address.put(ZipPlusFourCode, 
				AARequestXML.getElementValueByXPath("//Address/ZipPlusFourCode/text()"));
	}

	private void loadHVEData() {
		HVE= new HashMap<String, String>();	
		HVE.put(HomeValueExplorerMidPointValueEstimateAmount, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=1]/HomeValueExplorerMidPointValueEstimateAmount/text()"));
		HVE.put(HomeValueExplorerMaximumValueAmount, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=1]/HomeValueExplorerMaximumValueAmount/text()"));
		HVE.put(HomeValueExplorerMinimumValueAmount, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=1]/HomeValueExplorerMinimumValueAmount/text()"));
		HVE.put(HomeValueExplorerForecastStandardDeviationRate, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=1]/HomeValueExplorerForecastStandardDeviationRate/text()"));
		HVE.put(HomeValueExplorerConfidenceLevelType, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=1]/HomeValueExplorerConfidenceLevelType/text()"));
		HVE.put(PropertyValuationEffectiveDateTime, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=1]/PropertyValuationEffectiveDateTime/text()"));
		HVE.put(HomeValueExplorerAssessmentDateTime, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=1]/HomeValueExplorerAssessmenntDateTime/text()"));
		HVE.put(HomeValueExplorerOptionType, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=1]/HomeValueExplorerOptionType/text()"));
		
		HVE.put(AppraisalExistenceIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/AppraisalExistenceIndicator/text()"));
		HVE.put(PriceGrowthThresholdAmount, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/PriceGrowthThresholdAmount/text()"));
		HVE.put(PropertyConditionIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/PropertyConditionIndicator/text()"));
		HVE.put(LegalityIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/LegalityIndicator/text()"));
		HVE.put(PhysicalDeficiencyIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/PhysicalDeficiencyIndicator/text()"));
		HVE.put(HighestBestUseIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/HighestBestUseIndicator/text()"));
		HVE.put(AppraisalCompletionAsIsIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/AppraisalCompletionAsIsIndicator/text()"));
		HVE.put(NeighborhoodConformityIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/NeighborhoodConformityIndicator/text()"));
		HVE.put(ARMsLengthContractIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/ARMsLengthContractIndicator/text()"));
		HVE.put(QualityAgeIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/QualityAgeIndicator/text()"));
		HVE.put(DaysOnMarketIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/DaysOnMarketIndicator/text()"));
		HVE.put(DistressedIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/DistressedIndicator/text()"));
		HVE.put(PropertyAgeCount, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/PropertyAgeCount/text()"));
		HVE.put(PublicRecordExistenceIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/PublicRecordExistenceIndicator/text()"));
		HVE.put(MissingDataIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/MissingDataIndicator/text()"));
		HVE.put(ActiveMLSListingIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/ActiveMLSListingIndicator/text()"));
		HVE.put(MultipleListingIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/MultipleListingIndicator/text()"));
		HVE.put(QualityIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/QualityIndicator/text()"));
		
		HVE.put(StructureBuildingMaterialQualityRatingType, 
				AARequestXML.getElementValueByXPath("//PropertyStructureAnalysisContainer/PropertyStructureAnalysis[StructureAnalysisSequenceNumber=1]/StructureBuildingMaterialQualityRatingType/text()"));		
		HVE.put(StructureBuildingMaterialQualityRatingIdentifier, 
				AARequestXML.getElementValueByXPath("//PropertyStructureAnalysisContainer[PropertyStructureAnalysis/StructureAnalysisSequenceNumber=1]/PropertyStructureAnalysisDerivation/StructureBuildingMaterialQualityRatingIdentifier/text()"));
		
		// New Elements for HVE/FDR-CCE-Disaster
		HVE.put(PredictedConditionScoreValue, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/PredictedConditionScoreValue/text()"));
		HVE.put(FemaDisasterName, 
				AARequestXML.getElementValueByXPath("//PropertyDisasters/PropertyDisasterContainer/PropertyDisaster/FEMADisasterName/text()"));
		HVE.put(AppraisalAlternativeDisasterEligibilityDescription, 
				AARequestXML.getElementValueByXPath("//PropertyDisasters/PropertyDisasterContainer/PropertyDisaster/AppraisalAlternativeDisasterEligibilityDescription/text()"));
		
		
		normalizeValues(HVE);
	}

	public Map<String, String> getAddress() {
		return address;
	}

	public Map<String, String> getHVE() {
		return HVE;
	}

	private static ACECheckAPIAARequestData currentRequest=null;
	
	public static ACECheckAPIAARequestData getCurrentRequest() {
		if(currentRequest == null) {
			try {
				ACECheckAPIDB.getCorrespondingDoc(AA_Request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return currentRequest;
	}
	
	public static void setAARequest(String requestXMLStr) {
		currentRequest=new ACECheckAPIAARequestData(requestXMLStr);
	}

	public static void clear() {
		currentRequest=null;
	}

}
