package com.example.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web.dto.AnimalDTO.AnimalInputs;
import com.example.web.entities.Animal;
import com.example.web.entities.Zoo;
import com.example.web.repositories.AnimalRepository;

@Service
public class AnimalService {
	@Autowired
	private AnimalRepository animalRepo;
	
	// Zoo zoo, String name, String gender, String species, String habitat, Integer userId
	public Animal createAnimal(AnimalInputs input) {
		Zoo zoo = new Zoo();
		zoo.setId(input.getZooId());
		Animal newAnimal = new Animal(zoo, input.getName(), input.getGender(), input.getAnimalImg(), input.getSpecies(), input.getHabitat(), input.getUserId());
		animalRepo.save(newAnimal);
		return newAnimal;
	}
	
	public Animal getAnimalById(int id) {
		return animalRepo.findById(id);
	}
	
	public List<Animal> findAllAnimals() {
		return animalRepo.findAll();
	}
	
	public void deleteAnimalById(Integer id) {
		animalRepo.deleteById(id);
	}
	
	public Animal saveUpdatedAnimal(Animal animal) {
		return animalRepo.save(animal);
	}
}
