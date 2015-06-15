package com.github.larchaon.loanapp.loan.service;

import com.github.larchaon.loanapp.client.Client;
import com.github.larchaon.loanapp.loan.Loan;
import com.github.larchaon.loanapp.loan.service.risk.LoanRiskAnalyser;
import com.github.larchaon.loanapp.loan.service.risk.Risk;
import com.github.larchaon.loanapp.util.exceptions.LoanappValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LoanService {

    @Autowired
    LoanRepository repository;

    @Autowired
    LoanRiskAnalyser riskAnalyser;

    public long requestALoan(Loan loan, Client client) {
        List<Risk> risks = riskAnalyser.validatePotentialRisks(loan);
        if(!risks.isEmpty()) throw new LoanappValidationException(risks);

        loan.setClientId(client.getPk());
        Loan savedLoan = repository.save(loan);
        return savedLoan.getPk();
    }

    public Optional<Loan> findLoanById(long loanId) {
        return repository.findByPk(loanId);
    }
}
