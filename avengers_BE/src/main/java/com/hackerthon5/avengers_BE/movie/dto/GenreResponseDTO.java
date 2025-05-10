package com.hackerthon5.avengers_BE.movie.dto;

import java.util.List;

public record GenreResponseDTO(
        List<GenreDTO> genres
) {
}
