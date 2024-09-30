package com.example.web.services;

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
		Zoo newZoo = new Zoo(input.getCity(), input.getState(), input.getCountry(), input.getCapacity(), input.getInaugration(), input.getUserId());
		return zooRepo.save(newZoo);
	}
}
