package com.karsun.duke.steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karsun.duke.pages.DocumentationPage;
import com.karsun.duke.pages.SeleniumHQPage;
import com.karsun.kic.tan.duke.Steps;
import com.karsun.kic.tan.duke.util.ActionAnglrByCss;
import com.karsun.kic.tan.duke.util.ActionAnglrById;
import com.karsun.kic.tan.duke.util.ActionAnglrByLocator;
import com.karsun.kic.tan.duke.util.ActionByJavaScript;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Component
public class SeleniumHQSteps extends Steps {
	@Autowired 
	SeleniumHQPage seHQPage;
	@Autowired
	 DocumentationPage documentationPage;
	
		
	@Given("^I am on the Selenium HQ page$")
	public void i_am_on_the_Selenium_HQ_page() {
		seHQPage.get();
		String expectedPageTitle = executionContext.getCurrentScenarioObj().get("pageTitle").getAsString();
	    String actualPageTitle = seHQPage.getPageTitle();
	    Assert.assertEquals("Page title", expectedPageTitle, actualPageTitle);
	}

	@When("^I select the Documentation tab$")
	public void i_select_the_Documentation_tab() {
		seHQPage.navigateTo("Documentation");
	}

	@When("^I select the topic \"([^\"]*)\"$")
	public void i_select_the_topic(String topic) {
		documentationPage.get();
		documentationPage.navigateTo(topic);
	}

	@Then("^the \"([^\"]*)\" subtopic is displayed$")
	public void the_subtopic_is_displayed(String subtopic) {
		Assert.assertTrue("Sub-topic was not displayed: " + subtopic, documentationPage.isSubTopicDisplayed(subtopic));
	}
	
	private void test() {
		// Just to show some additional helpers built into Duke
		WebDriver driver = executionContext.getDriver();
		
		ActionAnglrByCss.assertEqualsText(driver,"form small","",30);
		ActionAnglrByCss.assertTextContainsTrue(driver,".summary-remarks-attachments label","",30);
		ActionAnglrByCss.click(driver,"[aria-label=\"Refresh\"]",30);
		
		ActionAnglrByCss.getTextContainsToVerify(driver, "[id=\"profile\"]", "someString".toUpperCase(), 5);
		
		ActionAnglrByCss.isDisplayed(driver,"[title='"+""+"']",30);
		ActionAnglrByCss.sendKeys(driver,"[placeholder=\"Rejection comments\"]","",30);
		
		ActionAnglrById.clear(driver,"rate",0,30);
		ActionAnglrById.click(driver, "logout", 10);
		
		ActionAnglrByLocator.click(driver,By.xpath(""),30);
		ActionAnglrByLocator.sendKeys(driver, By.className(""), "", 5);

		ActionByJavaScript.scrollIntoView(driver, ActionAnglrByCss.getElement(driver,"[title='"+""+"']",30));

	}
}
