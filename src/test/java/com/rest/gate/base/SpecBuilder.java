package com.rest.gate.base;

import com.rest.gate.utils.ConfigLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.IOException;

import static org.hamcrest.Matchers.lessThan;

public class SpecBuilder {
    public static RequestSpecification getRequestSpec() throws IOException {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.setup())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)         // logs every request
                .build();
    }

    // Build reusable Response Specification
    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .expectResponseTime(lessThan(5000L))
                .log(LogDetail.ALL)
                .build();
    }
}