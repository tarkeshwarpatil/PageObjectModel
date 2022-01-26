package com.tcs.resources;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends Base implements ITestListener {

	ExtentReports extent = ExtentReportNG.getReportObject();
	ExtentTest	test;
	
	//we are creating test object which is not thread safe. this will work when execution is in sequential mode but it will fail
	//when it is in parallel mode, so we need to make it thread safe
	ThreadLocal <ExtentTest> threadlocaltest = new ThreadLocal <ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		
		test=extent.createTest(result.getMethod().getMethodName());
		//we are initiating Extent Test object from here, so i will set this object into Thread Local class to make it thread safe
		threadlocaltest.set(test);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		threadlocaltest.get().log(Status.PASS, "Test Pass");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		//on test fail we need logs 
		threadlocaltest.get().fail(result.getThrowable());
		
		//in get screenshot method to catch the screenshots of the actual failed test case we have to use the
		//driver instance which is actually executing the test 
		//and that will be the driver instance intiated at method level.. to catch that driver we have below step
		
		WebDriver driver = null;
		
		
		try {
			 driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (NoSuchFieldException e1) {
			
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		
		
		
		String failedtestcasename=result.getMethod().getMethodName();
		try {
		String destinationFile	=getScreenshot(failedtestcasename, driver);
			threadlocaltest.get().addScreenCaptureFromPath(destinationFile, failedtestcasename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void onTestSkipped(ITestResult result) {
	
		threadlocaltest.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
	}

	@Override
	public int hashCode() {
		
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return false;
		
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return null;
		
	}

	@Override
	public String toString() {
		return null;
		
	}

	@Override
	protected void finalize() throws Throwable {
		
	}

	
}
