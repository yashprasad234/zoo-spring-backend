package com.example.web.services;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class CrudServiceImpl<T> implements CrudService<T, Integer> {

	private final CrudRepository<T, Integer> repository;
	
	public CrudServiceImpl(CrudRepository<T, Integer> repository) {
        this.repository = repository;
    }
	
	@Override
	public Iterable<T> getAll() {
		return repository.findAll();
	}

	@Override
	public Optional<T> getById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public T create(T entity) {
		return repository.save(entity);
	}

	@Override
	public T update(Integer id, T entity) {
		if (!repository.existsById(id)) {
            throw new RuntimeException("Resource not found with id: " + id);
        }
        return repository.save(entity);
	}

	@Override
	public void delete(Integer id) {
		if(!repository.existsById(id)) {
			throw new RuntimeException("Resource not found with id: " + id);
		}
		repository.deleteById(id);
		System.out.println("Deleted successfully");
	}

}
