package com.movierent.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.movierent.dto.RentalMovieDTO;
import com.movierent.dto.SaleMovieDTO;
import com.movierent.exception.NotFoundExceptionModel;
import com.movierent.model.Movie;
import com.movierent.model.StockMovie;
import com.movierent.model.User;
import com.movierent.service.IMovieService;
import com.movierent.service.IRentalMovieService;
import com.movierent.service.ISaleMovieService;
import com.movierent.service.IStockMovieService;
import com.movierent.service.IUserService;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

	@Autowired
	private IMovieService movieService;

	@Autowired
	private IRentalMovieService rentalService;

	@Autowired
	private IStockMovieService stockMovieService;

	@Autowired
	private ISaleMovieService saleMovieService;

	
	@Autowired
	private IUserService userService;

	
	// Creating a new Movie validating only Admin role can do this action
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping
	public ResponseEntity<Object> add(@RequestBody Movie movie) {
		Movie mov = movieService.save(movie);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mov.getIdMovie())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	// Modifying a  Movie validating only Admin role can do this action
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping
	public ResponseEntity<Movie> modify(@RequestBody Movie movie) {
		Movie obj = movieService.edit(movie);

		return new ResponseEntity<Movie>(obj, HttpStatus.OK);
	}

	// Removing a  Movie validating only Admin role can do this action

	@PreAuthorize("hasAuthority('ADMIN')")
	@PatchMapping("/{id}/remove")
	public ResponseEntity<Movie> remove(@PathVariable("id") Integer id) {
		Movie movie = movieService.listById(id);
		//Validate if movie exist and is not removed
		if (movie.getIdMovie() == null) {
			throw new NotFoundExceptionModel("Not Found id " + id);
		} else if(movie.getRemovedDateTime() !=null){
			throw new NotFoundExceptionModel("Not Found Movie");
		} else {
			Movie obj = movieService.remove(id);
			return new ResponseEntity<Movie>(obj, HttpStatus.OK);
		}
	}

	//Delete a movie
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
		Movie movie = movieService.listById(id);
		if (movie.getIdMovie() == null) {
			throw new NotFoundExceptionModel("Not Found id " + id);
		} else {
			movieService.delete(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
	}

	//Changing movie availability 1 is available any other number is unavailable
	@PreAuthorize("hasAuthority('ADMIN')")
	@PatchMapping("/{id}")
	public ResponseEntity<Movie> changeState(@PathVariable("id") Integer id,
			@RequestParam(name = "state", required = true) String state) {
		Movie movie = movieService.listById(id);
		if (movie.getIdMovie() == null) {
			throw new NotFoundExceptionModel("Not Found id " + id);
		}else if(movie.getRemovedDateTime() !=null){
			throw new NotFoundExceptionModel("Not Found Movie");
		}  else {
			Movie obj = movieService.changeState(id, state);
			return new ResponseEntity<Movie>(obj, HttpStatus.OK);
		}
	}


	//Create stock for specific movie 
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/{id}/stock")
	public ResponseEntity<Object> addStock(@PathVariable("id") Integer id){
		Movie movie = movieService.listById(id);
		if (movie.getIdMovie() == null) {
			throw new NotFoundExceptionModel("Not Found id " + id);
		}else if(movie.getRemovedDateTime() !=null){
			throw new NotFoundExceptionModel("Not Found Movie");
		}
		StockMovie stock = new StockMovie();
		stock.setAvailability("Available");
		stock.setMovie(movie);
		StockMovie st = stockMovieService.save(stock);
		return new ResponseEntity<Object>(st,HttpStatus.CREATED);
	}

	
	//Getting Stock quantity for a specific movie
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/{id}/stock")
	public ResponseEntity<Object> stockByMovie(@PathVariable("id") Integer id){
		Movie movie = movieService.listById(id);
		if (movie.getIdMovie() == null) {
			throw new NotFoundExceptionModel("Not Found id " + id);
		}else if(movie.getRemovedDateTime() !=null){
			throw new NotFoundExceptionModel("Not Found Movie");
		}

		Integer quantity = stockMovieService.getStockMovieById(id);
		return new ResponseEntity<Object>(quantity, HttpStatus.OK);
	}

	//Rent a movie, if you don't create stock previously you can't not rent a movie
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@PostMapping("/{id}/rent")
	public ResponseEntity<RentalMovieDTO> rentMovie(@PathVariable("id") Integer id, @RequestBody RentalMovieDTO rent)
	{
		Movie movie = movieService.listById(id);
		if (movie.getIdMovie() == null) {
			throw new NotFoundExceptionModel("Not Found id " + id);
		}else if(movie.getRemovedDateTime() !=null){
			throw new NotFoundExceptionModel("Not Found Movie");
		}
		List<StockMovie> stock = stockMovieService.availableStock(id);

		//validating if exist stock and if the stock is enought for the request
		if (stock.size() == 0 || stock.size() < rent.getQuantity()) {
			throw new NotFoundExceptionModel("No Stock Available For Quantity Requested");
		}
		rentalService.saveRental(rent, stock);
		return new ResponseEntity<RentalMovieDTO>(rent, HttpStatus.CREATED);
	}

	//Sale a movie, if you don't create stock previously you can't not sale a movie
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@PostMapping("/{id}/sale")
	public ResponseEntity<Object> saleMovie(@PathVariable("id") Integer id, @RequestBody SaleMovieDTO saleMovie) {
		Movie movie = movieService.listById(id);
		if (movie.getIdMovie() == null) {
			throw new NotFoundExceptionModel("Not Found id " + id);
		}else if(movie.getRemovedDateTime() !=null){
			throw new NotFoundExceptionModel("Not Found Movie");
		}
		List<StockMovie> stock = stockMovieService.availableStock(id);

		//validating if exist stock and if the stock is enought for the request
		if (stock.size() == 0 || stock.size() < saleMovie.getQuantity()) {
			throw new NotFoundExceptionModel("No Stock Available For Quantity Requested");
		}
		saleMovieService.saveSale(stock, saleMovie);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}


    //Getting list of movie
	@GetMapping
	public ResponseEntity<Page<Movie>> list( 
			@RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size,
			@RequestParam Optional<String> sortBy,
			@RequestParam Optional<String> title,
			@RequestParam Optional<String> state) {
		
		User user = userService.getLoggedUser();
		
		//validating if is a logged user
		if (user !=null) {
			//validate if the user has an ADMIN role
			if (user.getRoles().stream().filter(role-> role.getname().equals("ADMIN")).findFirst().isPresent()) {
				Pageable pag = PageRequest.of(page.orElse(0), size.orElse(Integer.MAX_VALUE));
				Page<Movie> movies=movieService.findAllMovies(state.orElse("_"),title.orElse("_"),pag );
				return new ResponseEntity<Page<Movie>>(movies,HttpStatus.OK);
			}
		}
		//IF is not an ADMIN role show only movies availables and with stock available
		Pageable pag = PageRequest.of(page.orElse(0), size.orElse(Integer.MAX_VALUE));
		Page<Movie> movies = movieService.findAvailableMovies(pag, title.orElse("_"));
		return new ResponseEntity<Page<Movie>>(movies, HttpStatus.OK);
	}

	//Getting information from specific movie
	@GetMapping("/{id}")
	public ResponseEntity<Movie> listById(@PathVariable("id") Integer id) {
		Movie movie = movieService.listById(id);
		if (movie.getIdMovie() == null) {
			throw new NotFoundExceptionModel("Not Found id " + id);
		}
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}

}
