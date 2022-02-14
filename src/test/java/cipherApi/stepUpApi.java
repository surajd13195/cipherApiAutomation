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
public class stepUpApi extends readConfig {

    public static HashMap map = new HashMap();
    String url = loadProperties().getProperty("stepUpUrl");
    String excelPath = loadProperties().getProperty("excelPath");
    String sheetName = loadProperties().getProperty("stepUpSheetName");
    long expectedResponseTime = Long.parseLong(loadProperties().getProperty("expectedResponseTime"));

    @Test(priority = 0)
    public void stepUpValidToken() throws Exception {

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
                        .body("sessionId", Matchers.notNullValue())
                        .body("sessionId.length()", Matchers.is(36))
                        //.body("sessionId", Matchers.matchesRegex("^[a-z0-9]+$"))
                        .extract().response();

        String generatedSessionId = response.path("sessionId");
        System.out.println("Session ID: " +generatedSessionId);
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
    public void stepUpWithMultipleDataSet(String bankId, String mobileNumber, String crnNo, String bankAppVersion, String deviceId, String deviceIpAddress, String deviceOs, String deviceOsVersion, String activityType, String sessionId, String statusCode, String statusLine, String message) throws Exception{

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
                        //.statusCode(Integer.parseInt(statusCode))
                        //.statusLine(statusLine)
                        .and()
                        /*.assertThat()
                        .body("sessionId", Matchers.notNullValue())
                        .body("sessionId.length()", Matchers.is(36))*/
                        .extract().response();

        int responseCode = response.getStatusCode();
        String responseBody = response.asString();
        long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
        Assert.assertTrue(responseTime<=expectedResponseTime,"Response time is not within limits");

        if(responseCode == 200){
            Assert.assertEquals(responseBody.contains(message),true);
            String generatedSessionId = response.path("sessionId");
            System.out.println("Session ID: " +generatedSessionId);
        }else
        {
            String responseName = response.path("name");
            Assert.assertEquals(responseName.contains(message),true);
            Assert.assertEquals(responseBody.contains("sessionId"),false);
        }
    }
}