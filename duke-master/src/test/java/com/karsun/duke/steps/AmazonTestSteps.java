package com.karsun.duke.steps;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karsun.duke.pages.AmazonWebPage;
import com.karsun.kic.tan.duke.Steps;
import com.karsun.kic.tan.duke.util.ActionAnglrByCss;
import com.karsun.kic.tan.duke.util.ActionAnglrById;
import com.karsun.kic.tan.duke.util.ActionAnglrByLocator;
import com.karsun.kic.tan.duke.util.ActionByJavaScript;
import com.karsun.kic.tan.duke.util.ActionByLocator;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Component
public class AmazonTestSteps extends Steps {
	
	@Autowired
	AmazonWebPage amzn;
	
	@Given("^user is on amazon webpage and click signon button$")
	public void user_is_on_amazon_webpage_and_click_signon_button(){
		amzn.get();
		String expectedPageTitle = executionContext.getCurrentScenarioObj().get("pageTitle").getAsString();
		String actualPageTitle = amzn.getPageTitle();
	Assert.assertEquals("Page title", expectedPageTitle, actualPageTitle);
	}

	@When("^user sign username and password$")
	public void user_sign_username_and_password(){
		amzn.clickSingin();
	
	
	}

//	@Then("^user validates the success$")
//	public void user_validates_the_success(String topic) {
//	   Assert.assertTrue("displayed: "+ topic, singedConfirmation.isSignedDisplayed(topic));
//	}
	
	

	@When("^user find my order history and clic$")
	public void user_find_my_order_history_and_clic() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^print out list of order history into report$")
	public void print_out_list_of_order_history_into_report() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	
	
//	@When("^I select the Documentation tab$")
//	public void i_select_the_Documentation_tab() {
//		amzn.navigateTo("Documentation");
//	}

//	@When("^I select the topic \"([^\"]*)\"$")
//	public void i_select_the_topic(String topic) {
//		amzn.get();
//		amzn.navigateTo(topic);
//	}

		
	
}

