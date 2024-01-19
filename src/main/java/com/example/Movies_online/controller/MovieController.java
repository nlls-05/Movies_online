package com.example.Movies_online.controller;

import com.example.Movies_online.dto.movie.MovieAddRequest;
import com.example.Movies_online.dto.movie.MovieResponse;
import com.example.Movies_online.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@AllArgsConstructor
public class MovieController {
    private MovieService movieService;

    @PostMapping("/add")
    public void addMovie(@RequestBody MovieAddRequest request, @RequestHeader("Authorization") String token){
        movieService.addMovie(request, token);
    }
    @GetMapping("/movies")
    public List<MovieResponse> movieResponses(@RequestHeader("Authorization") String string){
        return movieService.getAll(string);
    }


    @PostMapping("/buy/{movieId}")
    public void buy(@PathVariable Long movieId, @RequestHeader("Authorization") String token){
        movieService.buy(movieId, token);
    }
    @GetMapping("/my/movies")
    public List<MovieResponse> myMovies(@RequestHeader("Authorization") String string){
        return movieService.getMyMovies(string);
    }
}