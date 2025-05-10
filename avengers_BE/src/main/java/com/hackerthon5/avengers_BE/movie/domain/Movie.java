package com.hackerthon5.avengers_BE.movie.domain;

import com.hackerthon5.avengers_BE.movie.dto.MovieDTO;
import com.hackerthon5.avengers_BE.review.domain.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private double vote_average;

    @Column(nullable = false)
    private String poster_path;

    @Column(nullable = false)
    private LocalDate release_date;

    @Column(unique = true, nullable = false)
    private int tmdbId;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @Builder
    public Movie(int tmdbId, String title, String description, String genre, double vote_average, String poster_path, LocalDate release_date) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.release_date = release_date;
    }

    public static Movie toEntity(MovieDTO dto, Map<Integer, String> genreMap){
        String genreName = dto.genre();

        return Movie.builder()
                .tmdbId(dto.id())
                .title(dto.title())
                .description(dto.overview())
                .genre(genreName)
                .vote_average(dto.vote_average())
                .poster_path(dto.poster_path())
                .release_date(dto.release_date())
                .build();
    }
}
