package com.rest.gate.base;

import com.rest.gate.utils.ConfigLoader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    @BeforeClass
    public void initConfig() throws Exception {
        ConfigLoader.setup();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
