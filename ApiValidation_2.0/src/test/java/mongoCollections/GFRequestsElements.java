package mongoCollections;

import java.util.HashMap;

public class GFRequestsElements {
	public String returnGrandFatheringAceDataJsonPath(String elementName) {
		HashMap<String, String> elementJsonPath = new HashMap<String, String>();
 
		elementJsonPath.put(("sourceApplicationName").toLowerCase(), "$Needs Path");
		elementJsonPath.put(("loanIdentifier").toLowerCase(), "$.grandFatheringData.loanIdentifications.loanIdentification[0].loanIdentifier");
		elementJsonPath.put(("loanIdentifierType").toLowerCase(), "$.grandFatheringData.loanIdentifications.loanIdentification[0].loanIdentifierType");
		elementJsonPath.put(("addressLineText").toLowerCase(), "$.grandFatheringData.address.addressLineText");
		elementJsonPath.put(("postalCode").toLowerCase(), "$.grandFatheringData.address.postalCode");
		
		String messageText = elementJsonPath.get(elementName.toLowerCase());
		return messageText;
	}

}
