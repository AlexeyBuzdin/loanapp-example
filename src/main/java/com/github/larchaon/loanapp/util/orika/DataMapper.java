package com.github.larchaon.loanapp.util.orika;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataMapper {

    @Autowired(required = false)
    ApplicationMapper[] customMappers;

    @Autowired(required = false)
    CustomConverter[] customConverters;

    @Autowired
    MapperFactory factory;

    private MapperFacade facade;

    @PostConstruct
    public void init() {
        if (customMappers != null) {
            for (ApplicationMapper customMapper : customMappers) {
                factory.registerClassMap(customMapper.constructClassMap());
            }
        }

        ConverterFactory converterFactory = factory.getConverterFactory();
        if (customConverters != null) {
            for (CustomConverter customConverter : customConverters) {
                converterFactory.registerConverter(customConverter);
            }
        }
        facade = factory.getMapperFacade();
    }

    public <T> T map(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        return facade.map(source, targetClass);
    }
}
