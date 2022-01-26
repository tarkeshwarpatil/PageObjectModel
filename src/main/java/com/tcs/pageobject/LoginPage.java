package com.tcs.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;

	public LoginPage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath="//span[contains(text(),'Login')]")
	private	WebElement loginlink;
	
	@FindBy(xpath="//h2[contains(text(),'Featured Courses')]")
	private WebElement FeaturedCourseText;
	
	@FindBy(id="user_email")
	private	WebElement email;
	
	@FindBy(id="user_password")
	private WebElement passwordtext;
	
	@FindBy(xpath="//input[@name='commit']")
	private WebElement loginbutton;
	
	
	
	public void clickloginlink() {
		
		loginlink.click();
	}
	
	public void setEmail(String emailID) {
		email.sendKeys(emailID);
	}
	
	public void setPassword (String password) {
		
		passwordtext.sendKeys(password);
	}
	
	public void clickloginbutton() {
		
		loginbutton.click();
		
	}
	
	public String featuredcoursetext() {
		
		return FeaturedCourseText.getText();
	}
}
