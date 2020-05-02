package com.movierent.service;

import java.util.List;

import com.movierent.model.StockMovie;

public interface IStockMovieService extends ICRUD<StockMovie, Integer> {
    
	//to know availble stock of a movie
	List<StockMovie> availableStock(Integer movieId);
	// quantity of stock available
	Integer getStockMovieById( Integer idMovie);

}
