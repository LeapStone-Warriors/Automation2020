package com.crm.qa.util;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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
    
   
    public static void waitforElementVisible(WebElement element) throws InterruptedException { 
    	
    	String loc;
    	loc = geLocator(element);
    	new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath(loc))).click();
    
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
 	
 
     
     /**
      * generates random phone number
      * @return
      */
     public static String getPhoneNumber(){
         int num1, num2, num3; //3 numbers in area code
         int set2, set3; //sequence 2 and 3 of the phone number
         Random generator = new Random();
         //Area code number; Will not print 8 or 9
         num1 = generator.nextInt(7) + 1; //add 1 so there is no 0 to begin
         num2 = generator.nextInt(8); //randomize to 8 because 0 counts as a number in the generator
         num3 = generator.nextInt(8);
         /* Sequence two of phone number
          the plus 100 is so there will always be a 3 digit number
          randomize to 643 because 0 starts the first placement so if i randomized up to 642 it would only go up yo 641 plus 100
          and i used 643 so when it adds 100 it will not succeed 742*/
         set2 = generator.nextInt(643) + 100;
         /*Sequence 3 of number
          add 1000 so there will always be 4 numbers
          8999 so it wont succeed 9999 when the 1000 is added*/
         set3 = generator.nextInt(8999) + 1000;
         return "+1"+"(" + num1 + "" + num2 + "" + num3 + ")" + set2 + "-" + set3;
     }

     /**
      * generates random website name
      * @return
      */
     public static String getNewWebsite(){
         String string = "abcdefghijklmnopqrstuvwxyz";
         StringBuilder generatedString = new StringBuilder();
         Random random = new Random();
         //you can change 'n' to any number as needed
         int n = 10;
         for(int i=0; i<n;i++){
             int number = random.nextInt(string.length()-1);
             generatedString.append(string.charAt(number));
         }
         return generatedString.toString()+".com";
     }

     /**
      * will generate random string
      * @return
      */
     public static String getNewEmail() {
         String string = "abcdefghijklmnopqrstuvwxyz" + "0123456789";
         StringBuilder stringBuilder = new StringBuilder();
         Random random = new Random();
         //you can change 'n' to any number as needed
         int n = 10;
         for (int i = 0; i < n; i++) {
             int number = random.nextInt(string.length() - 1);
             stringBuilder.append(string.charAt(number));
         }
         int number = random.nextInt(3) + 1;
         switch (number) {
             case 1:
                 return stringBuilder + "@yahoo.com";
             case 2:
                 return stringBuilder + "@gmail.com";
             default:
                 return stringBuilder + "@outlook.com";
         }
     }

     /**
      * will generate random string
      * @return
      */
     public static String getRandomString(){
         String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"abcdefghijklmnopqrstuvwxyz";
         StringBuilder generatedString = new StringBuilder();
         Random random = new Random();
         //you can change 'n' to any number as needed
         int n = 10;
         for(int i=0; i<n;i++){
             int number = random.nextInt(string.length()-1);
             generatedString.append(string.charAt(number));
         }
         return generatedString.toString();
     }

     /**
      * creates random new name
      * @return
      */
     public static String getNewName(){
         String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"abcdefghijklmnopqrstuvwxyz";
         StringBuilder generatedName = new StringBuilder();
         Random random = new Random();
         //you can change 'n' to any number as needed
         int n = 10;
         for(int i=0; i<n;i++){
             int number = random.nextInt(string.length()-1);
             generatedName.append(string.charAt(number));
         }
         return generatedName.toString();
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
     
     
     
 
     
 	public static String geLocator(WebElement element) {

      
        String[] selectorWithValue= (element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "")).split(":");

        String selector = selectorWithValue[0].trim();
        String value = selectorWithValue[1].trim();
        return value;
    }   
   
     
     
     public boolean isLeadLinkVisible(){
 	    WebDriverWait zeroWait = new WebDriverWait(driver, 0);
 	    ExpectedCondition<WebElement> c = ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Create New Application']"));
 	    try {
 	        zeroWait.until(c);
 	        return true;
 	    } catch (Exception e) {return false;}
 	}   
     
    
  
     
     
}
