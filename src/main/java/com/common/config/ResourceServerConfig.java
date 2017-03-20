package com.common.config;

import com.common.store.HttpPathStore;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.io.IOException;

@Configuration
@EnableResourceServer
//@Order(99)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	public static final String RESOURCE_ID = "myapp/api";

	@Autowired
	private DataSourceConfig dataV1DataSourceConfig;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**") // ### Specify path pattern that need OAuth authentication(Bearer auth) and authorization
				.requestMatchers().antMatchers(
						"/**"
					).and()
				.authorizeRequests()
					.antMatchers(HttpMethod.GET, HttpPathStore.PING).permitAll()
				.anyRequest()
					.access("#oauth2.hasScope('write') " +
							"and #oauth2.clientHasRole('ROLE_CLIENT') " +
							"and hasRole('USER')");
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenServices(tokenServices()).resourceId(RESOURCE_ID);
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		defaultTokenServices.setAuthenticationManager(authenticationManager);
		return defaultTokenServices;
	}

	@Bean public TokenStore tokenStore() { return new JdbcTokenStore(dataV1DataSourceConfig.dataSource()); }

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		final Resource resource = new ClassPathResource("public.txt");
		String publicKey = null;
		try {
			publicKey = IOUtils.toString(resource.getInputStream());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
		converter.setVerifierKey(publicKey);
		return converter;
	}

}
