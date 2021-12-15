package restAssuredTest;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class postRequest {

    public static HashMap map = new HashMap();

    @BeforeClass
    public void postData(){

        map.put("name", restUtils.getName());
        map.put("job", restUtils.getJob());
        map.put("email", restUtils.getEmail());
        map.put("empsalary", restUtils.getEmpSalary());

        RestAssured.baseURI= "https://reqres.in/api";
        RestAssured.basePath= "/users";
    }

    @Test
    public void testData(){

        given()
                    .contentType("application/json")
                    .body(map)
                .when()
                    .post()
                .then()
                    .statusCode(201)
                    .and()
                    .statusLine("HTTP/1.1 201 Created");
                    //.and()
                    //.body("job", equalTo("leader"));
    }
}