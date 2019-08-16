package lpaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.Assert;

public class LpaHome {

	WebDriver driver;

	public LpaHome(WebDriver driverType) {
		this.driver = driverType;
	}

	String HOME_URL = "https://las.fmrei.com/lpa_gui_uat07/IndexServlet";

	By newLoansLink = By.linkText("New Loans");
	By importApplicationLink = By.linkText("IMPORT APPLICATION");
	By uploadButton = By.name("ImportFileName");
	By continueButton = By.name("continue");
	By loanApplicationDataLink = By.linkText("Loan Application Data");
	By submitLoanButton = By.id("submitLoan");
	By confirmButton = By.name("confirm");
	By evaluationSummaryTitle = By.xpath("//*[contains(text(), 'Evaluation Summary')]");

	public void uploadLoanFile(String filePath) {
		driver.findElement(newLoansLink).click();
		driver.findElement(importApplicationLink).click();
		driver.findElement(uploadButton).sendKeys(filePath);
		driver.findElement(continueButton).click();
		driver.findElement(loanApplicationDataLink).click();
		driver.findElement(submitLoanButton).click();
		driver.findElement(confirmButton).click();

	}

	public void waitForSubmittedFileToLoad() throws InterruptedException {
		for (int i = 0; i < 120; i++) {
			boolean pageLoaded = false;

			try {
				pageLoaded = driver.findElement(evaluationSummaryTitle).isDisplayed();

			} catch (Exception e) {
				// System.out.println("Page didn't load with submitted file");
			}

			if (pageLoaded == true) {
				break;
			} else {
				Thread.sleep(1000);
			}
		}

		if (!driver.findElement(evaluationSummaryTitle).isDisplayed()) {
			Assert.assertTrue(driver.findElement(evaluationSummaryTitle).isDisplayed());
			driver.close();
			driver.quit();

		}
	}

	public void goHome() {
		driver.get(HOME_URL);
	}

}
