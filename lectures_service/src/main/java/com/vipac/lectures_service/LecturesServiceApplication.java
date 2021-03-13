package com.vipac.lectures_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"com.vipac.lectures_service.controllers", "com.vipac.lectures_service.services", "com.vipac.authentication_service.configs"})
@EntityScan("com.vipac.lectures_service.domains")
@EnableMongoRepositories("com.vipac.lectures_service.repositories")
public class LecturesServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LecturesServiceApplication.class, args);
	}

}
