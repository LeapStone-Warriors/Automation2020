
package com.crm.qa.pages;

import java.io.OutputStream;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;
import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class SalesforceTestRestAPI extends TestBase {

	static OutputStream output = null;
	private static String taskId;
	private static String taskName;
	private static String taskCreatedDate;
	private static String taskCallOutcome;
	private static String taskDescription;
	private static Object taskCallType;
	private static Object taskType;
	private static Object taskStatus;
	private static Object taskSolutionsDiscussed;
	private static Object taskActivityDate;
	private static Object meetingId;
	private static Object meetingMedium;
	private static Object meetingStatus;
	public static  Object leadId;
	private static Object leadStatus;
	private static Object leadCallAttempts;
	private static Object leadCustomerContact;
	private static Object leadIsConverted;
	private static Object meetingType;
	private static Object meetingSubType;
	private static Object meetingStartDate;
	private static Object meetingEndDate;
	private static Object appointmentStartDateText;
	static String retailId;
	static String busleadId;
	static String branchleadId;
	static String callTime;
	static String timeData = "true";
	static boolean tData = Boolean.valueOf(timeData);
	static String descData = "true";
	static boolean dData = Boolean.valueOf(descData);
	static String description;
	static String Outcome;
	static String enteredDate;
	static String schedule;
	static String[] multiFutureActvities;
	
	static HomePage hpage = new HomePage();

	static SalesforceRestAPI sfAPI = new SalesforceRestAPI();

	static Logger log = Logger.getLogger("trail");

	public static void APIConnection() {

		/*
		try {output = new FileOutputStream(prop.getProperty("QAconfig_path"));} 
		catch (FileNotFoundException e) {e.printStackTrace();}
*/
		dataCreation_basic();

	}

	public static void dataCreation() {

		log.info("Running SFDC API");
		SalesforceRestAPI.APIConnection();log.info("Complete running SalesforceRestAPI.APIConnection()");
		SalesforceRestAPI.createRetailRecord();log.info("Complete running SalesforceRestAPI.createRetailRecord()");
		SalesforceRestAPI.updateRetailRecord();log.info("Complete running SalesforceRestAPI.updateRetailRecord()");
		SalesforceRestAPI.updateBrancAccountRelationship();log.info("Complete running SalesforceRestAPI.updateBrancAccountRelationship();");
		SalesforceRestAPI.createBusinessLead();log.info("Complete running SalesforceRestAPI.createBusinessLead()");
		SalesforceRestAPI.createBranchLead();log.info("Complete running SalesforceRestAPI.createBranchLead()");

	}
	
	
	public static void dataCreation_basic() {

		SalesforceRestAPI.APIConnection();log.info("Complete running SalesforceRestAPI.APIConnection()");
		SalesforceRestAPI.createLead();
	}
	
		
	public static void dataCreation_businesslead() {

		SalesforceRestAPI.APIConnection();log.info("Complete running SalesforceRestAPI.APIConnection()");
		SalesforceRestAPI.createRetailRecord();log.info("Complete running SalesforceRestAPI.createRetailRecord()");
		SalesforceRestAPI.updateRetailRecord();log.info("Complete running SalesforceRestAPI.updateRetailRecord()");
		SalesforceRestAPI.updateBrancAccountRelationship();log.info("Complete running SalesforceRestAPI.updateBrancAccountRelationship();");
		SalesforceRestAPI.createBusinessLead();log.info("Complete running SalesforceRestAPI.createBusinessLead()");
		
	}
	
	
	public static void dataCreation3(String FirstName, String LastName, String Email) {

		SalesforceRestAPI.APIConnection();log.info("Complete running SalesforceRestAPI.APIConnection()");
		SalesforceRestAPI.createRetailRecord_ExternalData(FirstName, LastName, Email);log.info("Complete running SalesforceRestAPI.createRetailRecord()");
		SalesforceRestAPI.updateRetailRecord();log.info("Complete running SalesforceRestAPI.updateRetailRecord()");
		SalesforceRestAPI.updateBrancAccountRelationship();log.info("Complete running SalesforceRestAPI.updateBrancAccountRelationship();");
		
	}
	
	public static void dataCreation4(String firstName, String lastName) throws InterruptedException {

		SalesforceRestAPI.createBranchOpp(firstName, lastName);log.info("SalesforceRestAPI.createBranchOpp()");

	}
	
	
	public static void dataCreation5(String firstName, String lastName) throws InterruptedException {

		SalesforceRestAPI.createBranchOpp_household(firstName, lastName);log.info("SalesforceRestAPI.createBranchOpp()");
		driver.navigate().refresh();
	}
	
	
	public static void dataCreation1() {

		SalesforceRestAPI.APIConnection();log.info("Complete running SalesforceRestAPI.APIConnection()");
		SalesforceRestAPI.createRetailRecord();log.info("Complete running SalesforceRestAPI.createRetailRecord()");
		SalesforceRestAPI.updateRetailRecord1();log.info("Complete running SalesforceRestAPI.updateRetailRecord1()");
		SalesforceRestAPI.updateBrancAccountRelationship();log.info("Complete running SalesforceRestAPI.updateBrancAccountRelationship();");
		
	}
	
	public static void dataCreation2() {
		
		/* This method creates retail record, updates record with advisor and set branch Account Relationships. In addition to that, it creates service record and RP, CT< regular accounts.   */

		SalesforceRestAPI.APIConnection();log.info("Complete running SalesforceRestAPI.APIConnection()");
		SalesforceRestAPI.createRetailRecord();log.info("Complete running SalesforceRestAPI.createRetailRecord()");
		SalesforceRestAPI.updateRetailRecord();log.info("Complete running SalesforceRestAPI.updateRetailRecord()");
		SalesforceRestAPI.updateBrancAccountRelationship();log.info("Complete running SalesforceRestAPI.updateBrancAccountRelationship();");
		
		SalesforceRestAPI.createService();log.info("Complete running SalesforceRestAPI.createService()");
	}

	@SuppressWarnings("static-access")
	public static void validateTaskData(int i) throws InterruptedException {
		
		Thread.sleep(10000);
		callTime = prop.getProperty("Call Start Time");
		
		

		try {
			JSONArray task = SalesforceRestAPI.queryTaskObject(i);
			taskId = task.getJSONObject(0).getString("Id");
			taskName = task.getJSONObject(0).getString("AccountId");
			taskCreatedDate = task.getJSONObject(0).getString("Call_Start_Time__c");
			taskDescription = (String) task.getJSONObject(0).get("Description");
			taskCallOutcome = task.getJSONObject(0).getString("Call_Outcome__c");
			taskCallType = task.getJSONObject(0).getString("Call_Type__c");
			taskStatus = task.getJSONObject(0).getString("Status");
			
			if (RetailAccount.userProfile.contains("Field Advisor")|| RetailAccount.userProfile.contains("Field CSM")){}
			else {taskSolutionsDiscussed = task.getJSONObject(0).getString("Solutions_Discussed__c");}
			
			boolean descriptionComparison = hpage.validateDescriptionData(taskDescription, description);
			softAssertion.assertEquals(descriptionComparison, dData , "Task description mismatch");
			softAssertion.assertEquals(taskCallOutcome, Outcome, "Call Outcome mismatch");
			softAssertion.assertEquals(taskCallType, "Call", "Call Type mismatch");
			softAssertion.assertEquals(taskStatus, "Completed", "Call Status mismatch");
		
			if (RetailAccount.userProfile.contains("Field Advisor")|| RetailAccount.userProfile.contains("Field CSM")){
				System.out.println("Task record is----------------------------------------------------- " + taskId + " "
						+ taskName + " " + taskCreatedDate + taskDescription + taskCallOutcome + taskCallType + taskStatus);}
				else {System.out.println("Task record is----------------------------------------------------- " + taskId + " "
						+ taskName + " " + taskCreatedDate + taskDescription + taskCallOutcome + taskCallType + taskStatus +taskSolutionsDiscussed);}
				
			softAssertion.assertAll();
			
			
		} catch (JSONException e) {e.printStackTrace();Assert.fail("Exception is thrown during validation through API");log.fatal("API Validation Failed");}

		
	}

	@SuppressWarnings("static-access")
	public static void validateTaskData1(int i) throws InterruptedException {

		Thread.sleep(15000);	
		callTime = prop.getProperty("Call Start Time");
		


		try {
			JSONArray task = SalesforceRestAPI.queryTaskObject(i);
			taskId = task.getJSONObject(0).getString("Id");
			taskName = task.getJSONObject(0).getString("AccountId");
			taskCreatedDate = task.getJSONObject(0).getString("Call_Start_Time__c");
			taskDescription = (String) task.getJSONObject(0).get("Description");
			taskCallOutcome = task.getJSONObject(0).getString("Call_Outcome__c");
			taskCallType = task.getJSONObject(0).getString("Call_Type__c");
			taskStatus = task.getJSONObject(0).getString("Status");
			
			if (RetailAccount.userProfile.contains("Field Advisor")|| RetailAccount.userProfile.contains("Field CSM")){}
			else {taskSolutionsDiscussed = task.getJSONObject(0).getString("Solutions_Discussed__c");}

			
			boolean descriptionComparison = hpage.validateDescriptionData(taskDescription, description);
			softAssertion.assertEquals(descriptionComparison, dData , "Task description mismatch");
			softAssertion.assertEquals(taskCallOutcome, Outcome, "Call Outcome mismatch");
			softAssertion.assertEquals(taskCallType, "Call", "Call Type mismatch");
			softAssertion.assertEquals(taskStatus, "Completed", "Call Status mismatch");

			System.out.println(taskDescription);
			
			if (RetailAccount.userProfile.contains("Field Advisor")|| RetailAccount.userProfile.contains("Field CSM")){
			System.out.println("Task record is----------------------------------------------------- " + taskId + " "
					+ taskName + " " + taskCreatedDate + taskDescription + taskCallOutcome + taskCallType + taskStatus);}
			else {System.out.println("Task record is----------------------------------------------------- " + taskId + " "
					+ taskName + " " + taskCreatedDate + taskDescription + taskCallOutcome + taskCallType + taskStatus +taskSolutionsDiscussed);}
			
			softAssertion.assertAll();
			
			
		} catch (JSONException e) {e.printStackTrace();Assert.fail("Exception is thrown during validation through API");log.fatal("API Validation Failed");}
	}

	

	@SuppressWarnings("static-access")
	public static void validateTaskData2(int i) throws InterruptedException {

		Thread.sleep(10000);	
		callTime = prop.getProperty("Call Start Time");
		

		try {
			JSONArray task = SalesforceRestAPI.queryTaskObject(i);
			taskId = task.getJSONObject(1).getString("Id");
			taskName = task.getJSONObject(1).getString("AccountId");
			//taskCreatedDate = task.getJSONObject(0).getString("Call_Start_Time__c");
			taskDescription = (String) task.getJSONObject(1).get("Description");
			taskCallOutcome = task.getJSONObject(1).getString("Call_Outcome__c");
			taskCallType = task.getJSONObject(1).getString("Call_Type__c");
			taskStatus = task.getJSONObject(1).getString("Status");
			
			if (RetailAccount.userProfile.contains("Field Advisor")|| RetailAccount.userProfile.contains("Field CSM")){}
			else {taskSolutionsDiscussed = task.getJSONObject(1).getString("Solutions_Discussed__c");}
			
			boolean descriptionComparison = hpage.validateDescriptionData(taskDescription, description);
			softAssertion.assertEquals(descriptionComparison, dData , "Task description mismatch");
			softAssertion.assertEquals(taskCallOutcome, Outcome, "Call Outcome mismatch");
			softAssertion.assertEquals(taskCallType, "Call", "Call Type mismatch");
			softAssertion.assertEquals(taskStatus, "Completed", "Call Status mismatch");

			System.out.println(taskDescription);

			if (RetailAccount.userProfile.contains("Field Advisor")|| RetailAccount.userProfile.contains("Field CSM")){
			System.out.println("Task record is----------------------------------------------------- " + taskId + " "
					+ taskName + " " + taskCreatedDate + taskDescription + taskCallOutcome + taskCallType + taskStatus);}
			else {System.out.println("Task record is----------------------------------------------------- " + taskId + " "
					+ taskName + " " + taskCreatedDate + taskDescription + taskCallOutcome + taskCallType + taskStatus +taskSolutionsDiscussed);}
			
			softAssertion.assertAll();
			
		} catch (JSONException e) {e.printStackTrace();Assert.fail("Exception is thrown during validation through API");log.fatal("API Validation Failed");}

	}

	
	

	@SuppressWarnings("static-access")
	public static void validateTaskData3(int i) throws InterruptedException {

		Thread.sleep(15000);	
		callTime = prop.getProperty("Call Start Time");


		try {
			JSONArray task = SalesforceRestAPI.queryTaskObject(i);
			taskId = task.getJSONObject(0).getString("Id");
			taskName = task.getJSONObject(0).getString("AccountId");
			taskCreatedDate = task.getJSONObject(0).getString("Call_Start_Time__c");
			taskDescription = (String) task.getJSONObject(0).get("Description");
			taskCallOutcome = task.getJSONObject(0).getString("Call_Outcome__c");
			//taskCallType = task.getJSONObject(0).getString("Call_Type__c");
			taskStatus = task.getJSONObject(0).getString("Status");
			
			if (RetailAccount.userProfile.contains("Field Advisor")|| RetailAccount.userProfile.contains("Field CSM")){}
			else {taskSolutionsDiscussed = task.getJSONObject(0).getString("Solutions_Discussed__c");}

			
			boolean descriptionComparison = hpage.validateDescriptionData(taskDescription, description);
			softAssertion.assertEquals(descriptionComparison, dData , "Task description mismatch");
			softAssertion.assertEquals(taskCallOutcome, Outcome, "Call Outcome mismatch");
			//softAssertion.assertEquals(taskCallType, "Call", "Call Type mismatch");
			softAssertion.assertEquals(taskStatus, "Completed", "Call Status mismatch");

			System.out.println(taskDescription);
			
			if (RetailAccount.userProfile.contains("Field Advisor")|| RetailAccount.userProfile.contains("Field CSM")){
			System.out.println("Task record is----------------------------------------------------- " + taskId + " "
					+ taskName + " " + taskCreatedDate + taskDescription + taskCallOutcome +  taskStatus);}
			else {System.out.println("Task record is----------------------------------------------------- " + taskId + " "
					+ taskName + " " + taskCreatedDate + taskDescription + taskCallOutcome +  taskStatus +taskSolutionsDiscussed);}
			
			softAssertion.assertAll();
			
			
		} catch (JSONException e) {e.printStackTrace();Assert.fail("Exception is thrown during validation through API");log.fatal("API Validation Failed");}
		}

	
	
	
	
	
	@SuppressWarnings("static-access")
	public static void validateTaskScheduleData(int i) throws InterruptedException, ParseException {

		Thread.sleep(5000);
		

		
		try {
			JSONArray task = SalesforceRestAPI.queryTaskObject(i);
			taskId = task.getJSONObject(1).getString("Id");
			taskName = task.getJSONObject(1).getString("AccountId");
			taskCreatedDate = task.getJSONObject(1).getString("Call_Start_Time__c");
			taskDescription = (String) task.getJSONObject(1).get("Description");
			taskCallOutcome = task.getJSONObject(1).getString("Call_Outcome__c");
			taskStatus = task.getJSONObject(1).getString("Status");
			taskActivityDate = task.getJSONObject(0).getString("ActivityDate");
			
			if (RetailAccount.userProfile.contains("Field Advisor")|| RetailAccount.userProfile.contains("Field CSM")){}
			else {taskSolutionsDiscussed = task.getJSONObject(1).getString("Solutions_Discussed__c");}
			
			boolean descriptionComparison = hpage.validateDescriptionData(taskDescription, description);
			softAssertion.assertEquals(descriptionComparison, dData , "Task description mismatch");
			softAssertion.assertEquals(taskCallOutcome, "Reached", "Call Outcome mismatch");
			softAssertion.assertEquals(taskStatus, "Completed", "Call Status mismatch");
			softAssertion.assertEquals(taskActivityDate, TestUtil.changeDateFormat(enteredDate), "Entered Date mismatch");

			System.out.println(taskDescription);
			
			if (RetailAccount.userProfile.contains("Field Advisor")|| RetailAccount.userProfile.contains("Field CSM")){
			System.out.println("Task record is----------------------------------------------------- " + taskId + " "
					+ taskName + " " + taskCreatedDate + taskDescription + taskCallOutcome + taskCallType + taskStatus);}
			else {System.out.println("Task record is----------------------------------------------------- " + taskId + " "
					+ taskName + " " + taskCreatedDate + taskDescription + taskCallOutcome + taskCallType + taskStatus
					+ taskSolutionsDiscussed);}
			
			softAssertion.assertAll();
		
		} catch (JSONException e) {e.printStackTrace();Assert.fail("Exception is thrown during validation through API");log.fatal("API Validation Failed");}

	}

	
	
	
	
}
