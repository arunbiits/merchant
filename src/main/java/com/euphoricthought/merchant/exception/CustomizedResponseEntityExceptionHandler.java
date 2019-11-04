/**
 * 
 */
package com.euphoricthought.merchant.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.euphoricthought.merchant.exception.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author bosco
 *
 */
@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(MerchantNotFoundException.class)
	public final ResponseEntity<Object> handleMerchantNotFoundException(Exception ex, WebRequest request) throws Exception { 
		ExceptionBean exception = new ExceptionBean(new Date(),ex.getMessage(),request.getDescription(false).toString());
		return new ResponseEntity<Object>(exception, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ExceptionBean exception = new ExceptionBean(new Date(),ex.getMessage(),request.getDescription(false).toString());
		return new ResponseEntity<Object>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionBean exception = new ExceptionBean(new Date(),ex.getMessage(),request.getDescription(false).toString());
		return new ResponseEntity<Object>(exception, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ThrottlingException.class)
	public final ResponseEntity<Object> handleThrottlingException(Exception ex, WebRequest request) throws Exception { 
		ExceptionBean exception = new ExceptionBean(new Date(),ex.getMessage(),request.getDescription(false).toString());
		return new ResponseEntity<>(exception, HttpStatus.TOO_MANY_REQUESTS);
	}
	
}
