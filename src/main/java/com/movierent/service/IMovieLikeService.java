package com.movierent.service;

import java.util.List;

import com.movierent.model.Movie;
import com.movierent.model.MovieLike;

public interface IMovieLikeService extends ICRUD<MovieLike, Integer> {

	void saveLike(Movie movie);
	List<MovieLike> verifyLike(Integer idMovie, Integer idUser);
}
