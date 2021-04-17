package com.qa.utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class Excel_Utility 
{

	XSSFWorkbook workbook;     // excel workbook declaration
	XSSFSheet sheet;			// excel sheet declaration


	//contructor 
	public Excel_Utility(String excelPath,String sheetName)
	{
		try {
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);

		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

	// method to exract data from excel sheet
	public Object[][] getCellData()
	{
		int rowCount = sheet.getPhysicalNumberOfRows();

		Object[][] data = new Object[rowCount][2]; 

		for(int i=0 ; i< rowCount ;i++)
		{
			data[i][0] = sheet.getRow(i).getCell(0).getStringCellValue();
			data[i][1] = sheet.getRow(i).getCell(1).getStringCellValue();

			//		System.out.println("Print1 "+data[i][0]);
			//		System.out.println("Print2 "+data[i][1]);
		}
		return data;
	}


}
