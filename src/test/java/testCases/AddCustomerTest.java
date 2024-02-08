package testCases;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

public class AddCustomerTest extends TestBase{

	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void addCustomerTest(String firstName, String lastName, String postCode) throws InterruptedException{
		click("bankmanagerlogin");
		log.debug("bankmanagerlogin clicked.");
		Thread.sleep(2000);
		click("addCustomer");
		Thread.sleep(2000);
		type("firstName", firstName);
		type("lastName", lastName);
		type("postCode", postCode);
		driver.findElement(By.cssSelector(OR.getProperty("addCustomerSubmit"))).click();
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains("Customer added successfully with customer id :"));
	}
	
	
}
