/**
 * 
 */
package com.euphoricthought.merchant.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author bosco
 *
 */
public class Login {

	@ApiModelProperty(position = 1)
	private String username;

	@ApiModelProperty(position = 2)
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
