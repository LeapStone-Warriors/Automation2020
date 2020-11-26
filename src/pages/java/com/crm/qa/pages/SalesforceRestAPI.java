
package com.crm.qa.pages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.time.*;

import com.crm.qa.base.TestBase;

 
public class SalesforceRestAPI extends TestBase {
 
	static final String USERNAME     	= prop.getProperty("username");
	static final String PASSWORD     	= prop.getProperty("password");
	static final String LOGINURL     	= prop.getProperty("url");
	static final String GRANTSERVICE 	= "/services/oauth2/token?grant_type=password";
    static final String CLIENTID     	= prop.getProperty("SFDC_API_ClientID");
    static final String CLIENTSECRET 	= prop.getProperty("SFDC_API_ClientSecret");
    private static String REST_ENDPOINT = "/services/data" ;
    private static String API_VERSION 	= "/v48.0" ;
    private static String baseUri;
    private static Header oauthHeader;
    private static Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");
    private static String taskId ;
    private static  String taskAccountId ;
    private static  String taskCallType ;
    private static  String taskStatus ;
    private static  String taskSolutionsDiscussed ;
    private static  String taskName;
    private static  String leadId ;
    private static  String leadStatus;
    private static  String taskCreatedDate;
    private static  String taskCallOutcome;
    private static  Object taskDescription;
    private static  String retailId;
    private static  String busleadId;
    private static  String branleadId;
    private static  int uriselection;
    private static  String sponsorId;
    private static 	String spousesfdcid;
    private static 	String primarysfdcid;
    
    private static LocalDate futureDate = LocalDate.now().plusDays(15);
   
    public static String uid;
    public static String accountId;
    public static String fname ;
    public static String lname ;
    public static String bname ;
    public static String callType;
	
	static OutputStream output = null;
	static String Uri = null;
	
	public static Map<String, String> objMap = new HashMap<String, String>();
	

   public static void APIConnection() {
	
	   
        HttpClient httpclient = HttpClientBuilder.create().build();
        
 
        // Assemble the login request URL
        String loginURL = LOGINURL +
                          GRANTSERVICE +
                          "&client_id=" + CLIENTID +
                          "&client_secret=" + CLIENTSECRET +
                          "&username=" + USERNAME +
                          "&password=" + PASSWORD;
 
        // Login requests must be POSTs
        HttpPost httpPost = new HttpPost(loginURL);
        HttpResponse response = null;
 
        // Execute the login POST request
        try { response = httpclient.execute(httpPost);}
        catch (ClientProtocolException cpException) { cpException.printStackTrace();} 
        catch (IOException ioException) { ioException.printStackTrace(); }
 
        // verify response is HTTP OK
        final int statusCode = response.getStatusLine().getStatusCode();
        
        // Error is in EntityUtils.toString(response.getEntity())
        if (statusCode != HttpStatus.SC_OK) {System.out.println("Error authenticating to Force.com: "+statusCode); return;}
 
        String getResult = null;
        try { getResult = EntityUtils.toString(response.getEntity());} 
        catch (IOException ioException) {ioException.printStackTrace(); }
 
        JSONObject jsonObject = null;
        String loginAccessToken = null;
        String loginInstanceUrl = null;
 
        try {
            jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
            loginAccessToken = jsonObject.getString("access_token");
            loginInstanceUrl = jsonObject.getString("instance_url");
        } catch (JSONException jsonException) { jsonException.printStackTrace();}
 
        
        baseUri = loginInstanceUrl + REST_ENDPOINT + API_VERSION ;
        oauthHeader = new BasicHeader("Authorization", "OAuth " + loginAccessToken) ;
        System.out.println("oauthHeader1: " + oauthHeader);
        System.out.println("\n" + response.getStatusLine());
        System.out.println("Successful login");
        System.out.println("instance URL: "+loginInstanceUrl);
        System.out.println("access token/session ID: "+loginAccessToken);
        System.out.println("baseUri: "+ baseUri);        
 
        httpPost.releaseConnection();
    }
    
   
   public static void dataCreation() {
	   APIConnection();
	   createRetailRecord();
	   updateRetailRecord();
	   updateBrancAccountRelationship();
	   createBusinessLead();
	   createBranchLead();
	   
	  	   
   }
   
   public static String[] getHashMapData() {
	   String[] sfdcId = new String[10];
	   sfdcId[0] = objMap.get("sfdcId");
	   sfdcId[1] = objMap.get("businessLeadId");
	   sfdcId[2] = objMap.get("spousesfdcId");
	   sfdcId[3] = objMap.get("primarysfdcId");
	   return sfdcId;
	   
   }
  
    
  
