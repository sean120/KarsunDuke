package com.seanfiles.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.seanfiles.helper.TestConfig;
import com.seanfiles.helper.TestScenario;

public class MongoDBUtils {
	
	private static MongoClient mongoClient = null;
	private static MongoDatabase mongoDB=null;

	private static MongoClient getMongoClient(String mongoDBUri, String mongoDBUserName, String mongoDBPasswordBase64Encoded) {
		if(mongoClient != null ) {
			return mongoClient;
		}		
		
		String bcapiMongoUri = "mongodb://" + mongoDBUserName + ":" + Base64Util.decodeString(mongoDBPasswordBase64Encoded).trim() + "@" + mongoDBUri;
		MongoClientURI uri = new MongoClientURI(bcapiMongoUri);
		mongoClient = new MongoClient(uri);
		return mongoClient;
	}
	
	private static MongoDatabase getMongoDatabase() {
		if(mongoDB != null) {
			return mongoDB;
		}
		
		String mongoDBUri=TestConfig.getProperty("MongoDBUri");
		String mongoDBUserName=TestConfig.getProperty("MongoDBUserName");
		String mongoDBPasswordBase64Encoded=TestConfig.getProperty("MongoDBPasswordBase64Encoded");
		String mongoDBName=TestConfig.getProperty("MongoDBName");
		
		mongoDB = getMongoClient(mongoDBUri, mongoDBUserName, mongoDBPasswordBase64Encoded).getDatabase(mongoDBName);
		return mongoDB;
	}
	
	public static Document getDocByID(String collectionName, String docID) throws Exception {
		Document query = new Document();
	    query.put("_id", new ObjectId(docID));	    

		return fetchqueryResult(collectionName, query, BasicDBObject.parse("{}"));
	}
	
	public static Document getDocByID(String collectionName, String docID, String idFieldName) throws Exception {
		Document query = new Document();
	    query.put(idFieldName, new ObjectId(docID));	    

		return fetchqueryResult(collectionName, query, BasicDBObject.parse("{}"));
	}
	
	public static Document getMostRecentDocument(String collectionName, String sortCriteria) throws Exception {
		return fetchqueryResult(collectionName, new Document(), BasicDBObject.parse(sortCriteria));
	}
	
	public static String getSourceType(Document doc) {
		Document sourceContext=(Document) doc.get("transactionContext");
		return sourceContext.getString("sourceType");
		
	}
	
	public static String getSynteticTransaction(Document doc) {
		Document sourceContext =(Document) doc.get("transactionContext");
		return sourceContext.getString("syntheticTransaction");

	}
	
	private static void updateDocument(String collectionName, Document doc, BasicDBObject newDocument) {
		BasicDBObject searchQuery = new BasicDBObject().append("_id", doc.getObjectId("_id"));

		MongoCollection<Document> collection = getMongoDatabase().getCollection(collectionName);
		collection.updateOne(searchQuery, newDocument);
	}
	
	public static Date convertToDate(String dateStr, String fmt, String timeZone) {
		Date dt=null;
		SimpleDateFormat dateFormatter = new SimpleDateFormat(fmt);
		dateFormatter.setTimeZone(TimeZone.getTimeZone(timeZone));
		try {
			dt=dateFormatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}
	
	public static void updateDocument(String collectionName, Document doc,String fieldname, String fieldValue) {
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject().append(fieldname, fieldValue));
		updateDocument(collectionName,doc,newDocument);
	}
	
