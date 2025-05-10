package com.hackerthon5.avengers_BE.movie.service;

import com.hackerthon5.avengers_BE.movie.dto.MoiveTop10ResponseDTO;
import com.hackerthon5.avengers_BE.movie.dto.MovieDetailResponse;
import com.hackerthon5.avengers_BE.movie.dto.MovieResponseDTO;
import com.hackerthon5.avengers_BE.movie.dto.SearchDTO;
import org.springframework.data.domain.Pageable;

public interface MovieService {
    MovieResponseDTO getMovies(Pageable pageable, SearchDTO searchDTO);
    MovieDetailResponse getMovie(Long movieId);
    MoiveTop10ResponseDTO getPopular();
}
