package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement eMail;
	
	public WebElement eMail() {
		return eMail;
	}
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement password;
	
	public WebElement password() {
		return password;
	}
	
	@FindBy(xpath = "//input[@class='btn btn-primary']")
	WebElement button;
	
	public WebElement button() {
		return button;
	}
	
	

}
