package com.github.larchaon.builder4j;

@FunctionalInterface
public interface SetProperty<Instance, ParamType> {

    void accept(Instance i, ParamType p);
}
