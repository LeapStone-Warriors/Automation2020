package com.crm.qa.base;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import io.github.bonigarcia.wdm.WebDriverManager;



	public class DriverManager {
	
		private static WebDriver drivermanager;
		
		public static WebDriver setupChrome() {
		
			//WebDriverManager.chromedriver().browserVersion(findBrowserVersion()).setup();
		    WebDriverManager.chromedriver().setup();
			drivermanager = new ChromeDriver();
			return drivermanager;
		
		}
		
		public static WebDriver setupFireFox() {
			
			//WebDriverManager.chromedriver().browserVersion(findBrowserVersion()).setup();
		    WebDriverManager.firefoxdriver().setup();
			drivermanager = new FirefoxDriver();
			return drivermanager;
		
		}
		
		 
		 public static String findBrowserVersion() {
			 
			 String dosCommand = "reg query \"HKEY_CURRENT_USER\\Software\\Google\\Chrome\\BLBeacon\" /v version";
			 String value = readRegistry(dosCommand);
	         
			 String[] parts = value.split(" ");
			 String version = parts[12].substring(0,2);
			 return version;
		 }
		 
		 
		 public static final String readRegistry(String location){
		        try {
		            // Run reg query, then read output with StreamReader (internal class)
		            Process process = Runtime.getRuntime().exec(location);

		            StreamReader reader = new StreamReader(process.getInputStream());
		            reader.start();
		            process.waitFor();
		            reader.join();
		            String output = reader.getResult();

		            String[] parsed = output.split("\n");
		            return parsed[parsed.length-2];
		        }
		        catch (Exception e) {  return null;  }

		    }

		    static class StreamReader extends Thread {
		        private InputStream is;
		        private StringWriter sw= new StringWriter();

		        public StreamReader(InputStream is) {this.is = is;}

		        public void run() {
		            try {
		                int c;
		                while ((c = is.read()) != -1)
		                    sw.write(c);
		            } catch (IOException e) { }
		        }

		        public String getResult() {return sw.toString();}
		   
		    }
				
	}
