package learning.spring.boot.rest.webservices.exception.handler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import learning.spring.boot.rest.webservices.exception.PostNotAssignedException;
import learning.spring.boot.rest.webservices.exception.PostNotFoundException;
import learning.spring.boot.rest.webservices.exception.UserAdditionNotAllowedException;
import learning.spring.boot.rest.webservices.exception.UserNotFoundException;
import learning.spring.boot.rest.webservices.exception.model.GenericExceptionResponse;

@RestControllerAdvice
@RestController
public class GenericResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		GenericExceptionResponse exceptionResponse = new GenericExceptionResponse(new Date(), ex.getMessage(),
		        request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		GenericExceptionResponse exceptionResponse = new GenericExceptionResponse(new Date(), ex.getMessage(),
		        request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserAdditionNotAllowedException.class)
	public ResponseEntity<Object> handleUserAdditionNotAllowedException(UserAdditionNotAllowedException ex,
	        WebRequest request) {
		GenericExceptionResponse exceptionResponse = new GenericExceptionResponse(new Date(), ex.getMessage(),
		        request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INSUFFICIENT_STORAGE);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	        HttpHeaders headers, HttpStatus status, WebRequest request) {
		GenericExceptionResponse exceptionResponse = new GenericExceptionResponse(new Date(), "Validation Failed",
		        ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse, status);
	}

	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<Object> handlePostNotFoundException(PostNotFoundException ex, WebRequest request) {
		GenericExceptionResponse exceptionResponse = new GenericExceptionResponse(new Date(), ex.getMessage(),
		        request.getDescription(true));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PostNotAssignedException.class)
	public ResponseEntity<Object> handlePostNotAssignedException(PostNotFoundException ex, WebRequest request) {
		GenericExceptionResponse exceptionResponse = new GenericExceptionResponse(new Date(), ex.getMessage(),
		        request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}

}
