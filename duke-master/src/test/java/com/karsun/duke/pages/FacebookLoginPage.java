package com.karsun.duke.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.karsun.duke.utils.LoadProperties;
import com.karsun.kic.tan.duke.Page;
import com.karsun.kic.tan.duke.util.ActionByLocator;

@Component
public abstract class FacebookLoginPage extends Page {
	
		//private static final String loggeriD = "Sean Middleton";
		private static final int TIME_OUT_SECONDS = 5;

		private static final By PAGE_TITLE = By.xpath("//*[@id='blueBarDOMInspector']/div/div/div/div[1]/h1/a");
		private static final By USERNAME_TXTBOX = By.id("email");
		private static final By PASSWORD_TXTBOX = By.id("pass");
		private static final By LOG_IN_BTN = By.id("u_0_3");
		 
		private static final By LOG_IN_Succesfull = By.xpath("//*[text()='Sean Middleton']");
		
		
		//private static final Logger log = LoggerFactory.getLogger(FacebookLoginPage.class);
		
		@Override
		protected boolean isLoaded() {
			return ActionByLocator.isDisplayed(getDriver(), PAGE_TITLE, TIME_OUT_SECONDS);
		}

		@Override
		protected void load() {
			getDriver().get(LoadProperties.getProperty("face.url"));
			Assert.assertTrue("Could not load facebook page", isLoaded());
		}

		public String getPageTitle() {
			return getDriver().getTitle();
		}

		public void getLoginPage() {
			get();
			String expectedPageTitle = getExecutionContext().getCurrentScenarioObj().get("facebookTitle").getAsString();
			String actualPageTitle = getPageTitle();
			System.out.println(actualPageTitle);
			Assert.assertEquals("facebook Login Page", expectedPageTitle, actualPageTitle);
		}
			
		public void enterUserName() {
			String username= "smiddleton393@gmail.com";
			
					
			ActionByLocator.sendKeys(getDriver(), USERNAME_TXTBOX, username, 5);
				//	getExecutionContext().getCurrentScenarioObj().get("user1").getAsString(), TIME_OUT_SECONDS);
			//log.info("User successfully entered Username");
		}

	
		public void enterPassword() {
			String password = "c2hhdW5ndWxqYQ==";
			ActionByLocator.sendKeys(getDriver(), PASSWORD_TXTBOX,password, 5);
					//getExecutionContext().getCurrentScenarioObj().get("pass1").getAsString(), TIME_OUT_SECONDS);
			//log.info("User successfully entered Password");
		}
		
				
		public void clickLogInbtn() {
			ActionByLocator.click(getDriver(), LOG_IN_BTN, TIME_OUT_SECONDS);
			//log.info("User successfully clicked login button");
		}

		public boolean isPageDisplayed() {
			
			ActionByLocator.isDisplayed(getDriver(), LOG_IN_Succesfull, TIME_OUT_SECONDS);
			String actualTitle = getPageTitle();
			System.out.println(actualTitle);
			Assert.assertTrue(actualTitle, true);
			//log.info("logged in facebook successfully");
			return true;
		}
		

//		public void navigateTo(String tabName) {
//			switch (tabName) {
//			case "homepage":
//				ActionByLocator.click(getDriver(), PAGE_TITLE, TIME_OUT_SECONDS);
//				break;
//			case "Support":
//				ActionByLocator.click(getDriver(), SUPPORT_LINK, TIME_OUT_SECONDS);
//				break;
//			default:
//				throw new RuntimeException("Unknown tab: " + tabName);
//			}
//		}

	}


