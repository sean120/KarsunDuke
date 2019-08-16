package lpaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginHomePage {

 public LoginHomePage(WebDriver driver, String hOME_URL, String uSERNAME_1, String pASSWORD_1, By userNameInputBox,
			By passwordInputBox, By loginButton) {
	 
	 
		super();
		this.driver = driver;
		HOME_URL = hOME_URL;
		USERNAME_1 = uSERNAME_1;
		PASSWORD_1 = pASSWORD_1;
		this.userNameInputBox = userNameInputBox;
		this.passwordInputBox = passwordInputBox;
		this.loginButton = loginButton;
	}

WebDriver driver;
	

	public LoginHomePage(WebDriver driverType) {
		this.driver = driverType;
	}

	String HOME_URL = "https://www.amazon.com";
	String USERNAME_1 = "sthorson120";
	String PASSWORD_1 = "$uyghur32U";

	By userNameInputBox = By.id("loginUsername");
	By passwordInputBox = By.id("loginPassword");
	By loginButton = By.id("loginButton");

	public void loginApplication() {
		driver.get(HOME_URL);
		driver.findElement(userNameInputBox).sendKeys(USERNAME_1);
		driver.findElement(passwordInputBox).sendKeys(PASSWORD_1);
		driver.findElement(loginButton).click();
	}

	public void goHome() {
		driver.get(HOME_URL);
	}

}
