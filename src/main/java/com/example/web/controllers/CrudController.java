package com.example.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.web.services.CrudService;

public abstract class CrudController<T, I> {
	private final CrudService<T, I> service;

    protected CrudController(CrudService<T, I> service) {
        this.service = service;
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody T entity) {
    	return ResponseEntity.ok(service.create(entity));
    }
	
    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
    	Iterable<T> list = service.getAll();
    	List<T> ans = new ArrayList<>();
    	list.forEach(el -> ans.add(el));
    	if(ans.size() == 0) return ResponseEntity.status(404).body("No data in the database");
    	return ResponseEntity.status(200).body(ans);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable I id) {
    	if(!service.existsById(id)) return ResponseEntity.status(404).body("No data found with the given id");
    	return ResponseEntity.status(200).body(service.getById(id));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable I id) {
    	if(!service.existsById(id)) return ResponseEntity.status(404).body("No data found with the given id");
    	service.delete(id);
    	return ResponseEntity.status(200).body("Deleted successfully");
    }
}
