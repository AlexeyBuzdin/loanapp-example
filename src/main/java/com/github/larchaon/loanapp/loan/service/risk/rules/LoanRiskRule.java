package com.github.larchaon.loanapp.loan.service.risk.rules;

import com.github.larchaon.loanapp.loan.Loan;
import com.github.larchaon.loanapp.loan.service.risk.Risk;

public interface LoanRiskRule {

    boolean violates(Loan loan);

    Risk violation();
}
