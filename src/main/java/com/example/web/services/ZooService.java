package com.example.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.web.dto.ZooDTO.ZooInputs;
import com.example.web.dto.ZooDTO.ZooPaginationRes;
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
	
	public Zoo findZooById(int id) {
		return zooRepo.findById(id);
	}
	
	public ZooPaginationRes fetchSomeZoosFromPage(Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Zoo> zooPage = zooRepo.findSizeFromPage(pageable);
		List<Zoo> zoos = zooPage.getContent();
		ZooPaginationRes res = new ZooPaginationRes(zoos, zooPage.getTotalPages());
		return res;
	}
	
	public Boolean exists(Integer id) {
		return zooRepo.existsById(id);
	}
	
	public void deleteZoo(Integer id) {
		zooRepo.deleteById(id);
		return;
	}
}
