package com.movierent.dto;

import java.time.LocalDateTime;

public class RentalMovieDTO {

	private Integer quantity;
	private Integer userId;
	private LocalDateTime rentalDate;
	private LocalDateTime dueDate;
	private LocalDateTime returnDate;
	private Boolean penalty;
	
	
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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

	
}
