package com.example.appspringdata;


import com.example.appspringdata.MovieAlreadyExistsException;
import com.example.appspringdata.NoSuchMovieExistsException;
import com.example.appspringdata.Movie;
import com.example.appspringdata.MovieRepository;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository MovieRespository;

    // Method to get Movie by Id. Throws
    // NoSuchElementException for invalid Id
    public Movie getMovie(String id) {
        return MovieRespository.findById(id).orElseThrow(
            () -> new NoSuchMovieExistsException("NO Movie PRESENT WITH ID = " + id));
    }

  
    // Simplifying the addMovie and updateMovie
    // methods with Optional for better readability.
    
    public String addMovie(Movie Movie) {
        Optional<Movie> existingMovie = MovieRespository.findById(Movie.getMovieId());
        if (!existingMovie.isPresent()) {
            MovieRespository.save(Movie);
            return "Movie added successfully";
        } else {
            throw new MovieAlreadyExistsException("Movie already exists!!");
        }
    }

    public String updateMovie(Movie Movie) {
        Optional<Movie> existingMovie = MovieRespository.findById(Movie.getMovieId());
        if (!existingMovie.isPresent()) {
            throw new NoSuchMovieExistsException("No Such Movie exists!!");
        } else {
            existingMovie.get().setTitle(Movie.getTitle());
            existingMovie.get().setPoster(Movie.getPoster());
            MovieRespository.save(existingMovie.get());
            return "Record updated Successfully";
        }
    }
}
