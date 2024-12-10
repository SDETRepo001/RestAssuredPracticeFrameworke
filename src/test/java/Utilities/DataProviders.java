package Utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir") + "testData//Book1.xlsx";
        excelUtil xl = new excelUtil(path);

        int rownum = xl.getRowCount("Sheet1");  // Get row count from Sheet1
        int colcount = xl.getCellCount("Sheet1", 1);  // Get column count from Sheet1

        String[][] apidata = new String[rownum][colcount];  // Create 2D array for storing data

        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);  // Read data from Excel and store in array
            }
        }

        return apidata;  // Return the 2D array
    }
}
