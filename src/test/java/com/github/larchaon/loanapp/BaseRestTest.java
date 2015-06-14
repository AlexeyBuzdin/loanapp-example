package com.github.larchaon.loanapp;

import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RunApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080")
public class BaseRestTest {
    @BeforeClass
    public static void setUpClass() {
        RestAssured.authentication = RestAssured.basic("user", "password");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.requestContentType("application/json");
    }
}
