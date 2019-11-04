package com.euphoricthought.merchant.util;

import java.util.List;

import com.euphoricthought.merchant.dto.NearerMerchant;
import com.euphoricthought.merchant.exception.ExceptionBean;
import com.euphoricthought.merchant.model.Merchant;
import com.euphoricthought.merchant.model.User;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("ResponseModelFilter")
public class ResponseModel {

	public ExceptionBean info;
	
	public List<Merchant> merchants;
	
	public Merchant merchant;
	
	public List<User> users;
	
	public User user;
	
	public List<NearerMerchant> nearerMerchants;

	public ExceptionBean getInfo() {
		return info;
	}

	public void setInfo(ExceptionBean info) {
		this.info = info;
	}

	public List<Merchant> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<Merchant> merchants) {
		this.merchants = merchants;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<NearerMerchant> getNearerMerchants() {
		return nearerMerchants;
	}

	public void setNearerMerchants(List<NearerMerchant> nearerMerchants) {
		this.nearerMerchants = nearerMerchants;
	}
	
	
}
