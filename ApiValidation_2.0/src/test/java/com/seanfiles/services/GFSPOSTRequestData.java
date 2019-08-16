package com.seanfiles.services;

import static com.seanfiles.elements.ACEElements.ARMsLengthContractIndicator;
import static com.seanfiles.elements.ACEElements.ActiveMLSListingIndicator;
import static com.seanfiles.elements.ACEElements.AppraisalCompletionAsIsIndicator;
import static com.seanfiles.elements.ACEElements.AppraisalExistenceIndicator;
import static com.seanfiles.elements.ACEElements.DaysOnMarketIndicator;
import static com.seanfiles.elements.ACEElements.DistressedIndicator;
import static com.seanfiles.elements.ACEElements.HighestBestUseIndicator;
import static com.seanfiles.elements.ACEElements.HomeValueExplorerAssessmentDateTime;
import static com.seanfiles.elements.ACEElements.HomeValueExplorerConfidenceLevelType;
import static com.seanfiles.elements.ACEElements.HomeValueExplorerForecastStandardDeviationRate;
import static com.seanfiles.elements.ACEElements.HomeValueExplorerMaximumValueAmount;
import static com.seanfiles.elements.ACEElements.HomeValueExplorerMidPointValueEstimateAmount;
import static com.seanfiles.elements.ACEElements.HomeValueExplorerMinimumValueAmount;
import static com.seanfiles.elements.ACEElements.LegalityIndicator;
import static com.seanfiles.elements.ACEElements.MissingDataIndicator;
import static com.seanfiles.elements.ACEElements.MultipleListingIndicator;
import static com.seanfiles.elements.ACEElements.NeighborhoodConformityIndicator;
import static com.seanfiles.elements.ACEElements.PhysicalDeficiencyIndicator;
import static com.seanfiles.elements.ACEElements.PriceGrowthThresholdAmount;
import static com.seanfiles.elements.ACEElements.PropertyAgeCount;
import static com.seanfiles.elements.ACEElements.PropertyConditionIndicator;
import static com.seanfiles.elements.ACEElements.PropertyLandUseIndicator;
import static com.seanfiles.elements.ACEElements.PropertyPurchaseLotSizeOneAcreIndicator;
import static com.seanfiles.elements.ACEElements.PropertyRefinanceLotSizeTwoAcreIndicator;
import static com.seanfiles.elements.ACEElements.PropertyUnitIndicator;
import static com.seanfiles.elements.ACEElements.PropertyValuationEffectiveDateTime;
import static com.seanfiles.elements.ACEElements.PublicRecordExistenceIndicator;
import static com.seanfiles.elements.ACEElements.QualityAgeIndicator;
import static com.seanfiles.elements.ACEElements.QualityIndicator;
import static com.seanfiles.elements.ACEElements.StructureBuildingMaterialQualityRatingIdentifier;
import static com.seanfiles.elements.ACEElements.StructureBuildingMaterialQualityRatingType;
import static com.seanfiles.elements.ACEGFSElements.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.seanfiles.db.GFSDB;
import com.seanfiles.db.GFSDBBase;
import com.seanfiles.elements.ACEGFSElementsPaths;
import com.seanfiles.helper.TestConfig;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.utils.JSONUtilities;

import net.minidev.json.JSONObject;

public class GFSPOSTRequestData extends BaseData {

	Map<String, String> requestMap;
	private String JSONStr=null;
	String requestSrc;
	GFSDBBase dbACEAPIRequestData;
	DocumentContext requestJSON;
	Map<String, String> HVE2Data;
	Map<String, String> HVEData;
	Map<String, String> addressData;
	Map<String, String> AAData;
	Map<String, String> PVData;
	
	public static final String RequestJSONTypePAYLOAD="PAYLOADJSON";
	public static final String RequestJSONTypeDB="DBJSON";
	
