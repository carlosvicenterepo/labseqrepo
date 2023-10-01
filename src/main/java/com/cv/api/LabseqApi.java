package com.cv.api;

import com.cv.dto.LabseqRecord;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.Cache;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * The LabseqApi.
 *
 * @author carlosvicente
 * @version 1.0.0
 * @since 01/10/2023
 */

@Path("/labseq")
@Tag(name = "LABSEQ RESOURCE")
@Produces(APPLICATION_JSON)
public interface LabseqApi {

    @GET
    @Cache
    @Path("/{n}")
    @Operation(summary = "Returning a value from the labseq sequence.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success."),
            @APIResponse(responseCode = "400", description = "Bad request.")
    })
    LabseqRecord labseq(final @PathParam("n") Long n);
}
