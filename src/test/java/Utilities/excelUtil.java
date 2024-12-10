package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class excelUtil {
;

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
        String path = "C:\\Users\\Hamid-Post\\IdeaProjects\\TekSchoolRestAssured\\testData\\Book1.xlsx";

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

            if (cell != null) {
                // Use a formula evaluator
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                DataFormatter formatter = new DataFormatter();

                // Evaluate and format the cell value
                data = formatter.formatCellValue(cell, evaluator);
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
    static public void writeIdToExcel(String sheetName, String id) throws IOException {
        String path = "C:\\Users\\Hamid-Post\\IdeaProjects\\TekSchoolRestAssured\\testData\\Book1.xlsx";
        FileInputStream fi = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fi);

        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName); // Create a new sheet if it doesn't exist
        }

        int rownum = sheet.getLastRowNum() + 1; // Get the next available row
        Row row = sheet.createRow(rownum);
        Cell cell = row.createCell(0); // Write ID in the first column
        cell.setCellValue(id);

        // Save the changes
        fi.close();
        FileOutputStream fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fo.close();
    }

    static public List<String> readIdsFromExcel(String sheetName) throws IOException {
        String path = "C:\\Users\\Hamid-Post\\IdeaProjects\\TekSchoolRestAssured\\testData\\Book1.xlsx";
        FileInputStream fi = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fi);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        List<String> ids = new ArrayList<>();
        if (sheet != null) {
            for (Row row : sheet) {
                Cell cell = row.getCell(0); // Read IDs from the first column
                if (cell != null) {
                    ids.add(cell.getStringCellValue());
                }
            }
        }

        fi.close();
        workbook.close();
        return ids;
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
