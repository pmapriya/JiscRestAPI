package com.qa.test;


import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class Delete_Request 
{
	@Test
	public void testDelete_01()
	{
		//deleting record and validating status code
		
		given()
			.delete("https://reqres.in/api/users/2")
				.then().statusCode(204);
		
	}

}
