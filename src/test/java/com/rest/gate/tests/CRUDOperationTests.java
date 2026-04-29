package com.rest.gate.tests;

import com.rest.gate.base.SpecBuilder;
import com.rest.gate.routes.UserRoutes;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.*;

public class CRUDOperationTests {
    @Test
    public void createUserTest(){}
    @Test
    public void testGetAllUsers() throws IOException {
        given(SpecBuilder.getRequestSpec())
                .when()
                .get(UserRoutes.GET_ALL_USERS)
                .then()
                .statusCode(200)
                .body("users", not(empty()))
                .body("users[0].id", notNullValue());
    }
    @Test
    public void testGetUserById() throws IOException {
        given(SpecBuilder.getRequestSpec())
                .pathParam("id", 1)
                .when()
                .get(UserRoutes.GET_USER_BY_ID)
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("firstName", notNullValue());
    }
    @Test
    public void testCreateUser_HashMap() throws IOException {
        Map<String, Object> user = new HashMap<>();
        user.put("firstName", "Alice");
        user.put("lastName", "Smith");
        user.put("age", 25);

        given(SpecBuilder.getRequestSpec())
                .body(user)
                .when()
                .post(UserRoutes.CREATE_USER)
                .then()
                .statusCode(201)
                .body("firstName", equalTo("Alice"));
    }

    @Test
    public void testUpdateUser() throws IOException {
        String body = "{ \n" +
                "\"firstName\": \"John\", \n" +
                "\"lastName\": \"Doe\",\n" +
                " \"age\": 30 \n" +
                "}";

        given(SpecBuilder.getRequestSpec())
                .pathParam("id", 1)
                .body(body)
                .when()
                .put(UserRoutes.UPDATE_USER)
                .then()
                .statusCode(200)
                .body("firstName", equalTo("UpdatedName"));
    }
    @Test
    public void testDeleteUser() throws IOException {
        given(SpecBuilder.getRequestSpec())
                .pathParam("id", 1)
                .when()
                .delete(UserRoutes.DELETE_USER)
                .then()
                .statusCode(200)
                .body("isDeleted", equalTo(true));
    }
}
