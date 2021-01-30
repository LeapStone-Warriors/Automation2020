package com.qa.EventListener;
/*************************************** PURPOSE **********************************

 - This class implements the WebDriverEventListener, which is included under events.
 The purpose of implementing this interface is to override all the methods and define certain useful  Log statements 
 which would be displayed/logged as the application under test is being run.

 Do not call any of these methods, instead these methods will be invoked automatically
 as an when the action done (click, findBy etc). 

 */

import java.io.IOException;
import org.apache.log4j.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.crm.qa.base.TestUtil;
import com.crm.qa.util.TestBase;

public class WebEventListener extends TestBase implements WebDriverEventListener {

	public static Logger Log = Logger.getLogger("trail");
	
	
	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before navigating to: '" + url + "'");
		//Log.debug("Before navigating to: '" + url + "'");
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("Navigated to:'" + url + "'");
		Log.debug("Navigated to: '" + url + "'");
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		System.out.println("Value of the:" + element.toString() + " before any changes made");
		//Log.debug("Value of the:" + element.toString() + " before any changes made");
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		System.out.println("Element value changed to: " + element.toString());
		Log.debug("Element value changed to: " + element.toString());
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("Trying to click on: " + element.toString());
		//Log.debug("Trying to click on: " + element.toString());
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("Clicked on: " + element.toString());
		Log.debug("Clicked on: " + element.toString());
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		System.out.println("Navigating back to previous page");
		//Log.debug("Navigating back to previous page");
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		System.out.println("Navigated back to previous page");
		Log.debug("Navigated back to previous page");
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		System.out.println("Navigating forward to next page");
		Log.debug("Navigating forward to next page");
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		System.out.println("Navigated forward to next page");
		//Log.debug("Navigated forward to next page");
	}

	@Override
	public void onException(Throwable error, WebDriver driver) {
		System.out.println("Exception occured: " + error);
		Log.fatal("Exception occured: " + error);
		try {
			TestUtil.takeScreenshotAtEndOfTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Trying to find Element By : " + by.toString());
		//Log.debug("Trying to find Element By : " + by.toString());
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Found Element By : " + by.toString());
		Log.debug("Trying to find Element By : " + by.toString());
	}

	/*
	 * non overridden methods of WebListener class
	 */
	@Override
	public void beforeScript(String script, WebDriver driver) {
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

}