	public static void updateDocument(String collectionName, Document doc,String fieldname, Date fieldValue) {
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject().append(fieldname, fieldValue));
		updateDocument(collectionName,doc,newDocument);
	}
	
	public static Document getDocByField(String collectionName, String fieldName, String fieldValue, String sortCriteria) throws Exception {
		Document query = new Document();
	    query.put(fieldName, fieldValue);

		Document doc = fetchqueryResult(collectionName, query, BasicDBObject.parse(sortCriteria));
		return doc;
	}
	
	public static Document getDocByField(String collectionName, String fieldName, Long fieldValue, String sortCriteria) throws Exception {
		Document query = new Document();
	    query.put(fieldName, fieldValue);

		Document doc = fetchqueryResult(collectionName, query, BasicDBObject.parse(sortCriteria));
		return doc;
	}
	
	public static Document getDocByQuery(String collectionName, Map<String, String> qryMap) throws Exception {
		Document query = new Document();
		for (Map.Entry<String, String> qryMapItem : qryMap.entrySet())
		{
			query.append(qryMapItem.getKey(), qryMapItem.getValue());
		}

		Document doc = fetchqueryResult(collectionName, query, BasicDBObject.parse("{}"));
		return doc;
	}

	public static List<String> getDocIDsByFieldValueStartsWith(String collectionName, String fieldName, String valueStartsWith) throws Exception {
		Document regexQuery = new Document();
		regexQuery.append("$regex", valueStartsWith + ".*");
		Document criteria = new Document();
		criteria.put(fieldName, regexQuery);
		//System.out.println(criteria.toJson().toString());

		FindIterable<Document> docs= fetchqueryResults(collectionName, criteria, BasicDBObject.parse("{}"));
		//return docs.first();
		List<String> docIds = new ArrayList<String>();
		for(Document doc : docs) {
			docIds.add(doc.getLong("_id").toString());
		}
		return docIds;
	}

	public static FindIterable<Document> getDocsBysFieldValue(String collectionName, String fieldName, String value) throws Exception {
		Document criteria = new Document();
		criteria.put(fieldName, value);
		//System.out.println(criteria.toJson().toString());

		FindIterable<Document> docs= fetchqueryResults(collectionName, criteria, BasicDBObject.parse("{}"));
		return docs;
	}

	public static void updateDocument(String collectionName, Document doc, Map<String, String> qryMap) throws Exception {
		Document query = new Document();
		for (Map.Entry<String, String> qryMapItem : qryMap.entrySet())
		{
			query.append(qryMapItem.getKey(), qryMapItem.getValue());
		}
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", query);
		updateDocument(collectionName,doc,newDocument);
	}

	public static Document fetchqueryResult(String collectionName, Document selection, Bson sortMethod)
			throws Exception {
		Document doc = null;
			MongoCollection<Document> collection = getMongoDatabase().getCollection(collectionName);
			FindIterable<Document> mongoresultSubset = collection.find(selection).sort(sortMethod);
			if (mongoresultSubset == null) {
				System.out.println("The query returned and empty result.");
			} else {
				doc = mongoresultSubset.first();				
			}			
		return doc;
	}
	
	public static FindIterable<Document> fetchqueryResults(String collectionName, Document selection, Bson sortMethod)
			throws Exception {
			MongoCollection<Document> collection = getMongoDatabase().getCollection(collectionName);
			FindIterable<Document> mongoresultSubset=collection.find(selection).sort(sortMethod);
			if (mongoresultSubset == null) {
				System.out.println("The query returned and empty result.");
			}
//			else {
//				System.out.println("The query returned result."+mongoresultSubset.first().toJson().toString());
//			}
			return mongoresultSubset;
	}
	
	public static String getDocID(Document doc) {
		return doc.getObjectId("_id").toString();
	}

	public static String getTransConextDocID(Document doc) {
		Document transContext=(Document) doc.get("transactionContext");
		return transContext.getObjectId("_id").toString();
	}

	public static String getDocCreatedDate(Document doc) {
		Date date = doc.getDate("createdDate");
		SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		formattedDate.setTimeZone(TimeZone.getTimeZone("UTC"));
		return formattedDate.format(date);
	}
	
	public static void removeDocumentsByIDList(String collectionName, List<ObjectId> postedDocIDs) {
		TestScenario.writeToScenario("Removing documents :"+postedDocIDs);
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new BasicDBObject("$in", postedDocIDs));
		MongoCollection<Document> collection = getMongoDatabase().getCollection(collectionName);
		collection.deleteMany(query);
	}
	
	public static void removeDocumentsByQuery(String collectionName, Map<String, String> qryMap) throws Exception {
		Document query = new Document();
		if(qryMap.size() <= 0 ) {
			return;
		}
		for (Map.Entry<String, String> qryMapItem : qryMap.entrySet())
		{
			String elmName=qryMapItem.getKey().trim();
			String elmVal=qryMapItem.getValue().trim();
			if(elmName == null || elmVal == null) {
				return;
			}
			if(elmName.length() ==0 || elmVal.length() == 0) {
				return;
			}
			query.append(elmName, elmVal);
		}
		TestScenario.writeToScenario("Removing documents by query:"+qryMap+" from collection "+collectionName);
		MongoCollection<Document> collection = getMongoDatabase().getCollection(collectionName);
		collection.deleteMany(query);
	}

	public static Document getDocByQry(String collectionName, Map<String, String> qryMap, String sortcriteria) throws Exception {
		Document query = new Document();
		if(qryMap.size() > 0 ) {
			for (Map.Entry<String, String> qryMapItem : qryMap.entrySet())
			{
				String elmName=qryMapItem.getKey();
				String elmVal=qryMapItem.getValue();
				if(elmName != null) {
					elmName=elmName.trim();
					if(elmVal != null) {
						elmVal=elmVal.trim();
					}
					query.put(elmName, elmVal);
				}
			}
		}
		TestScenario.writeToScenario("Retrieving documents by query:"+qryMap);
		Document doc = fetchqueryResult(collectionName, query, BasicDBObject.parse(sortcriteria));
		return doc;
	}

}