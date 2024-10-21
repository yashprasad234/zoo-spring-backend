package com.example.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web.entities.Animal;
import com.example.web.entities.AnimalHistory;
import com.example.web.entities.User;
import com.example.web.entities.Zoo;
import com.example.web.repositories.AnimalHistoryRepository;

@Service
public class AnimalHistoryService {
	
	@Autowired 
	private AnimalHistoryRepository animalHistoryRepo;
	
	public AnimalHistory create(Zoo from_zoo, Zoo to_zoo, Animal animal, User user) {
		AnimalHistory newHistory = new AnimalHistory(from_zoo, to_zoo, animal, user);
		animalHistoryRepo.save(newHistory);
		return newHistory;
	};
	
	public List<AnimalHistory> list() {
		return animalHistoryRepo.findAll();
	}
	
	public List<AnimalHistory> findAnimalHistories(Animal animal) {
		return animalHistoryRepo.findByAnimal_id(animal);
	}
	
	public void deleteAnimalHistory(AnimalHistory animalHistory) {
		animalHistoryRepo.delete(animalHistory);
		return;
	}
}
