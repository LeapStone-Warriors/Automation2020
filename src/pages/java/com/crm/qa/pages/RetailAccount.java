package com.crm.qa.pages;


import java.io.IOException;
import java.io.OutputStream;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;



public class RetailAccount extends TestBase {
	
	
	public RetailAccount() {
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "(//a[contains(text(),'Log out')])")
	WebElement LoggedUser;
			
	@FindBy(xpath = "//div[contains(text(),'Comments')]")
	WebElement comments;
	
	@FindBy(xpath="//lightning-icon[contains (@class, ('globalCreateButton'))]//*[contains(@class,'slds-icon slds-icon_x-small')]")
	WebElement globalButton;
	
	
	@FindBy(xpath="//span[contains(text(),'Create Retail Account')]")
	WebElement createRetail;
		
	@FindBy(xpath="//span[contains(text(),'Account Name')]/../following-sibling::div//input")
	WebElement AccountName;
	
	@FindBy(xpath="//span[contains(text(),'First Name')]/../following-sibling::input")
	WebElement firstName;
	
	@FindBy(xpath="//span[contains(text(),'Last Name')]/../following-sibling::input")
	WebElement lastName;

	@FindBy(xpath="(//a[contains(text(),'Not Set')])[1]")
	WebElement selectGender;
	
	@FindBy(xpath="//div[contains (@class, 'datetime')]//input[contains (@class, 'input')]")
	WebElement dateofBirth;
	
	@FindBy(xpath="//div[contains (@class, 'Phone')]//input[contains (@class, 'input')]")
	WebElement phone;
	
	@FindBy(xpath="//span[contains(text(),'Client Provided Email')]/../following-sibling::input")
	WebElement email;
	
	@FindBy(xpath="//span[contains(text(),'Street')]/../following-sibling::textarea")
	WebElement street;
	
	@FindBy(xpath="//span[contains(text(),'City')]/../following-sibling::input")
	WebElement city;
	
	@FindBy(xpath="//span[contains(text(),'State/Province')]/../following-sibling::input")
	WebElement state;
	
	@FindBy(xpath="//span[contains(text(),'Zip/Postal Code')]/../following-sibling::input")
	WebElement zip;
	
	@FindBy(xpath="//span[contains(text(),'Country')]/../following-sibling::input")
	WebElement country;
	
	@FindBy(xpath="//div[@class='bottomBarRight slds-col--bump-left']//button[@type='button']")
	WebElement saveButton;
	
	@FindBy(xpath = "//ul[@role='presentation']//li//a[@title='Female']")
	WebElement gender;
	
	@FindBy(xpath = "//input[@placeholder= 'Search Salesforce']")
	WebElement searchInput;
	
	@FindBy(xpath = "//div[contains (text() , 'give up yet')]")
	WebElement msgDontGiveUpYet;
	
	@FindBy(xpath = "//span[contains(text(),'Preferred Name')]/../..//div//span[contains(@class,('OutputText'))]")
	WebElement username;
	
	@FindBy(xpath="//span [text () = 'Plan Sponsor Name']/parent::div/following-sibling::div/child::span")
	WebElement retailText;
	
	@FindBy(xpath="//span[contains(text(),'Plan Sponsor')]/..//a")
	WebElement retailText_inbAdvs;
	
	
	@FindBy(xpath="//span [text () = 'User Number']/parent::div/following-sibling::div/child::span/span")
	WebElement userId;
	
	@FindBy(xpath="(//span[contains(text(),'User Number')]/..//div[contains (@class,('slds-truncate'))]/span)[last()]")
	WebElement userId_inbdAdvsr;
	
	
	@FindBy(xpath="//span[contains (@class, 'test-id')][contains(text(),'User Number')]")
	WebElement userNumberTitle;
	
	@FindBy(xpath="//ul[@role='presentation']//li//a[@title='Branch Retail']")
	WebElement retailType2;
	
	@FindBy(xpath="//ul[@role='presentation']//li//a[@title='Retail']")
	WebElement retailType1;
	
	
	@FindBy(xpath="(//a[contains (@class, ('tabHeader'))]//span[contains (text(), ('Household'))])[last()]")
	WebElement householdTab;
	
	@FindBy(xpath="//a[contains (@class, ('tabHeader'))][contains (@data-tab-name, ('customTab8'))]")
	WebElement communicationTab;
	
	@FindBy(xpath="(//div[contains(@class,'col main-col slds-size--6-of-12')]//li)[2]")
	WebElement householdTablink;
	
	@FindBy(xpath="//button[contains(text(),'Add Co-Client')]")
	WebElement addCoclient;
	
	@FindBy(xpath="//span[contains(text(),'Add Household Member')]")
	WebElement householdMember;
	
	@FindBy(xpath="//div[contains(@class, 'modal-body')]//span[@class='slds-checkbox_faux']")
	WebElement createNewAccount;
	
