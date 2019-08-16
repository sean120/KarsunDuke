package com.karsun.duke.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karsun.duke.pages.ActitimePracticePage;
import com.karsun.kic.tan.duke.Steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Component
public class ActitimePracticeSteps extends Steps {
	private static final Logger log = LoggerFactory.getLogger(ActitimePracticeSteps.class);
	@Autowired
	ActitimePracticePage actitimePracticePage;

	@Given("^user is on the log in page$")
	public void user_is_on_the_log_in_page() {
		actitimePracticePage.getLoginPage();
	}


	@When("^user enters valid login credentials$")
	public void user_enters_valid_login_credentials() {
		actitimePracticePage.enterUserName();
		log.info("User successfully entered Username");
		actitimePracticePage.enterPassword();
		log.info("User successfully entered Password");
	}

	@When("^click on log in button$")
	public void click_on_log_in_button() {
		actitimePracticePage.clickLogInbtn();
		log.info("User successfully clicked login button");
	}

	@Then("^time track page should be displayed$")
	public void time_track_page_should_be_displayed() {
		actitimePracticePage.isPageDisplayed();
		log.info("Arti Track page is displayed");
	}

}
