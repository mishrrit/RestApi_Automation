package com.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Client.restClient;
import com.data.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.TestUtil;

import restApi.baseClass;

public class getAPITest extends baseClass {

	baseClass base;
	String url;
	String api;
	String apiUrl;
	restClient rest;
	CloseableHttpResponse Response;

	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException {
		base = new baseClass();
		url = prop.getProperty("URL");
		api = prop.getProperty("serviceUrl");
		apiUrl = url + api;
	}

	@Test(priority = 1)
	public void getApiTest() throws ClientProtocolException, IOException {
		rest = new restClient();
		Response = rest.get(apiUrl);

		// a. Get Status Code
		int StatusCode = Response.getStatusLine().getStatusCode();
		System.out.println("Status Code #####" + StatusCode);
		Assert.assertEquals(StatusCode, RESP_STATUS_CODE_200, "In Valid Status Code");

		// b. Get Response Body
		String responseBody = EntityUtils.toString(Response.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseBody);
		System.out.println("-----------------Response from Json ----\n" + responseJson);

		// getting total elements
		String s = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println(" total is ---->" + s);
		Assert.assertEquals(s, "12");

		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		System.out.println("Avatar of the first set is -->" + avatar);

		// c. Get All Headers
		Header[] headerArray = Response.getAllHeaders();

		HashMap<String, String> hashMap = new HashMap<String, String>();

		for (Header head : headerArray) {
			hashMap.put(head.getName(), head.getValue());
		}

		System.out.println("------------ Headers are ----\n" + hashMap);
	}

	@Test(priority = 2)
	public void getApiTestwithheaders() throws ClientProtocolException, IOException {
		rest = new restClient();

		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "appication/json");
		headermap.put("username", "ritesh");
		headermap.put("password", "abc124");

		Response = rest.get(apiUrl);

		// a. Get Status Code
		int StatusCode = Response.getStatusLine().getStatusCode();
		System.out.println("Status Code #####" + StatusCode);
		Assert.assertEquals(StatusCode, RESP_STATUS_CODE_200, "In Valid Status Code");

		// b. Get Response Body
		String responseBody = EntityUtils.toString(Response.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseBody);
		System.out.println("-----------------Response from Json ----\n" + responseJson);

		// getting total elements
		String s = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println(" total is ---->" + s);
		Assert.assertEquals(s, "12");

		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		System.out.println("Avatar of the first set is -->" + avatar);

		// c. Get All Headers
		Header[] headerArray = Response.getAllHeaders();

		HashMap<String, String> hashMap = new HashMap<String, String>();

		for (Header head : headerArray) {
			hashMap.put(head.getName(), head.getValue());
		}

		System.out.println("------------ Headers are ----\n" + hashMap);
	}

	
}
