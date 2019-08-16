package lpaPages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class FeedbackSummaryPage {
	
	WebDriver driver;
	
	public FeedbackSummaryPage(WebDriver driverType){
		this.driver = driverType;
	}
	
	String HOME_URL = "https://las.fmrei.com/lpa_gui_uat07/PFSubmitServlet";
	
	By appraisalWaiverPrescreenEligibilityType = By.xpath("//*[@class='col-4'][3]//*[@class='call-to-act']");
	By HAMessageText = By.xpath("//*[contains(text(), 'Loan is eligible for collateral representation and warranty relief with an appraisal waiver')]");
	By B1Messagetext = By.xpath("//*[contains(text(), 'This loan is eligible for an appraisal waiver')]");
	By NotEligibleText = By.xpath("//*[contains(text(), 'Not Eligible')]");
	

	
	public void validateEligibility(String expectedAceEligibility){
		String aceEligibilityStatus = driver.findElement(appraisalWaiverPrescreenEligibilityType).getText();
		String aceEligibilityStatusFromatted = aceEligibilityStatus.replaceAll(" ", "");	
		Assert.assertTrue(aceEligibilityStatusFromatted.toLowerCase().contains(expectedAceEligibility.toLowerCase()));
		if (aceEligibilityStatusFromatted.equals("Eligible")){
			Assert.assertTrue(driver.findElement(HAMessageText).isDisplayed());
			Assert.assertTrue(driver.findElement(B1Messagetext).isDisplayed());
		}
	}
	
	public void validateNotEligibile(){
		String aceEligibilityStatus = driver.findElement(appraisalWaiverPrescreenEligibilityType).getText().toLowerCase();
		Assert.assertTrue(aceEligibilityStatus.contains("not eligible"));
//		Assert.assertTrue(driver.findElement(NotEligibleText).isDisplayed());
	}
	
	public void validateScrubbedAddress(String Address1, String Address2){
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement((By.xpath("//*[contains(text(), '"+Address1+"')]"))));
		actions.moveToElement(driver.findElement((By.xpath("//*[contains(text(), '"+Address2+"')]"))));
//		Boolean aceEligibilityStatus = driver.findElement(By.xpath("//*[contains(text(), '"+ expectedAddress +"')]")).isDisplayed();
//		Assert.assertTrue(aceEligibilityStatus);
	}
	
	public void goHome(){
		driver.get(HOME_URL);
	}
	


}
