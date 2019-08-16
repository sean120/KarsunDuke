package com.karsun.duke.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karsun.duke.pages.ActiviewTimePage;
import com.karsun.kic.tan.duke.ExecutionContext;
import com.karsun.kic.tan.duke.Steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Component
public class ActiviewTimeSteps extends Steps {
	@Autowired
	ActiviewTimePage activiewTimePage;

	@Given("^user is on the View Time-Track Page$")
	public void user_is_on_the_View_Time_Track_Page() {
		activiewTimePage.getactiViewPage();
		//executionContext.getDriver().get(url);
	
		
	}

	@When("^user selects current month type$")
	public void user_selects_current_month_type() {
		activiewTimePage.selectMonth();
	}

	@When("^clicks on the month type$")
	public void clicks_on_the_month_type() {
		activiewTimePage.clickMonthType();
	}

	@Then("^user should be able to see the details$")
	public void user_should_be_able_to_see_the_details() {
		activiewTimePage.detailDisplayed();
	}
}
