package cipherApi.cipherRegistration;

import cipherApi.resources.setData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class userRegistration {

        public static HashMap map = new HashMap();

        @BeforeClass
        public void registrationData() throws Exception {

            RestAssured.baseURI= "http://localhost:9000/api/v1/cipher";
        }

        @Test(priority = 0)
        public void registrationWithValidToken() throws Exception {

            setData sData = new setData();
            String encryptedMessage = sData.level1ValidToken();
            map.put("token", encryptedMessage);

            RestAssured.basePath= "/registration";
            Response response =
            given()
                    .contentType("application/json")
                    .body(map)
                    .when()
                    .post()
                    .then()
                    .log().all()
                    .statusCode(200)
                    .statusLine("HTTP/1.1 200 OK")
                    .and()
                    .extract().response();

            String jsonAsString = response.asString();
            Assert.assertEquals(jsonAsString.contains("User 11111111111111 registered successfully"),true);
        }

    @Test(priority = 1)
    public void registrationWithInvalidToken() throws Exception {

        setData sData = new setData();
        String encryptedMessage = sData.level1InvalidToken();
        map.put("token", encryptedMessage);

        RestAssured.basePath= "/registration";
        Response response =
                given()
                        .contentType("application/json")
                        .body(map)
                        .when()
                        .post()
                        .then()
                        .log().all()
                        .statusCode(400)
                        .statusLine("HTTP/1.1 400 Bad Request")
                        .and()
                        .extract().response();

        String responseName = response.path("name");
        Assert.assertEquals(responseName.contains("Error in calling register"),true);
    }
}