package com.movierent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movierent.model.StockMovie;
import com.movierent.repo.IStockMovieRepo;
import com.movierent.service.IStockMovieService;

@Service
public class StockMovieServiceImpl implements IStockMovieService{

	@Autowired
	private IStockMovieRepo stockMovieRepo;
	
	//get all stock
	@Override
	public List<StockMovie> list() {
		// TODO Auto-generated method stub
		return stockMovieRepo.findAll();
	}

	//get a specific stok by id
	@Override
	public StockMovie listById(Integer obj) {
		Optional<StockMovie> st = stockMovieRepo.findById(obj);
		if (st.get().getIdStockMovie() == 0){
			return new StockMovie();

		}
		return st.get();
	}

	//save a new stock record
	@Override
	public StockMovie save(StockMovie obj) {
		// TODO Auto-generated method stub
		return stockMovieRepo.save(obj);
	}

	//edit a stock record
	@Override
	public StockMovie edit(StockMovie obj) {
		// TODO Auto-generated method stub
		return stockMovieRepo.save(obj);
	}

	//Deletes a record
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		stockMovieRepo.deleteById(id);
	}

	//get all the stockDetails from a movie
	@Override
	public List<StockMovie> availableStock(Integer movieId) {
		List<StockMovie> stock= stockMovieRepo.availableStock(movieId);
		return stock;
	}

	//get the quantity of stock from a movie
	@Override
	public Integer getStockMovieById(Integer idMovie) {
		return stockMovieRepo.getStockMovieById(idMovie);
	}

}
