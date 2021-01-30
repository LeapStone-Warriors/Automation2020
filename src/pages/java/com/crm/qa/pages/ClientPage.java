package com.crm.qa.pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.util.*;
import com.crm.qa.base.TestBase;



public class ClientPage extends TestBase {

	
	
	@FindBy(xpath = "//div[text()='Person Client']")
	WebElement titlePersonClient;
	
	@FindBy(xpath = "//div[(text()='New')]")
	WebElement btnNewClient;
	
	@FindBy(xpath = "//h2[contains (text(), 'New Client')]")
	WebElement titleClientCreationModal;
	
	//*****Client Creation Modal Page****
	
	@FindBy(xpath = "//span[contains(text(),'Spouse')]/../..//input")
	WebElement txtSpouseDomesticPartnerName;
	
	@FindBy(xpath = "//span[text()='Date of Birth']/../..//div//input")
	WebElement inputDOB;
	
	@FindBy(xpath = "//span[text()='Relationship Start Date with SVB']/../..//div//input")
	WebElement inputRelationshipStartDate;
	
	@FindBy(xpath = "(//span[text()='Company'])[last()]/../..//input")
	WebElement txtCompany;
	
	
	@FindBy(xpath = "//span[contains(text(),'Mailing State')]/../..//div//a")
	WebElement dropdownMailingState;
	
	@FindBy(xpath = "//span[text()='As of Date']/../..//div//input")
	WebElement inputAsofDate;
	
	
	@FindBy(xpath = "(//span[contains(text(),'Banking and Deposits')])[last()]")
	WebElement productBankingDeposit;
	
	@FindBy(xpath = "//button[@title='Move selection to Chosen']")
	WebElement btnToMoveProdcuts;
	

	@FindBy(xpath = "//span[text()='Commentary']/../..//textarea")
	WebElement txtareaCommentary;
	
	@FindBy(xpath = "//span[text()='Salutation']/../..//a")
	WebElement drpdwnSalutation;

	@FindBy(xpath = "(//button[@title='Save'])[last()]")
	WebElement btnSave;
		
	//*****Client Detail Page****
	
	@FindBy(xpath = "//a[@id='detailTab__item']")
	WebElement tabClientDetail;
		
	@FindBy(xpath = "//span[contains(text(),'Employment Status')]/../..//lightning-primitive-icon")
	WebElement editEmploymentstatus;
	
	@FindBy(xpath = "(//span[contains(text(),'Estimated Networth')])[last()]")
	WebElement lblEstimatedNetworth;
	
	@FindBy(xpath = "(//span[contains(text(),'Liquid Assets')])[last()]")
	WebElement lblLiquidAssets;
	
	@FindBy(xpath = "(//span[contains(text(),'Deposit Potential')])[last()]")
	WebElement lblDepositPotential;
	
	@FindBy(xpath = "(//span[contains(text(),'Loan Potential')])[last()]")
	WebElement lblLoanPotential;
	
	@FindBy(xpath = "(//span[contains(text(),'Investment Potential')])[last()]")
	WebElement lblInvestmentPotential;
	
	@FindBy(xpath = "(//span[contains(text(),'Financial Needs Narrative')])[last()]/../..//textarea")
	WebElement txtFinancialNeedsNarrative;
	
	@FindBy(xpath = "(//span[text()='Liquid Assets'])[last()]/../..//a")
	WebElement drpdownLiquidAssets;
	
	@FindBy(xpath = "(//span[text()='Date of Birth'])[last()]/../..//input")
	WebElement inputDateofBirth;
	
	@FindBy(xpath = "(//span[contains(text(),'Home Street')])[last()]/../..//textarea")
	WebElement txtHomeStreet;
	
	@FindBy(xpath = "(//span[contains(text(),'Mailing Street')])[last()]/../..//textarea")
	WebElement txtMailingStreet;
	
	@FindBy(xpath = "(//span[text()='Last Interaction'])[last()]/../..//input")
	WebElement inputLastInteraction;
	
	@FindBy(xpath = "(//span[contains(text(),'Special Handling')])[last()]/../..//textarea")
	WebElement txtSpecialHandling;
	
	@FindBy(xpath = "(//span[contains(text(),'Hobbies')])[last()]/../..//textarea")
	WebElement txtInterestandHobbies;
	
	@FindBy(xpath = "(//span[text()='Referred By'])[last()]/../..//input")
	WebElement inputReferredBy;
	
	@FindBy(xpath = "(//span[text()='PB Team Code'])[last()]/../..//input")
	WebElement inputPBTeamCode;
	
	@FindBy(xpath = "(//a[contains(@href,'lightning/r/')])[last()]")
	WebElement selectPBTeamCode;
	
	@FindBy(xpath = "(//span[text()='CB Team Code'])[last()]/../..//input")
	WebElement inputCBTeamCode;
	
	@FindBy(xpath = "(//a[contains(@href,'lightning/r/')])[last()]")
	WebElement selectCBTeamCode;
	
	
	
	
	//************************************************************************************************************************	
	
	
	public  ClientPage() {	PageFactory.initElements(driver, this);	}
	public String verifyClientPageTitle(){return driver.getTitle();}
	
	
	
