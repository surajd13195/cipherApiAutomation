package reports;

public final class extentLogger {

    private extentLogger() {
    }

    public static void pass(String message) {
        extentManager.getExtTest().pass(message);
    }

    public static void fail(String message) {
        extentManager.getExtTest().fail(message);
    }

    public static void skip(String message) {
        extentManager.getExtTest().skip(message);
    }

    /*public static void pass(String message, boolean isScreenshotNeeded) throws Exception {
        if (propertyUtils.get(configProperties.PASSEDSTPESSCREENSHOT).equalsIgnoreCase("yes") && isScreenshotNeeded) {
            extentManager.getExtTest().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());
        }
    }

    public static <TakesScreenshot> String getBase64Image(){
        return ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
    }*/
}