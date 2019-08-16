package com.karsun.duke.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.karsun.duke.utils.LoadProperties;
import com.karsun.kic.tan.duke.Page;
import com.karsun.kic.tan.duke.util.ActionByJavaScript;
import com.karsun.kic.tan.duke.util.ActionByLocator;

@Component
public class ActiviewTimePage extends Page {
	private static final int TIME_OUT_SECONDS = 5;
	private static final By ACTI_VIEW = By.id("viewTTContent");
	private static final By SELECT_MONTH = By.xpath("//table[@id='ext-comp-1003']");
	private static final By SELECT_LIST_ITEM = By.xpath("//a[@id='ext-gen88']//span[@class='rangeItem']//div[contains(text(),'Previous month')]");
	private static final By TOTAL_TIME = By.id("totalsForPeriodLabel");
	private static final By LOGOUT_LINK = By.id("logoutLink");

	@Override
	protected boolean isLoaded() {
		return ActionByLocator.isDisplayed(getDriver(), ACTI_VIEW, TIME_OUT_SECONDS);
	}

	@Override
	protected void load() {
		getDriver().get(LoadProperties.getProperty("actiview.url"));
		Assert.assertTrue("Could not load ActiTime View page", isLoaded());
	}

	public String getPageTitle() {
		return getDriver().getTitle();
	}

	public void getactiViewPage() {
		get();
		String expectedPageTitle1 = getExecutionContext().getCurrentScenarioObj().get("actiViewTitle").getAsString();
		String actualPageTitle1 = getPageTitle();
		System.out.println(actualPageTitle1);
		Assert.assertEquals("actiTIME View Page", expectedPageTitle1, actualPageTitle1);
	}

	public void selectMonth() {
		ActionByJavaScript.click(getDriver(), SELECT_MONTH);
	}

	public void clickMonthType() {
		ActionByJavaScript.click(getDriver(), SELECT_LIST_ITEM);
	}

	public void detailDisplayed() {
		if (ActionByLocator.isDisplayed(getDriver(), TOTAL_TIME, TIME_OUT_SECONDS)) {
			ActionByJavaScript.click(getDriver(), LOGOUT_LINK);
		} else {
			getExecutionContext().getCurrentScenarioObj().get("Time").getAsString();

		}
	}
}
