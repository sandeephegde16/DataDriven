package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Test;

import base.TestBase;
import utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log("Test Case Passed");
		test.log(Status.PASS, result.getMethod().getMethodName()+" Passed.");
		report.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("Test Case Failed");
		test.log(Status.FAIL, result.getMethod().getMethodName()+" Failed.");
		
		Reporter.log("Capturing screenshot");
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+" height=300 width=300>Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=300 width=300></img></a>");
		test.log(Status.INFO, result.getThrowable().fillInStackTrace());
		test.addScreenCaptureFromPath(TestUtil.screenshotName);		
		report.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method 
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}

}
