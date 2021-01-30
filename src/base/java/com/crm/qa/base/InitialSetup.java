package com.crm.qa.base;

import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.salesforce.api.SalesforceCRUDOperationAPI;
import com.crm.qa.pages.ClientPage;
import com.crm.qa.pages.LeadPage;
import com.crm.qa.util.*;

public class InitialSetup extends TestBase {

		protected JSONObject lead = new JSONObject();
		
		
		protected LoginPage 					loginPage;
		protected HomePage 						homePage;
		protected SalesforceCRUDOperationAPI  	sfdcAPICRUDOperation;
		protected ClientPage					clientPage;
		protected LeadPage						leadPage;
		//protected Log							log;	
		
		
	
		
		@BeforeTest
		public void setUp() throws Exception {
			
			
			loginPage 			= new LoginPage();
			sfdcAPICRUDOperation= new SalesforceCRUDOperationAPI();
			clientPage			= new ClientPage();
			leadPage			= new LeadPage();
			//log					= new Log();
			
			homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
			System.out.println("Sucessfully login to SF................\n");
			
			homePage.navigateToMultipleUser("advisor", prop.getProperty("advisorsfdcId1"));
			
		}
		

		@AfterMethod
		public void movetoHomePage() throws Exception {
			homePage.goBacktoHomepage();
			System.out.println("\nSucessfully Navigated to the HomePage..............");
		}
		
		
		@AfterTest
		public void logout() throws Exception{
			loginPage.logout();
		}
			

}
