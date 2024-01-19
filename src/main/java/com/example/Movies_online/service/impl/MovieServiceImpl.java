package com.example.Movies_online.service.impl;

import com.example.Movies_online.dto.movie.MovieAddRequest;
import com.example.Movies_online.dto.movie.MovieResponse;
import com.example.Movies_online.entities.Movie;
import com.example.Movies_online.entities.Type;
import com.example.Movies_online.entities.User;
import com.example.Movies_online.enums.Role;
import com.example.Movies_online.exception.BadCredentialsException;
import com.example.Movies_online.exception.NotFoundException;
import com.example.Movies_online.mapper.MovieMapper;
import com.example.Movies_online.repositories.MovieRepository;
import com.example.Movies_online.repositories.TypeRepository;
import com.example.Movies_online.repositories.UserRepository;
import com.example.Movies_online.service.AuthService;
import com.example.Movies_online.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final AuthService authService;
    private final TypeRepository typeRepository;
    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Override
    public void addMovie(MovieAddRequest request, String token) {
        if (movieRepository.findByTranscript(request.getTranscript()).isPresent())
            throw new NotFoundException("movie with this transcript is already exist!: "+request.getTranscript(),
                    HttpStatus.BAD_REQUEST);
        if (!authService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("this function only for admin!");

        Movie movie = new Movie();

        movie.setName(request.getName());
        movie.setPrice(request.getPrice());
        movie.setAgeAccess(request.getAge_access());
        movie.setAuthor_full_name(request.getAuthor_full_name());
        movie.setTranscript(request.getTranscript());
        movie.setCreated_date(LocalDateTime.now().toString());
        movie.setExist(true);
        Optional<Type> type = typeRepository.findByName(request.getType());
        if (type.isEmpty())
            throw new NotFoundException("no type with name: "+request.getType(), HttpStatus.BAD_REQUEST);
        movie.setType(type.get());
        movieRepository.save(movie);
    }

    @Override
    public List<MovieResponse> getAll(String s) {
        User user = authService.getUsernameFromToken(s);

        if (!user.getRole().equals(Role.ADMIN)){
            System.out.println("the user");
            List<MovieResponse> movieResponses = movieMapper.toDtoS(movieRepository.findAllByExistAndAgeAccessLessThan(
                    true, user.getCustomer().getAge()));
            System.out.println("the size: "+movieResponses.size());
            return movieResponses;
        }
        System.out.println("the admin");

        return movieMapper.toDtoS(movieRepository.findAll());
    }

    @Override
    public void buy(Long movieId, String token) {
        User user = authService.getUsernameFromToken(token);
        Optional<Movie> book = movieRepository.findById(movieId);
        if (book.isEmpty())
            throw new NotFoundException("this book sold", HttpStatus.BAD_REQUEST);
        book.get().setExist(false);
        List<Movie> movies = new ArrayList<>();
        if (!user.getCustomer().getMovies().isEmpty())
            movies = user.getCustomer().getMovies();
        movies.add(book.get());
        user.getCustomer().setMovies(movies);
        userRepository.save(user);
    }

    @Override
    public List<MovieResponse> getMyMovies(String string) {
        User user = authService.getUsernameFromToken(string);

        if (!user.getRole().equals(Role.ADMIN)){
            System.out.println("the user");
            List<MovieResponse> movieResponses = movieMapper.toDtoS(user.getCustomer().getMovies());
            System.out.println("the size: "+movieResponses.size());
            return movieResponses;
        }
        return null;
    }
}