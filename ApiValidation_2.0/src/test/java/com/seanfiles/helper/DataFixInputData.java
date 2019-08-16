package com.seanfiles.helper;

import static com.seanfiles.elements.ACECheckAPIElements.*;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.mongodb.client.FindIterable;
import com.seanfiles.db.ACECheckAPIDB;
import com.seanfiles.db.ACECheckAPIDBBase;
import com.seanfiles.db.GFSDB;
import com.seanfiles.db.GFSDBBase;
import com.seanfiles.db.GFSDBDocuments;
import com.seanfiles.elements.ACEGFSElements;
import com.seanfiles.elements.ACEGFSElementsPaths;
import com.seanfiles.services.ACECheckAPIHVEResponseData;
import com.seanfiles.services.ACECheckAPIRequestData;
import com.seanfiles.services.ACECheckAPIResponseData;
import com.seanfiles.services.GFSPOSTRequestData;
import com.seanfiles.utils.JSONUtilities;
import com.seanfiles.utils.MongoDBUtils;

public class DataFixInputData {

	public static boolean assertStatus = false;

	private static Logger log = Logger.getLogger(DataFixInputData.class);

	public static void prepareDataFor1aScript() {
		// String
		// header="aceapiid|alternateappraisaleligibilitydecision|propertyagecount|homevalueexplorermidpointvalueestimateamount|createddate|sourceapplicationname|aceapiid_ace|requesttype|propertyagecount_ace|avmvalueamount|servicename|objectid|acecreatedate|message";
		// log.info(header);

		String values = "";
		GFSDBBase dbGFSPOSTData = GFSDB.getCurrentGFSDBDocuments().getDbDataACE();
		ACECheckAPIDBBase dbHVSResponse = ACECheckAPIDB.getCurrentACEAPIDBDocuments().getHVEResponse();
		DocumentContext dbGFSPOSTDataJson = dbGFSPOSTData.getDBData();
		DocumentContext dbHVSResponseSvcTraceDataJson = dbHVSResponse.getDBData();
		ACECheckAPIHVEResponseData responseHVE = ACECheckAPIHVEResponseData.getCurrentResponse();
		Map<String, String> HVE = responseHVE.getHVE();

		// values+=ACECheckAPIDerivedData.getDerivedData().getACEAPI10RequestID();
		values += JSONUtilities.getJsonElementValue(dbHVSResponseSvcTraceDataJson, "contextMap._id");
		values += "|";
		values += dbGFSPOSTData.getElementValue(AlternateAppraisalEligibilityDecision);
		values += "|";
		String propertyagecount = dbGFSPOSTData.getElementValue(PropertyAgeCount);
		if (propertyagecount == null || propertyagecount.length() == 0) {
			propertyagecount = "NULL";
		} else {
			propertyagecount = String.valueOf((int) Double.parseDouble(propertyagecount));
		}
		values += propertyagecount;
		values += "|";
		values += dbGFSPOSTData.getElementValue(HomeValueExplorerMidPointValueEstimateAmount);
		values += "|";
		values += dbGFSPOSTData.getDate("createdDate", "yyyy-MM-dd hh:mm:ss.SSS");
		values += "|";
		values += JSONUtilities.getJsonElementValue(dbGFSPOSTDataJson, "sourceApplicationName");
		values += "|";
		values += JSONUtilities.getJsonElementValue(dbGFSPOSTDataJson, "aceApiId");
		values += "|";
		values += JSONUtilities.getJsonElementValue(dbHVSResponseSvcTraceDataJson, "contextMap.requestType");
		values += "|";
		String propertyagecount_ace = HVE.get(PropertyAgeCount);
		if (propertyagecount_ace == null || propertyagecount_ace.length() == 0) {
			propertyagecount_ace = "NULL";
		} else {
			propertyagecount_ace = String.valueOf((int) Double.parseDouble(propertyagecount_ace));
		}
		values += propertyagecount_ace;
		values += "|";
		values += HVE.get(HomeValueExplorerMidPointValueEstimateAmount);
		values += "|";
		values += JSONUtilities.getJsonElementValue(dbHVSResponseSvcTraceDataJson, "contextMap.serviceName");
		values += "|";
		values += dbGFSPOSTData.getObjectID();
		values += "|";
		values += dbHVSResponse.getDate("date", "yyyy-MM-dd hh:mm:ss.SSS");
		values += "|";
		values += dbHVSResponse.getElementValue(Message);

		log.info("Data to compare and fix: " + values);

	}

