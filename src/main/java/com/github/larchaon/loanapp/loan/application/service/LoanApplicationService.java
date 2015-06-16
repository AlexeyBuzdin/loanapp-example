package com.github.larchaon.loanapp.loan.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LoanApplicationService {

    @Autowired
    LoanApplicationRepository repository;

    public int countTransactionsForAddress(String remoteAddress, Date createdOn) {

        return 0;
    }
}
