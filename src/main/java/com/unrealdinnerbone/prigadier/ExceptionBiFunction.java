package com.unrealdinnerbone.prigadier;

public interface ExceptionBiFunction<E extends Exception, T, A, B> {
    T get(A a, B b) throws E;
}