	public static void prepareDataFor1bScript() {

		String aceCheckData = getACECheckdata();
		GFSDBBase dbGFSPOSTData = GFSDB.getCurrentGFSDBDocuments().getDbDataACE();
		ACECheckAPIDBBase dbHVSResponse = ACECheckAPIDB.getCurrentACEAPIDBDocuments().getHVEResponse();
		DocumentContext dbGFSPOSTDataJson = dbGFSPOSTData.getDBData();

		String aceAPIID = JSONUtilities.getJsonElementValue(dbGFSPOSTDataJson, "aceApiId");
		FindIterable<Document> docs = null;
		try {
			docs = MongoDBUtils.getDocsBysFieldValue("grandFatheringAceResponse",
					"loanData.loanIdentifications.loanIdentification.loanIdentifier", aceAPIID);
			for (Document doc : docs) {
				if (doc != null) {
					TestScenario.writeJSONToScenario(doc.toJson().toString(), "grandFatheringAceResponse doc");
				}
				String values = aceCheckData;
				values += doc.getObjectId("_id").toString();
				values += "|";
				values += aceAPIID;
				values += "|";

				String reqDocId = MongoDBUtils.getTransConextDocID(doc);
				Document reqDoc = MongoDBUtils.getDocByID("grandFatheringAceRequest", reqDocId);
				if (doc != null) {
					TestScenario.writeJSONToScenario(reqDoc.toJson().toString(), "grandFatheringAceRequest doc");
				}
				DocumentContext requestJSON = JsonPath.parse(reqDoc.toJson().toString());

				String loanId = JSONUtilities.getJsonElementValue(requestJSON,
						"$.aceRequest.loanIdentifications.loanIdentification[0].loanIdentifier");
				values += loanId;
				values += "|";
				String loanIdType = JSONUtilities.getJsonElementValue(requestJSON,
						"$.aceRequest.loanIdentifications.loanIdentification[0].loanIdentifierType");
				values += loanIdType;
				values += "|";
				values += dbHVSResponse.getElementValue(Message);
				getLPAPostsValues(loanId, values);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void getLPAPostsValues(String loanId, String values) {
		TestScenario.writeToScenario("LPKey: " + loanId);
		FindIterable<Document> docs = null;
		try {
			docs = MongoDBUtils.getDocsBysFieldValue("grandFatheringAceData", "lpKey", loanId);

			for (Document doc : docs) {
				if (doc != null) {
					TestScenario.writeJSONToScenario(doc.toJson().toString(), "grandFatheringAceData");
					getPOSTDataValues(doc, values);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void getPOSTDataValues(Document doc, String values) {
		DocumentContext requestJSON = JsonPath.parse(doc.toJson().toString());

		values += "|" + MongoDBUtils.getDocID(doc);
		values += "|" + getDBElmValue(requestJSON, "$.customerId");
		values += "|" + getElmValue(requestJSON, ACEGFSElements.AddressLineText);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.CityName);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.PostalCode);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.StateCode);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.LoanID_LPKey);

		values += "|" + getElmValue(requestJSON, ACEGFSElements.AlternateAppraisalEligibilityDecision);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.AlternateAppraisalDecisionExpirationDatetime);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.AlternateAppraisalDecisionEffectiveDatetime);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.AlternateAppraisalDecisionStatusType);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2HomeValueExplorerAssessmentDateTime);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2HomeValueExplorerConfidenceLevelType);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2HomeValueExplorerForecastStandardDeviationRate);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2HomeValueExplorerMaximumValueAmount);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2HomeValueExplorerMidPointValueEstimateAmount);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2HomeValueExplorerMinimumValueAmount);

		values += "|HVEFeedback";
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2HomeValueExplorerAssessmentDateTime); // instead of
																											// propertyvaluationeffectivedatetime_lpa_gfs

		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2ActiveMLSListingIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2AppraisalCompletionAsIsIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2AppraisalExistenceIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2ARMsLengthContractIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2DaysOnMarketIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2DistressedIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2HighestBestUseIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2LegalityIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2MissingDataIndicator);

		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2MultipleListingIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2NeighborhoodConformityIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2PhysicalDeficiencyIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2PriceGrowthThresholdAmount);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2PropertyAgeCount);

		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2PropertyConditionIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2PropertyLandUseIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2PropertyPurchaseLotSizeOneAcreIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2PropertyRefinanceLotSizeTwoAcreIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2PropertyUnitIndicator);

		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2PublicRecordExistenceIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2QualityAgeIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2QualityIndicator);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2StructureBuildingMaterialQualityRatingIdentifier);
		values += "|" + getElmValue(requestJSON, ACEGFSElements.HVE2StructureBuildingMaterialQualityRatingType);

		TestScenario.writeToScenario("TransID: " + getElmValue(requestJSON, ACEGFSElements.LoanID_LPT));
		log.info("Data to compare and fix: " + values);
		CompareHVEContainers(doc);
	}

	private static String getDBElmValue(DocumentContext requestJSON, String elemName) {
		return JSONUtilities.getJsonElementValue(requestJSON, elemName);
	}

	private static String getElmValue(DocumentContext requestJSON, String elementName) {
		return JSONUtilities.getJsonElementValue(requestJSON,
				ACEGFSElementsPaths.getGFSElementsPaths().getGFSDBElementPath(elementName, "grandFatheringAceData"));
	}

	private static void CompareHVEContainers(Document doc) {
		GFSDBDocuments dbDocs = GFSDB.getCurrentGFSDBDocuments();
		dbDocs.setDBDoc(doc, GFSDBDocuments.collectionGFSACEData);
		GFSPOSTRequestData.setCurrentGFSPostData(dbDocs.getDbDataACE(), dbDocs.getDbDataPV());
		GFSPOSTRequestData currentPostData = GFSPOSTRequestData.getCurrentRequest();

		ACECheckAPIHVEResponseData currHVSResp = ACECheckAPIHVEResponseData.getCurrentResponse();

		String srcName = "ACECheckAPI HVS Response";
		Map<String, String> srcMap = currHVSResp.getHVE();
		String tgtName = "GFS POST HVE1";
		Map<String, String> tgtMap = currentPostData.getHVEMap();

		List<String> skipElems = new ArrayList<String>();
		skipElems.add("homeValueExplorerAssessmentDateTime");
		skipElems.add("propertyValuationEffectiveDateTime");
		boolean compareStatus = currentPostData.compare(srcMap, srcName, tgtMap, tgtName, skipElems, null);

		if (compareStatus == true) {
			TestScenario.writeToScenario("SUCCESS-HVS Resp-GFSPost Data Matched");
		} else {
			TestScenario.writeToScenario("FAILURE-HVS Resp-GFSPost Data Mismatch");
		}

		if (assertStatus) {
			assertTrue("GFS POST Verification Failed", compareStatus);
		}
	}

	private static String getACECheckdata() {
		String values = "";
		GFSDBBase dbGFSPOSTData = GFSDB.getCurrentGFSDBDocuments().getDbDataACE();
		ACECheckAPIDBBase dbHVSResponse = ACECheckAPIDB.getCurrentACEAPIDBDocuments().getHVEResponse();
		DocumentContext dbGFSPOSTDataJson = dbGFSPOSTData.getDBData();
		DocumentContext dbHVSResponseSvcTraceDataJson = dbHVSResponse.getDBData();
		ACECheckAPIHVEResponseData responseHVE = ACECheckAPIHVEResponseData.getCurrentResponse();
		Map<String, String> HVE = responseHVE.getHVE();

		ACECheckAPIRequestData currentAceCheckReq = ACECheckAPIRequestData.getCurrentRequest();
		ACECheckAPIResponseData currentAceCheckResp = ACECheckAPIResponseData.getCurrentResponse();

		values += JSONUtilities.getJsonElementValue(dbHVSResponseSvcTraceDataJson, "contextMap._id");
		values += "|";
		values += dbGFSPOSTData.getElementValue(AlternateAppraisalEligibilityDecision);
		values += "|";
		String propertyagecount = dbGFSPOSTData.getElementValue(PropertyAgeCount);
		if (propertyagecount == null || propertyagecount.length() == 0) {
			propertyagecount = "NULL";
		} else {
			propertyagecount = String.valueOf((int) Double.parseDouble(propertyagecount));
		}
		values += propertyagecount;
		values += "|";
		values += dbGFSPOSTData.getElementValue(HomeValueExplorerMidPointValueEstimateAmount);
		values += "|";
		values += dbGFSPOSTData.getDate("createdDate", "yyyy-MM-dd hh:mm:ss.SSS");
		values += "|";
		values += JSONUtilities.getJsonElementValue(dbGFSPOSTDataJson, "sourceApplicationName");
		values += "|";
		values += dbGFSPOSTData.getElementValue(ACEGFSElements.PartyID_SELLER);
		values += "|";
		values += JSONUtilities.getJsonElementValue(dbGFSPOSTDataJson, "aceApiId");
		values += "|";
		String propertyagecount_ace = HVE.get(PropertyAgeCount);
		if (propertyagecount_ace == null || propertyagecount_ace.length() == 0) {
			propertyagecount_ace = "NULL";
		} else {
			propertyagecount_ace = String.valueOf((int) Double.parseDouble(propertyagecount_ace));
		}
		values += propertyagecount_ace;
		values += "|";
		values += dbHVSResponse.getDate("date", "yyyy-MM-dd hh:mm:ss.SSS");
		values += "|";
		values += JSONUtilities.getJsonElementValue(dbHVSResponseSvcTraceDataJson, "contextMap.requestType");
		values += "|";
		values += HVE.get(HomeValueExplorerMidPointValueEstimateAmount);
		values += "|";
		values += currentAceCheckReq.getValue(PartyRoleIdentifier);
		values += "|";
		values += currentAceCheckReq.getValue(LoanPurposeType);
		values += "|";
		values += dbGFSPOSTData.getObjectID();
		values += "|";
		values += currentAceCheckResp.getValue(AddressLineText);
		values += "|";
		values += currentAceCheckResp.getValue(CityName);
		values += "|";
		values += currentAceCheckResp.getValue(StateCode);
		values += "|";
		values += currentAceCheckResp.getValue(PostalCode);
		values += "|";
		values += currentAceCheckResp.getValue(AppraisalWaiverPrescreenEligibilityType);
		values += "|";
		values += currentAceCheckResp.getValue(AppraisalWaiverPrescreenExpirationDate);
		values += "|";
		values += currentAceCheckResp.getValue(MaximumAuthorizedLoanAmount);
		values += "|";
		return values;
	}
}
