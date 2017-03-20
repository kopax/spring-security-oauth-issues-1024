package com.common.config;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

	private final static Logger logger = LoggerFactory.getLogger(FlywayConfig.class);

	@Autowired
	private DataSourceConfig dataV1DataSourceConfig;

	@Bean(initMethod = "migrate")
	public Flyway flyway() {
		Flyway flyway = new Flyway();
		flyway.setBaselineOnMigrate(true);
		flyway.setLocations("classpath:db/migration");
		flyway.setSqlMigrationPrefix("V");
		flyway.setSqlMigrationSuffix(".sql");
		flyway.setEncoding("UTF-8");
		flyway.setValidateOnMigrate(false);
		flyway.setOutOfOrder(true);
		flyway.setTargetAsString("0.0.9");
		flyway.setDataSource(dataV1DataSourceConfig.dataSource());
		return flyway;
	}

}
