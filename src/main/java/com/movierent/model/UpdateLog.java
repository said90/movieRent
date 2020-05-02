package com.movierent.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "update_logs")
public class UpdateLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUpdateLog;
	
	@Column(name = "date")
	private LocalDateTime date;
	
	@Column(name = "title", length = 150)
	private String title;
	
	@Column(name = "rent_price")
	private BigDecimal rentPrice;
	
	@Column(name = "sale_price")
	private BigDecimal salePrice;
	
	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false, foreignKey =  @ForeignKey(name="FK_user") )
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "id_movie", nullable = false, foreignKey =  @ForeignKey(name="FK_movie") )
	private Movie movie;

	public int getIdUpdateLog() {
		return idUpdateLog;
	}

	public void setIdUpdateLog(int idUpdateLog) {
		this.idUpdateLog = idUpdateLog;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
}
