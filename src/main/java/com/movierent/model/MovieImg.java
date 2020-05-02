package com.movierent.model;

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
@Table(name = "movie_imgs")
public class MovieImg {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMovieImag;
	
	@ManyToOne
	@JoinColumn(name = "id_movie", nullable = false )
	private Movie movie;
	
	@Column(name = "photo")
	private String photo;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;

	public int getIdMovieImag() {
		return idMovieImag;
	}

	public void setIdMovieImag(int idMovieImag) {
		this.idMovieImag = idMovieImag;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

}
