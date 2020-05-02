package com.movierent.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sale_movies")
public class SaleMovie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSaleMovie;
	
	@ManyToOne
	@JoinColumn(name = "id_stock_movie", nullable = false )
	private StockMovie stockMovie;
	
	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false )
	private User user;
	
	@Column(name = "date")
	private LocalDateTime date;
	
	@Column(name = "sale_price")
	private BigDecimal salePrice;

	
	public int getIdSaleMovie() {
		return idSaleMovie;
	}

	public void setIdSaleMovie(int idSaleMovie) {
		this.idSaleMovie = idSaleMovie;
	}

	public StockMovie getStockMovie() {
		return stockMovie;
	}

	public void setStockMovie(StockMovie stockMovie) {
		this.stockMovie = stockMovie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	
}
