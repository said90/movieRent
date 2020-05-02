package com.movierent.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movierent.model.StockMovie;

public interface IStockMovieRepo  extends JpaRepository<StockMovie, Integer>{

	//This method is to know if a movie has stock available
	@Query("from StockMovie s where s.movie.idMovie = :idMovie and s.availability = 'Available'" )
	List<StockMovie> availableStock(@Param("idMovie") Integer idMovie);

	//Return how many stock has a movie
	@Query("SELECT count(s.idStockMovie) FROM StockMovie s WHERE s.movie.idMovie = :idMovie")
	Integer getStockMovieById(@Param("idMovie") Integer idMovie);
}
