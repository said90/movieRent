package com.movierent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stock_movies")
public class StockMovie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idStockMovie;
	
	@ManyToOne
	@JoinColumn(name = "id_movie", nullable = false )
	private Movie movie;
	
	@Column(name = "availability")
	private String availability = "Available";

	public int getIdStockMovie() {
		return idStockMovie;
	}

	public void setIdStockMovie(int idStockMovie) {
		this.idStockMovie = idStockMovie;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	
}
