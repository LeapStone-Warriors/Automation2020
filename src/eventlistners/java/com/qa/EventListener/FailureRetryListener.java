/* we need to set the retryAnalyzer attribute in each of the @Test annotation. 
 * To deal with this we can implement IAnnotationTransformer interface. 
 * This interface provides the capability to alter the testNG annotation at runtime.
 * 
 */

package com.qa.EventListener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;


public class FailureRetryListener implements IAnnotationTransformer {
	
	//Overriding the transform method to set the RetryAnalyzer
	@Override
	public void transform(ITestAnnotation testAnnotation, @SuppressWarnings("rawtypes") Class testClass,@SuppressWarnings("rawtypes") Constructor testConstructor, Method testMethod){
		@SuppressWarnings("deprecation")
		IRetryAnalyzer retry = testAnnotation.getRetryAnalyzer();

		if (retry == null)
			testAnnotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}
