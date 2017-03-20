
package com.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * A simple configuration class for the web application.
 *
 * @author cdelashmutt
 */
@Configuration
public class RepositoryRestConfig extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureJacksonObjectMapper(ObjectMapper mapper) {
		super.configureJacksonObjectMapper(mapper);
		mapper.registerModule(new JavaTimeModule());
		mapper.disable( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS );
	}

}
