package utilities;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		if(extent==null) {
			ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\extent.html");
			try {
				spark.loadXMLConfig(System.getProperty("user.dir")+"\\src\\test\\resources\\extentConfig\\ReportsConfig.xml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			extent = new ExtentReports();
			extent.attachReporter(spark);
		}
		return extent;
		
	}

}
