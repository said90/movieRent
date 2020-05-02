package com.movierent.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.movierent.repo.IUserRepo;
import com.movierent.service.IUserService;
 

@Service
public class UserServiceImpl implements UserDetailsService, IUserService {

	@Autowired
	private IUserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.movierent.model.User user= repo.findOneByUsername(username);		
		
		if(user == null) {
			throw new UsernameNotFoundException(String.format("User not exist", username));
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		
		user.getRoles().forEach(rol -> {
			roles.add(new SimpleGrantedAuthority(rol.getname()));
		});

		UserDetails ud = new User(user.getUsername(), user.getPassword(), roles);
		return ud;
	}

	@Override
	public List<com.movierent.model.User> list() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public com.movierent.model.User listById(Integer obj) {
		Optional<com.movierent.model.User> user = repo.findById(obj);
		if (!user.isPresent()) {
			return  new com.movierent.model.User(); 
		}
		return user.get() ;
	}

	@Override
	public com.movierent.model.User save(com.movierent.model.User obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public com.movierent.model.User edit(com.movierent.model.User obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public com.movierent.model.User getLoggedUser() {
		Authentication userLogged = SecurityContextHolder.getContext().getAuthentication(); 
		com.movierent.model.User user= repo.findOneByUsername(userLogged.getName()); 
		return user;
	}

}
