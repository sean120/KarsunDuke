package com.karsun.duke.pages;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.karsun.duke.utils.LoadProperties;
import com.karsun.kic.tan.duke.Page;
import com.karsun.kic.tan.duke.util.ActionByJavaScript;
import com.karsun.kic.tan.duke.util.ActionByLocator;

@Component
public class ActitimeTrackPage extends Page {
	private static final int TIME_OUT_SECONDS = 5;
	private static final By ACTI_TRACK = By.id("enterTTMainContent");
	private static final By TASK_SEARCH_FIELD = By.id("taskSearchControl_field");
	private static final By TASK_FIELD = By.xpath("///table[@id='taskSearchControl']//div[@class='taskList']");
	private static final By SUBMIT_BTN = By.xpath("//*[@id=\"SubmitTTButton\"]");
	private static final By LEAVE_TIME = By.xpath("//input[@id='spent_98_0']");
	private static final By Day_TOTAL = By.xpath("//*[@id=\"dayTotal[0]\"]");
	private static final By HIDE_ROW = By.id("selectionRightLug98");
	private static final By TASK_TABLE = By.xpath("//*[@id='tt-table']/tbody/trgit push/td");
	private static final Logger log = LoggerFactory.getLogger(ActitimeTrackPage.class);

	@Override
	protected boolean isLoaded() {
		return ActionByLocator.isDisplayed(getDriver(), ACTI_TRACK, TIME_OUT_SECONDS);
	}

	@Override
	protected void load() {
		getDriver().get(LoadProperties.getProperty("actitrack.url"));
		Assert.assertTrue("Could not load ActiTime page", isLoaded());
	}

	public String getPageTitle() {
		return getDriver().getTitle();
	}

	public void getTimeTrackPage() {
		get();
		String expectedPageTitle1 = getExecutionContext().getCurrentScenarioObj().get("actiTrackTitle").getAsString();
		String actualPageTitle1 = getPageTitle();
		System.out.println(actualPageTitle1);
		Assert.assertEquals("actiTIME Track Page", expectedPageTitle1, actualPageTitle1);
	}

	public void enterTaskName() {
//		String rowCount = ActionByLocator.getText(getDriver(), TASK_TABLE, TIME_OUT_SECONDS);
//		System.out.println(rowCount);
//		if (rowCount != getExecutionContext().getCurrentScenarioObj().get("taskname").getAsString()) {
			ActionByLocator.sendKeys(getDriver(), TASK_SEARCH_FIELD,
					getExecutionContext().getCurrentScenarioObj().get("taskname").getAsString(), TIME_OUT_SECONDS);
			log.info("User successfully entered data in task search field");
//			ActionByLocator.click(getDriver(), TASK_FIELD, TIME_OUT_SECONDS);
			ActionByJavaScript.click(getDriver(), TASK_FIELD);
			log.info("User successfully clicked in task field");
			ActionByLocator.sendKeys(getDriver(), LEAVE_TIME,
					getExecutionContext().getCurrentScenarioObj().get("lvtime").getAsString(), TIME_OUT_SECONDS);
			log.info("User is able to enter leave time successfully ");
//		}

	}

	public void submitBTN() {
		ActionByLocator.click(getDriver(), SUBMIT_BTN, TIME_OUT_SECONDS);
		log.info("User successfully clicks on submit button");
	}

	public void dayTotal() {
		ActionByLocator.isDisplayed(getDriver(), Day_TOTAL, TIME_OUT_SECONDS);
		log.info("Total work time is displayed");
		redirectedUrl(getExecutionContext().getCurrentScenarioObj().get("actiTrackTitle").getAsString());
		ActionByJavaScript.click(getDriver(), HIDE_ROW);
		ActionByJavaScript.click(getDriver(), SUBMIT_BTN);
	}

}