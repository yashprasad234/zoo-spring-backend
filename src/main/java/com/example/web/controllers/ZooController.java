package com.example.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.ZooDTO.ZooInputs;
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
	
	@GetMapping("/list")
	public ResponseEntity<?> fetchAllZoos(@RequestHeader(value="Authorization") String authHeader ) {
		if(authHeader == null) return ResponseEntity.status(400).body("No Auth Header Sent");
		List<Zoo> zooList = zooService.fetchAllZoos();
		return ResponseEntity.status(200).body(zooList);
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('SUPERADMIN')")
	@GetMapping("/id/{id}")
	public ResponseEntity<?> fetchZoo(@PathVariable Integer id) {
		Optional<Zoo> fetchedZoo = zooService.findZooById(id);
		if(fetchedZoo == null) return ResponseEntity.status(404).body("No zoo exists with the given Id");
		return ResponseEntity.status(200).body(fetchedZoo);
	}
}
