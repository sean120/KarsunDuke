package mongoCollections;

import java.util.HashMap;

public class GrandFatheringPresentValueData {
	public String returnGrandFatheringAceDataJsonPath(String elementName) {
		HashMap<String, String> elementJsonPath = new HashMap<String, String>();
		// ACE Response JSON
		// Elements----------------------------------------------------------
		elementJsonPath.put(("sourceType").toLowerCase(), "$.transactionContext.sourceType");
		elementJsonPath.put(("loanIdentifier").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[0].loanIdentifier");
		elementJsonPath.put(("loanIdentifierType").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[0].loanIdentifierType");
		elementJsonPath.put(("addressLineText").toLowerCase(), "$.aceRequest.address.addressLineText");
		elementJsonPath.put(("postalCode").toLowerCase(), "$.aceRequest.address.postalCode");
		
		String messageText = elementJsonPath.get(elementName.toLowerCase());
		return messageText;
	}

}
