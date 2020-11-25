package com.crm.qa.util;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


public class TestUtil extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 60;
	public static long IMPLICIT_WAIT = 60;
	
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		
		}
	
    public static void SelectDropDownOption(WebElement dropdown, String Option ) {
		
    	
		Select dropdownoption = new Select(dropdown);
		dropdownoption.selectByVisibleText(Option);
		
	}
    
    
    public static void dropdownOptionViaSelect(String label,  String Option ) throws InterruptedException {
		
    	WebElement dropdown = driver.findElement(By.xpath("//span[contains(text(),'"+label+"')]/../..//a"));
    	clickElement(dropdown);
		Select dropdownoption = new Select(dropdown);
		dropdownoption.selectByVisibleText(Option);
		
	}
    
    
    public static void dropdownOption(String label, String option ) throws InterruptedException {
		
    	WebElement dropdown = driver.findElement(By.xpath("(//span[contains(text(),'"+label+"')]/../..//a)[last()]"));
    	clickElement(dropdown);
    	WebElement chooseOption = driver.findElement(By.xpath("(//a[contains(text(),'"+option+"')])[last()]"));
    	clickElement(chooseOption);
    	
    }
    
    public static void dropdownOptionbyIndex(WebElement element, int indexNumber ) throws InterruptedException {
	    Select Option = new Select(element);
	    Option.selectByIndex(indexNumber);
    }
    
    public static void enterTextinField(String label, String text ) throws InterruptedException {
    	
    	WebElement field = driver.findElement(By.xpath("(//span[contains(text(),'"+label+"')]/../..//input)[last()]"));
    	field.sendKeys(text);
    
    }
    
    public static void enterValue(WebElement element, String value )  {
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("arguments[0].value='" +value+ "';", element);
    }
    
    	
    
    
    
    public static void enterTextinTextArea(String label, String text ) throws InterruptedException {
    	
    	WebElement field = driver.findElement(By.xpath("//span[contains(text(),'"+label+"')]/../..//textarea"));
    	field.sendKeys(text);
    	
    }
    
    
    public static void clickElement(WebElement element) throws InterruptedException { 
    	
    	waitUntilElementVisible(element);
    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", element); 
    	    
    }
		
    public static void scrollintoView(WebElement element) { 
    	
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element); 
    }
      

    public static void waitUntilElementVisible(WebElement element) throws InterruptedException { 
	   	 
    	WebDriverWait wait = new WebDriverWait(driver, 60);
	   	wait.until(ExpectedConditions.visibilityOf(element));
    }
    
 
    
 
    
    public static void waitforPageLoad(WebElement element) throws InterruptedException { 
 			ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    @Override
					public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
    
   
   
    
    
    
    public static void waitUntilPageLoad(WebDriver driver) throws InterruptedException { 
    	new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
    
   
    
    
    public static void print(String TestCaseName) {
    	 
    	 System.out.println();
    	 System.out.println("***************************** Executing Test Case: "+TestCaseName+" ******************************");
    	 System.out.println();
     }
     
      
     public static void closeAllOpenTabs(WebDriver driver) throws InterruptedException {
    	 
    	WebElement openTabgrid= driver.findElement(By.xpath("//*[contains (@class, ('tabBarItems slds-grid'))]")); Thread.sleep(2000);
    	 	
    	 List<WebElement> listoftabs = openTabgrid.findElements(By.xpath("//a[contains (@class, ('slds-context-bar'))][contains (@href, ('/lightning/'))]"));
    	 	
    	 	int i = listoftabs.size();
    	 	
    	 	if (listoftabs.size()>1){
    	 		for (int j=0; j<i-1; j++)
    	 			{driver.findElement(By.xpath("//div[2]//button[1][contains (@title, ('Close'))]")).click(); }
    	 	}
     	 	
    	 	clickAccountLink();
     }
 
     
     public static void clickAccountLink() throws InterruptedException {
    	 
    	 String accountId = RetailUserdetails.get_sfdcID();
    	 
    	 driver.findElement(By.xpath("//a[contains (@class, ('" +accountId+ "'))]")).click();
    	 Thread.sleep(2000);
    	 
    	 
     }
 
     public void clickAccountviaAccountScreen() throws InterruptedException{ 
 		
 		Thread.sleep(2000);
 		changeNavigationMenu("Accounts");
 		Thread.sleep(2000);
 		TestUtil.closeAllOpenTabs(driver);
 		Thread.sleep(2000);
 		driver.findElement(By.xpath("(//a[contains (@title, ('" +RetailUserdetails.getaccountName("primary")+ "'))][contains (@class, ('slds-truncate'))])[1]")).click();
 		Thread.sleep(2000);
 		
 	}
     
     public static void changeNavigationMenu(String Menu) throws InterruptedException{
 		
 		driver.findElement(By.xpath("//button[contains (@title, ('Show Navigation Menu'))]")).click();
 		Thread.sleep(2000);
 		driver.findElement(By.xpath("//span[contains (@class,('menuLabel slds-listbox'))][contains(text(),'" +Menu+ "')]")).click();
 	}
 	
  
    
     //Validate Detail Page Fields
     public static void validatePageField(String fieldName, String expectedValue, String errorMessage) throws InterruptedException, AWTException {
	 		
	 		String actualValue = driver.findElement(By.xpath("(//span[(text()='" +fieldName+ "')]/../..//lightning-formatted-text)[last()]")).getText();
	 		softAssertion.assertEquals(actualValue, expectedValue, errorMessage);
	 	
	 	}
	 	 
     
     public static void validatePageFieldthatContains(String fieldName, String expectedValue, String errorMessage) throws InterruptedException, AWTException {
	 		
	 		String actualValue = driver.findElement(By.xpath("(//span[(text()='" +fieldName+ "')]/../..//lightning-formatted-text)[last()]")).getText();
	 		softAssertion.assertTrue(actualValue.contains(expectedValue), errorMessage);
	 	
	 	}
	 	 
     
     public static String changeDateFormat(String date) throws ParseException{
    	 
    	 DateFormat originalFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
    	 DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
    	 Date targetdate = originalFormat.parse(date);
    	 String formattedDate = targetFormat.format(targetdate);
    	 return formattedDate;
    	 
     }
   
     public static String pickFutureDate() {
    	 SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
    	 Calendar calendar = Calendar.getInstance();
	     calendar.add(Calendar.DAY_OF_YEAR, 10);
	     String futureDate = sdf.format(calendar.getTime()); 
	     return futureDate;
     }
     

 	
     public static String generateString() {
    	 int length = 6;
    	 String generatedString = RandomStringUtils.randomAlphabetic(length);
    	 return generatedString;
 	}
     
     
     
 	private static By getByFromElement(WebElement element) {

         By by = null;
         String[] selectorWithValue= (element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "")).split(":");

         String selector = selectorWithValue[0].trim();
         String value = selectorWithValue[1].trim();

         switch (selector) {
             case "id":
                 by = By.id(value);
                 break;
             case "className":
                 by = By.className(value);
                 break;
             case "tagName":
                 by = By.tagName(value);
                 break;
             case "xpath":
                 by = By.xpath(value);
                 break;
             case "cssSelector":
                 by = By.cssSelector(value);
                 break;
             case "linkText":
                 by = By.linkText(value);
                 break;
             case "name":
                 by = By.name(value);
                 break;
             case "partialLinkText":
                 by = By.partialLinkText(value);
                 break;
             default:
                 throw new IllegalStateException("locator : " + selector + " not found!!!");
         }
         return by;
     }   
    
     
     
     
     
     
     public boolean isLeadLinkVisible(){
 	    WebDriverWait zeroWait = new WebDriverWait(driver, 0);
 	    ExpectedCondition<WebElement> c = ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Create New Application']"));
 	    try {
 	        zeroWait.until(c);
 	        return true;
 	    } catch (Exception e) {
 	        return false;
 	    }
 	}   
     
    
  
     
     
     
     
     
     
     
     
}
