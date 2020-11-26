/*
 * For retrying the failure test runs automatically during the test run itself, 
 * we need to implement the IRetryAnalyzer interface provided by TestNG. 
 * The IRetryAnalyzer interface provide methods to control retrying the test runs. 
 * Here, we will override the retry() method of IRetryAnalyzer to make sure the test 
 * runs in case of failure with specified number of retry limit.
 */

package com.qa.EventListener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import com.crm.qa.base.TestBase;
import com.qa.ExtentReport.ExtentTestManager;



public class RetryAnalyzer extends TestBase implements IRetryAnalyzer {
	
	//Counter to keep track of retry attempts
	int retryAttemptsCounter = 0;
	
	//The max limit to retry running of failed test cases
	//Set the value to the number of times we want to retry
	int maxRetryLimit = 1;

	//Method to attempt retries for failure tests
	@Override
	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) {
			if(retryAttemptsCounter < maxRetryLimit){
				retryAttemptsCounter++;
				return true;
			}
		}
		return false;
	}	

	 public void extendReportsFailOperations(ITestResult iTestResult) {
	        @SuppressWarnings("unused")
			Object testClass = iTestResult.getInstance();
	        //WebDriver webDriver = ((TestBase) testClass).getDriver();
	        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
	            ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
	    }
	



}