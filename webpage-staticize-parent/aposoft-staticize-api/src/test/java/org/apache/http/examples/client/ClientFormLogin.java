package org.apache.http.examples.client;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * A example that demonstrates how HttpClient APIs can be used to perform
 * form-based logon.
 * based on 2008-2009 version 4 API http://hc.apache.org/
 */
public class ClientFormLogin {

    public static void main(String[] args) throws Exception {

    	CloseableHttpClient httpclient = HttpClients.createDefault();
    	HttpGet httpGet = new HttpGet("http://www.baidu.com");
    	CloseableHttpResponse response1 = httpclient.execute(httpGet);
    	// The underlying HTTP connection is still held by the response object
    	// to allow the response content to be streamed directly from the network socket.
    	// In order to ensure correct deallocation of system resources
    	// the user MUST call CloseableHttpResponse#close() from a finally clause.
    	// Please note that if response content is not fully consumed the underlying
    	// connection cannot be safely re-used and will be shut down and discarded
    	// by the connection manager. 
    	try {
    	    System.out.println(response1.getStatusLine());
    	    HttpEntity entity1 = response1.getEntity();
    	    // do something useful with the response body
    	    // and ensure it is fully consumed
    	    EntityUtils.consume(entity1);
    	} finally {
    	    response1.close();
    	}

    	HttpPost httpPost = new HttpPost("http://www.baidu.com");
    	List <NameValuePair> nvps = new ArrayList <NameValuePair>();
    	nvps.add(new BasicNameValuePair("username", "vip"));
    	nvps.add(new BasicNameValuePair("password", "secret"));
    	httpPost.setEntity(new UrlEncodedFormEntity(nvps));
  
    	CloseableHttpResponse response2 = httpclient.execute(httpPost);

    	try {
    	    System.out.println(response2.getStatusLine());
    	    HttpEntity entity2 = response2.getEntity();
    	    // do something useful with the response body
    	    // and ensure it is fully consumed
    	    EntityUtils.consume(entity2);
    	} finally {
    	    response2.close();
    	}
    	
    	
    }
}