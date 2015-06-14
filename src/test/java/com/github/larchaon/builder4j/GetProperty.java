package com.github.larchaon.builder4j;

@FunctionalInterface
public interface GetProperty<Instance, ParamType> {
    ParamType accept(Instance i);
}
