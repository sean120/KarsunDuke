package com.seanfiles.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.seanfiles.elements.ACEGFSElements.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.seanfiles.db.GFSDBBase;
import com.seanfiles.elements.ACEGFSElementsPaths;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.utils.JSONUtilities;

import net.minidev.json.JSONObject;

public class GFSGETResponseData extends BaseData {
	Map<String, String> PVData;
	Map<String, String> HVEData;
	
	Map<String, String> errorContainerPV;
	Map<String, String> errorContainerACE;
	
	Map<String, String> responseDataMap;
	
	private String JSONStr=null;
	
	private static GFSGETResponseData currentGETResponse=null;

	public Map<String, String> getPVData() {
		return PVData;
	}
	
	public Map<String, String> getResponseDataMap() {
		return responseDataMap;
	}
	
	public Map<String, String> getHVEData() {
		return HVEData;
	}
	
	public GFSGETResponseData(String responseJSONStr) {
		JSONStr=responseJSONStr;
		parseResponseJSON(responseJSONStr);
	}

	public String getResponseJSONStr() {
		return JSONStr;
	}
	
	private void parseResponseJSON(String responseJSONStr) {
		DocumentContext responseJSON= JsonPath.parse(responseJSONStr);
		loadErrorContainer(responseJSON);
		if(hasACEData()) {
			loadHVEData(responseJSON);
		}
		if(hasPVData()) {
			loadPVData(responseJSON);
		}
		setFullResponseData();
	}

	private void setFullResponseData() {
		responseDataMap=new HashMap<String, String>();
		responseDataMap.putAll(errorContainerACE);
		responseDataMap.putAll(errorContainerPV);
		if(this.hasACEData()) {
			responseDataMap.putAll(HVEData);
		}
		if(this.hasPVData()) {
			responseDataMap.putAll(PVData);		
		}
	}

	private void loadPVData(DocumentContext responseJSON) {
		PVData=new HashMap<String, String>();
		setValue(PVData, responseJSON, PropertyValuationAmount);
		setValue(PVData, responseJSON, PropertyValuationMethodType);
	}

	private void loadHVEData(DocumentContext responseJSON) {
		HVEData=new HashMap<String, String>();	
		setValue(HVEData, responseJSON, HomeValueExplorerMidPointValueEstimateAmount);		
		setValue(HVEData, responseJSON, HomeValueExplorerMaximumValueAmount);
		setValue(HVEData, responseJSON, HomeValueExplorerMinimumValueAmount);
		setValue(HVEData, responseJSON, HomeValueExplorerForecastStandardDeviationRate);
		setValue(HVEData, responseJSON, HomeValueExplorerConfidenceLevelType);
		setValue(HVEData, responseJSON, PropertyValuationEffectiveDateTime);
		setValue(HVEData, responseJSON, HomeValueExplorerAssessmentDateTime);
		
		setValue(HVEData, responseJSON, AppraisalExistenceIndicator);
		setValue(HVEData, responseJSON, PriceGrowthThresholdAmount);
		setValue(HVEData, responseJSON, PropertyConditionIndicator);
		setValue(HVEData, responseJSON, LegalityIndicator);
		setValue(HVEData, responseJSON, PhysicalDeficiencyIndicator);		
		setValue(HVEData, responseJSON, HighestBestUseIndicator);
		setValue(HVEData, responseJSON, AppraisalCompletionAsIsIndicator);
		setValue(HVEData, responseJSON, NeighborhoodConformityIndicator);
		setValue(HVEData, responseJSON, ARMsLengthContractIndicator);
		setValue(HVEData, responseJSON, QualityAgeIndicator);
		setValue(HVEData, responseJSON, DaysOnMarketIndicator);
		setValue(HVEData, responseJSON, DistressedIndicator);
		setValue(HVEData, responseJSON, PropertyAgeCount);
		setValue(HVEData, responseJSON, PublicRecordExistenceIndicator);
		setValue(HVEData, responseJSON, MissingDataIndicator);		
		setValue(HVEData, responseJSON, ActiveMLSListingIndicator);
		setValue(HVEData, responseJSON, MultipleListingIndicator);
		setValue(HVEData, responseJSON, QualityIndicator);
		
		setValue(HVEData, responseJSON, PropertyLandUseIndicator);
		setValue(HVEData, responseJSON, PropertyPurchaseLotSizeOneAcreIndicator);
		setValue(HVEData, responseJSON, PropertyRefinanceLotSizeTwoAcreIndicator);
		setValue(HVEData, responseJSON, PropertyUnitIndicator);
		
		setValue(HVEData, responseJSON, StructureBuildingMaterialQualityRatingIdentifier);
		setValue(HVEData, responseJSON, StructureBuildingMaterialQualityRatingType);
		
		// New Elements for HVE/FDR-CCE-Disaster
		setValue(HVEData, responseJSON, PredictedConditionScoreValue);
		setValue(HVEData, responseJSON, FemaDisasterName);
		setValue(HVEData, responseJSON, AppraisalAlternativeDisasterEligibilityDescription);
	}

