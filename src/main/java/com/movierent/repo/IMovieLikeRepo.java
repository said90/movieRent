package com.movierent.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movierent.model.MovieLike;

public interface IMovieLikeRepo extends JpaRepository<MovieLike, Integer> {
		//Verifying if an user liked before a same movie
	@Query("from MovieLike ml where ml.movie.idMovie = :idMovie and ml.user.idUser =:idUser" )
	List<MovieLike> verifyLike (@Param("idMovie") Integer idMovie, @Param("idUser") Integer idUser);
}
