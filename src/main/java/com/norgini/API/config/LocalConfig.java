package com.norgini.API.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.norgini.API.domain.User;
import com.norgini.API.repositories.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {
	
	@Autowired
	private UserRepository repository;
	
	@Bean
	public void startDB() {
		User u1 = new User(null, "Juliana", "juliana@gmail.com", "123");
		User u2 = new User(null, "Norgini", "norgini@gmail.com", "321");
		
		repository.saveAll(List.of(u1, u2));
	}

}
