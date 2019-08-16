package mongoCollections;

import java.util.HashMap;

public class GrandFatheringAceResponse {
	public String returnGrandFatheringAceDataJsonPath(String elementName) {
		HashMap<String, String> elementJsonPath = new HashMap<String, String>();
		// ACE Response JSON
		// Elements----------------------------------------------------------
		elementJsonPath.put(("message").toLowerCase(), "$.aceResponse.errors.error[0].errorResponse.message");
		elementJsonPath.put(("addressLineText").toLowerCase(), "$.loanData.address.addressLineText");
		elementJsonPath.put(("cityName").toLowerCase(), "$.loanData.address.cityName");
		elementJsonPath.put(("postalCode").toLowerCase(), "$.loanData.address.postalCode");
		elementJsonPath.put(("stateCode").toLowerCase(), "$.loanData.address.stateCode");
		elementJsonPath.put(("alternateAppraisalDecisionStatusType").toLowerCase(), "$.aceResponse.aceResponseData.alternateAppraisalDecisionData.alternateAppraisalDecisionStatusType");
		elementJsonPath.put(("homeValueExplorerOptionType").toLowerCase(), "$.aceResponse.aceResponseData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerOptionType");
		

		String messageText = elementJsonPath.get(elementName.toLowerCase());
		return messageText;
	}

}
