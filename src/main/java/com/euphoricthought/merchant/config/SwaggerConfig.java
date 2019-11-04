/**
 * 
 */
package com.euphoricthought.merchant.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.euphoricthought.merchant.security.JwtProperties;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author bosco
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(info()).globalOperationParameters(
				Arrays.asList(new ParameterBuilder().name(JwtProperties.TOKEN_HEADER).description("Authentication Token")
				.modelRef(new ModelRef("string")).parameterType("header").required(false).build()));
	}

	public ApiInfo info() {
		return new ApiInfo("Merchant API", "RESTFul Web Service for Merchant API", "V1.0", "apache.org",
				new Contact("Arun Kumar N", "arunbiits.online", "arunbiits@gmail.com"), "apache.org", "apache.org",
				Collections.emptyList());
	}

}
