package com.crm.qa.pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.util.*;
import com.crm.qa.base.TestBase;



public class HomePage extends TestBase {

	
	
	@FindBy(xpath = "//div[contains(text(),'User Detail')]")
	static
	WebElement advisorLink;

	@FindBy(xpath = "//span[contains(text(),'Details')]")
	static
	WebElement advisorDetails;
	
	@FindBy(xpath = "//iframe[contains (@name,('vfFrameId'))]")
	static
	WebElement iframe;
	
	
	@FindBy(xpath = "//input[@placeholder= 'Search Salesforce']")
	WebElement searchInput;
	
	@FindBy(xpath = "(//span[contains(text(),'Account Name')]/ancestor::thead/following-sibling::tbody/tr//th//a)[1]")
	WebElement selectUser;
	

	@FindBy(xpath = "//*[@id='topButtonRow']/input[4]")
	static
	WebElement advisorLogin;
	
	@FindBy(xpath = "//span[@class='title'][contains(text(),'Details')]")
	WebElement detailonDetailPage;
	

	@FindBy(xpath = "(//span[contains(text(),'Home')])[1]")
	WebElement homeLink;
	

	static
	@FindBy(xpath="//span[(text()= 'Profile')]/../..//a[contains(@href,'/lightning/r/')]")
	WebElement usrProfile;

	@FindBy(xpath="//div[text()='Person Client']")
	WebElement clientHomePage;

	
	
	
//************************************************************************************************************************	
	
	
	public static String userProfile;
	public static String AccountURL;
	public static String SpouseAccountURL;
	
	

	public HomePage() {	PageFactory.initElements(driver, this);	}
	public String verifyHomePageTitle(){return driver.getTitle();}
		

	
	public void navigateToMultipleUser(String role, String advisorId) throws InterruptedException{
		
		
		if (role.equalsIgnoreCase("advisor")){
		
			driver.navigate().to(prop.getProperty("SFDC_TestEnv")+"/lightning/r/User/"+advisorId+"/view");
			
			TestUtil.waitUntilElementVisible(advisorLink);
			TestUtil.clickElement(advisorLink);
			
			TestUtil.waitUntilElementVisible(iframe);
			driver.switchTo().frame(iframe);
			Thread.sleep(3000);
			
			TestUtil.waitforElementVisible(advisorLogin);
			TestUtil.clickElement(advisorLogin);Thread.sleep(3000);
			driver.switchTo().defaultContent();
			
	
			
			
		} else if (role.equalsIgnoreCase("admin")){
			
			driver.navigate().to(prop.getProperty("SFDC_TestEnv")+"/lightning/r/User/"+advisorId+"/view");
			
			
		}

	
	}

	
	
	/*
	
	public static void navigateToUser(String role) throws InterruptedException{
	
	
		if (role.equalsIgnoreCase("advisor")){
	
			String advisorId = prop.getProperty("advisorsfdcId1");
				
			driver.navigate().to(prop.getProperty("SFDC_TestEnv")+"/lightning/r/User/"+advisorId+"/view");
			userProfile  = RetailAccount.userProfile = usrProfile.getText();
			advisorLink.click();Thread.sleep(5000);

			WebElement iframe = driver.findElement(By.xpath("//iframe[contains (@name,('vfFrameId'))]"));Thread.sleep(3000);
			driver.switchTo().frame(iframe);Thread.sleep(5000);
			
			advisorLogin.click();Thread.sleep(5000);
			driver.switchTo().defaultContent();Thread.sleep(5000);
			driver.navigate().refresh();Thread.sleep(5000);}
		
		   
	
		else if (role.equalsIgnoreCase("nonadvisor")){}
		
	}

	*/
	
	
	
/*	
	
	public  void navigateToRetailuser(String userType) throws InterruptedException{
		
		//String sfdcId = new String();
		//sfdcId = SalesforceRestAPI.getHashMapData()[0];
		
		String sfdcId = "0011100001w7tyDAAQ";
		
		String url = "https://svb-pbwm--qa.lightning.force.com/lightning/r/Account/"+sfdcId+"/view";
		driver.navigate().to(url);
		TestUtil.waitUntilElementVisible(clientHomePage);
		
		
		if (userType.equalsIgnoreCase(("household"))){
			
		//Keep this code and use it if Household link is not working. Don't forget to comment out householdPage.gotoHousehold();
		//sfdcId = sfdcId.replace(sfdcId.charAt(14), (char)(sfdcId.charAt(14) + 1));
		
	}
	
}
		
	
	public void navigateToSponsoredClient(String sfdcId) throws InterruptedException{
		
		String url = "https://fei--fscfull.lightning.force.com/lightning/r/Account/"+sfdcId+"/view";
		driver.navigate().to(url);
		Thread.sleep(5000);
	}
	
	
	public void navigateToSpouseuser() throws InterruptedException{
		
	
		String spouseId = SalesforceRestAPI.objMap.get("spousesfdcId");
		String url = "https://fei--fscfull.lightning.force.com/lightning/r/Account/"+spouseId+"/view";
		
		SalesforceRestAPI.objMap.put("sfdcId", spouseId); 
		driver.navigate().to(url);
		
	}


	public void navigateToClientViewList(String listView) throws InterruptedException{
		
		String url = "https://svb-pbwm--qa.lightning.force.com/lightning/o/Account/list?filterName="+listView;
		driver.navigate().to(url);
		
	}
	
	
	
*/	
	public boolean validateDescriptionData(String taskDescription, String description) {
		
		boolean desccomp = true;
		
		if (taskDescription.contains(description)){System.out.print("Description data matched"); desccomp = true;}
		else {desccomp = false;}
		
		return desccomp;
			
		
	}
	



public String getAccountURL() {return HomePage.AccountURL;}
public String getSpouseAccountURL() {return HomePage.SpouseAccountURL;}


}
