package com.example.test7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Test7Application extends SpringBootServletInitializer {
	private static Logger logger = LoggerFactory.getLogger("test7");
	
	public Test7Application () {
    	logger.info("Test7Application constructor...");
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.info("Test7Application.configure()...");
		return application.sources(Test7Application.class);
	}
	
	public static void main(String[] args) {
		logger.info("Test7Application.main()...");
		SpringApplication.run(Test7Application.class, args);
	}
}
