package com.movierent.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movierent.model.Movie;

public interface IMovieRepo extends JpaRepository<Movie, Integer> {

}
