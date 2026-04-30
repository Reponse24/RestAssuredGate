package com.rest.gate.tests;

import com.rest.gate.base.Api;
import com.rest.gate.base.BaseTest;
import com.rest.gate.payloads.UserPayload;
import com.rest.gate.statuscodes.StatusCodes;
import com.rest.gate.utils.PayloadFaker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateUser extends BaseTest {

    @Test
    public void testCreateUser_by_hashmap() throws IOException {

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

        Response response = Api.createUser(user);

        Assert.assertEquals(response.statusCode(), StatusCodes.CODE_201.code);
        Assert.assertEquals(response.jsonPath().getString("firstName"), "Alice");
        Assert.assertEquals(response.jsonPath().getString("lastName"), "Smith");
    }

    @Test
    public void testCreateUser_by_raw_json_string() throws IOException {

        String payload = "{\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Doe\"}";

        Response response = Api.createUser(payload);

        Assert.assertEquals(response.statusCode(), StatusCodes.CODE_201.code);
        Assert.assertEquals(response.jsonPath().getString("firstName"), "John");
    }

    @Test
    public void testCreateUser_by_pojo() throws IOException {

        UserPayload payload = PayloadFaker.createUserPayload();


        Response response = Api.createUser(payload);
        UserPayload responseUser = response.as(UserPayload.class);

        Assert.assertEquals(response.statusCode(), StatusCodes.CODE_201.code);
        Assert.assertEquals(responseUser.getFirstName(), payload.getFirstName());
        Assert.assertEquals(responseUser.getLastName(), payload.getLastName());
        Assert.assertEquals(responseUser.getAddress().getCity(), payload.getAddress().getCity());
    }

    @Test
    public void testCreateUserInvalidJson() throws IOException {
        String invalidJson = "{ firstName: John }";

        Response response = Api.createUser(invalidJson);

        Assert.assertTrue(response.statusCode() >= StatusCodes.CODE_400.code);
    }
}