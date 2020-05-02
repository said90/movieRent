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
@Table(name = "rental_movies")
public class RentalMovie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRentalMovie;
	
	@ManyToOne
	@JoinColumn(name = "id_stock_movie", nullable = false )
	private StockMovie stockMovie;
	
	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false )
	private User user;
	
	@Column(name = "rental_date")
	private LocalDateTime rentalDate;
	
	@Column(name = "due_date")
	private LocalDateTime dueDate;
	
	@Column(name = "return_date")
	private LocalDateTime returnDate;
	
	@Column(name = "penalty")
	private Boolean penalty;
	
	@Column(name="penalty_recharge")
	private BigDecimal penaltyRecharge;

	public int getIdRentalMovie() {
		return idRentalMovie;
	}

	public void setIdRentalMovie(int idRentalMovie) {
		this.idRentalMovie = idRentalMovie;
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

	public LocalDateTime getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDateTime rentalDate) {
		this.rentalDate = rentalDate;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public Boolean getPenalty() {
		return penalty;
	}

	public void setPenalty(Boolean penalty) {
		this.penalty = penalty;
	}

	public BigDecimal getPenaltyRecharge() {
		return penaltyRecharge;
	}

	public void setPenaltyRecharge(BigDecimal penaltyRecharge) {
		this.penaltyRecharge = penaltyRecharge;
	}
		
}
