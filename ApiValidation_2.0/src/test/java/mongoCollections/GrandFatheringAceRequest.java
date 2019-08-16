package mongoCollections;

import java.util.HashMap;

public class GrandFatheringAceRequest {
	public String returnGrandFatheringAceDataJsonPath(String elementName) {
		HashMap<String, String> elementJsonPath = new HashMap<String, String>();
		// ACE Response JSON
		// Elements----------------------------------------------------------
		elementJsonPath.put(("sourceApplicationName").toLowerCase(), "$.aceRequest.sourceApplicationName");
		elementJsonPath.put(("loanIdentifier1").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[0].loanIdentifier");
		elementJsonPath.put(("loanIdentifier2").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[1].loanIdentifier");
		elementJsonPath.put(("loanIdentifier3").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[2].loanIdentifier");
		elementJsonPath.put(("loanIdentifier4").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[3].loanIdentifier");
		elementJsonPath.put(("loanIdentifier5").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[4].loanIdentifier");
		elementJsonPath.put(("loanIdentifier6").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[5].loanIdentifier");
		elementJsonPath.put(("loanIdentifierType1").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[0].loanIdentifierType");
		elementJsonPath.put(("loanIdentifierType2").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[1].loanIdentifierType");
		elementJsonPath.put(("loanIdentifierType3").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[2].loanIdentifierType");
		elementJsonPath.put(("loanIdentifierType4").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[3].loanIdentifierType");
		elementJsonPath.put(("loanIdentifierType5").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[4].loanIdentifierType");
		elementJsonPath.put(("loanIdentifierType6").toLowerCase(), "$.aceRequest.loanIdentifications.loanIdentification[5].loanIdentifierType");	
		elementJsonPath.put(("addressLineText").toLowerCase(), "$.aceRequest.address.addressLineText");
		elementJsonPath.put(("postalCode").toLowerCase(), "$.aceRequest.address.postalCode");
		
		String messageText = elementJsonPath.get(elementName.toLowerCase());
		return messageText;
	}

}
