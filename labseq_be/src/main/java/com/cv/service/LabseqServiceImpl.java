package com.cv.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.Getter;

import java.math.BigInteger;

/**
 * The LabseqServiceImpl.
 *
 * @author carlosvicente
 * @version 1.0.0
 * @since 01/10/2023
 */
@Getter
@RequestScoped
public class LabseqServiceImpl implements LabseqService{

    @Inject
    LabseqMap map;

    @Override
    public BigInteger labseq(final Long n) {
        return getMap().getOrPut(n);
    }
}
