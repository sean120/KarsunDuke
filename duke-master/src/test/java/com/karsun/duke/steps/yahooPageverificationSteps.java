package com.karsun.duke.steps;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karsun.duke.pages.YahooHomePage;
import com.karsun.kic.tan.duke.Steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

@Component
public class yahooPageverificationSteps  extends Steps{
@Autowired
YahooHomePage homePage;
	
@Given("^user is on homepage$")
public void user_is_on_homepage(){
    	homePage.get();
    	String expectedPageTitle = executionContext.getCurrentScenarioObj().get("pageTitle").getAsString();
	    String actualPageTitle = homePage.getPageTitle();
	    Assert.assertEquals("Page title", expectedPageTitle, actualPageTitle);
    	
  
}

@Given("^user navigates to \"([^\"]*)\" and verify the page$") 
public void user_navigates_to_finance(String topic)  {
	homePage.get();
	homePage.navigateTo(topic);	
//	String expectedPageTitle = executionContext.getCurrentScenarioObj().get("pageTitle").getAsString();
//    String actualPageTitle = homePage.getPageTitle();
//    Assert.assertEquals("Page title", expectedPageTitle, actualPageTitle);
//	
}

@Given("^user navigates to \"([^\"]*)\" and verify the page$")
public void user_navigates_(String topic1) {
    homePage.navigateTo("topic1");
    
 
}


@Given("^user navigates to \"([^\"]*)\" and verify the page$")
public void user_navigates_to_(String topic2) {
    // Write code here that turns the phrase above into concrete actions
	homePage.navigateTo(topic2);
  
}

@Then("^user closes the browser$")
public void user_closes_the_browser() throws Throwable {
    homePage.Teardown();
    throw new PendingException();
}

}
