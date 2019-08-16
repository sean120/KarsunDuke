package com.karsun.duke.steps;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karsun.duke.pages.UltimateQaPage;
import com.karsun.kic.tan.duke.Steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Component
public class ultimateqasteps extends Steps {
	@Autowired
	UltimateQaPage uqPage;

	@Given("^User is on the log in page$")
	public void user_is_on_the_log_in_page() {
		uqPage.get();
		String expectedPageTitle = executionContext.getCurrentScenarioObj().get("pageTitle1").getAsString();
		String actualPageTitle = uqPage.getPageTitle();
		System.out.println(actualPageTitle);
		Assert.assertEquals("Ultimate HQ Page", expectedPageTitle, actualPageTitle);

	}

	@When("^User enters wrong username and password$")
	public void user_enters_wrong_username_and_password() {
		uqPage.enterUserName();
		uqPage.enterPassword();
	}

	@When("^Click on the sign in button$")
	public void click_on_the_sign_in_button() {
		uqPage.submitButton();
	}

	@Then("^User should see error message$")
	public void user_should_see_error_message() {
		uqPage.errorMessage();

	}

}