	public void clickNewButtonOnClientViewList() throws InterruptedException{
		
		TestUtil.clickElement(btnNewClient);
		TestUtil.waitUntilElementVisible(titleClientCreationModal);
			
	}
	
	
	public void createClient() throws InterruptedException{
	
		drpdwnSalutation.click();drpdwnSalutation.sendKeys("Mr.", Keys.ENTER);
		TestUtil.enterTextinField("First Name", "AutoFirstName");
		TestUtil.enterTextinField("Middle Name", "S.");
		TestUtil.enterTextinField("Last Name", TestUtil.generateString()+"Imran");
		TestUtil.enterTextinField("Suffix", "III");
		TestUtil.enterTextinField("Preferred Name", "Automation01");
		TestUtil.dropdownOption("Tax ID Type", "SSN");
		TestUtil.enterTextinField("Tax ID Number", "1234");
		TestUtil.dropdownOption("Visa Details", "Student");
		TestUtil.enterTextinField("Email", "test"+"@email.com");
		TestUtil.enterTextinField("Mobile Phone", "9195568901");
		TestUtil.dropdownOption("Employment Status","Employed");
		TestUtil.enterTextinField("Company", "Sony");
		TestUtil.enterTextinField("Title", "Manager");
		TestUtil.dropdownOption("Region", "Southeast");
		productBankingDeposit.click();btnToMoveProdcuts.click();
		TestUtil.enterTextinField("Commentary", "AuomtateTesting Commentary");
		TestUtil.dropdownOption("Lead Source", "COI");
		TestUtil.enterTextinField("Referred By", "Tom Cruise");
		TestUtil.clickElement(btnSave);
	}
	
	
	
	public void updateClientDetailPage() throws InterruptedException{
		
		TestUtil.clickElement(tabClientDetail);Thread.sleep(3000);
		TestUtil.scrollintoView(tabClientDetail);
		TestUtil.clickElement(editEmploymentstatus);

		//****Employment Information****
		TestUtil.dropdownOption("Employment Status", "Employed");
		inputRelationshipStartDate.sendKeys("07/11/2020", Keys.TAB);
		TestUtil.dropdownOption("Round Of Funding", "Series A");
		TestUtil.enterTextinField("Company Website", "www.sony.com");
		TestUtil.dropdownOption("Industry Sector", "Life Science - Biotech");
		TestUtil.enterTextinField("Institutional Capital Raised", "23456");
		
		//****Financial Information****
		inputAsofDate.sendKeys("07/11/2020", Keys.TAB);
		TestUtil.dropdownOption("Estimated Networth", "$500K - 1M");
		TestUtil.dropdownOption("TCP Segment", "Bronze (TCP < $2M)");
		TestUtil.dropdownOption("Liquid Assets", "$500K - 1M");
		TestUtil.dropdownOption("TCP Potential", "Low Potential (Lo-Po)");
		TestUtil.dropdownOption("Deposit Potential", "$1M - 2M");
		TestUtil.dropdownOption("Annual Income", "$30,001 - 50,000");
		TestUtil.dropdownOption("Loan Potential", "$500K - 1M");
		TestUtil.enterTextinField("Primary Competitor", "Samsung");
		TestUtil.dropdownOption("Investment Potential", "$4M - 10M");
		txtFinancialNeedsNarrative.sendKeys("Financial Needs Narrative Automation");
		
		//****Profile Information****
		inputDateofBirth.sendKeys("07/23/1965", Keys.TAB);
		TestUtil.dropdownOption("Gender","Agender");
		TestUtil.dropdownOption("Marital Status", "Married");
		TestUtil.dropdownOption("Client Segment", "VC Investor");
		TestUtil.enterTextinField("Spouse", "SpouseAutomation");
		TestUtil.enterTextinField("Dependents", "2");
		TestUtil.enterTextinField("Language", "English");
		
		//***Contact Information****
		
		TestUtil.dropdownOption("Preferred Contact Method", "Email");
		txtHomeStreet.sendKeys("1234 AutoTest Drive");
		TestUtil.enterTextinField("Home City", "Chandler");
		TestUtil.dropdownOption("Home State", "Arizona");
		TestUtil.enterTextinField("Home Zip","85249");
		TestUtil.enterTextinField("Home Phone", "2345233070");
		txtMailingStreet.sendKeys("4321 AutoTest Drive");
		TestUtil.enterTextinField("Mailing City", "Chandler");
		TestUtil.dropdownOption("Mailing State", "Arizona");
		TestUtil.enterTextinField("Mailing Zip","85234");
		TestUtil.enterTextinField("Mobile Phone", "8014327865");
		
		//***Client Briefing****
		TestUtil.dropdownOption("Priority","High");
		TestUtil.enterTextinField("Commentary","Automation");
		txtSpecialHandling.sendKeys("AutomationSpecialHandling");
		
		//***Behavior Information****
		txtInterestandHobbies.sendKeys("Music, Football, Travelling");Thread.sleep(2000);
		
		//***Key Information****
		inputReferredBy.sendKeys("Tom Hanks", Keys.TAB);
		
		//***PB Team Code
		inputPBTeamCode.sendKeys("95R", Keys.ENTER);Thread.sleep(2000);
		inputPBTeamCode.sendKeys(Keys.ENTER);
		TestUtil.clickElement(selectPBTeamCode);
		
		//***PB Team Code
		inputCBTeamCode.sendKeys("11E", Keys.ENTER);Thread.sleep(2000);
		inputCBTeamCode.sendKeys(Keys.ENTER);
		TestUtil.clickElement(selectCBTeamCode);

		
		TestUtil.clickElement(btnSave);
		

	}
		

	public void newClientDetailPage() throws InterruptedException{
		
		TestUtil.clickElement(tabClientDetail);Thread.sleep(3000);
		TestUtil.scrollintoView(tabClientDetail);
		TestUtil.clickElement(editEmploymentstatus);

		//****Employment Information****
		TestUtil.dropdownOption("Employment Status", "Employed");
		inputRelationshipStartDate.sendKeys("07/11/2020", Keys.TAB);
		TestUtil.dropdownOption("Round Of Funding", "Series A");
	}
	
	
}
