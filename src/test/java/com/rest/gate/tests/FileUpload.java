package com.rest.gate.tests;

import com.rest.gate.base.BaseTest;
import com.rest.gate.base.SpecBuilder;
import com.rest.gate.routes.UserRoutes;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class FileUpload extends BaseTest {
   @Test
    public void testFileUpdload() throws IOException {
        given(SpecBuilder.getEchoRequestSpec())
                .contentType("multipart/form-data")
                .multiPart("file", new File("src/test/java/com/rest/gate/schemas/userSchema.json"))
                .multiPart("description", "Test file upload")

                .when().post(UserRoutes.UPLOAD_FILE)

                .then().spec(SpecBuilder.getResponseSpec())
                    .statusCode(200)
                    .body("files", notNullValue());
    }
}
