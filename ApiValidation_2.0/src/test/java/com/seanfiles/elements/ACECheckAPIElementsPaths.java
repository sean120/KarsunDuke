package com.seanfiles.elements;

import static com.seanfiles.elements.ACECheckAPIElements.*;

import java.util.HashMap;
import java.util.Map;

import com.seanfiles.db.ACECheckAPIDBDocuments;

public class ACECheckAPIElementsPaths {
	private static ACECheckAPIElementsPaths paths;
	Map<String, String> jsonPaths;
	
	public static ACECheckAPIElementsPaths getACEAPIElementsPaths() {
		if(paths == null ) {
			paths= new ACECheckAPIElementsPaths();
		}
		return paths;
	}
	
	public ACECheckAPIElementsPaths() {
		jsonPaths=new HashMap<String, String>();
		jsonPaths.put(PartyRoleType, "$.party.partyRoleType");
		jsonPaths.put(PartyRoleIdentifier, "$.party.partyRoleIdentifier");
		jsonPaths.put(AddressLineText, "$.deals[0].address.addressLineText");
		jsonPaths.put(CityName, "$.deals[0].address.cityName");
		jsonPaths.put(PostalCode, "$.deals[0].address.postalCode");
		jsonPaths.put(StateCode, "$.deals[0].address.stateCode");
		jsonPaths.put(LoanPurposeType, "$.deals[0].loanInformation.loanPurposeType");
		jsonPaths.put(RequestBatchIdentifier, "$.requestBatchIdentifier");
		jsonPaths.put(SalesContractAmount, "$.deals[0].loanInformation.salesContractAmount");
		jsonPaths.put(PropertyEstimatedValueAmount, "$.deals[0].loanInformation.propertyEstimatedValueAmount");
		jsonPaths.put(RequestTransactionIdentifier, "$.deals[0].requestTransactionIdentifier");
		
		jsonPaths.put(DOC_ID, "$._id");
		
		jsonPaths.put(Message, "$.message");
		
		jsonPaths.put(MessageCode1, "$.deals[0].messages[0].messageCode");
		jsonPaths.put(MessageText1, "$.deals[0].messages[0].messageText");
		
		jsonPaths.put(AppraisalWaiverPrescreenEligibilityType, "$.deals[0].loanInformation.appraisalWaiverPrescreenEligibilityType");
		jsonPaths.put(AppraisalWaiverPrescreenExpirationDate, "$.deals[0].loanInformation.appraisalWaiverPrescreenExpirationDate");
		jsonPaths.put(MaximumAuthorizedLoanAmount, "$.deals[0].loanInformation.maximumAuthorizedLoanAmount");
		
		jsonPaths.put(ContextMapId, "$.contextMap._id");
		jsonPaths.put(ContextMapServiceName, "$.contextMap.serviceName");
		jsonPaths.put(ContextMapRequestType, "$.contextMap.requestType");
	}
	
	public String getElementJSONPath(String elementName) {
		return jsonPaths.get(elementName);
	}
	
	public String getACEAPIDBElementPath(String elementName, String collectionName) {
		String elementPath=getElementJSONPath(elementName);
		if(elementPath == null) {
			return null;
		}
		switch(collectionName) {
		case ACECheckAPIDBDocuments.collectionACERequests:
			elementPath=elementPath.replace("$.", "$.automatedCollateralEvaluationRequest.");
			break;
		case ACECheckAPIDBDocuments.collectionACEResponses:
			elementPath=elementPath.replace("$.", "$.automatedCollateralEvaluationResponse.");
			break;
		}
		return elementPath;
	}
	
	public String getDBQueryElementPath(String elementName, String collectionName) {
		String elementPath=getElementJSONPath(elementName);
		if(elementPath == null) {
			return null;
		}
		if(elementPath.contains(".contextMap.")) {
			elementPath=elementPath.replace("$.", "");
			return elementPath;
		}
		elementPath=elementPath.replace("$.", "automatedCollateralEvaluationRequest.");
		elementPath=elementPath.replace("[", ".");
		elementPath=elementPath.replace("]", "");
		
		return elementPath;
	}
}
