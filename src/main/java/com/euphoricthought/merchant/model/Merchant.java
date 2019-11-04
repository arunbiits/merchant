package com.euphoricthought.merchant.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document
public class Merchant {

	@Id
	@ApiModelProperty(hidden=true)
	private String id;
	
	@NotNull(message="Merchant Code should not be null")
	@NotBlank(message="Merchant Code cant be blank")
	@NotEmpty(message="Merchant Code cant be empty")
	private String merchantCode;
	
	@NotNull(message="Legal name should not be null")
	@NotBlank(message="Legal name cant be blank")
	@NotEmpty(message="Legal name cant be empty")
	private String legalName;
	
	@NotNull(message="Type should not be null")
	@NotBlank(message="Type cant be blank")
	@NotEmpty(message="Type cant be empty")
	private String type;
	
	@NotNull(message="Famous for should not be null")
	@NotBlank(message="Famous for cant be blank")
	@NotEmpty(message="Famous for cant be empty")
	private String famousFor;
	
	@NotNull(message="Merchant password should not be null")
	@NotBlank(message="Merchant password cant be blank")
	@NotEmpty(message="Merchant password cant be empty")
	private String merchantPassword;
	
	@NotNull(message="Address should not be null")
	@NotBlank(message="Address cant be blank")
	@NotEmpty(message="Address cant be empty")
	private String address;
	
	@NotNull(message="City should not be null")
	@NotBlank(message="City cant be blank")
	@NotEmpty(message="City cant be empty")
	private String city;
	
	@NotNull(message="State should not be null")
	@NotBlank(message="State cant be blank")
	@NotEmpty(message="State cant be empty")
	private String state;
	
	@GeoSpatialIndexed(type=GeoSpatialIndexType.GEO_2DSPHERE)
	@ApiModelProperty(hidden=true)
	private GeoJsonPoint merchantLocation;
	
	@NotNull(message="Merchant Lat-Lang should not be null")
	@Transient
	private MerchantLocation merchantLatLang;

	public Merchant() {
		// TODO Auto-generated constructor stub
	}
	
	public Merchant(String id,
			@NotNull(message = "Merchant Code should not be null") @NotBlank(message = "Merchant Code cant be blank") @NotEmpty(message = "Merchant Code cant be empty") String merchantCode,
			@NotNull(message = "Legal name should not be null") @NotBlank(message = "Legal name cant be blank") @NotEmpty(message = "Legal name cant be empty") String legalName,
			@NotNull(message = "Type should not be null") @NotBlank(message = "Type cant be blank") @NotEmpty(message = "Type cant be empty") String type,
			@NotNull(message = "Famous for should not be null") @NotBlank(message = "Famous for cant be blank") @NotEmpty(message = "Famous for cant be empty") String famousFor,
			@NotNull(message = "Merchant password should not be null") @NotBlank(message = "Merchant password cant be blank") @NotEmpty(message = "Merchant password cant be empty") String merchantPassword,
			@NotNull(message = "Address should not be null") @NotBlank(message = "Address cant be blank") @NotEmpty(message = "Address cant be empty") String address,
			@NotNull(message = "City should not be null") @NotBlank(message = "City cant be blank") @NotEmpty(message = "City cant be empty") String city,
			@NotNull(message = "State should not be null") @NotBlank(message = "State cant be blank") @NotEmpty(message = "State cant be empty") String state,
			GeoJsonPoint location,
			@NotNull(message = "Merchant Lat-Lang should not be null") @NotBlank(message = "Merchant Lat-Lang cant be blank") @NotEmpty(message = "Merchant Lat-Lang cant be empty") MerchantLocation merchantLatLang) {
		super();
		this.id = id;
		this.merchantCode = merchantCode;
		this.legalName = legalName;
		this.type = type;
		this.famousFor = famousFor;
		this.merchantPassword = merchantPassword;
		this.address = address;
		this.city = city;
		this.state = state;
		this.merchantLatLang = merchantLatLang;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFamousFor() {
		return famousFor;
	}

	public void setFamousFor(String famousFor) {
		this.famousFor = famousFor;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public GeoJsonPoint getMerchantLocation() {
		return merchantLocation;
	}

	public void setMerchantLocation(GeoJsonPoint merchantLocation) {
		this.merchantLocation = merchantLocation;
	}

	public MerchantLocation getMerchantLatLang() {
		return merchantLatLang;
	}

	public void setMerchantLatLang(MerchantLocation merchantLatLang) {
		this.merchantLatLang = merchantLatLang;
	}

	@Override
	public String toString() {
		return "Merchant [id=" + id + ", merchantCode=" + merchantCode + ", legalName=" + legalName + ", type=" + type
				+ ", famousFor=" + famousFor + ", merchantPassword=" + merchantPassword + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", merchantLocation=" + merchantLocation
				+ ", merchantLatLang=" + merchantLatLang + "]";
	}

	
	
	
}
