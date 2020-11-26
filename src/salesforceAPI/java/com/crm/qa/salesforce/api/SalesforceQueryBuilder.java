package com.crm.qa.salesforce.api;

public class SalesforceQueryBuilder {

	private static String read, post;
	
	
	public static String getQueryFor(String queryFor, String sfdcId)   {
		
		if (queryFor.equalsIgnoreCase("Lead")) {
			read = "SELECT Name, Street,City,State, CompanyRegion__c, Company__c, Country, DateOfBirth__c, Email, Industry, InstitutionalCapitalRaised__c, TCPSegmentTier__c,Priority__c,ProductsofInterest__c,MobilePhone,RelationshipStartDateWithSVB__c,Title,Website FROM Lead WHERE Id = '"+sfdcId+"'";
		}else if (queryFor.equalsIgnoreCase("Account")) {
			read = "";
		}else if (queryFor.equalsIgnoreCase("Case")) {
			read = "";
		}else if (queryFor.equalsIgnoreCase("Opportunity")) {
			read = "";
		}else if (queryFor.equalsIgnoreCase("Contact")) {
			read = "";
		}	
			
		return read;
	}
	
	
	public static String postQueryFor(String queryFor, String sfdcId)   {
		
		if (queryFor.equalsIgnoreCase("Lead")) {
			post = "SELECT Name, Street,City,State, CompanyRegion__c, Company__c, Country, DateOfBirth__c, Email, Industry, InstitutionalCapitalRaised__c, TCPSegmentTier__c,Priority__c,ProductsofInterest__c,MobilePhone,RelationshipStartDateWithSVB__c,Title,Website FROM Lead WHERE Id = '"+sfdcId+"'";
		}else if (queryFor.equalsIgnoreCase("Account")) {
			post = "";
		}else if (queryFor.equalsIgnoreCase("Case")) {
			post = "";
		}else if (queryFor.equalsIgnoreCase("Opportunity")) {
			post = "";
		}else if (queryFor.equalsIgnoreCase("Contact")) {
			post = "";
		}	
			
		return post;
	}
	
	
}
