package com.hackerthon5.avengers_BE.movie.service;

import com.hackerthon5.avengers_BE.common.ApiExceptionCode;
import com.hackerthon5.avengers_BE.common.CustomException;
import com.hackerthon5.avengers_BE.movie.dto.GenreDTO;
import com.hackerthon5.avengers_BE.movie.dto.GenreResponseDTO;
import com.hackerthon5.avengers_BE.movie.dto.MovieDTO;
import com.hackerthon5.avengers_BE.movie.dto.MovieResponseDTO;
import com.hackerthon5.avengers_BE.movie.domain.Movie;
import com.hackerthon5.avengers_BE.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class MovieSaveScheduler {
    private final MovieRepository movieRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    private final String BASE_URL = "https://api.themoviedb.org/3";

    @Value("${my.api-key}")
    private String apiKey;

    @Scheduled(fixedRate = 3600000)
    public void fetchMoviesandGenresFirst() {
        log.info("영화랑 장르 가져오기 시작");

        Map<Integer, String> genreMap = fetchGenres();
        log.info("장르 먼저 가져오기 성공");

        String movieUrl = BASE_URL + "/movie/popular?language=ko-KR&page=1&api_key=" + apiKey;
        MovieResponseDTO response = restTemplate.getForObject(movieUrl, MovieResponseDTO.class);

        if (response != null && response.results() != null) {
            List<Movie> newMovies = response.results().stream()
                    .map(dto -> {
                        MovieDTO movie = new MovieDTO(
                                dto.id(),
                                dto.movieId(),
                                dto.title(),
                                dto.overview(),
                                dto.vote_average(),
                                dto.rating(),
                                dto.poster_path(),
                                dto.release_date(),
                                dto.genre_ids(),
                                (dto.genre_ids() != null && !dto.genre_ids().isEmpty()) ? genreMap.getOrDefault(dto.genre_ids().get(0), "기타") : "기타"
                        );
                        return Movie.toEntity(movie, genreMap);
                    })
                    .filter(movie -> !movieRepository.existsByTmdbId(movie.getTmdbId()))
                    .toList();


            if (!newMovies.isEmpty()) {
                movieRepository.saveAll(newMovies);
                log.info("{}개의 새 영화 저장 성공", newMovies.size());
            } else {
                log.info("저장할 새 영화 없음");
            }
        }
    }

    @Scheduled(fixedRate = 3600000)
    public void fetchMoviesandGenresSecond() {
        log.info("영화랑 장르 가져오기 시작");

        Map<Integer, String> genreMap = fetchGenres();
        log.info("장르 먼저 가져오기 성공");

        String movieUrl = BASE_URL + "/movie/popular?language=ko-KR&page=2&api_key=" + apiKey;
        MovieResponseDTO response = restTemplate.getForObject(movieUrl, MovieResponseDTO.class);

        if (response != null && response.results() != null) {
            List<Movie> newMovies = response.results().stream()
                    .map(dto -> {
                        MovieDTO movie = new MovieDTO(
                                dto.id(),
                                dto.movieId(),
                                dto.title(),
                                dto.overview(),
                                dto.vote_average(),
                                dto.rating(),
                                dto.poster_path(),
                                dto.release_date(),
                                dto.genre_ids(),
                                (dto.genre_ids() != null && !dto.genre_ids().isEmpty()) ? genreMap.getOrDefault(dto.genre_ids().get(0), "기타") : "기타"
                        );
                        return Movie.toEntity(movie, genreMap);
                    })
                    .filter(movie -> !movieRepository.existsByTmdbId(movie.getTmdbId()))
                    .toList();


            if (!newMovies.isEmpty()) {
                movieRepository.saveAll(newMovies);
                log.info("{}개의 새 영화 저장 성공", newMovies.size());
            } else {
                log.info("저장할 새 영화 없음");
            }
        }
    }

    @Scheduled(fixedRate = 3600000)
    public void fetchMoviesandGenresThird() {
        log.info("영화랑 장르 가져오기 시작");

        Map<Integer, String> genreMap = fetchGenres();
        log.info("장르 먼저 가져오기 성공");

        String movieUrl = BASE_URL + "/movie/popular?language=ko-KR&page=3&api_key=" + apiKey;
        MovieResponseDTO response = restTemplate.getForObject(movieUrl, MovieResponseDTO.class);

        if (response != null && response.results() != null) {
            List<Movie> newMovies = response.results().stream()
                    .map(dto -> {
                        MovieDTO movie = new MovieDTO(
                                dto.id(),
                                dto.movieId(),
                                dto.title(),
                                dto.overview(),
                                dto.vote_average(),
                                dto.rating(),
                                dto.poster_path(),
                                dto.release_date(),
                                dto.genre_ids(),
                                (dto.genre_ids() != null && !dto.genre_ids().isEmpty()) ? genreMap.getOrDefault(dto.genre_ids().get(0), "기타") : "기타"
                        );
                        return Movie.toEntity(movie, genreMap);
                    })
                    .filter(movie -> !movieRepository.existsByTmdbId(movie.getTmdbId()))
                    .toList();


            if (!newMovies.isEmpty()) {
                movieRepository.saveAll(newMovies);
                log.info("{}개의 새 영화 저장 성공", newMovies.size());
            } else {
                log.info("저장할 새 영화 없음");
            }
        }
    }

    public Map<Integer, String> fetchGenres(){
        String genreUrl = BASE_URL + "/genre/movie/list?language=ko&api_key=" + apiKey;

        GenreResponseDTO genreResponse = restTemplate.getForObject(genreUrl, GenreResponseDTO.class);

        if(genreResponse == null || genreResponse.genres() == null)
            throw new CustomException(ApiExceptionCode.GENRE_NOT_FOUND);

        return genreResponse.genres().stream()
                .collect(Collectors.toMap(GenreDTO::id, GenreDTO::name));
    }
}
