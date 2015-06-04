package com.github.larchaon.loanapp.client.loan;

import com.github.larchaon.loanapp.client.loan.history.LoanHistoryModel;
import com.github.larchaon.loanapp.client.loan.issue.IssueLoanModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "clients/{clientId}/loans")
public class ClientLoanRestController {

    // TODO: Add authentication
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public LoanHistoryModel clientLoans(@PathVariable("clientId") String clientId) {
        return new LoanHistoryModel();
    }

    // TODO: Add authentication
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void takeALoan(@PathVariable("clientId") String clientId, @RequestParam("model") IssueLoanModel model) {

    }
}
