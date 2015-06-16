package com.github.larchaon.loanapp.loan.service.risk.rules;

import com.github.larchaon.loanapp.loan.Loan;
import com.github.larchaon.loanapp.loan.application.service.LoanApplicationService;
import com.github.larchaon.loanapp.loan.service.risk.Risk;
import com.github.larchaon.loanapp.util.RequestHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MaxApplicationsFromSingleApi implements LoanRiskRule {

    public static final int MAX_TRANSACTION_COUNT = 3;

    @Autowired
    RequestHelperService requestHelper;

    @Autowired
    LoanApplicationService transactionService;

    @Override
    public boolean violates(Loan loan) {
        String remoteAddress = requestHelper.getRequestRemoteAddress();
        Date createdOn = loan.getCreatedOn();
        int transactionCount = transactionService.countTransactionsForAddress(remoteAddress, createdOn);
        return moreThanMaxApplicationsPerDay(transactionCount);
    }

    private boolean moreThanMaxApplicationsPerDay(int transactionCount) {
        return transactionCount > MAX_TRANSACTION_COUNT;
    }

    @Override
    public Risk violation() {
        return new Risk("Reached max applications per day from a single IP.");
    }
}
