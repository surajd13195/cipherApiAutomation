package testApi.restAssuredTest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class basicValidations {

    @Test(priority = 1)
    public void testStatusCode(){

        given()
                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .statusCode(200);
    }

    @Test(priority = 2)
    public void testLogging(){

        given()
                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .statusCode(200)
                    .log().all();
    }

    @Test(priority = 3)
    public void testSingleContent(){

        given()
                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .statusCode(200)
                    .log().all()
                    .body("data.first_name", hasItem("Michael"));
    }

    @Test(priority = 4)
    public void testMultipleContent(){

        given()
                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .statusCode(200)
                    .log().all()
                    .body("data.first_name", hasItems("Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"));
    }
    @Test(priority = 5)
    public void testParametersAndHeaders(){

        given()
                    .param("MyName","STest")
                    .header("MyHeader","Indian")
                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .statusCode(200)
                    .log().all();
    }
}