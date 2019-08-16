package com.seanfiles.stepdefinition;

import com.seanfiles.helper.DataFixInputData;

import cucumber.api.java.en.Then;


public class DataFixStepDef {

	@Then("^User verifies the GFS POST data for LPA/ULAD transactions grandfathered this ACEAPI transaction data$")
	public void verifyGFSPOSTData() throws Throwable {
		DataFixInputData.assertStatus=true;
		DataFixInputData.prepareDataFor1bScript();
	}

	@Then("^User retrieves the GFS POST data for LPA/ULAD transactions grandfathered this ACEAPI transaction data$")
	public void retrieveGFSPOSTData() throws Throwable {
		DataFixInputData.prepareDataFor1bScript();
	}

	
}
