
package com.common.settings;

import com.common.settings.server.ServerSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dka on 3/9/17.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class AppSettings {

	@Autowired
	private ServerSettings serverSettings;

	public ServerSettings getServerSettings() {
		return serverSettings;
	}

	public void setServerSettings(ServerSettings serverSettings) {
		this.serverSettings = serverSettings;
	}
}
