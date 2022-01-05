package restAssuredTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class oAuth2NotHardCodedToken {

    @BeforeClass
    public void postData() {
        RestAssured.baseURI = "http://coop.apps.symfonycasts.com/api";

    }

    @Test(priority = 0)
    public void validApiTest() {

        Response res = RestAssured
                .given()
                .formParam("client_id", "testOauthApplication")
                .formParam("client_secret", "5c46382a079029cad1be2683132660a6")
                .formParam("grant_type", "client_credentials")
                .post("http://coop.apps.symfonycasts.com/token");

        String token = res.jsonPath().get("access_token");
        System.out.println("Access Token: " + token);

        RestAssured.basePath = "/2652/chickens-feed";
        Response res1 = RestAssured
                .given()
                .auth()
                .oauth2(token)
                .post();
        //.post("http://coop.apps.symfonycasts.com/api/2652/chickens-feed");
        Assert.assertEquals(res1.getStatusCode(),200);
        System.out.println("Response: " + res1.jsonPath().prettify());
    }

    @Test(priority = 1)
    public void invalidApiTest() {

        Response res = RestAssured
                .given()
                .formParam("client_id", "testOauthApplication")
                .formParam("client_secret", "5c46382a079029cad1be2683132660a6")
                .formParam("grant_type", "client_credentials")
                .post("http://coop.apps.symfonycasts.com/token");

        String token = res.jsonPath().get("access_token");
        System.out.println("Access Token: " + token);

        RestAssured.basePath = "/2652/eggs-collect";
        Response res2 = RestAssured
                .given()
                .auth()
                .oauth2(token)
                .post();
        //.post("http://coop.apps.symfonycasts.com/api/2652/eggs-collect");
        Assert.assertEquals(res2.getStatusCode(),200);
        //System.out.println("Response: " + res2.getBody().asString());
        System.out.println("Response: " + res2.jsonPath().prettify());
    }
}