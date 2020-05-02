package com.movierent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.movierent.model.Movie;
import com.movierent.model.MovieLike;
import com.movierent.model.User;
import com.movierent.repo.IMovieLikeRepo;
import com.movierent.repo.IMovieRepo;
import com.movierent.repo.IUserRepo;
import com.movierent.service.IMovieLikeService;

@Service
public class MovieLikeServiceImpl implements IMovieLikeService {
	
	@Autowired
	private IMovieLikeRepo movieLikeRepo;

	@Autowired
	private IMovieRepo movieRepo;
	
	@Autowired
	private IUserRepo userRepo;
	
	@Override
	public List<MovieLike> list() {
		// TODO Auto-generated method stub
		return movieLikeRepo.findAll();
	}

	@Override
	public MovieLike listById(Integer obj) {
		Optional<MovieLike> op = movieLikeRepo.findById(obj);
		if (op.isPresent()) {
			return op.get();
		}
		return new MovieLike();
	}

	@Override
	public MovieLike save(MovieLike obj) {
		// TODO Auto-generated method stub
		return movieLikeRepo.save(obj);
	}

	@Override
	public MovieLike edit(MovieLike obj) {
		// TODO Auto-generated method stub
		return movieLikeRepo.save(obj);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		movieLikeRepo.deleteById(id);
	}

	@Override
	public void saveLike(Movie movie) {
		 MovieLike like = new MovieLike();
		 like.setMovie(movie);
		 
		 //Getting loggedUser
		  Authentication userLogged = SecurityContextHolder.getContext().getAuthentication(); 
		  User user= userRepo.findOneByUsername(userLogged.getName()); 
		  
		  like.setUser(user);
		  movieLikeRepo.save(like);
		  
		  Optional<Movie> mov = movieRepo.findById(like.getMovie().getIdMovie());
		  //verify how many likes has a movie and sum 1 like
		  mov.get().setLikes(mov.get().getLikes() == null ?1:mov.get().getLikes()+1);
		  movieRepo.save(mov.get());
	}

	@Override
	public List<MovieLike> verifyLike(Integer idMovie, Integer idUser) {
		// TODO Auto-generated method stub
		return movieLikeRepo.verifyLike(idMovie, idUser);
	}

}
