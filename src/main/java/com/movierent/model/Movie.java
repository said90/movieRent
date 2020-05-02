package com.movierent.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMovie;
	
	@Column
	private String title;
	
	@Column(name = "description", length = 1000)
	private String description;
	
	@Column(name = "likes")
	private Integer likes;
	
	@Column(name = "rent_price")
	private BigDecimal rentPrice;
	
	@Column(name = "sale_price")
	private BigDecimal salePrice;
	
	@Column(name = "availability")
	private String availability = "1";
	
	@Column(name = "removed_date")
	private LocalDateTime removedDateTime;
	
	
	public Integer getIdMovie() {
		return idMovie;
	}
	public void setIdMovie(Integer idMovie) {
		this.idMovie = idMovie;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public BigDecimal getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(BigDecimal rentPrice) {
		this.rentPrice = rentPrice;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public String isAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public LocalDateTime getRemovedDateTime() {
		return removedDateTime;
	}
	public void setRemovedDateTime(LocalDateTime removedDateTime) {
		this.removedDateTime = removedDateTime;
	}
}
