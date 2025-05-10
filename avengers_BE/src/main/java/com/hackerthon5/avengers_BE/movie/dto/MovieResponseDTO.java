package com.hackerthon5.avengers_BE.movie.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieResponseDTO(
        List<MovieDTO> results,
        int currentPage,
        int totalPages,
        long totalElements
) { }
