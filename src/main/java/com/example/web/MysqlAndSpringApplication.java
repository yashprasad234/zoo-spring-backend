package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.web.controllers.AbstractController;
import com.example.web.controllers.UserController;
import com.example.web.controllers.ZooController;

@SpringBootApplication
public class MysqlAndSpringApplication implements CommandLineRunner
{
	@Autowired
	UserController userController;
	
	@Autowired
	ZooController zooController;
	
	
	public static void main(String[] args) {
		SpringApplication.run(MysqlAndSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		
	}
	
	public void printMapping(AbstractController<?> abstractController)
	{	
		abstractController.log();
	}
	
}
