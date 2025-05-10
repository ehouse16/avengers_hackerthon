package com.hackerthon5.avengers_BE.review.repository;

import com.hackerthon5.avengers_BE.movie.domain.Movie;
import com.hackerthon5.avengers_BE.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 기존의 findByMovieId 메서드를 JPQL 쿼리로 변경
    @Query("SELECT r FROM Review r WHERE r.movie.id = :movieId")
    List<Review> findByMovieId(@Param("movieId") long movieId);

    // 기존 메서드 유지
    List<Review> findByMemberId(long memberId);

    // 기존 메서드 유지
    @Query("SELECT r FROM Review r WHERE r.movie.id IN :movieIds")
    List<Review> findAllByMovieIds(@Param("movieIds") List<Long> movieIds);

    Review findByReviewId(long reviewId);
}
