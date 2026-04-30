package com.rest.gate.tests;

import com.rest.gate.base.Api;
import com.rest.gate.base.BaseTest;
import com.rest.gate.payloads.UserPayload;
import com.rest.gate.statuscodes.StatusCodes;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUsers extends BaseTest {
    @Test
    public void testGetAllUsers() throws IOException {

        Response response = Api.getAllUsers();

        Assert.assertEquals(response.statusCode(), StatusCodes.CODE_200.code);

        List<UserPayload> users = response.jsonPath().getList("users", UserPayload.class);

        Assert.assertFalse(users.isEmpty());
    }
}
