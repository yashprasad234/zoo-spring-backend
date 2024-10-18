package com.example.web.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.web.entities.AnimalHistory;

public interface AnimalHistoryRepository extends CrudRepository<AnimalHistory, Integer> {
	List<AnimalHistory> findAll();
}
