package com.karsun.duke.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

import com.karsun.duke.utils.LoadProperties;
import com.karsun.kic.tan.duke.Page;
import com.karsun.kic.tan.duke.util.ActionAnglrByCss;
import com.karsun.kic.tan.duke.util.ActionAnglrById;
import com.karsun.kic.tan.duke.util.ActionAnglrByLocator;
import com.karsun.kic.tan.duke.util.ActionByJavaScript;
import com.karsun.kic.tan.duke.util.ActionByLocator;

@Component
public class AmazonWebPage extends Page {
	
//	public AmazonWebPage(WebDriver driver){
//		super();
//		
//	}
		
	private static final int TIME_OUT_SECONDS = 25;
	//ActionByLocator actionbyLocator = new ActionByLocator();
	@SuppressWarnings("static-access")
	private By Amazon_HOME_Page = By.className("nav-logo-link").tagName(getPageTitle());
	private By signIn = By.xpath("//span[text()='Hello, Sign in']");
	private By userId =By.xpath("//*[@id='ap_email']");
	private By passWord = By.xpath("//*[@id='ap_password']");
	private By search = By.xpath("//*[@id='twotabsearchtextbox']");
	public String userName = getExecutionContext().getCurrentScenarioObj().get("userName").getAsString();
	public String pasWord = getExecutionContext().getCurrentScenarioObj().get("passWord").getAsString();
	
	//private By SUPPORT_LINK = By.linkText("Support");
	
	@Override
	protected boolean isLoaded() {
		return ActionByLocator.isDisplayed(getExecutionContext().getDriver(), Amazon_HOME_Page, TIME_OUT_SECONDS);
	}

	@Override
	protected void load() {
		getDriver().get(LoadProperties.getProperty("amazon.url"));
		Assert.assertTrue("Home_page", isLoaded());
	}

	public String getPageTitle() {
		return this.getDriver().getTitle();
	}
	
	protected AmazonWebPage() {
		getDriver().get(LoadProperties.getProperty("amazon.url"));
			
		
	}
	
	
	public void clickSingin (){
		ActionByLocator.click(getDriver(), signIn, TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(getDriver(), userId, userName, TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(getDriver(), passWord, pasWord, TIME_OUT_SECONDS);
	
	}
	
	WebDriver driver = getExecutionContext().getDriver();
	protected void isLoaded1() {
	Actions actions = new Actions(driver);
	WebElement mainMenu = 
	driver.findElement(By.id("nav-link-accountList"));
	actions.moveToElement(mainMenu);
	actions.build().perform();

	WebElement subMenu = driver.findElement(By.cssSelector("span.nav-action-inner"));
	
		// TODO Auto-generated method stub
	

	
	
	
	
	
	
//	public void test() {
//		// Just to show some additional helpers built into Duke
//		WebDriver driver = getExecutionContext().getDriver();
//		
//			driver.get(LoadProperties.getProperty("amazon.url"));
//			String a =driver.getCurrentUrl();
//			System.out.println(a);
//		
//		ActionAnglrByCss.assertEqualsText(driver,"form small","",30);
//		ActionAnglrByCss.assertTextContainsTrue(driver,".summary-remarks-attachments label","",30);
//		ActionAnglrByCss.click(driver,"[aria-label=\"Refresh\"]",30);
//		
//		ActionAnglrByCss.getTextContainsToVerify(driver, "[id=\"profile\"]", "someString".toUpperCase(), 5);
//		
//		ActionAnglrByCss.isDisplayed(driver,"[title='"+""+"']",30);
//		ActionAnglrByCss.sendKeys(driver,"[placeholder=\"Rejection comments\"]","",30);
//		
//		ActionAnglrById.clear(driver,"rate",0,30);
//		ActionAnglrById.click(driver, "logout", 10);
//		
//		ActionAnglrByLocator.click(driver,By.xpath(""),30);
//		ActionAnglrByLocator.sendKeys(driver, By.className(""), "", 5);
//
//		ActionByJavaScript.scrollIntoView(driver, ActionAnglrByCss.getElement(driver,"[title='"+""+"']",30));
//
//	}


//	public void navigateTo(String tabName) {
//	
//		switch (tabName) {
//		case "signin":
//			ActionByLocator.click(getDriver(), signIn, TIME_OUT_SECONDS);
//			break;
//		case "search":
//			ActionByLocator.click(getDriver(), search, TIME_OUT_SECONDS);
//			break;
//		default:
//			throw new RuntimeException("Unknown tab: " + tabName);
//		}
//	}
	



}
}




