package com.movierent.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movierent.model.MovieImg;

public interface IMovieImgRepo extends JpaRepository<MovieImg, Integer> {

}
