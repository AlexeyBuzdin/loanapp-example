package com.github.larchaon.loanapp.loan.risk.rules;

import com.github.larchaon.loanapp.loan.Loan;
import com.github.larchaon.loanapp.loan.risk.Risk;
import com.github.larchaon.loanapp.transaction.Transaction;
import com.github.larchaon.loanapp.transaction.TransactionService;
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
    TransactionService transactionService;

    @Override
    public boolean violates(Loan loan) {
        String remoteAddress = requestHelper.getRequestRemoteAddress();
        Date createdOn = loan.getCreatedOn();
        Transaction trx = transactionService.loadTransactionForDate(createdOn);
        return moreThanMaxApplicationsPerDay(trx.getTransactionCount());
    }

    private boolean moreThanMaxApplicationsPerDay(int transactionCount) {
        return transactionCount > MAX_TRANSACTION_COUNT;
    }

    @Override
    public Risk violation() {
        return new Risk("Reached max applications per day from a single IP.");
    }
}
