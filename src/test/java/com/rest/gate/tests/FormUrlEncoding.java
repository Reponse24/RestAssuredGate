package com.rest.gate.tests;
import com.rest.gate.base.Api;
import com.rest.gate.base.BaseTest;
import com.rest.gate.statuscodes.StatusCodes;
import com.rest.gate.utils.PayloadFaker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class FormUrlEncoding extends BaseTest {

    @Test
    public void testFormUrlEncoded() throws IOException {
        Map<String, String> formData = PayloadFaker.createFormData();

        Response response = Api.sendFormData(formData);
        Assert.assertEquals(response.statusCode(), StatusCodes.CODE_200.code);
        Assert.assertEquals(response.jsonPath().getString("form.userName")!=null, true);
        System.out.println(response.prettyPrint());
    }
}
