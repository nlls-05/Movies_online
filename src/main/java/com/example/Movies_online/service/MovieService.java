package com.example.Movies_online.service;

import com.example.Movies_online.dto.movie.MovieAddRequest;
import com.example.Movies_online.dto.movie.MovieResponse;

import java.util.List;

public interface MovieService {
    void addMovie(MovieAddRequest request, String token);

    List<MovieResponse> getAll(String s);

    void buy(Long bookId, String token);

    List<MovieResponse> getMyMovies(String string);
}