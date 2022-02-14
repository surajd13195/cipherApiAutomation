package testApi.asyncRequestAndProxySettings;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class proxySettings {

    //Proxy Server - Intermediatory server virtually located between the client & the server (Used when client wants to hide information)
    //Charles application can be used for testing as it is mapped to local host (127.0.0.1) & port (88888)
    public static void main(String[] args) {

        RestAssured.proxy("127.0.0.1", 8888);
        RestAssured.
                given()
                .when()
                .get("http://localhost:3000/blogposts")
                .then()
                .log().all()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");

        //RestAssured.given().proxy("127.0.0.1", 8888).when().get("http://localhost:3000/blogposts").then().log().all();
    }
}