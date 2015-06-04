package com.github.larchaon.loanapp.client.loan;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "clients/{clientId}/loans")
public class LoanRestController {

    @RequestMapping(value = "/")
    public Loan clientLoans(@PathVariable("clientId") String clientId) {
        return new Loan();
    }
}
