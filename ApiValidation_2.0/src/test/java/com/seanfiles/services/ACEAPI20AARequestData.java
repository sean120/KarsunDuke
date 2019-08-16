package com.seanfiles.services;

import static com.seanfiles.elements.ACEAPI20Elements.*;
import static com.seanfiles.services.BaseData.AA_Request;

import java.util.HashMap;
import java.util.Map;

import com.seanfiles.db.ACEAPI20DB;
import com.seanfiles.utils.XMLParserUtil;

public class ACEAPI20AARequestData  extends BaseData{
	
	private XMLParserUtil AARequestXML=null;
	
	Map<String, String> loanData;
	Map<String, String> derivedData;
	Map<String, String> address;
	Map<String, String> PV1;
	Map<String, String> PV2;
	Map<String, String> sellerPV;
	Map<String, String> HVE;
	Map<String, String> HVE2;
	Map<String, String> currentHVE;
	Map<String, String> commFailures;

	public ACEAPI20AARequestData(String requestJSONStr) {
		AARequestXML=new XMLParserUtil(requestJSONStr);
		
		loadLoanDataElementsMap();
		loadDerivedDataElementsMap();
		loadAddressElementsMap();
		loadOriginalPVData();
		loadSubsequentPVData();
		loadHVEData();
		loadHVE2Data();
		loadSellerPVData();
		loadCommFailures();
	}

	private void loadCommFailures() {
		commFailures= new HashMap<String, String>();		
		commFailures.put(RDSCommunicationFailureType, 
				AARequestXML.getElementValueByXPath(" //CommunicationFailureDerivedContainer/RDSCommunicationFailureDerivation/RDSCommunicationFailureType/text()"));
		commFailures.put(CRACommunicationFailureType, 
				AARequestXML.getElementValueByXPath(" //CommunicationFailureDerivedContainer/CRACommunicationFailureDerivation/CRACommunicationFailureType/text()"));
		commFailures.put(HVSCommunicationFailureType, 
				AARequestXML.getElementValueByXPath(" //CommunicationFailureDerivedContainer/HVSCommunicationFailureDerivation/HVSCommunicationFailureType/text()"));
	}

