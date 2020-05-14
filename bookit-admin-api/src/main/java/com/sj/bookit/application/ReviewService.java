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

    public Review addReview(Long gymId, Review review) {
        review.setGymId(gymId);
        return reviewRepository.save(review);
    }
}
