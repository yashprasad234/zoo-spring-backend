//package com.example.web.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
//
//public abstract class AbstractController<T>
//{
//	@Autowired
//	CrudRepository<T, Integer> repository;
//	
//	void save(T entity)
//	{
//		System.out.println("Save called from abstract controller");
//		repository.save(entity);
//	}
//	
//	void delete(T entity)
//	{
//		System.out.println("Delete called from abstract controller");
//		repository.delete(entity);
//	}
//}
