package com.crm.qa.testcases;


import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.crm.qa.dataProvider.*;
import com.crm.qa.util.TestUtil;
import com.crm.qa.base.InitialSetup;



public class LeadTestCases extends InitialSetup {

	String txt;
	
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
		
		
		try {
			//homePage.navigateToMultipleUser("advisor", advisorId);		log.info("Logged in as : " +advisorType(advisorId));
			
			TestUtil.print("Readind Leads Data from SF UI Leads Highlight Panel");
			leadPage.openLead("00Q5500000BkliREAR");
			//Thread.sleep(3000);
			System.out.println(leadPage.readLeadValues());
			System.out.println("Test Passed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

	
	@Test(description = "Test Description: Create Lead through SF API. Grab the Lead ID and open up the Lead in SF . Compare UI Data with the API data using Assertions.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test4(String advisorId) throws Exception{
		
		TestUtil.print("CreateLead via API andValidate through UI");
		//homePage.navigateToMultipleUser("advisor", advisorId);		log.info("Logged in as : " +advisorType(advisorId));
		leadPage.createLeadviaAPIandValidatethroughUI();
		
	}
	
	
	@Test(description = "Test Description: Create Lead through SF API. Grab the Lead ID and open up the Lead in SF . Compare UI Data with the API data using Assertions.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test5(String advisorId) throws Exception{
		
		TestUtil.print("Reading Leads Data from SF UI Leads Highlight Panel");
		//homePage.navigateToMultipleUser("advisor", advisorId);		log.info("Logged in as : " +advisorType(advisorId));
		leadPage.openLead("00Q5500000Bl4inEAB");
		if (driver.findElement(By.xpath("(//div[contains(@class, 'entityNameTitle')])[last()]")).getText().equalsIgnoreCase("Lead")){
			System.out.println("Yes");
			
		}else {System.out.println("No");}
		
	}
	
	
	@Test(description = "Test Description: Create Lead through SF API. Grab the Lead ID and open up the Lead in SF . Compare UI Data with the API data using Assertions.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test6(String advisorId) throws Exception{
		
		TestUtil.print("Reading Leads Data from SF UI Leads Highlight Panel");
		//homePage.navigateToMultipleUser("advisor", advisorId);		log.info("Logged in as : " +advisorType(advisorId));
		leadPage.openLead("00Q5500000Bl7OxEAJ");
		if (driver.findElement(By.xpath("(//div[contains(@class, 'entityNameTitle')])[last()]")).getText().equalsIgnoreCase("Lead")){
			System.out.println("Yes");
			
		}else {System.out.println("No");}
		
		
	}
	
	@Test(description = "Test Description: Grab the exiting Lead and using API and print Leads Data.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test7(String advisorId) throws Exception{
		
		TestUtil.print("Reading Lead Data");
		System.out.println(sfdcAPICRUDOperation.getRecord("Lead", "00Q5500000Bl7VUEAZ"));
	}
	
	@Test(description = "Test Description: Grab the exiting Lead and using API and print Leads Data.",dataProvider = "multipleUsers", dataProviderClass = LogaCallDataProvider.class)
	public void test8(String advisorId) throws Exception{
		
		TestUtil.print("Reading Lead Data");
		System.out.println(sfdcAPICRUDOperation.getRecord("Lead", "00Q5500000BlC9QEAV"));
	}
	
	
	
	
	/*
	
	@AfterMethod
	public void movetoHomePage() throws Exception {
		homePage.goBacktoHomepage();
		System.out.println("\nSucessfully Navigated to the HomePage..............");
	}
	
	
	@AfterTest
	public void logout() throws Exception{
		loginPage.logout();
	}
	*/	
	
	
}
