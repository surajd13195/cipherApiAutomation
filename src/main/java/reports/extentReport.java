package reports;

import constants.frameworkConstants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public final class extentReport {

    //Constructor (don't want to expose)
    private extentReport(){
    }

    private static ExtentReports extent;

    public static void initReports() throws Exception {
        if(Objects.isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(frameworkConstants.getExtentReportFilePath());
            extent.attachReporter(spark);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Cipher API Automation");
            spark.config().setReportName("Test API Report");
        }
    }

    public static void flushReports() throws Exception {
        if(Objects.nonNull(extent)){
            extent.flush();
        }
        Desktop.getDesktop().browse(new File(frameworkConstants.getExtentReportFilePath()).toURI());
    }

    public static void createTest(String testCaseName){
        ExtentTest test = extent.createTest(testCaseName);
        extentManager.setExtTest(test);
    }
}