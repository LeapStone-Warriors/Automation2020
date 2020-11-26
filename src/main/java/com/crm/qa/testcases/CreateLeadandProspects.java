package com.crm.qa.testcases;



import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.dataProvider.*;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.base.InitializeUserData;
import com.crm.qa.pages.SalesforceRestAPI;
import com.crm.qa.pages.SalesforceTestRestAPI;
import com.crm.qa.salesforce.api.SalesforceCRUDOperationAPI;
import com.crm.qa.pages.ClientPage;
import com.crm.qa.pages.LeadPage;
import com.crm.qa.util.*;



public class CreateLeadandProspects extends TestBase {

	
	JSONObject lead = new JSONObject();
	
	
	LoginPage 					loginPage;
	HomePage 					homePage;
	SalesforceTestRestAPI 		sfdcTestRestAPI;
	SalesforceRestAPI 			sfdcRestAPI;
	SalesforceCRUDOperationAPI  sfdcAPICRUDOperation;
	InitializeUserData 			initializeData;
	ClientPage					clientPage;
	LeadPage					leadPage;
	Log							log;	
	
	
	
	
	@BeforeMethod
	public void setUp() throws Exception {
		initialization();
		
		loginPage 			= new LoginPage();
		sfdcTestRestAPI 	= new SalesforceTestRestAPI();
		sfdcRestAPI 		= new SalesforceRestAPI();
		sfdcAPICRUDOperation= new SalesforceCRUDOperationAPI();
		initializeData 		= new InitializeUserData();
		clientPage			= new ClientPage();
		leadPage			= new LeadPage();
		log					= new Log();
		
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
		
		initializeData.setTestCaseonDemandtoNo();
		initializeData.initialize();
		
	}
	

	@Test(description = "Test Description: Grab the exiting Lead and using API and print Leads Data.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test0(String advisorId) throws Exception{
		
		TestUtil.print("Reading Lead Data");
		System.out.println(sfdcAPICRUDOperation.getRecord("Lead", "00Q5500000BkliREAR"));
	}
	
	
	
	
	@Test(description = "Test Description: Creating Net New Lead through Rest API.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test1(String advisorId) throws Exception{
		
		TestUtil.print("Creating Net New Lead through Rest API");
		lead = sfdcAPICRUDOperation.createLead();
	
	}
	

	@Test(description = "Test Description: Read Leads Data from Salesforce Leads Highlight Panel.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test3(String advisorId) throws Exception{
		
		homePage.navigateToMultipleUser("advisor", advisorId);		log.info("Logged in as : " +advisorType(advisorId));
		
		TestUtil.print("Readind Leads Data from SF UI Leads Highlight Panel");
		leadPage.openLead("00Q5500000BkliREAR");
		//Thread.sleep(3000);
		System.out.println(leadPage.readLeadValues());
		System.out.println("Test Passed");
	
	}

	
	@Test(description = "Test Description: Log a Call with  Reached Scenario. Test Case will be run for multiple profiles.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test4(String advisorId) throws Exception{
		
		TestUtil.print("Create Prospect and add details to the Client");
		homePage.navigateToMultipleUser("advisor", advisorId);		log.info("Logged in as : " +advisorType(advisorId));
		leadPage.createLeadviaAPIandValidatethroughUI();
	
	}
	
	
	
	
	@AfterMethod
	//@AfterSuite
	public void tearDown(){
		driver.quit();
		
		
		
	}
	
}
