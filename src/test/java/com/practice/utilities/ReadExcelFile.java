package com.practice.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadExcelFile {

    public static FileInputStream inputStream;
    public static XSSFWorkbook workbook;
    public static XSSFSheet excelSheet;
    public static XSSFRow row;
    public static XSSFCell cell;

    public static String getCellValue(String fileName, String sheetName, int rowNo, int cellNo) throws IOException {

        //System.out.println("Row " + rowNo + " Cell " + cellNo + " - Type: " + cell.getCellType() + " | Value: " + cell.toString());

        try {
            inputStream = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(inputStream);
            excelSheet = workbook.getSheet(sheetName);
            cell = workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo);

            workbook.close();
            return cell.getStringCellValue();
        }
        catch (Exception e)

        {
            return "";

        }
    }

    public static int getRowCount(String fileName, String sheetName) throws IOException {
        inputStream = new FileInputStream(fileName);
        workbook = new XSSFWorkbook(inputStream);
        excelSheet = workbook.getSheet(sheetName);

        int totalRows = 0;
        for (Row row : excelSheet) {
            if (row != null && row.getCell(0) != null && row.getCell(0).getCellType() != CellType.BLANK) {
                totalRows++;
            }
        }
        workbook.close();
        return totalRows;
    }

    public static int getCellCount(String fileName, String sheetName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet excelSheet = workbook.getSheet(sheetName);
            Row headerRow = excelSheet.getRow(0);

            if (headerRow == null) {
                return 0;
            }

            int nonEmptyCellCount = 0;
            for (Cell cell : headerRow) {
                if (cell != null && cell.getCellType() != CellType.BLANK && cell.toString().trim().length() > 0) {
                    nonEmptyCellCount++;
                }
            }
            return nonEmptyCellCount;
        } catch (Exception e) {
            return 0;
        }
    }

}
