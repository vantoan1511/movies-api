package com.vtoan.movies.service;

import com.vtoan.movies.document.Movie;

import java.util.List;
import java.util.Optional;

public interface IMovieService {
    public List<Movie> allMovies();
    public Optional<Movie> singleMovie(String imdbId);
}
