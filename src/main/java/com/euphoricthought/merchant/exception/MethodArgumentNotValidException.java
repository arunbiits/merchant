/**
 * 
 */
package com.euphoricthought.merchant.exception;

/**
 * @author bosco
 *
 */
@SuppressWarnings("serial")
public class MethodArgumentNotValidException extends RuntimeException{

	public MethodArgumentNotValidException(String message) {
		super(message);
	}

}
