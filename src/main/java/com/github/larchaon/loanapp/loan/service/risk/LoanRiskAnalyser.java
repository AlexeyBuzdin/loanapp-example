package com.github.larchaon.loanapp.loan.service.risk;

import com.github.larchaon.loanapp.loan.Loan;
import com.github.larchaon.loanapp.loan.service.risk.rules.LoanRiskRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoanRiskAnalyser {

    @Autowired
    LoanRiskRule[] risks;

    public List<Risk> validatePotentialRisks(Loan loan) {
        List<Risk> validationMessages = new ArrayList<>();
        for (LoanRiskRule risk : risks) {
            if (risk.violates(loan)) {
                validationMessages.add(risk.violation());
            }
        }
        return validationMessages;
    }
}
