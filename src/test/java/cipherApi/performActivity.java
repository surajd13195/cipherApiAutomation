package cipherApi;

import constants.frameworkConstants;
import enums.configProperties;
import listeners.testNGListeners;
import utils.propertyUtils;
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
public class performActivity extends readConfig {

    public static HashMap map = new HashMap();
    String url;
    String sheetName;
    long expectedResponseTime;

    {
        try {
            url = propertyUtils.get(configProperties.PERFORMACTIVITYURL);
            sheetName = propertyUtils.get(configProperties.PERFORMACTIVITYSHEETNAME);
            expectedResponseTime = frameworkConstants.getExpectedResponseTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 0)
    public void performActivityWithValidToken() throws Exception {

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
                        .body("stepUp", Matchers.notNullValue())
                        .extract().response();

        String responseBody = response.asString();
        Assert.assertEquals(responseBody.contains("false"),true);
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
    public void performActivityWithMultipleDataSet(String bankId, String mobileNumber, String crnNo, String bankAppVersion, String deviceId, String deviceIpAddress, String deviceOs, String deviceOsVersion, String activityType, String sessionId, String levelTag, String statusCode, String statusLine, String message) throws Exception{

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

        int responseCode = response.getStatusCode();
        String responseBody = response.asString();
        long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
        Assert.assertTrue(responseTime<=expectedResponseTime,"Response time is not within limits");

        if(responseCode == 200){
            Assert.assertEquals(responseBody.contains(message.toLowerCase()),true);
            Boolean stepUpValue = response.path("stepUp");
            System.out.println("stepUp: " +stepUpValue);
        }else
        {
            String responseName = response.path("name");
            Assert.assertEquals(responseName.contains(message),true);
            Assert.assertEquals(responseBody.contains("stepUp"),false);
        }
    }
}