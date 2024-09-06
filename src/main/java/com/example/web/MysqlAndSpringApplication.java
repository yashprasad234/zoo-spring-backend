package com.example.web;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MysqlAndSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlAndSpringApplication.class, args);
	}
	

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
	
}
