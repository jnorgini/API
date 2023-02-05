package com.norgini.API.services;

import java.util.List;

import com.norgini.API.domain.User;
import com.norgini.API.dto.UserDTO;

public interface UserService {
	
	User findById(Integer id);
	
	List<User> findAll();
	
	User create(UserDTO obj);

}
