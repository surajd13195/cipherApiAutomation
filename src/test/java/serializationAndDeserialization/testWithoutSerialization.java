package serializationAndDeserialization;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import restAssuredTest.restUtils;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;

public class testWithoutSerialization {

    public static HashMap map = new HashMap();

    @Test(priority = 1)
    public void register(){

        map.put("email", "eve.holt@reqres.in");
        map.put("password", "pistol");

        //When object is present in request
        /*  ArrayList<String> coursesList = new ArrayList<String>();
        coursesList.add("Java");
        coursesList.add("Selenium");
        map.put("courses", coursesList);   */

        RestAssured.baseURI= "https://reqres.in/api";
        RestAssured.basePath= "/register";

        given()
                    .contentType(ContentType.JSON)
                    .body(map)
                .when()
                    .post()
                .then()
                    .statusCode(200);

                //To verify response parameter
                /*  .assertThat()
                .body("msg", equalTo("Registration Successful"));  */
    }
}
