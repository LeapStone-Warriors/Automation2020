package com.crm.qa.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelWrite extends TestBase{

	
	static String path = "C://Users//ISharaf//Desktop//ExcelFile//NWB.xlsx";
	static List<String> VarArray = new LinkedList<>();
	
	 
	Properties cloned = (Properties)prop.clone();
	
	 	public void writeTestCaseResults(String ...Arguments ) throws IOException, InvalidFormatException {

				
				//List<String> VarArray = new LinkedList<>();
				VarArray.clear();
				
				//Add Arguments to List
				for (String Arg: Arguments){VarArray.add(Arg);}
				
				
				InputStream inp = new FileInputStream(path);
				
				// Access excel file.....
				Workbook wb = WorkbookFactory.create(inp);
				
				// Get the SheetNumber index start with 0.
				Sheet sheet = wb.getSheetAt(0);
				
				// Get the last row number of the current sheet.
				int num = sheet.getLastRowNum();
				
				// Increase the last row number with 1.
				Row row = sheet.createRow(++num);
				
				
				for (int i = 0; i<6; i++){
					// Create a new cell and set the value or data.
					row.createCell(i).setCellValue(VarArray.get(i));
								
					}
					
				for (int i = 0; i<6; i++){
					// Remove first six Array Element. Only multiple validation fields will be left in Array.
					VarArray.remove(0);
					}
				
				//Print fields in excel sheet that needs to be validated
				String[] OutputResult = new String[VarArray.size()];
				OutputResult = VarArray.toArray(OutputResult);
				String OResult = String.join(",", OutputResult);
				row.createCell(6).setCellValue(OResult);
				
				// Now this Write the output to a file
				FileOutputStream fileOut = new FileOutputStream(path);
				
				// Write the data
				wb.write(fileOut);
				
				// close the connection
				fileOut.close();
				
	}
	
	
	 	public void writeValidationResults(String ...Arguments ) throws IOException, InvalidFormatException {
		
		
				//Creating a list of Array for Validation fields
				List<String> ValdList = new LinkedList<>();
				ValdList.clear();
				
				
				//Moving all the Arguments to List
				for (String Arg: Arguments){ValdList.add(Arg);}
				
				
				InputStream inp = new FileInputStream(path);
				
				// Access excel file.....
				Workbook wb = WorkbookFactory.create(inp);
				
				// Get the SheetNumber index start with 0.
				Sheet sheet = wb.getSheetAt(0);
				
				// Get the last row number of the current sheet.
				int num = sheet.getLastRowNum();
				
				// Increase the last row number with 1.
				Row row = sheet.getRow(num++);
		
				
				//Print Validation output
				String[] NewResult = new String[VarArray.size()];
				NewResult= ValdList.toArray(NewResult);
				String OutResult = String.join(",", NewResult);
				row.createCell(7).setCellValue(OutResult);
				
			
				// Now this Write the output to a file
				FileOutputStream fileOut = new FileOutputStream(path);
				
				// Write the data
				wb.write(fileOut);
				
				// close the connection
				fileOut.close();
		
	 	}
	
	/*
	public static void main(String...strings) throws IOException, InvalidFormatException{
	
		writeTestCaseResults("LogActivity", "Not Reached","1", "GetUsername", "imransharaf", "Pass", "Output1","Output2");
		writeValidationResults("This", "New", "Value", "Yes");

	}
*/

	 	
}