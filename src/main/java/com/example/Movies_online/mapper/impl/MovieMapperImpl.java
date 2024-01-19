package com.example.Movies_online.mapper.impl;

import com.example.Movies_online.dto.movie.MovieResponse;
import com.example.Movies_online.entities.Movie;
import com.example.Movies_online.mapper.MovieMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapperImpl implements MovieMapper {
    @Override
    public List<MovieResponse> toDtoS(List<Movie> all) {
        List<MovieResponse> movieResponses = new ArrayList<>();
        for (Movie movie: all){
            movieResponses.add(toDto(movie));
        }
        return movieResponses;
    }

    @Override
    public MovieResponse toDto(Movie movie) {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setId(movie.getId());
        movieResponse.setName(movie.getName());
        movieResponse.setExist(movie.getExist());
        movieResponse.setTranscript(movie.getTranscript());
        movieResponse.setAge_access(movie.getAgeAccess());
        movieResponse.setPrice(movie.getPrice());
        movieResponse.setCreated_date(movie.getCreated_date());
        movieResponse.setAuthor_full_name(movie.getAuthor_full_name());
        movieResponse.setType(movie.getType().getName());
        return movieResponse;
    }
}