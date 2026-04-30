package com.rest.gate.base;

import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.util.Map;


import static com.rest.gate.base.SpecBuilder.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RestResource {

    public static Response post(String path, Object payload) throws IOException {
        return given(getRequestSpec())
                .body(payload)
                .when()
                .post(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String path) throws IOException {
        return given(getRequestSpec())
                .when()
                .get(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String path, int id) throws IOException {
        return given(getRequestSpec())
                .pathParam("id", id)
                .when()
                .get(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response put(String path, int id, Object payload) throws IOException {
        return given(getRequestSpec())
                .pathParam("id", id)
                .body(payload)
                .when()
                .put(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response delete(String path, int id) throws IOException {
        return given(getRequestSpec())
                .pathParam("id", id)
                .when()
                .delete(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response upload(String path, File file) throws IOException {
        return given(SpecBuilder.getEchoRequestSpec())
                .contentType("multipart/form-data")
                .multiPart("file", file)
                .multiPart("description", "Test file upload")
                .when()
                .post(path)
                .then().spec(getResponseSpec())
                .extract().response();
    }

    public static Response formUrlEncoded(String path, Map<String, String> formData) throws IOException {

        return given(SpecBuilder.getRequestSpec())
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParams(formData)
                .when()
                .post(path)
                .then()
                .extract().response();
    }
}
