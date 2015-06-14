package com.github.larchaon.loanapp.client;

import com.github.larchaon.builder4j.TestBuilder;
import com.github.larchaon.loanapp.BaseRestTest;
import com.github.larchaon.loanapp.client.register.RegisterClientModel;
import com.jayway.restassured.response.ExtractableResponse;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import static com.github.larchaon.matchers.PatternMatcher.matchesPattern;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.*;

public class ClientRestControllerTest extends BaseRestTest {

    @Test
    public void registerClient_should_return_Created_status_code_on_valid_Client() {
        given().
          body(TestBuilder.forBean(RegisterClientModel::new).
                  with(RegisterClientModel::setPersonalCode, 111111111L).
                  with(RegisterClientModel::setEmail, "my@email.com").
                  with(RegisterClientModel::setPassword, "pass").
                  with(RegisterClientModel::setPhoneNumber, "+371 232323").
                  build()).
        when().
          post("/clients").
        then().
          statusCode(CREATED.value());
    }

    @Test
    public void registerClient_should_return_Bad_Request_status_code_on_invalid_Client() {
        given().
          body(TestBuilder.forBean(RegisterClientModel::new).
            with(RegisterClientModel::setPersonalCode, 111111111L).
            with(RegisterClientModel::setEmail, "my@email.com").
            build()).
        when().
          post("/clients").
        then().
          statusCode(BAD_REQUEST.value());
    }

    @Test
    public void registerClient_should_return_Location_header_for_created_entity() throws Exception {
        given().
          body(TestBuilder.forBean(RegisterClientModel::new).
                  with(RegisterClientModel::setPersonalCode, 111111111L).
                  with(RegisterClientModel::setEmail, "my@email.com").
                  with(RegisterClientModel::setPassword, "pass").
                  with(RegisterClientModel::setPhoneNumber, "+371 232323").
                  build()).
        when().
          post("/clients").
        then().
          header(LOCATION, matchesPattern("/clients/\\d+"));
    }

    @Test
    public void getById_should_return_NotFound_status_code_for_non_existing_Client() throws Exception {
        when().
          get("/clients/999").
        then().
          statusCode(NOT_FOUND.value());
    }

    @Test
    public void getById_should_return_OK_status_code_for_existing_Client() throws Exception {
        ExtractableResponse<Response> resp =
            given().
              body(TestBuilder.forBean(RegisterClientModel::new).
                      with(RegisterClientModel::setPersonalCode, 111111111L).
                      with(RegisterClientModel::setEmail, "my@email.com").
                      with(RegisterClientModel::setPassword, "pass").
                      with(RegisterClientModel::setPhoneNumber, "+371 232323").
                      build()).
            when().
              post("/clients").
            then().extract();

        when().
          get(resp.header(LOCATION)).
        then().
          statusCode(OK.value());
    }
}