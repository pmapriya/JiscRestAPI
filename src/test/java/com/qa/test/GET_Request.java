package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;




public class GET_Request 
{
	@Test
	public void test_01()
	{
		// validating status code ,lastname in response body 
		//and whether age is present in response header

		given().get("https://reqres.in/api/users?page=2")
		.then().statusCode(200)
		.body("data.last_name",hasItems("Lawson","Ferguson","Funke","Fields","Edwards"))
		.header("age", notNullValue());


	}

	@Test
	public void test_02()
	{
		//validating status code, id and first name in response body


		given().get("https://reqres.in/api/users/2")
		.then().statusCode(200).
		body("data.id", equalTo(2)).
		body("data.first_name", equalTo("Janet"));
	}

	@Test
	public void test_03()
	{
		//validating status code and text in response body  and printing all response payload

		given().get("https://reqres.in/api/unknown/2")
		.then().statusCode(200)
		.body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!")).log().all();
	}

	@Test
	public void test_04()
	{
		//validating status code after HTTP Get request

		Response response = get("https://reqres.in/api/users?page=2");	//Getting response payload  in response

		//	Response response =RestAssured.get("https://reqres.in/api/users?page=2");
		//response.getTime();
		
		int actual_StatusCode = response.getStatusCode();	
		Assert.assertEquals(actual_StatusCode, 200, "Status code coming is not 200");  

	}

}
