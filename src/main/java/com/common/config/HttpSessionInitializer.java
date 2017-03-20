package com.common.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class HttpSessionInitializer extends AbstractHttpSessionApplicationInitializer {

	public HttpSessionInitializer() {
		super(HttpSessionConfig.class);
	}

}
