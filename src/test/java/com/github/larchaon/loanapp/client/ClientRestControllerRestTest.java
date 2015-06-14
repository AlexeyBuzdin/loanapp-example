package com.github.larchaon.loanapp.client;

import com.github.larchaon.builder4j.TestBuilder;
import com.github.larchaon.loanapp.BaseRestTest;
import com.github.larchaon.loanapp.client.register.RegisterClientModel;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

public class ClientRestControllerRestTest extends BaseRestTest {

    @Test
    public void registerClient_should_return_Created_status_code_on_valid_Client() {
        RegisterClientModel model = TestBuilder.forBean(RegisterClientModel::new)
                .with(RegisterClientModel::setPersonalCode, 111111111L)
                .with(RegisterClientModel::setEmail, "my@email.com")
                .with(RegisterClientModel::setPassword, "pass")
                .with(RegisterClientModel::setPhoneNumber, "+371 232323")
                .build();

        given().body(model).
        when().post("/clients").
        then().statusCode(CREATED.value());
    }

    @Test
    public void registerClient_should_return_Bad_Request_status_code_on_invalid_Client() {
        RegisterClientModel model = TestBuilder.forBean(RegisterClientModel::new)
                .with(RegisterClientModel::setPersonalCode, 111111111L)
                .with(RegisterClientModel::setEmail, "my@email.com")
                .build();

        given().body(model).
        when().post("/clients").
        then().statusCode(BAD_REQUEST.value());
    }
}