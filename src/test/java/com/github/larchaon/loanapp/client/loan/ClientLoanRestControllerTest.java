package com.github.larchaon.loanapp.client.loan;

import com.github.larchaon.builder4j.TestBuilder;
import com.github.larchaon.loanapp.BaseRestTest;
import com.github.larchaon.loanapp.client.register.RegisterClientModel;
import com.jayway.restassured.response.ExtractableResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.github.larchaon.loanapp.helpers.IssueLoanModelDataSource.simpleLoan;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.*;

public class ClientLoanRestControllerTest extends BaseRestTest {

    String clientLocation;

    @Before
    public void setUp() throws Exception {
        clientLocation = given().
            body(TestBuilder.forBean(RegisterClientModel::new).
                    with(RegisterClientModel::setPersonalCode, 111111111L).
                    with(RegisterClientModel::setEmail, "my@email.com").
                    with(RegisterClientModel::setPassword, "pass").
                    with(RegisterClientModel::setPhoneNumber, "+371 232323").
                    build()).
        when().
            post("/clients").
        then().extract().header(LOCATION);
    }

    @Test
    public void takeALoan_should_return_NotFound_for_non_existing_user() throws Exception {
        takeALoan(10, 10L, "/clients/999", NOT_FOUND);
    }

    @Test
    public void takeALoan_should_return_Created_for_valid_Loan_and_Client() throws Exception {
        takeALoan(10, 10L, clientLocation, CREATED);
    }

    @Test
    public void takeALoan_should_return_BadRequest_for_less_than_min_amount() throws Exception {
        takeALoan(10, 4L, clientLocation, BAD_REQUEST);
    }

    @Test
    public void takeALoan_should_return_BadRequest_for_more_than_max_amount() throws Exception {
        takeALoan(10, 301L, clientLocation, BAD_REQUEST);
    }

    @Test
    public void takeALoan_should_return_BadRequest_for_less_than_min_term() throws Exception {
        takeALoan(0, 10L, clientLocation, BAD_REQUEST);
    }

    @Test
    public void takeALoan_should_return_BadRequest_for_more_than_max_term() throws Exception {
        takeALoan(31, 10L, clientLocation, BAD_REQUEST);
    }

    @Test
    public void takeALoan_should_return_Location_of_created_Loan() throws Exception {
        ExtractableResponse response = takeALoan(10, 10L, clientLocation, CREATED);
        String loanLocation = response.header(LOCATION);

        when().
          get(loanLocation).
        then().
          statusCode(OK.value());
    }

    private ExtractableResponse takeALoan(int term, long amount, String location, HttpStatus status) {
        return given().
          body(simpleLoan(term, amount)).
        when().
          post(location + "/loans").
        then().
          statusCode(status.value()).extract();
    }
}