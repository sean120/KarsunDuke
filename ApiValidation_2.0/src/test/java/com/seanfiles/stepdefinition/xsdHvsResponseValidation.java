package com.seanfiles.stepdefinition;
//package com.seanfiles.stepdefinition;
//
//import java.util.Comparator;
//import java.util.Map;
//
//import org.junit.Test;
//
//import com.seanfiles.db.ACECheckAPIDB;
//import com.seanfiles.helper.ACECheckAPI;
//import com.seanfiles.utils.MongoDBUtils;
//import com.seanfiles.utils.MongoQueries;
//
//import cucumber.api.DataTable;
//import cucumber.api.PendingException;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.When;
//
//public class xsdHvsResponseValidation{
//	
//	public static void main(String[] args) {
//		
//			
//		
//		Comparator<String> stringComparatorLambda = 
//				(String o1, String o2)-> {return o1.compareTo(o2);};
//				
//			int a1 =	stringComparatorLambda.compare("");
//				System.out.println(a1);
//				
//				
//				Comparator<String> stringComparatorLambda1 = 
//						(o1, o2) -> o1.compareTo(o2);
//						
//		}
//			
//		
//
//}
//
//	
////
//////
////////	
////////	//@When("^User submits ACE Check API \\(No APIGEE\\) request and gets response$")
////////	@Test
////////	public void submitACEACheckPINoAPIGEE(DataTable dataTable) throws Throwable {
////////		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
////////		ACECheckAPI.submitACECheckAPINoAPIGEE(dataMap);
////////	}
////////	
////////	@Test
////////	public void reRetriewsHVS_Response(){
////////		MongoDBUtils.fetchqueryResult(, selection, sortMethod)
////////		MongoQueries.fetchqueryResult(, selection, projection)
////////		ACECheckAPIDB.getCurrentACEAPIDBDocuments(HVSresresponse, )
////////		
////////	}
////////	
////////@Test
////////
////////public void UserValidatesHVSresPonseToXSD(Xml){
////////
////////	AssertTrue(matchesXsdInClasspath(), )
////////	
////////	
////////}
////////
////////
////////public void () -> {
////////    // Write code here that turns the phrase above into concrete actions
////////    throw new PendingException();
////////});
////////
////////@Given("^User validate both HVSResponse from mongodb validates current HVSResponse_XSD$")
////////public void () -> {
////////    // Write code here that turns the phrase above into concrete actions
////////    throw new PendingException();
////////};
////////
////////
////////}
