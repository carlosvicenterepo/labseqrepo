package com.cv.service;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.util.Collections.max;

/**
 * The LabseqMap.
 *
 * @author carlosvicente
 * @version 1.0.0
 * @since 01/10/2023
 */
@Slf4j
@ApplicationScoped
public class LabseqMapImpl extends HashMap<Long, BigInteger> implements LabseqMap {

    private static final long MAX_CACHE_VALUE = 300000L;

    void onStart(final @Observes StartupEvent event) {
        log.info("Begin labseq cache.");

        put(0L, ZERO);
        put(1L, ONE);
        put(2L, ZERO);
        put(3L, ONE);
    }

    @Override
    public BigInteger getOrPut(final Long key) {
        if (key > MAX_CACHE_VALUE) {
            return calculateLabseqWithoutCache(key);
        }

        return Optional.ofNullable(get(key))
                       .orElseGet(getLabseqSupplier(key));
    }

    private BigInteger calculateLabseqWithoutCache(final long value) {
        final BigInteger[] arrayOfInicialData = new BigInteger[] { get(0L), get(1L), get(2L), get(3L) };

        return Stream.iterate(arrayOfInicialData,
                             prevValues -> new BigInteger[] { prevValues[1], prevValues[2], prevValues[3],
                                     prevValues[1].add(prevValues[2]) })
                     .limit(value + 1)
                     .reduce((first, second) -> second)
                     .map(values -> values[3])
                     .orElse(BigInteger.ZERO);
    }

    private Supplier<BigInteger> getLabseqSupplier(final Long value) {
        return () -> {
            IntStream.rangeClosed(getMaxKey(), toIntExact(value))
                     .forEach(n -> put((long) n, get(n - 4L).add(get(n - 3L))));

            return get(value);
        };
    }

    int getMaxKey() {
        return toIntExact(max(keySet())) + 1;
    }
}
