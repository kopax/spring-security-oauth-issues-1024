package com.common.config;

import com.domain.security.SiteService;
import com.domain.companyManagement.Company;
import com.domain.customerManagement.Customer;
import com.domain.userManagement.Manager;
import com.domain.userManagement.OAuthClient;
import com.domain.userManagement.Role;
import com.common.typeHandler.CommaSeparatedTypeHandler;
import com.common.typeHandler.JsonMapTypeHandler;
import com.myapp.typeHandler.RoleListTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.*;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

	private static final Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);

	@Autowired
	private DataSourceConfig dataV1DataSourceConfig;

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataV1DataSourceConfig.dataSource());
		sessionFactory.setTypeHandlers(new TypeHandler[] {
		    new InstantTypeHandler(),
			new LocalDateTimeTypeHandler(),
			new LocalDateTypeHandler(),
			new LocalTimeTypeHandler(),
			new OffsetDateTimeTypeHandler(),
			new OffsetTimeTypeHandler(),
			new ZonedDateTimeTypeHandler(),
			new YearTypeHandler(),
		    new MonthTypeHandler(),
			new RoleListTypeHandler(),
			new CommaSeparatedTypeHandler(),
			new JsonMapTypeHandler(),
		});
		sessionFactory.setTypeAliases(new Class[] {
			Role.class,
			Manager.class,
			OAuthClient.class,
			Customer.class,
			Company.class,
			SiteService.class,
		});
		return sessionFactory;
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}