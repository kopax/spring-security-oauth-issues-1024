/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.common.config;

import com.common.settings.server.session.cookie.CookieSettings;
import com.common.store.HttpPathStore;
import com.myapp.services.userManagement.ManagerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private CookieSettings cookieSettings;

	@Autowired
	private ManagerDetailsService managerDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.managerDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.NEVER)
				.and()
			.csrf().disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, HttpPathStore.PING).permitAll()
				.anyRequest().hasRole("USER")
			.and()
				.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler)
				.authenticationEntryPoint(authenticationEntryPoint)
			.and()
				.formLogin()
				.loginProcessingUrl(HttpPathStore.LOGIN)
				.successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler)
				.permitAll()
			.and()
				.logout()
				.logoutUrl(HttpPathStore.LOGOUT)
				.logoutSuccessUrl(HttpPathStore.LOGIN_FROM_LOGOUT)
				.logoutSuccessHandler(logoutSuccessHandler)
				.permitAll();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CookieSerializer cookieSerializer() {
		DefaultCookieSerializer serializer = new DefaultCookieSerializer();
		if (null != cookieSettings.getName()) { serializer.setCookieName(cookieSettings.getName()); }
		if (null != cookieSettings.getPath()) { serializer.setCookiePath(cookieSettings.getPath()); }
		if (null != cookieSettings.getHttpOnly()) { serializer.setUseHttpOnlyCookie(cookieSettings.getHttpOnly()); }
		if (null != cookieSettings.getMaxAge()) { serializer.setCookieMaxAge(cookieSettings.getMaxAge()); }
		if (null != cookieSettings.getSecure()) { serializer.setUseSecureCookie(cookieSettings.getSecure()); }
		if (null != cookieSettings.getDomain()) { serializer.setDomainName(cookieSettings.getDomain()); }
		return serializer;
	}

}