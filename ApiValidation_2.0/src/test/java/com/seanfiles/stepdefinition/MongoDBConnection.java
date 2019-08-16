package com.seanfiles.stepdefinition;
//package com.seanfiles.stepdefinition;
//
//import com.seanfiles.helper.TestConfig;
//import com.seanfiles.utils.Base64Util;
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
//import com.mongodb.client.MongoDatabase;
//
//public class MongoDBConnection {
//	
//	
//	private static MongoClient mongoClient = null;
//	private static MongoDatabase mongoDB = null;
//	
//	
//	private static MongoClient getMongoClient(String mongoDBUri, String mongoDBUserName, String mongoDBPasswordBase64Encoded) {
//		if(mongoClient != null ) {
//			return mongoClient; 	
//		}
//		
//		String mongoDBUserName1 = "C48461";
//		String mongoDBPassword = "$uyghur33U";
//		String bcapiMongoUri = "mongodb://"+ mongoDBUserName1 +":"+mongoDBPassword+ "@"+mongoDBUri;
//		
//		MongoClientURI uri = new MongoClientURI(bcapiMongoUri);
//		mongoClient = new MongoClient(uri);
//		return mongoClient;
//		
//		
//	}	
//		
//private static MongoDatabase getMongoDatabase(){
//	
//	if(mongoDB != null){
//		return mongoDB;
//	}
//	String mongoDBUri =TestConfig.getProperty("MongoDBuri");
//	String mongoDB 
//	
//	
//	return mongoDB; 
//}
//		
//		
//		
//		
//}

