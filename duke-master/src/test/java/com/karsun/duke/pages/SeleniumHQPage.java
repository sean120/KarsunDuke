package com.karsun.duke.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.karsun.duke.utils.LoadProperties;
import com.karsun.kic.tan.duke.Page;
import com.karsun.kic.tan.duke.util.ActionByLocator;
@Component
public class SeleniumHQPage extends Page {
	private static final int TIME_OUT_SECONDS = 5;
	private By SE_HOME_PAGE_TEXT = By.xpath("//h2[contains(.,'What is Selenium?')]");
	
	// Tab navigation elements
	private By DOCUMENTATION_LINK = By.cssSelector("a[title='Technical references and guides']");
	private By SUPPORT_LINK = By.linkText("Support");

	

	@Override
	protected boolean isLoaded() {
		return ActionByLocator.isDisplayed(getExecutionContext().getDriver(), SE_HOME_PAGE_TEXT, TIME_OUT_SECONDS);
	}

	@Override
	protected void load() {
		getDriver().get(LoadProperties.getProperty("web.url"));
		Assert.assertTrue("Could not load Se HQ page", isLoaded());
	}

	public String getPageTitle() {
		return this.getDriver().getTitle();
	}

	public void navigateTo(String tabName) {
		switch (tabName) {
		case "Documentation":
			ActionByLocator.click(getDriver(), DOCUMENTATION_LINK, TIME_OUT_SECONDS);
			break;
		case "Support":
			ActionByLocator.click(getDriver(), SUPPORT_LINK, TIME_OUT_SECONDS);
			break;
		default:
			throw new RuntimeException("Unknown tab: " + tabName);
		}
	}
}
