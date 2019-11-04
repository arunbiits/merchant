/**
 * 
 */
package com.euphoricthought.merchant.model;

import javax.validation.constraints.NotNull;

/**
 * @author bosco
 *
 */
public class SearchMerchant {

	@NotNull(message="Merchant Type should not be null")
	private String merchantType;
	
	@NotNull(message="User Latitude should not be null")
	private Double userLatitude;
	
	@NotNull(message="User Longitude should not be null")
	private Double userLongitude;
	
	private Double maxDistance;
	
	public SearchMerchant() {
		
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public Double getUserLatitude() {
		return userLatitude;
	}

	public void setUserLatitude(Double userLatitude) {
		this.userLatitude = userLatitude;
	}

	public Double getUserLongitude() {
		return userLongitude;
	}

	public void setUserLongitude(Double userLongitude) {
		this.userLongitude = userLongitude;
	}

	public Double getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(Double maxDistance) {
		this.maxDistance = maxDistance;
	}

	
}
