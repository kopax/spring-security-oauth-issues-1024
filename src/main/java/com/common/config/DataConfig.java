/*
 * Kopax Ltd Copyright (c) 2016.
 */

package com.common.config;

import com.domain.userManagement.Manager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mybatis.domains.AuditDateAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;

/**
 * Created by dka on 12/25/16.
 */
@Configuration
public class DataConfig {

	@Bean
	public AuditDateAware<Instant> auditDateAware() {
		return new AuditDateAware<Instant>() {
			@Override
			public Instant getCurrentDate() {
				return Instant.now();
			}
		};
	}

	@Bean
	public AuditorAware<Long> auditorAware() {
		return new AuditorAware<Long>() {
			@Override
			public Long getCurrentAuditor() {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

				if (authentication == null || !authentication.isAuthenticated()) {
					return null;
				}

				Manager manager = null;
				Object principal = authentication.getPrincipal();

				if (principal.getClass().equals(Manager.class)) {
					manager = (Manager) principal;
				}

				if (null == manager) {
					return null;
				}

				return manager.getId();
			}
		};
	}

}
