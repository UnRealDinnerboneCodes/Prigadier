package com.unrealdinnerbone.prigadier.api.util;

public interface ExceptionFunction<E extends Exception, T, A> {
    T get(A a) throws E;
}
