package com.euphoricthought.merchant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.euphoricthought.merchant.model.User;
import com.euphoricthought.merchant.respository.UserRepository;

@Service
public class InitDatabase implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		User admin = new User("admin", passwordEncoder.encode("admin"), "Administrator", "admin@gmail.com", "9876541230", "ADMIN", "");
		if(!userRepository.findByUsername(admin.getUsername()).isPresent()) {
			userRepository.save(admin);
		}
	}

}
