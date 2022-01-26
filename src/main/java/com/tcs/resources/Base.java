package com.tcs.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
	public WebDriver driverInit() throws IOException {
		
		
		 prop = new Properties();
		FileInputStream file = new FileInputStream("C:\\Users\\Lenovo\\eclipse-workspace\\PageObjectModel\\src\\main\\java\\com\\tcs\\resources\\data.properties");
		
		prop.load(file);
		
		//to drive intput from mvn command we have to get property from system
		//String browsername= prop.getProperty("browser");
		
		String browsername= System.getProperty("browser");
		
		//command mvn test -Dbrowser=chrome
		
		if(browsername.equalsIgnoreCase("chrome")) {
		
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
			 driver = new ChromeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
		
	}
	
	public String getScreenshot(String testcasename, WebDriver driver) throws IOException {
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile= System.getProperty("user.dir")+"\\Screenshots\\"+testcasename+".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}


}
