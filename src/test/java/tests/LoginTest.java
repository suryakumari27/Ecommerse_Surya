package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {
	WebDriver driver;
	String actualResult;
	Logger log;

	@BeforeMethod
	public void setUp() throws IOException {

		log = LogManager.getLogger(LoginTest.class.getName()); // Initiated log manager
		driver = initializeBrowser();
		log.debug("Browser Initiation");
		driver.get(prop.getProperty("url"));
		log.debug("Navigate to application url");
	}

	@Test(dataProvider = "getLoginData")
	public void login(String email, String password, String expectedStatus) {

		LandingPage landp = new LandingPage(driver); // Create obj for Landing page class
		landp.myAccountDropdown().click(); // calling myaccountdropdown method using object
		log.debug("clicked on my account dropdown"); // Logging
		landp.login().click();
		log.debug("clicked on login dropdown");

		LoginPage loginp = new LoginPage(driver);
		loginp.eMail().sendKeys(email);
		log.debug("sending email");
		loginp.password().sendKeys(password);
		log.debug("sending password");
		loginp.button().click();
		log.debug("clicked on login button");

		AccountPage Ap = new AccountPage(driver);

		try {
			Ap.EdityouraccountinformationLink();
			actualResult = "Successfull";
			log.debug("user got logedin");

		} catch (Exception e) {

			actualResult = "Failure";
			log.debug("user failed to log");
		}
		if (actualResult.equals(expectedStatus)) {
			
			log.info("Login Test got passed");
			
		}else {
			
			log.error("Login Test got failed");
		}

	}

	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = { { "suryakumari3143@gmail.com", "Surya123#", "Success" },
				{ "suryakumari31@gmail.com", "Surya123", "Failure" } };
		return data;
	}

	@AfterMethod
	public void closure() {
		driver.close();
	}

}
