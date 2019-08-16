package com.seanfiles.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReusableUtilities {

	public static String EMPTY_STRING = "";
	public static String MISMO_LABEL = "MISMO";
	public static String JSON_LABEL = "JSON";

	public static final int logCount = 1000;
	public static final int waitTime = 10000;
	public static final int shortwaitTime = 3000;
	public static final int MedwaitTime = 5000;

	public static String labelSearchString = "";

	public static String loanTypeSearchString = "";

	WebDriver driver;

	public WebDriver launchDriver() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		return driver;
	}

}