    // Query Leads using REST HttpGet
    public static JSONArray queryTaskObject(int i) {
    	
    	
    	String sfdcid = objMap.get("sfdcId");
    	String leadid = objMap.get("businessLeadId");
    	
    	
        String taskUri 		= baseUri + "/query?q=select+Id+,+AccountId+,+Call_Start_Time__c+,+Description+,+Call_Outcome__c+,+Call_Type__c+,+Status+,+Solutions_Discussed__c+from+Task+where+AccountId+='" +sfdcid+"'+and+Call_Type__c+='Call'+ORDER+BY+CreatedDate+Desc";
        String scheduleUri 	= baseUri + "/query?q=select+Id+,+AccountId+,+Type+,+Call_Start_Time__c+,+Description+,+Call_Outcome__c+,+Status+,+Solutions_Discussed__c,+ActivityDate+from+Task+where+AccountId+='" +sfdcid+"'+and+(+Call_Outcome__c+!=''+OR+Type+!='Call'+)";
        String meetingUri 	= baseUri + "/query?q=select+Id+,+Meeting_Medium__c+,+Meeting_Status__c+,+StartDateTime+,+EndDateTime+,+Type+,+Meeting_Sub_Type__c+,+Appointment_Start_Date_Time_Text__c+from+Event+where+AccountId+='" +sfdcid+"'+ORDER+BY+CreatedDate+Desc";
        String lead1Uri 	= baseUri + "/query?q=select+Id+,+Call_attempts_made__c+,+Status+from+Lead+where+Id+='" +leadid+"'+ORDER+BY+CreatedDate+Desc";
        String lead2Uri 	= baseUri + "/query?q=select+Id+,+Call_attempts_made__c+,+Status+,+Customer_Contact__C+,+IsConverted+,+LastActivityDate+from+Lead+where+Id+='" +leadid+"'+ORDER+BY+CreatedDate+Desc";
        String branchoppUri = baseUri + "/query?q=select+Id+,+Total_Expected_Amount__c+,+CreatedDate+,+LeadSource+,+StageName+from+opportunity+where+AccountId+='" +sfdcid+"'+ORDER+BY+CreatedDate+Desc";
        String finAccUri 	= baseUri + "/query?q=select+Id+,+Deactivated__c+,+Finserv__FinancialAccountType__c+from+FinServ__FinancialAccount__c+where+FinServ__PrimaryOwner__c+='" +sfdcid+"'+ORDER+BY+Deactivated__c+Desc";
        String taskUrihousehold		= baseUri + "/query?q=select+Id+,+AccountId+,+Call_Start_Time__c+,+Description+,+Call_Outcome__c+,+Call_Type__c+,+Status+,+Solutions_Discussed__c+from+Task+where+AccountId+='" +sfdcid+"'+and+Status+='Completed'+ORDER+BY+CreatedDate+Desc";
        
        
        
    	JSONArray j = null;
    	
        System.out.println("\n_______________ QUERY _______________");
        try {
 
            //Set up the HTTP objects needed to make the request.
            HttpClient httpClient = HttpClientBuilder.create().build();

            
            if 		(i == 1) {Uri = taskUri;} 
            else if (i == 2) {Uri = scheduleUri;} 
            else if (i == 3) {Uri = meetingUri;} 
            else if (i == 4) {Uri = lead1Uri;} 
            else if (i == 5) {Uri = lead2Uri;} 
            else if (i == 6) {Uri = branchoppUri;} 
            else if (i == 7) {Uri = finAccUri;}
            else if (i == 8) {Uri = taskUrihousehold;}
            
            
            HttpGet httpGet = new HttpGet(Uri);
    		System.out.println("Query URL: " + Uri);
    		System.out.println("oauthHeader2: " + oauthHeader);
            httpGet.addHeader(oauthHeader);
            httpGet.addHeader(prettyPrintHeader);
            
            // Make the request.
            HttpResponse response = httpClient.execute(httpGet);
           
 
            // Process the result
            int statusCode = response.getStatusLine().getStatusCode();
            
            if (statusCode == 200) {
            	String response_string = EntityUtils.toString(response.getEntity());
                try {
                    JSONObject json = new JSONObject(response_string);
                    System.out.println("JSON result of Query:\n" + json.toString(1));
                    j = json.getJSONArray("records");
                  
                } catch (JSONException je) {je.printStackTrace();}
               
                
            } else {
                System.out.println("Query was unsuccessful. Status code returned is " + statusCode);
                System.out.println("An error has occured. Http status: " + response.getStatusLine().getStatusCode());
                System.out.println(getBody(response.getEntity().getContent()));
                System.exit(-1);
            }
        } 	
        	catch (IOException ioe) {ioe.printStackTrace();} 
        	catch (NullPointerException npe) {npe.printStackTrace();}
        
		return j;
    }
 
    
 
    
 
    // Extend the Apache HttpPost method to implement an HttpPatch
    private static class HttpPatch extends HttpPost {
       
    	public HttpPatch(String uri) { super(uri);}
        @Override
		public String getMethod() {return "PATCH";}
    }
 
    
 
    private static String getBody(InputStream inputStream) {
        String result = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            
            String inputLine;
            while ( (inputLine = in.readLine() ) != null ) {
                result += inputLine;
                result += "\n";
            }
            in.close();
        } catch (IOException ioe) {ioe.printStackTrace();}
        