	@FindBy(xpath="//div[contains(@class, 'modal-body')]//button[contains(@class, 'slds-button')]")
	WebElement nextButton;
		
	@FindBy(xpath="//button[contains(text(),'Previous')]/following-sibling::button[contains(text(),'Next')]")
	WebElement nextButton1;
		
	@FindBy(xpath="//div[contains(@class, 'modal-body')]//input[contains(@name,'Partner_Spouse_First_Name')]")
	WebElement spouseFirstName;
		
	@FindBy(xpath="//div[contains(@class, 'modal-body')]//input[contains(@name,'Partner_Spouse_Last_Name')]")
	WebElement spouseLastName;
	
	@FindBy(xpath="//div[contains(@class, 'modal-body')]//div[contains(@class,'slds-form-element')]//input[@name ='DOB']")
	WebElement spouseDOB;
	
	@FindBy(xpath="//div[contains(text(),'Female')]")
	WebElement genderSelection;
		
	@FindBy(xpath="(//a[contains(text(),'Not Set')])[1]")
	WebElement genderdropdown;
		
	@FindBy(xpath="//label[contains(@class, 'slds-radio__label')]//div[contains(text(), 'Spouse')]")
	WebElement relationshipSelection;
		
	@FindBy(xpath="//button[contains(@type,'button')][contains(text(),'Close All')]")
	WebElement closeAll;
	
	@FindBy(xpath="//a[contains(text(),'Co-Client')]")
	WebElement coClientButton;
	
	@FindBy(xpath="//input[contains (@title, 'Search People')]")
	WebElement assignedPlanner;
	
	@FindBy(xpath="(//a[contains (@role, 'option')])[1]")
	WebElement assignedPlannerOption;
	
	@FindBy(xpath="(//a[contains(@class,'forceOutputLookup')])[1]")
	WebElement plannerAdvisor;
	
	@FindBy(xpath="//div[contains (@class, 'slds-lookup__result-text')]")
	WebElement assignedPlannerdropdown;
	
	@FindBy(xpath="//input[contains (@placeholder, 'Search People...')]")
	WebElement searchpeopleDropDown;
	
	@FindBy(xpath="//span[contains(@title, 'My Open Opportunities')]")
	WebElement myOpenOptty;
	

	
	
	JavascriptExecutor jse2 = (JavascriptExecutor)driver;
	

	

	String LoggedinUser;
	String AccountId;
	String Accountfor;
	
