package listeners;

import reports.extentLogger;
import reports.extentReport;
import org.testng.*;

import java.io.IOException;

public class testNGListeners implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        try {
            extentReport.initReports();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        try {
            extentReport.flushReports();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentReport.createTest(result.getMethod().getMethodName());
        System.out.println("**** Test is started: " +result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentLogger.pass(result.getMethod().getMethodName() +" is passed");
        System.out.println("**** Test is successful: " +result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentLogger.fail(result.getMethod().getMethodName() +" is failed");
        System.out.println("**** Test is failed: " +result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentLogger.skip(result.getMethod().getMethodName() +" is skipped");
        System.out.println("**** Test is skipped: " +result.getName());
        System.out.println("**** Test is started: " +result.getSkipCausedBy());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
        System.out.println("**** Time-out: " +result.getName());
    }

    /*public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
        System.out.println("**** Test is completed: " +context.getName());
    }*/

}