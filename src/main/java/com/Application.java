package com;

import com.common.store.HttpPathStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mybatis.repository.config.EnableMybatisRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@EnableAutoConfiguration
@RestController
@SpringBootApplication
@EnableMybatisRepositories(
		value = "com.myapp.api",
		mapperLocations = "classpath*:/mappers/*Mapper.xml"
)
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		org.apache.ibatis.logging.LogFactory.useSlf4jLogging();
		logger.debug("Starting app with SpringApplication.run");
		SpringApplication.run(Application.class, args);
		logger.info("application api started!");
	}

	@RequestMapping(value = HttpPathStore.PING, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> ping() throws Exception {
		HashMap<String, Object> map = new HashMap<>();
		map.put("message", "Welcome to our API");
		map.put("date", new Date());
		map.put("status", HttpStatus.OK);
		return new ResponseEntity(map, HttpStatus.OK);
	}

}