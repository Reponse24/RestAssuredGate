package com.rest.gate.tests;
import com.rest.gate.base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FormUrlEncodingTest extends BaseTest {


@Test
    public void formUrlEncodedTest() {

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("username", "Pacifique")
                .formParam("password", "123456")
                .log().all()
                .when()
                .post("https://postman-echo.com/post")
                .then()
                .log().all()
                .statusCode(200)
                .body("form.username", equalTo("Pacifique"));
    }
}
