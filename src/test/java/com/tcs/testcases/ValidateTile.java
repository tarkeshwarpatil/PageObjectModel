package com.tcs.testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tcs.pageobject.LoginPage;
import com.tcs.resources.Base;



public class ValidateTile extends Base {
	
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(Basepage_TestCaseID_001.class.getName());
	@BeforeTest
	public void beforestart() throws IOException {
		
		driver=driverInit();
		log.info("driver is initialized");
		driver.get(prop.getProperty("URL"));
		log.info(prop.getProperty("URL")+" URL is loaded");
		
	}
	
	
	
	@Test
	public void validateTiltle() {
		
		LoginPage l= new LoginPage(driver);
		Assert.assertEquals(l.featuredcoursetext(), "FEATURED COURSES12");
		log.info("text has been asserted");
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		log.info("driver has been closed");
		
	}
	

}
