package com.cv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * The LabseqDto.
 *
 * @author carlosvicente
 * @version 1.0.0
 * @since 01/10/2023
 */
public record LabseqRecord(@JsonProperty String value) implements Serializable {}
