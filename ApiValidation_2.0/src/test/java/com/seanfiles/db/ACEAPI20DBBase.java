package com.seanfiles.db;

import org.bson.Document;

import com.jayway.jsonpath.JsonPath;
import com.seanfiles.elements.ACEAPI20ElementsPaths;
import com.seanfiles.utils.JSONUtilities;

public class ACEAPI20DBBase extends DBBase{

	
	public ACEAPI20DBBase(Document dbDoc, String collectionName) {
		doc=dbDoc;
		dbDataJson=JsonPath.parse(doc.toJson());
		this.collectionName=collectionName;
	}
	
	
	@Override
	public String getElementValue(String elementName) {
		String elementValue=null;
		try {
			String jsonPathDB=ACEAPI20ElementsPaths.getACEAPI20ElementsPaths().getACEAPI20DBElementPath(elementName,collectionName);	
			elementValue=JSONUtilities.getJsonElementValue(getDBData(),jsonPathDB);
			//elementValue=normaliseElementValue(elementName, elementValue);
		}
		catch(Exception e) {
			System.out.println("----Exception: elementName "+elementName+" "+e.getStackTrace());
		}
		return elementValue;
	}

//	private String normaliseElementValue(String elementName, String elementValue) {
//		switch(elementName) {
//		case ACEAPI20Elements.PartyRoleType:
//			switch(elementValue) {
//			case "LOAN_SELLER":
//				elementValue="LoanSeller";
//				break;
//			}
//			break;
//		case ACEAPI20Elements.LoanPurposeType:
//			switch(elementValue) {
//			case "PURCHASE":
//				elementValue="Purchase";
//				break;
//			case "REFINANCE":
//				elementValue="Refinance";
//				break;
//			}
//			break;
//		}
//		return elementValue;
//	}

	
	public Long getLongElementValue(String elementPath) {
		return doc.getLong(elementPath);		
	}
	

}
