package com.movierent.service;

import java.util.List;

import com.movierent.dto.RentalMovieDTO;
import com.movierent.model.RentalMovie;
import com.movierent.model.StockMovie;

public interface IRentalMovieService extends ICRUD<RentalMovie, Integer> {

	RentalMovieDTO saveRental(RentalMovieDTO rent  , List<StockMovie> stockmov);
}
