package constants;

import enums.configProperties;
import utils.propertyUtils;

public class frameworkConstants {

    private frameworkConstants(){
    }

    private static final String EXCELPATH = System.getProperty("user.dir")+"/data/cipherTestData.xlsx";
    private static final String EXPECTEDRESPONSETIME = null;
    private static final String CONFIGFILEPATH = System.getProperty("user.dir")+"/src/main/resources/config.properties";
    //private static final String CONFIGFILEPATH = "src/test/java/cipherApi/resources/config.properties";
    private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir")+"/extent-test-output/";
    private static String extentReportFilePath = "";

    public static String getExtentReportFilePath() throws Exception {
        if(extentReportFilePath.isEmpty()){
            extentReportFilePath=createReportPath();
        }
        return extentReportFilePath;
    }

    public static String createReportPath() throws Exception {
        if(propertyUtils.get(configProperties.OVERRIDEREPORTS).equalsIgnoreCase("no")){
            return EXTENTREPORTFOLDERPATH+System.currentTimeMillis()+"/index.html";
        }else {
            return EXTENTREPORTFOLDERPATH+"/index.html";
        }
    }

    public static String getConfigfilepath(){
        return CONFIGFILEPATH;
    }

    public static String getExcelpath(){
        return EXCELPATH;
    }

    public static long getExpectedResponseTime() throws Exception {
        long expectedResponseTime= Long.parseLong(propertyUtils.get(configProperties.EXPECTEDRESPONSETIME));
        return expectedResponseTime;
    }
}