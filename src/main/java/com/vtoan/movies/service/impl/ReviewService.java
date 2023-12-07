package com.vtoan.movies.service.impl;

import com.vtoan.movies.document.Movie;
import com.vtoan.movies.document.Review;
import com.vtoan.movies.repository.ReviewRepository;
import com.vtoan.movies.service.IReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Review createReview(String reviewBody, String imdbId) {
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewsIds").value(review))
                .first();
        return review;
    }

    @Override
    public void deleteReview(ObjectId id) {
        reviewRepository.deleteById(id);
        mongoTemplate.updateMulti(
                new Query().addCriteria(Criteria.where("id").in(id)),
                new Update().pull("id", id),
                Movie.class
        );
    }
}
