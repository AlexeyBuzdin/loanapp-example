package com.github.larchaon.loanapp.client.loan.history;

import java.util.List;

public class LoanHistoryModel {
    private List<LoanHistory> loans;

    public List<LoanHistory> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanHistory> loans) {
        this.loans = loans;
    }
}
