package com.seanfiles.services;

import static com.seanfiles.elements.ACEAPI20Elements.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.seanfiles.db.ACEAPI20DB;
import com.seanfiles.db.ACEAPI20DBBase;
import com.seanfiles.elements.ACEAPI20ElementsPaths;
import com.seanfiles.helper.TestConfig;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.utils.ExcelFileUtil;
import com.seanfiles.utils.JSONUtilities;

public class ACEAPI20RequestData extends BaseData {

	Map<String, String> requestMap;
	Map<String, String> hveRequestElementsMap;
	private String JSONStr=null;
	String requestSrc;
	ACEAPI20DBBase dbACEAPIRequestData;
	DocumentContext requestJSON;
	String subscriberIdentifier;
	boolean grandfatheringIndicator=false;
	
	public static final String RequestJSONTypePAYLOAD="PAYLOADJSON";
	public static final String RequestJSONTypeDB="DBJSON";
	
	public ACEAPI20RequestData(String requestJSONFileName) {
		requestMap=new HashMap<String, String>();
		if (requestJSONFileName != null) {

			String JSONFilePath=System.getProperty("user.dir").concat(TestConfig.getProperty("ACEAPI20ReqFiles").concat(requestJSONFileName));
			try {
				JSONStr = FileUtils.readFileToString(new File(JSONFilePath),Charset.defaultCharset()).trim();
				parseACEAPI20RequestJSON();
				buildHVERequestElementsMap();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void buildHVERequestElementsMap() {
		hveRequestElementsMap=new HashMap<String, String>();
		hveRequestElementsMap.put(AddressLineText, getValue(AddressLineText));
		hveRequestElementsMap.put(CityName, getValue(CityName));
		hveRequestElementsMap.put(StateCode, getValue(StateCode));
		hveRequestElementsMap.put(PostalCode, getValue(PostalCode));
		hveRequestElementsMap.put(ZipPlusFourCode, getValue(ZipPlusFourCode));
		hveRequestElementsMap.put(CensusTractBaseIdentifier, getValue(CensusTractBaseIdentifier));
		hveRequestElementsMap.put(CoreBasedStatisticalAreaCode, getValue(CoreBasedStatisticalAreaCode));
		hveRequestElementsMap.put(FIPSStateNumericCode, getValue(FIPSStateNumericCode));
		hveRequestElementsMap.put(LatitudeNumber, getValue(LatitudeNumber));
		hveRequestElementsMap.put(LongitudeNumber, getValue(LongitudeNumber));
		hveRequestElementsMap.put(FIPSCountyThreeDigitCode, getValue(FIPSCountyThreeDigitCode));
		
		String LPT=getValue(LoanID_LPT);
		String LQA=getValue(LoanID_LQA);
		if(LPT != null) {
			hveRequestElementsMap.put(SubscriberRequestCorrelationIdentifier, LPT);
			hveRequestElementsMap.put(LoanID_LPT, LPT);
			hveRequestElementsMap.put(LoanID_LPUL, getValue(LoanID_LPUL));
		}
		else if(LQA != null) {
			hveRequestElementsMap.put(SubscriberRequestCorrelationIdentifier, LQA);
			hveRequestElementsMap.put(LoanID_LQA, LQA);
		}
		
		subscriberIdentifier=getValue(SubscriberIdentifier);
		if(getValue(GrandfatheringIndicator) != null && getValue(GrandfatheringIndicator).equalsIgnoreCase("Yes")) {
			grandfatheringIndicator=true;
		}
	}
	
	public ACEAPI20RequestData(ACEAPI20DBBase dbACEAPIRequestData) {
		requestMap=new HashMap<String, String>();
		if (dbACEAPIRequestData != null) {
			parseACEAPI20DBJSON(dbACEAPIRequestData);
			buildHVERequestElementsMap();
		}
	}
	private void parseACEAPI20DBJSON(ACEAPI20DBBase dbACEAPIRequestData) {
		requestSrc=RequestJSONTypeDB;
		this.dbACEAPIRequestData=dbACEAPIRequestData;
		parseJSON();
	}
	
	private void setValue(String elementName) {		
		if(requestSrc.equalsIgnoreCase(RequestJSONTypePAYLOAD)) {
			setValue(requestJSON, elementName);
		}
		else {
			setValue(dbACEAPIRequestData, elementName);
		}
		
	}
	
	private void setValue(ACEAPI20DBBase dbACEAPIRequestData, String elementName) {
		setValue(elementName, dbACEAPIRequestData.getElementValue(elementName));
		
	}
	
	private void parseJSON() {
		setValue(LoanID_LPKey);
		setValue(LoanID_LPT);
		setValue(LoanID_LPUL);
		setValue(LoanID_HPGT);
		setValue(LoanID_HPLPT);
		
		setValue(LoanID_LQA);
		setValue(LoanID_SLS);
		
		setValue(PartyID_SELLER);		
		
		setValue(SubscriberIdentifier);
		setValue(SubscriberRequestCorrelationIdentifier);
		setValue(ServiceName);
		setValue(ServiceRequestOperationName);
		
		setValue(AddressLineText);
		setValue(CityName);
		setValue(PostalCode);
		setValue(StateCode);
		setValue(StreetName);
		setValue(StreetPrimaryNumberText);
		setValue(StreetSuffixText);
		setValue(ZipPlusFourCode);
		setValue(AddressSourceType);
		setValue(AddressMatchLevelType);
		setValue(AddressUnitDesignatorType);
		setValue(AddressUnitIdentifier);
		setValue(CensusTractBaseIdentifier);
		setValue(CoreBasedStatisticalAreaCode);
		setValue(FIPSStateNumericCode);
		setValue(LatitudeNumber);
		setValue(LongitudeNumber);
		setValue(FIPSCountyThreeDigitCode );
		
		setValue(CarrierRouteType );
		setValue(GeocodingIndicator );
		setValue(HighwayContractIdentifier );
		setValue(PostOfficeBoxIdentifier );
		setValue(RuralRouteIdentifier );
		setValue(StreetPostDirectionalText );
		setValue(StreetPreDirectionalText );
		
		setValue(PreScrub_AddressLineText);
		setValue(PreScrub_CityName);
		setValue(PreScrub_PostalCode);
		setValue(PreScrub_StateCode);
		setValue(PreScrub_ZipPlusFourCode);
		setValue(PreScrub_AddressSourceType);
		setValue(PreScrub_GeocodingIndicator );

		
		setValue(LoanProcessingStageType);
		setValue(AppraisalIdentifier);
		setValue(DerivationRiskClassType);
		setValue(DerivedCalculationSourceType);
		setValue(StrategicOfferingCreditRiskType);
		setValue(ConstructionLoanIndicator);
		if(requestMap.get(ConstructionLoanIndicator) == null) {
			requestMap.put(ConstructionLoanIndicator, "false");
		}
		setValue(GrandfatheringIndicator);
		setValue(LoanPurposeType);
		setValue(LoanRefinanceCashOutDeterminationType);
		setValue(MortgageType);
		setValue(LoanConformityType);
		setValue(SellerAccountIdentifier);
		setValue(SellerLoanIdentifier);
		setValue(BorrowerPropertyPurchasePriceAmount);
		setValue(LoanApplicationBaseLoanAmount);
		setValue(MiAndFundingFeeFinancedAmount);
		setValue(LoanScopeType1);
		setValue(LtvRatioPercent1);
		setValue(LtvSourceType1);
		setValue(LoanScopeType2);
		setValue(LtvRatioPercent2);
		setValue(LtvSourceType2);
		setValue(ConstructionLoanType);
		setValue(MiPremiumFinancedAmount);
		setValue(MiPremiumFinancedIndicator);
		setValue(LoanOriginationSystemVersionIdentifier);
		setValue(LoanPropertyUsageType);
		setValue(NoteAmount);
		setValue(FinancedUnitCount);
		setValue(LoanUnderwritingDecisionDefaultProbabilityRate);
		setValue(LoanUnderwritingCaseIdentifier);
		setValue(PartyRoleIdentifier);
		setValue(PartyRoleType);
		setValue(ProductDescription);
		setValue(ProductIdentifier);
		setValue(ProductType);
		setValue(ProgramType);
		setValue(RefinanceProgramType);
		setValue(ProgramType2);
		setValue(RefinanceProgramType2);
		setValue(IntentToOccupyType);
		setValue(BorrowerClassificationType);
		setValue(BorrowerIdentifier);
		setValue(PropertyCategoryType);
		setValue(PropertyEstateType);

		setValue(PropertyValuationAmount);
		setValue(PropertyValuationMethodType);
		setValue(PropertyValuationSequenceNumber);
		setValue(PropertyValuationType);

		
		setValue(EST_O_PropertyValuationAmount);
		setValue(EST_O_PropertyAssessmentSourceType);
		setValue(EST_O_PropertyValuationEffectiveDateTime);
		setValue(EST_O_PropertyValuationMethodType);
		setValue(EST_O_PropertyValuationSequenceNumber);
		setValue(EST_O_PropertyValuationType);

		
		setValue(EST_S_PropertyValuationAmount);
		setValue(EST_S_PropertyAssessmentSourceType);
		setValue(EST_S_PropertyValuationEffectiveDateTime);
		setValue(EST_S_PropertyValuationMethodType);
		setValue(EST_S_PropertyValuationSequenceNumber);
		setValue(EST_S_PropertyValuationType);

		
		setValue(FR_PropertyValuationAmount);
		setValue(FR_PropertyValuationEffectiveDateTime);
		setValue(FR_PropertyValuationMethodType);
		setValue(FR_PropertyValuationSequenceNumber);
		
		setValue(CraCommunicationFailureType);
		setValue(RdsCommunicationFailureType);
		setValue(ProjectAttachmentType);
		setValue(ProjectLegalStructureType);
		setValue(ConstructionMethodType);
		setValue(AutomatedUnderwritingSystemType);
		setValue(LoanQualityAdvisorResultType);
		setValue(MessageCode);
		setValue(MessageText);
		setValue(InvestorCollateralProgramType);
	}
	
	private void parseACEAPI20RequestJSON() {
		DocumentContext requestJSON= JsonPath.parse(getRequestJSONStr());
		requestSrc=RequestJSONTypePAYLOAD;
		this.requestJSON=requestJSON;
		parseJSON();
	}
	
	private void setValue(DocumentContext requestJSON, String elementName) {
		setValue(elementName, 
				JSONUtilities.getJsonElementValue(requestJSON, 
						ACEAPI20ElementsPaths.getACEAPI20ElementsPaths().getElementJSONPath(elementName)));
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
	
	public String getSubscriberIdentifier() {
		return subscriberIdentifier;
	}

	public boolean getGrandfatheringIndicator() {
		return grandfatheringIndicator;
	}

	public Map<String, String> getHVERequestElementsMap() {
		return hveRequestElementsMap;
	}

	private static ACEAPI20RequestData currentRequest=null;
	
	public static ACEAPI20RequestData getCurrentRequest() {
		return currentRequest;
	}
	
	public static ACEAPI20RequestData getNewACEAPI20Request(String requestJSONFileName) {
		currentRequest=new ACEAPI20RequestData(requestJSONFileName);
		return currentRequest;
	}
	
	public static void clear() {
		currentRequest=null;
	}
	public String getValue(String fieldName) {
		return requestMap.get(fieldName);
	}
	
	public static ACEAPI20RequestData createACEAPI20RequestFromDBDoc() {
		ACEAPI20DBBase dbACEAPIRequestData=ACEAPI20DB.getCurrentACEAPIDBDocuments().getACEAPIRequest();
		if(dbACEAPIRequestData == null) {
			TestScenario.writeToScenario("ERROR: DB doc for ACEAPI Request is null");
			return null;
		}
		currentRequest=new ACEAPI20RequestData(dbACEAPIRequestData);
		ACEAPI20DB.setACEAPI20RequestID();
		return currentRequest;
	}
	
	public void updateACEAPI20Request(Map<String, String> dataMap) {
		DocumentContext jsonData=JsonPath.parse(JSONStr);
		for (Map.Entry<String, String> dataMapItem : dataMap.entrySet())
		{
			String elementName=dataMapItem.getKey().trim();
			if(elementName.length() == 0 ) {
				continue;
			}
			String elementValue=dataMapItem.getValue();
			//System.out.println("#####element: "+elementName+"*");
			if(elementValue != null) {
				elementValue=elementValue.trim();
				if(elementValue.contains(RUN_ID)) {
					elementValue=elementValue.replace(RUN_ID, TestConfig.getRunID());
				}
			}
			String jsonPath=ACEAPI20ElementsPaths.getACEAPI20ElementsPaths().getElementJSONPath(elementName);
			if(jsonPath == null || jsonPath == "") {
				System.out.println("#####jsonPath not found for element: "+elementName);
			}
			if(JSONUtilities.modifyValueAtJsonPath(jsonData, jsonPath, elementValue)) {
				currentRequest.setValue(elementName, elementValue);
			}
		}
		JSONStr=jsonData.jsonString();
		buildHVERequestElementsMap();
	}
	
	public void updateACEAPI20RequestWithExcelData(Map<String, String> excelDataMap) {
		Map<String, String> dataMap=ExcelFileUtil.ConvertExcelDataToMap(excelDataMap);	
		if(dataMap != null && ! dataMap.isEmpty()) {
			updateACEAPI20Request(dataMap);
		}
	}

}
