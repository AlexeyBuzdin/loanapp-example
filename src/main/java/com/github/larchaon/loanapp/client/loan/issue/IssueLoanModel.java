package com.github.larchaon.loanapp.client.loan.issue;

public class IssueLoanModel {
    private long amount;
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
