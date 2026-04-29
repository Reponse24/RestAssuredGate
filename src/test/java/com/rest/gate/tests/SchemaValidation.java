package com.rest.gate.tests;

import com.rest.gate.base.SpecBuilder;
import com.rest.gate.routes.UserRoutes;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidation {
    @Test
    public void testSchemaValidation() throws IOException {
        given(SpecBuilder.getRequestSpec())
                .pathParam("id", 1)
                .when()
                .get(UserRoutes.GET_USER_BY_ID)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/userSchema.json"));
    }
// Import: import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
}
