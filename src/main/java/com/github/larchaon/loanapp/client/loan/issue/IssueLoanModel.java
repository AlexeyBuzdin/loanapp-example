package com.github.larchaon.loanapp.client.loan.issue;

import com.github.larchaon.loanapp.loan.service.LoanConstants;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class IssueLoanModel implements LoanConstants {
    @Min(MIN_LOAN_AMOUNT)
    @Max(MAX_LOAN_AMOUNT)
    private long amount;
    @Min(MIN_LOAN_TERM)
    @Max(MAX_LOAN_TERM)
    private int term;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
