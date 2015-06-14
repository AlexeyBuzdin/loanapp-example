package com.github.larchaon.loanapp.client.loan;

import com.github.larchaon.loanapp.client.loan.history.LoanHistoryModel;
import com.github.larchaon.loanapp.client.loan.issue.IssueLoanModel;
import com.github.larchaon.loanapp.util.ToDoException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "clients/{clientId}/loans")
public class ClientLoanRestController {

    @RequestMapping(value = "", method = GET)
    public LoanHistoryModel clientLoans(@PathVariable("clientId") String clientId, HttpServletRequest request) {
        throw new ToDoException("Fetch client loans");
    }

    @RequestMapping(value = "", method = POST)
    public void takeALoan(@PathVariable("clientId") String clientId, @RequestBody IssueLoanModel model) {
        throw new ToDoException("Validate and Save a loan");
    }
}
