package com.hackerthon5.avengers_BE.review.controller;

import com.hackerthon5.avengers_BE.review.DTO.MyReviewDto;
import com.hackerthon5.avengers_BE.review.domain.Review;
import com.hackerthon5.avengers_BE.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/test")
    public String testReview(){ return "Test SUCCESS"; }

    @PostMapping("/createReview")
    public ResponseEntity<Review> createReview(@RequestBody Review review){
        Review newReview = reviewService.createReview(review);
        return ResponseEntity.ok(newReview);
    }

    @GetMapping("/getMovieReview")
    public List<Review> getAllReview(@RequestParam("movieId") long movieId){
        return reviewService.getMovieReview(movieId);
    }

    @GetMapping("/getMyReview")
    public ResponseEntity<List<MyReviewDto>> getMyReview(@AuthenticationPrincipal User user){
        List<MyReviewDto> myReviews = reviewService.getMyReview(user);
        return ResponseEntity.ok(myReviews);
    }

    @GetMapping("/getMemberReview")
    public List<Review> getMemberReview(@RequestParam("memberId") long memberId){
        return reviewService.getMemberReview(memberId);

    }
    @GetMapping("/getOneReview/{reviewId}")
    public Review getOneReview(@PathVariable("reviewId") long reviewId){
        return reviewService.getOneReview(reviewId);
    }

    @PostMapping("/updateReview")
    public ResponseEntity<Review> updateReview(@RequestBody Review reviewDTO){
        //제목, 내용, 별점, 수정일자 수정하기
        Review reReview = reviewService.updateReview(reviewDTO);
        return ResponseEntity.ok(reReview);
    }

    @PostMapping("/deleteReview")
    public String deleteReview(@RequestParam("reviewId") long reviewId){
        System.out.println("지우려고 하는 리뷰 아이디 : " + reviewId);
        reviewService.deleteReview(reviewId);

        return "지움!";
    }

}