	private void loadErrorContainer(DocumentContext responseJSON) {
		errorContainerACE= new HashMap<String, String>();
		setValue(errorContainerACE, responseJSON, ErrorCodeACE);
		setValue(errorContainerACE, responseJSON, ErrorMessageACE);
		
		errorContainerPV= new HashMap<String, String>();
		setValue(errorContainerPV, responseJSON, ErrorCodePV);
		setValue(errorContainerPV, responseJSON, ErrorMessagePV);
	}

	private void setValue(Map<String, String> container, DocumentContext responseJSON, String elementName) {
		container.put(elementName, getResponseElementValue(responseJSON, elementName));		
	}

	private String getResponseElementValue(DocumentContext responseJSON, String elementName) {
		try {
			 return JSONUtilities.getJsonElementValue(responseJSON, 
							ACEGFSElementsPaths.getGFSElementsPaths().getGFSResponseElementPath(elementName));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean hasPVData() {
		if(errorContainerPV.get(ErrorCodePV).compareTo(CodeForSuccess) == 0) {
			return true;
		}
		return false;
	}

	public boolean hasACEData() {
		if(errorContainerACE.get(ErrorCodeACE).compareTo(CodeForSuccess) == 0) {
			return true;
		}
		return false;
	}

	public static GFSGETResponseData getCurrentGETResponse() {
		return currentGETResponse;
	}

	public static void setCurrentGETResponse(String responseJSONStr) {
		currentGETResponse=new GFSGETResponseData(responseJSONStr);
	}

	public static void clear() {
		currentGETResponse=null;		
	}

	@SuppressWarnings("unchecked")
	public static void setCurrentGETResponse(GFSDBBase dbResponseACE, GFSDBBase dbResponsePV) {
		boolean merge=false;
		ObjectMapper mapper=new ObjectMapper();
		JSONObject jsonObject3=new JSONObject();
		
		if(dbResponseACE != null) {
			String dataACE=dbResponseACE.getDBDoc().toJson().toString();
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
				jsonObject3.put("loanData", (Map<? extends String, ? extends Object>)jsonObject1.get("loanData"));
				jsonObject3.put("aceServiceData", (Map<? extends String, ? extends Object>)jsonObject1.get("aceResponse"));
				merge=true;
			}
		}
		if(dbResponsePV != null) {
			String dataPV=dbResponsePV.getDBDoc().toJson().toString();
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
				jsonObject3.put("presentValueServiceData", (Map<? extends String, ? extends Object>)jsonObject2.get("presentValueResponse"));
				if(!merge) {
					jsonObject3.put("loanData", (Map<? extends String, ? extends Object>)jsonObject2.get("loanData"));
				}
			}
		}
		currentGETResponse=new GFSGETResponseData(jsonObject3.toJSONString());
		TestScenario.writeJSONToScenario(GFSGETResponseData.getCurrentGETResponse().getResponseJSONStr(), "GFS GET Response");	
	}
}
