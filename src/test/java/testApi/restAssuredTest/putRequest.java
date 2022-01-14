package testApi.restAssuredTest;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class putRequest {

    public static HashMap map = new HashMap();

    String empName = restUtils.getName();
    String empJob = restUtils.getJob();
    int id = 2;

    @BeforeClass
    public void putData(){

        map.put("name", empName);
        map.put("job", empJob);

        RestAssured.baseURI= "https://reqres.in/api";
        RestAssured.basePath= "/users/"+id;
    }

    @Test
    public void testPut() {

        given()
                    .contentType("application/json")
                    .body(map)
                .when()
                    .put()
                .then()
                    .statusCode(200)
                .log().all();
    }
}