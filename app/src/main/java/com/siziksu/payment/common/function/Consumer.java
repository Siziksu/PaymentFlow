package com.siziksu.payment.common.function;

@FunctionalInterface
public interface Consumer<T> {

    void accept(T t);
}
