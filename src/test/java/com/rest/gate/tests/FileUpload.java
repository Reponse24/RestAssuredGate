package com.rest.gate.tests;

import com.rest.gate.base.Api;
import com.rest.gate.base.BaseTest;
import com.rest.gate.statuscodes.StatusCodes;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class FileUpload extends BaseTest {
    @Test
    public void testFileUpload() throws IOException {

        File file = new File("src/test/java/com/rest/gate/schemas/userSchema.json");

        Response response = Api.uploadFile(file);

        Assert.assertEquals(response.statusCode(), StatusCodes.CODE_200.code);

        String data = response.jsonPath().getString("data");
        Assert.assertNotNull(data);

        System.out.println(response.prettyPrint());
    }
}

