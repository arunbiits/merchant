/**
 * 
 */
package com.euphoricthought.merchant.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author bosco
 *
 */
@Component
public class MyAppAuditor implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null) {
			return Optional.of("ADMIN");
		}
		if(authentication.getName().equals("anonymousUser")) {
			return Optional.of("ANONYMOUS_USER");
		}
		return Optional.of(authentication.getName());
	}

}
