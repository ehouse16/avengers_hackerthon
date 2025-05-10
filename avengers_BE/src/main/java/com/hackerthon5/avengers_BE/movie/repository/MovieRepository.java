package com.hackerthon5.avengers_BE.movie.repository;

import com.hackerthon5.avengers_BE.movie.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTmdbId(int tmdbId);
    Page<Movie> findByTitleContaining(String keyword, Pageable pageable);
    Page<Movie> findByDescriptionContaining(String keyword, Pageable pageable);
    Page<Movie> findByTitleContainingOrDescriptionContaining(String keyword, String keyword1, Pageable pageable);
}
