package com.movierent.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movierent.model.MovieLike;

public interface IMovieLikeRepo extends JpaRepository<MovieLike, Integer> {

}
