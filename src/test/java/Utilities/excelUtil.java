package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;

public class excelUtil {

     String path; 

public excelUtil (String path){
    this.path = path;
}

    public static int getRowCount(String sheetName) throws IOException {
        String path ="C:\\Users\\Hamid-Post\\IdeaProjects\\TekSchoolRestAssured\\testData\\Book1.xlsx";
        FileInputStream fi = null;
        XSSFWorkbook workbook = null;
        int rowCount = 0;

        try {
            // Open the file
            fi = new FileInputStream(path);

            // Load the workbook
            workbook = new XSSFWorkbook(fi);

            // Get the sheet
            XSSFSheet sheet = workbook.getSheet(sheetName);

            // Get the row count
            if (sheet != null) {
                rowCount = sheet.getLastRowNum(); // Returns the index of the last row (0-based)
            }
        } finally {
            // Close resources
            if (workbook != null) workbook.close();
            if (fi != null) fi.close();
        }

        return rowCount;
    }


    public static int getColumnCount(String filePath, int sheetIndex) {
        try (FileInputStream file = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(file)) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);

            // Assuming the first row contains the maximum number of columns
            Row firstRow = sheet.getRow(0); // Get the first row
            if (firstRow != null) {
                return firstRow.getPhysicalNumberOfCells(); // Count non-empty cells in the row
            } else {
                return 0; // If the first row is null, return 0
            }
        } catch (Exception e) {
            System.out.println("Error reading Excel file: " + e.getMessage());
            return -1; // Return -1 in case of an error
        }
    }

     



    public static int getCellCount(String sheetName, int rowNum) throws IOException {

        String path ="C:\\Users\\Hamid-Post\\IdeaProjects\\TekSchoolRestAssured\\testData\\Book1.xlsx";
        FileInputStream fi = null;
        XSSFWorkbook workbook = null;
        int cellCount = 0;
        
        try {
            // Open the file
            fi = new FileInputStream(path);

            // Load the workbook
            workbook = new XSSFWorkbook(fi);

            // Get the sheet
            XSSFSheet sheet = workbook.getSheet(sheetName);

            // Get the row
            if (sheet != null && sheet.getRow(rowNum) != null) {
                cellCount = sheet.getRow(rowNum).getLastCellNum(); // Get the last cell number
            }
        } finally {
            // Close resources
            if (workbook != null) workbook.close();
            if (fi != null) fi.close();
        }

        return cellCount;
    }


    public static String getCellData(String sheetName, int rowNum, int column) throws IOException {
        String path ="C:\\Users\\Hamid-Post\\IdeaProjects\\TekSchoolRestAssured\\testData\\Book1.xlsx";

        FileInputStream fi = null;
        XSSFWorkbook workbook = null;
        String data = "";

        try {
            // Open the file
            fi = new FileInputStream(path);

            // Load the workbook
            workbook = new XSSFWorkbook(fi);

            // Get the sheet
            XSSFSheet sheet = workbook.getSheet(sheetName);

            // Get the row
            Row row = sheet.getRow(rowNum);

            // Get the cell
            Cell cell = (row != null) ? row.getCell(column) : null;

            // Format the cell value
            DataFormatter formatter = new DataFormatter();
            if (cell != null) {
                data = formatter.formatCellValue(cell); // Returns cell value as a formatted String
            }
        } catch (Exception e) {
            data = ""; // Return an empty string in case of an error
        } finally {
            // Close resources
            if (workbook != null) workbook.close();
            if (fi != null) fi.close();
        }

        return data;
    }


    @Test
    void returndata() throws IOException {
        System.out.println("Number of rows in Sheet1: " + getRowCount("Sheet1"));

        int totalRows = getRowCount("Sheet1");
        int totalColumns = getColumnCount(path, 0);

        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                String cellData = getCellData("Sheet1", i, j);
                System.out.println("Cell [" + i + "][" + j + "]: " + cellData);
            }
        }

    }

}