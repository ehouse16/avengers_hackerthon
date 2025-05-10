package com.hackerthon5.avengers_BE.review.DTO;

import com.hackerthon5.avengers_BE.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MyReviewDto {

        private Long reviewId;
        private String movieTitle;
        private String content;
        private Long memberRate;
        private Date postDate;

        public MyReviewDto(Review review) {
                this.movieTitle = review.getMovie() != null ? review.getMovie().getTitle() : null;
                this.content = review.getContent();
                this.memberRate = review.getMemberRate();
                this.postDate = review.getPostDate();
        }

}
