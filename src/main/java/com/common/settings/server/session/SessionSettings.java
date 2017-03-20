
package com.common.settings.server.session;

import com.common.settings.server.session.cookie.CookieSettings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dka on 3/9/17.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(value = "server.session")
public class SessionSettings {

	private Integer timeout;
	private CookieSettings cookieSettings;

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public CookieSettings getCookieSettings() {
		return cookieSettings;
	}

	public void setCookieSettings(CookieSettings cookieSettings) {
		this.cookieSettings = cookieSettings;
	}
}
