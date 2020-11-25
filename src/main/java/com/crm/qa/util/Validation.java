package com.crm.qa.util;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.crm.qa.base.TestBase;

public class Validation extends TestBase{

	WebDriver driver;
	
	private final int TIMEOUT = 10;
/*
	public static void SelectDropDownOption(WebElement dropdown, String Option ) {
		
		Select dropdownoption = new Select(dropdown);
		System.out.println(dropdownoption);
		dropdownoption.selectByVisibleText(Option);
		
		}
		
	*/

	public void waitForElement(WebDriver driver, final String webElementId){
    	int timeOut = 90;
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(new ExpectedCondition<Boolean>() {
        	@Override
			public Boolean apply(WebDriver driver) {
                        if (driver.findElement(By.id(webElementId)) != null){
        			return Boolean.TRUE;
        		}
        		return null;
            }
        }); 
    }	
	
	//@SuppressWarnings("unchecked")
	public void waitUntilElementDisplayed(final WebElement webElement, WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		ExpectedCondition<Boolean> elementIsDisplayed = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver arg0) {
		  try {
		     webElement.isDisplayed();
		     return true;
		  }
		  catch (NoSuchElementException e ) {
		    return false;
		  }
		  catch (StaleElementReferenceException f) {
		    return false;
		  }
		    }
		};
		wait.until(elementIsDisplayed);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
}
