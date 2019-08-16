package com.karsun.duke.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.karsun.duke.utils.LoadProperties;
import com.karsun.kic.tan.duke.Page;
import com.karsun.kic.tan.duke.util.ActionByLocator;

@Component
public class UltimateQaPage extends Page {
	private static final int TIME_OUT_SECONDS = 5;

	private static final By USERNAME_TXTBOX = By.id("user_email");
	private static final By PASSWORD_TXTBOX = By.id("user_password");
	private static final By SIGN_IN_BTN = By.id("btn-signin");
	private static final By ERROR_MSG = By.id("notifications-error");

	@Override
	protected boolean isLoaded() {
		return ActionByLocator.isDisplayed(getDriver(), USERNAME_TXTBOX, TIME_OUT_SECONDS);
	}

	@Override
	protected void load() {
		getDriver().get(LoadProperties.getProperty("dev.url"));
		Assert.assertTrue("Could not load Ultimate page", isLoaded());
	}

	public String getPageTitle() {
		return getDriver().getTitle();
	}

	public void enterUserName() {
		ActionByLocator.sendKeys(getDriver(), USERNAME_TXTBOX,
				getExecutionContext().getCurrentScenarioObj().get("username").getAsString(), TIME_OUT_SECONDS);
	}

	public void enterPassword() {
		ActionByLocator.sendKeys(getDriver(), PASSWORD_TXTBOX,
				getExecutionContext().getCurrentScenarioObj().get("password").getAsString(), TIME_OUT_SECONDS);
	}

	public void submitButton() {
		ActionByLocator.click(getDriver(), SIGN_IN_BTN, TIME_OUT_SECONDS);
	}

	public void errorMessage() {
		Assert.assertEquals("Invalid Login Details", "Invalid Email or Password",
				ActionByLocator.getText(getDriver(), ERROR_MSG, TIME_OUT_SECONDS));

	}

}
