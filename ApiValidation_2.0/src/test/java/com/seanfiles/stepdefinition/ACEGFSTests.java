package com.seanfiles.stepdefinition;

import java.util.Map;

import com.seanfiles.db.GFSDB;
import com.seanfiles.helper.ACEAPI20;
import com.seanfiles.helper.ACEGFSDataValidation;
import com.seanfiles.helper.ACEGFSGET;
import com.seanfiles.helper.ACEGFSPOST;
import com.seanfiles.helper.TestScenario;
import com.seanfiles.services.GFSGETRequestData;
import com.seanfiles.services.GFSGETResponseData;
import com.seanfiles.services.GFSPOSTRequestData;
import com.seanfiles.utils.ACEGFSGherkinElements;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ACEGFSTests {
	@Before
	public void loadTestScenario(Scenario s) {
		TestScenario.setScenario(s);
	}

	@Given("^Remove \"GFS\" MongoDB documents with the following values$")
	public void removeGFSDBDocs(DataTable dataTable) throws Throwable {
		GFSDB.removeDocs(dataTable.asMaps(String.class, String.class));
	}

	@Given("^User submits GFS POST request with the following values$")
	public void submitGFSPOSTRequest(DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACEGFSPOST.submitPOSTRequest(ACEGFSGherkinElements.updateGherkinElementNames(dataMap));
	}

	@Given("^User submits GFS POST request \"([^\"]*)\" with the following values$")
	public void submitGFSPOSTRequest(String postJSONFile, DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACEGFSPOST.submitPOSTRequest(postJSONFile, ACEGFSGherkinElements.updateGherkinElementNames(dataMap));
	}

	@Given("^User submits GFS GET request with the following values$")
	public void submitGFSGETRequest(DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACEGFSGET.submitGETRequest(ACEGFSGherkinElements.updateGherkinElementNames(dataMap));
	}

	@Given("^User gets GFS GET Response with the following values$")
	public void verifyGFSGETResponse(DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		ACEGFSDataValidation.verifyGETResponse(ACEGFSGherkinElements.updateGherkinElementNames(dataMap));
	}
	
	@Given("^User retrieves \"([^\"]*)\" from GFS Database$")
	public void retrieveGFSDBDoc(String type, DataTable dataTable) throws Throwable {
		Map<String, String> dataMap=dataTable.asMaps(String.class,String.class).get(0);
		GFSDB.getLatestDocs(type, dataMap);
	}
	
	@Then("^User retrieves the corresponding ACEAPI2.0 \"([^\"]*)\" from GFS Database$")
	public void retrieveCorrespondingGFSDBDoc(String type) throws Throwable {
		GFSDB.getCorrespondingACEAPI20GFSDocs(type);
	}
	
	@Then("^User verifies GFS \"([^\"]*)\" \"([^\"]*)\" data matches \"([^\"]*)\" \"([^\"]*)\" data$")
	public void verifyGFSData(String src, String srcCont, String dest, String destCont) throws Throwable {
		ACEGFSDataValidation.verifyGFSData(src, srcCont, dest, destCont);
	}
	
	@After
	public void cleanupGFSDB() throws Throwable {
		ACEGFSPOST.removeDateModifiedDocs();
		ACEGFSPOST.clearData();
	}
	
	
}
