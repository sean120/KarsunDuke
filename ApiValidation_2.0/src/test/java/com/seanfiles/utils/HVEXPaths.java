package com.seanfiles.utils;

import java.util.HashMap;

public class HVEXPaths {
	
	public String returnHveReqXpaths(String requestElement) {
		HashMap<String, String> HVEReqXpath = new HashMap<String, String>();

//		.. XML input
//		.. values----------------------------------------------------------------------------------------------------------------------------------------------------
		HVEReqXpath.put(("AddressLineText").toLowerCase(),"HVSRequest.HVSRequestContainers.HVSRequestContainer.ADDRESS.AddressLineText");
		HVEReqXpath.put(("CityName").toLowerCase(),"HVSRequest.HVSRequestContainers.HVSRequestContainer.ADDRESS.CityName");
		HVEReqXpath.put(("FIPSStateAlphaCode").toLowerCase(),"HVSRequest.HVSRequestContainers.HVSRequestContainer.ADDRESS.FIPSStateAlphaCode");
		HVEReqXpath.put(("PostalCode").toLowerCase(),"HVSRequest.HVSRequestContainers.HVSRequestContainer.ADDRESS.PostalCode");
		HVEReqXpath.put(("ZIPPlusFourCode").toLowerCase(),"HVSRequest.HVSRequestContainers.HVSRequestContainer.ADDRESS.ZIPPlusFourCode");
		HVEReqXpath.put(("AlternateLoanIdentifier").toLowerCase(),"HVSRequest.HVSRequestContainers.HVSRequestContainer.AlternativeLoanIdentifications.AlternativeLoanIdentification.AlternateLoanIdentifier[0]");
		
		HVEReqXpath.put(("LoanIdentifier").toLowerCase(),"HVSRequest.HVSRequestContainers.HVSRequestContainer.AlternativeLoanIdentifications.AlternativeLoanIdentification.AlternateLoanIdentifier[0]");
		HVEReqXpath.put(("LoanIdentifierType").toLowerCase(),"HVSRequest.HVSRequestContainers.HVSRequestContainer.AlternativeLoanIdentifications.AlternativeLoanIdentification.LoanIdentifierType[0]");
		HVEReqXpath.put(("CensusTractBaseIdentifier").toLowerCase(),"HVSRequest.HVSRequestContainers.HVSRequestContainer.GEOCODE_INFORMATION.CensusTractBaseIdentifier");
		HVEReqXpath.put(("CoreBasedStatisticalAreaCode").toLowerCase(),"HVSRequest.HVSRequestContainers.HVSRequestContainer.GEOCODE_INFORMATION.CoreBasedStatisticalAreaCode");
		HVEReqXpath.put(("FIPSStateNumericCode").toLowerCase(),"HVSRequest.HVSRequestContainers.HVSRequestContainer.GEOCODE_INFORMATION.FIPSStateNumericCode");
		HVEReqXpath.put(("LatitudeNumber").toLowerCase(),"HVSRequest.HVSRequestContainers.HVSRequestContainer.GEOCODE_INFORMATION.LatitudeNumber");
		HVEReqXpath.put(("LongitudeNumber").toLowerCase(),"HVSRequest.HVSRequestContainers.HVSRequestContainer.GEOCODE_INFORMATION.LongitudeNumber");
		HVEReqXpath.put(("FIPSCountyThreeDigitCode").toLowerCase(),".HVSRequest.HVSRequestContainers.HVSRequestContainer.GEOCODE_INFORMATION_DERIVATION.FIPSCountyThreeDigitCode");
		HVEReqXpath.put(("AccountIdentifier").toLowerCase(),"HVSRequest.AccountIdentifier");
		HVEReqXpath.put(("RequestType").toLowerCase(),"HVSRequest.HVSRequestContainers.HVSRequestContainer.RequestType");
		HVEReqXpath.put(("ClientSchemaVersionIdentifier").toLowerCase(),"HVSRequest.ServiceRequestContainer.ServiceRequest.ClientSchemaVersionIdentifier");
		HVEReqXpath.put(("RequestorIdentifier").toLowerCase(),"HVSRequest.ServiceRequestContainer.ServiceRequest.RequestorIdentifier");
		HVEReqXpath.put(("SchemaVersionIdentifier").toLowerCase(),"HVSRequest.ServiceRequestContainer.ServiceRequest.SchemaVersionIdentifier");
		HVEReqXpath.put(("ServiceName").toLowerCase(),"HVSRequest.ServiceRequestContainer.ServiceRequest.ServiceName");
		HVEReqXpath.put(("ServiceRequestDateTime").toLowerCase(),"HVSRequest.ServiceRequestContainer.ServiceRequest.ServiceRequestDateTime");
		HVEReqXpath.put(("ServiceRequestOperationName").toLowerCase(),"HVSRequest.ServiceRequestContainer.ServiceRequest.ServiceRequestOperationName");
		HVEReqXpath.put(("SubscriberRequestCorrelationIdentifier").toLowerCase(),"HVSRequest.ServiceRequestContainer.ServiceRequest.SubscriberRequestCorrelationIdentifier");
		
		String hveRequestElementXpath = HVEReqXpath.get(requestElement.toLowerCase());
		return hveRequestElementXpath;
	}

