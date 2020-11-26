package com.crm.qa.dataProvider;

/* Different profiles thats can be used to run test cases
 
**UserName**		  ***Profile ID***		****Type****
Ian Krekelberg 		= 0050R000000XRFmQAO = Field Advisor
Travis Adams 		= 0050R000000tUilQAE = Inbound Advisor
Andrew Kovalcik  	= 0050R000000XRHEQA4 = Outbound1Advisor
Robert Fitzpatrick 	= 0050R000000XRHYQA4 = Outbound2Advisor
Rachel Alandzes 	= 0050R000000kTdBQAU = Field CSM
Allison Boyd		= 0050R0000030MXPQA2 = Field CSM
Imran Sharaf		= 0050R0000032jRGQAY = System Administrator for Developers
Mark Plamer			= 0050R000004Y8nOQAS = Field Advisor Dragonfly1
*/


import org.testng.annotations.DataProvider;

public class LogaCallDataProvider {

	@DataProvider(name = "multipleUsers1")
    public static Object[][] dataProviderMethod1() 
    {
	
		return new Object[][] { { "0050R000000tUilQAE" }, { "0050R000000XRHEQA4" }, { "0050R000000XRHYQA4" }, { "0050R000000kTdBQAU" }, {"0050R000000XRFmQAO"}, {"0050R0000030MXPQA2"} };
    }

	@DataProvider(name = "multipleUsers")
    public static Object[][] dataProviderMethod() 
    {
	
		//return new Object[][] {   { "0050R000000XRFmQAO" }, { "0050R000000tUilQAE" },{ "0050R000000XRHEQA4" },{ "0050R000000XRHYQA4" },{ "0050R000000kTdBQAU" } , { "0050R0000030MXPQA2"} };
		return new Object[][] {{ "0056g000005JzikAAC" } };
    
    }

	@DataProvider(name = "Ian")
    public static Object[][] Ian_dataProviderMethod() 
    {
		return new Object[][] { { "0050R000000XRFmQAO" }};
    
    }

	@DataProvider(name = "Travis")
    public static Object[][] Travis_dataProviderMethod() 
    {
		return new Object[][] { { "0050R000000tUilQAE" } };
    
    }
	
	
	@DataProvider(name = "Andrew")
    public static Object[][] Andrew_dataProviderMethod() 
    {
		return new Object[][] { { "0050R000000XRHEQA4" } };
    
    }
	
	@DataProvider(name = "Robert")
    public static Object[][] Robert_dataProviderMethod() 
    {
		return new Object[][] { { "0050R000000XRHYQA4" } };
    
    }
	
	@DataProvider(name = "Rachel")
    public static Object[][] Rachel_dataProviderMethod() 
    {
		return new Object[][] { { "0050R000000kTdBQAU" } };
    
    }
	
	@DataProvider(name = "Allison")
    public static Object[][] Allison_dataProviderMethod() 
    {
		return new Object[][] { { "0050R0000030MXPQA2" } };
    
    }
	
	@DataProvider(name = "Imran")
    public static Object[][] Imran_dataProviderMethod() 
    {
		return new Object[][] { { "0050R0000032jRGQAY" } };
    
    }
	
	
		
	


}
