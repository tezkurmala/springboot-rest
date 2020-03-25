package com.boot.accellearn.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.boot.accellearn.user.UserNotFoundException;

@RestController
@ControllerAdvice //Tez notes: Common component across all controllers
//This class is required if we do not want general 
//exception response structure but something for company like AccelExceptionResponse.
//Otherwise, UserNotFoundException having @ResponseStatus(HttpStatus.NOT_FOUND) 
//would allow change the Http response status.
public class AccelReponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAccelException(Exception ex, WebRequest request) throws Exception {
		AccelExceptionResponse response  = new AccelExceptionResponse(
				new Date(), 
				ex.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		AccelExceptionResponse response  = new AccelExceptionResponse(
				new Date(), 
				ex.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	}
	
	//Tez notes: We override this to inform user that the request has failed because invalid inputs are provided in body.
	//An example is user name not being 2 characters. Otherwise, we would just know that it is bad request but would
	//not know what the request is bad.
	//The ex.getBindingResult would help provide more information.
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		AccelExceptionResponse response  = new AccelExceptionResponse(
				new Date(), 
				"Validation Failed", 
				ex.getBindingResult().toString());
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
}
