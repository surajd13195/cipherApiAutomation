package testApi.passJsonFileAsPayload;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.File;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

public class passJsonAsPayload {

    ValidatableResponse validatableResponse;

    @Test
    public void createUser() {

        // Creating a File instance
        File jsonData = new File("data/employee.json");

        // GIVEN
        given()
                .baseUri("http://dummy.restapiexample.com/api")
                .contentType(ContentType.JSON)
                .body(jsonData)

                // WHEN
                .when()
                .post("/v1/create")

                // THEN
                .then()
                .assertThat()
                .log().all()
                .statusCode(200)
                .body("data.employee_name", equalTo("Json_Test_New"))
                .body("message", equalTo("Successfully! Record has been added."));
                //.log().all();
    }
}