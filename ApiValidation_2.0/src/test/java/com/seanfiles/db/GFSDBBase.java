package com.seanfiles.db;

import org.bson.Document;

import com.jayway.jsonpath.JsonPath;
import com.seanfiles.elements.ACEGFSElementsPaths;
import com.seanfiles.utils.JSONUtilities;

public class GFSDBBase extends DBBase{
	
	public GFSDBBase(Document dbResponseACE, String collectionName) {
		doc=dbResponseACE;
		dbDataJson=JsonPath.parse(doc.toJson());
		this.collectionName=collectionName;
	}
	
	@Override
	public String getElementValue(String elementName) {
		String elementValue=null;
		try {
			String jsonPathDB=ACEGFSElementsPaths.getGFSElementsPaths().getGFSDBElementPath(elementName,collectionName);	
			elementValue=JSONUtilities.getJsonElementValue(getDBData(),jsonPathDB);
		}
		catch(Exception e) {
			
		}
		return elementValue;
	}

}
