/**
 * 
 */
package com.euphoricthought.merchant.service;

import org.springframework.http.ResponseEntity;

import com.euphoricthought.merchant.model.Merchant;
import com.euphoricthought.merchant.model.SearchMerchant;

/**
 * @author bosco
 *
 */
public interface MerchantService {

	public ResponseEntity<Object> saveMerchant(Merchant merchant);

	public ResponseEntity<Object> updateMerchant(Merchant merchant);
	
	public ResponseEntity<Object> findMerchantById(String id);
	
	public ResponseEntity<Object> deleteMerchantById(String id);
	
	public ResponseEntity<Object> findAllMerchants();
	
	public ResponseEntity<Object> findNearerMerchants(SearchMerchant search);
	
}
