package com.rest.gate.base;

import com.rest.gate.filters.CustomLoggingFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;

import static com.rest.gate.utils.ConfigLoader.properties;
import static org.hamcrest.Matchers.lessThan;

public class SpecBuilder{
    private static PrintStream logStream;

    static {
        try {
            logStream = new PrintStream(new FileOutputStream("restAssured.log", false));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static RequestSpecification getRequestSpec() throws IOException {
        return new RequestSpecBuilder()
                .setBaseUri(properties.getProperty("base_url"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new CustomLoggingFilter(logStream))        // logs every request
                .build();
    }

    public static RequestSpecification getEchoRequestSpec() throws IOException {
        return new RequestSpecBuilder()
                .setBaseUri(properties.getProperty("echo-url"))
                .addFilter(new CustomLoggingFilter(logStream))        // logs every request
                .build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .expectResponseTime(lessThan(50000L))
                .log(LogDetail.ALL)
                .build();
    }
}