	public String returnHveResXpaths(String responseElement) {
		HashMap<String, String> HVERespXpath = new HashMap<String, String>();

//..		.. XML input
//..		.. values----------------------------------------------------------------------------------------------------------------------------------------------------
		HVERespXpath.put(("ActiveMLSListingIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.ActiveMLSListingIndicator");
		HVERespXpath.put(("AppraisalCompletionAsIsIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.AppraisalCompletionAsIsIndicator");
		HVERespXpath.put(("AppraisalExistenceIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.AppraisalExistenceIndicator");
		HVERespXpath.put(("ARMsLengthContractIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.ARMsLengthContractIndicator");
		HVERespXpath.put(("DaysOnMarketIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.DaysOnMarketIndicator");
		HVERespXpath.put(("DistressedIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.DistressedIndicator");
		HVERespXpath.put(("HighestBestUseIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.HighestBestUseIndicator");
		HVERespXpath.put(("LegalityIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.LegalityIndicator");
		HVERespXpath.put(("MissingDataIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.MissingDataIndicator");
		HVERespXpath.put(("MultipleListingIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.MultipleListingIndicator");
		HVERespXpath.put(("NeighborhoodConformityIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.NeighborhoodConformityIndicator");
		HVERespXpath.put(("PhysicalDeficiencyIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.PhysicalDeficiencyIndicator");
		HVERespXpath.put(("PriceGrowthThresholdAmount").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.PriceGrowthThresholdAmount");
		
		HVERespXpath.put(("PropertyAgeCount").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.PropertyAgeCount");
		HVERespXpath.put(("PropertyConditionIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.PropertyConditionIndicator");
		HVERespXpath.put(("PublicRecordExistenceIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.PublicRecordExistenceIndicator");
		HVERespXpath.put(("StructureBuildingMaterialQualityRatingType").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.StructureContainer.PropertyStructureAnalyses.PropertyStructureAnalysisContainer.PropertyStructureAnalysis.StructureBuildingMaterialQualityRatingType");
		HVERespXpath.put(("StructureBuildingMaterialQualityRatingIdentifier").toLowerCase(),".EnterpriseDecisionService.LoanDeliveryStructure.LoanContainer.Properties.PropertyContainer.StructureContainer.PropertyStructureAnalyses.PropertyStructureAnalysisContainer.PropertyStructureAnalysisDerivation.StructureBuildingMaterialQualityRatingIdentifier");
		HVERespXpath.put(("QualityAgeIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.QualityAgeIndicator");
		HVERespXpath.put(("QualityIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.QualityIndicator");
		HVERespXpath.put(("AVMConfidenceScoreValue").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.AVMValuations.AVMValuation.AVMConfidenceScoreValue");
		HVERespXpath.put(("HVEStandardDeviationNumber").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.AVMValuations.AVMValuation.HVEStandardDeviationNumber");
		HVERespXpath.put(("AVMHighValueRangeAmount").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.AVMValuations.AVMValuation[0].AVMHighValueRangeAmount");
		HVERespXpath.put(("AVMValueAmount").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.AVMValuations.AVMValuation[0].AVMValueAmount");
		HVERespXpath.put(("AVMLowValueRangeAmount").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.AVMValuations.AVMValuation.AVMLowValueRangeAmount");
		HVERespXpath.put(("ResponseDateTime").toLowerCase(),".HVSResponse.ResponseDateTime");
		HVERespXpath.put(("PropertyLandUseIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.PropertyLandUseIndicator");
		HVERespXpath.put(("PropertyPurchaseLotSizeOneAcreIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.PropertyPurchaseLotSizeOneAcreIndicator");
		HVERespXpath.put(("PropertyRefinanceLotSizeTwoAcreIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.PropertyRefinanceLotSizeTwoAcreIndicator");
		HVERespXpath.put(("PropertyUnitIndicator").toLowerCase(),".HVSResponse.HVSResponseContainers.HVSResponseContainer.HVSValuation.PropertyInspectionWaiverContainer.PropertyInspectionWaiverResult.PropertyUnitIndicator");

		
		
		
		String hveResponseElementXpath = HVERespXpath.get(responseElement.toLowerCase());
		return hveResponseElementXpath;
	}
	

}
