package cipherApi.cipherLogin;

import cipherApi.resources.setData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class userLogin {

    public static HashMap map = new HashMap();

    @BeforeClass
    public void loginData() throws Exception {

        RestAssured.baseURI= "http://localhost:9000/api/v1/cipher";
    }

    @Test(priority = 0)
    public void loginWithValidToken() throws Exception {

        setData sData = new setData();
        String encryptedMessage = sData.level1ValidToken();
        map.put("token", encryptedMessage);

        RestAssured.basePath= "/login";
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
                        .assertThat()
                        .body("sessionId", Matchers.notNullValue())
                        .body("sessionId.length()", Matchers.is(36))
                        //.body("sessionId", Matchers.matchesRegex("^[a-z0-9]+$"))
                        .extract().response();

        String sessionId = response.path("sessionId");
        System.out.println("Session ID: " +sessionId);
    }

    @Test(priority = 1)
    public void loginWithInvalidToken() throws Exception {

        setData sData = new setData();
        String encryptedMessage = sData.level1InvalidToken();
        map.put("token", encryptedMessage);

        RestAssured.basePath= "/login";
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
                        .assertThat()
                        .body("sessionId", Matchers.nullValue())
                        .extract().response();

        String responseName = response.path("name");
        Assert.assertEquals(responseName.contains("Error in calling login"),true);
    }
}