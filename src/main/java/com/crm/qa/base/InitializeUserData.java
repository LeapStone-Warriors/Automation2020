package com.crm.qa.base;


import java.util.HashMap;
import java.util.Map;
import com.crm.qa.base.TestBase;

import com.crm.qa.pages.SalesforceRestAPI;
import com.crm.qa.pages.RetailAccount;


public class InitializeUserData extends TestBase{



	SalesforceRestAPI sfdcAPI = new SalesforceRestAPI();

	RetailAccount retailAccount = new RetailAccount();
	
	 String RunningTestCaseonDemand;
	 static String opttyCreated = "No";
	 
	
	@SuppressWarnings("static-access")
	public  void initialize() throws Exception{
		
		
		Map<String, String> Data = new HashMap<String, String>();
		Data = getDetailPageData();
		
		String uid = Data.get("uniqueid");
		
		
		
		
	if (RunningTestCaseonDemand.equalsIgnoreCase("Yes")){}
	else { 	
			sfdcAPI.set_fname("Testf"+ uid);retailAccount.set_fname("Testf"+ uid);
			sfdcAPI.set_lname("Testl"+ uid);retailAccount.set_lname("Testl"+ uid);
	
	
	
	}
		
		sfdcAPI.set_bname("Testb"+ uid);
		
		retailAccount.set_aname(SalesforceRestAPI.fname+" "+SalesforceRestAPI.lname);
		retailAccount.set_sposuefname("spouseTestf"+ uid);
		retailAccount.set_spouselname("spouseTestl"+ uid);
		

		
		sfdcAPI.set_accountId(SalesforceRestAPI.getHashMapData()[0]);retailAccount.set_accountId(SalesforceRestAPI.getHashMapData()[0]);
		
		
	}
	
	public static Map<String, String> getDetailPageData(){
		
		String[] ret = addDaysToCurrentTime(7);
		
		Map<String, String> detailPageData = new HashMap<String, String>();
		    
		detailPageData.put("enteredDate", ret[0]);
		detailPageData.put("verifyDate", ret[1]);
		detailPageData.put("meetingDate", ret[2]);
		detailPageData.put("meetingformattedDate", ret[3]);
		detailPageData.put("uniqueid", ret[4]);
		detailPageData.put("unplannedDate", ret[6]);
		
		return detailPageData;
	
		}

	
	public void setTestCaseonDemandtoYes()	{RunningTestCaseonDemand = "Yes";}
	public void setTestCaseonDemandtoNo()	{RunningTestCaseonDemand = "No";}
	public void setopttyCreatetoYes()		{opttyCreated = "Yes";}
	public static String getOpttyCreated()	{return opttyCreated;}

	
	
}
