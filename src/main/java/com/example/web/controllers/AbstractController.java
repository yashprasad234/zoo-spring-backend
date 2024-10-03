package com.example.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public abstract class AbstractController<T>
{
	@Autowired
	CrudRepository<T, Integer> repository;
	
	void save(T entity)
	{
		repository.save(entity);
	}
	
	void delete(T entity)
	{
		repository.delete(entity);
	}
	
	public T getUser(Integer id) {
		return repository.findById(id).get();
	}
}
