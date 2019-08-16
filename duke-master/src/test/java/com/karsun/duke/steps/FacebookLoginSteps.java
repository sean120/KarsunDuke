package com.karsun.duke.steps;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karsun.duke.pages.FacebookLoginPage;
import com.karsun.kic.tan.duke.Steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Component
public abstract class FacebookLoginSteps extends Steps {
	
	@Autowired
	FacebookLoginPage loginpage; 
	
	WebDriver driver = executionContext.getDriver();
	

	 @Given("^user navigate to face book page loginPage$")
	 public void user_is_on_loginpage(){
		 
		 driver.get("https://www.facebook.com");
		
		 System.out.println("succesffully loggedin!");
		 
	 }
	 
@When("user enters userName")
public void user_enters_username(){
		
	  }
	 @And("user enters passWord")
	 public void user_enters_password(){
		 loginpage.enterPassword();
		 
	 }
	  @And("user clicks on login")
	  public void user_clicks_login(){
		  
		  loginpage.clickLogInbtn();
	  }
	  @Then("user should be abble to see the successful login")
	  public void user_to_see_succesfullLogin(){
		  loginpage.isPageDisplayed();
		  
		  
	  }
	
	  
	  @When("^the user enters set of usernames and password$")
	  public void user_enters_credentials(DataTable credentials){
		  //extract data into map then we will iterate through map
		  for(Map<String, String> data : credentials.asMaps(String.class, String.class)){
			  //parse map into local veriable
			  
			  String username = data.get("username");
			  String password = data.get("password");
			 
			  driver.get("https://www.facebook.com");
			 String a = driver.getCurrentUrl();
			 System.out.println(a);
			  driver.findElement(By.id("email")).clear();
			  driver.findElement(By.id("email")).sendKeys(username);
			  driver.findElement(By.id("pass")).clear();
			  driver.findElement(By.id("pass")).sendKeys(password);;
			  driver.findElement(By.id("u_0_3")).click();
			
			
		  }
		  
		
		 
		  
	  }
	
	 
		   
	
	  	

	
	
	
	  
	  
	  
	 
	    
	
	  
	  		

	  	

}
