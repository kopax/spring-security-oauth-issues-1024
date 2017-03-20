package com.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.nio.charset.Charset;
import java.util.TimeZone;

public class SystemPropertyDefaultsInitializer implements WebApplicationInitializer {

	private static final Logger logger = LoggerFactory.getLogger(SystemPropertyDefaultsInitializer.class);

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		logger.info("SystemPropertyWebApplicationInitializer onStartup called");

		// can be set runtime before Spring instantiates any beans
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		// cannot override encoding in Spring at runtime as some strings have already been read
		// however, we can assert and ensure right values are loaded here

		// verify system property is set
		Assert.isTrue("UTF-8".equals(System.getProperty("file.encoding")), "File encoding is UTF-8");

		// and actually verify it is being used
		Charset charset = Charset.defaultCharset();
		Assert.isTrue(charset.equals(Charset.forName("UTF-8")), "charset if UTF-8");

		// locale
		// set and verify language

	}

}