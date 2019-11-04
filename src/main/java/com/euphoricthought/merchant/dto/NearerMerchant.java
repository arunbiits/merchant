package com.euphoricthought.merchant.dto;

public class NearerMerchant {

	private String merchantName;
	private String merchantCode;
	private String merchantType;
	private String merchantAddress;
	private String famousFor;
	private Double distanceInMeter;
	
	public NearerMerchant() {
		// TODO Auto-generated constructor stub
	}

	public NearerMerchant(String merchantName, String merchantCode, String merchantType, String merchantAddress,
			String famousFor, Double distanceInMeter) {
		super();
		this.merchantName = merchantName;
		this.merchantCode = merchantCode;
		this.merchantType = merchantType;
		this.merchantAddress = merchantAddress;
		this.famousFor = famousFor;
		this.distanceInMeter = distanceInMeter;
	}



	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getMerchantAddress() {
		return merchantAddress;
	}

	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}

	public String getFamousFor() {
		return famousFor;
	}

	public void setFamousFor(String famousFor) {
		this.famousFor = famousFor;
	}

	public Double getDistanceInMeter() {
		return distanceInMeter;
	}

	public void setDistanceInMeter(Double distanceInMeter) {
		this.distanceInMeter = distanceInMeter;
	}

}
