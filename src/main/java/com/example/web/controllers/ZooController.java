package com.example.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.ZooDTO.ZooInputs;
import com.example.web.dto.ZooDTO.ZooPaginationRes;
import com.example.web.entities.Zoo;
import com.example.web.services.ZooService;

@RestController
@RequestMapping("/zoo")
public class ZooController
{
	
	@Autowired
	private ZooService zooService;
	
	@PreAuthorize("hasRole('SUPERADMIN')")
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ZooInputs input) {
		Zoo newZoo = zooService.createZoo(input);
		return ResponseEntity.status(201).body(newZoo);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> fetchAllZoos(@RequestHeader(value="Authorization") String authHeader ) {
		if(authHeader == null) return ResponseEntity.status(400).body("No Auth Header Sent");
		List<Zoo> zooList = zooService.fetchAllZoos();
		return ResponseEntity.status(200).body(zooList);
	}
	
	@GetMapping("/all/{id}")
	public ResponseEntity<?> fetchAllExceptOneZoos(@PathVariable int id) {
		List<Zoo> zooList = zooService.fetchAllZoos();
		List<Zoo> res = new ArrayList<Zoo>();
		for(int i = 0; i < zooList.size(); i++) {
			if(zooList.get(i).getId() != id) res.add(zooList.get(i));
		}
		if(res.size() == 0) return ResponseEntity.status(404).body("No other zoo exists!");
		return ResponseEntity.status(200).body(res);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> fetchZoosPagination(@RequestParam Integer size, @RequestParam Integer page) {
		ZooPaginationRes response = zooService.fetchSomeZoosFromPage(page, size);
		if(response.getZoos().isEmpty()) {
			return ResponseEntity.status(204).body("No more zoos to send");
		}
		return ResponseEntity.status(200).body(response);
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('SUPERADMIN')")
	@GetMapping("/id/{id}")
	public ResponseEntity<?> fetchZoo(@PathVariable Integer id) {
		Zoo fetchedZoo = zooService.findZooById(id);
		if(fetchedZoo == null) return ResponseEntity.status(404).body("No zoo exists with the given Id");
		return ResponseEntity.status(200).body(fetchedZoo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteZoo(@PathVariable Integer id) {
		if(!zooService.exists(id)) return ResponseEntity.status(404).body("No zoo exists with the given id");
		zooService.deleteZoo(id);
		return ResponseEntity.status(200).body("Deleted zoo with id: "+ id +" successfully");
	}
}
