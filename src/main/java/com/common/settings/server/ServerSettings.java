package com.common.settings.server;

import com.common.settings.server.session.SessionSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dka on 3/9/17.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(value = "server")
public class ServerSettings {

	private Integer port;

	@Autowired
	private SessionSettings sessionSettings;

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public SessionSettings getSessionSettings() {
		return sessionSettings;
	}

	public void setSessionSettings(SessionSettings sessionSettings) {
		this.sessionSettings = sessionSettings;
	}

}
