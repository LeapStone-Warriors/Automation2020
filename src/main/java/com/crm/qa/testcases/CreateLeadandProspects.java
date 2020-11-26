package com.crm.qa.testcases;



import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.crm.qa.base.TestBase;

import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

import com.crm.qa.base.InitializeUserData;
import com.crm.qa.pages.SalesforceRestAPI;
import com.crm.qa.pages.SalesforceTestRestAPI;

import com.crm.qa.pages.ClientPage;
import com.crm.qa.pages.LeadPage;
import com.crm.qa.util.*;
import com.qa.DataProvider.*;



public class CreateLeadandProspects extends TestBase {

	
	LoginPage 					loginPage;
	HomePage 					homePage;
	SalesforceTestRestAPI 		sfdcTestRestAPI;
	SalesforceRestAPI 			sfdcRestAPI;
	InitializeUserData 			initializeData;
	ClientPage					clientPage;
	LeadPage					leadPage;
	Log							log;	
	
	SoftAssert softAssertion = new SoftAssert();
	
	
	@BeforeMethod
	public void setUp() throws Exception {
		initialization();
		
		loginPage 			= new LoginPage();
		sfdcTestRestAPI 	= new SalesforceTestRestAPI();
		sfdcRestAPI 		= new SalesforceRestAPI();
		initializeData 		= new InitializeUserData();
		clientPage			= new ClientPage();
		leadPage			= new LeadPage();
		log					= new Log();
		
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
		
		initializeData.setTestCaseonDemandtoNo();
		initializeData.initialize();
		
	}
	



	
	
	@Test(description = "Test Description: Log a Call with  Reached Scenario. Test Case will be run for multiple profiles.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test(String advisorId) throws Exception{
		
		TestUtil.print("Create Prospect and add details to the Client");
		
		homePage.navigateToMultipleUser("advisor", advisorId);		log.info("Logged in as : " +advisorType(advisorId));
	
	}
	
	@Test(description = "Test Description: Log a Call with  Reached Scenario. Test Case will be run for multiple profiles.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test1(String advisorId) throws Exception{
		
		TestUtil.print("Creating Net New Lead through Rest API");
		
		//homePage.navigateToMultipleUser("advisor", advisorId);		log.info("Logged in as : " +advisorType(advisorId));
		SalesforceTestRestAPI.APIConnection();		log.info("Complete running SalesforceTestRestAPI.APIConnection()");
	}
	
	
	
	
	
	
	@Test(description = "Test Description: Log a Call with  Reached Scenario. Test Case will be run for multiple profiles.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test0(String advisorId) throws Exception{
		
		TestUtil.print("Reached Scenario for Multiple Users");
		
		homePage.navigateToMultipleUser("advisor", advisorId);		log.info("Logged in as : " +advisorType(advisorId));
//		SalesforceTestRestAPI.APIConnection();		log.info("Complete running SalesforceTestRestAPI.APIConnection()");
		//homePage.navigateToClientViewList("00B6g00000AHFP7EAP"); log.info("Go to Listview Page and set filter to All client");
		//homePage.navigateToRetailuser("Primary");
		driver.navigate().to("https://svb--qa.lightning.force.com/lightning/r/Account/0015500000qOjsNAAS/view");
		clientPage.newClientDetailPage();
	
	}
	

	@Test(description = "Test Description: Log a Call with  Reached Scenario. Test Case will be run for multiple profiles.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test3(String advisorId) throws Exception{
		
		homePage.navigateToMultipleUser("advisor", advisorId);		log.info("Logged in as : " +advisorType(advisorId));
		
		TestUtil.print("Experiment");
		leadPage.openLead();Thread.sleep(3000);
		System.out.println(leadPage.readLeadValues());
		
		System.out.println("Test Passed");
	
	}

	@AfterMethod
	//@AfterSuite
	public void tearDown(){
		driver.quit();
		
		
		
	}
	
}
