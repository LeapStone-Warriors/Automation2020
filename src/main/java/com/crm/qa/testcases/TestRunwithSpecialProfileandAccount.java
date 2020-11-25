package com.crm.qa.testcases;




import java.text.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.crm.qa.base.TestBase;

import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

import com.crm.qa.pages.RetailAccount;
import com.crm.qa.base.InitializeUserData;
import com.crm.qa.pages.SalesforceRestAPI;
import com.crm.qa.pages.SalesforceTestRestAPI;

import com.crm.qa.util.*;
import com.qa.DataProvider.*;





public class TestRunwithSpecialProfileandAccount  extends TestBase {
	
	
	LoginPage loginPage;
	HomePage homePage;

	
	SalesforceTestRestAPI sfdc;
	SalesforceTestRestAPI sfdcTestRestAPI;
	SalesforceRestAPI sfdcRestAPI;
	RetailAccount retailAccount;

	InitializeUserData initializeData;

	Log	log;
	
		
	SoftAssert softAssertion = new SoftAssert();
	
	
	@BeforeMethod
	public void setUp() throws Exception {
		
		initialization();
		
	
		loginPage 			= new LoginPage();
		sfdcTestRestAPI 	= new SalesforceTestRestAPI();
		sfdcRestAPI 		= new SalesforceRestAPI();
		retailAccount 		= new RetailAccount();
	
		initializeData 		= new InitializeUserData();
	
		log					= new Log();
		
		
		
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		//homePage = loginPage.login(prop.getProperty("advusername"), prop.getProperty("advpassword"));
		
		initializeData.setTestCaseonDemandtoNo();
		initializeData.initialize();
		
		
		try {Thread.sleep(2000);} 
		catch (InterruptedException e) {e.printStackTrace();}
		
	}
	
	
	@Test(description = "Test Description: Create a Branch Opportunity. Log a Call with Reached.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test(String advisorId) throws Exception {
		
	
				
	}
	
	
	

	
	
	
	
	@Test()
	public void navigateToRetailUser() throws InterruptedException, ParseException{
		
		SalesforceRestAPI.dataCreation();
		homePage.navigateToRetailuser("Primary");		
								
	}

	
	
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
		
		
		
	}

}








