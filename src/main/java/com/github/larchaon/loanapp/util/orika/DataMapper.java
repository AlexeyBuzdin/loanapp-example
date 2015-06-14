package com.github.larchaon.loanapp.util.orika;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class DataMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        // TODO: Add Orika configuration for Loan
    }
}
