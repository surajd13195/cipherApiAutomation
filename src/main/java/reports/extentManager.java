package reports;

import com.aventstack.extentreports.ExtentTest;

public class extentManager {

    private extentManager(){
    }

    private static ThreadLocal<ExtentTest> extTest = new ThreadLocal<>();

    // By default - it is default (default --> it can be only accessed within the package)
    static ExtentTest getExtTest() {
        return extTest.get();
    }

    // By default - it is default
    static void setExtTest(ExtentTest test) {
        extTest.set(test);
    }

    // By default - it is default
    static void unload(){
        extTest.remove();
    }
}