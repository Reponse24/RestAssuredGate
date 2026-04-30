package com.rest.gate.tests;

import com.rest.gate.base.Api;
import com.rest.gate.base.BaseTest;
import com.rest.gate.payloads.UserPayload;
import com.rest.gate.statuscodes.StatusCodes;
import com.rest.gate.utils.PayloadFaker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteUser extends BaseTest {
    @Test
    public void testDeleteUser() throws IOException {
        int userId = 1;

        Response deleteResponse = Api.deleteUser(userId);

        Assert.assertEquals(deleteResponse.statusCode(), StatusCodes.CODE_200.code);
    }
}
