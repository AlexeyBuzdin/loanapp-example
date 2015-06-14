package com.github.larchaon.loanapp.util

import com.github.larchaon.loanapp.BaseRestTest
import org.junit.Test

import static com.jayway.restassured.RestAssured.given
import static com.jayway.restassured.RestAssured.when

public class SecurityConfigRestTest extends BaseRestTest {

    @Test
    public void "should return unauthorized status code if user is not logged in"() {
        given().auth().none().
        when().get("/").
                then().statusCode(401);
    }

    @Test
    public void "should return OK status code if user is logged in"() {
        given().auth().basic("user", "password")
        when().get("/").
                then().statusCode(200);
    }
}