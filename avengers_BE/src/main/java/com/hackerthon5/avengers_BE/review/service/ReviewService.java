package com.hackerthon5.avengers_BE.review.service;
import com.hackerthon5.avengers_BE.review.DTO.MyReviewDto;
import com.hackerthon5.avengers_BE.review.domain.Review;
import java.util.List;
import org.springframework.security.core.userdetails.User;

public interface ReviewService {
    public Review createReview(Review review);

    Review updateReview(Review review);

    void deleteReview(long reviewId);

    List<Review> getMovieReview(long movieId);


    List<Review> getMemberReview(long memberId);

    Review getOneReview(long reviewId);


    List<MyReviewDto> getMyReview(User user);
}
