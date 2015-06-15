package com.github.larchaon.loanapp.loan.service.risk;

import com.github.larchaon.loanapp.loan.Loan;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class LoanRiskAnalyser {

    public List<Risk> validatePotentialRisks(Loan loan) {
        return Collections.emptyList();
    }
}
