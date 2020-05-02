package com.movierent.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movierent.model.StockMovie;

public interface IStockMovieRepo  extends JpaRepository<StockMovie, Integer>{

}
