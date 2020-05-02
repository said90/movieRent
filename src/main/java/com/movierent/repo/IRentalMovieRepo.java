package com.movierent.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movierent.model.RentalMovie;

public interface IRentalMovieRepo extends JpaRepository<RentalMovie, Integer>{

}
