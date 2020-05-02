package com.movierent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movie_likes")
public class MovieLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMovieLike;
	
	@ManyToOne
	@JoinColumn(name = "id_movie", nullable = false )
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false )
	private User user;

	public Integer getIdMovieLike() {
		return idMovieLike;
	}

	public void setIdMovieLike(Integer idMovieLike) {
		this.idMovieLike = idMovieLike;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