        return result;
    }
  
    
    
    
    
 
    
    public static void createLead() {
   	 
    	System.out.println("\n_______________ Creating Lead_______________");
		
		String uri = baseUri + "/sobjects/Lead";
		
	    try {
	    	 
	        //create the JSON object containing the new lead details.
	        JSONObject buslead = new JSONObject();

	        buslead.put("FirstName","vozy");
	        buslead.put("LastName","vomato");
	        buslead.put("Email","vzabist@gmail.com");
	        buslead.put("Salutation","Mr.");
	        buslead.put("MobilePhone", "2021910021");
	        buslead.put("DateOfBirth__c", "1965-12-20");
	        buslead.put("Industry","Life Science - Biotech");
	        buslead.put("Website", "www.google.com");
	        buslead.put("CompanyRegion__c","Southeast");
	        buslead.put("Company__c", "Google");
	        buslead.put("Title", "Manager");
	        buslead.put("TCPSegmentTier__c", "Bronze: Low (< $500k)");
	        buslead.put("InstitutionalCapitalRaised__c", "2345678");
	        buslead.put("Priority__c", "Immediate");
	        buslead.put("ProductsOfInterest__c", "Mortgages");
	        buslead.put("ReferredBy__c", "Tony Ford");
	        buslead.put("RelationshipStartDateWithSVB__c", "2020-07-01");
	        buslead.put("CBTeamCode__c", "a0Z11000004lkIwEAI");
	        buslead.put("RoundOfFunding__c", "Series A");
	        buslead.put("Street", "1221 South Spectrum Boulevard");
	        buslead.put("City", "Chandler");
	        //buslead.put("StateCode", "AZ");
	        buslead.put("PostalCode", "85286");
	        buslead.put("LeadSource", "COI");
	        buslead.put("Description", "Created by Automation Script using Rest API");
	        
	        	        
	        System.out.println("JSON for Business Lead record to be inserted:\n" +buslead.toString(1));

	        //Construct the objects needed for the request
	        HttpClient httpClient = HttpClientBuilder.create().build();
	        
	        HttpPost httpPost = new HttpPost(uri);
	        httpPost.addHeader(oauthHeader);
	        httpPost.addHeader(prettyPrintHeader);
	        // The message we are going to post
	        
	        StringEntity body = new StringEntity(buslead.toString(1));
	        body.setContentType("application/json");
	        httpPost.setEntity(body);

	        //Make the request
	        HttpResponse response = httpClient.execute(httpPost);
	        System.out.println("Response Body:\n"+ response);
	        
	        //Process the results
	        int statusCode = response.getStatusLine().getStatusCode();
	        
	        if (statusCode == 201) {
	            String response_string = EntityUtils.toString(response.getEntity());
	            JSONObject json = new JSONObject(response_string);
	            
	            // Store the retrieved retail id to use when we update the retail.
	            busleadId = json.getString("id");
	            System.out.println("New Business Lead id from response: " + busleadId);
	          
	        } else {System.out.println("Insertion unsuccessful. Status code returned is " + statusCode);}
	    } 
	    
	    catch (JSONException e) {System.out.println("Issue creating JSON or processing results"); e.printStackTrace();} 
	    catch (IOException ioe) {ioe.printStackTrace();} 
	    catch (NullPointerException npe) {npe.printStackTrace();}
	    
	    objMap.put("businessLeadId", busleadId);
	
      
     }    
    
    
    
    
    
 public static void createRetailRecord() {
	 
	System.out.println("\n_______________ Creating Retail Record _______________");
	
	String uri = baseUri + "/quickActions/Create_Retail_Account";
	
    try {
    
        //create the JSON object containing the new lead details.
        JSONObject retail = new JSONObject();
        retail.put("FirstName", fname);
        retail.put("LastName", lname);
        retail.put("Gender__c", "Female");
        retail.put("DOB__c", "1980-12-11");
        retail.put("Email__c", fname+"@testaccount.com");
        retail.put("Phone", "408-555-1234");
        retail.put("BillingStreet", "116 Batman Rd");
        retail.put("BillingCity", "Charlotte");
        retail.put("BillingState", "NC");
        retail.put("BillingPostalCode", "28219");
        retail.put("BillingCountry", "USA");
        retail.put("Type", "Branch Retail");
 
        //Construct the objects needed for the request
        HttpClient httpClient = HttpClientBuilder.create().build();
        JSONObject retail1 = new JSONObject();
        retail1.put("record", retail);
        
        System.out.println("JSON for retail record to be inserted:\n" + retail1.toString(1));
        
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader(oauthHeader);
        httpPost.addHeader(prettyPrintHeader);
        // The message we are going to post
        
        StringEntity body = new StringEntity(retail1.toString(1));
        body.setContentType("application/json");
        httpPost.setEntity(body);

        //Make the request
        HttpResponse response = httpClient.execute(httpPost);

        //Process the results
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 201) {
            String response_string = EntityUtils.toString(response.getEntity());
            JSONObject json = new JSONObject(response_string);
            // Store the retrieved retail id to use when we update the retail.
           retailId = json.getString("id");
            System.out.println("New Retail id from response: " + retailId);
          
        } else {
            System.out.println("Insertion unsuccessful. Status code returned is " + statusCode);
        }
    } catch (JSONException e) {
        System.out.println("Issue creating JSON or processing results");
        e.printStackTrace();
    } catch (IOException ioe) {
        ioe.printStackTrace();
    } catch (NullPointerException npe) {
        npe.printStackTrace();
    }

    objMap.put("sfdcId", retailId);
		/*
		 * prop.setProperty("retailsfdcID", retailId); try { prop.store(output,
		 * "Retail record created"); //output.close(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
  
 }   
 
 public static void createRetailRecord_ExternalData(String fname1, String lname1, String Email  ) {
	 
		System.out.println("\n_______________ Creating Retail Record _______________");
		
		String uri = baseUri + "/quickActions/Create_Retail_Account";
		
	    try {
	    	 
	        //create the JSON object containing the new lead details.
	        JSONObject retail = new JSONObject();
	        retail.put("FirstName", fname1);
	        retail.put("LastName", lname1);
	        retail.put("Gender__c", "Male");
	        retail.put("DOB__c", "1978-09-08");
	        retail.put("Email__c", Email);
	        retail.put("Phone", "480-555-1234");
	        retail.put("BillingStreet", "4742 N 24th St Suite 270");
	        retail.put("BillingCity", "Phoenix");
	        retail.put("BillingState", "AZ");
	        retail.put("BillingPostalCode", "85016");
	        retail.put("BillingCountry", "USA");
	        retail.put("Type", "Branch Retail");
	 // create reatil.put for advisor and donot call the retail update via API
	        
	      //  System.out.println("JSON for retail record to be inserted:\n" + retail.toString(1));

	        //Construct the objects needed for the request
	        HttpClient httpClient = HttpClientBuilder.create().build();
	        JSONObject retail1 = new JSONObject();
	        retail1.put("record", retail);
	        
	        System.out.println("JSON for retail record to be inserted:\n" + retail1.toString(1));
	        
	        HttpPost httpPost = new HttpPost(uri);
	        httpPost.addHeader(oauthHeader);
	        httpPost.addHeader(prettyPrintHeader);
	        // The message we are going to post
	        
	        StringEntity body = new StringEntity(retail1.toString(1));
	        body.setContentType("application/json");
	        httpPost.setEntity(body);

	        //Make the request
	        HttpResponse response = httpClient.execute(httpPost);

	        //Process the results
	        int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode == 201) {
	            String response_string = EntityUtils.toString(response.getEntity());
	            JSONObject json = new JSONObject(response_string);
	            // Store the retrieved retail id to use when we update the retail.
	           retailId = json.getString("id");
	            System.out.println("New Retail id from response: " + retailId);
	          
	        } else {
	            System.out.println("Insertion unsuccessful. Status code returned is " + statusCode);
	        }
	    } catch (JSONException e) {
	        System.out.println("Issue creating JSON or processing results");
	        e.printStackTrace();
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    } catch (NullPointerException npe) {
	        npe.printStackTrace();
	    }

	    objMap.put("sfdcId", retailId);
			/*
			 * prop.setProperty("retailsfdcID", retailId); try { prop.store(output,
			 * "Retail record created"); //output.close(); } catch (IOException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
	  
	 }   
 
 
 public static void createSponsoredRecord() {
	 
		System.out.println("\n_______________ Creating Sponsor Record _______________");
		
		String uri = baseUri + "/sobjects/Account/";
		
	    try {
	    	 
	    	
	    	//User_Number__c: 25478553
	        //create the JSON object containing the new lead details.
	        JSONObject sponsor = new JSONObject();
	        sponsor.put("Name", fname+ " " +lname);
	        sponsor.put("FE_External_ID__c", "feId");
	        sponsor.put("RecordTypeId", "01246000000hP80AAE");
	        sponsor.put("Plan_Owner__c", "0010R00000AoJo3QAF");
	        sponsor.put("Type", "Sponsored");
	        
	        
	      //  System.out.println("JSON for retail record to be inserted:\n" + retail.toString(1));

	        //Construct the objects needed for the request
	        HttpClient httpClient = HttpClientBuilder.create().build();
	       // JSONObject retail1 = new JSONObject();
	        //retail1.put("record", sponsor);
	        
	        System.out.println("JSON for retail record to be inserted:\n" + sponsor.toString(1));
	        
	        HttpPost httpPost = new HttpPost(uri);
	        httpPost.addHeader(oauthHeader);
	        httpPost.addHeader(prettyPrintHeader);
	        // The message we are going to post
	        
	        StringEntity body = new StringEntity(sponsor.toString(1));
	        body.setContentType("application/json");
	        httpPost.setEntity(body);

	        //Make the request
	        HttpResponse response = httpClient.execute(httpPost);

	        //Process the results
	        int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode == 201) {
	            String response_string = EntityUtils.toString(response.getEntity());
	            JSONObject json = new JSONObject(response_string);
	            // Store the retrieved retail id to use when we update the retail.
	            sponsorId = json.getString("id");
	            System.out.println("New Retail id from response: " + sponsorId);
	          
	        } else {
	            System.out.println("Insertion unsuccessful. Status code returned is " + statusCode);
	        }
	    } catch (JSONException e) {
	        System.out.println("Issue creating JSON or processing results");
	        e.printStackTrace();
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    } catch (NullPointerException npe) {
	        npe.printStackTrace();
	    }

	    objMap.put("SponsorsfdcId", sponsorId);
			/*
			 * prop.setProperty("retailsfdcID", retailId); try { prop.store(output,
			 * "Retail record created"); //output.close(); } catch (IOException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
	  
	 }   
  
 
 
 
 
 public static void updateRetailRecord() {
	 
     System.out.println("\n_______________ Retail Record UPDATE _______________");

     //Notice, the id for the record to update is part of the URI, not part of the JSON
     String uri = baseUri + "/sobjects/Account/" + objMap.get("sfdcId");
     try {
         //Create the JSON object containing the updated lead last name
         //and the id of the lead we are updating.
         JSONObject retail = new JSONObject();
         retail.put("Assigned_Adviser__c", "0050R000000XRFmQAO");
         //retail.put("Current_Enrollment__c", "Branch Management");
         
         System.out.println("JSON for update of retail record:\n" + retail.toString(1));

         //Set up the objects necessary to make the request.
         //DefaultHttpClient httpClient = new DefaultHttpClient();
         HttpClient httpClient = HttpClientBuilder.create().build();

         HttpPatch httpPatch = new HttpPatch(uri);
         httpPatch.addHeader(oauthHeader);
         httpPatch.addHeader(prettyPrintHeader);
         StringEntity body = new StringEntity(retail.toString(1));
         body.setContentType("application/json");
         httpPatch.setEntity(body);

         //Make the request
         HttpResponse response = httpClient.execute(httpPatch);

         //Process the response
         int statusCode = response.getStatusLine().getStatusCode();
         if (statusCode == 204) {
             System.out.println("Updated the lead successfully.");
         } else {
             System.out.println("Lead update NOT successfully. Status code is " + statusCode);
         }
     } catch (JSONException e) {
         System.out.println("Issue creating JSON or processing results");
         e.printStackTrace();
     } catch (IOException ioe) {
         ioe.printStackTrace();
     } catch (NullPointerException npe) {
         npe.printStackTrace();
     }
 }
 
public static void updateRetailRecord1() {
	 
     System.out.println("\n_______________ Retail Record UPDATE _______________");

     //Notice, the id for the record to update is part of the URI, not part of the JSON
     String uri = baseUri + "/sobjects/Account/" + objMap.get("sfdcId");
     try {
         //Create the JSON object containing the updated lead last name
         //and the id of the lead we are updating.
         JSONObject retail = new JSONObject();
         
         retail.put("Current_Enrollment__c", "Branch Management");
         retail.put("Assigned_Adviser__c", "0050R000000XRFmQAO");
         
         System.out.println("JSON for update of retail record:\n" + retail.toString(1));

         //Set up the objects necessary to make the request.
         //DefaultHttpClient httpClient = new DefaultHttpClient();
         HttpClient httpClient = HttpClientBuilder.create().build();

         HttpPatch httpPatch = new HttpPatch(uri);
         httpPatch.addHeader(oauthHeader);
         httpPatch.addHeader(prettyPrintHeader);
         StringEntity body = new StringEntity(retail.toString(1));
         body.setContentType("application/json");
         httpPatch.setEntity(body);

         //Make the request
         HttpResponse response = httpClient.execute(httpPatch);

         //Process the response
         int statusCode = response.getStatusLine().getStatusCode();
         if (statusCode == 204) {
             System.out.println("Updated the lead successfully.");
         } else {
             System.out.println("Lead update NOT successfully. Status code is " + statusCode);
         }
     } catch (JSONException e) {
         System.out.println("Issue creating JSON or processing results");
         e.printStackTrace();
     } catch (IOException ioe) {
         ioe.printStackTrace();
     } catch (NullPointerException npe) {
         npe.printStackTrace();
     }
 }
 
 public static void updateBrancAccountRelationship() {
	 
		System.out.println("\n_______________ Update Branch Account Relationshio for Retail Record _______________");
		
		String uri = baseUri + "/sobjects/Branch_Account_Relationship__c";
		
	    try {

	    	
	    	//create the JSON object containing the new lead details.
	        JSONObject branch = new JSONObject();
	        branch.put("Name", bname);
	        branch.put("Account__c", objMap.get("sfdcId"));
	        branch.put("Branch_Location__c", "a270R000001amjpQAA");
	        
	  
	        branch.put("Location_Type__c", "Preferred Location");
	             
	        System.out.println("JSON for Branch Relationship to be inserted:\n" + branch.toString(1));

	        //Construct the objects needed for the request
	        HttpClient httpClient = HttpClientBuilder.create().build();

	        
	        HttpPost httpPost = new HttpPost(uri);
	        httpPost.addHeader(oauthHeader);
	        httpPost.addHeader(prettyPrintHeader);
	        // The message we are going to post
	        
	        StringEntity body = new StringEntity(branch.toString(1));
	        body.setContentType("application/json");
	        httpPost.setEntity(body);

	        //Make the request
	        HttpResponse response = httpClient.execute(httpPost);

	        //Process the results
	        int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode == 201) {
	            String response_string = EntityUtils.toString(response.getEntity());
	            JSONObject json = new JSONObject(response_string);
	            // Store the retrieved retail id to use when we update the retail.
	           String branchId = json.getString("id");
	            System.out.println("New Branch Account Relationship id from response: " + branchId);
	          
	        } else {
	            System.out.println("Insertion unsuccessful. Status code returned is " + statusCode);
	        }
	    	//}
	    } catch (JSONException e) {
	        System.out.println("Issue creating JSON or processing results");
	        e.printStackTrace();
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    } catch (NullPointerException npe) {
	        npe.printStackTrace();
	    }

	  
	 }   
 
 
 
 public static void createBusinessLead() {
	 
		System.out.println("\n_______________ Creating Business Lead_______________");
		
		String uri = baseUri + "/sobjects/Lead";
		
	    try {
	    	 
	        //create the JSON object containing the new lead details.
	        JSONObject buslead = new JSONObject();
	        buslead.put("FirstName",fname);
	        buslead.put("LastName", lname);
	        buslead.put("RecordTypeId", "0120R0000005GRQQA2");
	        buslead.put("Company", fname+lname);
	        buslead.put("Account__c", objMap.get("sfdcId"));
	        
	        
	        System.out.println("JSON for Business Lead record to be inserted:\n" +buslead.toString(1));

	        //Construct the objects needed for the request
	        HttpClient httpClient = HttpClientBuilder.create().build();
	        
	        HttpPost httpPost = new HttpPost(uri);
	        httpPost.addHeader(oauthHeader);
	        httpPost.addHeader(prettyPrintHeader);
	        // The message we are going to post
	        
	        StringEntity body = new StringEntity(buslead.toString(1));
	        body.setContentType("application/json");
	        httpPost.setEntity(body);

	        //Make the request
	        HttpResponse response = httpClient.execute(httpPost);

	        //Process the results
	        int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode == 201) {
	            String response_string = EntityUtils.toString(response.getEntity());
	            JSONObject json = new JSONObject(response_string);
	            // Store the retrieved retail id to use when we update the retail.
	            busleadId = json.getString("id");
	            System.out.println("New Business Lead id from response: " + busleadId);
	          
	        } else {
	            System.out.println("Insertion unsuccessful. Status code returned is " + statusCode);
	        }
	    } catch (JSONException e) {
	        System.out.println("Issue creating JSON or processing results");
	        e.printStackTrace();
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    } catch (NullPointerException npe) {
	        npe.printStackTrace();
	    }
	    
	    objMap.put("businessLeadId", busleadId);
		/*
		 * prop.setProperty("BusinessLeadsfdcID", busleadId); try { prop.store(output,
		 * "Business Lead record created"); //output.close(); } catch (IOException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	  
	 }   
	  
 

 
 public static void createBranchLead() {
	 
		System.out.println("\n_______________ Creating Branch Lead_______________");
		
		String uri = baseUri + "/sobjects/Lead";
		
	    try {
	    	 
	        //create the JSON object containing the new lead details.
	        JSONObject branchlead = new JSONObject();
	        branchlead.put("FirstName",fname);
	        branchlead.put("LastName", lname);
	        branchlead.put("RecordTypeId", "0120R0000002p1uQAA");
	        branchlead.put("Company", fname+lname);
	        branchlead.put("Account__c", objMap.get("sfdcId"));
	        branchlead.put("LeadSource", "Referral - ETrade Network");
	        
	        
	        System.out.println("JSON for Branch Lead record to be inserted:\n" +branchlead.toString(1));

	        //Construct the objects needed for the request
	        HttpClient httpClient = HttpClientBuilder.create().build();
	        
	        HttpPost httpPost = new HttpPost(uri);
	        httpPost.addHeader(oauthHeader);
	        httpPost.addHeader(prettyPrintHeader);
	        // The message we are going to post
	        
	        StringEntity body = new StringEntity(branchlead.toString(1));
	        body.setContentType("application/json");
	        httpPost.setEntity(body);

	        //Make the request
	        HttpResponse response = httpClient.execute(httpPost);

	        //Process the results
	        int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode == 201) {
	            String response_string = EntityUtils.toString(response.getEntity());
	            JSONObject json = new JSONObject(response_string);
	            // Store the retrieved retail id to use when we update the retail.
	            branleadId = json.getString("id");
	            System.out.println("New Branch Lead id from response: " + branleadId);
	          
	        } else {
	            System.out.println("Insertion unsuccessful. Status code returned is " + statusCode);
	        }
	    } catch (JSONException e) {
	        System.out.println("Issue creating JSON or processing results");
	        e.printStackTrace();
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    } catch (NullPointerException npe) {
	        npe.printStackTrace();
	    }
	    
	    objMap.put("branchLeadId", branleadId);
		/*
		 * prop.setProperty("BranchLeadsfdcID", branchleadId); try { prop.store(output,
		 * "BranchLead record created"); output.close(); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
	  
	 }   
 
	static String businessLeadId = prop.getProperty("BusinessLeadsfdcID");
 
 public static void validateBusinessLead() {
 	
 	JSONArray j = null;
 	
     System.out.println("\n_______________ Business Lead QUERY _______________");
     try {

         //Set up the HTTP objects needed to make the request.
         HttpClient httpClient = HttpClientBuilder.create().build();
    
         String uri = baseUri + "/query?q=select+Id+,+Status+from+Lead+where+Id+='" +prop.getProperty("BusinessLeadsfdcID")+"'";
         System.out.println("Query URL: " + uri);
         HttpGet httpGet = new HttpGet(uri);
         System.out.println("oauthHeader2: " + oauthHeader);
         httpGet.addHeader(oauthHeader);
         httpGet.addHeader(prettyPrintHeader);

         // Make the request.
         HttpResponse response = httpClient.execute(httpGet);

         // Process the result
         int statusCode = response.getStatusLine().getStatusCode();
         if (statusCode == 200) {
             String response_string = EntityUtils.toString(response.getEntity());
             try {
                 JSONObject json = new JSONObject(response_string);
                 System.out.println("JSON result of Query:\n" + json.toString(1));
                 
                 	
                     leadId = json.getJSONArray("records").getJSONObject(0).getString("Id");
                     leadStatus = json.getJSONArray("records").getJSONObject(0).getString("Status");
                               //System.out.println(taskDescription);
                     System.out.println("Lead record is: "  + leadId + " " + leadStatus);
                    // return j;
             
             } catch (JSONException je) {
                 je.printStackTrace();
             }
            
             
         } else {
             System.out.println("Query was unsuccessful. Status code returned is " + statusCode);
             System.out.println("An error has occured. Http status: " + response.getStatusLine().getStatusCode());
             System.out.println(getBody(response.getEntity().getContent()));
             System.exit(-1);
         }
     } catch (IOException ioe) {
         ioe.printStackTrace();
     } catch (NullPointerException npe) {
         npe.printStackTrace();
     }
	 }


public static void hashmapdata() {
	// TODO Auto-generated method stub
	
	System.out.println(objMap.get("sfdcId"));
	   System.out.println(objMap.keySet());
	   System.out.println(objMap.values());
	   System.out.println(objMap);
	   
	
}


public static void createBranchOpp(String fname1, String lname1) throws InterruptedException {
	 
	Thread.sleep(5000);
	
	
	
	System.out.println("\n_______________ Creating Branch Opportunity_______________");
	
	String uri = baseUri + "/sobjects/Opportunity";
	String oppname = fname1+" "+ lname1+" "+"-"+" "+"Retail - Branch Management";
	
    try {
    	 
        //create the JSON object containing the new lead details.
        JSONObject branchopp = new JSONObject();
        branchopp.put("RecordTypeId","0120R0000005GRSQA2");
        
        if (fname1.contains("spouse")){branchopp.put("AccountId",objMap.get("spousesfdcId"));}
        else {branchopp.put("AccountId",objMap.get("sfdcId"));}
        
        branchopp.put("Description","Demo Purpose");
        branchopp.put("Type","Referral");
        branchopp.put("LeadSource","NAC Outbound");
        branchopp.put("Agent_Input_Expected_Amount__c","125000");
        branchopp.put("StageName","New");
        branchopp.put("CloseDate",futureDate);
        branchopp.put("Name",fname1+" "+lname1);
        branchopp.put("Preferred_Meeting_Zip_Code__c","85016");
        branchopp.put("OwnerId", "0050R000000XRFmQAO");
        branchopp.put("QMM_Disclosure__c","QMM Disclosure Not Required");
        
          
        System.out.println("JSON for Branch Lead record to be inserted:\n" +branchopp.toString(1));

        //Construct the objects needed for the request
        HttpClient httpClient = HttpClientBuilder.create().build();
        
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader(oauthHeader);
        httpPost.addHeader(prettyPrintHeader);
        // The message we are going to post
        
        StringEntity body = new StringEntity(branchopp.toString(1));
        body.setContentType("application/json");
        httpPost.setEntity(body);

        //Make the request
        HttpResponse response = httpClient.execute(httpPost);

        Thread.sleep(5000);
        
        System.out.println("Response: " +response);
        
        
        //Process the results
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("status code: " +statusCode);
        
        if (statusCode == 201) {
            String response_string = EntityUtils.toString(response.getEntity());
            JSONObject json = new JSONObject(response_string);
            // Store the retrieved retail id to use when we update the retail.
            String branoppId = json.getString("id");
            System.out.println("New Branch Lead id from response: " + branoppId);
          
        } else {
            System.out.println("Insertion unsuccessful. Status code returned is " + statusCode);
        }
    } catch (JSONException e) {
        System.out.println("Issue creating JSON or processing results");
        e.printStackTrace();
    } catch (IOException ioe) {
        ioe.printStackTrace();
    } catch (NullPointerException npe) {
        npe.printStackTrace();
    }
    
   // objMap.put("branchLeadId", branleadId);
	/*
	 * prop.setProperty("BranchLeadsfdcID", branchleadId); try { prop.store(output,
	 * "BranchLead record created"); output.close(); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 */
  
 }   


