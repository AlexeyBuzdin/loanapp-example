package com.github.larchaon.loanapp.util.orika;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.metadata.ClassMap;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class CustomMapper<A, B> {

    private final Class<A> classA;
    private final Class<B> classB;
    protected A A;
    protected B B;
    private ClassMapBuilder<A, B> classMapBuilder;

    @Autowired
    MapperFactory mapperFactory;

    public CustomMapper(Class<A> classA, Class<B> classB) {
        this.classA = classA;
        this.classB = classB;
        A = Attributes.rootAttribute(classA);
        B = Attributes.rootAttribute(classB);

        classMapBuilder = mapperFactory.classMap(classA, classB);
    }

    protected abstract void init(ClassMapBuilder<A, B> classMapBuilder);

    public ClassMap<A, B> constructClassMap() {
        return classMapBuilder.toClassMap();
    }
}
