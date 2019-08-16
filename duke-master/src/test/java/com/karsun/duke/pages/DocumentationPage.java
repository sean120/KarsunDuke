package com.karsun.duke.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.karsun.duke.utils.LoadProperties;
import com.karsun.kic.tan.duke.Page;
import com.karsun.kic.tan.duke.util.ActionByLocator;

@Component
public class DocumentationPage extends Page {
	private static final int TIME_OUT_SECONDS = 5;
	private static final By DOC_PAGE_ID = By.id("selenium-documentation");
	private static final String DOC_URL = "/docs";

	
	@Override
	protected boolean isLoaded() {
		return ActionByLocator.isDisplayed(getExecutionContext().getDriver(), DOC_PAGE_ID, TIME_OUT_SECONDS);
	}

	@Override
	protected void load() {
		String webURL = LoadProperties.getProperty("web.url") + DOC_URL;
		getDriver().get(webURL);
		Assert.assertTrue("Could not load Documentation page", isLoaded());
	}

	public void navigateTo(String topic) {
		ActionByLocator.click(getDriver(), By.linkText(topic), TIME_OUT_SECONDS);
	}

	public boolean isSubTopicDisplayed(String subtopic) {
		return ActionByLocator.isVisible(getDriver(), By.xpath("//h2[contains(.,'"+subtopic+"')]"), TIME_OUT_SECONDS);
	}
}
