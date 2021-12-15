package restAssuredTest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class basicValidationXml {

    @Test(priority = 1)
    void testSingeContent(){

        given()
                .when()
                    .get("http://thomas-bayer.com/sqlrest/CUSTOMER/15/")
                .then()
                    .statusCode(200)
                    .body("CUSTOMER.ID", equalTo("15"))
                .log().all();
    }

    @Test(priority = 2)
    void testMultipContent(){

        given()
                .when()
                .get("http://thomas-bayer.com/sqlrest/CUSTOMER/15/")
                .then()
                .statusCode(200)
                .body("CUSTOMER.ID", equalTo("15"))
                .body("CUSTOMER.FIRSTNAME", equalTo("Bill"))
                .body("CUSTOMER.LASTNAME", equalTo("Clancy"))
                .body("CUSTOMER.STREET", equalTo("319 Upland Pl."))
                .body("CUSTOMER.CITY", equalTo("Seattle"))
                .log().all();
    }

    @Test(priority = 3)
    void testMultipContentInOneGo(){

        given()
                .when()
                .get("http://thomas-bayer.com/sqlrest/CUSTOMER/15/")
                .then()
                .statusCode(200)
                .body("CUSTOMER.text()", equalTo("15BillClancy319 Upland Pl.Seattle"))
                .log().all();
    }

    @Test(priority = 4)
    void testFindingValuesUsingXmlPath(){

        given()
                .when()
                .get("http://thomas-bayer.com/sqlrest/CUSTOMER/15/")
                .then()
                .statusCode(200)
                .body(hasXPath("/CUSTOMER/FIRSTNAME", containsString("Bill")))
                .log().all();
    }

    @Test(priority = 5)
    void testFindingValuesUsingXmlPath2(){

        given()
                .when()
                .get("http://thomas-bayer.com/sqlrest/INVOICE/")
                .then()
                .statusCode(200)
                .body(hasXPath("/INVOICEList/INVOICE[text()='30']"))
                .log().all();
    }
}