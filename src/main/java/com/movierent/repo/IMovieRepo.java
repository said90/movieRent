package com.movierent.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movierent.model.Movie;

public interface IMovieRepo extends JpaRepository<Movie, Integer> {

	//This Query get all the movies available or not available in base to the filter parameter, and not removed movies.
	//Uses a pageable object to paginate results, also filter the title   when a user send the parameter
	//and is ordered by  greater likes first then is ordered by title this is used for ADMIN role
	@Query("FROM Movie m WHERE m.removedDateTime= null AND  m.availability LIKE  CONCAT('%',:state,'%') AND  lower(m.title)  LIKE CONCAT('%',lower(:title),'%') ORDER BY m.likes, m.title" )
	Page<Movie> findAllMovies(@Param("state") String state,@Param("title") String title,  Pageable pageable);
	
	//This Query get all the movies available , and not removed movies.
	//Uses a pageable object to paginate results, also filter the title, when a user send the parameter
	//and is ordered by  greater likes first then is ordered by title
	//Show only movies with stock available for every movie and if a movie  has not availability even if has stock the movie will not be showed 
	//this is used for USER role
	@Query("FROM Movie  m WHERE  m.removedDateTime= null AND m.availability = '1' AND (SELECT COUNT(sm.idStockMovie) FROM StockMovie sm WHERE  sm.movie.idMovie = m.idMovie and sm.availability = 'Available')>0 AND lower(m.title) LIKE CONCAT('%',lower( :find),'%') order by m.likes, m.title")
	Page<Movie> findAvailableMovies( @Param("find") String find,Pageable pageable);
}
