package com.movierent.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.movierent.model.Movie;
import com.movierent.model.UpdateLog;
import com.movierent.model.User;
import com.movierent.repo.IMovieRepo;
import com.movierent.repo.IUpdateLogRepo;
import com.movierent.repo.IUserRepo;
import com.movierent.service.IMovieService;

@Service
public class MovieServiceImpl implements IMovieService{

	@Autowired
	private IMovieRepo movieRepo;
	@Autowired
	private IUserRepo userRepo;
	@Autowired
	private IUpdateLogRepo updateLogRepo;
	
	//Get list of movies not removed
	@Override
	public List<Movie> list() {
		 List<Movie> lst = movieRepo.findAll();
		 List<Movie> lstFilter = lst.stream().filter(movie -> movie.getRemovedDateTime() ==null).collect(Collectors.toList());
		 
		return lstFilter;
	}

	//Find a Movie by Id
	@Override
	public Movie listById(Integer id) {
		Optional<Movie> op= movieRepo.findById(id);
		return op.isPresent()?op.get(): new Movie();
	}

	
	//Save Movie
	@Override
	public Movie save(Movie obj) {
		return movieRepo.save(obj);
	}

	//Edit Movie
	@Override
	public Movie edit(Movie obj) {
		Optional<Movie> op= movieRepo.findById(obj.getIdMovie());
		
		//Verifying if the movie exist on db
		if (op.isPresent()) {
			UpdateLog log = new UpdateLog();
			log.setDate(LocalDateTime.now());
			log.setMovie(obj);
			
			//verifying what field was modified
			if (!op.get().getTitle().equals(obj.getTitle())) {
				log.setTitle(obj.getTitle());
			} else if(!op.get().getRentPrice().equals(obj.getRentPrice())) {
				log.setRentPrice(obj.getRentPrice());
			}else if(!op.get().getSalePrice().equals(obj.getSalePrice())) {
				log.setSalePrice(obj.getSalePrice());
			}
			//verifying if one of the field was updates to save on update log table
			if (log.getTitle() !=null || log.getSalePrice() !=null || log.getRentPrice() !=null ) {
				Authentication userLogged = SecurityContextHolder.getContext().getAuthentication();
				User user= userRepo.findOneByUsername(userLogged.getName());
				log.setUser(user);
				updateLogRepo.save(log);
			}
		}
			return movieRepo.save(obj);
	}

	@Override
	public void delete(Integer id) {
		movieRepo.deleteById(id);
	}

	//Removing a Movie by Id adding a removed date
	@Override
	public Movie remove(Integer id) {
		Optional<Movie> op= movieRepo.findById(id);
		 if (op.isPresent()) {
			op.get().setRemovedDateTime(LocalDateTime.now());
			movieRepo.save(op.get());
			return op.get();
		}else {
			return new Movie();
		}
	}

	//Change movie availability 1 is available and other number is not available
	@Override
	public Movie changeState(Integer id, String state) {
		Optional<Movie> op= movieRepo.findById(id);
		 if (op.isPresent()) {
			op.get().setAvailability(state);
			movieRepo.save(op.get());
			return op.get();
		}else {
			return new Movie();
		}
	}

	//This method is only used to show movies for ADMIN role
	//Get all movies filtering by state , finding by title y paging configuration
	@Override
	public Page<Movie> findAllMovies(String state,String title, Pageable pageable) {
		// TODO Auto-generated method stub
		return movieRepo.findAllMovies(state, title, pageable);
	}

	
	//This method is only used to show movies for USER role or not authenticated
	//Get movies with stock available, not removed movies, and if a movie has not availability but has stock is not showed
	@Override
	public Page<Movie> findAvailableMovies(Pageable pageable, String find) {
		Page<Movie> m = movieRepo.findAvailableMovies( find,pageable);
		return m;
	}
}
