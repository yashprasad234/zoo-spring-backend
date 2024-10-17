package com.example.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.web.entities.AnimalHistory;

public interface AnimalHistoryRepository extends CrudRepository<AnimalHistory, Integer> {
//	@Query("select a from AnimalTransferHistory a where u.animal_id = :id")
//	List<AnimalHistory> findAnimalHistory(@Param("id") int id);
}
