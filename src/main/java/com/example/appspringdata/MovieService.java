package com.example.appspringdata;


public interface MovieService {

    // Method to get Movie by its Id
    Movie getMovie(String id);

    // Method to add a new Movie
    // into the database
    String addMovie(Movie movie);

    // Method to update details of a Movie
    String updateMovie(Movie movie);
}
