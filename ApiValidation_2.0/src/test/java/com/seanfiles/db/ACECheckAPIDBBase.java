package com.seanfiles.db;

import org.bson.Document;

import com.jayway.jsonpath.JsonPath;
import com.seanfiles.elements.ACECheckAPIElements;
import com.seanfiles.elements.ACECheckAPIElementsPaths;
import com.seanfiles.utils.JSONUtilities;

public class ACECheckAPIDBBase extends DBBase{

	
	public ACECheckAPIDBBase(Document dbDoc, String collectionName) {
		doc=dbDoc;
		dbDataJson=JsonPath.parse(doc.toJson());
		this.collectionName=collectionName;
	}
	
	
	@Override
	public String getElementValue(String elementName) {
		String elementValue=null;
		try {
			String jsonPathDB=ACECheckAPIElementsPaths.getACEAPIElementsPaths().getACEAPIDBElementPath(elementName,collectionName);	
			elementValue=JSONUtilities.getJsonElementValue(getDBData(),jsonPathDB);
			elementValue=normaliseElementValue(elementName, elementValue);
		}
		catch(Exception e) {
			System.out.println("----Exception: elementName "+elementName+" "+e.getStackTrace());
		}
		return elementValue;
	}

	private String normaliseElementValue(String elementName, String elementValue) {
		switch(elementName) {
		case ACECheckAPIElements.PartyRoleType:
			switch(elementValue) {
			case "LOAN_SELLER":
				elementValue="LoanSeller";
				break;
			}
			break;
		case ACECheckAPIElements.LoanPurposeType:
			switch(elementValue) {
			case "PURCHASE":
				elementValue="Purchase";
				break;
			case "REFINANCE":
				elementValue="Refinance";
				break;
			}
			break;
		}
		return elementValue;
	}

	
	public Long getLongElementValue(String elementPath) {
		return doc.getLong(elementPath);		
	}
	

}
