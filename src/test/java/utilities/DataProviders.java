package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	//Data Provider 1
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		
		String filePath = ".//testData//Opencart_LoginData.xlsx";
		ExcelUtility utilityFile = new ExcelUtility(filePath);
		
		int cntRows = utilityFile.getRowCount("Sheet1");
		int cntCols = utilityFile.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String[cntRows][cntCols];
		
		for(int i= 1; i<=cntRows;i++) {
			for(int j= 0; j<cntCols;j++) {
				logindata[i-1][j] = utilityFile.getCellData("Sheet1", i, j);
			}
		}
			
		return logindata;
		
	}
}
