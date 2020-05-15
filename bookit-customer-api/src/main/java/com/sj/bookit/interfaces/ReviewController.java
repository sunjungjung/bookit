package com.sj.bookit.interfaces;


import com.sj.bookit.application.ReviewService;
import com.sj.bookit.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/gyms/{gymId}/reviews")
    public ResponseEntity<?> create(
            @PathVariable("gymId") Long gymId,
            @Valid @RequestBody Review resource
    ) throws URISyntaxException {
        Review review = reviewService.addReview(gymId, resource);

        String url = "/gyms/"+ gymId + "/reviews/" + review.getId();
        return ResponseEntity.created(new URI(url))
                .body("{}");
    }
}
