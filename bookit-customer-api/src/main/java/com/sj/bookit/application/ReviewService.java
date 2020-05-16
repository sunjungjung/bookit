package com.sj.bookit.application;

import com.sj.bookit.domain.Review;
import com.sj.bookit.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Long gymId, String name, Integer score,
                            String description) {
        Review review = Review.builder()
                .gymId(gymId)
                .name(name)
                .score(score)
                .description(description)
                .build();
        return reviewRepository.save(review);
    }
}
