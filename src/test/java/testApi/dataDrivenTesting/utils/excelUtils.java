package testApi.dataDrivenTesting.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

    public static void getRowCount(){

        int rowCount = sheet.getPhysicalNumberOfRows();
        System.out.println("Number of rows: " +rowCount);
    }

    public static void getCellData(int rowNum, int colNum){

        DataFormatter formatter = new DataFormatter();
        Object value = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
        System.out.println("Cell data: " +value);
    }

    public static void main(String[] args){
        getRowCount();
        getCellData(1,0);
    }
}