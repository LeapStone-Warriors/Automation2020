package com.crm.qa.pages;



import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.salesforce.api.*;



public class LeadPage extends TestBase {

	
	//**************************Highlight Panel**********************************************************************************************	
	@FindBy(xpath = "//lightning-formatted-email[1]/a[1]")
	WebElement email;
	
	@FindBy(xpath = "//a[contains(@href, 'tel:')]")
	WebElement mobilePhone;
	
	@FindBy(xpath = "//div[contains(@class,'fieldlabel')][text()='Company']/..//div[contains(@class,'fieldvalue')]")
	WebElement companyName;
	
	@FindBy(xpath = "//div[contains(@class,'fieldlabel')][text()='Region']/..//div[contains(@class,'fieldvalue')]")
	WebElement regionName;
	
	@FindBy(xpath = "//div[contains(@class,'fieldlabel')][text()='Industry Sector']/..//div[contains(@class,'fieldvalue')]")
	WebElement industrySector;
	
	@FindBy(xpath = "//div[contains(text(),'Relationship Start Date with SVB')]/../..//lightning-formatted-date-time")
	WebElement stratDateWithSVB;
	
	@FindBy(xpath = "//div[contains(text(),'Round of Funding')]/..//div[contains(@class,'fieldvalue')]")
	WebElement roundOfFunding;
	
	@FindBy(xpath = "//div[contains(text(),'Institutional Capital Raised')]/..//lightning-formatted-number")
	WebElement insitutionalCapitalRaised;
	
	@FindBy(xpath = "//div[contains(text(),'Lead Source')]/..//div[contains(@class,'fieldvalue')]")
	WebElement leadSource;
	
	@FindBy(xpath = "//div[contains(text(),'Referred By')]/..//div[contains(@class,'fieldvalue')]")
	WebElement referredBy;
	
	@FindBy(xpath = "//div[contains(text(),'TCP Segment')]/..//div[contains(@class,'fieldvalue')]")
	WebElement tcpSegment;
	
	@FindBy(xpath = "//div[contains(text(),'Product(s) of Interest')]/..//div[contains(@class,'fieldvalue')]")
	WebElement productOfInterest;
	
	@FindBy(xpath = "//div[contains(text(),'Commentary')]/..//div[contains(@class,'fieldvalue')]")
	WebElement commentary;
	
	
	//************************************************************************************************************************	
	
	
	
	Map<String, String> readleadDataUI  = new HashMap<String, String>();
	JSONObject createleadDataAPI = new JSONObject();
	SalesforceCRUDOperationAPI leadAPIOps = new SalesforceCRUDOperationAPI();
	
	
	
	
	public  LeadPage() {PageFactory.initElements(driver, this);	}
	public String verifyLeadPageTitle(){return driver.getTitle();}
	
	public void openLead(String leadID) throws InterruptedException{driver.navigate().to("https://svb--qa.lightning.force.com/lightning/r/Lead/"+leadID+"/view");}
	
	
	public Map<String, String>  readLeadValues() throws Exception{
		
		
		 readleadDataUI.put("userEmail" , email.getText().substring(6).trim());
		 readleadDataUI.put("companyName", companyName.getText());
		 readleadDataUI.put("relationalStratDateSVB", stratDateWithSVB.getText());
		 readleadDataUI.put("region", regionName.getText()); 
		 readleadDataUI.put("industrySector", industrySector.getText());
		 readleadDataUI.put("roundOfFunding", roundOfFunding.getText());
		 readleadDataUI.put("leaddSource", leadSource.getText());
		 readleadDataUI.put("referredBy", referredBy.getText());
		 readleadDataUI.put("tcpSegment", tcpSegment.getText());
		 readleadDataUI.put("productofInterests", productOfInterest.getText());
		 readleadDataUI.put("commentary", commentary.getText());
		 readleadDataUI.put("insitutionalCapitalRaised", insitutionalCapitalRaised.getText().replaceAll("[$,]","").replaceAll("0*$", "").replaceAll("\\.$", ""));
		
		return readleadDataUI;	
	}
	
	

	public String createLeadviaAPI() throws Exception{
	
		createleadDataAPI = leadAPIOps.createLead();
		return  createleadDataAPI.getString("leadSFDCId");
		
	}
	
	
	
	public void createLeadviaAPIandValidatethroughUI() throws Exception{
		
		String leadID = createLeadviaAPI();
		openLead(leadID);
		System.out.println(readLeadValues());

	}
		

	
	
	
}
