package com.cv.api;

import com.cv.dto.LabseqRecord;
import com.cv.service.LabseqService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import lombok.Getter;

/**
 * The Labseq.
 *
 * @author carlosvicente
 * @version 1.0.0
 * @since 01/10/2023
 */
@Getter
@RequestScoped
public class Labseq implements LabseqApi{

    @Inject
    LabseqService service;

    @Override
    public LabseqRecord labseq(final Long n)  {
        if (n < 0) {
            throw new BadRequestException();
        }

        return new LabseqRecord(getService().labseq(n).toString());
    }
}
