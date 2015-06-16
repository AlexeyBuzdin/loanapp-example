package com.github.larchaon.loanapp.loan.risk.rules;

import com.github.larchaon.loanapp.loan.Loan;
import com.github.larchaon.loanapp.loan.risk.Risk;

public interface LoanRiskRule {

    boolean violates(Loan loan);

    Risk violation();
}
