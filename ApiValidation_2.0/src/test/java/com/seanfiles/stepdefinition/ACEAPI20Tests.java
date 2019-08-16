package com.seanfiles.stepdefinition;

import java.util.List;
import java.util.Map;

import com.seanfiles.db.ACEAPI20DB;
import com.seanfiles.helper.ACEAPI20;
import com.seanfiles.helper.ACEAPI20DataValidation;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.services.ACEAPI20DerivedData;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ACEAPI20Tests {
	@Before
	public void loadTestScenario(Scenario s) {
		TestScenario.setScenario(s);
	}

	@Given("^User pauses execution for (\\d+) seconds$")
	public void pauseExecution(int seconds) throws Throwable {
		TestScenario.writeToScenario("Paused execution for "+seconds+" seconds");
		Thread.sleep(seconds*1000);
	}
	
	@Given("^User submits ACEAPI2.0 request \"([^\"]*)\"$")
	public void submitACEAPIRequest(String JSONFileName, DataTable dataTable) throws Throwable {
		ACEAPI20.setACEAPI20RequestFile(JSONFileName);
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACEAPI20.updateACEAPI20Request(dataMap);
		ACEAPI20.submitACEAPI20();
		ACEAPI20.getACEAPI20Response();
	}

	@Given("^User submits ACEAPI2.0 request \"([^\"]*)\" with below changes$")
	public void submitACEAPIRequest2(String JSONFileName, DataTable dataTable) throws Throwable {
		ACEAPI20.setACEAPI20RequestFile(JSONFileName);
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACEAPI20.updateACEAPI20Request(dataMap);
		ACEAPI20.submitACEAPI20();
	}

	@Given("^ACEAPI2.0 request payload in the file \"([^\"]*)\"$")
	public void setACEAPIRequest(String JSONFileName) throws Throwable {
		ACEAPI20.setACEAPI20RequestFile(JSONFileName);
	}

	@Given("^User submits ACEAPI2.0 request in the file \"([^\"]*)\"$")
	public void submitACEAPIRequestFile(String JSONFileName) throws Throwable {
		ACEAPI20.setACEAPI20RequestFile(JSONFileName);
		ACEAPI20.submitACEAPI20();
	}

	@Given("^The Address in the ACEAPI2.0 request payload updated as below$")
	public void updateACEAPIRequest(DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACEAPI20.updateACEAPI20Request(dataMap);
	}

	@Given("^Update the ACEAPI2.0 request payload using the below data$")
	public void updateACEAPIRequestData(DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACEAPI20.updateACEAPI20Request(dataMap);
	}

	@Given("^Update the ACEAPI2.0 request payload using the below Excel data$")
	public void updateACEAPIRequestWithExcelData(DataTable dataTable) throws Throwable {
		Map<String, String> excelDataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACEAPI20.updateACEAPI20RequestWithExcelData(excelDataMap);
	}

	@When("^User submits ACEAPI2.0 request$")
	public void submitACEAPIRequest() throws Throwable {
		ACEAPI20.submitACEAPI20();
	}

	@Then("^User gets ACEAPI2.0 response$")
	public void getACEAPIResponse() throws Throwable {
		ACEAPI20.getACEAPI20Response();
	}

	@Then("^ACEAPI2.0 returns HTTP status code (\\d+)$")
	public void checkACEAPIReturnCode(int expectedReturnCode) throws Throwable {
		ACEAPI20.checkACEAPIReturnCode(expectedReturnCode);
	}

	@Then("^User verifies the ACEAPI2.0 response values$")
	public void getAndVerifyACEAPIResponse(DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACEAPI20DataValidation.verifyACEAPIResponse(dataMap);
	}

	@Then("^User verifies the \"([^\"]*)\" values$")
	public void verifyData(String type, DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACEAPI20DataValidation.verifyAARequestData(dataMap);
	}

	@Then("^User verifies the \"([^\"]*)\" \"([^\"]*)\" values$")
	public void verifyData(String type, String item, DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACEAPI20DataValidation.verifyAARequestCommunicationFailureData(dataMap);
	}

	@Given("^The ACEAPI2.0 RequestID is \"([^\"]*)\"$")
	public void setACEAPI20RequestID(String ACEAPI20RequestID) throws Throwable {
		ACEAPI20.clearServiceData();
		ACEAPI20DerivedData.getNewACEAPI20DerivedData(ACEAPI20RequestID);
	}
	
	@Then("^User retrieves all the corresponding Documents from ACEAPI2.0 Database$")
	public void getCorrespondingACEAPIDocs() throws Throwable {
		ACEAPI20DB.getCorrespondingDocs();
	}

	
	@Then("^User retrieves the corresponding \"([^\"]*)\" from ACEAPI2.0 Database$")
	public void getCorrespondingACEAPIDoc(String type) throws Throwable {
		ACEAPI20DB.getCorrespondingDoc(type);
	}

	@Given("^User creates the \"([^\"]*)\" using the ACEAPI2.0 DB document$")
	public void createACEAPI20FromDBDoc(String type) throws Throwable {
		ACEAPI20.createFromDBDoc(type);
	}

	@Then("^User verifies the \"([^\"]*)\" retrieved from ACEAPI2.0 Database$")
	public void verifyACEAPI20Doc(String type) throws Throwable {
		ACEAPI20DataValidation.verifyDBDoc(type);
	}

	@Then("User verifies there is \"([^\"]*)\" in the \"([^\"]*)\"$")
	public void verifyDataPresence(String expected, String type) throws Throwable {
		ACEAPI20DataValidation.verifyDataPresence(expected, type);
	}

	@Then("^User verifies \"([^\"]*)\" data elements are correctly mapped to \"([^\"]*)\"$")
	public void verifyACEAPI20DataElements(String src, String tgt) throws Throwable {
		ACEAPI20DataValidation.verifyDataElements(src, tgt);
	}

	@Then("^User verifies \"([^\"]*)\" data in ACEAPI2.0 \"([^\"]*)\"$")
	public void verifyServiceDataGroup(String dataGroup, String tgt) throws Throwable {
		ACEAPI20DataValidation.verifyServiceDataGroup(tgt, dataGroup);
	}
	
	@Then("^User verifies \"([^\"]*)\" data is copied as \"([^\"]*)\" data$")
	public void compareData(String src, String tgt) throws Throwable {
		ACEAPI20DataValidation.verifyData(src, tgt);
	}
	
	@Then("^User verifies \"([^\"]*)\" data copied to ACEAPI2.0 response$")
	public void verifyAAResponse(String src) throws Throwable {
		ACEAPI20DataValidation.verifyAAResponse();
	}
	
	@Then("^User retrieves all ACEAPI20 DocIDs having the element \"([^\"]*)\" value starts with \"([^\"]*)\"$")
	public void listDocsIDs(String elementName, String elementValueStartsWith) throws Throwable {
		ACEAPI20DB.listAllACE20RequestDocIDsByElementValueStartsWith(elementName, elementValueStartsWith);
	}
	
	@Then("^User verifies the ACEAPI2.0 \"([^\"]*)\" data contains the following values$")
	public void verifyServiceData(String type, DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACEAPI20DataValidation.verifyServiceData(type, dataMap);
	}	
	
	@Then("^User verifies the following data elements from ACEAPI2.0 \"([^\"]*)\" data are mapped to \"([^\"]*)\"$")
	public void verifyServiceData(String src, String tgt, DataTable dataTable) throws Throwable {
		List<String> elementsList = dataTable.asList(String.class);
		ACEAPI20DataValidation.compareServiceData(src, tgt, elementsList);
	}
	
	@Then("^User verifies all the data elements from ACEAPI2.0 \"([^\"]*)\" data are mapped to \"([^\"]*)\"$")
	public void compareServiceData(String src, String tgt) throws Throwable {
		ACEAPI20DataValidation.compareServiceData(src, tgt, null);
	}

	@Then("^User verifies the following data elements from ACEAPI2.0 \"([^\"]*)\" data are mapped to \"([^\"]*)\" \"([^\"]*)\"$")
	public void verifyServiceDataWithContName(String src, String tgt, String tgtContName, DataTable dataTable) throws Throwable {
		List<String> elementsList = dataTable.asList(String.class);
		ACEAPI20DataValidation.compareServiceDataWithContName(src, tgt, tgtContName, elementsList);
	}
	
	@Then("^User verifies all the data elements from ACEAPI2.0 \"([^\"]*)\" data are mapped to \"([^\"]*)\" \"([^\"]*)\"$")
	public void compareServiceDataWithContName(String src, String tgt, String tgtContName) throws Throwable {
		ACEAPI20DataValidation.compareServiceDataWithContName(src, tgt, tgtContName, null);
	}
	
	@Then("^User verifies the following data elements from ACEAPI2.0 \"([^\"]*)\" \"([^\"]*)\" data are mapped to \"([^\"]*)\" \"([^\"]*)\"$")
	public void verifyServiceDataWithBothContName(String src, String srcContName, String tgt, String tgtContName, DataTable dataTable) throws Throwable {
		List<String> elementsList = dataTable.asList(String.class);
		ACEAPI20DataValidation.compareServiceDataWithBothContName(src, srcContName, tgt, tgtContName, elementsList);
	}
	
	@Then("^User verifies all the data elements from ACEAPI2.0 \"([^\"]*)\" \"([^\"]*)\" data are mapped to \"([^\"]*)\" \"([^\"]*)\"$")
	public void compareServiceDataWithBothContName(String src, String srcContName, String tgt, String tgtContName) throws Throwable {
		ACEAPI20DataValidation.compareServiceDataWithBothContName(src, srcContName, tgt, tgtContName, null);
	}
}
