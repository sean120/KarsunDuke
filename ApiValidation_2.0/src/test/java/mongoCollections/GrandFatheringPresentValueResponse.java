package mongoCollections;

import java.util.HashMap;

public class GrandFatheringPresentValueResponse {
	public String returnGrandFatheringAceDataJsonPath(String elementName) {
		HashMap<String, String> elementJsonPath = new HashMap<String, String>();
		// ACE Response JSON
		// Elements----------------------------------------------------------
		elementJsonPath.put(("propertyValuationAmount").toLowerCase(), "$.presentValueResponse.presentValueResponseData.propertyValuationData.propertyValuationAmount");
		elementJsonPath.put(("propertyValuationType").toLowerCase(), "$.presentValueResponse.presentValueResponseData.propertyValuationData.propertyValuationType");
		
		String messageText = elementJsonPath.get(elementName.toLowerCase());
		return messageText;
	}

}
