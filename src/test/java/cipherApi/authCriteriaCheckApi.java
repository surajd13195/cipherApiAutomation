package cipherApi;

import listeners.testNGListeners;
import utils.readConfig;
import cipherTokenGen.readPojo;
import cipherTokenGen.setData;
import utils.excelDataProvider;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;

@Listeners(testNGListeners.class)
public class authCriteriaCheckApi extends readConfig {

    public static HashMap map = new HashMap();
    String url = loadProperties().getProperty("authCriteriaCheckUrl");
    String excelPath = loadProperties().getProperty("excelPath");
    String sheetName = loadProperties().getProperty("authCriteriaCheckSheetName");
    long expectedResponseTime = Long.parseLong(loadProperties().getProperty("expectedResponseTime"));

    @Test(priority = 0)
    public void getAuthCriteriaCheckWithValidToken() throws Exception {

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
                        .assertThat()
                        .body("message", Matchers.notNullValue())
                        .body("message.length()", Matchers.is(6))
                        .extract().response();

        String responseBody = response.asString();
        Assert.assertEquals(responseBody.contains("Enough"),true);
        long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
        Assert.assertTrue(responseTime<=expectedResponseTime,"Response time is not within limits");
    }

    @DataProvider(name = "testData")
    public Object[][] getData() throws IOException {

        excelDataProvider excelDP = new excelDataProvider();
        Object data[][] = excelDP.testData(sheetName);
        return data;
    }

    @Test(priority = 1, dataProvider="testData", enabled = true)
    public void getAuthCriteriaCheckWithMultipleDataSet(String bankId, String mobileNumber, String crnNo, String bankAppVersion, String deviceId, String deviceIpAddress, String deviceOs, String deviceOsVersion, String activityType, String sessionId, String statusCode, String statusLine, String message) throws Exception{

        readPojo readPojo = new readPojo(bankId, mobileNumber, crnNo,  bankAppVersion, deviceId, deviceIpAddress, deviceOs, deviceOsVersion, activityType, sessionId);
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