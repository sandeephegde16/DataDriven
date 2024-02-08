package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.ExcelReader;
import utilities.ExtentManager;

public class TestBase {
	
	/*
	 * Webdriver - Chrome, FF, IE - Done
	 * Properties - Config, OR - Done
	 * Logs - Log4j jar, Application.log, Selenium.log, Log4j Properties file, Logger Class
	 * Extent Report
	 * DB
	 * Mail
	 * Excel
	 */
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = LogManager.getLogger();
	public static WebDriverWait wait;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\TestData.xlsx");;
	public static ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest test;
	public SoftAssert s;
	public Actions a;

	
	@BeforeMethod
	public void setUp() throws IOException {
		if (driver==null) {
			fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			config.load(fis);
			log.debug("Config File Loaded.");
			
			fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			OR.load(fis);
			log.debug("OR File Loaded.");
		}
		
		if (config.getProperty("browser").equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
			driver=new ChromeDriver();
			log.debug("Chrome driver Loaded.");
		} else if (config.getProperty("browser").equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
			driver=new FirefoxDriver();
		} else if (config.getProperty("browser").equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.internetexplorer.driver","iedriver.exe");
			driver=new InternetExplorerDriver();
		}
		driver.get(config.getProperty("url"));
		log.debug("URL opened: " + config.getProperty("url"));
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofSeconds(10,1));
		s = new SoftAssert();
		a = new Actions(driver);
		
	}
	
	public void click(String locator) {
		driver.findElement(By.xpath(OR.getProperty(locator))).click();
		test.log(Status.INFO, "Clicking on " + locator);
	}
	
	public void type(String locator, String value) {
		driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		test.log(Status.INFO, "Entering " + value + " in " + locator);
	}
	
	public void softAssert(String locator) {
		s.assertTrue(isElementPresent(By.xpath(OR.getProperty(locator))), "Element is not visible in Page");
		test.log(Status.INFO, "Checking for presence of " + locator + " in Page.");
	}
	
	public void actionsMoveToElement(String locator) {
		a.moveToElement(driver.findElement(By.xpath(OR.getProperty(locator)))).build().perform();
		test.log(Status.INFO, "Moving to element " + locator + " in Page.");
	}
	
	public boolean isElementPresent(By by) {
		try{
			driver.findElement(by);
			return true;
		} catch(NoSuchElementException e){
			return false;
		}
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
			log.debug("Browser Closed.");
		}
		
	}

}
