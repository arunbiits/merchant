package com.euphoricthought.merchant.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

public class Auditing {

	@CreatedBy
	private String createdBy;
	
	@CreatedDate
	private Instant createdDate;
	
	@LastModifiedBy
	private String lastModifiedBy;
	
	@LastModifiedDate
	private Instant lastModifiedDate;
	
}
