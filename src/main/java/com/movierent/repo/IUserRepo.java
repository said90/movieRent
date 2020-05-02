package com.movierent.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movierent.model.User;

public interface IUserRepo extends JpaRepository<User, Integer> {

	User findOneByUsername(String username);	

}
