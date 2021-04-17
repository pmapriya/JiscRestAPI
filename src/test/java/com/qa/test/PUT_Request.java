package com.qa.test;

import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.*;


public class PUT_Request 
{
	@Test
	public void putTest_01()
	{
		//validating status code and printing response body after put call
		
		JSONObject requestPayload = new JSONObject();    
		
		requestPayload.put("name", "Yashvi");
		requestPayload.put("job", "QA Engineer");
		
		given()
			.header("Content-Type","application/json")
			.body(requestPayload.toJSONString())
				.when().put("https://reqres.in/api/users/2")
					.then().statusCode(200).log().all();
	}
	

}
