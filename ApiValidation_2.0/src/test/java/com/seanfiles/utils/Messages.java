package com.seanfiles.utils;

import java.util.HashMap;

public class Messages {

	public String returnMessageText(String textToRetrieve) {
		HashMap<String, String> messageTextHashMap = new HashMap<String, String>();

		// XML input
		// values----------------------------------------------------------------------------------------------------------------------------------------------------
		messageTextHashMap.put(("SV001").toLowerCase(),
				"A Party Role Identifier is required. Please verify and re-submit.");
		messageTextHashMap.put(("SV002").toLowerCase(),
				"The Address Line Text for the property is required Please verify and re-submit.");
		messageTextHashMap.put(("SV003").toLowerCase(),
				"The City name for the property is required. Please verify and re-submit.");
		messageTextHashMap.put(("SV004").toLowerCase(),
				"The Postal Code for the property is required. Please verify and re-submit.");
		messageTextHashMap.put(("SV005").toLowerCase(),
				"A valid Loan Purpose Type (Purchase or Refinance) is required. Please verify and re-submit");
		messageTextHashMap.put(("SV006").toLowerCase(),
				"A Property Estimated Value Amount is required. Please verify and re-submit.");
		messageTextHashMap.put(("SV007").toLowerCase(),
				"The State code for the property is required. Please verify and re-submit.");
		messageTextHashMap.put(("SV008").toLowerCase(),
				"A Sales Contract Amount is required. Please verify and re-submit.");
		messageTextHashMap.put(("SV009").toLowerCase(), "A Party Role Type is required. Please verify and re-submit.");
		messageTextHashMap.put(("ContainerMin").toLowerCase(), "Container needs to have one cardinality");
		messageTextHashMap.put(("ContainerMax").toLowerCase(), "Container exceeds max cardinality");
		messageTextHashMap.put(("AWP0001").toLowerCase(),
				"Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements for the appraisal waiver, as per Freddie Mac’s Selling Guide.");
		messageTextHashMap.put(("AWP0002").toLowerCase(),
				"Based on the submitted data, the subject property is not eligible for an appraisal waiver.");
		messageTextHashMap.put(("AWP0003").toLowerCase(),
				"Unable to assess the submitted property for appraisal waiver eligibility at this time. Resubmit for an assessment.");
		messageTextHashMap.put(("AWP0004").toLowerCase(),
				"The subject property address cannot be validated. Please confirm accuracy and resubmit if necessary.");
		messageTextHashMap.put(("AWP0001MSGText").toLowerCase(),
				"Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver.");

		messageTextHashMap.put(("AWP0002MSGText").toLowerCase(),
				"Based on the submitted data, the subject property is not eligible for an appraisal waiver.");
		messageTextHashMap.put(("AWP0003MSGText").toLowerCase(),
				"Unable to assess the submitted property for appraisal waiver eligibility at this time. Resubmit for an assessment.");
		messageTextHashMap.put(("AWP0004MSGText").toLowerCase(),
				"The subject property address cannot be validated. Please confirm accuracy and resubmit if necessary.");

		messageTextHashMap.put(("AWP0001MSGCode").toLowerCase(), "AWP0001");
		messageTextHashMap.put(("AWP0002MSGCode").toLowerCase(), "AWP0002");
		messageTextHashMap.put(("AWP0003MSGCode").toLowerCase(), "AWP0003");
		messageTextHashMap.put(("AWP0004MSGCode").toLowerCase(), "AWP0004");

		String messageText = messageTextHashMap.get(textToRetrieve.toLowerCase());
		return messageText;
	}

}
