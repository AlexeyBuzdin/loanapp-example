package com.github.larchaon.loanapp.client.loan.issue;

import com.github.larchaon.loanapp.loan.Loan;
import com.github.larchaon.loanapp.util.DateService;
import com.github.larchaon.loanapp.util.orika.ApplicationMapper;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class IssueLoanModelMapper extends ApplicationMapper<IssueLoanModel, Loan> {

    @Autowired
    DateService dateService;

    public IssueLoanModelMapper() {
        super(IssueLoanModel.class, Loan.class);
    }

    @Override
    protected void init(ClassMapBuilder<IssueLoanModel, Loan> classMapBuilder) {
        classMapBuilder.customize(new CustomMapper<IssueLoanModel, Loan>() {
            @Override
            public void mapAtoB(IssueLoanModel issueLoanModel, Loan loan, MappingContext context) {
                Calendar now = dateService.nowCalendar();
                loan.setCreatedOn(now.getTime());
                now.add(Calendar.DATE, issueLoanModel.getTerm());
                loan.setExpiresOn(now.getTime());
            }
        });
    }
}
