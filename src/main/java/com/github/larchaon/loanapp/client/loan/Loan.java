package com.github.larchaon.loanapp.client.loan;

import com.github.larchaon.loanapp.client.Client;

import java.util.Date;

public class Loan {
    private Client client;
    private Date term;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }
}
