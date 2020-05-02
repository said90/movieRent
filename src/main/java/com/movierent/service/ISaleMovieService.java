package com.movierent.service;

import java.util.List;

import com.movierent.dto.SaleMovieDTO;
import com.movierent.model.SaleMovie;
import com.movierent.model.StockMovie;

public interface ISaleMovieService extends ICRUD<SaleMovie, Integer>{

	
	void saveSale( List<StockMovie> stockmov , SaleMovieDTO saleMovie);

}
