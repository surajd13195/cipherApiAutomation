package serializationAndDeserialization;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class testWithSerialization {

    //Serialization
    @Test(priority = 1)
    public void registerUserSerialization(){

        /*  ArrayList<String> coursesList = new ArrayList<String>();
        coursesList.add("Java");
        coursesList.add("Selenium");  */

        registrationData data = new registrationData();
        data.setEmail("eve.holt@reqres.in");
        data.setPassword("pistol");

        RestAssured.baseURI= "https://reqres.in/api";
        RestAssured.basePath= "/register";

        given()
                    .contentType(ContentType.JSON)
                    .body(data)
                .when()
                    .post()
                .then()
                    .statusCode(200);
    }

    //Serialization
    @Test(priority = 2)
    public void detSerializedData(){

        //url is nothing but get url after posting data
        registrationData data = get("https://reqres.in/api/users/4").as(registrationData.class);
        //System.out.println(data.getRegistrationData());
        System.out.println(data.getEmail());
        Assert.assertEquals(data.getEmail(), "eve.holt@reqres.in");
    }
}