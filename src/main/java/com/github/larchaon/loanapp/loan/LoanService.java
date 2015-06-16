package com.github.larchaon.loanapp.loan;

import com.github.larchaon.loanapp.client.Client;
import com.github.larchaon.loanapp.loan.risk.LoanRiskAnalyser;
import com.github.larchaon.loanapp.loan.risk.Risk;
import com.github.larchaon.loanapp.transaction.Transaction;
import com.github.larchaon.loanapp.transaction.TransactionService;
import com.github.larchaon.loanapp.util.exceptions.LoanappValidationException;
import com.github.larchaon.loanapp.util.orika.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LoanService {

    @Autowired
    LoanRepository repository;

    @Autowired
    TransactionService applicationService;

    @Autowired
    LoanRiskAnalyser riskAnalyser;

    @Autowired
    DataMapper mapper;

    public long requestALoan(Loan loan, Client client) {
        registerTransaction(loan);
        validateLoan(loan);

        Loan savedLoan = saveLoan(loan, client);
        return savedLoan.getPk();
    }

    private void registerTransaction(Loan loan) {
        Transaction trx = applicationService.loadTransactionForDate(loan.getCreatedOn());
        trx.setTransactionCount(trx.getTransactionCount()+1);
        applicationService.saveTransaction(trx);
    }

    private void validateLoan(Loan loan) {
        List<Risk> risks = riskAnalyser.validatePotentialRisks(loan);
        if(!risks.isEmpty()) throw new LoanappValidationException(risks);
    }

    private Loan saveLoan(Loan loan, Client client) {
        loan.setClientId(client.getPk());
        return repository.save(loan);
    }

    public Optional<Loan> findLoanById(long loanId) {
        return repository.findByPk(loanId);
    }
}
