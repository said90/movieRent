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
@Table(name = "event_logs")
public class EventLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEventLog;
	
	@ManyToOne
	@JoinColumn(name = "id_movie", nullable = false )
	private Movie movie;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false )
	private User user;
	
	@Column(name = "date")
	private LocalDateTime date;
	
	@Column(name = "event_type")
	private String eventType;
	
	@Column(name = "qty")
	private int qty;

	public int getIdEventLog() {
		return idEventLog;
	}

	public void setIdEventLog(int idEventLog) {
		this.idEventLog = idEventLog;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
}
