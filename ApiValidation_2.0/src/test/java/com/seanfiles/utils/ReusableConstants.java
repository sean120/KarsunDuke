package com.seanfiles.utils;

public class ReusableConstants {

	public static final String EMPTY_STRING = "";
	public static final String APPLICATION_JSON = "application/json";
	public static final String APPLICATION_JAVASCRIPT = "application/javascript";
	public static final String JSON = "json";
	public static final String ANY = "any";
	public static final String TEXT_PLAIN = "text/plain";
	public static final String TEXT = "text";
	public static final String APPLICATION_XML = "application/xml";
	public static final String XML = "XML";
	public static final String HTML = "html";
	public static final String URLENC = "URLENC";
	public static final String BINARY = "BINARY";
	public static final String APPLICATION_URLENCODED = "application/x-www-form-urlencoded";
	public static final String APPLICATION_OCTET = "application/octet-stream";
	public static final String TEXT_XML = "text/xml";
	public static final String APPLICATION_XHTML = "application/xhtml+xml";
	public static final String TEXT_JAVASCRIPT = "text/javascript";
	public static final String TRUE = "true";
	public static final String InternetProxyHost = "nzenpxy.fhlmc.com";
	public static final int InternetProxyPort = 9400;
	public static final String uatUserpwd = "JHV5Z2h1cjMzVQ==";
	public static final String mongoUATDBUri = "he3qlxvtdbs584.fhlmc.com:27000/aceu01db?authsource=$external&replicaSet=lasapi-uat-rs&authMechanism=PLAIN";

	public static final int HTTPSUCCESSCODE = 200;
	public static final int HTTPERRORCODE[] = { 500, 401, 405, 404 };
	public static final String userpwd = "rs2MOn#zB46G7PAT";
	public static final String mongoSITDBUri = "he3qlxvddbs553.fhlmc.com:27000/aceci01db?authsource=$external&replicaSet=lasapi-dev-rs&authMechanism=PLAIN";
	public static final String decisionfromEDSRespxpath = "/EDSRuleResponse/AnswerContainer/Answer/DecisionContainer/Decision";
	public static final String conclusionfromEDSRespxpath = "RowIdContainer/RowIdWrapper/Conclusion";
	public static final String EDSPropertyPurchasePriceAmount = "/EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer/LoanDetailContainer/LoanApplications/LoanApplicationContainer/LoanApplication/BorrowerPropertyPurchasePriceAmount";
	public static final String EDSPropertyValuationAmount = "/EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyValuationContainer/PropertyValuation/PropertyValuationAmount";
	//public static final String clientID = "nFCT7gBQ5zvBjb5vQH4D6CI6AI99nSaQ";
	//public static final String clientSecret = "tABc17kAWU5Jd2wf";
	public static final String clientID = "QnqaRPWTQtgManGMEgXdq4opZXjW7WS7";
	public static final String clientSecret = "6Wu1GMd5zGOrShMy";
	public static final String apigeegrantType = "password";
	public static final String POST = "POST";
	public static final String GET = "GET";

	/**
	 * General Exception messages
	 */

	public static final String ACCEPTTYPE_CONTENTTYPE_REQUESTCAPTURE_NULL = "AcceptType or ContentType or RequestCapture instance is null.";
	public static final String PROPERTY_NOT_FOUND = "Property not found";
	public static final String CALL_ACTUAL_SERVICE = "Call Actual service.";
	public static final String SERVICE_NOT_FOUND_IN_FEATURE_FILE = "Service name is not found in feature file.";
	public static final String DATAPATH_NOT_FOUND_IN_FEATURE_FILE = "DatasetPath is not found in feature file.";
	public static final String API_CREDENTIALS_NOT_FOUND_IN_FEATURE_FILE = "Please do not provide NULL credentials !";
	public static final String BASEURL_EMPTY = "Baseurl empty.";
	public static final String INVALID_BASEURL = "Invalid baseurl event : ";
	public static final String RESOURCE_EMPTY = "Resource empty.";
	public static final String INVALID_RESOURCE_EVENT = "Invalid resource event : ";
	public static final String CONTENT_TYPE_EMPTY = "Contenttype empty.";
	public static final String INVALID_CONTRNT_TYPE = "Invalid contenttype event : ";
	public static final String ACCEPT_TYPE_EMPTY = "Accept type empty";
	public static final String INVALID_ACCEPT_TYPE = "Invalid Accept event :";
	public static final String OPERATION_STRING_EMPTY = "Operation String Empty";
	public static final String INVALID_OPERATION_EVENT = "Invalid Operation event :";
	public static final String SCHEMA_STRING_EMPTY = "Schema String Empty";
	public static final String INVALID_SCHEMA_EVENT = "Invalid Schema event :";
	public static final String JSON_PAYLOAD_ISNULL = "Json payload is null";

}
