package com.vtoan.movies.service;

import com.vtoan.movies.document.Review;
import org.bson.types.ObjectId;

public interface IReviewService {

    public Review createReview(String reviewBody, String imdbId);
    public void deleteReview(ObjectId id);
}
