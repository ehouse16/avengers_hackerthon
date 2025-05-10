package com.hackerthon5.avengers_BE.review.service;

import com.hackerthon5.avengers_BE.member.domain.Member;
import com.hackerthon5.avengers_BE.member.repository.MemberRepository;
import com.hackerthon5.avengers_BE.movie.domain.Movie;
import com.hackerthon5.avengers_BE.movie.repository.MovieRepository;
import com.hackerthon5.avengers_BE.review.DTO.MyReviewDto;
import com.hackerthon5.avengers_BE.review.domain.Review;
import com.hackerthon5.avengers_BE.review.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public Review createReview(Review review) {
        // 리뷰 생성 날짜 설정
        review.setPostDate(new Date());

        // movieId를 기반으로 Movie 객체 찾기
        if (review.getMovieId() > 0) {
            Optional<Movie> movieOpt = movieRepository.findById(review.getMovieId());
            if (movieOpt.isPresent()) {
                review.setMovie(movieOpt.get());
            } else {
                throw new RuntimeException("Movie with ID " + review.getMovieId() + " not found");
            }
        }

        // 저장 및 반환
        return reviewRepository.save(review);
    }

    public Review updateReview(Review reviewDTO){
        long pastReviewId = reviewDTO.getReviewId();

        Review pastReview = reviewRepository.findById(pastReviewId)
                .orElseThrow(() -> new EntityNotFoundException("review not found"));

        pastReview.setTitle(reviewDTO.getTitle());
        pastReview.setContent(reviewDTO.getContent());
        pastReview.setMemberRate(reviewDTO.getMemberRate());
        pastReview.setUpdateDate(new Date());

        return reviewRepository.save(pastReview);
    }

    @Override
    public void deleteReview(long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("review not found"));
        reviewRepository.delete(review);
    }

    @Override
    public List<Review> getMovieReview(long movieId) {
        List<Review> movieRList = reviewRepository.findByMovieId(movieId);
        return movieRList;
    }

    @Override
    public List<Review> getMemberReview(long memberId) {
        List<Review> memberRList = reviewRepository.findByMemberId(memberId);
        return memberRList;
    }

    @Override
    public Review getOneReview(long reviewId) {
        return reviewRepository.findByReviewId(reviewId);
    }

    @Override
    public List<MyReviewDto> getMyReview(User user) {

        Member member = memberRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("유저정보가 없습니다."));

        List<Review> reviews = reviewRepository.findByMemberId(member.getMemberId());

        return reviews.stream()
                .map(MyReviewDto::new)
                .collect(Collectors.toList());

    }


}
