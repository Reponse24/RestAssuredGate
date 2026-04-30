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

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetUser extends BaseTest {
    @Test
    public void testGetUserById() throws IOException {

        int userId = 1;

        Response getResponse = Api.getUser(userId);
        UserPayload responseUser = getResponse.as(UserPayload.class);

        Assert.assertEquals(getResponse.statusCode(), StatusCodes.CODE_200.code);
        Assert.assertNotNull(responseUser.getFirstName());
    }
}
