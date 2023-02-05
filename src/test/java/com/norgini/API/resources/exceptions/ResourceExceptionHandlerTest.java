package com.norgini.API.resources.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.norgini.API.services.exceptions.DataIntegrityViolationException;
import com.norgini.API.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class ResourceExceptionHandlerTest {
	
	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
	private static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";
	
	@InjectMocks
	private ResourceExceptionHandler exceptionHandler;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void whenObjectNotFoundExceptionThenReturnAnResponseEntity() {
		ResponseEntity<StandardError> response = exceptionHandler
				.objectNotFound(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO), new MockHttpServletRequest());
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
		assertEquals(404, response.getBody().getStatus());
		assertNotEquals("/user/2", response.getBody().getPath());
		assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
	}

	@Test
	void whenDataIntegrityViolationExceptionThenReturnAnResponseEntity() {
		ResponseEntity<StandardError> response = exceptionHandler
				.dataIntegrityViolationException(
						new DataIntegrityViolationException(E_MAIL_JA_CADASTRADO_NO_SISTEMA), 
						new MockHttpServletRequest());
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, response.getBody().getError());
		assertEquals(400, response.getBody().getStatus());
	}

}
