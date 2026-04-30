package com.rest.gate.base;

import com.rest.gate.utils.ConfigLoader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class BaseTest {
    @BeforeClass
    public void initConfig() throws Exception {
        ConfigLoader.setup();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeSuite
    public void setupLogFile() throws FileNotFoundException {
        new PrintStream(new FileOutputStream("restAssured.log", false)).close();
    }

}