	public GFSPOSTRequestData(String requestJSONFileName) {
		requestMap=new HashMap<String, String>();
		if (requestJSONFileName != null) {

			String JSONFilePath=System.getProperty("user.dir").concat(TestConfig.getProperty("ACEGFSReqFiles").concat(requestJSONFileName));
			try {
				JSONStr = FileUtils.readFileToString(new File(JSONFilePath),Charset.defaultCharset()).trim();
				parseACEGFSRequestJSON();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public GFSPOSTRequestData(GFSDBBase dbACEAPIRequestData) {
		requestMap=new HashMap<String, String>();
		if (dbACEAPIRequestData != null) {
			parseGFSDBJSON(dbACEAPIRequestData);
		}
	}
	public GFSPOSTRequestData() {
		requestMap=new HashMap<String, String>();
	}

	private void parseGFSDBJSON(GFSDBBase dbACEAPIRequestData) {
		requestSrc=RequestJSONTypeDB;
		this.dbACEAPIRequestData=dbACEAPIRequestData;
		parseJSON();
		loadAAData(requestJSON);
		loadPVData(requestJSON);
		loadAddressData(requestJSON);
		loadHVE2Data(requestJSON);
		loadHVEData(requestJSON);
	}
	
	private void setValue(String elementName) {		
		if(requestSrc.equalsIgnoreCase(RequestJSONTypePAYLOAD)) {
			setValue(requestJSON, elementName);
		}
		else {
			setValue(dbACEAPIRequestData, elementName);
		}
		
	}
	
	private void setValue(GFSDBBase dbACEAPIRequestData, String elementName) {
		setValue(elementName, dbACEAPIRequestData.getElementValue(elementName));
		
	}
	
	private void parseJSON() {
		setValue(sourceApplicationName);
		
		setValue(LoanID_LPKey);
		setValue(LoanID_LPT);
	
		setValue(PartyID_SELLER);		
		
		setValue(AddressLineText);
		setValue(PostalCode);
		
		setValue(AlternateAppraisalDecisionStatusType);
		setValue(AlternateAppraisalEligibilityDecision);
		setValue(PropertyValuationAmount);
		setValue(HomeValueExplorerMidPointValueEstimateAmount);
		setValue(HomeValueExplorerOptionType);
		setValue(HVE2HomeValueExplorerMidPointValueEstimateAmount);
		setValue(HVE2HomeValueExplorerOptionType);
}
	
	private void parseACEGFSRequestJSON() {
		DocumentContext requestJSON= JsonPath.parse(getRequestJSONStr());
		requestSrc=RequestJSONTypePAYLOAD;
		this.requestJSON=requestJSON;
		parseJSON();
		loadAAData(requestJSON);
		loadPVData(requestJSON);
		loadAddressData(requestJSON);
		loadHVE2Data(requestJSON);
		loadHVEData(requestJSON);
	}
	
	private void setValue(DocumentContext requestJSON, String elementName) {
		setValue(elementName, 
				JSONUtilities.getJsonElementValue(requestJSON, 
						ACEGFSElementsPaths.getGFSElementsPaths().getGFSElementPath(elementName)));
	}
	
	private void loadAddressData(DocumentContext requestJSON) {
		addressData=new HashMap<String, String>();	
		setValue(addressData, requestJSON, AddressLineText);
		setValue(addressData, requestJSON, CityName);
		setValue(addressData, requestJSON, PostalCode);
		setValue(addressData, requestJSON, StateCode);
		setValue(addressData, requestJSON, ZipPlusFourCode);
	}
	
	private void loadAAData(DocumentContext requestJSON) {
		AAData=new HashMap<String, String>();	
		setValue(AAData, requestJSON, AlternateAppraisalDecisionStatusType);
		setValue(AAData, requestJSON, AlternateAppraisalDecisionEffectiveDatetime);
		setValue(AAData, requestJSON, AlternateAppraisalDecisionExpirationDatetime);
		setValue(AAData, requestJSON, AlternateAppraisalEligibilityDecision);
	}
	
	private void loadPVData(DocumentContext requestJSON) {
		PVData=new HashMap<String, String>();	
		setValue(PVData, requestJSON, PropertyValuationAmount);
		setValue(PVData, requestJSON, PropertyValuationMethodType);
		setValue(PVData, requestJSON, PropertyValuationType);
	}
	
	private void loadHVE2Data(DocumentContext requestJSON) {
		HVE2Data=new HashMap<String, String>();	
		setValue(HVE2Data, requestJSON, HVE2HomeValueExplorerMidPointValueEstimateAmount);		
		setValue(HVE2Data, requestJSON, HVE2HomeValueExplorerMaximumValueAmount);
		setValue(HVE2Data, requestJSON, HVE2HomeValueExplorerMinimumValueAmount);
		setValue(HVE2Data, requestJSON, HVE2HomeValueExplorerForecastStandardDeviationRate);
		setValue(HVE2Data, requestJSON, HVE2HomeValueExplorerConfidenceLevelType);
		setValue(HVE2Data, requestJSON, HVE2PropertyValuationEffectiveDateTime);
		setValue(HVE2Data, requestJSON, HVE2HomeValueExplorerAssessmentDateTime);
		setValue(HVE2Data, requestJSON, HVE2AppraisalExistenceIndicator);
		setValue(HVE2Data, requestJSON, HVE2PriceGrowthThresholdAmount);
		setValue(HVE2Data, requestJSON, HVE2PropertyConditionIndicator);
		setValue(HVE2Data, requestJSON, HVE2LegalityIndicator);
		setValue(HVE2Data, requestJSON, HVE2PhysicalDeficiencyIndicator);		
		setValue(HVE2Data, requestJSON, HVE2HighestBestUseIndicator);
		setValue(HVE2Data, requestJSON, HVE2AppraisalCompletionAsIsIndicator);
		setValue(HVE2Data, requestJSON, HVE2NeighborhoodConformityIndicator);
		setValue(HVE2Data, requestJSON, HVE2ARMsLengthContractIndicator);
		setValue(HVE2Data, requestJSON, HVE2QualityAgeIndicator);
		setValue(HVE2Data, requestJSON, HVE2DaysOnMarketIndicator);
		setValue(HVE2Data, requestJSON, HVE2DistressedIndicator);
		setValue(HVE2Data, requestJSON, HVE2PropertyAgeCount);
		setValue(HVE2Data, requestJSON, HVE2PublicRecordExistenceIndicator);
		setValue(HVE2Data, requestJSON, HVE2MissingDataIndicator);		
		setValue(HVE2Data, requestJSON, HVE2ActiveMLSListingIndicator);
		setValue(HVE2Data, requestJSON, HVE2MultipleListingIndicator);
		setValue(HVE2Data, requestJSON, HVE2QualityIndicator);
		
		setValue(HVE2Data, requestJSON, HVE2PropertyLandUseIndicator);
		setValue(HVE2Data, requestJSON, HVE2PropertyPurchaseLotSizeOneAcreIndicator);
		setValue(HVE2Data, requestJSON, HVE2PropertyRefinanceLotSizeTwoAcreIndicator);
		setValue(HVE2Data, requestJSON, HVE2PropertyUnitIndicator);
		
		setValue(HVE2Data, requestJSON, HVE2StructureBuildingMaterialQualityRatingIdentifier);
		setValue(HVE2Data, requestJSON, HVE2StructureBuildingMaterialQualityRatingType);
		
		// New Elements for HVE/FDR-CCE-Disaster
		setValue(HVE2Data, requestJSON, HVE2PredictedConditionScoreValue);
		setValue(HVE2Data, requestJSON, HVE2FemaDisasterName);
		setValue(HVE2Data, requestJSON, HVE2AppraisalAlternativeDisasterEligibilityDescription);
	}
	
	private void loadHVEData(DocumentContext requestJSON) {
		HVEData=new HashMap<String, String>();	
		setValue(HVEData, requestJSON, HomeValueExplorerMidPointValueEstimateAmount);		
		setValue(HVEData, requestJSON, HomeValueExplorerMaximumValueAmount);
		setValue(HVEData, requestJSON, HomeValueExplorerMinimumValueAmount);
		setValue(HVEData, requestJSON, HomeValueExplorerForecastStandardDeviationRate);
		setValue(HVEData, requestJSON, HomeValueExplorerConfidenceLevelType);
		setValue(HVEData, requestJSON, PropertyValuationEffectiveDateTime);
		setValue(HVEData, requestJSON, HomeValueExplorerAssessmentDateTime);
		setValue(HVEData, requestJSON, AppraisalExistenceIndicator);
		setValue(HVEData, requestJSON, PriceGrowthThresholdAmount);
		setValue(HVEData, requestJSON, PropertyConditionIndicator);
		setValue(HVEData, requestJSON, LegalityIndicator);
		setValue(HVEData, requestJSON, PhysicalDeficiencyIndicator);		
		setValue(HVEData, requestJSON, HighestBestUseIndicator);
		setValue(HVEData, requestJSON, AppraisalCompletionAsIsIndicator);
		setValue(HVEData, requestJSON, NeighborhoodConformityIndicator);
		setValue(HVEData, requestJSON, ARMsLengthContractIndicator);
		setValue(HVEData, requestJSON, QualityAgeIndicator);
		setValue(HVEData, requestJSON, DaysOnMarketIndicator);
		setValue(HVEData, requestJSON, DistressedIndicator);
		setValue(HVEData, requestJSON, PropertyAgeCount);
		setValue(HVEData, requestJSON, PublicRecordExistenceIndicator);
		setValue(HVEData, requestJSON, MissingDataIndicator);		
		setValue(HVEData, requestJSON, ActiveMLSListingIndicator);
		setValue(HVEData, requestJSON, MultipleListingIndicator);
		setValue(HVEData, requestJSON, QualityIndicator);
		
		setValue(HVEData, requestJSON, PropertyLandUseIndicator);
		setValue(HVEData, requestJSON, PropertyPurchaseLotSizeOneAcreIndicator);
		setValue(HVEData, requestJSON, PropertyRefinanceLotSizeTwoAcreIndicator);
		setValue(HVEData, requestJSON, PropertyUnitIndicator);
		
		setValue(HVEData, requestJSON, StructureBuildingMaterialQualityRatingIdentifier);
		setValue(HVEData, requestJSON, StructureBuildingMaterialQualityRatingType);
		
		// New Elements for HVE/FDR-CCE-Disaster
		setValue(HVEData, requestJSON, PredictedConditionScoreValue);
		setValue(HVEData, requestJSON, FemaDisasterName);
		setValue(HVEData, requestJSON, AppraisalAlternativeDisasterEligibilityDescription);
	}
		
	private void setValue(Map<String, String> container, DocumentContext rquestJSON, String elementName) {
		container.put(elementName, JSONUtilities.getJsonElementValue(requestJSON, 
				ACEGFSElementsPaths.getGFSElementsPaths().getGFSElementPath(elementName)));	
	}

	private void setValue(String fieldName, String fieldValue) {
		requestMap.put(fieldName, fieldValue);		
	}
	
	public String getRequestElementValue(String fieldName) {
		return requestMap.get(fieldName);		
	}
	
	public String getRequestJSONStr() {
		return JSONStr;
	}
	
	public Map<String, String> getRequestMap() {
		return requestMap;
	}
	
	public Map<String, String> getHVE2Map() {
		return HVE2Data;
	}
	
	public Map<String, String> getHVEMap() {
		return HVEData;
	}
	
	public Map<String, String> getAddressMap() {
		return addressData;
	}
	
	public Map<String, String> getAADataMap() {
		return AAData;
	}
	
	public Map<String, String> getPVDataMap() {
		return PVData;
	}
	
	private static GFSPOSTRequestData currentRequest=null;
	
	public static GFSPOSTRequestData getCurrentRequest() {
		if(currentRequest == null) {
			GFSDB.getCorrespondingACEAPI20GFSDocs(GFSDB.GFSPostData);
		}
		return currentRequest;
	}
	
	public static GFSPOSTRequestData getNewACEGFSRequest(String requestJSONFileName) {
		currentRequest=new GFSPOSTRequestData(requestJSONFileName);
		return currentRequest;
	}
	
	public static void setCurrentPOSTRequest(String requestJSONStr) {
		currentRequest=new GFSPOSTRequestData();
		currentRequest.JSONStr=requestJSONStr;
		currentRequest.parseACEGFSRequestJSON();
	}
	
	public static void clear() {
		currentRequest=null;
	}
	public String getValue(String fieldName) {
		return requestMap.get(fieldName);
	}
	
	public static GFSPOSTRequestData createACEGFSRequestFromDBDoc() {
		GFSDBBase dbACEAPIRequestData=GFSDB.getCurrentGFSDBDocuments().getDbRequestACE();
		if(dbACEAPIRequestData == null) {
			TestScenario.writeToScenario("ERROR: DB doc for ACEAPI Request is null");
			return null;
		}
		currentRequest=new GFSPOSTRequestData(dbACEAPIRequestData);
		return currentRequest;
	}
	
	public void updateACEGFSRequest(Map<String, String> dataMap) {
		DocumentContext jsonData=JsonPath.parse(JSONStr);
		for (Map.Entry<String, String> dataMapItem : dataMap.entrySet())
		{
			String elementName=dataMapItem.getKey().trim();
			String elementValue=dataMapItem.getValue();
			//System.out.println("#####element: "+elementName+"*");
			if(elementValue != null) {
				elementValue=elementValue.trim();
			}
			String jsonPath=ACEGFSElementsPaths.getGFSElementsPaths().getGFSElementPath(elementName);
			if(jsonPath == null || jsonPath == "") {
				System.out.println("#####jsonPath not found for element: "+elementName);
			}
			if(JSONUtilities.modifyValueAtJsonPath(jsonData, jsonPath, elementValue)) {
				currentRequest.setValue(elementName, elementValue);
			}
		}
		JSONStr=jsonData.jsonString();
	}

	@SuppressWarnings("unchecked")
	public static void setCurrentGFSPostData(GFSDBBase dbDataACE, GFSDBBase dbDataPV) {
		boolean merge=false;
		ObjectMapper mapper=new ObjectMapper();
		JSONObject jsonObject3=new JSONObject();
		
		if(dbDataACE != null) {
			String dataACE=dbDataACE.getDBDoc().toJson().toString();
			JSONObject jsonObject1=null;
			try {
				jsonObject1 = mapper.readValue(dataACE, JSONObject.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(jsonObject1 !=null) {
				jsonObject3.put("sourceApplicationName", jsonObject1.get("sourceApplicationName"));
				jsonObject3.putAll((Map<? extends String, ? extends Object>)jsonObject1.get("loanData"));
				jsonObject3.putAll((Map<? extends String, ? extends Object>)jsonObject1.get("aceData"));
				merge=true;
			}
		}
		if(dbDataPV != null) {
			String dataPV=dbDataPV.getDBDoc().toJson().toString();
			JSONObject jsonObject2=null;
			try {
				jsonObject2 = mapper.readValue(dataPV, JSONObject.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(jsonObject2 != null) {
				jsonObject3.putAll((Map<? extends String, ? extends Object>)jsonObject2.get("presentValueData"));
				if(!merge) {
					jsonObject3.put("sourceApplicationName", jsonObject2.get("sourceApplicationName"));
					jsonObject3.putAll((Map<? extends String, ? extends Object>)jsonObject2.get("loanData"));
				}
			}
		}
		setCurrentPOSTRequest(jsonObject3.toJSONString());
		TestScenario.writeJSONToScenario(GFSPOSTRequestData.getCurrentRequest().getRequestJSONStr(), "GFS POST Request");
	}	
}
