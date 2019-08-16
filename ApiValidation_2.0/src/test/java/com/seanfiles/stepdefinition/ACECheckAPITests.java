package com.seanfiles.stepdefinition;

import java.util.List;
import java.util.Map;

import com.seanfiles.db.ACECheckAPIDB;
import com.seanfiles.db.GFSDB;
import com.seanfiles.helper.ACECheckAPI;
import com.seanfiles.helper.ACECheckAPIDataValidation;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.services.ACECheckAPIDerivedData;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ACECheckAPITests {
	@Before
	public void loadTestScenario(Scenario s) {
		TestScenario.setScenario(s);
	}

	@When("^User submits ACEAPI request file \"([^\"]*)\"$")
	public void submitACEAPIRequest(String JSONFileName) throws Throwable {
		ACECheckAPI.submitACEAPIRequestFile(JSONFileName);
	}

	@When("^User submits ACEAPI request$")
	public void submitACEAPIData(DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACECheckAPI.submitACEAPIData(dataMap);
	}

	@When("^User verifies the ACE Check API response$")
	public void verifyACECheckAPIResponse(DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACECheckAPIDataValidation.verifyACECheckAPIResponse(dataMap);
	}

	@When("^User submits ACE Check API request$")
	public void submitACEACheckPIData(DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACECheckAPI.submitACECheckAPIData(dataMap);
	}
	
	@When("^User submits ACE Check API \\(No APIGEE\\) request$")
	public void submitACEACheckPINoAPIGEEData(DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACECheckAPI.submitACECheckAPINoAPIGEEData(dataMap);
	}
	
	@When("^User submits ACE Check API \\(No APIGEE\\) request and gets response$")
	public void submitACEACheckPINoAPIGEE(DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACECheckAPI.submitACECheckAPINoAPIGEE(dataMap);
	}
	
	@Then("^ACE Check API returns response$")
	public void getACECheckAPIResponse() throws Throwable {
		ACECheckAPI.getACECheckAPIResponse();
	}
	
	@Then("^ACE Check API returns HTTP status code (\\d+)$")
	public void checkACEAPIReturnCode(int expectedReturnCode) throws Throwable {
		ACECheckAPI.checkACEAPIReturnCode(expectedReturnCode);
	}
	
	@Given("^ACEAPI request$")
	public void setACEAPIRequest(DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACECheckAPI.setACEAPIRequest(dataMap);
	}

	@Given("^The ACEAPI1.0 RequestID is \"([^\"]*)\"$")
	public void setACEAPI10RequestID(String ACEAPI10RequestID) throws Throwable {
		ACECheckAPI.clearServiceData();
		ACECheckAPIDerivedData.getNewACEAPI10DerivedData(ACEAPI10RequestID);
	}

	@Given("^User creates the \"([^\"]*)\" using the ACEAPI DB document$")
	public void createACEAPI10FromDBDoc(String type) throws Throwable {
		ACECheckAPI.createFromDBDoc(type);
	}

	@Then("^User retrieves the latest \"([^\"]*)\" from ACEAPI Database$")
	public void getLatestACEAPIDoc(String type) throws Throwable {
		ACECheckAPIDB.getLatestDoc(type);
	}
	
	@Then("^User retrieves all the corresponding Documents from ACECheckAPI Database$")
	public void getCorrespondingDocs() throws Throwable {
		ACECheckAPIDB.getCorrespondingDocs();
	}

	@Then("^User retrieves the corresponding \"([^\"]*)\" from ACEAPI Database$")
	public void getCorrespondingACEAPIDoc(String type) throws Throwable {
		ACECheckAPIDB.getCorrespondingDoc(type);
	}

	@Then("^User verifies the \"([^\"]*)\" retrieved from ACEAPI Database$")
	public void verifyACEAPIDoc(String type) throws Throwable {
		ACECheckAPIDataValidation.verifyDBDoc(type);
	}

	@Then("^User verifies \"([^\"]*)\" wrt \"([^\"]*)\"$")
	public void verifyServiceData(String tgt, String src) throws Throwable {
		ACECheckAPIDataValidation.verifyServiceData(tgt, src);
	}

	@Then("^User verifies all the data elements from ACE Check API \"([^\"]*)\" data are mapped to \"([^\"]*)\"$")
	public void compareServiceData(String src, String tgt) throws Throwable {
		ACECheckAPIDataValidation.compareServiceData(src, tgt, null);
	}

	@Then("^User verifies the ACE Check API \"([^\"]*)\" data contains the following values$")
	public void verifyData(String type, DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACECheckAPIDataValidation.verifyServiceData(type, dataMap);
	}
	
	@Then("^User verifies the following data elements from ACE Check API \"([^\"]*)\" data are mapped to \"([^\"]*)\"$")
	public void verifyServiceData(String src, String tgt, DataTable dataTable) throws Throwable {
		List<String> elementsList = dataTable.asList(String.class);
		ACECheckAPIDataValidation.compareServiceData(src, tgt, elementsList);
	}
	
	@Then("^User retrieves the corresponding \"([^\"]*)\" from GFS Database$")
	public void getCorrespondingGFSDoc(String type) throws Throwable {
		GFSDB.getCorrespondingDoc(type);
	}

	// validate containers
	@Then("^User verifies the \"([^\"]*)\" data in \"([^\"]*)\" is copied to \"([^\"]*)\"$")
	public void verifyServiceData(String srcContainer, String src, String tgt) throws Throwable {
		ACECheckAPIDataValidation.verifyServiceData(srcContainer, src, tgt);
	}
	
	@Then("^User retrieves all ACECheckAPI DocIDs having the element \"([^\"]*)\" value starts with \"([^\"]*)\"$")
	public void listDocsIDs(String elementName, String elementValueStartsWith) throws Throwable {
		ACECheckAPIDB.listAllACECheckRequestDocIDsByElementValueStartsWith(elementName, elementValueStartsWith);
	}

	@Then("^User prepares input data for GFS POST data fix script$")
	public void prepareDataForScript() throws Throwable {
		ACECheckAPIDataValidation.prepareDataForScript();
	}

}
