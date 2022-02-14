package listeners;

import enums.configProperties;
import utils.propertyUtils;
import utils.readConfig;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retryFailedTests extends readConfig implements IRetryAnalyzer {

    private int count=0;
    //private int retries= Integer.parseInt(loadProperties().getProperty("retryCountOfFailedTC"));
    private int retries;

    {
        try {
            retries = Integer.parseInt(propertyUtils.get(configProperties.RETRYCOUNTOFFAILEDTC));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean retry(ITestResult iTestResult) {

        //boolean value = count<retries ? true:false;
        boolean value = count<retries;
        count++;
        return value;
    }
}