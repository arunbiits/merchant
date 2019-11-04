/**
 * 
 */
package com.euphoricthought.merchant.exception;

/**
 * @author bosco
 *
 */
@SuppressWarnings("serial")
public class MerchantNotFoundException extends RuntimeException{

	public MerchantNotFoundException(String message) {
		super(message);
	}
}
