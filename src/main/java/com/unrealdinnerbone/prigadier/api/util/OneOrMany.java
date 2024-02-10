package com.unrealdinnerbone.prigadier.api.util;

import java.util.List;

public record OneOrMany<T>(Type<T> single, Type<List<T>> many) { }
