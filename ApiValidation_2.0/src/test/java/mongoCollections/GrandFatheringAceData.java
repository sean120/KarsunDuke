package mongoCollections;

import java.util.HashMap;

public class GrandFatheringAceData {
	public String returnGrandFatheringAceDataJsonPath(String elementName) {
		HashMap<String, String> elementJsonPath = new HashMap<String, String>();
		// ACE Response JSON
		// Elements----------------------------------------------------------
		elementJsonPath.put(("sourceType").toLowerCase(), "$.transactionContext.sourceType");
		elementJsonPath.put(("loanIdentifier").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[0].loanIdentifier");
		elementJsonPath.put(("loanIdentifierType").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[0].loanIdentifierType");
		elementJsonPath.put(("addressLineText").toLowerCase(), "$.loanData.address.addressLineText");
		elementJsonPath.put(("postalCode").toLowerCase(), "$.aceRequest.address.postalCode");
		elementJsonPath.put(("alternateAppraisalDecisionStatusType").toLowerCase(), "$.aceData.alternateAppraisalDecisionData.alternateAppraisalDecisionStatusType");
		elementJsonPath.put(("homeValueExplorerOptionType").toLowerCase(), "$.aceData.homeValueExplorerDataContainer.homeValueExplorerData[1].propertyAppraiserValuationContainer.homeValueExplorerOptionType");
		elementJsonPath.put(("propertyValuationEffectiveDateTime").toLowerCase(), "$.aceData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.propertyValuationEffectiveDateTime");
			
		
		
		String messageText = elementJsonPath.get(elementName.toLowerCase());
		return messageText;
	}

}
