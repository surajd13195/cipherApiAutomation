package testApi.dataDrivenTesting.test;

import testApi.dataDrivenTesting.utils.excelUtils;

public class excelUtilsTest {

    public static void main(String[] args) {

        String excelPath = "data/testData.xlsx";
        String sheetName = "Data";

        excelUtils excel = new excelUtils(excelPath, sheetName);
        excel.getRowCount();
        excel.getCellData(1,0);
    }
}