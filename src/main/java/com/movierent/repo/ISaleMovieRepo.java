package com.movierent.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movierent.model.SaleMovie;

public interface ISaleMovieRepo extends JpaRepository<SaleMovie, Integer> {

}
