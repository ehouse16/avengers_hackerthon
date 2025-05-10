package com.hackerthon5.avengers_BE.movie.controller;

import com.hackerthon5.avengers_BE.movie.dto.MoiveTop10ResponseDTO;
import com.hackerthon5.avengers_BE.movie.dto.MovieDetailResponse;
import com.hackerthon5.avengers_BE.movie.dto.MovieResponseDTO;
import com.hackerthon5.avengers_BE.movie.dto.SearchDTO;
import com.hackerthon5.avengers_BE.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<MovieResponseDTO> getMovies(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size,
            @ModelAttribute SearchDTO searchDTO) {

        Pageable pageable = PageRequest.of(page, size);

        MovieResponseDTO response = movieService.getMovies(pageable, searchDTO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDetailResponse> getMovie(@PathVariable("movieId") Long movieId) {
        MovieDetailResponse response = movieService.getMovie(movieId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getPopular")
    public ResponseEntity<MoiveTop10ResponseDTO> getPopularMovies() {
        MoiveTop10ResponseDTO response = movieService.getPopular();

        return ResponseEntity.ok(response);
    }
}
