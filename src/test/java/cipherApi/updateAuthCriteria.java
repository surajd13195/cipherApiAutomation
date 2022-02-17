package cipherApi;

import constants.frameworkConstants;
import enums.configProperties;
import listeners.testNGListeners;
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

@Listeners(testNGListeners.class)
public class updateAuthCriteria extends readConfig {

    public static HashMap map = new HashMap();
    String url;
    String sheetName;
    long expectedResponseTime;

    {
        try {
            url = propertyUtils.get(configProperties.UPDATEAUTHCRITERIAURL);
            sheetName = propertyUtils.get(configProperties.UPDATEAUTHCRITERIASHEETNAME);
            expectedResponseTime = frameworkConstants.getExpectedResponseTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "testData")
    public Object[][] getData() throws IOException {

        excelDataProvider excelDP = new excelDataProvider();
        Object data[][] = excelDP.testData(sheetName);
        return data;
    }

    @Test(priority = 0, dataProvider="testData", enabled = true)
    public void updateAuthCriteriaWithMultipleDataSet(String bankId, String mobileNumber, String crnNo, String bankAppVersion, String deviceId, String deviceIpAddress, String deviceOs, String deviceOsVersion, String activityType, String sessionId, String levelTag, String statusCode, String statusLine, String message) throws Exception{

        map.put("activityType", activityType);
        if(levelTag.isEmpty()){
            map.put("level",null);
        }else {
            map.put("level", levelTag);
        }

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
        }else
        {
            String responseName = response.path("name");
            Assert.assertEquals(responseName.contains(message),true);
            Assert.assertEquals(responseBody.contains("true"),false);
        }
    }
}