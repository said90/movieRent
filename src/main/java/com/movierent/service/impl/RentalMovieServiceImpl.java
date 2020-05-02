package com.movierent.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.movierent.dto.RentalMovieDTO;
import com.movierent.model.EventLog;
import com.movierent.model.RentalMovie;
import com.movierent.model.StockMovie;
import com.movierent.model.User;
import com.movierent.repo.IEventLogRepo;
import com.movierent.repo.IRentalMovieRepo;
import com.movierent.repo.IStockMovieRepo;
import com.movierent.repo.IUserRepo;
import com.movierent.service.IRentalMovieService;

@Service
public class RentalMovieServiceImpl implements IRentalMovieService {

	@Autowired
	private IRentalMovieRepo rentalRepo;
	
	@Autowired
	private IStockMovieRepo stockRepo;
	
	@Autowired
	private IUserRepo userRepo;
	
	@Autowired
	private IEventLogRepo eventLogRepo;
	
	@Override
	public List<RentalMovie> list() {
		// TODO Auto-generated method stub
		return rentalRepo.findAll();
	}

	@Override
	public RentalMovie listById(Integer obj) {
		Optional<RentalMovie> op = rentalRepo.findById(obj);
		 if (op.isPresent()) {
			 return op.get();

		} 
			return new RentalMovie();
		
	}

	@Override
	public RentalMovie save(RentalMovie obj) {
		// TODO Auto-generated method stub
		return rentalRepo.save(obj);
	}

	@Override
	public RentalMovie edit(RentalMovie obj) {
		// TODO Auto-generated method stub
		return rentalRepo.save(obj);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		rentalRepo.deleteById(id);
	}

	@Override
	public RentalMovieDTO saveRental(RentalMovieDTO rent ,  List<StockMovie> sto) {
		  //Getting loggedUser
		  Authentication userLogged = SecurityContextHolder.getContext().getAuthentication(); 
		  User user= userRepo.findOneByUsername(userLogged.getName()); 
		  
		 // this counter is a helper to know how many rents has to save 
		  int[] count = {0};
		  sto.stream().forEachOrdered(stockMovie->{
			  if (count[0]< rent.getQuantity()) {
				  RentalMovie rental = new RentalMovie();
		        	 rental.setStockMovie(stockMovie);
				     rental.setRentalDate(rent.getRentalDate());
				     rental.setDueDate(rent.getDueDate());
				     rental.setUser(user);
				     rentalRepo.save(rental); 
				     stockMovie.setAvailability("Rented");
				     stockRepo.save(stockMovie);
				     count[0]++;
			  	}  
		     }); 
		 //Here Save a rent log register
		  EventLog log = new EventLog();
		  log.setDate(LocalDateTime.now()); 
		  log.setEventType("Rent");
		  log.setQty(rent.getQuantity());
		  log.setUser(user);
		  log.setMovie(sto.get(0).getMovie()); 
		  eventLogRepo.save(log);
		return rent;
	}

}
