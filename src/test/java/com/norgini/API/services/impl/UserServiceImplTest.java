package com.norgini.API.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.norgini.API.domain.User;
import com.norgini.API.dto.UserDTO;
import com.norgini.API.repositories.UserRepository;
import com.norgini.API.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class UserServiceImplTest {
	
	private static final Integer ID = 1;
	private static final String NAME = "Juliana";
	private static final String EMAIL = "juliana@gmail.com";
	private static final String PASSWORD = "123";
	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
	private static final int INDEX = 0;

	@InjectMocks
	private UserServiceImpl service;
	
	@Mock
	private UserRepository repository;
	
	@Mock
	private ModelMapper mapper;
	
	private User user;
	private UserDTO userDTO;
	private Optional<User> optionalUser;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void whenFindByIdThenReturnUserInstance() {
		when(repository.findById(anyInt())).thenReturn(optionalUser);
		
		User response = service.findById(ID);
		
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
	}

	@Test
	void whenFindAllThenReturnAnListOfUsers() {
		when(repository.findAll()).thenReturn(List.of(user));
		
		List<User> response = service.findAll();
		
		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(User.class, response.get(0).getClass());
		
		assertEquals(ID, response.get(INDEX).getId());
		assertEquals(NAME, response.get(INDEX).getName());
		assertEquals(EMAIL, response.get(INDEX).getEmail());
		assertEquals(PASSWORD, response.get(INDEX).getPassword());
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}
	
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
	}
	
	@Test
	void whenFindByIdThenReturnAnObjectNotFoundException() {
		when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
		
		try {
			service.findById(ID);
		} catch (Exception ex) {
			assertEquals(ObjectNotFoundException.class, ex.getClass());
			assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
		}
		
	}

}
