/**
 * 
 */
package com.euphoricthought.merchant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euphoricthought.merchant.exception.MethodArgumentNotValidException;
import com.euphoricthought.merchant.model.Merchant;
import com.euphoricthought.merchant.model.SearchMerchant;
import com.euphoricthought.merchant.service.MerchantService;

/**
 * @author bosco
 *
 */
@RestController
@RequestMapping("/api")
public class MerchantController {

	@Autowired
	MerchantService merchantService;
	
	@GetMapping("/v1/merchants")
	public ResponseEntity<Object> retrieveAllMerchants() {
		return merchantService.findAllMerchants();
	}
	
	@PostMapping("/v1/merchants")
	public ResponseEntity<Object> saveMerchant(@Valid @RequestBody Merchant merchant, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException("Validation Failed");
		}
		return merchantService.saveMerchant(merchant);
	}
	
	@GetMapping("/v1/merchants/{id}")
	public ResponseEntity<Object> findMerchantById(@PathVariable String id){
		return merchantService.findMerchantById(id);
	}
	
	@DeleteMapping("/v1/merchants/{id}")
	public ResponseEntity<Object> deleteMerchantById(@PathVariable String id){
		return merchantService.deleteMerchantById(id);
	}
	
	@PostMapping("/v1/merchants/search")
	public ResponseEntity<Object> findNearestMerchants(@Valid @RequestBody SearchMerchant search,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException("Validation Failed");
		}
		return merchantService.findNearerMerchants(search);
	}
	
}
