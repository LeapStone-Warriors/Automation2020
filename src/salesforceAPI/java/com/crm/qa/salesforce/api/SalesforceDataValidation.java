package com.crm.qa.salesforce.api;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import com.crm.qa.salesforce.api.SalesforceConstants;
import com.crm.qa.util.TestBase;
import com.crm.qa.salesforce.api.SalesforceCRUDOperationAPI;


public class SalesforceDataValidation extends TestBase{

	private static JSONObject json = new JSONObject();	
	private static Map<String, String> salesforceLead = new HashMap<String, String>();
	static SalesforceCRUDOperationAPI sfdcAPICRUDOperation = new SalesforceCRUDOperationAPI();
	private static int i = 0;
		
	 	public static void  getSFLeadData() throws Exception  {
			salesforceLead.put(SalesforceConstants.Lead.CITY[0],json.getJSONArray("records").getJSONObject(i).getString(SalesforceConstants.Lead.CITY[1])); 
            salesforceLead.put(SalesforceConstants.Lead.COMPANYREGION[0],json.getJSONArray("records").getJSONObject(i).getString(SalesforceConstants.Lead.COMPANYREGION[1])); 
			salesforceLead.put(SalesforceConstants.Lead.COMPANY[0],json.getJSONArray("records").getJSONObject(i).getString(SalesforceConstants.Lead.COMPANY[1]));
            salesforceLead.put(SalesforceConstants.Lead.COUNTRY[0],json.getJSONArray("records").getJSONObject(i).getString(SalesforceConstants.Lead.COUNTRY[1]));
            salesforceLead.put(SalesforceConstants.Lead.DATEOFBIRTH[0],json.getJSONArray("records").getJSONObject(i).getString(SalesforceConstants.Lead.DATEOFBIRTH[1])); 
            salesforceLead.put(SalesforceConstants.Lead.EMAIL[0],json.getJSONArray("records").getJSONObject(i).getString(SalesforceConstants.Lead.EMAIL[1])); 
            salesforceLead.put(SalesforceConstants.Lead.INDUSTRY[0],json.getJSONArray("records").getJSONObject(i).getString(SalesforceConstants.Lead.INDUSTRY[1])); 
            salesforceLead.put(SalesforceConstants.Lead.NAME[0],json.getJSONArray("records").getJSONObject(i).getString(SalesforceConstants.Lead.NAME[1]));
            salesforceLead.put(SalesforceConstants.Lead.PRIORITY[0],json.getJSONArray("records").getJSONObject(i).getString(SalesforceConstants.Lead.PRIORITY[1]));
            salesforceLead.put(SalesforceConstants.Lead.PRODUCTSOFINTEREST[0],json.getJSONArray("records").getJSONObject(i).getString(SalesforceConstants.Lead.PRODUCTSOFINTEREST[1])); 
            salesforceLead.put(SalesforceConstants.Lead.STATE[0],json.getJSONArray("records").getJSONObject(i).getString(SalesforceConstants.Lead.STATE[1])); 
            salesforceLead.put(SalesforceConstants.Lead.TITLE[0],json.getJSONArray("records").getJSONObject(i).getString(SalesforceConstants.Lead.TITLE[1])); 
            salesforceLead.put(SalesforceConstants.Lead.WEBSITE[0],json.getJSONArray("records").getJSONObject(i).getString(SalesforceConstants.Lead.WEBSITE[1]));
	    }
	

