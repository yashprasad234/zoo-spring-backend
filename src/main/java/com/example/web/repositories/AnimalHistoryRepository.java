package com.example.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.web.entities.Animal;
import com.example.web.entities.AnimalHistory;

public interface AnimalHistoryRepository extends JpaRepository<AnimalHistory, Integer> {
	List<AnimalHistory> findAll();
	List<AnimalHistory> findByAnimal_id(Animal animal_id);
}