	public static String uid;
	public static String accountId;
	public static String aname;
	public static String fname; 
	public static String lname ;
	public static String spousefname;
	public static String spouselname;
	public static String userProfile;
	
	
	
	
	String textInsideUserNumber;
	String planSponsor;
	

	
	OutputStream output = null;
	SalesforceRestAPI restapi = new SalesforceRestAPI();

	
	public void createRetailuser(int i) throws Exception {
		
		if (i==2){LoggedinUser = LoggedUser.getText().substring(11);}
		
	
		
		Thread.sleep(15000);
		globalButton.click(); 
		
		Thread.sleep(5000);
		createRetail.click();
		
		firstName.sendKeys(SalesforceRestAPI.fname);
		lastName.sendKeys(SalesforceRestAPI.lname);
		
		genderdropdown.click();
		gender.click();
		
		phone.sendKeys("5554564564");
		email.sendKeys("test123@test123.com");
		dateofBirth.sendKeys("10/16/1948");
		street.sendKeys("1050 Enterprise way");
		city.sendKeys("Sunnyvale");
		zip.sendKeys("94089");
		state.sendKeys("CA");
		country.sendKeys("USA");
	
		
		
		if (i==2){assignedPlanner.sendKeys(LoggedinUser);jse2.executeScript("arguments[0].click()", assignedPlannerdropdown);}
		else if (i==1) {searchpeopleDropDown.click();assignedPlannerOption.click();}
		
	 	//If you logged in as Advisor, Type will be automatically selected for you
	  	if (i == 1) {driver.findElement(By.xpath("//a[@class='select'][contains(text(),'Retail')]")).click();retailType1.click();}
	 
	 	
	  	saveButton.click();
		prop.put("retailuser", "password");
		Thread.sleep(10000);
		System.out.println(aname);
		
		
		prop.setProperty("retailuser", aname);
		Thread.sleep(3000);
		
		try {
			prop.store(output, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//Method to search for an Account using global Search. 
	public void validRetailuser() throws InterruptedException {
		
		Thread.sleep(3000);
		searchInput.sendKeys(aname);
		
		Thread.sleep(5000);
		searchInput.sendKeys(Keys.RETURN);
		
		Thread.sleep(10000);
		
		
		if (driver.findElements(By.xpath("//div[contains (text() , 'give up yet')]")).size() != 0)
		{ driver.navigate().refresh(); Thread.sleep(3000);}
		
		
		driver.findElement(By.xpath("(//a[contains(text(),'"+aname+"')])[1]")).click();
		
		Thread.sleep(10000);
		String AccountName = username.getText();
		
		driver.navigate().refresh();
		
		Thread.sleep(5000);
		
		
		
		if (userProfile.contains("Inbound Advisor")){textInsideUserNumber = userId_inbdAdvsr.getText(); }
		else {textInsideUserNumber = userId.getText();}
		
		
		// Check whether input field is blank
		if(textInsideUserNumber.isEmpty()){System.out.println("User Number is empty");}
		softAssertion.assertEquals(AccountName, prop.getProperty("retailuser"), "Account Name mismatch in open task");
		
		if (userProfile.contains("Inbound Advisor")){softAssertion.assertEquals(retailText_inbAdvs.getText(), "Retail");}
		else {softAssertion.assertEquals(retailText.getText(), "");}
	
	}
	
	
	
	
	public void searchAccount() throws InterruptedException{
		
		String Text = driver.findElement(By.xpath("//div[contains (@class, ('slds-context-bar'))]/span[contains (@class, ('slds'))]")).getText();
		
		if (Text.contains("Advisor")){ changeNavigationMenu("Accounts"); Thread.sleep(2000);}
		else if (Text.contains("Sales")){ driver.findElement(By.xpath("//a[contains(@href, ('Account'))]")).click(); Thread.sleep(2000);}
		
		
		driver.findElement(By.xpath("(//a[contains (@title, ('" +aname+ "'))][contains (@class, ('slds-truncate'))])[1]")).click();
		Thread.sleep(2000);
		
		
		if (userProfile.contains("Inbound Advisor")){textInsideUserNumber = userId_inbdAdvsr.getText();}
		else {textInsideUserNumber = userId.getText();}
		
		// Check whether input field is blank
		if(textInsideUserNumber.isEmpty()){System.out.println("User Number is empty");}
					
		System.out.println(textInsideUserNumber);
		System.out.println(prop.getProperty("retailuser"));
		softAssertion.assertEquals(AccountName, prop.getProperty("retailuser"), "Account Name mismatch in open task");
	
		
		if (userProfile.contains("Inbound Advisor")){softAssertion.assertEquals(retailText_inbAdvs.getText(), "Retail");}
		else {softAssertion.assertEquals(retailText.getText(), "");}
		
		Thread.sleep(2000);
	
	}
	
	public static void changeNavigationMenu(String Menu) throws InterruptedException{
		
		driver.findElement(By.xpath("//button[contains (@title, ('Show Navigation Menu'))]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains (@class,('menuLabel slds-listbox'))][contains(text(),'" +Menu+ "')]")).click();
	}
	
	
	public void clickAccountviaAccountScreen() throws InterruptedException{ 
		
		Thread.sleep(2000);
		changeNavigationMenu("Accounts");
		Thread.sleep(3000);
		TestUtil.closeAllOpenTabs(driver);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//a[contains (@title, ('" +aname+ "'))][contains (@class, ('slds-truncate'))])[1]")).click();
		
		Thread.sleep(3000);
		
	}
	
	

	public void clickHouseholdviaAccountScreen() throws InterruptedException{ 
		
		Thread.sleep(2000);
		changeNavigationMenu("Accounts");
		Thread.sleep(3000);
		TestUtil.closeAllOpenTabs(driver);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//a[contains (@title, ('" +aname+ "'))][contains (@class, ('slds-truncate'))])[1]")).click();
		
		Thread.sleep(3000);
		
	}
	
	
	
	
	public static String getAccountId() throws InterruptedException{
		
		String accountUrl = driver.getCurrentUrl();
		String []url = accountUrl.split("/");
		String accountId = url[6];
		
		return accountId;
	}
	
	
	public static String generateRndmNum(String range) {
	    
		 int intrange = Integer.parseInt(range);
		 Random rand = new Random(System.currentTimeMillis());
		 int num = rand.nextInt(intrange);
		 String snum = Integer.toString(num); 
		 
		  return snum;
		
		}	

	
	public void set_uid(String userid) {uid = userid;}
	public void set_fname(String firstname) {fname = firstname;}
	public void set_lname(String lastname) {lname = lastname;}
	public void set_aname(String accountname) {aname = accountname;}
	public void set_accountId(String AccountId) {accountId = AccountId;}
	public void set_sposuefname(String sfirstname) {spousefname = sfirstname;}
	public void set_spouselname(String slastname) {spouselname = slastname;}
	
	public String get_uid(){return uid;}
	public String get_accountId(){return accountId;}
	public String get_spousefname(){return spousefname;}
	public String get_spouselname(){return spouselname;}

	
	
}

