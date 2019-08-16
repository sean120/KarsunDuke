package com.karsun.duke.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karsun.duke.pages.ActitimeTrackPage;
import com.karsun.kic.tan.duke.Steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Component
public class ActitimeTrackSteps extends Steps {
	private static final Logger log = LoggerFactory.getLogger(ActitimeTrackSteps.class);
	@Autowired
	ActitimeTrackPage actitimeTrackPage;

	@Given("^user is on the Time Track Page$")
	public void user_is_on_the_Time_Track_Page() {
		actitimeTrackPage.getTimeTrackPage();
	}

	@When("^user enters task details$")
	public void user_enters_task_details() {
		actitimeTrackPage.enterTaskName();
		log.info("User successfully entered data on task field");
	}

	@When("^click on the save changes btn$")
	public void click_on_the_save_changes_btn() {
		actitimeTrackPage.submitBTN();
		log.info("User successfully clicks on submit button");
	}

	@Then("^the total working time should display correctly$")
	public void the_total_working_time_should_display_correctly() {
		actitimeTrackPage.dayTotal();
		log.info("Total work time is displayed");
	}
}