public static void createBranchOpp_household(String fname1, String lname1) throws InterruptedException {
	 
	Thread.sleep(5000);
	
	
	
	System.out.println("\n_______________ Creating Branch Opportunity_______________");
	
	String uri = baseUri + "/sobjects/Opportunity";
	String oppname = fname1+" "+ lname1+" "+"-"+" "+"Retail - Branch Management";
	
    try {
    	 
        //create the JSON object containing the new lead details.
        JSONObject branchopp = new JSONObject();
        branchopp.put("RecordTypeId","0120R0000005GRSQA2");
        
        if (fname1.contains("spouse")){branchopp.put("AccountId",objMap.get("spousesfdcId"));}
        else {branchopp.put("AccountId",objMap.get("householdsfdcId"));}
        
        branchopp.put("Description","Demo Purpose");
        branchopp.put("Type","Referral");
        branchopp.put("LeadSource","NAC Outbound");
        branchopp.put("Agent_Input_Expected_Amount__c","125000");
        branchopp.put("StageName","New");
        branchopp.put("CloseDate",futureDate);
        branchopp.put("Name",fname1+" "+lname1);
        branchopp.put("Preferred_Meeting_Zip_Code__c","85016");
        branchopp.put("OwnerId", "0050R000000XRFmQAO");
        branchopp.put("QMM_Disclosure__c","QMM Disclosure Not Required");
        
          
        System.out.println("JSON for Branch Lead record to be inserted:\n" +branchopp.toString(1));

        //Construct the objects needed for the request
        HttpClient httpClient = HttpClientBuilder.create().build();
        
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader(oauthHeader);
        httpPost.addHeader(prettyPrintHeader);
        // The message we are going to post
        
        StringEntity body = new StringEntity(branchopp.toString(1));
        body.setContentType("application/json");
        httpPost.setEntity(body);

        //Make the request
        HttpResponse response = httpClient.execute(httpPost);

        Thread.sleep(5000);
        
        System.out.println("Response: " +response);
        
        
        //Process the results
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("status code: " +statusCode);
        
        if (statusCode == 201) {
            String response_string = EntityUtils.toString(response.getEntity());
            JSONObject json = new JSONObject(response_string);
            // Store the retrieved retail id to use when we update the retail.
            String branoppId = json.getString("id");
            System.out.println("New Branch Lead id from response: " + branoppId);
          
        } else {
            System.out.println("Insertion unsuccessful. Status code returned is " + statusCode);
        }
    } catch (JSONException e) {
        System.out.println("Issue creating JSON or processing results");
        e.printStackTrace();
    } catch (IOException ioe) {
        ioe.printStackTrace();
    } catch (NullPointerException npe) {
        npe.printStackTrace();
    }
    
   // objMap.put("branchLeadId", branleadId);
	/*
	 * prop.setProperty("BranchLeadsfdcID", branchleadId); try { prop.store(output,
	 * "BranchLead record created"); output.close(); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 */
  
 }   





