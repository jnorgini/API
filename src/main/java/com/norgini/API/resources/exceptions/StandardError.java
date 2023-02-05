package com.norgini.API.resources.exceptions;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {
	
	private LocalDate timestamp;
	private Integer status;
	private String error;
	private String path;
}
