package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class LoginPage extends TestBase{
	
	
	@FindBy(xpath="//input[@id='username']")
	WebElement username;
	
	@FindBy(xpath = "//input[@id='password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='Login']")
	WebElement loginBtn;

	
		//Initializing the Page Objects:
		public LoginPage(){
			PageFactory.initElements(driver, this);
		}
		
		
		public HomePage login(String uname, String pwd) throws InterruptedException{

			TestUtil.enterValue(username, uname);
			TestUtil.enterValue(password, pwd);
			TestUtil.clickElement(loginBtn);

			return new HomePage();
		}
		
		
		
		
	
	}
