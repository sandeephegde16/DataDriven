package base;

import utilities.ExcelReader;

public class exc {

	public static void main(String[] args) {
		ExcelReader excel1 = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\TestData.xlsx");
		String sheetName="AddCustomerTest";
		int rows = excel1.getRowCount(sheetName);
		System.out.println(rows);
		int cols = excel1.getColumnCount(sheetName);
		System.out.println(cols);
		Object[][] data = new Object[rows-1][cols];
		for(int rowNum=2; rowNum<=rows; rowNum++) {
			for(int colNum=0; colNum<cols; colNum++) {
				data[rowNum-2][colNum]=excel1.getCellData(sheetName, colNum, rowNum);
			}
		}
	System.out.println(data[0][0]);
	}

}
