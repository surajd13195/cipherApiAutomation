package restAssuredTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class oAuth2HardCodedToken {

    @BeforeClass
    public void postData(){

        RestAssured.baseURI= "http://coop.apps.symfonycasts.com/api";
        RestAssured.basePath= "/2652/chickens-feed";
    }

    @Test
    public void test1(){

        Response res = RestAssured
                .given()
                .auth()
                .oauth2("b79eaa9348c6821639896f97b073ac3abbefc13e\n")
                .post();
                //.post("http://coop.apps.symfonycasts.com/api/2652/chickens-feed");
        System.out.println("Status Code: " +res.getStatusCode());
        System.out.println("Response: " +res.getBody().asString());
    }
}