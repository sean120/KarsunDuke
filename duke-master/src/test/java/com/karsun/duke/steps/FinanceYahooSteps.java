package com.karsun.duke.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.karsun.duke.pages.YahooHomePage;
import com.karsun.kic.tan.duke.Steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


@Component
public class FinanceYahooSteps  extends Steps{
	@Autowired
	YahooHomePage finance;
	
	@Given("^user is finance.yahoo.com$")
	public void getThePage(){
		
		
	}
	
	@When("^user checks Dowjones value for theday$")
	public void pullsDowPoints(){
		
	}
	@And("^user checks enters fb in to pricecheck$")
	public void getFbvalue(){
		
	}
	
	@Then("^user gets the price of the stock and print out value$")
	public void getsthePriceandPrints(){
		
	}
	
	 
	
}
