package cipherApi;

import constants.frameworkConstants;
import enums.configProperties;
import listeners.testNGListeners;
import utils.propertyUtils;
import utils.readConfig;
import cipherTokenGen.readPojo;
import cipherTokenGen.setData;
import utils.excelDataProvider;
import utils.excelUtils;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.CellType;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;

@Listeners(testNGListeners.class)
public class userLogin extends readConfig {

    public static HashMap map = new HashMap();
    String url;
    String sheetName;
    long expectedResponseTime;

    {
        try {
            url = propertyUtils.get(configProperties.LOGINURL);
            sheetName = propertyUtils.get(configProperties.LOGINSHEETNAME);
            expectedResponseTime = frameworkConstants.getExpectedResponseTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 0)
    public void loginWithValidToken(ITestContext context) throws Exception {

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

        String sessionId = response.path("sessionId");
        context.setAttribute("generatedSessionId",sessionId);


        excelUtils excelUtils = new excelUtils("PerformActivity");
        int rowCount = excelUtils.getRowCount();
        if(response.statusCode()==200) {
            for(int i=1; i<rowCount-3; i++) {
                excelUtils.writeData(sessionId, i, 9, CellType.STRING);
            }
        }

        /*excelUtils excelUtils = new excelUtils(excelPath, sheetName);
        if(response.statusCode()==200) {
            System.out.println("---------------------------------------------------------------------------");
            excelUtils.writeData(excelPath, "Passed", 1, 13, CellType.STRING);
            excelUtils.writeData(excelPath, response.statusCode(), 1, 14, CellType.NUMERIC);
            excelUtils.writeData(excelPath, response.asString(), 1, 15, CellType.STRING);
        }else {
            excelUtils.writeData(excelPath, "Failed", 1, 13, CellType.STRING);
            excelUtils.writeData(excelPath, response.statusCode(), 1, 14, CellType.NUMERIC);
            excelUtils.writeData(excelPath, response.asString(), 1, 15, CellType.STRING);
        }*/

        String generatedSessionId = response.path("sessionId");
        System.out.println("Session ID: " +generatedSessionId);
        long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
        Assert.assertTrue(responseTime<=expectedResponseTime,"Response time is not within limits");
    }

    @Test(priority = 1)
    public void loginWithInvalidToken() throws Exception {

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
                        .assertThat()
                        .body("sessionId", Matchers.nullValue())
                        .extract().response();

        String responseName = response.path("name");
        Assert.assertEquals(responseName.contains("No key"),true);
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