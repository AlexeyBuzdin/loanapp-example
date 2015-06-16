package com.github.larchaon.loanapp.loan.risk.rules;

import com.github.larchaon.loanapp.loan.Loan;
import com.github.larchaon.loanapp.loan.LoanConstants;
import com.github.larchaon.loanapp.loan.risk.Risk;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class MaxAmountLoanWithSuspiciousTiming implements LoanRiskRule, LoanConstants {

    @Override
    public boolean violates(Loan loan) {
        if(loanHasMaxPossibleAmount(loan)) {
            return loanTakenShortAfterMidnight(loan);
        }
        return false;
    }

    boolean loanHasMaxPossibleAmount(Loan loan) {
        return MAX_LOAN_AMOUNT == loan.getAmount();
    }

    boolean loanTakenShortAfterMidnight(Loan loan) {
        Date createdOn = loan.getCreatedOn();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createdOn);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        return hours < 3;
    }

    @Override
    public Risk violation() {
        return new Risk("the attempt to take a loan is made after 00:00 with max possible amount");
    }
}
