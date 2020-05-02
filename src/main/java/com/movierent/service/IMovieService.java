package com.movierent.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.movierent.model.Movie;

public interface IMovieService extends ICRUD<Movie, Integer> {

	//This Method will be use to add a remove date
	Movie remove(Integer id);
	//This method will change movie's availability 1 is available any other is not available
	Movie changeState(Integer id, String state);
	//For ADMIN role
	Page<Movie> findAllMovies(String state,String title, Pageable pageable);
	//FOR USER role
	Page<Movie> findAvailableMovies(Pageable pageable, String find);
}
