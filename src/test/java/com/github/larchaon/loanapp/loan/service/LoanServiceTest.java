package com.github.larchaon.loanapp.loan.service;

import com.github.larchaon.loanapp.client.Client;
import com.github.larchaon.loanapp.loan.Loan;
import com.github.larchaon.loanapp.loan.service.risk.LoanRiskAnalyser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {
    @InjectMocks
    LoanService service;

    @Mock
    LoanRiskAnalyser riskAnalyser;

    @Mock
    LoanRepository repository;

    @Test
    public void requestALoan_should_save_a_Loan_if_no_risks() throws Exception {
        when(riskAnalyser.validatePotentialRisks(any(Loan.class))).thenReturn(Collections.emptyList());
        when(repository.save(any(Loan.class))).thenReturn(new Loan());
        service.requestALoan(new Loan(), new Client());
        verify(repository).save(any(Loan.class));
    }

    @Test
    public void requestALoan_should_not_save_a_Loan_if_risks_present() throws Exception {
        when(riskAnalyser.validatePotentialRisks(any(Loan.class))).thenReturn(Collections.emptyList());
        when(repository.save(any(Loan.class))).thenReturn(new Loan());
        service.requestALoan(new Loan(), new Client());
        verify(repository).save(any(Loan.class));
    }
}