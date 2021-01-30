package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.*;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.crm.qa.util.TestUtil;



public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static SoftAssert softAssertion = new SoftAssert();
	public static Logger log = Logger.getLogger("trail");
	
		public TestBase(){
		try {
			
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/configuration/java/com/crm"+ "/qa/config/QAconfig.properties");
			prop.load(ip);

		} 	catch (FileNotFoundException e) {e.printStackTrace();} 
			catch (IOException e) {e.printStackTrace();}
	}
	
		
	@BeforeSuite(alwaysRun=true)
	public void initialSetup(){
			
			System.out.println("\nConfiguring Driver and Browser properties .....................\n");
		
			String browserName = 	prop.getProperty("browser");
		
			if(browserName.equals("chrome")){
				
				new DesiredCapabilities();
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
	    
		    	ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				options.addArguments("--disable-popup-blocking");
				options.addArguments("–disable-web-security");
				options.addArguments("–allow-running-insecure-content");
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				options.setExperimentalOption("prefs", prefs);
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT );
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				
				
			    driver = DriverManager.setupChrome();
			    driver.manage().window().maximize();
				
			
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ prop.getProperty("firefox_path"));	
			driver = new FirefoxDriver(); 
		
		}
	
		System.out.println("Browser properties are configured..............");	
		
		System.out.println("\nConfiguring Browser Setup .....................");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		System.out.println("Browser Setup is Completed.....................");
		System.out.println("********************************************************************************************************\n");
		
		driver.get(prop.getProperty("url"));
		
		
	}
	
	
	@AfterSuite(alwaysRun=true)
	public void closeDriver() {
		
		System.out.println("\nClosing Browser and terminating driver instance.............");
		driver.quit();
		
	}
	
	
	
	public static String[] addDaysToCurrentTime(int days){
		String timestamp1, timestamp2, timestamp3, timestamp4, timestamp5, timestamp6, timestamp7;
		
		Calendar calendar = Calendar.getInstance();	calendar.add(Calendar.DATE,days);Date date = calendar.getTime();
		Calendar calendar1 = Calendar.getInstance();Date date1 = calendar1.getTime();
		
		Format formatter 	= new SimpleDateFormat("MMM dd, yyyy");			timestamp1 = formatter.format(date);
		Format formatter1 	= new SimpleDateFormat("MM/dd/yyyy");			timestamp2 = formatter1.format(date);
		Format formatter2 	= new SimpleDateFormat("yyyy-MM-dd");			timestamp3 = formatter2.format(date);
		Format formatter3 	= new SimpleDateFormat("MM/dd/yyyy h:mm a");	timestamp4 = formatter3.format(date);
		Format formatter4 	= new SimpleDateFormat("Mdhmm");prop.setProperty("Call Start Time", timestamp4);		timestamp5 = formatter4.format(date);
		Format formatter5 	= new SimpleDateFormat("Mdhmm");				timestamp6 = formatter5.format(date); 	timestamp7 = formatter1.format(date1);
		
		return new String[] {timestamp1, timestamp2, timestamp3, timestamp4, timestamp5, timestamp6, timestamp7};
	}
	
	
	 
	public static String advisorType(String adv){
		/* 
		**UserName**		  ***Profile ID***		****Type****
		Ian Krekelberg 		= 0050R000000XRFmQAO = Field Advisor
		Travis Adams 		= 0050R000000tUilQAE = Inbound Advisor
		Andrew Kovalcik  	= 0050R000000XRHEQA4 = Outbound1Advisor
		Robert Fitzpatrick 	= 0050R000000XRHYQA4 = Outbound2Advisor
		Rachel Alandzes 	= 0050R000000kTdBQAU = Field CSM
		Allison Boyd		= 0050R0000030MXPQA2 = Field CSM
		Imran Sharaf		= 0050R0000032jRGQAY = System Administrator for Developers
		*/
		String advisor = null;

		if 		(adv.equalsIgnoreCase("0050R000000XRFmQAO")){advisor= "Field Advisor: Ian Krekelberg";}
		else if (adv.equalsIgnoreCase("0050R000000tUilQAE")){advisor= "Inbound Advisor: Travis Adams";}
		else if (adv.equalsIgnoreCase("0050R000000XRHEQA4")){advisor="Outbound1Advisor: Andrew Kovalcik";}
		else if (adv.equalsIgnoreCase("0050R000000XRHYQA4")){advisor="Outbound2Advisor: Robert Fitzpatrick";}
		else if (adv.equalsIgnoreCase("0050R000000kTdBQAU")){advisor= "Field CSM: Rachel Alandzes";}	
		else if (adv.equalsIgnoreCase("0050R0000030MXPQA2")){advisor= "Field CSM: Allison Boyd";}	
		else if (adv.equalsIgnoreCase("0050R0000032jRGQAY")){advisor= "System Administrator for Developers: Imran Sharaf";}	
		
		return advisor;
	
	}
	
	
	

}
