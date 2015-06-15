package com.github.larchaon.loanapp.util.orika;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaConfiguration {
    @Bean
    public MapperFactory provideMapperFactory() {
        return new DefaultMapperFactory.Builder().build();
    }
}
