package com.crm.qa.testcases;



import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.base.InitializeUserData;
import com.crm.qa.pages.SalesforceRestAPI;
import com.crm.qa.pages.SalesforceTestRestAPI;
import com.crm.qa.pages.ClientPage;
import com.crm.qa.util.*;
import com.qa.DataProvider.*;



public class TestExperiment extends TestBase {

	

	LoginPage 					loginPage;
	HomePage 					homePage;
	SalesforceTestRestAPI 		sfdcTestRestAPI;
	SalesforceRestAPI 			sfdcRestAPI;
	InitializeUserData 			initializeData;
	ClientPage					clientPage;
	Log							log;	
	
	
	
	SoftAssert softAssertion = new SoftAssert();
	
	
	@BeforeSuite
	public void setUp() throws Exception {
		initialization();
		
		loginPage 			= new LoginPage();
		sfdcTestRestAPI 	= new SalesforceTestRestAPI();
		sfdcRestAPI 		= new SalesforceRestAPI();
		initializeData 		= new InitializeUserData();
		clientPage			= new ClientPage();
		log					= new Log();
		
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
		initializeData.setTestCaseonDemandtoNo();
		initializeData.initialize();
		
		try {Thread.sleep(2000);} 
		catch (InterruptedException e) {e.printStackTrace();}
		
	}
	
	
	

	
	
	
	@Test(description = "Test Description: Log a Call with  Reached Scenario. Test Case will be run for multiple profiles.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test(String advisorId) throws Exception{
		
		TestUtil.print("Create Prospect and add details to the Client");
		
		homePage.navigateToMultipleUser("advisor", advisorId);		log.info("Logged in as : " +advisorType(advisorId));
//		SalesforceTestRestAPI.APIConnection();		log.info("Complete running SalesforceTestRestAPI.APIConnection()");
		homePage.navigateToClientViewList("00B6g00000AHFP7EAP"); log.info("Go to Listview Page and set filter to All client");
		//homePage.navigateToRetailuser("Primary");
		clientPage.clickNewButtonOnClientViewList();
		clientPage.createClient();
		clientPage.updateClientDetailPage();
	
	}
	
	@Test(description = "Test Description: Log a Call with  Reached Scenario. Test Case will be run for multiple profiles.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test1(String advisorId) throws Exception{
		
		TestUtil.print("Creating Net New Lead through Rest API");
		
		//homePage.navigateToMultipleUser("advisor", advisorId);		log.info("Logged in as : " +advisorType(advisorId));
		//SalesforceTestRestAPI.APIConnection();		log.info("Complete running SalesforceTestRestAPI.APIConnection()");
	}
	
	
	
	
	
	
	@Test(description = "Test Description: Log a Call with  Reached Scenario. Test Case will be run for multiple profiles.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test0(String advisorId) throws Exception{
		
		TestUtil.print("Reached Scenario for Multiple Users");
		
		homePage.navigateToMultipleUser("advisor", advisorId);		log.info("Logged in as : " +advisorType(advisorId));
//		SalesforceTestRestAPI.APIConnection();		log.info("Complete running SalesforceTestRestAPI.APIConnection()");
		//homePage.navigateToClientViewList("00B6g00000AHFP7EAP"); log.info("Go to Listview Page and set filter to All client");
		//homePage.navigateToRetailuser("Primary");
		driver.navigate().to("https://svb-pbwm--qa.lightning.force.com/lightning/r/Account/0011100001wK9yRAAS/view");
		clientPage.updateClientDetailPage();
	
	}
	


	@AfterSuite
	public void tearDown(){
		driver.quit();
		
		
		
	}
	
}
