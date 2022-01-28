package cipherApi.cipherRegistration;

import cipherApi.pojo.*;
import cipherApi.resources.readPojo;
import cipherApi.resources.setData;
import cipherApi.resources.readConfig;
import cipherApi.utils.excelDataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class userRegistration extends readConfig {

    public static HashMap map = new HashMap();
    String url = loadProperties().getProperty("registrationUrl");
    String excelPath = loadProperties().getProperty("excelPath");
    String sheetName = loadProperties().getProperty("loginSheetName");

    @Test(priority = 0)
    public void registrationWithValidToken() throws Exception {

        setData sData = new setData();
        String encryptedMessage = sData.level1ValidToken(excelPath, sheetName);
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
        Assert.assertEquals(jsonAsString.contains("User somecrn registered successfully"),true);
    }

    @Test(priority = 1)
    public void registrationWithInvalidToken() throws Exception {

        setData sData = new setData();
        String encryptedMessage = sData.level1InvalidToken(excelPath, sheetName);
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
    }

    @DataProvider(name = "testRegistration")
    public Object[][] getData() throws IOException {

        excelDataProvider excelDP = new excelDataProvider();
        Object data[][] = excelDP.testData(excelPath, sheetName);
        return data;
    }

    @Test(priority = 2, dataProvider="testRegistration", enabled = true)
    public void registrationWithMultipleDataSet(String bankId, String mobileNumber, String crnNo, String bankAppVersion, String deviceId, String deviceIpAddress, String deviceOs, String deviceOsVersion, String activityType, String sessionId, String statusCode, String statusLine, String message) throws Exception {

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
    }
}