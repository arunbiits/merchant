/**
 * 
 */
package com.euphoricthought.merchant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euphoricthought.merchant.model.Login;
import com.euphoricthought.merchant.model.User;
import com.euphoricthought.merchant.respository.UserRepository;

/**
 * @author bosco
 *
 */
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/v1/users")
	public ResponseEntity<Object> retrieveAllUsers(){
		List<User> users = userRepository.findAll();
		return new ResponseEntity<Object>(users,HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public void login(@RequestBody Login login) {
		
	}
	
}