	 	public static void validateLeadData() throws Exception {
	 		
	 		getSFLeadData();
	 		
	 		try {
	 		
	 		softAssertion.assertEquals(salesforceLead.get(SalesforceConstants.Lead.CITY[0]), "Dennis" , "City description mismatch");
	 		softAssertion.assertEquals(salesforceLead.get(SalesforceConstants.Lead.COMPANYREGION[0]), "New England" , "Company Region description mismatch");
			softAssertion.assertEquals(salesforceLead.get(SalesforceConstants.Lead.COMPANY[0]), "Cerevel" , "Company description mismatch");
	 		softAssertion.assertEquals(salesforceLead.get(SalesforceConstants.Lead.COUNTRY[0]), "United States" , "Country description mismatch");
			softAssertion.assertEquals(salesforceLead.get(SalesforceConstants.Lead.DATEOFBIRTH[0]), "1985-09-11" , "DateofBirth is mismatch");
	 		softAssertion.assertEquals(salesforceLead.get(SalesforceConstants.Lead.EMAIL[0]), "a.sharma@email.com" , "Email address is mismatch");
			softAssertion.assertEquals(salesforceLead.get(SalesforceConstants.Lead.INDUSTRY[0]), "Life Science - Biotech" , "Industry description mismatch");
	 		softAssertion.assertEquals(salesforceLead.get(SalesforceConstants.Lead.NAME[0]), "Anushka Sharma" , "Name is mismatch");
			softAssertion.assertEquals(salesforceLead.get(SalesforceConstants.Lead.PRIORITY[0]), "Medium" , "Priority description mismatch");
	 		softAssertion.assertEquals(salesforceLead.get(SalesforceConstants.Lead.PRODUCTSOFINTEREST[0]), "Mortgages" , "Product of Interest description mismatch");
			softAssertion.assertEquals(salesforceLead.get(SalesforceConstants.Lead.STATE[0]), "Massachusetts" , "State description mismatch");
	 		softAssertion.assertEquals(salesforceLead.get(SalesforceConstants.Lead.TITLE[0]), "Director of Data Analytics" , "Title description mismatch");
	 		softAssertion.assertEquals(salesforceLead.get(SalesforceConstants.Lead.WEBSITE[0]), "www.cerevel.com" , "Website description mismatch");
	 			 		
	 		softAssertion.assertAll();
	 	
	 	}catch (JSONException e) {e.printStackTrace();Assert.fail("Exception is thrown during Lead Data validation through API");}
	 }
	 	
	 	
	 	public static void validateLeadDataViaAPI(String sfdcId) throws Exception {
	 		
		 	json = sfdcAPICRUDOperation.getRecord("Lead", sfdcId);
		 	
		 	try {
		 		
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("City"), "Chandler" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("DateOfBirth__c"), "1965-11-11" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("Website"), "www.astra.com" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("Company__c"), "Pfizer" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("ProductsOfInterest__c"), "Tailored Lending" , "City description mismatch,");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("MobilePhone"), "5164432109" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("RelationshipStartDateWithSVB__c"), "2020-07-20" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("Priority__c"), "Immediate" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("TCPSegmentTier__c"), "Bronze: Low (< $500k)" , "City description mismatch");
		 		softAssertion.assertEquals(String.valueOf(json.getJSONArray("records").getJSONObject(0).getDouble("InstitutionalCapitalRaised__c")), "2345678.0" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("Industry"), "Life Science - Biotech" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("Country"), "United States" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("State"), "AZ" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("Street"), "123 Park Lane" , "City description mismatch");
		 		softAssertion.assertTrue(json.getJSONArray("records").getJSONObject(0).getString("Name").contains("Auto"), "Name mismatch");
		 		
		 		softAssertion.assertAll();
		 	
		 	}catch (JSONException e) {e.printStackTrace();Assert.fail("Exception is thrown during Lead Data validation through API");}
 	}
	 	
	 
	 	
	 	public static void validateLeadDataonUI(String sfdcId) throws Exception {
	 		
		 	json = sfdcAPICRUDOperation.getRecord("Lead", sfdcId);
		 	
		 	try {
		 		
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("City"), "Chandler" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("DateOfBirth__c"), "1965-11-11" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("Website"), "www.astra.com" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("Company__c"), "Pfizer" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("ProductsOfInterest__c"), "Tailored Lending" , "City description mismatch,");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("MobilePhone"), "5164432109" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("RelationshipStartDateWithSVB__c"), "2020-07-20" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("Priority__c"), "Immediate" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("TCPSegmentTier__c"), "Bronze: Low (< $500k)" , "City description mismatch");
		 		softAssertion.assertEquals(String.valueOf(json.getJSONArray("records").getJSONObject(0).getDouble("InstitutionalCapitalRaised__c")), "2345678.0" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("Industry"), "Life Science - Biotech" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("Country"), "United States" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("State"), "AZ" , "City description mismatch");
		 		softAssertion.assertEquals(json.getJSONArray("records").getJSONObject(0).getString("Street"), "123 Park Lane" , "City description mismatch");
		 		softAssertion.assertTrue(json.getJSONArray("records").getJSONObject(0).getString("Name").contains("Auto"), "Name mismatch");
		 		
		 		softAssertion.assertAll();
		 	
		 	}catch (JSONException e) {e.printStackTrace();Assert.fail("Exception is thrown during Lead Data validation through API");}
	 }
	 


}
