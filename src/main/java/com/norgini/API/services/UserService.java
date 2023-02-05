package com.norgini.API.services;

import java.util.List;

import com.norgini.API.domain.User;

public interface UserService {
	
	User findById(Integer id);
	
	List<User> findAll(); 

}
