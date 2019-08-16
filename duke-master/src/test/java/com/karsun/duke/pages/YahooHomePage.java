package com.karsun.duke.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.karsun.duke.utils.LoadProperties;
import com.karsun.kic.tan.duke.Page;
import com.karsun.kic.tan.duke.util.ActionByLocator;

@Component
public class YahooHomePage extends Page {
	private static final int TIME_OUT_SECONDS = 5;
	private By Yahoo_HOME_PAGE_TEXT = By.xpath("//a[@id='uh-logo']");
// Tab navigation elements
	private By finance = By.id("yui_3_18_0_3_1565900969998_805");
	private By news = By.id("yui_3_18_0_3_1565901780179_732");
	private By Sports = By.id("yui_3_18_0_3_1565901780179_805");
	private By email = By.id("yui_3_18_0_3_1565901780179_786");
	

	@Override
	protected boolean isLoaded() {
		return ActionByLocator.isDisplayed(getExecutionContext().getDriver(), Yahoo_HOME_PAGE_TEXT, TIME_OUT_SECONDS);
	}

	@Override
	protected void load() {
		getDriver().get(LoadProperties.getProperty("yahoo.url"));
		Assert.assertTrue("Could not load Yahoo page", isLoaded());
	}

	public String getPageTitle() {
		return this.getDriver().getTitle();
	}

	public void navigateTo(String tabName) {
		switch (tabName) {
		case "fiance":
			ActionByLocator.click(getDriver(), finance, TIME_OUT_SECONDS);
			break;
		case "Sports":
			ActionByLocator.click(getDriver(), Sports, TIME_OUT_SECONDS);
			break;
			
		case "email":
			ActionByLocator.click(getDriver(), email, TIME_OUT_SECONDS);
		default:
			throw new RuntimeException("Unknown tab: " + tabName);
		}
	}
	
	public void Teardown(){
		getExecutionContext().getDriver().close();
	}
	
}