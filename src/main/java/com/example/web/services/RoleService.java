package com.example.web.services;

import org.springframework.stereotype.Service;

import com.example.web.entities.Role;
import com.example.web.repositories.RoleRepository;

@Service
public class RoleService extends CrudServiceImpl<Role>{
	public RoleService(RoleRepository repository) {
        super(repository);
    }
}
