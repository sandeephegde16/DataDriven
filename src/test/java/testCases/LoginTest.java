package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;

public class LoginTest extends TestBase{
	
	@Test(priority=3)
	public void loginAsBankManagerHardAssert() throws InterruptedException {
		click("bankmanagerlogin");
		log.debug("bankmanagerlogin clicked.");
		Thread.sleep(2000);
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("addCustomerFail"))), "Element is not visible in Page");
		click("addCustomer");
		log.debug("Waited for 3 seconds.");
		actionsMoveToElement("addCustomer");
		Reporter.log("Bank Manager login failed");
	}

@Test(priority=2)
public void loginAsBankManagerSoftAssert() throws InterruptedException {
	click("bankmanagerlogin");
	log.debug("bankmanagerlogin clicked.");
	Thread.sleep(2000);
	softAssert("addCustomerFail");
	click("addCustomer");
	log.debug("Waited for 3 seconds.");
	actionsMoveToElement("addCustomer");
	s.assertAll();
}

@Test(priority=1)
public void loginAsBankManagerPass() throws InterruptedException {
	click("bankmanagerlogin");
	log.debug("bankmanagerlogin clicked.");
	Thread.sleep(2000);
	click("addCustomer");
	driver.findElement(By.xpath(OR.getProperty("addCustomer"))).click();
	log.debug("Waited for 3 seconds.");
	actionsMoveToElement("addCustomer");
	log.debug("Test Passed.");
}

}
