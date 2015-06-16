package com.github.larchaon.loanapp.loan.service.risk.rules;

import com.github.larchaon.builder4j.TestBuilder;
import com.github.larchaon.loanapp.loan.Loan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.HOUR_OF_DAY;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MaxAmountLoanWithSuspiciousTimingTest {

    @InjectMocks
    MaxAmountLoanWithSuspiciousTiming rule;

    @Test
    public void should_recognize_2_PM_as_short_after_midnight() throws Exception {
        Date date = getDate(2);
        Loan loan = TestBuilder.forBean(Loan::new).with(Loan::setCreatedOn, date).build();
        boolean statement = rule.loanTakenShortAfterMidnight(loan);
        assertThat(statement, is(true));
    }

    @Test
    public void should_not_recognize_3_PM_as_short_after_midnight() throws Exception {
        Date date = getDate(2);
        Loan loan = TestBuilder.forBean(Loan::new).with(Loan::setCreatedOn, date).build();
        boolean statement = rule.loanTakenShortAfterMidnight(loan);
        assertThat(statement, is(true));
    }

    private Date getDate(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
}