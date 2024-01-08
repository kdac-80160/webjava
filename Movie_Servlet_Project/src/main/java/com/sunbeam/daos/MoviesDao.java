package com.sunbeam.daos;

import java.util.List;

import com.sunbeam.pojos.Movies;

public interface MoviesDao extends AutoCloseable {
	int searchMovie(int movieId);
	List<Movies> getMovies();
	Movies getMovieById(int id);
}
