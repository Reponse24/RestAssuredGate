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

public class UpdateUser extends BaseTest {
    @Test
    public void testUpdateUser() throws IOException {
        int userId= 1;
        UserPayload updatedPayload = PayloadFaker.createUserPayload();
        updatedPayload.setFirstName("UpdatedName");

        Response updateResponse = Api.updateUser(userId, updatedPayload);
        UserPayload responseUser = updateResponse.as(UserPayload.class);

        Assert.assertEquals(updateResponse.statusCode(), StatusCodes.CODE_200.code);
        Assert.assertEquals(responseUser.getFirstName(), "UpdatedName");
    }
}
