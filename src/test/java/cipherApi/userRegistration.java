package cipherApi;

import constants.frameworkConstants;
import enums.configProperties;
import listeners.testNGListeners;
import cipherTokenGen.readPojo;
import cipherTokenGen.setData;
import utils.propertyUtils;
import utils.readConfig;
import utils.excelDataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;

@Listeners({testNGListeners.class})
public class userRegistration extends readConfig {

    public static HashMap map = new HashMap();
    String url;
    String sheetName;
    long expectedResponseTime;

    {
        try {
            url = propertyUtils.get(configProperties.REGISTRATIONURL);
            sheetName = propertyUtils.get(configProperties.REGISTRATIONSHEETNAME);
            expectedResponseTime = frameworkConstants.getExpectedResponseTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 0)
    public void registrationWithValidToken() throws Exception {

        setData sData = new setData();
        String encryptedMessage = sData.level1ValidToken(sheetName);
        map.put("token", encryptedMessage);

        Response response =
                given()
                        .contentType("application/json")
                        .body(map)
                        .when()
                        .post(url)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .statusLine("HTTP/1.1 200 OK")
                        .and()
                        .extract().response();

        String jsonAsString = response.asString();
        Assert.assertEquals(jsonAsString.contains("User 111111 registered successfully"),true);
        long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
        Assert.assertTrue(responseTime<=expectedResponseTime,"Response time is not within limits");
    }

    //@Test(priority = 1, retryAnalyzer = retryFailedTests.class)
    @Test(priority = 1)
    public void registrationWithInvalidToken() throws Exception {

        setData sData = new setData();
        String encryptedMessage = sData.level1InvalidToken(sheetName);
        map.put("token", encryptedMessage);

        Response response =
                given()
                        .contentType("application/json")
                        .body(map)
                        .when()
                        .post(url)
                        .then()
                        .log().all()
                        .statusCode(400)
                        .statusLine("HTTP/1.1 400 Bad Request")
                        .and()
                        .extract().response();

        String responseName = response.path("name");
        Assert.assertEquals(responseName.contains("Error in calling register"),true);
        long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
        Assert.assertTrue(responseTime<=expectedResponseTime,"Response time is not within limits");
    }

    @DataProvider(name = "testData")
    public Object[][] getData() throws IOException {

        excelDataProvider excelDP = new excelDataProvider();
        Object data[][] = excelDP.testData(sheetName);
        return data;
    }

    @Test(priority = 2, dataProvider="testData", enabled = true)
    public void registrationWithMultipleDataSet(String bankId, String mobileNumber, String crnNo, String bankAppVersion, String deviceId, String deviceIpAddress, String deviceOs, String deviceOsVersion, String activityType, String sessionId, String levelTag, String statusCode, String statusLine, String message) throws Exception {

        readPojo readPojo = new readPojo(bankId, mobileNumber, crnNo, bankAppVersion, deviceId, deviceIpAddress, deviceOs, deviceOsVersion, activityType, sessionId, levelTag);
        String encryptedMessage = readPojo.readParameters();

        map.put("token", encryptedMessage);

        Response response =
                given()
                        .contentType("application/json")
                        .body(map)
                        .when()
                        .post(url)
                        .then()
                        .log().all()
                        .statusCode(Integer.parseInt(statusCode))
                        .statusLine(statusLine)
                        .and()
                        .extract().response();

        String responseBody = response.asString();
        Assert.assertEquals(responseBody.contains(message),true);
        long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
        Assert.assertTrue(responseTime<=expectedResponseTime,"Response time is not within limits");
    }
}