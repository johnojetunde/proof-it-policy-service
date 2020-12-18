package com.proofit.policy.domain.util;

import java.util.Collection;
import java.util.stream.Stream;

public class FunctionUtil {

    private FunctionUtil() {
    }

    public static <T> Stream<T> emptyIfNullStream(Collection<T> list) {
        return (list == null) ? Stream.empty() : list.stream();
    }
}
