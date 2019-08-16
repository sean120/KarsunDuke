package com.karsun.duke.pages;
//package com.karsun.duke.pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.FindBy;
//import org.springframework.stereotype.Component;
//
//import com.karsun.kic.tan.duke.Page;
//@Component

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

import com.karsun.duke.utils.LoadProperties;
import com.karsun.kic.tan.duke.Page;

@Component
public class AmazonSearchPage  extends Page{


	
	
	
	WebDriver driver = getExecutionContext().getDriver();
	@Override
	protected boolean isLoaded() {
	Actions actions = new Actions(driver);
	WebElement mainMenu = 
	driver.findElement(By.id("nav-link-accountList"));
	actions.moveToElement(mainMenu);
	actions.build().perform();

	WebElement subMenu = driver.findElement(By.cssSelector("span.nav-action-inner"));
	
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
	
	
}
	
	



