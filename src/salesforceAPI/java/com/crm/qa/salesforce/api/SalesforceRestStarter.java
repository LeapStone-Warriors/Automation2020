package com.crm.qa.salesforce.api;

import com.crm.qa.base.TestBase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SalesforceRestStarter extends TestBase {

    private static final String TOKEN_URL =  "https://test.salesforce.com/services/oauth2/token";

    public static Map<String, String> SalesforceToken() throws Exception {

    	final String username = prop.getProperty("username");
        final String password = prop.getProperty("password");
        final String consumerKey  =  prop.getProperty("SFDC_API_ClientID");
        final String consumerSecret =  prop.getProperty("SFDC_API_ClientSecret");
    	final String accessToken;
        final String instanceUrl;
        final JsonNode loginResult;
             
        Map<String, String> SFTokenandInstanceURL = new HashMap<String, String>();
        
     try {
            // login
            final CloseableHttpClient httpclient = HttpClients.createDefault();

            final List<NameValuePair> loginParams = new ArrayList<NameValuePair>();
            loginParams.add(new BasicNameValuePair("client_id", consumerKey));
            loginParams.add(new BasicNameValuePair("client_secret", consumerSecret));
            loginParams.add(new BasicNameValuePair("grant_type", "password"));
            loginParams.add(new BasicNameValuePair("username", username));
            loginParams.add(new BasicNameValuePair("password", password));

            final HttpPost post = new HttpPost(TOKEN_URL);
            post.setEntity(new UrlEncodedFormEntity(loginParams));

            final HttpResponse loginResponse = httpclient.execute(post);

            // parse
            final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

             loginResult = mapper.readValue(loginResponse.getEntity().getContent(), JsonNode.class);
             accessToken = loginResult.get("access_token").asText();
             instanceUrl = loginResult.get("instance_url").asText();
             
             SFTokenandInstanceURL.put("accessToken", accessToken);
             SFTokenandInstanceURL.put("instanceURL", instanceUrl);
         
        	}	catch (IOException e) {e.printStackTrace();}
   
        return SFTokenandInstanceURL;
    
    }

    
    
}
