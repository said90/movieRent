package com.movierent.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movierent.model.Movie;

public interface IMovieRepo extends JpaRepository<Movie, Integer> {

	//This Query get all the movies available or not available in base to the parameter, and not removed movies.
	//Uses a pageable object to paginate results, also filter the title   when a user send the paremeter
	//and is ordered by  greater likes first then is ordered by title
	@Query("FROM Movie m WHERE m.removedDateTime= null AND  m.availability LIKE  CONCAT('%',:state,'%') AND  lower(m.title)  LIKE CONCAT('%',lower(:title),'%') ORDER BY m.likes, m.title" )
	Page<Movie> findAllMovies(@Param("state") String state,@Param("title") String title,  Pageable pageable);
}
