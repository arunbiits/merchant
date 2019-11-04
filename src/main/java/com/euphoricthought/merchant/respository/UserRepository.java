package com.euphoricthought.merchant.respository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.euphoricthought.merchant.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	Optional<User> findByUsername(String username);

}
