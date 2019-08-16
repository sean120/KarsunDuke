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
public class ActitimePracticePage extends Page {
	private static final int TIME_OUT_SECONDS = 5;

	private static final By PAGE_TITLE = By.id("whiteBoxWithLogo");
	private static final By USERNAME_TXTBOX = By.id("username");
	private static final By PASSWORD_TXTBOX = By.name("pwd");
	private static final By LOG_IN_BTN = By.id("loginButton");
	private static final By ARTI_TRACK = By.id("enterTTMainContent");
	
	private static final Logger log = LoggerFactory.getLogger(ActitimePracticePage.class);
	@Override
	protected boolean isLoaded() {
		return ActionByLocator.isDisplayed(getDriver(), PAGE_TITLE, TIME_OUT_SECONDS);
	}

	@Override
	protected void load() {
		getDriver().get(LoadProperties.getProperty("acti.url"));
		Assert.assertTrue("Could not load ActiTime page", isLoaded());
	}

	public String getPageTitle() {
		return getDriver().getTitle();
	}

	public void getLoginPage() {
		get();
		String expectedPageTitle = getExecutionContext().getCurrentScenarioObj().get("actiTitle").getAsString();
		String actualPageTitle = getPageTitle();
		System.out.println(actualPageTitle);
		Assert.assertEquals("actiTIME Login Page", expectedPageTitle, actualPageTitle);
	}
		
	public void enterUserName() {
		ActionByLocator.sendKeys(getDriver(), USERNAME_TXTBOX,
				getExecutionContext().getCurrentScenarioObj().get("username").getAsString(), TIME_OUT_SECONDS);
		log.info("User successfully entered Username");
	}

	public void enterPassword() {
		ActionByLocator.sendKeys(getDriver(), PASSWORD_TXTBOX,
				getExecutionContext().getCurrentScenarioObj().get("password").getAsString(), TIME_OUT_SECONDS);
		log.info("User successfully entered Password");
	}

	public void clickLogInbtn() {
		ActionByLocator.click(getDriver(), LOG_IN_BTN, TIME_OUT_SECONDS);
		log.info("User successfully clicked login button");
	}

	public boolean isPageDisplayed() {
		ActionByLocator.isVisible(getDriver(), ARTI_TRACK, TIME_OUT_SECONDS);
		String actualTitle = getPageTitle();
		System.out.println(actualTitle);
		Assert.assertTrue(actualTitle, true);
		log.info("Arti Track page is displayed");
		return true;
	}

}
