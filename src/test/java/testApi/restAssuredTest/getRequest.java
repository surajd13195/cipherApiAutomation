package testApi.restAssuredTest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class getRequest {

    @Test
    public void getUserDetails(){

        given()
                .when()
                    .get("https://reqres.in/api/users/2")
                .then()
                    .statusCode(200)
                    .statusLine("HTTP/1.1 200 OK")
                    .assertThat().body("data.id", equalTo(2))
                    .assertThat().body("data.first_name", equalTo("Janet"))
                    .header("Content-Type", "application/json; charset=utf-8");
    }
}