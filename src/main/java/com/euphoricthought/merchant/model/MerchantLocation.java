package com.euphoricthought.merchant.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MerchantLocation {

	@NotNull
	@NotBlank
	@NotEmpty
	private Double latitude;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private Double longitude;
	
	public MerchantLocation() {
		// TODO Auto-generated constructor stub
	}
	
	public MerchantLocation(@NotNull @NotBlank @NotEmpty Double latitude, @NotNull @NotBlank @NotEmpty Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}



	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	
	
}
