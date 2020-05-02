package com.movierent.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.movierent.dto.SaleMovieDTO;
import com.movierent.model.EventLog;
import com.movierent.model.SaleMovie;
import com.movierent.model.StockMovie;
import com.movierent.model.User;
import com.movierent.repo.IEventLogRepo;
import com.movierent.repo.ISaleMovieRepo;
import com.movierent.repo.IStockMovieRepo;
import com.movierent.repo.IUserRepo;
import com.movierent.service.ISaleMovieService;

@Service
public class SaleMovieServiceImpl implements ISaleMovieService{

	@Autowired
	private ISaleMovieRepo saleMovieRepo;
	
	@Autowired
	private IStockMovieRepo stockRepo;
	
	@Autowired
	private IUserRepo userRepo;
	
	@Autowired
	private IEventLogRepo eventLogRepo;
	
	@Override
	public List<SaleMovie> list() {
		// TODO Auto-generated method stub
		return saleMovieRepo.findAll();
	}

	@Override
	public SaleMovie listById(Integer obj) {
		Optional<SaleMovie> op = saleMovieRepo.findById(obj);
		if (op.isPresent()) {
			 return op.get();
		}
		return new SaleMovie();
	}

	@Override
	public SaleMovie save(SaleMovie obj) {
		// TODO Auto-generated method stub
		return saleMovieRepo.save(obj);
	}

	@Override
	public SaleMovie edit(SaleMovie obj) {
		// TODO Auto-generated method stub
		return saleMovieRepo.save(obj);
	}

	@Override
	public void delete(Integer id) {
		saleMovieRepo.deleteById(id);
		
	}

	@Override
	public void saveSale( List<StockMovie> stockmov, SaleMovieDTO sale) {
		//Getting loggedUser
		  Authentication userLogged = SecurityContextHolder.getContext().getAuthentication(); 
		  User user= userRepo.findOneByUsername(userLogged.getName()); 
		  
		  //This count is to save only the quantity buyed
		  int[] count = {0};
		  stockmov.stream().forEachOrdered(stockMovie->{
			  if (count[0]< sale.getQuantity()) {
				  SaleMovie saleMovie = new SaleMovie();
				  saleMovie.setDate(LocalDateTime.now());
				  saleMovie.setSalePrice(stockMovie.getMovie().getSalePrice());
				  saleMovie.setStockMovie(stockMovie);
				  saleMovie.setUser(user);
				  saleMovieRepo.save(saleMovie);
				  stockMovie.setAvailability("Saled");
				  stockRepo.save(stockMovie);
				  count[0]++;
			  }
		  });
		  //Save a log of the sale
		  EventLog log = new EventLog();
		  log.setDate(LocalDateTime.now()); 
		  log.setEventType("Sale");
		  log.setQty(sale.getQuantity());
		  log.setUser(user);
		  log.setMovie(stockmov.get(0).getMovie()); 
		  eventLogRepo.save(log);
	}

}
