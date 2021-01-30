package com.crm.qa.salesforce.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import javax.servlet.ServletException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;
import com.crm.qa.base.TestUtil;
import com.crm.qa.salesforce.api.SalesforceQueryBuilder;
import com.crm.qa.salesforce.api.SalesforceRestStarter;




public class SalesforceCRUDOperationAPI {

	
	final static String queryPath = "/services/data/v49.0/query/";
	final static CloseableHttpClient httpclient = HttpClients.createDefault();
	final static ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	private static String REST_ENDPOINT = "/services/data" ;
    private static String API_VERSION 	= "/v49.0" ;
    static Map<String, String> SFTokenandInstanceURL;
    static String SFDCId;
    static String instanceUrl, accessToken ;
	public static JSONObject leadDataAPI = new JSONObject();
	public static Faker faker = new Faker();
	
	
	 public SalesforceCRUDOperationAPI() {
		
		try {SFTokenandInstanceURL = SalesforceRestStarter.SalesforceToken();} 
		catch (Exception e) {	e.printStackTrace();}
		
		instanceUrl = (String) SFTokenandInstanceURL.get("instanceURL");
    	accessToken = (String) SFTokenandInstanceURL.get("accessToken");
	}
	
	
	public  JSONObject getRecord(String sObject, String leadID) throws Exception {
    
		 // query leads
        final URIBuilder builder = new URIBuilder(instanceUrl);
        builder.setPath(queryPath).setParameter("q", SalesforceQueryBuilder.getQueryFor(sObject, leadID));

        final HttpGet get = new HttpGet(builder.build());
        get.setHeader("Authorization", "Bearer " + accessToken);

        final HttpResponse queryResponse = httpclient.execute(get);
        final JsonNode queryResults = mapper.readValue(queryResponse.getEntity().getContent(), JsonNode.class);
        JSONObject json = new JSONObject(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(queryResults));
        
        int statusCode = queryResponse.getStatusLine().getStatusCode();
        
        if (statusCode == 200) {
        		try {System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(queryResults));} 
        		catch (JSONException je) {je.printStackTrace();}
          	} else {
	            System.out.println("Query was unsuccessful. Status code returned is " + statusCode);
	            System.out.println("An error has occured. Http status: " + queryResponse.getStatusLine().getStatusCode());
	            System.out.println(getBody(queryResponse.getEntity().getContent()));
	            System.exit(-1);
          	}
    
        return json;
	
	}
    
	
	public  JSONObject createLead() throws Exception {
		
		String leadId 		= null;
		String firstName 	= "Auto_"+faker.name().firstName();
		String lastName 	= faker.name().lastName();
		String email 		= TestUtil.getNewEmail();
			
		try {
			leadDataAPI.put("FirstName", firstName);
			leadDataAPI.put("LastName", lastName);
			leadDataAPI.put("Email", email);
			leadDataAPI.put("CompanyRegion__c", "New England");
			leadDataAPI.put("Company__c", "Yahoo");
			leadDataAPI.put("RoundOfFunding__c", "Series A");
			leadDataAPI.put("Street", "123 Park Lane");
			leadDataAPI.put("City", "Chandler");
			leadDataAPI.put("State", "Vermont");
			leadDataAPI.put("Country", "United States");
			leadDataAPI.put("DateOfBirth__c", "1965-11-11");
			leadDataAPI.put("Industry", "Technology - Tech Banking / GFB");
			leadDataAPI.put("InstitutionalCapitalRaised__c", "2345678");
			leadDataAPI.put("TCPSegmentTier__c", "Bronze: Low (< $500k)");
			leadDataAPI.put("Priority__c", "Immediate");
			leadDataAPI.put("ProductsofInterest__c", "Tailored Lending");
			leadDataAPI.put("ReferredBy__c", "Abel Matthews");
			leadDataAPI.put("RelationshipStartDateWithSVB__c", "2020-07-20");
			leadDataAPI.put("Title", "CTO");
			leadDataAPI.put("Website", "www.astra.com");
			leadDataAPI.put("MobilePhone", "5164432109");
			leadDataAPI.put("leadSource", "New Prospecting Tool");
			leadDataAPI.put("Description", "This Lead was created through Automation Script");
			leadDataAPI.put("CBTeamCode__c", "a0Z550000061ZyfEAE");
				
			}
			catch (JSONException e) {e.printStackTrace();throw new ServletException(e);}
			
			HttpPost httpost = new HttpPost(instanceUrl+ REST_ENDPOINT+ API_VERSION +"/sobjects/Lead");
			httpost.addHeader("Authorization", "OAuth " + accessToken);
			StringEntity messageEntity = new StringEntity( leadDataAPI.toString(), ContentType.create("application/json"));
			httpost.setEntity(messageEntity);

			// Execute the request.
			CloseableHttpResponse  closeableresponse = httpclient.execute(httpost);
			System.out.println("Response Status line :" + closeableresponse.getStatusLine());
			
			try {
			
				System.out.println("HTTP status : " + closeableresponse.getStatusLine().getStatusCode());

				if (closeableresponse.getStatusLine().getStatusCode()  == HttpStatus.SC_CREATED) {
				
					try {
					
						System.out.println("Record created successfully and here is the Response.............\n\n "); 
						// Do the needful with entity.
						HttpEntity entity = closeableresponse.getEntity();
						InputStream rstream = entity.getContent();
						JSONObject authResponse = new JSONObject(new JSONTokener(rstream));
						
						System.out.println("Create response: " + authResponse.toString(2));

						if (authResponse.getBoolean("success")) {
							leadId = authResponse.getString("id");
							System.out.println("New record id " + leadId + "\n\n");
							leadDataAPI.put("leadSFDCId", leadId);
						}
						
					} catch (JSONException e) {e.printStackTrace();}
				}
			}
			finally {httpclient.close();}
			
			return leadDataAPI;
			
		}
		
	
		
	private  String getBody(InputStream inputStream) {
		String result = "";
		try {
		      BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		            
		      String inputLine;
		         while ( (inputLine = in.readLine() ) != null ) {
		               result += inputLine;
		               result += "\n";
		         } in.close();
		    } catch (IOException ioe) {ioe.printStackTrace();}
		        
		        return result;
		 }
		
}
