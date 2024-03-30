package AppiumProject.Framework.testUtils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import AppiumProject.Framework.utils.AppiumUtils;
import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumUtils implements ITestListener{
	
	AppiumDriver driver;
	ExtentTest test;
	
	// Calling getReporterObject() static method in ExtentReporterNG class
	ExtentReports extent= ExtentReporterNG.getReporterObject();
	
	public void onTestStart(ITestResult result) {
		
		// Creating test and then generating report for each test class
		test= extent.createTest(result.getMethod().getMethodName());
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test.log(Status.PASS, "Test passed");
	}
	
	public void onTestFailure(ITestResult result) {
		
		// Screenshot code
		String testCaseName= result.getMethod().getMethodName();
		
		test.log(Status.FAIL, "Test failed");
		test.fail(result.getThrowable());
		
		try {
			driver= (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			test.addScreenCaptureFromPath(getScreenshotPath(driver, testCaseName), testCaseName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onFinish(ITestContext context) {
		
		// To complete the generation of extent report
		extent.flush();
	}
}
