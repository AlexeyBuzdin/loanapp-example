package com.github.larchaon.loanapp.client

import com.github.larchaon.loanapp.BaseRestTest
import com.github.larchaon.loanapp.client.register.RegisterClientModel
import com.jayway.restassured.RestAssured
import org.junit.Test

import static com.jayway.restassured.RestAssured.given

public class ClientRestControllerRestTest extends BaseRestTest {

    @Test
    public void "should return Created status code on registerClient"() {
        RestAssured.when().get("/");

        def model = new RegisterClientModel()
        model.personalCode = 111111111
        model.email = "my@email.com"
        model.password = "pass"
        model.phoneNumber = "+371 232323"

        given().
            body(model).
        when().
            post("/clients").
        then().
            statusCode(201);
    }
}