package cipherApi.utils;

import java.io.IOException;

public class excelDataProvider {

    public Object[][] testData(String excelPath, String sheetName) throws IOException {

        excelUtils excel = new excelUtils(excelPath, sheetName);
        int rowCount = excel.getRowCount();
        int cellCount = excel.getCellCount();

        Object data[][] = new Object[rowCount-1][cellCount];

        for(int i=1; i<rowCount; i++){
            for(int j=0; j<cellCount; j++){

                String cellData = excel.getCellDataString(i, j);
                data[i-1][j] = cellData;
            }
        }
        return data;
    }
}