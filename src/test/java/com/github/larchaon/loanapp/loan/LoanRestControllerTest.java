package com.github.larchaon.loanapp.loan;

import com.github.larchaon.loanapp.BaseRestTest;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class LoanRestControllerTest extends BaseRestTest {
    @Test
    public void getById_should_return_NotFound_status_code_for_non_existing_Loan() throws Exception {
        when().
          get("/loans/999").
        then().
          statusCode(NOT_FOUND.value());
    }

    @Test
    public void getById_should_return_BadRequest_status_code_for_invalid_loanId() throws Exception {
        when().
           get("/loans/non_valid").
        then().
           statusCode(BAD_REQUEST.value());
    }
}