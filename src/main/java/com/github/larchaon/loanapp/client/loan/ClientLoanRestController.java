package com.github.larchaon.loanapp.client.loan;

import com.github.larchaon.loanapp.client.loan.history.LoanHistoryModel;
import com.github.larchaon.loanapp.client.loan.issue.IssueLoanModel;
import com.github.larchaon.loanapp.util.ToDoException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "clients/{clientId}/loans")
public class ClientLoanRestController {

    // TODO: Add authentication
    @RequestMapping(value = "", method = RequestMethod.GET)
    public LoanHistoryModel clientLoans(@PathVariable("clientId") String clientId, HttpServletRequest request) {
        throw new ToDoException("Fetch client loans");
    }

    // TODO: Add authentication
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void takeALoan(@PathVariable("clientId") String clientId, @RequestBody IssueLoanModel model) {
        throw new ToDoException("Validate and Save a loan");
    }
}
