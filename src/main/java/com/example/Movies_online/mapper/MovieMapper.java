package com.example.Movies_online.mapper;

import com.example.Movies_online.dto.movie.MovieResponse;
import com.example.Movies_online.entities.Movie;

import java.util.List;

public interface MovieMapper {
    List<MovieResponse> toDtoS(List<Movie> all);

    MovieResponse toDto(Movie movie);
}