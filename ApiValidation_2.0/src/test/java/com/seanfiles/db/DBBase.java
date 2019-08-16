package com.seanfiles.db;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.Document;

import com.jayway.jsonpath.DocumentContext;

public class DBBase {
	Document doc;
	DocumentContext dbDataJson;
	String collectionName;

	public DocumentContext getDBData() {
		return dbDataJson;
	}
	public Document getDBDoc() {
		return doc;
	}
	public String getObjectID() {
		return doc.getObjectId("_id").toString();		
	}
	
	public String getDate(String element, String fmt) {
		SimpleDateFormat simpleDateFormat =
		        new SimpleDateFormat(fmt);
		Date d=doc.getDate(element);
		String dateStr = simpleDateFormat.format(d);
		return dateStr;
	}
	
	public String getCollectionName() {
		return collectionName;
	}
	public String getElementValue(String elementName) {
		// TODO Auto-generated method stub
		return null;
	}
}
