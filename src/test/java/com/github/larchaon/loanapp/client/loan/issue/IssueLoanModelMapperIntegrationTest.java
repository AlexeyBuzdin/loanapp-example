package com.github.larchaon.loanapp.client.loan.issue;

import com.github.larchaon.loanapp.BaseIntegrationTest;
import com.github.larchaon.loanapp.loan.Loan;
import com.github.larchaon.loanapp.util.DateService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

import static com.github.larchaon.loanapp.helpers.IssueLoanModelDataSource.simpleLoan;
import static com.github.larchaon.loanapp.matchers.BeanMatchers.hasProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IssueLoanModelMapperIntegrationTest extends BaseIntegrationTest {

    @Autowired
    MapperFactory factory;

    @Autowired
    IssueLoanModelMapper mapper;

    private MapperFacade facade;
    private Calendar calendar;

    @Before
    public void setUp() throws Exception {
        calendar = Calendar.getInstance();
        mapper.dateService = mock(DateService.class);
        when(mapper.dateService.nowCalendar()).thenReturn(calendar);

        factory.registerClassMap(mapper.constructClassMap());
        facade = factory.getMapperFacade();
    }

    @Test
    public void should_set_current_date_as_createdOn() throws Exception {
        Calendar calendar = (Calendar) this.calendar.clone();

        Loan loan = facade.map(simpleLoan(10, 100), Loan.class);
        assertThat(loan, hasProperty(Loan::getCreatedOn, equalTo(calendar.getTime())));
    }

    @Test
    public void should_set_current_date_plus_term_as_expiresOn() throws Exception {
        Calendar calendar = (Calendar) this.calendar.clone();
        int term = 10;
        calendar.add(Calendar.DATE, term);
        Loan loan = facade.map(simpleLoan(term, 100), Loan.class);
        assertThat(loan, hasProperty(Loan::getExpiresOn, equalTo(calendar.getTime())));
    }
}