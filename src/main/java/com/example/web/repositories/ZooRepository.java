package com.example.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.web.entities.Zoo;

/**
 * 
 */
public interface ZooRepository extends CrudRepository<Zoo, Integer> {
	List<Zoo> findAll();
	Optional<Zoo> findById(Integer id);
	
	@Query("SELECT z FROM Zoo z")
    Page<Zoo> findSizeFromPage(Pageable pageable);
}
