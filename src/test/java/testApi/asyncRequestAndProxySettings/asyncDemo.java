package testApi.asyncRequestAndProxySettings;

import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;
import org.testng.annotations.Test;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static io.restassured.RestAssured.given;

public class asyncDemo {

    @Test(priority = 0)
    public static void syncRequest(){

        given()
                .when()
                .get("https://reqres.in/api/users?delay=5")
                .then()
                .log().all()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");
    }

    @Test(priority = 1)
    public static void asyncRequest() throws ExecutionException, InterruptedException {

        Future<Response> responseFuture = Dsl.asyncHttpClient().prepareGet("https://reqres.in/api/users?delay=5").execute();
        Response response = responseFuture.get();

        System.out.println("Response: " +response);
        System.out.println("Response code: " +response.getStatusCode());
    }
}