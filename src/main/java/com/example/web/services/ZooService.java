package com.example.web.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web.dto.ZooDTO.ZooInputs;
import com.example.web.entities.Zoo;
import com.example.web.repositories.ZooRepository;

@Service
public class ZooService {
	
	@Autowired
	private ZooRepository zooRepo;
	
	public Zoo createZoo(ZooInputs input) {
		Zoo newZoo = new Zoo(input.getUserId(), input.getName(), input.getLocation(), input.getArea(), input.getDescription());
		return zooRepo.save(newZoo);
	}
	
	public List<Zoo> fetchAllZoos() {
		return zooRepo.findAll();
	}
	
	public Optional<Zoo> findZooById(Integer id) {
		return zooRepo.findById(id);
	}
}
