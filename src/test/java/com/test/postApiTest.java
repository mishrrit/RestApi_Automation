package com.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Client.restClient;
import com.data.User;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import restApi.baseClass;

public class postApiTest extends baseClass {
	baseClass base;
	String url;
	String api;
	String apiUrl;
	restClient rest;
	CloseableHttpResponse Response;

	@BeforeMethod
	public void setuo() {
		base = new baseClass();
		url = prop.getProperty("URL");
		api = prop.getProperty("serviceUrl");
		apiUrl = url + api;
	}

	@Test
	public void postApi() throws JsonGenerationException, JsonMappingException, IOException {
		rest = new restClient();

		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "appication/json");

		// jackson api
		ObjectMapper mapper = new ObjectMapper();
		User user = new User("morpheus", "leader");

		mapper.writeValue(new File("C:/Users/mishrrit/eclipse-workspace/restapi/src/main/java/com/data/user.json"),user);
		
		//Java Object to Java String --Marshalling 
		String jsonresp = mapper.writeValueAsString(user);
		System.out.println("User json string is --->" + jsonresp);

		Response = rest.Post(apiUrl, jsonresp, headermap);
		int StatusCode = Response.getStatusLine().getStatusCode();

		Assert.assertEquals(StatusCode, RESP_STATUS_CODE_201);

		// Get Response Body
		String responseBody = EntityUtils.toString(Response.getEntity(), "UTF-8");
			
		JSONObject responseJson = new JSONObject(responseBody);
		System.out.println("-----------------Response from Json ----" + responseJson);
		
		//Java String to Java Object-- UnMarshalling
		User user1 = mapper.readValue(responseBody, User.class);
		System.out.println(user1);

		System.out.println(user.getName()+user1.getName());
		
		System.out.println(user.getJob()+user1.getJob());
	}

}
