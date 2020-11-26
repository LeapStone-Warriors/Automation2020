package com.crm.qa.util;

import org.apache.log4j.Logger;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.Status;
import com.qa.EventListener.TestListener;
 
 


public class Log extends TestListenerAdapter{
 
	
	
    //Initialize Log4j instance
   // private static Logger Log = Logger.getLogger(Log.class.getName());
	public static Logger Log = Logger.getLogger("trail");
    
    
 
    //We can use it when starting tests
    public  void startLog (String testClassName){
        Log.info("Test is Starting...");
    	
    }
 
    //We can use it when ending tests
    public static void endLog (String testClassName){
        Log.info("Test is Ending...");
    }
 
    //Info Level Logs
    
	public void info (String message) {
        Log.info(message);
        TestListener.test.get().log(Status.PASS, message);
    }
 
    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
    }
 
    //Error Level Logs
    public static void error (String message) {
        Log.error(message);
    }
 
    //Fatal Level Logs
    public static void fatal (String message) {
        Log.fatal(message);
    }
 
    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }

}
