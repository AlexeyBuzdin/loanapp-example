package com.github.larchaon.loanapp.helpers;

import com.github.larchaon.builder4j.TestBuilder;
import com.github.larchaon.loanapp.client.loan.issue.IssueLoanModel;

public class IssueLoanModelDataSource {

    public static IssueLoanModel simpleLoan(int term, long amount) {
        return TestBuilder.forBean(IssueLoanModel::new).
                with(IssueLoanModel::setTerm, term).
                with(IssueLoanModel::setAmount, amount).
                build();
    }
}
