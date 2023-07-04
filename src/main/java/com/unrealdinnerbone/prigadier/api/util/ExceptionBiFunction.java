package com.unrealdinnerbone.prigadier.api.util;

public interface ExceptionBiFunction<E extends Exception, T, A, B> {
    T get(A a, B b) throws E;
}