public static void createService() {
	 
	System.out.println("\n_______________ Creating Service, which updates CP value______________");
	
	String uri = baseUri + "/sobjects/Asset";
	
    try {
    	 
        //create the JSON object containing the new lead details.
        JSONObject asset = new JSONObject();
        
   
     
       
        asset.put("RecordTypeId", "0120R0000005GRGQA2");
        asset.put("Status", "Enrolled");
        asset.put("AccountId", objMap.get("sfdcId"));
        asset.put("Name", "Branch Management");
        
        
        System.out.println("JSON for Asset record to be inserted:\n" +asset.toString(1));

        //Construct the objects needed for the request
        HttpClient httpClient = HttpClientBuilder.create().build();
        
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader(oauthHeader);
        httpPost.addHeader(prettyPrintHeader);
        // The message we are going to post
        
        StringEntity body = new StringEntity(asset.toString(1));
        body.setContentType("application/json");
        httpPost.setEntity(body);

        //Make the request
        HttpResponse response = httpClient.execute(httpPost);

        //Process the results
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 201) {
            String response_string = EntityUtils.toString(response.getEntity());
            JSONObject json = new JSONObject(response_string);
            // Store the retrieved retail id to use when we update the retail.
            String assetId = json.getString("id");
            System.out.println("New Asset id from response: " + assetId);
          
        } else {
            System.out.println("Insertion unsuccessful. Status code returned is " + statusCode);
        }
    } catch (JSONException e) {
        System.out.println("Issue creating JSON or processing results");
        e.printStackTrace();
    } catch (IOException ioe) {
        ioe.printStackTrace();
    } catch (NullPointerException npe) {
        npe.printStackTrace();
    }
    
   // objMap.put("branchLeadId", branleadId);
	/*
	 * prop.setProperty("BranchLeadsfdcID", branchleadId); try { prop.store(output,
	 * "BranchLead record created"); output.close(); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 */
  
 }   





public void set_uid(String userid) {uid = userid;}
public void set_fname(String firstname) {fname = firstname;}
public void set_lname(String lastname) {lname = lastname;}
public void set_bname(String busname) {bname = busname;}

public void set_accountId(String AccountId) {accountId = AccountId;}

public String get_accountId(){return accountId;}






 
}
