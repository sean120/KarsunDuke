package com.seanfiles.stepdefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import com.seanfiles.helper.ACEAPIHelperMethods;
import com.seanfiles.root.LoadTestConfig;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.specification.RequestSpecification;

public class ACEAPICardinalityCheck {

	private static Logger log = Logger.getLogger(ACEAPICardinalityCheck.class);
	public Scenario scenario;
	public Properties props;
	//private String filename;
	private Boolean fileExist;
	private DataTable datatab;
	public ACEAPIHelperMethods aceapimethods;
	public LoadTestConfig aceapiconfig;
	protected RequestSpecification reqspec;

	// ================================Framework_SETUP================================\\

	@Before
	public void loadTestScenario(Scenario scenario) {
		this.scenario = scenario;
		this.aceapiconfig = new LoadTestConfig();
		this.props = aceapiconfig.loadProperties();
		this.aceapimethods = new ACEAPIHelperMethods();
	}

	// @After
	// public void Teardown() {
	// try {
	// String htmlreport = System.getProperty("user.dir")
	// .concat("\\target\\cucumber-report-html\\cucumber-html-reports\\feature-overview.html");
	// Desktop.getDesktop().browse(new URL(htmlreport).toURI());
	// } catch (Exception ex) {
	// log.error(ex.getMessage());
	// }
	// }

	// ================================Framework_SETUP================================\\

	/**
	 * Verify the ACE API JSON request file present
	 * 
	 * 
	 * @param String
	 *            aceapirequestfilename
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws IOException
	 */

	@Given("^JSON file \"([^\"]*)\" is present$")
	public void aceapirequest(String aceapirequestfilename) throws IOException {

		log.info("*********** in GIVEN  ---   Verifing the Request Json File is present  *****************");
		//this.filename = aceapirequestfilename;
		fileExist = aceapimethods
				.isValidPath(System.getProperty("user.dir").concat(props.getProperty("aceapirequestfile")));
		log.info("Json Request file present status is :" + fileExist);
		scenario.write("Json Request file present status is :" + fileExist);
		Assert.assertTrue("Json Request file not present", fileExist);
	}
	
	/**
	 * Verify cardinality of containers in the ACE API JSON request file
	 * 
	 * 
	 * @param String aceapirequestfilename, DataTable elementnvalue
	 *            
	 * 
	 * 
	 * @author C47174 - COGNIZANT - LAS - ACE API
	 * @throws IOException, ParseException
	 */

	@Then("^Check for containers for cardinality in \"([^\"]*)\"$")
	public void aceApiContainersCheck(String aceapirequestfilename, DataTable elementnvalue)
			throws IOException, ParseException, java.text.ParseException {
		this.datatab = elementnvalue;
		//this.filename = aceapirequestfilename;
		int elementCount;
		String filepath = System.getProperty("user.dir").concat(props.getProperty("aceapirequestfile"));
		List<Map<String, String>> data = datatab.asMaps(String.class, String.class);
		for (int i = 0; i < data.size(); i++) {
			elementCount = 0;
			if (data.get(i).get("Element").contains(" or ")) {
				String elementArr[] = data.get(i).get("Element").split(" or ");
				for (int j = 0; j < elementArr.length; j++) {
					elementCount += aceapimethods.getElementCountinJson(filepath, elementArr[j]);
				}
			} else {
				elementCount = aceapimethods.getElementCountinJson(filepath, data.get(i).get("Element"));
			}
			log.info("The count of Element '" + data.get(i).get("Element") + "' is :" + elementCount);
			scenario.write("Json Request file present status is :" + fileExist);
			Assert.assertEquals(1, elementCount);
		}
	}

}
