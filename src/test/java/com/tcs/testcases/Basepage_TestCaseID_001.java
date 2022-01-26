package com.tcs.testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tcs.pageobject.LoginPage;
import com.tcs.resources.Base;



public class Basepage_TestCaseID_001 extends Base {
	
	WebDriver driver;
	public static Logger log = LogManager.getLogger(Basepage_TestCaseID_001.class.getName());
	
	@BeforeMethod
	public void driverstart() throws IOException {
		
		driver=driverInit();
		log.info("driver is initialized");
		log.info("URL is being loaded");
		driver.get(prop.getProperty("URL"));
		log.info(prop.getProperty("URL")+" URL is loaded");
	}
	
	@Test(dataProvider="getData")
	public void basenavigation(String email, String password) throws IOException {		
		LoginPage l= new LoginPage(driver);
		l.clickloginlink();
		log.info("clicked on login link");
		l.setEmail(email);
		log.info("email has been entered");
		l.setPassword(password);
		log.info("password has been set");
		l.clickloginbutton();
		log.info("clicked on login button");
		
	}
	
	@DataProvider
	public Object getData() {
		
		Object[][] data = new Object[2][2];
		
		data [0][0]= "nonrestrictadeuser@gmail.com";
		data [0][1]= "password1";
		data [1] [0] = "restrcicteduser@gmail.com";
		data [1] [1]= "password2";
		
		
		return data;
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("driver closed");
	}

}
