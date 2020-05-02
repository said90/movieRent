package com.movierent.service;

import com.movierent.model.User;

public interface IUserService extends ICRUD<User, Integer> {

	User getLoggedUser();
}
