package com.crm.qa.pages;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.base.TestBase;



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
	
	//String userEmail, mobPhone, compName,region, industry, startDate, roundofFund,  ldSource, refBy, tcpSeg, prdofInterest, relationalDateSVB,comment;
	//String capitalRaised;
	//BigDecimal capRaised;
	
	Map<String, String> leadData  = new HashMap<String, String>();

	
	public  LeadPage() {PageFactory.initElements(driver, this);	}
	public String verifyLeadPageTitle(){return driver.getTitle();}
	
	
	
	public void openLead() throws InterruptedException{
		
		driver.navigate().to("https://svb--qa.lightning.force.com/lightning/r/Lead/00Q5500000BkliREAR/view");	
	}
	
	
	public Map<String, String>  readLeadValues() throws InterruptedException{
		
		/* 
		 userEmail = email.getText().substring(6).trim();
		 compName = companyName.getText();
		 relationalDateSVB = stratDateWithSVB.getText();
		 region= regionName.getText(); 
		 industry= industrySector.getText();
		 startDate= stratDateWithSVB.getText();
		 roundofFund= roundOfFunding.getText();
		 ldSource= leadSource.getText();
		 refBy= referredBy.getText();
		 tcpSeg= tcpSegment.getText();
		 prdofInterest= productOfInterest.getText();
		 comment= commentary.getText();
		 capitalRaised= insitutionalCapitalRaised.getText().replaceAll("[$,]","").replaceAll("0*$", "").replaceAll("\\.$", "");
		 */
		
		
		 leadData.put("userEmail" , email.getText().substring(6).trim());
		 leadData.put("companyName", companyName.getText());
		 leadData.put("relationalStratDateSVB", stratDateWithSVB.getText());
		 leadData.put("region", regionName.getText()); 
		 leadData.put("industrySector", industrySector.getText());
		 leadData.put("roundOfFunding", roundOfFunding.getText());
		 leadData.put("leaddSource", leadSource.getText());
		 leadData.put("referredBy", referredBy.getText());
		 leadData.put("tcpSegment", tcpSegment.getText());
		 leadData.put("productofInterests", productOfInterest.getText());
		 leadData.put("commentary", commentary.getText());
		 leadData.put("insitutionalCapitalRaised", insitutionalCapitalRaised.getText().replaceAll("[$,]","").replaceAll("0*$", "").replaceAll("\\.$", ""));
		
		return leadData;	
	}
	
	

	public void createClient() throws InterruptedException{
	
	}
	
	
	
	public void updateClientDetailPage() throws InterruptedException{
		
		

	}
		

	public void newClientDetailPage() throws InterruptedException{
		

	}
	
	
}
