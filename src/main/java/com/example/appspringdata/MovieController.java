package com.example.appspringdata;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieRepository movieRepo;
    @Autowired private MovieService movieService;

    public MovieController(MovieRepository movieRepo ) {
        this.movieRepo = movieRepo;
    }
    @GetMapping()
    Iterable<Movie> findAllMovies() {
        return movieRepo.findMoviesSubset();
    }

    @GetMapping("/{movieId}")
    Movie findMovieById(@PathVariable String movieId) {
        Movie m1= movieService.getMovie(movieId);
        System.out.println("Finding movie with movieId: " + m1.getTitle());
        return m1;
    }

    @PostMapping("/save")

    Movie save(@RequestBody Movie movie) {
        return movieRepo.save(movie);
    }

    @DeleteMapping("/delete")
    void delete(@RequestParam String movieId) {
        movieRepo.deleteById(movieId);
        System.out.println("Deleted movie with movieId: " + movieId);
    }

    @GetMapping("/person")
    Iterable<Movie> findMoviesByPerson(@RequestParam String name) {
        return movieRepo.findMoviesByPerson(name);
    }

    @PutMapping("/updateVotes")
    Movie updateVotes(@RequestParam String movieId) {
        return movieRepo.incrementImdbVotes(movieId);
    }

    @GetMapping("/movielist")

    Iterable<MovieProjection> findAllMovieProjections() { return movieRepo.findAllMovieProjectionsBy(); }

    @PutMapping("/update")

    String updateMovie(@RequestBody Movie movie) {
        return movieService.updateMovie(movie);
    }
    @PostMapping("/add")

    String addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @ExceptionHandler(value = MovieAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleMovieAlreadyExistsException(MovieAlreadyExistsException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(value = NoSuchMovieExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoSuchMovieExistsException(NoSuchMovieExistsException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoSuchElementException(NoSuchElementException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
