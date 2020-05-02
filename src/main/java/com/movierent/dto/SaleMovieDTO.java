package com.movierent.dto;

import java.time.LocalDateTime;

public class SaleMovieDTO {

	
	private Integer quantity;
	private LocalDateTime date;
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
}
