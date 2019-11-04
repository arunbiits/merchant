/**
 * 
 */
package com.euphoricthought.merchant.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.euphoricthought.merchant.dto.NearerMerchant;
import com.euphoricthought.merchant.exception.ExceptionBean;
import com.euphoricthought.merchant.exception.MerchantNotFoundException;
import com.euphoricthought.merchant.model.Merchant;
import com.euphoricthought.merchant.model.MerchantLocation;
import com.euphoricthought.merchant.model.SearchMerchant;
import com.euphoricthought.merchant.respository.MerchantRepository;
import com.euphoricthought.merchant.service.MerchantService;
import com.euphoricthought.merchant.util.DistanceCalculator;
import com.euphoricthought.merchant.util.FilterUtil;
import com.euphoricthought.merchant.util.ResponseModel;
import com.euphoricthought.merchant.util.StringConstants;
import com.weddini.throttling.Throttling;
import com.weddini.throttling.ThrottlingType;

/**
 * @author bosco
 *
 */
@Service
public class MerchantServiceImpl implements MerchantService{

	@Autowired
	MerchantRepository merchantRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	ResponseModel response;

	String fields[] = null;

	MappingJacksonValue mappingJacksonValue;
	
	
	public MerchantServiceImpl() {
		response = new ResponseModel();
	}
	
	/**
	 * To save the merchant details inside the mongoDB
	 */
	@Override
	public ResponseEntity<Object> saveMerchant(Merchant merchant) {
		fields = new String[] {"info","merchant"};
		Merchant savedMerchant = null;
		merchant.setMerchantLocation(new GeoJsonPoint(merchant.getMerchantLatLang().getLatitude(), merchant.getMerchantLatLang().getLongitude()));
		if(merchant.getId() == null) {
			savedMerchant = merchantRepository.insert(merchant);
			response.setInfo(new ExceptionBean(new Date(), StringConstants.SUCCESS, StringConstants.MERCHANT_ADD_SUCCESS));
			response.setMerchant(savedMerchant);
			mappingJacksonValue = FilterUtil.filterFields(response, fields);
			return new ResponseEntity<Object>(mappingJacksonValue,HttpStatus.CREATED);
		}else {
			return updateMerchant(merchant);
		}
	}

	/**
	 * To update the mechant details inside the mongoDB
	 */
	@Override
	public ResponseEntity<Object> updateMerchant(Merchant merchant) {
		fields = new String[] {"info","merchant"};
		Merchant savedMerchant = merchantRepository.save(merchant);
		response.setInfo(new ExceptionBean(new Date(), StringConstants.SUCCESS, StringConstants.MERCHANT_ADD_SUCCESS));
		response.setMerchant(savedMerchant);
		mappingJacksonValue = FilterUtil.filterFields(response, fields);
		return new ResponseEntity<Object>(savedMerchant,HttpStatus.OK);
	}

	/**
	 * To find a merchant using id...
	 */
	@Override
	public ResponseEntity<Object> findMerchantById(String id) {
		fields = new String[] {"info","merchant"};
		Optional<Merchant> merchantOptional = merchantRepository.findById(id);
		if(!merchantOptional.isPresent()) {
			throw new MerchantNotFoundException("Merchant Not Found for the given merchant id.");
		}
		response.setInfo(new ExceptionBean(new Date(), StringConstants.DATA_FOUND, StringConstants.MERCHANT_FOUND));
		response.setMerchant(merchantOptional.get());
		mappingJacksonValue = FilterUtil.filterFields(response, fields);
		return new ResponseEntity<Object>(mappingJacksonValue,HttpStatus.OK);
	}

	/***
	 * to delete a merchant using id...
	 */
	@Override
	public ResponseEntity<Object> deleteMerchantById(String id) {
		fields = new String[] {"info"};
		merchantRepository.deleteById(id);
		Optional<Merchant> merchantOptional = merchantRepository.findById(id);
		if(!merchantOptional.isPresent()) {
			response.setInfo(new ExceptionBean(new Date(), StringConstants.SUCCESS, StringConstants.MERCHANT_DELETED));
			mappingJacksonValue = FilterUtil.filterFields(response, fields);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		return null;
	}

	/**
	 * to list all the merchants available in the database..
	 */
	@Override
	public ResponseEntity<Object> findAllMerchants() {
		fields = new String[] {"info","merchants"};
		List<Merchant> merchants = merchantRepository.findAll();
		if(!merchants.isEmpty()) {
			response.setInfo(new ExceptionBean(new Date(), StringConstants.DATA_FOUND, StringConstants.MERCHANT_FOUND));
		}else {
			merchants.stream().forEach(m -> {
				MerchantLocation location = new MerchantLocation(m.getMerchantLocation().getCoordinates().get(0),m.getMerchantLocation().getCoordinates().get(1));
				m.setMerchantLatLang(location);
			});
			response.setInfo(new ExceptionBean(new Date(), StringConstants.DATA_NOT_FOUND, StringConstants.MERCHANT_NOT_FOUND));
		}
		
		response.setMerchants(merchants);
		mappingJacksonValue = FilterUtil.filterFields(response, fields);
		return new ResponseEntity<Object>(mappingJacksonValue, HttpStatus.OK);
	}

	/**
	 * to find the nearer mechants for the given lat-lang value...
	 */
	@Override
	@Throttling(type = ThrottlingType.RemoteAddr, limit = 1, timeUnit = TimeUnit.SECONDS)
	public ResponseEntity<Object> findNearerMerchants(SearchMerchant search) {
		fields = new String[] {"info","nearerMerchants"};
		Point currentLocation = new Point(search.getUserLatitude(), search.getUserLongitude());		
		List<Merchant> merchants = merchantRepository.findByTypeAndMerchantLocationNear(search.getMerchantType(), currentLocation, new Distance(1000, Metrics.KILOMETERS));
		List<NearerMerchant> nearerMerchants = new ArrayList<>();
		merchants.stream().forEach(m -> {
			NearerMerchant nearer = new NearerMerchant();
			nearer.setMerchantName(m.getLegalName());
			nearer.setMerchantCode(m.getMerchantCode());
			nearer.setMerchantType(m.getType());
			nearer.setMerchantAddress(m.getAddress());
			nearer.setFamousFor(m.getFamousFor());
			double distance = DistanceCalculator.distance(search.getUserLatitude(), m.getMerchantLocation().getCoordinates().get(0), search.getUserLongitude(), m.getMerchantLocation().getCoordinates().get(1));
			nearer.setDistanceInMeter(distance*1000);
			nearerMerchants.add(nearer);
		});
		if(!nearerMerchants.isEmpty()) {
			response.setInfo(new ExceptionBean(new Date(),StringConstants.DATA_FOUND,StringConstants.MERCHANT_FOUND));
		}else {
			response.setInfo(new ExceptionBean(new Date(),StringConstants.DATA_NOT_FOUND, StringConstants.MERCHANT_NOT_FOUND));
		}
		response.setNearerMerchants(nearerMerchants);
		mappingJacksonValue = FilterUtil.filterFields(response, fields);
		return new ResponseEntity<Object>(mappingJacksonValue, HttpStatus.OK);
	}

	
}
