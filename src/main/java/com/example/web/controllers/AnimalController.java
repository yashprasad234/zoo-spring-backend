package com.example.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.AnimalDTO.AnimalInputs;
import com.example.web.entities.Animal;
import com.example.web.entities.Zoo;
import com.example.web.services.AnimalService;
import com.example.web.services.ZooService;



@RestController
@RequestMapping("/animal")
public class AnimalController {
	
	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private ZooService zooService;
	
	@PostMapping("/create")
	public ResponseEntity<Animal> create(@RequestBody AnimalInputs input) {
		Animal newAnimal = animalService.createAnimal(input);
		return ResponseEntity.status(201).body(newAnimal);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> fetchAnimals() {
		List<Animal> animals = animalService.findAllAnimals();
		return ResponseEntity.status(200).body(animals);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> fetchAnimal(@PathVariable Integer id) {
		Optional<Animal> animal = animalService.getAnimalById(id);
		if(animal == null) return ResponseEntity.status(404).body("Failed to fetch animals");
		return ResponseEntity.status(200).body(animal);
	}
	
	@GetMapping("/zoo/{id}")
	public ResponseEntity<?> fetchAnimalsForZoo(@PathVariable Integer id) {
		Optional<Zoo> zoo = zooService.findZooById(id);
		if(zoo == null) return ResponseEntity.status(404).body("No zoo with the given id exists");
		else {
			ArrayList<Animal> list = new ArrayList<>();
			List<Animal> allAnimals = animalService.findAllAnimals();
			
			for(Animal a: allAnimals) {
				if(zoo.get().getId() == a.getZoo().getId() ) {
					list.add(a);
				}
			}
			if(list.size() == 0) return ResponseEntity.status(400).body("No animals in the zoo");
			return ResponseEntity.status(200).body(list);
		}
	}
}
