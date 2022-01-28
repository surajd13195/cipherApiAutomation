package cipherApi.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class excelUtils {

    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    // Constructor to get excel path & sheet name
    public excelUtils(String excelPath, String sheetName){

        try {
            workbook = new XSSFWorkbook(excelPath);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }

    public static int getRowCount(){

        int rowCount = sheet.getPhysicalNumberOfRows();
        System.out.println("Number of rows: " +rowCount);
        return rowCount;
    }

    public static int getCellCount(){

        int cellCount = sheet.getRow(0).getPhysicalNumberOfCells();
        System.out.println("Number of cells: " +cellCount);
        return cellCount;
    }

    public static String getCellData(int rowNum, int colNum) throws IOException {

        DataFormatter formatter = new DataFormatter();
        Object value = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
        //System.out.println("Cell data: " +value);
        workbook.close();
        return (String) value;
    }

    public static String getCellDataString(int rowNum, int colNum) throws IOException {

        String value = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
        System.out.println("Cell data: " +value);
        workbook.close();
        return value;
    }

    public static double getCellDataNumeric(int rowNum, int colNum) throws IOException {

        double value = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
        System.out.println("Cell data: " +value);
        workbook.close();
        return value;
    }

    /*public Object[][] testData() {

        //excelUtils excel = new excelUtils(excelPath, sheetName);
        int rowCount = getRowCount();
        int cellCount = getCellCount();

        Object data[][] = new Object[rowCount-1][cellCount];

        for(int i=2; i<rowCount; i++){
            for(int j=0; j<cellCount; j++){

                String cellData = getCellDataString(i, j);
                data[i-2][j] = cellData;
            }
        }
        return data;
    }*/

    public static void main(String[] args){
        //getRowCount();
        //getCellData(1,0);
        //getRowCount();
    }
}