	private void loadAddressElementsMap() {
		address= new HashMap<String, String>();		
		address.put(AddressLineText, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/AddressLineText/text()"));
		address.put(AddressMatchLevelType, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/AddressMatchLevelType/text()"));
		address.put(AddressSourceType, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/AddressSourceType/text()"));
		address.put(CityName, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/CityName/text()"));
		address.put(StateCode, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/FIPSStateAlphaCode/text()"));
		address.put(PostalCode, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/PostalCode/text()"));
		address.put(StreetName, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/StreetName/text()"));
		address.put(StreetPrimaryNumberText, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/StreetPrimaryNumberText/text()"));
		address.put(StreetSuffixText, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/StreetSuffixText/text()"));
		address.put(ZipPlusFourCode, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/ZipPlusFourCode/text()"));
				
		address.put(AddressUnitDesignatorType, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/AddressUnitDesignatorType/text()"));
		address.put(AddressUnitIdentifier, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/AddressUnitIdentifier/text()"));
		address.put(CarrierRouteType, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/CarrierRouteType/text()"));
		address.put(HighwayContractIdentifier, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/HighwayContractIdentifier/text()"));
		address.put(PostOfficeBoxIdentifier, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/PostOfficeBoxIdentifier/text()"));
		address.put(RuralRouteIdentifier, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/RuralRouteIdentifier/text()"));
		address.put(StreetPostDirectionalText, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/StreetPostDirectionalText/text()"));
		address.put(StreetPreDirectionalText, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=1]/StreetPreDirectionalText/text()"));

		address.put(PreScrub_AddressSourceType, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=2]/AddressSourceType/text()"));
		address.put(PreScrub_PostalCode, 
				AARequestXML.getElementValueByXPath(" // Address[AddressIdentifier=2]/PostalCode/text()"));
		
		normalizeValues(address);
	}

	private void loadHVE2Data() {
		HVE2= new HashMap<String, String>();
		HVE2.put(HVE2HomeValueExplorerMidPointValueEstimateAmount, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=2]/HomeValueExplorerMidPointValueEstimateAmount/text()"));
		HVE2.put(HVE2HomeValueExplorerMaximumValueAmount, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=2]/HomeValueExplorerMaximumValueAmount/text()"));
		HVE2.put(HVE2HomeValueExplorerMinimumValueAmount, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=2]/HomeValueExplorerMinimumValueAmount/text()"));
		HVE2.put(HVE2HomeValueExplorerForecastStandardDeviationRate, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=2]/HomeValueExplorerForecastStandardDeviationRate/text()"));
		HVE2.put(HVE2HomeValueExplorerConfidenceLevelType, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=2]/HomeValueExplorerConfidenceLevelType/text()"));
		HVE2.put(HVE2PropertyValuationEffectiveDateTime, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=2]/PropertyValuationEffectiveDateTime/text()"));
		HVE2.put(HVE2HomeValueExplorerAssessmentDateTime, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=2]/HomeValueExplorerAssessmenntDateTime/text()"));
		HVE2.put(HVE2HomeValueExplorerOptionType, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuationAssessment[PropertyValuationAssessmentSequenceNumber=2]/HomeValueExplorerOptionType/text()"));
		
		HVE2.put(HVE2AppraisalExistenceIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/AppraisalExistenceIndicator/text()"));
		HVE2.put(HVE2PriceGrowthThresholdAmount, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/PriceGrowthThresholdAmount/text()"));
		HVE2.put(HVE2PropertyConditionIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/PropertyConditionIndicator/text()"));
		HVE2.put(HVE2LegalityIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/LegalityIndicator/text()"));
		HVE2.put(HVE2PhysicalDeficiencyIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/PhysicalDeficiencyIndicator/text()"));
		HVE2.put(HVE2HighestBestUseIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/HighestBestUseIndicator/text()"));
		HVE2.put(HVE2AppraisalCompletionAsIsIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/AppraisalCompletionAsIsIndicator/text()"));
		HVE2.put(HVE2NeighborhoodConformityIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/NeighborhoodConformityIndicator/text()"));
		HVE2.put(HVE2ARMsLengthContractIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/ARMsLengthContractIndicator/text()"));
		HVE2.put(HVE2QualityAgeIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/QualityAgeIndicator/text()"));
		HVE2.put(HVE2DaysOnMarketIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/DaysOnMarketIndicator/text()"));
		HVE2.put(HVE2DistressedIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/DistressedIndicator/text()"));
		HVE2.put(HVE2PropertyAgeCount, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/PropertyAgeCount/text()"));
		HVE2.put(HVE2PublicRecordExistenceIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/PublicRecordExistenceIndicator/text()"));
		HVE2.put(HVE2MissingDataIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/MissingDataIndicator/text()"));
		HVE2.put(HVE2ActiveMLSListingIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/ActiveMLSListingIndicator/text()"));
		HVE2.put(HVE2MultipleListingIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/MultipleListingIndicator/text()"));
		HVE2.put(HVE2QualityIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/QualityIndicator/text()"));
		
		HVE2.put(HVE2PropertyLandUseIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/PropertyLandUseIndicator/text()"));
		HVE2.put(HVE2PropertyPurchaseLotSizeOneAcreIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/PropertyPurchaseLotSizeOneAcreIndicator/text()"));
		HVE2.put(HVE2PropertyRefinanceLotSizeTwoAcreIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/PropertyRefinanceLotSizeTwoAcreIndicator/text()"));
		HVE2.put(HVE2PropertyUnitIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/PropertyUnitIndicator/text()"));
		
		HVE2.put(HVE2StructureBuildingMaterialQualityRatingType, 
				AARequestXML.getElementValueByXPath("//PropertyStructureAnalysisContainer/PropertyStructureAnalysis[StructureAnalysisSequenceNumber=2]/StructureBuildingMaterialQualityRatingType/text()"));		
		HVE2.put(HVE2StructureBuildingMaterialQualityRatingIdentifier, 
				AARequestXML.getElementValueByXPath("//PropertyStructureAnalysisContainer[PropertyStructureAnalysis/StructureAnalysisSequenceNumber=2]/PropertyStructureAnalysisDerivation/StructureBuildingMaterialQualityRatingIdentifier/text()"));
		
		// New Elements for HVE/FDR-CCE-Disaster
		HVE2.put(HVE2PredictedConditionScoreValue, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=2]/PredictedConditionScoreValue/text()"));
		HVE2.put(HVE2FemaDisasterName, 
				AARequestXML.getElementValueByXPath("//PropertyDisasters/PropertyDisasterContainer[PropertyDisasterDerivation/PropertyDisasterSequenceNumber=2]/PropertyDisaster/FEMADisasterName/text()"));
		HVE2.put(HVE2AppraisalAlternativeDisasterEligibilityDescription, 
				AARequestXML.getElementValueByXPath("//PropertyDisasters/PropertyDisasterContainer[PropertyDisasterDerivation/PropertyDisasterSequenceNumber=2]/PropertyDisaster/AppraisalAlternativeDisasterEligibilityDescription/text()"));

		normalizeValues(HVE2);
		copyToCurrentHVE(HVE2);
	}

	private void copyToCurrentHVE(Map<String, String> HVE2) {
		currentHVE= new HashMap<String, String>();
		for (Map.Entry<String, String> mapItem : HVE2.entrySet())
		{
			String elementName=mapItem.getKey();
			currentHVE.put(elementName.substring(4), mapItem.getValue());
		}
		
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
		
		HVE.put(PropertyLandUseIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/PropertyLandUseIndicator/text()"));
		HVE.put(PropertyPurchaseLotSizeOneAcreIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/PropertyPurchaseLotSizeOneAcreIndicator/text()"));
		HVE.put(PropertyRefinanceLotSizeTwoAcreIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/PropertyRefinanceLotSizeTwoAcreIndicator/text()"));
		HVE.put(PropertyUnitIndicator, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/PropertyUnitIndicator/text()"));
		
		HVE.put(StructureBuildingMaterialQualityRatingType, 
				AARequestXML.getElementValueByXPath("//PropertyStructureAnalysisContainer/PropertyStructureAnalysis[StructureAnalysisSequenceNumber=1]/StructureBuildingMaterialQualityRatingType/text()"));		
		HVE.put(StructureBuildingMaterialQualityRatingIdentifier, 
				AARequestXML.getElementValueByXPath("//PropertyStructureAnalysisContainer[PropertyStructureAnalysis/StructureAnalysisSequenceNumber=1]/PropertyStructureAnalysisDerivation/StructureBuildingMaterialQualityRatingIdentifier/text()"));
		
		// New Elements for HVE/FDR-CCE-Disaster
		HVE.put(PredictedConditionScoreValue, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult[BusinessDecisionResultIdentifier=1]/PredictedConditionScoreValue/text()"));
		HVE.put(FemaDisasterName, 
				AARequestXML.getElementValueByXPath("//PropertyDisasters/PropertyDisasterContainer[PropertyDisasterDerivation/PropertyDisasterSequenceNumber=1]/PropertyDisaster/FEMADisasterName/text()"));
		HVE.put(AppraisalAlternativeDisasterEligibilityDescription, 
				AARequestXML.getElementValueByXPath("//PropertyDisasters/PropertyDisasterContainer[PropertyDisasterDerivation/PropertyDisasterSequenceNumber=1]/PropertyDisaster/AppraisalAlternativeDisasterEligibilityDescription/text()"));
		
		normalizeValues(HVE);
	}

	private void loadSubsequentPVData() {
		PV2= new HashMap<String, String>();		
		PV2.put(PropertyValuationAmount, 
				AARequestXML.getElementValueByXPath("//PropertyValuationContainer/PropertyValuation[PropertyValuationType='Subsequent']/PropertyValuationAmount/text()"));
		PV2.put(PropertyValuationMethodType, 
				AARequestXML.getElementValueByXPath("//PropertyValuationContainer/PropertyValuation[PropertyValuationType='Subsequent']/PropertyValuationMethodType/text()"));
		PV2.put(PropertyValuationType, 
				AARequestXML.getElementValueByXPath("//PropertyValuationContainer/PropertyValuation[PropertyValuationType='Subsequent']/PropertyValuationType/text()"));
		normalizeValues(PV2);
	}

	private void loadSellerPVData() {
		sellerPV= new HashMap<String, String>();		
		sellerPV.put(EST_O_PropertyValuationAmount, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuation[PropertyValuationSequenceNumber='3']/PropertyValuationAmount/text()"));
		sellerPV.put(EST_O_PropertyValuationMethodType, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuation[PropertyValuationSequenceNumber='3']/PropertyValuationMethodType/text()"));
		sellerPV.put(EST_O_PropertyValuationType, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuation[PropertyValuationSequenceNumber='3']/PropertyValuationType/text()"));
		sellerPV.put(EST_O_PropertyValuationEffectiveDateTime, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuation[PropertyValuationSequenceNumber='3']/PropertyValuationEffectiveDateTime/text()"));
		sellerPV.put(EST_O_PropertyAssessmentSourceType, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyAssessment[PropertyAssessmentSequenceNumber='3']/PropertyAssessmentSourceType/text()"));

		sellerPV.put(EST_S_PropertyValuationAmount, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuation[PropertyValuationSequenceNumber='4']/PropertyValuationAmount/text()"));
		sellerPV.put(EST_S_PropertyValuationMethodType, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuation[PropertyValuationSequenceNumber='4']/PropertyValuationMethodType/text()"));
		sellerPV.put(EST_S_PropertyValuationType, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuation[PropertyValuationSequenceNumber='4']/PropertyValuationType/text()"));
		sellerPV.put(EST_S_PropertyValuationEffectiveDateTime, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyValuation[PropertyValuationSequenceNumber='4']/PropertyValuationEffectiveDateTime/text()"));
		sellerPV.put(EST_S_PropertyAssessmentSourceType, 
				AARequestXML.getElementValueByXPath("//PropertyAppraiserValuationContainer/PropertyAssessment[PropertyAssessmentSequenceNumber='4']/PropertyAssessmentSourceType/text()"));

		sellerPV.put(FR_PropertyValuationAmount, 
				AARequestXML.getElementValueByXPath("//PropertyValuationContainer/PropertyValuation[PropertyValuationSequenceNumber='7']/PropertyValuationAmount/text()"));
		sellerPV.put(FR_PropertyValuationMethodType, 
				AARequestXML.getElementValueByXPath("//PropertyValuationContainer/PropertyValuation[PropertyValuationSequenceNumber='7']/PropertyValuationMethodType/text()"));

		normalizeValues(sellerPV);
	}

	private void loadOriginalPVData() {
		PV1= new HashMap<String, String>();		
		PV1.put(PropertyValuationAmount, 
				AARequestXML.getElementValueByXPath("//PropertyValuationContainer/PropertyValuation[PropertyValuationType='Original']/PropertyValuationAmount/text()"));
		PV1.put(PropertyValuationMethodType, 
				AARequestXML.getElementValueByXPath("//PropertyValuationContainer/PropertyValuation[PropertyValuationType='Original']/PropertyValuationMethodType/text()"));
		PV1.put(PropertyValuationType, 
				AARequestXML.getElementValueByXPath("//PropertyValuationContainer/PropertyValuation[PropertyValuationType='Original']/PropertyValuationType/text()"));
		normalizeValues(PV1);
	}

	private void loadDerivedDataElementsMap() {
		derivedData= new HashMap<String, String>();
		
		derivedData.put(EDS_BusinessDecisionEvaluationType, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionContainer/BusinessDecision[BusinessDecisionIdentifier=1]/BusinessDecisionEvaluationType/text()"));
		derivedData.put(EDS_BusinessDecisionResultSourceType, 
				AARequestXML.getElementValueByXPath("//BusinessDecisionResultContainer/BusinessDecisionResult[BusinessDecisionResultIdentifier=1]/BusinessDecisionResultSourceType/text()"));
		derivedData.put(EDS_EvaluationResultMessageSourceType, AARequestXML.getElementValueByXPath("//BusinessDecisionResultMessages/EvaluationResultMessage[BusinessDecisionResultIdentifier=1]/EvaluationResultMessageSourceType/text()"));
		derivedData.put(EDS_LoanRoleType, AARequestXML.getElementValueByXPath("//LoanRelationships/LoanRelationship/LoanRoleType/text()"));
		//derivedData.put(EDS_LoanRoleType1, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[LoanState/LoanStateType='AtClosing']/LoanDetailContainer/LoanRelationships/LoanRelationship/LoanRoleType/text()"));
		//derivedData.put(EDS_LoanRoleType2, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[LoanState/LoanStateType='Current']/LoanDetailContainer/LoanRelationships/LoanRelationship/LoanRoleType/text()"));

		derivedData.put(EDS_ClientSchemaVersionIdentifier, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/ClientSchemaVersionIdentifier/text()"));
		derivedData.put(EDS_EDSSchemaVersionIdentifier, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/EDSSchemaVersionIdentifier/text()"));
		derivedData.put(EDS_RequestorIdentifier, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/RequestorIdentifier/text()"));
		derivedData.put(EDS_ServiceName, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/ServiceName/text()"));
		derivedData.put(EDS_ServiceRequestIdentifier, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/ServiceRequestIdentifier/text()"));
		derivedData.put(EDS_ServiceRequestOperationName, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/ServiceRequestOperationName/text()"));
		derivedData.put(EDS_ServiceRequestType, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/ServiceRequestType/text()"));
		derivedData.put(EDS_SubscriberIdentifier, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/SubscriberIdentifier/text()"));
		derivedData.put(EDS_SubscriberRequestCorrelationIdentifier, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/SubscriberRequestCorrelationIdentifier/text()"));
	}
	
	private void loadLoanDataElementsMap() {
		loanData= new HashMap<String, String>();		
		loanData.put(LoanID_LPKey, 
				AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/AlternativeLoanIdentifications/AlternativeLoanIdentification[LoanIdentifierType='LoanProspectorKey']/AlternateLoanIdentifier/text()"));
		loanData.put(LoanID_LPT, 
				AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/AlternativeLoanIdentifications/AlternativeLoanIdentification[LoanIdentifierType='LoanProspectorTransaction']/AlternateLoanIdentifier/text()"));
		loanData.put(LoanID_LPUL, 
				AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/AlternativeLoanIdentifications/AlternativeLoanIdentification[LoanIdentifierType='LoanProspectorUniqueLoan']/AlternateLoanIdentifier/text()"));
		loanData.put(LoanID_HPGT, 
				AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/AlternativeLoanIdentifications/AlternativeLoanIdentification[LoanIdentifierType='ExtendedHewlettPackardLoanProspectorTransaction']/AlternateLoanIdentifier/text()"));
		loanData.put(LoanID_HPLPT, 
				AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/AlternativeLoanIdentifications/AlternativeLoanIdentification[LoanIdentifierType='HewlettPackardLoanProspectorTransaction']/AlternateLoanIdentifier/text()"));
		loanData.put(LoanID_LQA, 
				AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/AlternativeLoanIdentifications/AlternativeLoanIdentification[LoanIdentifierType='LoanQualityAdvisor']/AlternateLoanIdentifier/text()"));
		loanData.put(LoanID_SLS, 
				AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/AlternativeLoanIdentifications/AlternativeLoanIdentification[LoanIdentifierType='SubmissionLinkService']/AlternateLoanIdentifier/text()"));
				
				
		loanData.put(PartyID_SELLER, 
				AARequestXML.getElementValueByXPath("//LoanAcquisition/SellerAccountIdentifier/text()"));
		
		loanData.put(AppraisalIdentifier, AARequestXML.getElementValueByXPath("//PropertyValuationContainer/Appraisal/AppraisalIdentifier/text()"));
		loanData.put(CraCommunicationFailureType, AARequestXML.getElementValueByXPath("//CommunicationFailureDerivedContainer/CRACommunicationFailureDerivation/CRACommunicationFailureType/text()"));
		loanData.put(DerivationRiskClassType, AARequestXML.getElementValueByXPath("//CreditRiskDerivation/DerivationRiskClassType/text()"));
		loanData.put(DerivedCalculationSourceType, AARequestXML.getElementValueByXPath("//CreditRiskDerivation/DerivedCalculationSourceType/text()"));
		loanData.put(StrategicOfferingCreditRiskType, AARequestXML.getElementValueByXPath("//CreditRiskDerivation/StrategicOfferingCreditRiskType/text()"));
		loanData.put(MessageCode, AARequestXML.getElementValueByXPath("//BusinessDecisionResultMessages/EvaluationResultMessage[BusinessDecisionResultIdentifier=1]/EvaluationResultMessageCode/text()"));
		loanData.put(MessageText, AARequestXML.getElementValueByXPath("//BusinessDecisionResultMessages/EvaluationResultMessage[BusinessDecisionResultIdentifier=1]/EvaluationResultMessageText/text()"));
		loanData.put(ConstructionLoanIndicator, AARequestXML.getElementValueByXPath("//Loan/ConstructionLoanIndicator/text()"));
		loanData.put(LoanProcessingStageType, AARequestXML.getElementValueByXPath("//Loan/LoanProcessingStageType/text()"));
		loanData.put(LoanPurposeType, AARequestXML.getElementValueByXPath("//Loan/LoanPurposeType/text()"));
		loanData.put(LoanRefinanceCashOutDeterminationType, AARequestXML.getElementValueByXPath("//Loan/LoanRefinanceCashOutDeterminationType/text()"));
		loanData.put(MortgageType, AARequestXML.getElementValueByXPath("//Loan/MortgageType/text()"));
		loanData.put(LoanConformityType, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[LoanState/LoanStateType='Current']/LoanDetailContainer/LoanAcquisitionContainer/LoanAcquisition/LoanConformityType/text()"));
		loanData.put(SellerAccountIdentifier, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[LoanState/LoanStateType='Current']/LoanDetailContainer/LoanAcquisitionContainer/LoanAcquisition/SellerAccountIdentifier/text()"));
		loanData.put(SellerLoanIdentifier, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[LoanState/LoanStateType='Current']/LoanDetailContainer/LoanAcquisitionContainer/LoanAcquisition/SellerLoanIdentifier/text()"));
		//loanData.put(LA_LoanIdentifier, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[LoanState/LoanStateType='Current']/LoanDetailContainer/LoanAcquisitionContainer/LoanAcquisition/LoanIdentifer/text()"));
		loanData.put(BorrowerPropertyPurchasePriceAmount, AARequestXML.getElementValueByXPath("//LoanApplication/BorrowerPropertyPurchasePriceAmount/text()"));
		loanData.put(LoanApplicationBaseLoanAmount, AARequestXML.getElementValueByXPath("//LoanApplication/LoanApplicationBaseLoanAmount/text()"));
		loanData.put(MiAndFundingFeeFinancedAmount, AARequestXML.getElementValueByXPath("//LoanApplication/MIAndFundingFeeFinancedAmount/text()"));
		loanData.put(LtvRatioPercent1, AARequestXML.getElementValueByXPath("//LoanCollateralRiskAssessmentContainer/LoanCollateralRiskAssessment[LoanScopeType='Combined']/LTVRatioPercent/text()"));
		loanData.put(LtvSourceType1, AARequestXML.getElementValueByXPath("//LoanCollateralRiskAssessmentContainer/LoanCollateralRiskAssessment[LoanScopeType='Combined']/LTVSourceType/text()"));
		loanData.put(LoanScopeType1, AARequestXML.getElementValueByXPath("//LoanCollateralRiskAssessmentContainer/LoanCollateralRiskAssessment[LoanScopeType='Combined']/LoanScopeType/text()"));
		loanData.put(LoanScopeType2, AARequestXML.getElementValueByXPath("//LoanCollateralRiskAssessmentContainer/LoanCollateralRiskAssessment[LoanScopeType='SubjectLoan']/LoanScopeType/text()"));
		loanData.put(LtvRatioPercent2, AARequestXML.getElementValueByXPath("//LoanCollateralRiskAssessmentContainer/LoanCollateralRiskAssessment[LoanScopeType='SubjectLoan']/LTVRatioPercent/text()"));
		loanData.put(LtvSourceType2, AARequestXML.getElementValueByXPath("//LoanCollateralRiskAssessmentContainer/LoanCollateralRiskAssessment[LoanScopeType='SubjectLoan']/LTVSourceType/text()"));
		loanData.put(ConstructionLoanType, AARequestXML.getElementValueByXPath("//LoanConversionRuleContainer/LoanConversionRule/ConstructionLoanType/text()"));
		loanData.put(MiPremiumFinancedAmount, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer/LoanDetailContainer/InsurancePolicyContainer/LoanMI/MIPremiumFinancedAmount/text()"));
		//loanData.put(LoanMI_LoanIdentifier, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer/LoanDetailContainer/InsurancePolicyContainer/LoanMI/LoanIdentifier/text()"));
		loanData.put(MiPremiumFinancedIndicator, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer/LoanDetailContainer/InsurancePolicyContainer/LoanMI/MIPremiumFinancedIndicator/text()"));
		loanData.put(InvestorCollateralProgramType, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[LoanState/LoanStateType='Current']/LoanDetailContainer/LoanOriginationContainer/LoanOrigination/InvestorCollateralProgramType/text()"));
		//loanData.put(LS1_LO_LoanIdentifier, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[LoanState/LoanStateType='Current']/LoanDetailContainer/LoanOriginationContainer/LoanOrigination/LoanIdentifier/text()"));
		loanData.put(LoanOriginationSystemVersionIdentifier, AARequestXML.getElementValueByXPath("//LoanOrigination/LoanOriginationSystemVersionIdentifier/text()"));
		loanData.put(LoanPropertyUsageType, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[LoanState/LoanStateType='AtClosing']/LoanDetailContainer/LoanOriginationContainer/LoanOrigination/LoanPropertyUsageType/text()"));
		//loanData.put(LS2_LO_LoanIdentifier, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[LoanState/LoanStateType='AtClosing']/LoanDetailContainer/LoanOriginationContainer/LoanOrigination/LoanIdentifier/text()"));
		loanData.put(NoteAmount, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[LoanState/LoanStateType='AtClosing']/LoanDetailContainer/LoanOriginationContainer/LoanOrigination/NoteAmount/text()"));
		loanData.put(FinancedUnitCount, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanPropertyCollaterals/LoanPropertyCollateralContainer/LoanPropertyCollateral/FinancedUnitCount/text()"));
		//loanData.put(LPC_LoanIdentifier, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanPropertyCollaterals/LoanPropertyCollateralContainer/LoanPropertyCollateral/LoanIdentifier/text()"));
		//loanData.put(LPC_PropertyIdentifier, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanPropertyCollaterals/LoanPropertyCollateralContainer/LoanPropertyCollateral/PropertyIdentifier/text()"));
		loanData.put(LoanQualityAdvisorResultType, AARequestXML.getElementValueByXPath("//BusinessDecisionResultDetailContainer/LoanQualityResult[BusinessDecisionResultIdentifier=1]/LoanQualityAdvisorResultType/text()"));
		loanData.put(AutomatedUnderwritingSystemType, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[LoanState/LoanStateType='Current']/LoanDetailContainer/LoanOriginationContainer/LoanUnderwritingCases/LoanUnderwritingCaseContainer/LoanUnderwritingCase/AutomatedUnderwritingSystemType/text()"));
		//loanData.put(LoanUnderwritingCaseIdentifier, AARequestXML.getElementValueByXPath("//LoanUnderwritingDecision/LoanUnderwritingCaseIdentifier/text()"));
		loanData.put(LoanUnderwritingDecisionDefaultProbabilityRate, AARequestXML.getElementValueByXPath("//LoanUnderwritingDecision/LoanUnderwritingDecisionDefaultProbabilityRate/text()"));
		loanData.put(LoanUnderwritingCaseIdentifier, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[LoanState/LoanStateType='Current']/LoanDetailContainer/LoanOriginationContainer/LoanUnderwritingCases/LoanUnderwritingCaseContainer/LoanUnderwritingDecisions/LoanUnderwritingDecisionContainer/LoanUnderwritingDecision/LoanUnderwritingCaseIdentifier/text()"));
		loanData.put(LoanUnderwritingDecisionDefaultProbabilityRate, AARequestXML.getElementValueByXPath("//EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[LoanState/LoanStateType='Current']/LoanDetailContainer/LoanOriginationContainer/LoanUnderwritingCases/LoanUnderwritingCaseContainer/LoanUnderwritingDecisions/LoanUnderwritingDecisionContainer/LoanUnderwritingDecision/LoanUnderwritingDecisionDefaultProbabilityRate/text()"));
		loanData.put(ProductDescription, AARequestXML.getElementValueByXPath("//ProductDefinition/ProductDescription/text()"));
		loanData.put(ProductIdentifier, AARequestXML.getElementValueByXPath("//ProductDefinition/ProductIdentifier/text()"));
		loanData.put(ProductType, AARequestXML.getElementValueByXPath("//ProductDefinition/ProductType/text()"));
		loanData.put(PropertyCategoryType, AARequestXML.getElementValueByXPath("//Property/PropertyCategoryType/text()"));
		loanData.put(PropertyEstateType, AARequestXML.getElementValueByXPath("//PropertyTitle/PropertyEstateType/text()"));
		loanData.put(RdsCommunicationFailureType, AARequestXML.getElementValueByXPath("//CommunicationFailureDerivedContainer/RDSCommunicationFailureDerivation/RDSCommunicationFailureTypeailureType/text()"));
		loanData.put(ProjectAttachmentType, AARequestXML.getElementValueByXPath("//RealEstateProject/ProjectAttachmentType/text()"));
		loanData.put(ProjectLegalStructureType, AARequestXML.getElementValueByXPath("//RealEstateProject/ProjectLegalStructureType/text()"));
		loanData.put(ConstructionMethodType, AARequestXML.getElementValueByXPath("//StructureContainer/Structure[StructureIdentifier=1]/ConstructionMethodType/text()"));
		
		loanData.put(ProgramType, AARequestXML.getElementValueByXPath("//Programs/Program[ProgramIdentifier=1]/ProgramType/text()"));
		loanData.put(RefinanceProgramType, AARequestXML.getElementValueByXPath("//Programs/Program[ProgramIdentifier=1]/RefinanceProgramType/text()"));
		
		loanData.put(ProgramType2, AARequestXML.getElementValueByXPath("//Programs/Program[ProgramIdentifier=2]/ProgramType/text()"));
		loanData.put(RefinanceProgramType2, AARequestXML.getElementValueByXPath("//Programs/Program[ProgramIdentifier=2]/RefinanceProgramType/text()"));
	}

	public Map<String, String> getLoanData() {
		return loanData;
	}

	public Map<String, String> getDerivedData() {
		return derivedData;
	}

	public Map<String, String> getAddress() {
		return address;
	}

	public Map<String, String> getPV1() {
		return PV1;
	}

	public Map<String, String> getSellerPV() {
		return sellerPV;
	}

	public boolean hasSellerPV() {
		if(sellerPV != null && sellerPV.size() > 0) {
			return true;
		}
		return false;
	}

	public Map<String, String> getPV2() {
		return PV2;
	}

	public Map<String, String> getHVE() {
		return HVE;
	}

	public Map<String, String> getCurrentHVE() {
		return currentHVE;
	}

	public Map<String, String> getHVE2() {
		return HVE2;
	}

	private static ACEAPI20AARequestData currentRequest=null;
	
	public static ACEAPI20AARequestData getCurrentRequest() {
		if(currentRequest == null) {
			try {
				ACEAPI20DB.getCorrespondingDoc(AA_Request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return currentRequest;
	}
	
	public static void setAARequest(String requestJSONStr) {
		currentRequest=new ACEAPI20AARequestData(requestJSONStr);
	}

	public static void clear() {
		currentRequest=null;
	}

	public Map<String, String> getCommunicationFailureData() {
		// TODO Auto-generated method stub
		return commFailures;
	}

}
