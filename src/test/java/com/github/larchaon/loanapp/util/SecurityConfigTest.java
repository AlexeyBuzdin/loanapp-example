package com.github.larchaon.loanapp.util;

import com.github.larchaon.loanapp.BaseRestTest;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class SecurityConfigTest extends BaseRestTest {

    @Test
    public void should_return_Unauthorized_status_code_if_user_is_not_logged_in() {
        given().auth().none().
        when().get("/").
                then().statusCode(UNAUTHORIZED.value());
    }

    @Test
    public void should_return_OK_status_code_if_user_is_logged_in() {
        given().auth().basic("user", "password").
        when().get("/").
                then().statusCode(OK.value());
    }
}