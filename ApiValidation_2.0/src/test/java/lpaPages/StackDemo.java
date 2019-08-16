package lpaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StackDemo {

	
	
public static void main(String[] args) {
	String url = "https://wwww.google.com";

	System.setProperty("webdriver.chrome.driver", "/Users/seanthorson/api/project 2/ACEAPI2.0_Validation/src/test/resources/executables/chromedriver");
	
	WebDriver driver = new ChromeDriver();
	driver.get(url);
	WebElement element = driver.findElement(By.id("lst_ib"));
	element.sendKeys("2*3");
	element.submit();
	
	WebElement result = driver.findElement(By.id("cwos"));
	
	System.out.println(result);

	
	
	
}
	
	
	

}
