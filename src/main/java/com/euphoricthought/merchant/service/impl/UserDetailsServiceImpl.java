package com.euphoricthought.merchant.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.euphoricthought.merchant.model.User;
import com.euphoricthought.merchant.model.UserPrincipal;
import com.euphoricthought.merchant.respository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByUsername(username);
		if(userOptional.isPresent()) {
			UserPrincipal userPrincipal = new UserPrincipal(userOptional.get()); 
			return userPrincipal;
		}
		return null;
	}

}
