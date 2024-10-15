package com.example.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.entities.Role;
import com.example.web.services.CrudService;

@RestController
@RequestMapping("/role")
public class RoleController extends CrudController<Role, Integer> {
	protected RoleController(CrudService<Role, Integer> service) {
		super(service);
	}
}
