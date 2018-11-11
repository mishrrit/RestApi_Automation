package com.Client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class restClient {

	// 1. Get Method without headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		//CloseableHttpClient httpclient = HttpClients.createSystem();
		HttpGet httpGet = new HttpGet(url);
		//System.setProperty("java.net.useSystemProxies", "true");
		CloseableHttpResponse Response = httpclient.execute(httpGet);
		return Response;
	}

	// 2. Get Method with headers
	public CloseableHttpResponse get(String url, HashMap<String, String> headermap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);

		for (Map.Entry<String, String> map : headermap.entrySet()) {
			httpGet.addHeader(map.getKey(), map.getValue());
		}

		CloseableHttpResponse Response = httpclient.execute(httpGet);
		return Response;
	}

	// 3. POST Method without headers
	public CloseableHttpResponse Post(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient ClosehttpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		CloseableHttpResponse Response = ClosehttpClient.execute(post);
		return Response;

	}
	
	// 3. POST Method 
		public CloseableHttpResponse Post(String url,String entityString,HashMap<String, String> headermap) throws ClientProtocolException, IOException {

			CloseableHttpClient ClosehttpClient = HttpClients.createDefault();
			HttpPost post = new HttpPost(url);
			post.setEntity(new StringEntity(entityString));
			
			for (Map.Entry<String, String> map : headermap.entrySet()) {
				post.addHeader(map.getKey(), map.getValue());
			}
			CloseableHttpResponse Response = ClosehttpClient.execute(post);
			return Response;

		}
	

}
