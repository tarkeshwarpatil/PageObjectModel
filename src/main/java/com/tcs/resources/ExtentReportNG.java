package com.tcs.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	
	public static ExtentReports getReportObject() {
		
		String Path =System.getProperty("user.dir")+"\\reports\\index.html";
		
		ExtentSparkReporter reporter =new ExtentSparkReporter(Path);
		
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Test Result");
		
		ExtentReports  extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Tarkeshwar");
		
		return extent;
	}

}
