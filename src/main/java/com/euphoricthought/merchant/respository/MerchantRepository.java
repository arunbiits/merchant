package com.euphoricthought.merchant.respository;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.euphoricthought.merchant.model.Merchant;

public interface MerchantRepository extends MongoRepository<Merchant, String>{
	
	List<Merchant> findByTypeAndMerchantLocationNear(String type, Point point, Distance d);

}
