package com.ub.service;

import com.ub.models.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class MovieService {

    private List<Movie> movies = new ArrayList<Movie>();

    public MovieService(){

        Calendar date = Calendar.getInstance();
        date.set(1976, 1, 1);
        Movie movie = new Movie(1,"Rocky", date);
        movies.add(movie);

        Calendar date2 = Calendar.getInstance();
        date.set(1977, 1, 1);
        movie = new Movie(2,"Star Wars", date2);
        movies.add(movie);

        Calendar date3 = Calendar.getInstance();
        date.set(1979, 1,1);
        movie = new Movie(3,"Mad Max", date3);
        movies.add(movie);

        Calendar date4 = Calendar.getInstance();
        date.set(1981, 0,1);
        movie = new Movie(4,"Indiana Jones", date4);
        movies.add(movie);
    }

    public List<Movie> getMovies(){
        return movies;
    }

    public Movie getMovieById(int id){
        Movie movie = movies.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if(movie != null) {
            return movie;
        } else{
            throw new IllegalArgumentException("Register not found.");
        }
    }

    public Movie createMovie(Movie movie){

        movies.add(movie);
        return movie;
    }

    public Movie patchMovieDate(int id, Calendar date){
        Movie movie = getMovieById(id);
        if(movie != null){
            int position = movies.indexOf(movie);

            movie.setDate(date);
            movies.remove(position);
            movies.add(movie);
            return movie;
        } else{
            throw new IllegalArgumentException("Register not found.");
        }

    }

    public Movie putMovie(int id, Movie movieParam){
        Movie movie = getMovieById(id);
        if(movie != null){
            int position = movies.indexOf(movie);

            movies.remove(position);
            movies.add(movieParam);
            return movieParam;
        } else{
            throw new IllegalArgumentException("Register not found.");
        }
    }

    public boolean removeMovie(int id){
        Movie movie = getMovieById(id);
        if(movie != null){
            int position = movies.indexOf(movie);
            movies.remove(position);
            return true;
        } else{
            throw new IllegalArgumentException("Register not found.");
        }
    }


}
