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

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private IUserRepo repo;
	
	// to set user logged and all roles asociated to the user
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
	

}
