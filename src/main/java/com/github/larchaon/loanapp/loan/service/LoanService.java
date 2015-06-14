package com.github.larchaon.loanapp.loan.service;

import com.github.larchaon.loanapp.client.Client;
import com.github.larchaon.loanapp.loan.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoanService {

    @Autowired
    LoanRepository repository;

    public long requestALoan(Loan loan, Client client) {
        loan.setClientId(client.getPk());
        Loan savedLoan = repository.save(loan);
        return savedLoan.getPk();
    }

    public Optional<Loan> findLoanById(long loanId) {
        return repository.findByPk(loanId);
    }
}
