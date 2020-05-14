package com.sj.bookit.application;

import com.sj.bookit.domain.Review;
import com.sj.bookit.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class ReviewServiceTest {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void addReview() {


        Review review = Review.builder()
                .name("doe")
                .score(3)
                .description("diff")
                .build();
        reviewService.addReview(1001L, review);

        verify(reviewRepository).save(any());
    }

}