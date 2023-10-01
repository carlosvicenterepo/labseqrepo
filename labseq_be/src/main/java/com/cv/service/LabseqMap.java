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
public class LabseqMap extends HashMap<Long, BigInteger> {

    void onStart(final @Observes StartupEvent event) {
        log.info("Begin labseq cache.");

        put(0L, ZERO);
        put(1L, ONE);
        put(2L, ZERO);
        put(3L, ONE);
    }

    BigInteger getOrPut(final Long key) {
        return Optional.ofNullable(get(key))
                       .orElseGet(getLabseqSupplier(key));
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
