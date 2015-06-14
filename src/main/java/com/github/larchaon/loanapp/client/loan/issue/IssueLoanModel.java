package com.github.larchaon.loanapp.client.loan.issue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class IssueLoanModel {
    @Min(5)
    @Max(300)
    private long amount;
    @Min(1)
    @Max(30)
    private long term;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getTerm() {
        return term;
    }

    public void setTerm(long term) {
        this.term = term;
    }
}
