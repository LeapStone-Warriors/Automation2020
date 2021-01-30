package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class LoginPage extends TestBase {
	
	
	@FindBy(xpath="//input[@id='username']")
	WebElement username;
	
	@FindBy(xpath = "//input[@id='password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='Login']")
	WebElement loginBtn;
	
	@FindBy(xpath = "//img[contains (@title, 'User')]/ancestor::button")
	WebElement userIconBtn;
	
	@FindBy(xpath = "//a[contains(text(),'Log Out')]")
	WebElement logOut;
	
	
	
		//Initializing the Page Objects:
		public LoginPage() throws Exception {
			PageFactory.initElements(driver, this);
		}
		
		
		public HomePage login(String uname, String pwd) throws InterruptedException{

			TestUtil.enterValue(username, uname);
			TestUtil.enterValue(password, pwd);
			TestUtil.clickElement(loginBtn);

			return new HomePage();
		}
		
		public void logout() throws InterruptedException{
			
			System.out.println("\n*********************************************************************************************************************");
			System.out.println("\n Last TestCase in suite was executed. Logging out of SF.........");
			TestUtil.clickElement(userIconBtn);
			TestUtil.clickElement(logOut);
			System.out.println("Sucessfully logged out of SF.........");
			
		}
		
		
	
	}
