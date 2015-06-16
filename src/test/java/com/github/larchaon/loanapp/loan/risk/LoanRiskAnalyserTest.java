package com.github.larchaon.loanapp.loan.risk;

import com.github.larchaon.loanapp.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

public class LoanRiskAnalyserTest extends BaseIntegrationTest{

    @Autowired
    LoanRiskAnalyser analyser;

    @Test
    public void should_be_atleast_one_risk_rule() throws Exception {
        assertThat(analyser.risks, arrayWithSize(greaterThanOrEqualTo(1)));
    }
}