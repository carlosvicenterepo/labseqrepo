package com.cv.service;

import java.math.BigInteger;

/**
 * The LabseqMap.
 *
 * @author carlosvicente
 * @version 1.0.0
 * @since 02/10/2023
 */
public interface LabseqMap {
    BigInteger getOrPut(Long n);
}
