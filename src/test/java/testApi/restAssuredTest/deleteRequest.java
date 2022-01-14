package testApi.restAssuredTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.ResponseCache;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class deleteRequest {

    int id = 2;

    @Test
    public void deleteUser(){

        RestAssured.baseURI= "https://reqres.in/api";
        RestAssured.basePath= "/users/"+id;

        Response response =
        given()
                .when()
                    .delete()
                .then()
                    .statusCode(204)
                    .statusLine("HTTP/1.1 204 No Content")
                .log().all()
                .extract().response();

        String jsonAsString = response.asString();
        Assert.assertEquals(jsonAsString.contains("HTTP/1.1 204 No Content"),true);
                //contains(204),true);
    }
}