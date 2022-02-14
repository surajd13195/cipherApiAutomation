package listeners;

import reports.extentLogger;
import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

@Listeners(testNGListeners.class)
public class testNGListenersDemo {

    private static Logger demoLogger;

    @Test
    public void test1(){
        //extentReport.createTest(getClass().getSimpleName());
        System.out.println("In test 1");
        extentLogger.pass("Test started");
    }

    @Test
    public void test2(){
        //extentReport.createTest("test2");
        System.out.println("In test 2");
        Assert.assertTrue(false);
        extentLogger.fail("Test Failed");
    }

    @Test(enabled = true)
    public void test3(){
        System.out.println("In test 3");
        //extentLogger.skip("Test skipped");
        throw new SkipException("This test is skipped");
    }

    @Test(enabled = false)
    public void test4(){

        demoLogger = LogManager.getLogger(testNGListenersDemo.class.getName());
        demoLogger.info("Info logged");
        demoLogger.error("Error logged");
        demoLogger.debug("Debug logged");
        demoLogger.fatal("Fatal logged");
    }
}