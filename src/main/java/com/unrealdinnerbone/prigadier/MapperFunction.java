package com.unrealdinnerbone.prigadier;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface MapperFunction<E extends Exception, T, A, B> {
    T get(A a, B b) throws E;
}
