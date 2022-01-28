package cipherApi.cipherLogin;

import cipherApi.resources.readConfig;
import cipherApi.resources.readPojo;
import cipherApi.resources.setData;
import cipherApi.utils.excelDataProvider;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class userLogin extends readConfig {

    public static HashMap map = new HashMap();
    String url = loadProperties().getProperty("loginUrl");
    String excelPath = loadProperties().getProperty("excelPath");
    String sheetName = loadProperties().getProperty("registrationSheetName");

    @Test(priority = 0)
    public void loginWithValidToken() throws Exception {

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
                        .assertThat()
                        .body("sessionId", Matchers.notNullValue())
                        .body("sessionId.length()", Matchers.is(36))
                        //.body("sessionId", Matchers.matchesRegex("^[a-z0-9]+$"))
                        .extract().response();

        String generatedSessionId = response.path("sessionId");
        System.out.println("Session ID: " +generatedSessionId);
    }

    @Test(priority = 1)
    public void loginWithInvalidToken() throws Exception {

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
                        .assertThat()
                        .body("sessionId", Matchers.nullValue())
                        .extract().response();

        String responseName = response.path("name");
        Assert.assertEquals(responseName.contains("Error in calling login"),true);
    }

    @DataProvider(name = "testLogin")
    public Object[][] getData() throws IOException {

        excelDataProvider excelDP = new excelDataProvider();
        Object data[][] = excelDP.testData(excelPath, sheetName);
        return data;
    }

    @Test(priority = 2, dataProvider="testLogin", enabled = true)
    public void loginWithMultipleDataSet(String bankId, String mobileNumber, String crnNo, String bankAppVersion, String deviceId, String deviceIpAddress, String deviceOs, String deviceOsVersion, String activityType, String sessionId, String statusCode, String statusLine, String message) throws Exception {

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
                        /*.assertThat()
                        .body("sessionId", Matchers.notNullValue())
                        .body("sessionId.length()", Matchers.is(36))*/
                        .extract().response();

        int responseCode = response.getStatusCode();
        String responseBody = response.asString();

        if(responseCode == 200){
            Assert.assertEquals(responseBody.contains("sessionId"),true);
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