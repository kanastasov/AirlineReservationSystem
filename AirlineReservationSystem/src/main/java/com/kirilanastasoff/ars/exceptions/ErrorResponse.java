package com.kirilanastasoff.ars.exceptions;

import lombok.Data;

@Data
public class ErrorResponse {

	private int errorCode;
	private String message;
	
}
