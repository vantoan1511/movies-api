package com.vtoan.movies.api;

import com.vtoan.movies.document.Review;
import com.vtoan.movies.service.IReviewService;
import com.vtoan.movies.service.impl.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private IReviewService reviewService;

    @Autowired
    public ReviewController(IReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<>(reviewService.createReview(
                payload.get("reviewBody"),
                payload.get("imdbId")), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable ObjectId id) {
        reviewService.deleteReview(id);
    }
}
