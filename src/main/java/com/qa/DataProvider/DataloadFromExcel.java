package com.qa.DataProvider;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import com.crm.qa.util.ReadFromExcel;

public class DataloadFromExcel {

	
    @DataProvider(name="excelData")
    public Object[][] readExcel() throws InvalidFormatException, IOException {
	        return ReadFromExcel.readExcel();
    }







}
