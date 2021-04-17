package com.qa.test;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.utils.Excel_Utility;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.simple.JSONObject;



public class POST_Request 
{
	//created data provider for test "postTest_01"

	@DataProvider(name ="Test_data")
	public Object[][] dataForPost()
	{
		return new Object[][] {
			{"Priya","QA Engineer","Paris"},
			{"Abhi","Developer","Delhi"}
		};
	}


	 @Test(dataProvider = "Test_data")
	public void postTest_01(String Name,String Job,String Address)
	{
		Map<String,Object> hMap = new HashMap<String,Object>();    //hashmap to send request payload

		hMap.put("name", Name);                          //getting these value from dataProvider = "Test_data"
		hMap.put("job", Job);
		hMap.put("address", Address);

		JSONObject requestPayload = new JSONObject(hMap);		// parsed request payload to JSON type

		given()												//validating status code after creating record
		.header("Content-Type","application/json")			
		.body(requestPayload.toJSONString()).
		when().
		post("https://reqres.in/api/users").
		then().statusCode(201);

	}
	
	// data provider for test "postTest_02", passing data from excel sheet

	@DataProvider(name ="TestData")		
	public Object[][] getData()
	{
		String excelPath ="C:\\Users\\AbhishekPriya\\Selenium\\JiscRestAPI\\TestData\\Post_Test_Data.xlsx";
		String sheetName = "Sheet1";


		Excel_Utility excel = new Excel_Utility(excelPath,sheetName);

		Object[][] testData  = excel.getCellData();
		return testData;
	}

	//@Test(dataProvider = "TestData")
	public void postTest_02(String email,String password)
	{

		JSONObject requestPayload = new JSONObject();    //validating status code after post http request

		requestPayload.put("email", email);
		requestPayload.put("password",password);

		given().header("Content-Type","application/json")
		.body(requestPayload.toJSONString())
		.when()
		.post("https://reqres.in/api/register")
		.then().statusCode(200);

	}
	
	@Test
	public void postTest_03()
	{

		JSONObject requestPayload = new JSONObject();    //validating status code after post http request

		requestPayload.put("email", "eve.holt@reqres.in");
		requestPayload.put("password","cityslicka");

		given().header("Content-Type","application/json")
		.body(requestPayload.toJSONString())
		.post("https://reqres.in/api/login")
		.then().statusCode(200);

	}

}
