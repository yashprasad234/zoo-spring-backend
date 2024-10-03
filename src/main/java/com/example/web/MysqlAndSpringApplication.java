package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.web.controllers.UserController;
import com.example.web.controllers.ZooController;

@SpringBootApplication
public class MysqlAndSpringApplication {
	@Autowired
	UserController userController;
	
	@Autowired
	ZooController zooController;
	
	
	public static void main(String[] args) {
		SpringApplication.run(MysqlAndSpringApplication.class, args);
	}	
}
