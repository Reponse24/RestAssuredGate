package com.rest.gate.tests;

import com.rest.gate.base.BaseTest;
import com.rest.gate.base.SpecBuilder;
import com.rest.gate.payloads.Address;
import com.rest.gate.payloads.Company;
import com.rest.gate.payloads.UserPayload;
import com.rest.gate.routes.UserRoutes;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.*;

public class CRUDOperationTests extends BaseTest {
    @Test
    public void testGetAllUsers() throws IOException {
        given(SpecBuilder.getRequestSpec())
                .when()
                .get(UserRoutes.GET_ALL_USERS)
                .then().spec(SpecBuilder.getResponseSpec())
                .statusCode(200)
                .body("users", not(empty()))
                .body("users[0].id", notNullValue());
    }
//    @Test
//    public void testGetUserById() throws IOException {
//        given(SpecBuilder.getRequestSpec())
//                .pathParam("id", 1)
//                .when()
//                .get(UserRoutes.GET_USER_BY_ID)
//                .then().spec(SpecBuilder.getResponseSpec())
//                .statusCode(200)
//                .body("id", equalTo(1))
//                .body("firstName", notNullValue());
//    }
    @Test
    public void testDeserializeUser() throws IOException {
        UserPayload user = given(SpecBuilder.getRequestSpec())
                .pathParam("id", 1)
                .when()
                .get(UserRoutes.GET_USER_BY_ID)
                .then()
                .statusCode(200)
                .extract().as(UserPayload.class);

        Assert.assertNotNull(user.getFirstName());
    }
    @Test
    public void testCreateUser_HashMap() throws IOException {
        Map<String, Object> address = new HashMap<>();
        address.put("country", "Rwanda");
        address.put("city", "Musanze");
        address.put("street", "NM 123 st");

        Map<String, Object> company = new HashMap<>();
        company.put("name", "MTN");
        company.put("department", "Call Center");


        Map<String, Object> user = new HashMap<>();
        user.put("firstName", "Alice");
        user.put("lastName", "Smith");
        user.put("address", address);
        user.put("company", company);

        given(SpecBuilder.getRequestSpec())
                .body(user)
                .when()
                .post(UserRoutes.CREATE_USER)
                .then().spec(SpecBuilder.getResponseSpec())
                .statusCode(201)
                .body("firstName", equalTo("Alice"));
    }

    @Test
    public void testCreateUser_POJO() throws IOException {
        Address address= new Address();
        address.setCountry("United States");
        address.setCity("New York");
        address.setStreet("NY 123 st");

        Company company = new Company();
        company.setName("Coding Company");
        company.setDepartment("Development");

        UserPayload user = new UserPayload();
        user.setFirstName("Carol");
        user.setLastName("White");
        user.setAddress(address);
        user.setCompany(company);

        given(SpecBuilder.getRequestSpec())
                .body(user)
                .when()
                .post(UserRoutes.CREATE_USER)
                .then().spec(SpecBuilder.getResponseSpec())
                .statusCode(201)
                .body("firstName", equalTo("Carol"));
    }

    @Test
    public void testUpdateUser() throws IOException {
        String body = "{ \"firstName\": \"UpdatedName\" }";

        given(SpecBuilder.getRequestSpec())
                .pathParam("id", 1)
                .body(body)
                .when()
                .put(UserRoutes.UPDATE_USER)
                .then().spec(SpecBuilder.getResponseSpec())
                .statusCode(200)
                .body("firstName", equalTo("UpdatedName"));
    }
    @Test
    public void testDeleteUser() throws IOException {
        given(SpecBuilder.getRequestSpec())
                .pathParam("id", 1)
                .when()
                .delete(UserRoutes.DELETE_USER)
                .then().spec(SpecBuilder.getResponseSpec())
                .statusCode(200)
                .body("isDeleted", equalTo(true));
    }
}
